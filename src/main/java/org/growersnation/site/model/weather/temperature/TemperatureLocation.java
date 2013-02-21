package org.growersnation.site.model.weather.temperature;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.growersnation.site.model.MonthlyVariance;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>Value object to provide the following to {@link TemperatureLocationList}:</p>
 * <ul>
 * <li>Provision of monthly temperature data for a given location</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TemperatureLocation {

  @JsonProperty
  @XmlElement(name = "lat")
  private double lat;

  @JsonProperty
  @XmlElement(name = "lon")
  private double lng;

  @JsonProperty
  @XmlElement(name = "unit")
  private String unit;

  @JsonProperty
  @XmlElement(name = "var")
  private MonthlyVariance monthlyVariance;

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLng() {
    return lng;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public MonthlyVariance getMonthlyVariance() {
    return monthlyVariance;
  }

  public void setMonthlyVariance(MonthlyVariance monthlyVariance) {
    this.monthlyVariance = monthlyVariance;
  }
}
