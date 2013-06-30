package org.growersnation.site.application;

import com.google.common.base.Optional;
import org.growersnation.site.interfaces.rest.api.security.UserDto;

import java.util.List;
import java.util.UUID;

/**
 * <p>ReadService to provide the following to Resources:</p>
 * <ul>
 * <li>Read access to {@link org.growersnation.site.interfaces.rest.api.security.UserDto}s</li>
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
  List<UserDto> fetchUsers(int page, int pageSize);

  /**
   * @return The total number of users
   */
  long getUserCount();

  /**
   * @param sessionToken The session token
   *
   * @return A User with the session token or absent
   */
  Optional<UserDto> getBySessionToken(UUID sessionToken);

  /**
   * @param openIDIdentifier The OpenID identifier
   *
   * @return A User with the identifier or absent
   */
  Optional<UserDto> getByOpenIDIdentifier(String openIDIdentifier);

  /**
   * @param emailAddress The email address
   *
   * @return A User with the email address or absent
   */
  Optional<UserDto> getByEmailAddress(String emailAddress);
}
