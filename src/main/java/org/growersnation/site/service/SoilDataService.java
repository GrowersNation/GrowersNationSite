package org.growersnation.site.service;

import org.growersnation.site.model.soil.SoilData;

import javax.validation.Valid;

/**
 * <p>Service to provide the following to Resources:</p>
 * <ul>
 * <li>Business logic relating to {@link SoilData}s</li>
 * </ul>
 *
 * Note: This service does not provide read access to soil data. Please see {@link SoilDataReadService}.
 *
 * @since 0.0.1
 */
public interface SoilDataService {

  /**
   * @param soilData The soil data to create
   */
  void createSoilData(@Valid SoilData soilData);
}
