package org.growersnation.site.dao.soil.http;

import org.growersnation.site.dao.BaseHttpDao;
import org.growersnation.site.model.soil.nutrients.FeatureInfoResponse;
import org.growersnation.site.model.soil.nutrients.TopsoilNutrientsFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p>HTTP DAO to provide the following to application:</p>
 * <ul>
 * <li>Access to BGS soil portal via HTTP</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class TopsoilNutrientsDao extends BaseHttpDao {

  private static final Logger log = LoggerFactory.getLogger(TopsoilNutrientsDao.class);

  /**
   * @param lat The precise latitude
   * @param lng The precise longitude
   *
   * @return The available topsoil nutrient data near this area (subject to bounding box)
   */
  public List<TopsoilNutrientsFields> getTopsoilNutrientsData(double lat, double lng) {

    String bbox = getBBox(lat, lng);

    StringBuilder sb = new StringBuilder();
    sb.append("http://lasigpublic.nerc-lancaster.ac.uk/ArcGIS/services//Biodiversity/CS_topsoil_nutrients/MapServer/WMSServer")
      .append("?REQUEST=Getfeatureinfo")
      .append("&VERSION=1.1.1")
      .append("&LAYERS=")
      .append("1")
      .append("&STYLES=default")
      .append("&FORMAT=text/xml")
      .append("&SRS=CRS:84")
      .append("&BBOX=")
      .append(bbox)
      .append("&WIDTH=500")
      .append("&HEIGHT=500")
      .append("&X=250")
      .append("&Y=250")
      .append("&QUERY_LAYERS=")
      .append("1");

    return queryFeatureInfoFieldHttpSource(sb.toString(), FeatureInfoResponse.class, TopsoilNutrientsFields.class);

  }


}
