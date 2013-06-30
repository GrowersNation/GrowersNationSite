package org.growersnation.site.interfaces.rest.api.security;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* <p>UserFaker provides test implementations of {@link UserDto} for use in test cases</p>
*
* See the <a href="https://github.com/DiUS/java-faker">GitHub Java Faker project</a> more details
*/
public class UserFaker {

  private static Faker faker = new Faker();

  /**
* @return A bare bones user with just an ID and the session token
*/
  public static UserDto createSessionUser() {
    return new UserDto(UUID.randomUUID());
  }

  /**
* @param userNamePrefix The user name with a number
* @param count The number to generate
*
* @return The list of Users populated with email address, first and last names
*/
  public static List<UserDto> createOpenIDUsers(String userNamePrefix, int count) {
    // Pre-allocate for efficiency at larger scale
    List<UserDto> list = new ArrayList<UserDto>(count);
    for (int i = 0; i < count; i++) {
      UserDto user = UserFaker.createSessionUser();
      user.setUserName(userNamePrefix + " " + i);
      user.setFirstName(faker.firstName());
      user.setLastName(faker.lastName());
      user.setEmailAddress(user.getFirstName()+"@example.org");
      list.add(user);
    }
    return list;
  }

}
