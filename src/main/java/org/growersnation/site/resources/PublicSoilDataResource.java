package org.growersnation.site.resources;

import com.google.common.base.Splitter;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.bson.types.ObjectId;
import org.growersnation.site.dao.soil.http.PHBulkDensityDao;
import org.growersnation.site.dao.soil.http.SoilTextureDao;
import org.growersnation.site.dao.soil.http.TopsoilCarbonDao;
import org.growersnation.site.dao.soil.http.TopsoilNutrientsDao;
import org.growersnation.site.model.soil.SoilData;
import org.growersnation.site.model.soil.carbon.TopsoilCarbonFields;
import org.growersnation.site.model.soil.nutrients.TopsoilNutrientsFields;
import org.growersnation.site.model.soil.ph.PHBulkDensityFields;
import org.growersnation.site.model.soil.texture.SoilTextureFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>CRUD operations on soil data via API</li>
 * <li>CRUD operations on soil data via web forms</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Path("/soildata")
@Produces(MediaType.TEXT_HTML)
public class PublicSoilDataResource extends BaseResource {

  private static final Logger log = LoggerFactory.getLogger(PublicSoilDataResource.class);

  private PHBulkDensityDao phBulkDensityDao = new PHBulkDensityDao();
  private SoilTextureDao soilTextureDao = new SoilTextureDao();
  private TopsoilCarbonDao topsoilCarbonDao = new TopsoilCarbonDao();
  private TopsoilNutrientsDao topsoilNutrientsDao = new TopsoilNutrientsDao();

  /**
   * Provide soil data based on a Lat/Lng combination
   * @param rawLocation The location expressed as lat,long
   * @param rawRadius The radius in 0.02
   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
  @Produces(MediaType.APPLICATION_JSON)
  public SoilData getSoilData(
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
    List<TopsoilCarbonFields> topsoilCarbonFieldsList = topsoilCarbonDao.getTopsoilCarbonData(lat, lng);
    List<PHBulkDensityFields> phBulkDensityFieldsList = phBulkDensityDao.getPHBulkDensityData(lat, lng);
    List<SoilTextureFields> soilTextureFieldsList = soilTextureDao.getSoilTextureData(lat, lng);
    List<TopsoilNutrientsFields> topsoilNutrientsFieldsList = topsoilNutrientsDao.getTopsoilNutrientsData(lat, lng);

    //
    SoilData soilData = new SoilData(ObjectId.get().toString());
    soilData.setTopsoilCarbonFields(topsoilCarbonFieldsList);
    soilData.setPhBulkDensityFields(phBulkDensityFieldsList);
    soilData.setSoilTextureFields(soilTextureFieldsList);
    soilData.setTopsoilNutrientsFields(topsoilNutrientsFieldsList);

    return soilData;

  }

  /**
   * Provide soil data based on a Lat/Lng combination
   *
   * @return A localised view containing HTML
   */
  @POST
  public Response postSoilData(SoilData soilData) {

    log.info("Soil data = ",soilData.toString());

    URI location = UriBuilder
      .fromResource(PublicSoilDataResource.class)
      .queryParam("id",12345)
      .build()
      ;

    return Response.created(location).build();

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
