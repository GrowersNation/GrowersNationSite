package org.growersnation.site.model.soil.nutrients;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * <p>DTO to provide the following to {@link org.growersnation.site.model.soil.nutrients.FeatureInfoResponse}:</p>
 * <ul>
 * <li>Storage of state of topsoil nutrients fields</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TopsoilNutrientsFields {

  @JsonProperty
  @XmlAttribute(name = "DOMINANT_BROAD_HABITAT")
  private String dominantBroadHabitat;

  @JsonProperty
  @XmlAttribute(name = "Minerisable_N_2007")
  private String minerisableN2007;

  public String getDominantBroadHabitat() {
    return dominantBroadHabitat;
  }

  public void setDominantBroadHabitat(String dominantBroadHabitat) {
    this.dominantBroadHabitat = dominantBroadHabitat;
  }

  public String getMinerisableN2007() {
    return minerisableN2007;
  }

  public void setMinerisableN2007(String minerisableN2007) {
    this.minerisableN2007 = minerisableN2007;
  }
}
