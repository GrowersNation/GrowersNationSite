package org.growersnation.site.domain.thirdparty.bgs.soil.ph;

import com.google.common.collect.Lists;
import org.growersnation.site.domain.thirdparty.bgs.soil.FieldAccessor;

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
@XmlSeeAlso(PHBulkDensityFields.class)
public class FeatureInfoResponse implements FieldAccessor<PHBulkDensityFields> {

  @XmlElement(name = "FIELDS", namespace = "http://www.esri.com/wms")
  private List<PHBulkDensityFields> fields= Lists.newArrayList();

  public List<PHBulkDensityFields> getFields() {
    return fields;
  }

  public void setFields(List<PHBulkDensityFields> fields) {
    this.fields = fields;
  }

}
