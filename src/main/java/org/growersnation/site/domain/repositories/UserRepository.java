package org.growersnation.site.domain.repositories;

import com.google.common.base.Optional;
import org.growersnation.site.infrastructure.dto.security.User;
import org.growersnation.site.interfaces.rest.api.PaginationData;

import java.util.List;
import java.util.UUID;

/**
 * <p>Repository to provide storage and retrieval of SoilData aggregates to the application</p>
 */
public interface UserRepository extends EntityRepository<User, String> {

  /**
   * Attempt to locate the User
   *
   * @param id The ID
   *
   * @return A matching User
   */
  Optional<User> getById(String id);

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
   * @return The ID of the persisted User
   */
  String saveOrUpdate(User User);

  /**
   * <p>Force an immediate in-transaction flush</p>
   * <p>Normally, this is only used in test code but must be on the interface to ensure
   * that injection works as expected</p>
   */
  void flush();

  /**
   * Removes the User from the database (or marks it as such for audit purposes)
   *
   * @param user The User to delete
   */
  void delete(User user);

}
