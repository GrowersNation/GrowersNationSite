package org.growersnation.site.infrastructure.persistence.dao.security.mem;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.growersnation.site.infrastructure.persistence.dao.security.UserDao;
import org.growersnation.site.interfaces.rest.api.PaginationData;
import org.growersnation.site.interfaces.rest.api.security.UserDto;

import java.util.List;
import java.util.UUID;

/**
 * <p>DAO to provide the following to {@link org.growersnation.site.interfaces.rest.api.security.UserDto}:</p>
 * <ul>
 * <li>A small scale implementation of a simple User repository</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class InMemoryUserDao implements UserDao {

  private final List<UserDto> users = Lists.newArrayList();

  @Override
  public Optional<UserDto> getById(Long id) {
    synchronized (users) {
      Preconditions.checkNotNull(id);
      for (UserDto user : users) {
        if (id.equals(user.getId())) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<UserDto> getByApiKey(String apiKey) {
    synchronized (users) {
      Preconditions.checkNotNull(apiKey);
      for (UserDto user : users) {
        if (apiKey.equals(user.getApiKey())) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<UserDto> getByEmailAddress(String emailAddress) {
    synchronized (users) {
      Preconditions.checkNotNull(emailAddress);
      for (UserDto user : users) {
        if (emailAddress.equals(user.getEmailAddress())) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<UserDto> getByOpenIDIdentifier(String openIDIdentifier) {
    synchronized (users) {
      Preconditions.checkNotNull(openIDIdentifier);
      for (UserDto user : users) {
        if (openIDIdentifier.equals(user.getOpenIDIdentifier())) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<UserDto> getBySessionToken(UUID sessionToken) {
    synchronized (users) {
      Preconditions.checkNotNull(sessionToken);
      for (UserDto user : users) {
        if (sessionToken.equals(user.getSessionToken())) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<UserDto> getByCredentials(String username, String passwordDigest) {
    synchronized (users) {
      Preconditions.checkNotNull(passwordDigest);
      for (UserDto user : users) {
        if (username.equals(user.getUserName()) &&
          passwordDigest.equals(user.getPasswordDigest())
          ) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public List<UserDto> getAllByPage(PaginationData paginationData) {
    return users;
  }

  @Override
  public UserDto saveOrUpdate(UserDto user) {

    synchronized (users) {
      Preconditions.checkNotNull(user);
      int index = users.indexOf(user);
      if (index == -1) {
        user.setId(ObjectId.get().toString());
        users.add(user);

      } else {
        users.remove(index);
        users.add(user);
      }

      return user;
    }
  }

  @Override
  public void flush() {
    // Do nothing
  }

  @Override
  public void delete(UserDto user) {
    users.remove(user);
  }
}
