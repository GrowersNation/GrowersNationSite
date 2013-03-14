package org.growersnation.site.resources;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.yammer.dropwizard.views.View;
import org.growersnation.site.dao.security.UserDao;
import org.growersnation.site.model.security.Authority;
import org.growersnation.site.model.security.User;
import org.growersnation.site.model.view.BaseModel;
import org.growersnation.site.views.PrivateFreemarkerView;
import org.growersnation.site.views.PublicFreemarkerView;
import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.MessageException;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for public home page</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Path("/openid")
@Produces(MediaType.TEXT_HTML)
public class PublicOpenIDResource extends BaseResource {

  private static final Logger log = LoggerFactory.getLogger(PublicOpenIDResource.class);

  private final static String YAHOO_ENDPOINT = "https://me.yahoo.com";
  private final static String GOOGLE_ENDPOINT = "https://www.google.com/accounts/o8/id";

  public final ConsumerManager manager;

  /**
   * @param userDao The security DAO
   */
  @Inject
  public PublicOpenIDResource(UserDao userDao) {
    super(userDao);
    // Proxy configuration must come before ConsumerManager construction
//    ProxyProperties proxyProps = new ProxyProperties();
//    proxyProps.setProxyHostName("some-proxy");
//    proxyProps.setProxyPort(8080);
//    HttpClientFactory.setProxyProperties(proxyProps);

    this.manager = new ConsumerManager();
  }

  /**
   * @return A login view with a session token
   */
  @GET
  @Path("/login")
  public View login() {
    BaseModel model = new BaseModel();
    return new PublicFreemarkerView<BaseModel>("openid/login.ftl", model);
  }

  /**
   * @return A login view with a session token
   */
  @GET
  @Path("/logout")
  public Response logout() {

    BaseModel model = newBaseModel();
    User user = model.getUser();
    if (user != null) {
      user.setSessionToken(null);
      userDao.saveOrUpdate(user);
      model.setUser(null);
    }

    View view = new PublicFreemarkerView<BaseModel>("common/home.ftl", model);

    // Remove the session token which will have the effect of logout
    return Response
      .ok()
      .cookie(replaceSessionTokenCookie(Optional.<User>absent()))
      .entity(view)
      .build();

  }

  /**
   * Handles the authentication request from the user after they select their OpenId server
   *
   * @param identifier The identifier for the OpenId server
   *
   * @return A redirection or a form view containing user-specific permissions
   */
  @POST
  public Response authenticationRequest(
    @FormParam("identifier")
    String identifier
  ) {

    UUID sessionToken = UUID.randomUUID();

    try {

      // The OpenId server will use this endpoint to provide authentication
      // Parts of this may be shown to the user
      final String returnToUrl;
      if (request.getServerPort() == 80) {
        returnToUrl = String.format(
          "http://%s/openid/verify?token=%s",
          request.getServerName(),
          sessionToken);
      } else {
        returnToUrl = String.format(
          "http://%s:%d/openid/verify?token=%s",
          request.getServerName(),
          request.getServerPort(),
          sessionToken);
      }

      log.debug("Return to URL '{}'", returnToUrl);

      // Perform discovery on the user-supplied identifier
      List discoveries = manager.discover(identifier);

      // Attempt to associate with the OpenID provider
      // and retrieve one service endpoint for authentication
      DiscoveryInformation discovered = manager.associate(discoveries);

      // Create a temporary User to preserve state between requests without
      // using a session (we could be in a cluster)
      User tempUser = new User();
      tempUser.setOpenIDDiscoveryInformation(discovered);
      tempUser.setSessionToken(sessionToken);
      userDao.saveOrUpdate(tempUser);

      // Build the AuthRequest message to be sent to the OpenID provider
      AuthRequest authReq = manager.authenticate(discovered, returnToUrl);

      // Build the FetchRequest containing the information to be copied
      // from the OpenID provider
      FetchRequest fetch = FetchRequest.createFetchRequest();
      // Attempt to decode each entry
      if (identifier.startsWith(GOOGLE_ENDPOINT)) {
        fetch.addAttribute("email", "http://axschema.org/contact/email", true);
        fetch.addAttribute("firstName", "http://axschema.org/namePerson/first", true);
        fetch.addAttribute("lastName", "http://axschema.org/namePerson/last", true);
      } else if (identifier.startsWith(YAHOO_ENDPOINT)) {
        fetch.addAttribute("email", "http://axschema.org/contact/email", true);
        fetch.addAttribute("fullname", "http://axschema.org/namePerson", true);
      } else { // works for myOpenID
        fetch.addAttribute("fullname", "http://schema.openid.net/namePerson", true);
        fetch.addAttribute("email", "http://schema.openid.net/contact/email", true);
      }

      // Attach the extension to the authentication request
      authReq.addExtension(fetch);

      // Redirect the user to their OpenId server authentication process
      return Response
        .seeOther(URI.create(authReq.getDestinationUrl(true)))
        .build();

    } catch (MessageException e1) {
      log.error("MessageException:", e1);
    } catch (DiscoveryException e1) {
      log.error("DiscoveryException:", e1);
    } catch (ConsumerException e1) {
      log.error("ConsumerException:", e1);
    }
    return Response.ok().build();
  }

