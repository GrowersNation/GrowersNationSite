package org.growersnation.site.service.mongo;

import com.google.inject.Inject;
import org.growersnation.site.model.soil.SoilData;
import org.growersnation.site.repository.SoilDataRepository;
import org.growersnation.site.service.SoilDataService;

/**
 * <p>Default implementation of {@link SoilDataService}</p>
 *
 * @since 0.0.1
 */
public class DefaultSoilDataService implements SoilDataService {

  private final SoilDataRepository soilDataRepository;

  @Inject
  public DefaultSoilDataService(SoilDataRepository soilDataRepository) {
    this.soilDataRepository = soilDataRepository;
  }

  @Override
  public void createSoilData(SoilData soilData) {
    soilDataRepository.create(soilData);
  }
}
