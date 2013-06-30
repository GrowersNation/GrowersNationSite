package org.growersnation.site.infrastructure.persistence.dao.soil.http;

import org.growersnation.site.infrastructure.thirdparty.bgs.soil.nutrients.FeatureInfoResponse;
import org.growersnation.site.infrastructure.thirdparty.bgs.soil.nutrients.TopsoilNutrientsFields;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.InputStream;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class TopsoilNutrientsDaoTest {

  @Test
  public void testJAXB() {

    InputStream is = TopsoilNutrientsDaoTest.class.getResourceAsStream("/fixtures/soil/nutrients/test-nerc-topsoil-nutrients-1.xml");
    FeatureInfoResponse fir = JAXB.unmarshal(is, FeatureInfoResponse.class);

    TopsoilNutrientsFields fields = fir.getFields().get(0);

    assertThat(fields.getDominantBroadHabitat()).isEqualTo("Improved grassland");

  }

  /**
   * Only required during development
   */
  @Ignore
  public void testLiveData() {

    TopsoilNutrientsDao testObject = new TopsoilNutrientsDao();

    List<TopsoilNutrientsFields> fields = testObject.getTopsoilNutrientsData(51.2, 0.1);

    assertThat(fields.get(0).getDominantBroadHabitat()).isEqualTo("Improved grassland");

  }


}
