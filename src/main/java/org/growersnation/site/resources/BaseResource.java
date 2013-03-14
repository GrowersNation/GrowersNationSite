package org.growersnation.site.resources;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import org.growersnation.site.SiteConfiguration;
import org.growersnation.site.dao.security.UserDao;
import org.growersnation.site.model.security.User;
import org.growersnation.site.model.view.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.*;
import java.util.Locale;
import java.util.UUID;

/**
 * <p>Abstract base class to provide the following to subclasses:</p>
 * <ul>
 * <li>Provision of common methods</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class BaseResource {

  private static final Logger log = LoggerFactory.getLogger(BaseResource.class);

  /**
   * User DAO
   */
  protected final UserDao userDao;

  /**
   * Jersey guarantees request scope
   */
  @Context
  protected UriInfo uriInfo;

  /**
   * Jersey guarantees request scope
   */
  @Context
  protected HttpHeaders httpHeaders;

  /**
   * Jersey guarantees request scope
   */
  @Context
  protected HttpServletRequest request;

  /**
   * @param userDao The user DAO
   */
  @Inject
  public BaseResource(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * @return The most appropriate locale for the upstream request (never null)
   */
  public Locale getLocale() {
    // TODO This should be a configuration setting
    Locale defaultLocale = Locale.UK;

    Locale locale;
    if (httpHeaders == null) {
      locale = defaultLocale;
    } else {
      locale = httpHeaders.getLanguage();
      if (locale == null) {
        locale = defaultLocale;
      }
    }
    return locale;
  }

  /**
   * <p>Utility method to create a base model</p>
   *
   * <p>If a User is present then it will be added to the model, otherwise it will
   * be left as null</p>
   *
   * @return A base model
   */
  protected BaseModel newBaseModel() {

    // Populate the model
    BaseModel model = new BaseModel();

    Optional<UUID> sessionTokenOptional = retrieveSessionToken();

    if (sessionTokenOptional.isPresent()) {
      Optional<User> user = userDao.getBySessionToken(sessionTokenOptional.get());
      if (user.isPresent()) {
        model.setUser(user.get());
      }
    }

    // Add User based on their session token
    return model;
  }

  /**
   * @param user A user with a session token. If absent then the cookie will be removed.
   *
   * @return A cookie with a long term expiry date suitable for use as a session token for OpenID
   */
  protected NewCookie replaceSessionTokenCookie(Optional<User> user) {

    if (user.isPresent()) {

      String value = user.get().getSessionToken().toString();

      log.debug("Replacing session token with {}", value);

      return new NewCookie(
        SiteConfiguration.SESSION_TOKEN_NAME,
        value,   // Value
        "/",     // Path
        null,    // Domain
        null,    // Comment
        86400 * 30, // 30 days
        false);
    } else {
      // Remove the session token cookie
      log.debug("Removing session token");

      return new NewCookie(
        SiteConfiguration.SESSION_TOKEN_NAME,
        null,   // Value
        null,    // Path
        null,   // Domain
        null,   // Comment
        0,      // Expire immediately
        false);
    }
  }

  /**
   * @return The session token from the cookie if present
   */
  private Optional<UUID> retrieveSessionToken() {

    if (httpHeaders != null && httpHeaders.getCookies() != null) {
      Cookie sessionTokenCookie = httpHeaders.getCookies().get(SiteConfiguration.SESSION_TOKEN_NAME);
      if (sessionTokenCookie == null) {
        return Optional.absent();
      }
      String value = sessionTokenCookie.getValue();
      if (value != null) {
        // TODO Convert to trace
        log.debug("Session token: {}", value);
        return Optional.of(UUID.fromString(value));
      }
    }

    // No session token
    return Optional.absent();

  }


}
