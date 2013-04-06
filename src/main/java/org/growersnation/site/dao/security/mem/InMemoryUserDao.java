package org.growersnation.site.dao.security.mem;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
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
      Preconditions.checkNotNull(id);
      for (User user : users) {
        if (id.equals(user.getId())) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<User> getByApiKey(String apiKey) {
    synchronized (users) {
      Preconditions.checkNotNull(apiKey);
      for (User user : users) {
        if (apiKey.equals(user.getApiKey())) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<User> getByEmailAddress(String emailAddress) {
    synchronized (users) {
      Preconditions.checkNotNull(emailAddress);
      for (User user : users) {
        if (emailAddress.equals(user.getEmailAddress())) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<User> getByOpenIDIdentifier(String openIDIdentifier) {
    synchronized (users) {
      Preconditions.checkNotNull(openIDIdentifier);
      for (User user : users) {
        if (openIDIdentifier.equals(user.getOpenIDIdentifier())) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<User> getBySessionToken(UUID sessionToken) {
    synchronized (users) {
      Preconditions.checkNotNull(sessionToken);
      for (User user : users) {
        if (sessionToken.equals(user.getSessionToken())) {
          return Optional.of(user);
        }
      }
      return Optional.absent();
    }
  }

  @Override
  public Optional<User> getByCredentials(String username, String passwordDigest) {
    synchronized (users) {
      Preconditions.checkNotNull(passwordDigest);
      for (User user : users) {
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
  public List<User> getAllByPage(PaginationData paginationData) {
    return users;
  }

  @Override
  public User saveOrUpdate(User user) {

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
  public void delete(User user) {
    users.remove(user);
  }
}
