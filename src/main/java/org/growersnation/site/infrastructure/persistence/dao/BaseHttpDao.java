package org.growersnation.site.infrastructure.persistence.dao;

import com.google.common.base.Charsets;
import org.growersnation.site.infrastructure.thirdparty.bgs.soil.FieldAccessor;
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
 * <p>Abstract base class to provide the following to HTTP DAOs:</p>
 * <ul>
 * <li>Provision of common methods</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public abstract class BaseHttpDao {

  private static final Logger log = LoggerFactory.getLogger(BaseHttpDao.class);

  private static final double MODEL_RESOLUTION = 0.02;

  /**
   * Default request header fields
   */
  protected Map<String, String> defaultHttpHeaders = new HashMap<String, String>();

  protected BaseHttpDao() {
    // Always use UTF8
    defaultHttpHeaders.put("Accept-Charset", Charsets.UTF_8.name());
    // Accept text/xml by default
    defaultHttpHeaders.put("Accept", "text/xml");
  }

  /**
   * Handles the process of querying a WMS server offering FeatureInfo/FIELD combinations
   *
   * @param url The URL
   *
   * @return The list of fields
   */
  protected <F> List<F> queryFeatureInfoFieldHttpSource(String url, Class<? extends FieldAccessor<F>> featureInfoResponse, Class<F> fieldType) {
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
      FieldAccessor<F> fir = JAXB.unmarshal(inputStream, featureInfoResponse);

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
   * Handles the process of querying an eXist DB server offering arbitrary XML responses
   *
   * @param url The URL
   *
   * @return The root element
   */
  protected <T> T queryExistDB(String url, Class<T> returnType) {
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
      return JAXB.unmarshal(inputStream, returnType);

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
   * Tests should mock this method to keep the build local
   *
   * @param urlString The URL string
   *
   * @return a HttpURLConnection instance
   *
   * @throws java.io.IOException
   */
  /* package */ HttpURLConnection getHttpURLConnection(String urlString) throws IOException {

    return (HttpURLConnection) new URL(urlString).openConnection();
  }

  /**
   *
   * @param lat A precise latitude
   * @param lng A precise longitude
   * @return A bounding box (BBox) representing an imprecise region
   */
  protected String getBBox(double lat, double lng) {

    // Calculate the BBOX based on lat/lon
    double minLong = lng - MODEL_RESOLUTION;
    double maxLong = lng + MODEL_RESOLUTION;
    double minLat = lat - MODEL_RESOLUTION;
    double maxLat = lat + MODEL_RESOLUTION;

    return String.format("%f,%f,%f,%f",minLong, minLat, maxLong, maxLat);
  }
}
