package org.growersnation.site.model.soil.carbon;

import com.google.common.collect.Lists;
import org.growersnation.site.model.soil.FieldAccessor;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * <p>DTO to provide the following to DAOs:</p>
 * <ul>
 * <li>Storage of state for NERC topsoil pH bulk density data</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
@XmlRootElement(name = "FeatureInfoResponse", namespace = "http://www.esri.com/wms")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(CarbonFields.class)
public class FeatureInfoResponse implements FieldAccessor<CarbonFields> {

  @XmlElement(name="FIELDS", namespace = "http://www.esri.com/wms")
  private List<CarbonFields> fields= Lists.newArrayList();

  public List<CarbonFields> getFields() {
    return fields;
  }

  public void setFields(List<CarbonFields> fields) {
    this.fields = fields;
  }
}
