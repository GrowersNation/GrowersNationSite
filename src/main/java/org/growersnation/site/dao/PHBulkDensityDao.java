package org.growersnation.site.dao;

import org.growersnation.site.model.ph.PHBulkDensityFields;
import org.growersnation.site.model.ph.FeatureInfoResponse;
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
public class PHBulkDensityDao extends BaseHttpDao {

  private static final Logger log = LoggerFactory.getLogger(PHBulkDensityDao.class);

  public List<PHBulkDensityFields> getPHBulkDensityData(double lat, double lon) {

    // Calculate the BBOX based on lat/lon
    double minLong = lon - 0.02;
    double maxLong = lon + 0.02;
    double minLat = lat - 0.02;
    double maxLat = lat + 0.02;
    String bbox = String.format("%f,%f,%f,%f",minLong, minLat, maxLong, maxLat);

    StringBuilder sb = new StringBuilder();
    sb.append("http://lasigpublic.nerc-lancaster.ac.uk/ArcGIS/services/Biodiversity/CS_topsoil_PhBulkDens/MapServer/WMSServer")
      .append("?REQUEST=Getfeatureinfo")
      .append("&VERSION=1.1.1")
      .append("&LAYERS=")
      .append("0")
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
      .append("0");

    return queryHttpSource(sb.toString(), FeatureInfoResponse.class, PHBulkDensityFields.class);

  }


}
