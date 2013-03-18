package org.growersnation.site.auth.openid;


import com.google.inject.Inject;
import com.sun.jersey.api.model.Parameter;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import org.growersnation.site.auth.annotation.RestrictedTo;

/**
 * <p>Authentication provider to provide the following to Jersey:</p>
 * <ul>
 * <li>Bridge between Dropwizard and Jersey for OpenID authentication</li>
 * </ul>
 *
 * @since 0.0.1
 */
public class OpenIDRestrictedToProvider implements InjectableProvider<RestrictedTo, Parameter> {

  private final OpenIDAuthenticator authenticator;

  /**
   * Creates a new {@link OpenIDRestrictedToProvider} with the given {@link com.yammer.dropwizard.auth.Authenticator} and realm.
   *
   * @param authenticator the authenticator which will take the {@link OpenIDCredentials} and
   *                      convert them into instances of {@code T}
   */
  @Inject
  public OpenIDRestrictedToProvider(OpenIDAuthenticator authenticator) {
    this.authenticator = authenticator;
  }

  @Override
  public ComponentScope getScope() {
    return ComponentScope.PerRequest;
  }

  @Override
  public Injectable<?> getInjectable(ComponentContext ic,
                                     RestrictedTo a,
                                     Parameter c) {
    return new OpenIDRestrictedToInjectable(authenticator, a.value());
  }
}

