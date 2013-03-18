package org.growersnation.site.dao.soil.http;

import org.growersnation.site.model.soil.ph.FeatureInfoResponse;
import org.growersnation.site.model.soil.ph.PHBulkDensityFields;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class PHBulkDensityDaoTest {

  @Test
  public void testJAXB() throws JAXBException {

    InputStream is = PHBulkDensityDaoTest.class.getResourceAsStream("/soil/ph/test-nerc-topsoil-ph-1.xml");

    // Build JAXB the long way to get better error messages
    JAXBContext context = JAXBContext.newInstance(FeatureInfoResponse.class);

    FeatureInfoResponse fir = (FeatureInfoResponse) context.createUnmarshaller().unmarshal(is);

    assertThat(fir.getFields().size()).isEqualTo(1);

    PHBulkDensityFields fields = fir.getFields().get(0);

    assertThat(fields.getCnRatio1998()).isEqualTo("11.8");

  }

  /**
   * Only required during development
   */
  @Ignore
  public void testLiveData() {

    PHBulkDensityDao testObject = new PHBulkDensityDao();

    List<PHBulkDensityFields> fields = testObject.getPHBulkDensityData(51.2, 0.1);

    assertThat(fields.size()).isEqualTo(1);
    assertThat(fields.get(0).getCnRatio1998()).isEqualTo("11.8");

  }


}

