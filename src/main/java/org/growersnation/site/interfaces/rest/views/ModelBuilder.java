package org.growersnation.site.interfaces.rest.views;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import org.growersnation.site.SiteConfiguration;
import org.growersnation.site.infrastructure.persistence.dao.security.UserDao;
import org.growersnation.site.interfaces.rest.api.security.UserDto;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import java.util.UUID;

/**
 * <p>Builder to provide the following to resources:</p>
 * <ul>
 * <li>Utility methods to build backing models for Views</li>
 * </ul>
 *
 * @since 0.0.1
 */
public class ModelBuilder {

  private final UserDao userDao;

  @Inject
  public ModelBuilder(UserDao userDao) {
    Preconditions.checkNotNull(userDao);
    this.userDao = userDao;
  }

  private Optional<UUID> extractSessionToken(HttpHeaders httpHeaders) {

    // Want to fail fast to absent() since this will get called a lot
    Optional<UUID> sessionToken = Optional.absent();

    if (httpHeaders == null) {
      // Might be an internal call such as an error condition
      return sessionToken;
    }
    if (httpHeaders.getCookies() == null) {
      // This is a cold user
      return sessionToken;
    }

    Cookie cookie = httpHeaders.getCookies().get(SiteConfiguration.SESSION_TOKEN_NAME);
    if (cookie == null) {
      // This is a cold user
      return sessionToken;
    }
    String value = cookie.getValue();
    if (value == null) {
      // This is a broken cookie
      // Rather than throw an error we can force a fresh login to fix it up
      return sessionToken;
    }

    // Must be OK to be here
    return Optional.of(UUID.fromString(value));

  }

  /**
   * @return A new base model with user populated from the session token if present
   */
  public BaseModel newBaseModel(HttpHeaders httpHeaders) {

    BaseModel baseModel = new BaseModel();

    // Locate and populate the user by their session token (if present)
    Optional<UUID> sessionToken = extractSessionToken(httpHeaders);
    if (sessionToken.isPresent()) {
      Optional<UserDto> user = userDao.getBySessionToken(sessionToken.get());
      if (user.isPresent()) {
        baseModel.setUser(user.get());
      }
    }

    return baseModel;

  }

}
