package org.growersnation.site.interfaces.rest.views;

import org.growersnation.site.interfaces.rest.api.security.UserDto;

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

  private UserDto user;

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }
}
