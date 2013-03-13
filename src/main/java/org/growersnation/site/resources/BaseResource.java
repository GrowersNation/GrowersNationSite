package org.growersnation.site.resources;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import org.growersnation.site.SiteConfiguration;
import org.growersnation.site.dao.security.UserDao;
import org.growersnation.site.model.security.User;
import org.growersnation.site.model.view.BaseModel;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.CookieParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.UriInfo;
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

  /**
   * User DAO
   */
  protected final UserDao userDao;

  /**
   * Jersey creates a fresh resource every request so this is safe
   */
  @Context
  protected UriInfo uriInfo;

  /**
   * Jersey creates a fresh resource every request so this is safe
   */
  @Context
  protected HttpHeaders httpHeaders;

  /**
   * Jersey creates a fresh resource every request so this is safe
   */
  @Context
  protected HttpServletRequest request;

  /**
   * The current session token (we do not use jsessionid for scalability reasons)
   */
  @CookieParam(value = SiteConfiguration.SESSION_TOKEN_NAME)
  protected String rawSessionToken;

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
    Optional<User> user = retrieveUserBySessionTokenCookie();
    if (user.isPresent()) {
      model.setUser(user.get());
    }
    return model;
  }

  /**
   * @param user The authenticated user
   *
   * @return The associated session token for subsequent cookie authentication
   */
  protected NewCookie replaceSessionTokenCookie(Optional<User> user) {
    return new NewCookie(
      SiteConfiguration.SESSION_TOKEN_NAME,
      user.get().getSessionToken().toString(),   // Value
      "/",   // Path
      null,   // Domain
      null,   // Comment
      NewCookie.DEFAULT_MAX_AGE, // Max age - expire on close
      false);
  }

  /**
   * @return The user associated with the session token
   */
  protected Optional<User> retrieveUserBySessionTokenCookie() {

    // Fail fast
    if (!isSessionTokenPresent()) {
      return Optional.absent();
    }

    return userDao.getBySessionToken(UUID.fromString(rawSessionToken));

  }

  /**
   * @return True if a session token was offered in the request
   */
  protected boolean isSessionTokenPresent() {

    return rawSessionToken != null;

  }


}
