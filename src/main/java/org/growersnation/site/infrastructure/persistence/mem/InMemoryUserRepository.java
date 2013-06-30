package org.growersnation.site.infrastructure.persistence.mem;

import com.google.common.base.Optional;
import org.growersnation.site.domain.repositories.UserRepository;
import org.growersnation.site.domain.security.User;

import java.util.UUID;

public class InMemoryUserRepository extends InMemoryEntityRepository<User> implements UserRepository {

  @Override
  public Optional<User> getBySessionToken(UUID sessionToken) {
    return null;
  }
}
