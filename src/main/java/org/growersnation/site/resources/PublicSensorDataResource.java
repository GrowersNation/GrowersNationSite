package org.growersnation.site.resources;

import com.yammer.metrics.annotation.Timed;
import org.growersnation.site.model.sensor.SensorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>CRUD operations on soil data via API</li>
 * <li>CRUD operations on soil data via web forms</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Path("/sensordata")
@Produces(MediaType.TEXT_HTML)
public class PublicSensorDataResource extends BaseResource {

  private static final Logger log = LoggerFactory.getLogger(PublicSensorDataResource.class);

  /**
   * Provide soil data based on a Lat/Lng combination
   * @param rawLocation The location expressed as lat,long
   * @param rawRadius The radius in 0.02
   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSensorData(
    @QueryParam("location") String rawLocation,
    @QueryParam("radius") String rawRadius
    ) {

    return Response.ok().build();

  }

  /**
   * Provide soil data based on a Lat/Lng combination
   *
   * @return A localised view containing HTML
   */
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public Response postSensorData(SensorData sensorData) {

    URI location = UriBuilder
      .fromResource(PublicSensorDataResource.class)
      .queryParam("id", sensorData.getId())
      .build()
      ;

    return Response.created(location).entity("Success! Soil pH was: "+sensorData.getpH()).build();

  }

}
