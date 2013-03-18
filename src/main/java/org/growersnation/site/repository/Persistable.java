package org.growersnation.site.repository;

/**
 * <p>Interface to provide the following to repositories:</p>
 * <ul>
 * <li>A single method common to all entities to ease persistence</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public interface Persistable {

  /**
   * @return The unique ID for this entity
   */
  String getId();
}
