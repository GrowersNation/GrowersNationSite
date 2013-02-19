package org.growersnation.site.resources;

import com.google.common.base.Splitter;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.growersnation.site.dao.PHBulkDensityDao;
import org.growersnation.site.dao.SoilTextureDao;
import org.growersnation.site.dao.TopsoilCarbonDao;
import org.growersnation.site.dao.TopsoilNutrientsDao;
import org.growersnation.site.model.SoilData;
import org.growersnation.site.model.carbon.CarbonFields;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.List;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for public home page</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Path("/soildata")
@Produces(MediaType.TEXT_HTML)
public class PublicSoilDataResource extends BaseResource {

  PHBulkDensityDao phBulkDensityDao = new PHBulkDensityDao();
  SoilTextureDao soilTextureDao = new SoilTextureDao();
  TopsoilCarbonDao topsoilCarbonDao = new TopsoilCarbonDao();
  TopsoilNutrientsDao topsoilNutrientsDao = new TopsoilNutrientsDao();

  /**
   * Provide soil data based on a Lat/Lng combination

   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @CacheControl(noCache = true)
  @Produces(MediaType.APPLICATION_JSON)
  public SoilData getSoilData(
    @QueryParam("location") String rawLocation,
    @QueryParam("radius") String rawRadius
    ) {

    Iterator<String> iterator = Splitter.on(",").split(rawLocation).iterator();

    final double lat;
    final double lon;
    try {

      lat = Double.valueOf(iterator.next());
      lon = Double.valueOf(iterator.next());

    } catch (NumberFormatException e) {

      throw new WebApplicationException(Response.Status.BAD_REQUEST);

    }

    List<CarbonFields> carbonFieldsList = topsoilCarbonDao.getTopsoilCarbonData(Double.valueOf(lat), Double.valueOf(lon));

    SoilData soilData = new SoilData();
    soilData.setCarbonFields(carbonFieldsList);

    return soilData;

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
