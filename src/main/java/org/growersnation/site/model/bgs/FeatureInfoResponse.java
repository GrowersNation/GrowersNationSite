package org.growersnation.site.model.bgs;

import com.google.common.collect.Lists;

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
@XmlRootElement
@XmlType(name = "FeatureInfoResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class FeatureInfoResponse {

  @XmlElement(name="FIELDS")
  private List<Fields> fields= Lists.newArrayList();

  public List<Fields> getFields() {
    return fields;
  }

  public void setFields(List<Fields> fields) {
    this.fields = fields;
  }
}
