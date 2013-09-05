package org.growersnation.site.infrastructure.persistence.mem;


import org.growersnation.site.domain.repositories.SoilDataRepository;
import org.growersnation.site.infrastructure.dto.thirdparty.bgs.soil.SoilData;

public class InMemorySoilDataRepository extends InMemoryEntityRepository<SoilData> implements SoilDataRepository {

}
