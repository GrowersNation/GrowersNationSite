package org.growersnation.site.dao;

import org.growersnation.site.model.texture.FeatureInfoResponse;
import org.growersnation.site.model.texture.SoilTextureFields;
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
public class SoilTextureDao extends BaseHttpDao {

  private static final Logger log = LoggerFactory.getLogger(SoilTextureDao.class);

  public List<SoilTextureFields> getSoilTextureData(double lat, double lon) {

    // Calculate the BBOX based on lat/lon
    double minLong = lon - 0.02;
    double maxLong = lon + 0.02;
    double minLat = lat - 0.02;
    double maxLat = lat + 0.02;
    String bbox = String.format("%f,%f,%f,%f",minLong, minLat, maxLong, maxLat);

    StringBuilder sb = new StringBuilder();
    sb.append("http://maps.bgs.ac.uk/ArcGIS/services/SoilPortal/SoilPortal/MapServer/WMSServer")
      .append("?REQUEST=Getfeatureinfo")
      .append("&VERSION=1.1.1")
      .append("&LAYERS=")
      .append("6")
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
      .append("6");

    return queryHttpSource(sb.toString(), FeatureInfoResponse.class, SoilTextureFields.class);

  }


}
