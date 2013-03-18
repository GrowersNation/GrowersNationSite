package org.growersnation.site.repository.mongo;


import com.google.common.collect.Lists;
import com.mongodb.DB;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import java.util.List;

/**
 * <p>Abstract base class to provide the following to MongoDB-based repositories:</p>
 * <ul>
 * <li>Provision of common methods</li>
 * </ul>
 *
 * @since 0.0.1
 */
public abstract class BaseMongoRepository<T> {

  protected final JacksonDBCollection<T, String> entitiesCollection;

  private final DB mongoDb;

  public BaseMongoRepository(DB mongoDb, JacksonDBCollection<T, String> entitiesCollection) {
    this.mongoDb = mongoDb;
    this.entitiesCollection = entitiesCollection;
  }

  public DB getMongoDb() {
    return mongoDb;
  }

  public String create(T entity) {
    WriteResult<T, String> writeResult = entitiesCollection.insert(entity);
    return writeResult.getSavedId();
  }

  public List<String> createAll(List<T> entities) {
    WriteResult<T, String> writeResult = entitiesCollection.insert(entities);
    return writeResult.getSavedIds();
  }

  public String upsert(T entity) {
    // TODO This needs checking
    WriteResult<T, String> writeResult = entitiesCollection.update(entity, entity, true, false);
    return null;
//    return writeResult.getSavedId();
  }

  public List<String> upsertAll(List<T> entities) {

    // TODO Need to use updateMulti() and construct a suitable query
    List<String> ids = Lists.newArrayList();

    for (T entity : entities) {
      ids.add(upsert(entity));
    }
    return ids;
  }

  public void hardDelete(T entity) {
    entitiesCollection.remove(entity);
  }

}
