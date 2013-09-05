package org.growersnation.site.application.mongo;

import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.growersnation.site.application.SoilDataReadService;
import org.growersnation.site.infrastructure.dto.thirdparty.bgs.soil.SoilData;
import org.mongojack.JacksonDBCollection;

import java.util.List;

/**
 * <p>MongoDB implementation of {@link SoilDataReadService}:</p>
 *
 * @since 0.0.1
 */
public class MongoSoilDataReadService implements SoilDataReadService {

  JacksonDBCollection<SoilData, String> soilDataCollection;

  @Inject
  public MongoSoilDataReadService(DB mongoDb) {
    soilDataCollection = JacksonDBCollection.wrap(
      mongoDb.getCollection("soilData"),
      SoilData.class,
      String.class);
  }

  @Override
  public List<SoilData> fetchNewestSoilData(int page, int pageSize) {
    DBObject orderBy = new BasicDBObject("_id", -1);
    int skip = page * pageSize;
    return soilDataCollection.find().sort(orderBy).skip(skip).limit(pageSize).toArray();
  }

  @Override
  public long getNewestSoilDataCount() {
    return soilDataCollection.find().size();
  }

}
