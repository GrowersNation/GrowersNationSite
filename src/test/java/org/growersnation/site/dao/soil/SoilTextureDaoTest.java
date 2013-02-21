package org.growersnation.site.dao.soil;

import org.growersnation.site.model.soil.texture.FeatureInfoResponse;
import org.growersnation.site.model.soil.texture.SoilTextureFields;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SoilTextureDaoTest {

  @Test
  public void testJAXB() {

    InputStream is = SoilTextureDaoTest.class.getResourceAsStream("/texture/test-bgs-texture-clay-1.xml");
    FeatureInfoResponse fir = JAXB.unmarshal(is, FeatureInfoResponse.class);

    assertEquals(2,fir.getFields().size());

    SoilTextureFields fields = fir.getFields().get(0);

    assertEquals("Polygon",fields.getShape());

  }

  /**
   * Only required during development
   */
  @Ignore
  public void testLiveData() {

    SoilTextureDao testObject = new SoilTextureDao();

    List<SoilTextureFields> fields = testObject.getSoilTextureData(51.2, 0.1);

    assertEquals(1,fields.size());

    assertEquals("Polygon",fields.get(0).getShape());

  }


}
