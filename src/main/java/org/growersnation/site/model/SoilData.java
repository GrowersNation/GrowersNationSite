package org.growersnation.site.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import org.growersnation.site.model.carbon.CarbonFields;

import java.util.List;

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
public class SoilData {

  @JsonProperty
  private List<CarbonFields> carbonFields = Lists.newArrayList();

  public List<CarbonFields> getCarbonFields() {
    return carbonFields;
  }

  public void setCarbonFields(List<CarbonFields> carbonFields) {
    this.carbonFields = carbonFields;
  }
}
