package org.growersnation.site.domain.thirdparty.bgs.soil.nutrients;

import com.google.common.collect.Lists;
import org.growersnation.site.domain.thirdparty.bgs.soil.FieldAccessor;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * <p>DTO to provide the following to DAOs:</p>
 * <ul>
 * <li>Storage of state for NERC topsoil nutrient data</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
@XmlRootElement(name = "FeatureInfoResponse", namespace = "http://www.esri.com/wms")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(TopsoilNutrientsFields.class)
public class FeatureInfoResponse implements FieldAccessor<TopsoilNutrientsFields> {

  @XmlElement(name="FIELDS", namespace = "http://www.esri.com/wms")
  private List<TopsoilNutrientsFields> fields= Lists.newArrayList();

  public List<TopsoilNutrientsFields> getFields() {
    return fields;
  }

  public void setFields(List<TopsoilNutrientsFields> fields) {
    this.fields = fields;
  }
}
