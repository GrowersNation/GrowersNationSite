package org.growersnation.site.service;

import org.growersnation.site.model.security.User;

import javax.validation.Valid;

/**
 * <p>Service to provide the following to Resources:</p>
 * <ul>
 * <li>Business logic relating to {@link User}s</li>
 * </ul>
 *
 * Note: This service does not provide read access to soilData. Please see {@link UserReadService}.
 *
 * @since 0.0.1
 */
public interface UserService {

  /**
   * @param user The user to insert
   *
   * @return The ID of the inserted entity
   */
  String create(@Valid User user);

  /**
   * @param user The user to upsert
   *
   * @return The ID of the upserted entity
   */
  String upsert(@Valid User user);

  /**
   * <p>A hard delete occurs when the user is physically removed from the database and out of the audit checking</p>
   * <p>This is normally required for short lived activities.</p>
   *
   * @param user The user to delete
   */
  void hardDelete(@Valid User user);

}
