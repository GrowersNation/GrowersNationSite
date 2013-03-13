package org.growersnation.site.model.soil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.growersnation.site.model.PaginationData;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>Value object to provide the following to {@link org.growersnation.site.resources.PublicSoilDataResource}:</p>
 * <ul>
 * <li>Representation of a collection of soil data results</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class SoilDataList {

  @JsonProperty
  @NotNull
  private List<SoilData> soilData;

  @JsonProperty
  @NotNull
  private PaginationData paginationData;

  @JsonCreator
  public SoilDataList(
    @JsonProperty("paginationData") PaginationData paginationData,
    @JsonProperty("soilData") List<SoilData> soilData
  ) {

    this.paginationData = paginationData;
    this.soilData = soilData;
  }

  /**
   * @return The list of soil data results sorted in accordance with the pagination data
   */
  public List<SoilData> getSoilData() {
    return soilData;
  }

  /**
   * @return The pagination data describing how the soil data results fit into the overall result set
   */
  public PaginationData getPaginationData() {
    return paginationData;
  }

}
