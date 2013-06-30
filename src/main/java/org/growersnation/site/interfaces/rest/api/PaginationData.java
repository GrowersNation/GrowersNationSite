package org.growersnation.site.interfaces.rest.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Value object to provide pagination data to Freemarker macros</p>
 */
public class PaginationData {

  public enum SortDirection {
    ASCENDING,
    DESCENDING;
  }

  private final int pageNumber;
  private final int pageSize;
  private final long pageCount;
  private final SortDirection sortDirection;
  private final String sortField;

  @JsonCreator
  public PaginationData(
    @JsonProperty("pageNumber")
    int pageNumber,
    @JsonProperty("pageSize")
    int pageSize,
    @JsonProperty("pageCount")
    long pageCount,
    @JsonProperty("sortDirection")
    SortDirection sortDirection,
    @JsonProperty("sortField")
    String sortField) {

    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.pageCount = pageCount;
    this.sortDirection = sortDirection;
    this.sortField = sortField;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public long getPageCount() {
    return pageCount;
  }

  public SortDirection getSortDirection() {
    return sortDirection;
  }

  public String getSortField() {
    return sortField;
  }

}
