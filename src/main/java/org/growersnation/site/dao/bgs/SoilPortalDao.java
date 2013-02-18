package org.growersnation.site.dao.bgs;

import com.sun.jersey.api.client.Client;
import org.growersnation.site.model.bgs.FeatureInfoResponse;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * <p>DAO to provide the following to application:</p>
 * <ul>
 * <li>Access to BGS soil portal</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class SoilPortalDao {

  private final Client client;

  public SoilPortalDao(Client client) {
    this.client = client;
  }

  public void getSoilData(double lat, double lon) {

    // TODO Calculate the BBOX based on lat/lon

    String bbox = "0.08,51.48,0.12,51.52";

    URI uri = UriBuilder
      .fromUri("http://maps.bgs.ac.uk/ArcGIS/services/SoilPortal/SoilPortal/MapServer/WMSServer")
      .queryParam("REQUEST=Getfeatureinfo&VERSION=1.1.1&LAYERS={layers}&STYLES=default&FORMAT=text/xml&SRS=CRS:84&BBOX={bbox}&WIDTH=500&HEIGHT=500&X=250&Y=250&QUERY_LAYERS={queryLayers}", "6", bbox, 6)
      .build();

    FeatureInfoResponse featureInfo = client.resource(uri).get(FeatureInfoResponse.class);


  }

}
