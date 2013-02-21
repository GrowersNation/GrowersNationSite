package org.growersnation.site.model.weather.temperature;

import com.google.common.collect.Lists;
import org.growersnation.site.model.soil.texture.SoilTextureFields;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * <p>DTO to provide the following to DAOs:</p>
 * <ul>
 * <li>Wrapper for the temperature location data for JAXB</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
// No namespace for the texture data from BGS
@XmlRootElement(name = "locs")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(SoilTextureFields.class)
public class TemperatureLocationList {

  // No namespace provided
  @XmlElement(name="loc")
  private List<TemperatureLocation> locations= Lists.newArrayList();

  public List<TemperatureLocation> getLocations() {
    return locations;
  }

  public void setLocations(List<TemperatureLocation> locations) {
    this.locations = locations;
  }
}
