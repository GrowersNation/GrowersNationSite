package org.growersnation.site.application.mongo;

import com.google.inject.Inject;
import org.growersnation.site.application.SoilDataService;
import org.growersnation.site.domain.repositories.SoilDataRepository;
import org.growersnation.site.infrastructure.dto.thirdparty.bgs.soil.SoilData;

/**
 * <p>Default implementation of {@link SoilDataService}</p>
 *
 * @since 0.0.1
 */
public class DefaultSoilDataService implements SoilDataService<String> {

  private final SoilDataRepository soilDataRepository;

  @Inject
  public DefaultSoilDataService(SoilDataRepository soilDataRepository) {
    this.soilDataRepository = soilDataRepository;
  }

  @Override
  public String save(SoilData soilData) {
    return soilDataRepository.save(soilData);
  }

}
