package org.growersnation.site.infrastructure.thirdparty.metoffice.gdd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Value object to provide the following to application:</p>
 * <ul>
 * <li>Provision of monthly growing degree day data for a given location over 30 years</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class GrowingDegreeDays30YearLocation {

  @JsonProperty
  private final double[] loc;

  @JsonProperty
  private String valueUnit="GDD";

  @JsonCreator
  public GrowingDegreeDays30YearLocation(
    @JsonProperty("loc") double[] loc
  ) {
    this.loc = loc;
  }


}
