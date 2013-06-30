package org.growersnation.site.infrastructure.persistence.mem;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.growersnation.site.domain.repositories.UserRepository;
import org.growersnation.site.domain.security.User;
import org.growersnation.site.interfaces.rest.api.PaginationData;

import java.util.List;
import java.util.UUID;

public class InMemoryUserRepository extends InMemoryEntityRepository<User> implements UserRepository {

  private final List<User> users = Lists.newArrayList();

  @Override
  public Optional<User> getById(String id) {
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
  public String saveOrUpdate(User user) {

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

      return user.getId();
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
