package org.growersnation.site.dao.security;

import com.google.common.base.Optional;
import org.growersnation.site.model.PaginationData;
import org.growersnation.site.model.security.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {

  /**
   * Attempt to locate the User
   *
   * @param id The ID
   *
   * @return A matching User
   */
  Optional<User> getById(Long id);

  /**
   * Attempt to locate the User by a UUID
   *
   * @param apiKey The apiKey that identifies the user during HMAC authentication
   *
   * @return A matching User
   */
  Optional<User> getByApiKey(String apiKey);

  /**
   * Attempt to locate the User by their email address
   *
   * @param emailAddress The email address provided by the user
   *
   * @return A matching User
   */
  Optional<User> getByEmailAddress(String emailAddress);

  /**
   * Attempt to locate the User by a UUID
   *
   * @param openIDIdentifier The OpenID identifier provided during verification
   *
   * @return A matching User
   */
  Optional<User> getByOpenIDIdentifier(String openIDIdentifier);

  /**
   * Attempt to locate the User by a UUID
   *
   * @param sessionToken The apiKey that identifies the user during HMAC authentication
   *
   * @return A matching User
   */
  Optional<User> getBySessionToken(UUID sessionToken);

  /**
   * Attempt to locate the User by a UUID
   *
   * @param username       The username
   * @param passwordDigest The password digest from a one-pass digest algorithm
   *
   * @return A matching User with Roles and Authorities initialised
   */
  Optional<User> getByCredentials(String username, String passwordDigest);

  /**
   * Provide a paged list of all Users
   *
   * @param paginationData The pagination data describing the result set
   */
  List<User> getAllByPage(PaginationData paginationData);

  /**
   * Persist the given User
   *
   * @param User A User (either new or updated)
   *
   * @return The persisted User
   */
  User saveOrUpdate(User User);

  /**
   * <p>Force an immediate in-transaction flush</p>
   * <p>Normally, this is only used in test code but must be on the interface to ensure
   * that injection works as expected</p>
   */
  void flush();
}