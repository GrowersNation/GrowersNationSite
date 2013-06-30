package org.growersnation.site.domain.repositories;

import com.google.common.base.Optional;
import org.growersnation.site.domain.security.User;

import java.util.UUID;

/**
 * <p>Repository to provide storage and retrieval of SoilData aggregates to the application</p>
 */
public interface UserRepository extends EntityRepository<User, String> {

  /**
   * @param sessionToken The session token
   *
   * @return The User with the session token (or absent)
   */
  Optional<User> getBySessionToken(UUID sessionToken);


}
