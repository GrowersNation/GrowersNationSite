package org.growersnation.site.service.mongo;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.mongojack.JacksonDBCollection;
import uk.co.froot.coinapull.model.security.User;
import uk.co.froot.coinapull.service.UserReadService;

import java.util.List;
import java.util.UUID;

/**
 * <p>MongoDB implementation of {@link UserReadService}:</p>
 *
 * @since 0.0.1
 */
public class MongoUserReadService implements UserReadService {

  JacksonDBCollection<User, String> usersCollection;

  @Inject
  public MongoUserReadService(DB mongoDb) {
    usersCollection = JacksonDBCollection.wrap(
      mongoDb.getCollection("users"),
      User.class,
      String.class);
  }

  @Override
  public List<User> fetchUsers(int page, int pageSize) {
    DBObject orderBy = new BasicDBObject("_id", -1);
    int skip = page * pageSize;
    return usersCollection.find().sort(orderBy).skip(skip).limit(pageSize).toArray();
  }

  @Override
  public long getUserCount() {
    return usersCollection.count();
  }

  @Override
  public Optional<User> getBySessionToken(UUID sessionToken) {

    DBObject query = new BasicDBObject("sessionToken", sessionToken);
    User user = usersCollection.findOne(query);
    return Optional.fromNullable(user);
  }

  @Override
  public Optional<User> getByOpenIDIdentifier(String openIDIdentifier) {
    DBObject query = new BasicDBObject("openIDIdentifier", openIDIdentifier);
    User user = usersCollection.findOne(query);
    return Optional.fromNullable(user);
  }

  @Override
  public Optional<User> getByEmailAddress(String emailAddress) {
    DBObject query = new BasicDBObject("emailAddress", emailAddress);
    User user = usersCollection.findOne(query);
    return Optional.fromNullable(user);
  }
}
