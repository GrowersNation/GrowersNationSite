package org.growersnation.site.application;

import org.growersnation.site.infrastructure.dto.thirdparty.bgs.soil.SoilData;

import javax.validation.Valid;

/**
 * <p>Service to provide the following to Resources:</p>
 * <ul>
 * <li>Business logic relating to {@link SoilData}s</li>
 * </ul>
 * <p/>
 * Note: This service does not provide read access to soil data. Please see {@link SoilDataReadService}.
 * <p/>
 * @param <K> is the key type for the DTO
 *
 * @since 0.0.1
 */
public interface SoilDataService<K> {

  /**
   * @param soilData The soil data to either create or update depending on ID matching
   * @return The optional ID (present if a create operation was performed)
   */
  K save(@Valid SoilData soilData);

}
