package org.growersnation.site.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

/**
 * <p>Interface to provide the following to repositories:</p>
 * <ul>
 * <li>A single method common to all entities to ease persistence</li>
 * </ul>
 *
 * @param <K> is the key type for the DTO
 * @since 0.0.1
 *         
 */
public interface Persistable<K> {

  /**
   * @return The unique ID for this entity
   */
  @ObjectId
  @JsonProperty("_id")
  K getId();

  /**
   * @param id The unique ID for this entity
   */
  @ObjectId
  @JsonProperty("_id")
  void setId(K id);
}
