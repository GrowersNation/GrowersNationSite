package org.growersnation.site.interfaces.rest.resources;

import com.google.common.base.Splitter;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.growersnation.site.infrastructure.dto.thirdparty.metoffice.gdd.GDDDocument;
import org.growersnation.site.infrastructure.persistence.dao.soil.http.PHBulkDensityDao;
import org.growersnation.site.infrastructure.persistence.dao.soil.http.SoilTextureDao;
import org.growersnation.site.infrastructure.persistence.dao.soil.http.TopsoilCarbonDao;
import org.growersnation.site.infrastructure.persistence.dao.soil.http.TopsoilNutrientsDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of growing degree day data over 30 years</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Path("/gdd")
@Produces(MediaType.TEXT_HTML)
public class PublicGDDResource extends BaseResource {

  PHBulkDensityDao phBulkDensityDao = new PHBulkDensityDao();
  SoilTextureDao soilTextureDao = new SoilTextureDao();
  TopsoilCarbonDao topsoilCarbonDao = new TopsoilCarbonDao();
  TopsoilNutrientsDao topsoilNutrientsDao = new TopsoilNutrientsDao();

  /**
   * Provide weather data based on a Lat/Lng combination
   * @param rawLocation The location expressed as lat,long
   * @param rawRadius The radius in 0.02
   *
   * @return A GDDDocument providing the required data points
   */
  @GET
  @Timed
  @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
  @Produces(MediaType.APPLICATION_JSON)
  public GDDDocument getWeatherData(
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
    //List<GrowingDegreeDays30Year> temperatureLocations = growingDegreeDaysDao.getByLocation(lat, lng);

    // Build some weather data
    //GrowingDegreeDays30Year weatherData = new GrowingDegreeDays30Year();
    //weatherData.setTemperatureLocations(temperatureLocations);

    //return weatherData;

    return null;

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
