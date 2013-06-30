package org.growersnation.site.infrastructure.thirdparty.sensor;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>[Pattern] to provide the following to {@link Object}:</p>
 * <ul>
 * <li></li>
 * </ul>
 * <p>Example:</p>
 * <pre>
 * </pre>
 *
 * @since 0.0.1
 *         
 */
public class SensorData {

  @JsonProperty
  private String id;

  @JsonProperty
  private String time;

  @JsonProperty
  private String lat;

  @JsonProperty(value = "long")
  private String lng;

  @JsonProperty
  private Boolean hasAltitude;

  @JsonProperty
  private String altitude;

  @JsonProperty
  private Boolean hasPH;

  @JsonProperty
  private String pH;

  @JsonProperty
  private Boolean hasMoisture;

  @JsonProperty
  private String moisture;

  @JsonProperty
  private Boolean hasTemperature;

  @JsonProperty
  private String temperature;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getLng() {
    return lng;
  }

  public void setLng(String lng) {
    this.lng = lng;
  }

  public Boolean getHasAltitude() {
    return hasAltitude;
  }

  public void setHasAltitude(Boolean hasAltitude) {
    this.hasAltitude = hasAltitude;
  }

  public String getAltitude() {
    return altitude;
  }

  public void setAltitude(String altitude) {
    this.altitude = altitude;
  }

  public Boolean getHasPH() {
    return hasPH;
  }

  public void setHasPH(Boolean hasPH) {
    this.hasPH = hasPH;
  }

  public String getpH() {
    return pH;
  }

  public void setpH(String pH) {
    this.pH = pH;
  }

  public Boolean getHasMoisture() {
    return hasMoisture;
  }

  public void setHasMoisture(Boolean hasMoisture) {
    this.hasMoisture = hasMoisture;
  }

  public String getMoisture() {
    return moisture;
  }

  public void setMoisture(String moisture) {
    this.moisture = moisture;
  }

  public Boolean getHasTemperature() {
    return hasTemperature;
  }

  public void setHasTemperature(Boolean hasTemperature) {
    this.hasTemperature = hasTemperature;
  }

  public String getTemperature() {
    return temperature;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }
}
