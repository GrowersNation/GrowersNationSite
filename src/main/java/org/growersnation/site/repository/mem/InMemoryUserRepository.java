package org.growersnation.site.repository.mem;

import com.google.common.base.Optional;
import uk.co.froot.coinapull.model.security.User;
import uk.co.froot.coinapull.repository.UserRepository;

import java.util.UUID;

public class InMemoryUserRepository extends InMemoryEntityRepository<User> implements UserRepository {

  @Override
  public Optional<User> getBySessionToken(UUID sessionToken) {
    return null;
  }
}
