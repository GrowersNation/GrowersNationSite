package org.growersnation.site.domain;

import org.growersnation.site.domain.repositories.Persistable;

/**
 * <p>Abstract base entity to provide the following to entities:</p>
 * <ul>
 * <li>Access to common base methods</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public abstract class BaseEntity implements Persistable<String> {

  private String id;

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }
}
