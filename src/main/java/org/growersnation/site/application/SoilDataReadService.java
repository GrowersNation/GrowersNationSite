package org.growersnation.site.application;


import org.growersnation.site.infrastructure.dto.thirdparty.bgs.soil.SoilData;

import java.util.List;

/**
 * <p>ReadService to provide the following to Resources:</p>
 * <ul>
 * <li>Read access to {@link SoilData}s</li>
 * </ul>
 *
 * @since 0.0.1
 */
public interface SoilDataReadService {

  /**
   * @param page     The starting page (0-based)
   * @param pageSize The number of results per page
   * @return The list of newest soilData
   */
  List<SoilData> fetchNewestSoilData(int page, int pageSize);

  /**
   * @return The total number of new soilData
   */
  long getNewestSoilDataCount();

}
