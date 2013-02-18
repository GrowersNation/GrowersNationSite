package org.growersnation.site.dao.bgs;

import com.google.common.base.Charsets;
import org.growersnation.site.model.bgs.FeatureInfoResponse;
import org.growersnation.site.model.bgs.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  private static final Logger log = LoggerFactory.getLogger(SoilPortalDao.class);

  /**
   * Default request header fields
   */
  private Map<String, String> defaultHttpHeaders = new HashMap<String, String>();

  public SoilPortalDao() {
    // Always use UTF8
    defaultHttpHeaders.put("Accept-Charset", Charsets.UTF_8.name());
    // Accept text/xml by default
    defaultHttpHeaders.put("Accept", "text/xml");
  }

  public List<Fields> getSoilData(double lat, double lon) {

    // TODO Calculate the BBOX based on lat/lon

    String bbox = "0.08,51.48,0.12,51.52";

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

    return queryBGSPortal(sb.toString());

  }

  /**
   * Handles the process of querying the BGS soil portal
   *
   * @param url The URL
   *
   * @return The list of fields
   */
  private List<Fields> queryBGSPortal(String url) {
    HttpURLConnection connection = null;
    try {
      connection = getHttpURLConnection(url);
      connection.setRequestMethod("GET");

      // Copy default HTTP headers
      Map<String, String> headerKeyValues = new HashMap<String, String>(defaultHttpHeaders);

      // Add HTTP headers to the request
      for (Map.Entry<String, String> entry : headerKeyValues.entrySet()) {
        connection.setRequestProperty(entry.getKey(), entry.getValue());
        log.debug("Header request property: key='{}', value='{}'", entry.getKey(), entry.getValue());
      }

      // Get the input stream
      InputStream inputStream = connection.getInputStream();

      int httpStatus = connection.getResponseCode();
      log.debug("Request http status = {}", httpStatus);

      // Get the data
      FeatureInfoResponse fir = JAXB.unmarshal(inputStream, FeatureInfoResponse.class);

      return fir.getFields();
    } catch (MalformedURLException e) {
      throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
    } catch (IOException e) {
      throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }

  /**
   * @param urlString The URL string
   *
   * @return a HttpURLConnection instance
   *
   * @throws IOException
   */
  /* package */ HttpURLConnection getHttpURLConnection(String urlString) throws IOException {

    return (HttpURLConnection) new URL(urlString).openConnection();
  }


}
