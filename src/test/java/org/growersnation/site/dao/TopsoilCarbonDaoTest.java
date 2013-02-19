package org.growersnation.site.dao;

import org.growersnation.site.model.carbon.CarbonFields;
import org.growersnation.site.model.carbon.FeatureInfoResponse;
import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TopsoilCarbonDaoTest {

  @Test
  public void testJAXB() {

    InputStream is = TopsoilCarbonDaoTest.class.getResourceAsStream("/carbon/test-nerc-topsoil-carbon-1.xml");
    FeatureInfoResponse fir = JAXB.unmarshal(is, FeatureInfoResponse.class);

    CarbonFields fields = fir.getFields().get(0);

    assertEquals("11.7",fields.getCnRatio1998());

  }

  @Test
  public void testLiveData() {

    TopsoilCarbonDao testObject = new TopsoilCarbonDao();

    // 0.08,51.63,0.12,51.67
    List<CarbonFields> fields = testObject.getTopsoilCarbonData(51.65, 0.1);

    assertEquals("11.7",fields.get(0).getCnRatio1998());

  }


}
