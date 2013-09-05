package org.growersnation.site.interfaces.rest.resources;

import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.growersnation.site.infrastructure.dto.security.Authority;
import org.growersnation.site.interfaces.rest.api.security.UserDto;
import org.growersnation.site.interfaces.rest.auth.annotation.RestrictedTo;
import org.growersnation.site.interfaces.rest.views.BaseModel;
import org.growersnation.site.interfaces.rest.views.PrivateFreemarkerView;
import org.growersnation.site.interfaces.rest.views.PublicFreemarkerView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for private use account page</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Path("/private")
@Produces(MediaType.TEXT_HTML)
public class PrivateInfoResource extends BaseResource {

  /**
   * @return The private home view if authenticated
   */
  @GET
  @Path("/home")
  @Timed
  @CacheControl(noCache = true)
  public PublicFreemarkerView viewHome(
    @RestrictedTo(Authority.ROLE_PUBLIC)
    UserDto publicUser
  ) {

    return new PrivateFreemarkerView<BaseModel>("private/home.ftl", modelBuilder.newBaseModel(httpHeaders));

  }

  /**
   * @return The private admin view if authenticated
   */
  @GET
  @Path("/admin")
  @Timed
  @CacheControl(noCache = true)
  public PublicFreemarkerView viewAdmin(
    @RestrictedTo(Authority.ROLE_ADMIN)
    UserDto adminUser
  ) {

    return new PrivateFreemarkerView<BaseModel>("private/admin.ftl", modelBuilder.newBaseModel(httpHeaders));

  }

}
