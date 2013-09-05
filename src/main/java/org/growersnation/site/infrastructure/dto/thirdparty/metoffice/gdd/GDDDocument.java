package org.growersnation.site.infrastructure.dto.thirdparty.metoffice.gdd;

import java.util.List;

public class GDDDocument {

  private String description;
  private List<GDDLocations> locations;
  private String name;
  private String valueUnit;

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<GDDLocations> getLocations() {
    return this.locations;
  }

  public void setLocations(List<GDDLocations> locations) {
    this.locations = locations;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValueUnit() {
    return this.valueUnit;
  }

  public void setValueUnit(String valueUnit) {
    this.valueUnit = valueUnit;
  }
}
