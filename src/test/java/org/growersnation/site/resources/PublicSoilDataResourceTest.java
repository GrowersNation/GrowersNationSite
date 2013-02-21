package org.growersnation.site.resources;

import org.growersnation.site.model.soil.SoilData;
import org.growersnation.site.test.BaseResourceTest;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PublicSoilDataResourceTest extends BaseResourceTest {

  @Test
  public void testGetSoilData() {

    PublicSoilDataResource testObject = new PublicSoilDataResource();

    SoilData soilData = testObject.getSoilData("51.65,0.1",null);

    assertNotNull(soilData);

  }

}
