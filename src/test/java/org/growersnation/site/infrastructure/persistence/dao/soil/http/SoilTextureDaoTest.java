package org.growersnation.site.infrastructure.persistence.dao.soil.http;


import org.growersnation.site.infrastructure.thirdparty.bgs.soil.texture.FeatureInfoResponse;
import org.growersnation.site.infrastructure.thirdparty.bgs.soil.texture.SoilTextureFields;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class SoilTextureDaoTest {

  @Test
  public void testJAXB() {

    InputStream is = SoilTextureDaoTest.class.getResourceAsStream("/fixtures/soil/texture/test-bgs-texture-clay-1.xml");
    FeatureInfoResponse fir = JAXB.unmarshal(is, FeatureInfoResponse.class);

    assertThat(fir.getFields().size()).isEqualTo(2);

    SoilTextureFields fields = fir.getFields().get(0);

    assertThat(fields.getShape()).isEqualTo("Polygon");

  }

  /**
   * Only required during development
   */
  @Ignore
  public void testLiveData() {

    SoilTextureDao testObject = new SoilTextureDao();

    List<SoilTextureFields> fields = testObject.getSoilTextureData(51.2, 0.1);

    assertThat(fields.size()).isEqualTo(1);

    assertThat(fields.get(0).getShape()).isEqualTo("Polygon");

  }


}
