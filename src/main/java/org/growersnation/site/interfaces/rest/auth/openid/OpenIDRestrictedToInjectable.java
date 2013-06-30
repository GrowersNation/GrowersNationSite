package org.growersnation.site.interfaces.rest.auth.openid;


import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.yammer.dropwizard.auth.AuthenticationException;
import org.growersnation.site.SiteConfiguration;
import org.growersnation.site.domain.security.Authority;
import org.growersnation.site.interfaces.rest.api.security.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * <p>Injectable to provide the following to {@link OpenIDRestrictedToProvider}:</p>
 * <ul>
 * <li>Performs decode from HTTP request</li>
 * <li>Carries OpenID authentication data</li>
 * </ul>
 *
 * @since 0.0.1
 */
class OpenIDRestrictedToInjectable extends AbstractHttpContextInjectable<UserDto> {

  private static final Logger log = LoggerFactory.getLogger(OpenIDRestrictedToInjectable.class);

  private final OpenIDAuthenticator authenticator;
  private final Set<Authority> requiredAuthorities;

  /**
   * @param authenticator The Authenticator that will compare credentials
   * @param requiredAuthorities The required authorities as provided by the RestrictedTo annotation
   */
  OpenIDRestrictedToInjectable(
    OpenIDAuthenticator authenticator,
    Authority[] requiredAuthorities) {
    this.authenticator = authenticator;
    this.requiredAuthorities = Sets.newHashSet(Arrays.asList(requiredAuthorities));
  }

  public OpenIDAuthenticator getAuthenticator() {
    return authenticator;
  }

  public Set<Authority> getRequiredAuthorities() {
    return requiredAuthorities;
  }

  @Override
  public UserDto getValue(HttpContext httpContext) {

    try {

      // Get the Authorization header
      final Map<String,Cookie> cookieMap = httpContext.getRequest().getCookies();
      if (!cookieMap.containsKey(SiteConfiguration.SESSION_TOKEN_NAME)) {
        throw new WebApplicationException(Response.Status.UNAUTHORIZED);
      }

      UUID sessionToken = UUID.fromString(cookieMap.get(SiteConfiguration.SESSION_TOKEN_NAME).getValue());

      if (sessionToken != null) {

        final OpenIDCredentials credentials = new OpenIDCredentials(sessionToken, requiredAuthorities);

        final Optional<UserDto> result = authenticator.authenticate(credentials);
        if (result.isPresent()) {
          return result.get();
        }
      }
    } catch (IllegalArgumentException e) {
      log.warn("Error decoding credentials",e);
    } catch (AuthenticationException e) {
      log.warn("Error authenticating credentials",e);
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }

    // Must have failed to be here
    throw new WebApplicationException(Response.Status.UNAUTHORIZED);
  }

}