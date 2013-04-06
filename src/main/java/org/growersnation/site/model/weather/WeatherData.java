package org.growersnation.site.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.growersnation.site.model.weather.temperature.TemperatureLocation;

import java.util.List;

/**
 * <p>Value object to provide the following to {@link org.growersnation.site.resources.PublicWeatherDataResource}:</p>
 * <ul>
 * <li>Wrapper for various climate data entries</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class WeatherData {

  @JsonProperty
  private List<TemperatureLocation> temperatureLocations;

  public void setTemperatureLocations(List<TemperatureLocation> temperatureLocations) {
    this.temperatureLocations = temperatureLocations;
  }

  public List<TemperatureLocation> getTemperatureLocations() {
    return temperatureLocations;
  }
}
