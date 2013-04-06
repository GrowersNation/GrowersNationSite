package org.growersnation.site.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>Value object to provide the following to data sets labelled with month names:</p>
 * <ul>
 * <li>Consistent mapping format</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MonthlyVariance {

  @JsonProperty
  @XmlAttribute(name = "name")
  private String name;

  @JsonProperty
  @XmlElement(name="jan")
  private String jan;

  @JsonProperty
  @XmlElement(name="feb")
  private String feb;

  @JsonProperty
  @XmlElement(name="mar")
  private String mar;

  @JsonProperty
  @XmlElement(name="apr")
  private String apr;

  @JsonProperty
  @XmlElement(name="may")
  private String may;

  @JsonProperty
  @XmlElement(name="jun")
  private String jun;

  @JsonProperty
  @XmlElement(name="jul")
  private String jul;

  @JsonProperty
  @XmlElement(name="aug")
  private String aug;

  @JsonProperty
  @XmlElement(name="sep")
  private String sep;

  @JsonProperty
  @XmlElement(name="oct")
  private String oct;

  @JsonProperty
  @XmlElement(name="nov")
  private String nov;

  @JsonProperty
  @XmlElement(name="dec")
  private String dec;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJan() {
    return jan;
  }

  public void setJan(String jan) {
    this.jan = jan;
  }

  public String getFeb() {
    return feb;
  }

  public void setFeb(String feb) {
    this.feb = feb;
  }

  public String getMar() {
    return mar;
  }

  public void setMar(String mar) {
    this.mar = mar;
  }

  public String getApr() {
    return apr;
  }

  public void setApr(String apr) {
    this.apr = apr;
  }

  public String getMay() {
    return may;
  }

  public void setMay(String may) {
    this.may = may;
  }

  public String getJun() {
    return jun;
  }

  public void setJun(String jun) {
    this.jun = jun;
  }

  public String getJul() {
    return jul;
  }

  public void setJul(String jul) {
    this.jul = jul;
  }

  public String getAug() {
    return aug;
  }

  public void setAug(String aug) {
    this.aug = aug;
  }

  public String getSep() {
    return sep;
  }

  public void setSep(String sep) {
    this.sep = sep;
  }

  public String getOct() {
    return oct;
  }

  public void setOct(String oct) {
    this.oct = oct;
  }

  public String getNov() {
    return nov;
  }

  public void setNov(String nov) {
    this.nov = nov;
  }

  public String getDec() {
    return dec;
  }

  public void setDec(String dec) {
    this.dec = dec;
  }
}
