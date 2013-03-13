package org.growersnation.site.resources;

import com.google.common.base.Splitter;
import com.google.inject.Inject;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.growersnation.site.dao.security.UserDao;
import org.growersnation.site.dao.soil.http.PHBulkDensityDao;
import org.growersnation.site.dao.soil.http.SoilTextureDao;
import org.growersnation.site.dao.soil.http.TopsoilCarbonDao;
import org.growersnation.site.dao.soil.http.TopsoilNutrientsDao;
import org.growersnation.site.dao.weather.http.TemperatureLocationDao;
import org.growersnation.site.model.weather.WeatherData;
import org.growersnation.site.model.weather.temperature.TemperatureLocation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for public home page</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Path("/weatherdata")
@Produces(MediaType.TEXT_HTML)
public class PublicWeatherDataResource extends BaseResource {

  PHBulkDensityDao phBulkDensityDao = new PHBulkDensityDao();
  SoilTextureDao soilTextureDao = new SoilTextureDao();
  TopsoilCarbonDao topsoilCarbonDao = new TopsoilCarbonDao();
  TopsoilNutrientsDao topsoilNutrientsDao = new TopsoilNutrientsDao();
  TemperatureLocationDao temperatureLocationDao = new TemperatureLocationDao();

  /**
   * @param userDao The security DAO
   */
  @Inject
  public PublicWeatherDataResource(UserDao userDao) {
    super(userDao);
  }

  /**
   * Provide weather data based on a Lat/Lng combination
   * @param rawLocation The location expressed as lat,long
   * @param rawRadius The radius in 0.02
   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
  @Produces(MediaType.APPLICATION_JSON)
  public WeatherData getWeatherData(
    @QueryParam("location") String rawLocation,
    @QueryParam("radius") String rawRadius
    ) {

    Iterator<String> iterator = Splitter.on(",").split(rawLocation).iterator();

    final double lat;
    final double lng;

    try {

      lat = Double.valueOf(iterator.next());
      lng = Double.valueOf(iterator.next());

    } catch (NumberFormatException e) {

      throw new WebApplicationException(Response.Status.BAD_REQUEST);

    }

    // Assume that the DAOs are perfectly accurate for their APIs
    List<TemperatureLocation> temperatureLocations = temperatureLocationDao.getTemperatureLocationData(lat, lng);

    // Build some weather data
    WeatherData weatherData = new WeatherData();
    weatherData.setTemperatureLocations(temperatureLocations);

    return weatherData;

  }

  /* package */ void setPhBulkDensityDao(PHBulkDensityDao phBulkDensityDao) {
    this.phBulkDensityDao = phBulkDensityDao;
  }

  /* package */ void setSoilTextureDao(SoilTextureDao soilTextureDao) {
    this.soilTextureDao = soilTextureDao;
  }

  /* package */ void setTopsoilCarbonDao(TopsoilCarbonDao topsoilCarbonDao) {
    this.topsoilCarbonDao = topsoilCarbonDao;
  }

  /* package */ void setTopsoilNutrientsDao(TopsoilNutrientsDao topsoilNutrientsDao) {
    this.topsoilNutrientsDao = topsoilNutrientsDao;
  }
}
