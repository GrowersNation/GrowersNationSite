package org.growersnation.site.interfaces.rest.resources;

import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.growersnation.site.interfaces.rest.views.BaseModel;
import org.growersnation.site.interfaces.rest.views.PublicFreemarkerView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of error pages</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Path("/error")
@Produces(MediaType.TEXT_HTML)
public class PublicErrorResource extends BaseResource {

  /**
   * Provide the 401 Unauthorized page
   *
   * @return A localised view containing HTML
   */
  @GET
  @Path("/401")
  @Timed
  @CacheControl(noCache = true)
  public PublicFreemarkerView view401() {

    return new PublicFreemarkerView<BaseModel>("error/401.ftl",modelBuilder.newBaseModel(httpHeaders));
  }

  /**
   * Provide the 404 Not Found page
   *
   * @return A localised view containing HTML
   */
  @GET
  @Path("/404")
  @Timed
  @CacheControl(noCache = true)
  public PublicFreemarkerView view404() {

    return new PublicFreemarkerView<BaseModel>("error/404.ftl",modelBuilder.newBaseModel(httpHeaders));
  }

  /**
   * Provide the 500 Internal Server Error page
   *
   * @return A localised view containing HTML
   */
  @GET
  @Path("/500")
  @Timed
  @CacheControl(noCache = true)
  public PublicFreemarkerView view500() {

    return new PublicFreemarkerView<BaseModel>("error/500.ftl",modelBuilder.newBaseModel(httpHeaders));
  }
}
