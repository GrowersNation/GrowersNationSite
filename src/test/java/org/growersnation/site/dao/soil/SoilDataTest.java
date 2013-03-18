package org.growersnation.site.dao.soil;

import org.growersnation.site.model.soil.SoilData;
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
    final SoilData testObject = SoilDataFaker.createSoilDataWithId("abcd-1234");

    assertThat(
      // Act
      asJson(testObject))
      // Assert
      .isEqualTo(jsonFixture("fixtures/issues/issue-simple.json"));
  }

  @Test
  public void validateSoilData_nullId() {
    // Arrange
    SoilData issue = SoilDataFaker.createSoilDataWithId(null);

    // Act
    Set<ConstraintViolation<SoilData>> constraintViolations = validator.validate(issue);

    // Assert
    assertThat(constraintViolations.size()).isEqualTo(0);
  }

  @Test
  public void validateSoilData_nullTitle() {
    // Arrange
    SoilData issue = SoilDataFaker.createSoilDataWithTitle(null);

    // Act
    Set<ConstraintViolation<SoilData>> constraintViolations = validator.validate(issue);

    // Assert
    assertThat(constraintViolations.size()).isEqualTo(1);
    assertThat(constraintViolations.iterator().next().getMessage()).isNotNull();
  }
}
