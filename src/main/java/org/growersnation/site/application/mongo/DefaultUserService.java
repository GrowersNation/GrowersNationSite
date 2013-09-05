package org.growersnation.site.application.mongo;

import com.google.inject.Inject;
import org.growersnation.site.application.UserService;
import org.growersnation.site.domain.repositories.UserRepository;
import org.growersnation.site.infrastructure.dto.security.User;

import javax.validation.Valid;

/**
 * <p>Default implementation of {@link UserService}</p>
 *
 * @since 0.0.1
 */
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;

  @Inject
  public DefaultUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public String create(User user) {
    return userRepository.create(user);
  }

  @Override
  public String upsert(@Valid User user) {
    return userRepository.upsert(user);
  }

  @Override
  public void hardDelete(User user) {
    userRepository.hardDelete(user);
  }
}
