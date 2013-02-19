package org.growersnation.site.model.carbon;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * <p>DTO to provide the following to {@link org.growersnation.site.model.carbon.FeatureInfoResponse}:</p>
 * <ul>
 * <li>Storage of state of soil portal data</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CarbonFields {

  @JsonProperty
  @XmlAttribute(name = "DOMINANT_BROAD_HABITAT")
  private String dominantBroadHabitat;

  @JsonProperty
  @XmlAttribute(name = "CORPADMIN.Soils2000Data.DOMINANT_BROAD_HABITAT")
  private String dominantBroadHabitat2000;

  @JsonProperty
  @XmlAttribute(name = "C_CONC_1978")
  private String concentration1978;

  @JsonProperty
  @XmlAttribute(name = "C_CONC_1998")
  private String concentration1998;

  @JsonProperty
  @XmlAttribute(name = "C_CONC_2007")
  private String concentration2007;

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
  private String nConcentration1998_2007;

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
  private String numBroadtaxa9807;

  public String getDominantBroadHabitat() {
    return dominantBroadHabitat;
  }

  public void setDominantBroadHabitat(String dominantBroadHabitat) {
    this.dominantBroadHabitat = dominantBroadHabitat;
  }

  public String getDominantBroadHabitat2000() {
    return dominantBroadHabitat2000;
  }

  public void setDominantBroadHabitat2000(String dominantBroadHabitat2000) {
    this.dominantBroadHabitat2000 = dominantBroadHabitat2000;
  }

  public String getConcentration1978() {
    return concentration1978;
  }

  public void setConcentration1978(String concentration1978) {
    this.concentration1978 = concentration1978;
  }

  public String getConcentration1998() {
    return concentration1998;
  }

  public void setConcentration1998(String concentration1998) {
    this.concentration1998 = concentration1998;
  }

  public String getConcentration2007() {
    return concentration2007;
  }

  public void setConcentration2007(String concentration2007) {
    this.concentration2007 = concentration2007;
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

  public String getnConcentration1998_2007() {
    return nConcentration1998_2007;
  }

  public void setnConcentration1998_2007(String nConcentration1998_2007) {
    this.nConcentration1998_2007 = nConcentration1998_2007;
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

  public String getNumBroadtaxa9807() {
    return numBroadtaxa9807;
  }

  public void setNumBroadtaxa9807(String numBroadtaxa9807) {
    this.numBroadtaxa9807 = numBroadtaxa9807;
  }
}
