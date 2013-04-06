package org.growersnation.site.repository.mem;

import com.google.common.base.Optional;
import org.growersnation.site.model.security.User;
import org.growersnation.site.repository.UserRepository;

import java.util.UUID;

public class InMemoryUserRepository extends InMemoryEntityRepository<User> implements UserRepository {

  @Override
  public Optional<User> getBySessionToken(UUID sessionToken) {
    return null;
  }
}
