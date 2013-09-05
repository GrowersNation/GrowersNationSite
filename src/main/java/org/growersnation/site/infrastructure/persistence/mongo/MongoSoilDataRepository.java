package org.growersnation.site.infrastructure.persistence.mongo;

import com.google.inject.Inject;
import com.mongodb.DB;
import org.growersnation.site.domain.repositories.SoilDataRepository;
import org.growersnation.site.infrastructure.dto.thirdparty.bgs.soil.SoilData;
import org.mongojack.JacksonDBCollection;

/**
 * <p>Repository to provide MongoDB-based persistence to {@link SoilDataRepository}:</p>
 *
 * @since 0.0.1
 */
public class MongoSoilDataRepository extends BaseMongoRepository<SoilData, String> implements SoilDataRepository {

  @Inject
  public MongoSoilDataRepository(DB mongoDb) {
    super(mongoDb,
      JacksonDBCollection.wrap(
        mongoDb.getCollection("soilData"),
        SoilData.class,
        String.class));
  }

}
