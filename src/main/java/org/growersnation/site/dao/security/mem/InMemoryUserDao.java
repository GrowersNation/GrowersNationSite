package org.growersnation.site.dao.security.mem;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.growersnation.site.dao.security.UserDao;
import org.growersnation.site.model.PaginationData;
import org.growersnation.site.model.security.User;

import java.util.List;
import java.util.UUID;

/**
 * <p>DAO to provide the following to {@link User}:</p>
 * <ul>
 * <li>A small scale implementation of a simple User repository</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class InMemoryUserDao implements UserDao {

  private final List<User> users = Lists.newArrayList();

  @Override
  public Optional<User> getById(Long id) {
    synchronized (users) {
      for (User user : users) {
        if (user.getId().equals(id)) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<User> getByApiKey(String apiKey) {
    synchronized (users) {
      for (User user : users) {
        if (user.getApiKey().equals(apiKey)) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<User> getByEmailAddress(String emailAddress) {
    synchronized (users) {
      for (User user : users) {
        if (user.getEmailAddress().equals(emailAddress)) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<User> getByOpenIDIdentifier(String openIDIdentifier) {
    synchronized (users) {
      for (User user : users) {
        if (user.getOpenIDIdentifier().equals(openIDIdentifier)) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<User> getBySessionToken(UUID sessionToken) {
    synchronized (users) {
      for (User user : users) {
        if (user.getSessionToken().equals(sessionToken)) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<User> getByCredentials(String username, String passwordDigest) {
    synchronized (users) {
      for (User user : users) {
        if (user.getUserName().equals(username) &&
          user.getPasswordDigest().equals(passwordDigest)
          ) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public List<User> getAllByPage(PaginationData paginationData) {
    return users;
  }

  @Override
  public User saveOrUpdate(User user) {

    synchronized (users) {
      int index = users.indexOf(user);
      if (index == -1) {
        user.setId(users.size() + 1L);
        users.add(user);

      } else {
        user.setId(users.size() + 1L);
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
  public void delete(User user) {
    users.remove(user);
  }
}
