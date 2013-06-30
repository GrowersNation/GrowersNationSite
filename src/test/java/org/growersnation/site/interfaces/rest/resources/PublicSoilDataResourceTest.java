package org.growersnation.site.interfaces.rest.resources;

import com.google.common.base.Preconditions;
import com.yammer.dropwizard.testing.FixtureHelpers;
import com.yammer.dropwizard.testing.JsonHelpers;
import org.growersnation.site.infrastructure.persistence.dao.soil.http.PHBulkDensityDao;
import org.growersnation.site.infrastructure.persistence.dao.soil.http.SoilTextureDao;
import org.growersnation.site.infrastructure.persistence.dao.soil.http.TopsoilCarbonDao;
import org.growersnation.site.infrastructure.persistence.dao.soil.http.TopsoilNutrientsDao;
import org.growersnation.site.infrastructure.thirdparty.bgs.soil.SoilData;
import org.growersnation.site.test.BaseResourceTest;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PublicSoilDataResourceTest extends BaseResourceTest {

  private final PHBulkDensityDao phBulkDensityDao = mock(PHBulkDensityDao.class);
  private final SoilTextureDao soilTextureDao = mock(SoilTextureDao.class);
  private final TopsoilCarbonDao topsoilCarbonDao = mock(TopsoilCarbonDao.class);
  private final TopsoilNutrientsDao topsoilNutrientsDao = mock(TopsoilNutrientsDao.class);

  private SoilData expectedSoilData;

  @Before
  public void setUp() throws IOException {

    expectedSoilData = JsonHelpers.fromJson(FixtureHelpers.fixture("fixtures/soil/soildata/test-soildata-all-1.json"), SoilData.class);

    Preconditions.checkNotNull(expectedSoilData);

    // Configure expectations
    when(phBulkDensityDao.getPHBulkDensityData(51.65,0.1))
      .thenReturn(expectedSoilData.getPhBulkDensityFields());

    when(soilTextureDao.getSoilTextureData(51.65, 0.1))
      .thenReturn(expectedSoilData.getSoilTextureFields());

    when(topsoilCarbonDao.getTopsoilCarbonData(51.65, 0.1))
      .thenReturn(expectedSoilData.getTopsoilCarbonFields());

    when(topsoilNutrientsDao.getTopsoilNutrientsData(51.65, 0.1))
      .thenReturn(expectedSoilData.getTopsoilNutrientsFields());

  }

  @Test
  public void testGetSoilData() {

    // Arrange
    PublicSoilDataResource testObject = new PublicSoilDataResource();
    testObject.setPhBulkDensityDao(phBulkDensityDao);
    testObject.setSoilTextureDao(soilTextureDao);
    testObject.setTopsoilCarbonDao(topsoilCarbonDao);
    testObject.setTopsoilNutrientsDao(topsoilNutrientsDao);

    // Act
    SoilData soilData = testObject.getSoilData("51.65,0.1",null);

    // Assert
    assertThat(soilData).isNotNull();

  }

  @Test
  public void testPostSoilData() throws IOException {

    // Arrange
    PublicSoilDataResource testObject = new PublicSoilDataResource();
    testObject.setPhBulkDensityDao(phBulkDensityDao);
    testObject.setSoilTextureDao(soilTextureDao);
    testObject.setTopsoilCarbonDao(topsoilCarbonDao);
    testObject.setTopsoilNutrientsDao(topsoilNutrientsDao);

    // Act
    Response response = testObject.postSoilData(expectedSoilData);

    // Assert
    assertThat(response).isNotNull();

  }
}