  /**
   * Handles the OpenId server response to the earlier AuthRequest
   *
   * @return The OpenId identifier for this user if verification was successful
   */
  @GET
  @Path("/verify")
  public Response verifyOpenIdServerResponse(@QueryParam("token") String rawToken) {

    // Retrieve the previously stored discovery information from the temporary User
    if (rawToken == null) {
      log.debug("Authentication failed due to no session token");
      throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }

    Optional<User> tempUserOptional = userDao.getBySessionToken(UUID.fromString(rawToken));
    if (!tempUserOptional.isPresent()) {
      log.debug("Authentication failed due to no temp User matching session token {}", rawToken);
      throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }

    // Must have a temporary User to be here
    User tempUser = tempUserOptional.get();

    DiscoveryInformation discovered = tempUser.getOpenIDDiscoveryInformation();
    if (discovered == null) {
      log.debug("Authentication failed due to temp User having no discovery information");
      throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }


    // Extract the receiving URL from the HTTP request
    StringBuffer receivingURL = request.getRequestURL();
    String queryString = request.getQueryString();
    if (queryString != null && queryString.length() > 0) {
      receivingURL.append("?").append(request.getQueryString());
    }
    log.debug("Receiving URL = '{}", receivingURL.toString());

    // Extract the parameters from the authentication response
    // (which comes in as a HTTP request from the OpenID provider)
    ParameterList parameterList = new ParameterList(request.getParameterMap());

    try {

      // Verify the response
      // ConsumerManager needs to be the same (static) instance used
      // to place the authentication request
      // This could be tricky if this service is load-balanced
      VerificationResult verification = manager.verify(
        receivingURL.toString(),
        parameterList,
        discovered);

      // Examine the verification result and extract the verified identifier
      Optional<Identifier> verified = Optional.fromNullable(verification.getVerifiedId());
      if (verified.isPresent()) {
        // Verified
        AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();

        // We have successfully authenticated so remove the temp user
        // and replace it with a potentially new one
        userDao.delete(tempUser);

        tempUser = new User();
        tempUser.setOpenIDIdentifier(verified.get().getIdentifier());
        tempUser.setSessionToken(UUID.randomUUID());

        // Provide a basic authority in light of successful authentication
        tempUser.getAuthorities().add(Authority.ROLE_PUBLIC);

        // Extract additional information
        if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
          tempUser.setEmailAddress(extractEmailAddress(authSuccess));
          tempUser.setFirstName(extractFirstName(authSuccess));
          tempUser.setLastName(extractLastName(authSuccess));
        }
        log.info("Extracted a temporary {}", tempUser);

        // Search for a pre-existing User matching the temp User
        Optional<User> userOptional = userDao.getByOpenIDIdentifier(tempUser.getOpenIDIdentifier());
        User user;
        if (!userOptional.isPresent()) {
          // This is either a new registration or the OpenID identifier has changed
          if (tempUser.getEmailAddress() != null) {
            userOptional = userDao.getByEmailAddress(tempUser.getEmailAddress());
            if (!userOptional.isPresent()) {
              // This is a new User
              log.debug("Registering new {}", tempUser);
              user = userDao.saveOrUpdate(tempUser);
            } else {
              // The OpenID identifier has changed so update it
              log.debug("Updating OpenID identifier for {}", tempUser);
              user = userOptional.get();
              user.setOpenIDIdentifier(tempUser.getOpenIDIdentifier());
              user = userDao.saveOrUpdate(user);
            }
          } else {
            // No email address to use as backup
            log.warn("Rejecting valid authentication. No email address for {}");
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
          }
        } else {
          // The User has been located by their OpenID identifier
          log.debug("Found an existing User using OpenID identifier {}", tempUser);
          user = userOptional.get();
        }

        // Create a suitable view for the response
        // The session token has changed so we create the base model directly
        BaseModel model = new BaseModel();
        model.setUser(user);

        // Authenticated
        View view = new PrivateFreemarkerView<BaseModel>("private/home.ftl", model);

        // Refresh the session token cookie
        return Response
          .ok()
          .cookie(replaceSessionTokenCookie(Optional.of(user)))
          .entity(view)
          .build();

      } else {
        log.debug("Failed verification");
      }
    } catch (OpenIDException e) {
      // present error to the user
      log.error("OpenIDException", e);
    }

    // Must have failed to be here
    throw new WebApplicationException(Response.Status.UNAUTHORIZED);
  }

  private String extractEmailAddress(AuthSuccess authSuccess) throws MessageException {
    FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);
    return getAttributeValue(
      fetchResp,
      "email",
      "",
      String.class);
  }

  private String extractFirstName(AuthSuccess authSuccess) throws MessageException {
    FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);
    return getAttributeValue(
      fetchResp,
      "firstname",
      "",
      String.class);
  }

  private String extractLastName(AuthSuccess authSuccess) throws MessageException {
    FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);
    return getAttributeValue(
      fetchResp,
      "lastname",
      "",
      String.class);
  }

  @SuppressWarnings("unchecked")
  private <T> T getAttributeValue(FetchResponse fetchResponse, String attribute, T defaultValue, Class<T> clazz) {
    List list = fetchResponse.getAttributeValues(attribute);
    if (list != null && !list.isEmpty()) {
      return (T) list.get(0);
    }

    return defaultValue;

  }

}
