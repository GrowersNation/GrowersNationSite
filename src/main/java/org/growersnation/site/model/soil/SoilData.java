package org.growersnation.site.model.soil;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import org.growersnation.site.model.soil.carbon.TopsoilCarbonFields;
import org.growersnation.site.model.soil.nutrients.TopsoilNutrientsFields;
import org.growersnation.site.model.soil.ph.PHBulkDensityFields;
import org.growersnation.site.model.soil.texture.SoilTextureFields;

import java.util.List;

/**
 * <p>Value object to provide the following to {@link org.growersnation.site.resources.PublicSoilDataResource}:</p>
 * <ul>
 * <li>Representation of soil data</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class SoilData {

  @JsonProperty
  private List<TopsoilCarbonFields> topsoilCarbonFields = Lists.newArrayList();

  @JsonProperty
  private List<TopsoilNutrientsFields> topsoilNutrientsFields = Lists.newArrayList();

  @JsonProperty
  private List<PHBulkDensityFields> phBulkDensityFields = Lists.newArrayList();

  @JsonProperty
  private List<SoilTextureFields> soilTextureFields = Lists.newArrayList();

  public List<TopsoilCarbonFields> getTopsoilCarbonFields() {
    return topsoilCarbonFields;
  }

  public void setTopsoilCarbonFields(List<TopsoilCarbonFields> topsoilCarbonFields) {
    this.topsoilCarbonFields = topsoilCarbonFields;
  }

  public List<TopsoilNutrientsFields> getTopsoilNutrientsFields() {
    return topsoilNutrientsFields;
  }

  public void setTopsoilNutrientsFields(List<TopsoilNutrientsFields> topsoilNutrientsFields) {
    this.topsoilNutrientsFields = topsoilNutrientsFields;
  }

  public List<PHBulkDensityFields> getPhBulkDensityFields() {
    return phBulkDensityFields;
  }

  public void setPhBulkDensityFields(List<PHBulkDensityFields> phBulkDensityFields) {
    this.phBulkDensityFields = phBulkDensityFields;
  }

  public List<SoilTextureFields> getSoilTextureFields() {
    return soilTextureFields;
  }

  public void setSoilTextureFields(List<SoilTextureFields> soilTextureFields) {
    this.soilTextureFields = soilTextureFields;
  }
}
