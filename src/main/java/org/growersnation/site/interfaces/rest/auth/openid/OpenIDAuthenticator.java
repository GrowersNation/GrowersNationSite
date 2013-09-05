package org.growersnation.site.interfaces.rest.auth.openid;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import org.growersnation.site.domain.repositories.UserRepository;
import org.growersnation.site.infrastructure.dto.security.User;

/**
 * <p>Authenticator to provide the following to application:</p>
 * <ul>
 * <li>Verifies the provided credentials are valid</li>
 * </ul>
 *
 * @since 0.0.1
 */
public class OpenIDAuthenticator implements Authenticator<OpenIDCredentials, User> {

  private final UserRepository userRepository;

  @Inject
  public OpenIDAuthenticator(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> authenticate(OpenIDCredentials credentials) throws AuthenticationException {

    // Get the User referred to by the API key
    Optional<User> user = userRepository.getBySessionToken(credentials.getSessionToken());
    if (!user.isPresent()) {
      return Optional.absent();
    }

    // Check that their authorities match their credentials
    if (!user.get().hasAllAuthorities(credentials.getRequiredAuthorities())) {
      return Optional.absent();
    }

    // Must be OK to here
    return user;

  }

}