package org.growersnation.site.infrastructure.dto.thirdparty.bgs.soil.ph;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * <p>DTO to provide the following to {@link org.growersnation.site.model.soil.ph.FeatureInfoResponse}:</p>
 * <ul>
 * <li>Storage of state of soil portal data</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PHBulkDensityFields {

  @JsonProperty
  @XmlAttribute(name = "DOMINANT_BROAD_HABITAT")
  private String dominantBroadHabitat;

  @JsonProperty
  @XmlAttribute(name = "pH_1978")
  private String ph1978;

  @JsonProperty
  @XmlAttribute(name = "pH_1998")
  private String ph1998;

  @JsonProperty
  @XmlAttribute(name = "pH_2007")
  private String ph2007;

  @JsonProperty
  @XmlAttribute(name = "CORPADMIN.Soils2000Data.CN_RATIO_1998")
  private String cnRatio1998;

  @JsonProperty
  @XmlAttribute(name = "CORPADMIN.Soils2000Data.CN_RATIO_2007")
  private String cnRatio2007;

  @JsonProperty
  @XmlAttribute(name = "CORPADMIN.Soils2000Data.CN_RATIO_1998_2007")
  private String cnRatio1998_2007;

  @JsonProperty
  @XmlAttribute(name = "CORPADMIN.Soils2000Data.N_CONC_1998_2007")
  private String nConc1998_2007;

  @JsonProperty
  @XmlAttribute(name = "CORPADMIN.Soils2000Data.MITE_SPRINGTAIL_RATIO_1998")
  private String miteSpringtailRatio1998;

  @JsonProperty
  @XmlAttribute(name = "CORPADMIN.Soils2000Data.MITE_SPRINGTAIL_RATIO_2007")
  private String miteSpringtailRatio2007;

  @JsonProperty
  @XmlAttribute(name = "CORPADMIN.Soils2000Data.MITE_SPRINGTAIL_RATIO_9807")
  private String miteSpringtailRatio9807;

  @JsonProperty
  @XmlAttribute(name = "CORPADMIN.Soils2000Data.NUM_BROADTAXA_1998")
  private String numBroadtaxa1998;

  @JsonProperty
  @XmlAttribute(name = "CORPADMIN.Soils2000Data.NUM_BROADTAXA_2007")
  private String numBroadtaxa2007;

  @JsonProperty
  @XmlAttribute(name = "CORPADMIN.Soils2000Data.NUM_BROADTAXA_1998_2007")
  private String numBroadtaxa1998_2007;

  public String getDominantBroadHabitat() {
    return dominantBroadHabitat;
  }

  public void setDominantBroadHabitat(String dominantBroadHabitat) {
    this.dominantBroadHabitat = dominantBroadHabitat;
  }

  public String getPh1978() {
    return ph1978;
  }

  public void setPh1978(String ph1978) {
    this.ph1978 = ph1978;
  }

  public String getPh1998() {
    return ph1998;
  }

  public void setPh1998(String ph1998) {
    this.ph1998 = ph1998;
  }

  public String getPh2007() {
    return ph2007;
  }

  public void setPh2007(String ph2007) {
    this.ph2007 = ph2007;
  }

  public String getCnRatio1998() {
    return cnRatio1998;
  }

  public void setCnRatio1998(String cnRatio1998) {
    this.cnRatio1998 = cnRatio1998;
  }

  public String getCnRatio2007() {
    return cnRatio2007;
  }

  public void setCnRatio2007(String cnRatio2007) {
    this.cnRatio2007 = cnRatio2007;
  }

  public String getCnRatio1998_2007() {
    return cnRatio1998_2007;
  }

  public void setCnRatio1998_2007(String cnRatio1998_2007) {
    this.cnRatio1998_2007 = cnRatio1998_2007;
  }

  public String getnConc1998_2007() {
    return nConc1998_2007;
  }

  public void setnConc1998_2007(String nConc1998_2007) {
    this.nConc1998_2007 = nConc1998_2007;
  }

  public String getMiteSpringtailRatio1998() {
    return miteSpringtailRatio1998;
  }

  public void setMiteSpringtailRatio1998(String miteSpringtailRatio1998) {
    this.miteSpringtailRatio1998 = miteSpringtailRatio1998;
  }

  public String getMiteSpringtailRatio2007() {
    return miteSpringtailRatio2007;
  }

  public void setMiteSpringtailRatio2007(String miteSpringtailRatio2007) {
    this.miteSpringtailRatio2007 = miteSpringtailRatio2007;
  }

  public String getMiteSpringtailRatio9807() {
    return miteSpringtailRatio9807;
  }

  public void setMiteSpringtailRatio9807(String miteSpringtailRatio9807) {
    this.miteSpringtailRatio9807 = miteSpringtailRatio9807;
  }

  public String getNumBroadtaxa1998() {
    return numBroadtaxa1998;
  }

  public void setNumBroadtaxa1998(String numBroadtaxa1998) {
    this.numBroadtaxa1998 = numBroadtaxa1998;
  }

  public String getNumBroadtaxa2007() {
    return numBroadtaxa2007;
  }

  public void setNumBroadtaxa2007(String numBroadtaxa2007) {
    this.numBroadtaxa2007 = numBroadtaxa2007;
  }

  public String getNumBroadtaxa1998_2007() {
    return numBroadtaxa1998_2007;
  }

  public void setNumBroadtaxa1998_2007(String numBroadtaxa1998_2007) {
    this.numBroadtaxa1998_2007 = numBroadtaxa1998_2007;
  }
}
