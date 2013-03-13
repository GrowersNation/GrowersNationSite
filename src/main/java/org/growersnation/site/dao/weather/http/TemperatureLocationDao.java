package org.growersnation.site.dao.weather.http;

import com.google.common.collect.Lists;
import org.growersnation.site.dao.BaseHttpDao;
import org.growersnation.site.model.weather.temperature.TemperatureLocation;
import org.growersnation.site.model.weather.temperature.TemperatureLocationList;

import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.util.List;

/**
 * <p>HTTP DAO to provide the following to application:</p>
 * <ul>
 * <li>Access to eXist temperature location data via HTTP</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class TemperatureLocationDao extends BaseHttpDao {

  // TODO Remove this once the database is hooked up
  private static final TemperatureLocationList TEMPERATURE_LOCATION_LIST;

  static {

    InputStream is = TemperatureLocationDao.class.getResourceAsStream("/data/weather/example-temperature-1.xml");

    TEMPERATURE_LOCATION_LIST= JAXB.unmarshal(is,TemperatureLocationList.class);

  }

  /**
   *
   * @param lat The latitude
   * @param lng The longitude
   * @return A list of matching temperatures locations within a bounding box
   */
  public List<TemperatureLocation> getTemperatureLocationData(double lat, double lng) {

    if (lat == 51.65 && lng == 0.1) {
      return TEMPERATURE_LOCATION_LIST.getLocations();
    }

    return Lists.newArrayList();
  }
}
