package org.growersnation.site.resources;

import com.yammer.dropwizard.testing.FixtureHelpers;
import com.yammer.dropwizard.testing.JsonHelpers;
import org.growersnation.site.dao.security.UserDao;
import org.growersnation.site.dao.security.mem.InMemoryUserDao;
import org.growersnation.site.dao.soil.http.PHBulkDensityDao;
import org.growersnation.site.dao.soil.http.SoilTextureDao;
import org.growersnation.site.dao.soil.http.TopsoilCarbonDao;
import org.growersnation.site.dao.soil.http.TopsoilNutrientsDao;
import org.growersnation.site.model.soil.SoilData;
import org.growersnation.site.test.BaseResourceTest;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
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

    expectedSoilData = JsonHelpers.fromJson(FixtureHelpers.fixture("soil/soildata/test-soildata-all-1.json"), SoilData.class);

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

    UserDao userDao = new InMemoryUserDao();

    // Arrange
    PublicSoilDataResource testObject = new PublicSoilDataResource(userDao);
    testObject.setPhBulkDensityDao(phBulkDensityDao);
    testObject.setSoilTextureDao(soilTextureDao);
    testObject.setTopsoilCarbonDao(topsoilCarbonDao);
    testObject.setTopsoilNutrientsDao(topsoilNutrientsDao);

    // Act
    SoilData soilData = testObject.getSoilData("51.65,0.1",null);

    // Assert
    assertNotNull(soilData);

  }

  @Test
  public void testPostSoilData() throws IOException {

    UserDao userDao = new InMemoryUserDao();

    // Arrange
    PublicSoilDataResource testObject = new PublicSoilDataResource(userDao);
    testObject.setPhBulkDensityDao(phBulkDensityDao);
    testObject.setSoilTextureDao(soilTextureDao);
    testObject.setTopsoilCarbonDao(topsoilCarbonDao);
    testObject.setTopsoilNutrientsDao(topsoilNutrientsDao);

    // Act
    Response response = testObject.postSoilData(expectedSoilData);

    // Assert
    assertNotNull(response);

  }
}
