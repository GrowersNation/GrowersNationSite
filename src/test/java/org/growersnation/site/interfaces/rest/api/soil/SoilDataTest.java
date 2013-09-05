package org.growersnation.site.interfaces.rest.api.soil;

import com.yammer.dropwizard.testing.FixtureHelpers;
import com.yammer.dropwizard.testing.JsonHelpers;
import org.growersnation.site.infrastructure.dto.thirdparty.bgs.soil.SoilData;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.fest.assertions.api.Assertions.assertThat;

public class SoilDataTest {


  private Validator validator;

  @Before
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void serializesToJSON() throws Exception {

        // Arrange
    final SoilData testObject = JsonHelpers.fromJson(
      FixtureHelpers.fixture("fixtures/soil/soildata/test-soildata-all-1.json"),
      SoilData.class);

    assertThat(
      // Act
      asJson(testObject))
      // Assert
      .isEqualTo(jsonFixture("fixtures/soil/soildata/test-soildata-all-1.json"));

    assertThat(testObject.getPhBulkDensityFields().size()).isEqualTo(1);
//
//
//
//
//    // Arrange
//    final SoilData testObject = SoilDataFaker.createValidSoilData();
//
//    assertThat(
//      // Act
//      asJson(testObject))
//      // Assert
//      .isEqualTo(jsonFixture("fixtures/soil/soildata/test-soildata-all-1.json"));
  }

  @Test
  public void validateSoilData_nullId() {
    // Arrange
    SoilData issue = SoilDataFaker.createValidSoilData();

    // Act
    Set<ConstraintViolation<SoilData>> constraintViolations = validator.validate(issue);

    // Assert
    assertThat(constraintViolations.size()).isEqualTo(0);
  }

}
