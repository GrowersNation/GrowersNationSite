package org.growersnation.site.dao.weather.http;

import org.growersnation.site.model.weather.temperature.TemperatureLocation;
import org.growersnation.site.model.weather.temperature.TemperatureLocationList;
import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TemperatureLocationDaoTest {

  @Test
  public void testJAXB() {

    InputStream is = TemperatureLocationDaoTest.class.getResourceAsStream("/fixtures/weather/test-temperature-1.xml");
    TemperatureLocationList tll = JAXB.unmarshal(is, TemperatureLocationList.class);

    TemperatureLocation tl = tll.getLocations().get(0);

    assertEquals(-59.083,tl.getLat(),0.001);
    assertEquals(-26.583,tl.getLng(),0.001);

  }

  /**
   * Only required during development
   */
  @Test
  public void testLiveData() {

    TemperatureLocationDao testObject = new TemperatureLocationDao();

    List<TemperatureLocation> results = testObject.getTemperatureLocationData(51.65, 0.1);

    assertEquals("temp", results.get(0).getMonthlyVariance().getName());
    assertEquals("2.2", results.get(0).getMonthlyVariance().getJan());

  }

}
