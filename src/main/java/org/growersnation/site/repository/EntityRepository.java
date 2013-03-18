package org.growersnation.site.repository;

import java.util.List;

/**
 * <p>Repository to provide storage and retrieval of Entity aggregates to the application</p>
 */
public interface EntityRepository<T> {

  /**
   * Creates an Entity assuming that none previously exists
   *
   * @param entity The entity
   *
   * @return The ID of the inserted entity
   */
  String create(T entity);

  /**
   * Creates a list of entities as a batch assuming that none previously exist
   *
   * @param entities The entities
   *
   * @return The IDs of the inserted entities (same order as the original list)
   */
  List<String> createAll(List<T> entities);

  /**
   * Creates or updates the entity (slower)
   *
   * @param entity The entity
   *
   * @return The ID of the upserted entity
   */
  String upsert(T entity);

  /**
   * Creates or updates a list of entities as a batch (slower)
   *
   * @param entities The entities
   *
   * @return The IDs of the inserted entities (same order as the original list)
   */
  List<String> upsertAll(List<T> entities);

  /**
   * <p></p>Deletes the given entity from the collection. This is a hard delete in that
   * the entity is removed, not merely marked as deleted for audit purposes.</p>
   * <p>The use case for the hard delete is normally to remove a temporary entity
   * created for the purposes of a short lived activity.</p>
   *
   * @param entity The entity to delete
   */
  void hardDelete(T entity);
}
