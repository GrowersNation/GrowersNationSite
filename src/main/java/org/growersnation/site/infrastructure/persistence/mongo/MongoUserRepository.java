package org.growersnation.site.infrastructure.persistence.mongo;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.mongodb.DB;
import org.growersnation.site.domain.repositories.UserRepository;
import org.growersnation.site.infrastructure.dto.security.User;
import org.growersnation.site.interfaces.rest.api.PaginationData;
import org.mongojack.JacksonDBCollection;

import java.util.List;
import java.util.UUID;

/**
 * <p>Repository to provide MongoDB-based persistence to {@link UserRepository}:</p>
 *
 * @since 0.0.1
 *
 */
public class MongoUserRepository extends BaseMongoRepository<User, String> implements UserRepository {

  @Inject
  public MongoUserRepository(DB mongoDb) {
    super(mongoDb,
      JacksonDBCollection.wrap(
        mongoDb.getCollection("users"),
        User.class,
        String.class));
  }

  @Override
  public Optional<User> getById(String id) {

    return findOne("id", id);
  }

  @Override
  public Optional<User> getByApiKey(String apiKey) {
    return findOne("apiKey", apiKey);
  }

  @Override
  public Optional<User> getByEmailAddress(String emailAddress) {
    return findOne("emailAddress", emailAddress);
  }

  @Override
  public Optional<User> getByOpenIDIdentifier(String openIDIdentifier) {
    return findOne("openIDIdentifier", openIDIdentifier);
  }

  @Override
  public Optional<User> getBySessionToken(UUID sessionToken) {
    return findOne("sessionToken", sessionToken);
  }

  @Override
  public Optional<User> getByCredentials(String username, String passwordDigest) {
    return findOne("userName", username, "passwordDigest", passwordDigest);
  }

  @Override
  public List<User> getAllByPage(PaginationData paginationData) {
    return null;
  }

  @Override
  public String saveOrUpdate(User user) {
    return save(user);
  }

  @Override
  public void flush() {
    // Do nothing
  }

  @Override
  public void delete(User user) {
    // Do nothing
  }

  @Override
  public void hardDelete(User entity) {

    Preconditions.checkNotNull(entity);

    entitiesCollection.remove(entity);

  }


}
