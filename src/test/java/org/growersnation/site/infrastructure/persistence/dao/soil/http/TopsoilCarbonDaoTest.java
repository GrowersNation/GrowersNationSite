package org.growersnation.site.infrastructure.persistence.dao.soil.http;

import org.growersnation.site.infrastructure.dto.thirdparty.bgs.soil.carbon.FeatureInfoResponse;
import org.growersnation.site.infrastructure.dto.thirdparty.bgs.soil.carbon.TopsoilCarbonFields;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class TopsoilCarbonDaoTest {

  @Test
  public void testJAXB() {

    InputStream is = TopsoilCarbonDaoTest.class.getResourceAsStream("/fixtures/soil/carbon/test-nerc-topsoil-carbon-1.xml");
    FeatureInfoResponse fir = JAXB.unmarshal(is, FeatureInfoResponse.class);

    TopsoilCarbonFields fields = fir.getFields().get(0);

    assertThat(fields.getCnRatio1998()).isEqualTo("11.7");

  }

  /**
   * Only required during development
   */
  @Ignore
  public void testLiveData() {

    TopsoilCarbonDao testObject = new TopsoilCarbonDao();

    // 0.08,51.63,0.12,51.67
    List<TopsoilCarbonFields> fields = testObject.getTopsoilCarbonData(51.65, 0.1);

    assertThat(fields.get(0).getCnRatio1998()).isEqualTo("11.7");

  }


}
