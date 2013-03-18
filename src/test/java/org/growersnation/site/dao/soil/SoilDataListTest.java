package org.growersnation.site.dao.soil;

import com.yammer.dropwizard.testing.FixtureHelpers;
import com.yammer.dropwizard.testing.JsonHelpers;
import org.growersnation.site.model.soil.SoilDataList;
import org.junit.Test;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.fest.assertions.api.Assertions.assertThat;

public class SoilDataListTest {

  @Test
  public void serializesToJSON() throws Exception {

    // Arrange
    final SoilDataList testObject = JsonHelpers.fromJson(
      FixtureHelpers.fixture("fixtures/issues/issue-list-simple.json"),
      SoilDataList.class);

    assertThat(
      // Act
      asJson(testObject))
      // Assert
      .isEqualTo(jsonFixture("fixtures/issues/issue-list-simple.json"));

    assertThat(testObject.getPaginationData().getPageNumber()).isEqualTo(0);
    assertThat(testObject.getPaginationData().getPageSize()).isEqualTo(10);

  }

}
