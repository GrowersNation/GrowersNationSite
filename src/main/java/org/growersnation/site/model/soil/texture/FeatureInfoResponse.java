package org.growersnation.site.model.soil.texture;

import com.google.common.collect.Lists;
import org.growersnation.site.model.soil.FieldAccessor;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * <p>DTO to provide the following to DAOs:</p>
 * <ul>
 * <li>Storage of state for soil portal data</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
// No namespace for the texture data from BGS
@XmlRootElement(name = "FeatureInfoResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(SoilTextureFields.class)
public class FeatureInfoResponse implements FieldAccessor<SoilTextureFields> {

  // No namespace provided
  @XmlElement(name="FIELDS")
  private List<SoilTextureFields> fields= Lists.newArrayList();

  public List<SoilTextureFields> getFields() {
    return fields;
  }

  public void setFields(List<SoilTextureFields> fields) {
    this.fields = fields;
  }
}
