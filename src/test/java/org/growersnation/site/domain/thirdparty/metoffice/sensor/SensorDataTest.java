package org.growersnation.site.domain.thirdparty.metoffice.sensor;

import com.yammer.dropwizard.testing.FixtureHelpers;
import com.yammer.dropwizard.testing.JsonHelpers;
import org.growersnation.site.domain.thirdparty.sensor.SensorData;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.fest.assertions.api.Assertions.assertThat;

public class SensorDataTest {


  private Validator validator;

  @Before
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void serializesToJSON() throws Exception {

        // Arrange
    final SensorData testObject = JsonHelpers.fromJson(
      FixtureHelpers.fixture("fixtures/sensor/test-sensordata.json"),
      SensorData.class);

    assertThat(
      // Act
      asJson(testObject))
      // Assert
      .isEqualTo(jsonFixture("fixtures/sensor/test-sensordata.json"));

  }


}
