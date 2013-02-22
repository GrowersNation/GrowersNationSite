package org.growersnation.site.model.view;

import org.growersnation.site.model.security.User;

/**
 * <p>Base class to provide the following to views:</p>
 * <ul>
 * <li>Access to common data (user, adverts etc)</li>
 *
 * @since 0.0.1
 *         
 */
public class AuthenticatedModel extends BaseModel {

  private final User user;

  public AuthenticatedModel(User user) {
    super();
    this.user = user;
  }

  /**
   * @return The currently authenticated user
   */
  public User getUser() {
    return user;
  }
}
