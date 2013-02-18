package org.growersnation.site.dao.bgs;

import org.growersnation.site.model.bgs.FeatureInfoResponse;
import org.growersnation.site.model.bgs.Fields;
import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SoilPortalDaoTest {

  @Test
  public void testJAXB() {

    InputStream is = SoilPortalDaoTest.class.getResourceAsStream("/bgs/test-fir-clay-1.xml");
    FeatureInfoResponse fir = JAXB.unmarshal(is, FeatureInfoResponse.class);

    Fields fields = fir.getFields().get(0);

    assertEquals("Polygon",fields.getShape());

  }

  @Test
  public void testLiveData() {

    SoilPortalDao testObject = new SoilPortalDao();

    List<Fields> fields = testObject.getSoilData(51.2, 0.1);

    assertEquals("Polygon",fields.get(0).getShape());

  }


}
