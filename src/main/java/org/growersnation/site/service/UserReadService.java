package org.growersnation.site.service;

import com.google.common.base.Optional;
import org.growersnation.site.model.security.User;

import java.util.List;
import java.util.UUID;

/**
 * <p>ReadService to provide the following to Resources:</p>
 * <ul>
 * <li>Read access to {@link User}s</li>
 * </ul>
 *
 * @since 0.0.1
 */
public interface UserReadService {

  /**
   * @param page     The starting page (0-based)
   * @param pageSize The number of results per page
   *
   * @return The list of newest soilData
   */
  List<User> fetchUsers(int page, int pageSize);

  /**
   * @return The total number of users
   */
  long getUserCount();

  /**
   * @param sessionToken The session token
   *
   * @return A User with the session token or absent
   */
  Optional<User> getBySessionToken(UUID sessionToken);

  /**
   * @param openIDIdentifier The OpenID identifier
   *
   * @return A User with the identifier or absent
   */
  Optional<User> getByOpenIDIdentifier(String openIDIdentifier);

  /**
   * @param emailAddress The email address
   *
   * @return A User with the email address or absent
   */
  Optional<User> getByEmailAddress(String emailAddress);
}
