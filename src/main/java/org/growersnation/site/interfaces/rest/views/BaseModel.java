package org.growersnation.site.interfaces.rest.views;

import org.growersnation.site.infrastructure.dto.security.User;

/**
 * <p>Base class to provide the following to views:</p>
 * <ul>
 * <li>Access to common data (user, preferences etc)</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class BaseModel {

  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
