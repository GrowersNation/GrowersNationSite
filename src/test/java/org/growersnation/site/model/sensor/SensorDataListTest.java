package org.growersnation.site.model.sensor;

import com.yammer.dropwizard.testing.FixtureHelpers;
import com.yammer.dropwizard.testing.JsonHelpers;
import org.growersnation.site.model.soil.SoilDataList;
import org.junit.Test;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.fest.assertions.api.Assertions.assertThat;

public class SensorDataListTest {

  @Test
  public void serializesToJSON() throws Exception {

    // Arrange
    final SoilDataList testObject = JsonHelpers.fromJson(
      FixtureHelpers.fixture("fixtures/soil/soildata/test-soildatalist-1.json"),
      SoilDataList.class);

    assertThat(
      // Act
      asJson(testObject))
      // Assert
      .isEqualTo(jsonFixture("fixtures/soil/soildata/test-soildatalist-1.json"));

    assertThat(testObject.getPaginationData().getPageNumber()).isEqualTo(0);
    assertThat(testObject.getPaginationData().getPageSize()).isEqualTo(10);

  }

}
