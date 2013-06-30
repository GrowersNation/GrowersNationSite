package org.growersnation.site.application.mongo;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.growersnation.site.application.UserReadService;
import org.growersnation.site.interfaces.rest.api.security.UserDto;
import org.mongojack.JacksonDBCollection;

import java.util.List;
import java.util.UUID;

/**
 * <p>MongoDB implementation of {@link UserReadService}:</p>
 *
 * @since 0.0.1
 */
public class MongoUserReadService implements UserReadService {

  JacksonDBCollection<UserDto, String> usersCollection;

  @Inject
  public MongoUserReadService(DB mongoDb) {
    usersCollection = JacksonDBCollection.wrap(
      mongoDb.getCollection("users"),
      UserDto.class,
      String.class);
  }

  @Override
  public List<UserDto> fetchUsers(int page, int pageSize) {
    DBObject orderBy = new BasicDBObject("_id", -1);
    int skip = page * pageSize;
    return usersCollection.find().sort(orderBy).skip(skip).limit(pageSize).toArray();
  }

  @Override
  public long getUserCount() {
    return usersCollection.count();
  }

  @Override
  public Optional<UserDto> getBySessionToken(UUID sessionToken) {

    DBObject query = new BasicDBObject("sessionToken", sessionToken);
    UserDto user = usersCollection.findOne(query);
    return Optional.fromNullable(user);
  }

  @Override
  public Optional<UserDto> getByOpenIDIdentifier(String openIDIdentifier) {
    DBObject query = new BasicDBObject("openIDIdentifier", openIDIdentifier);
    UserDto user = usersCollection.findOne(query);
    return Optional.fromNullable(user);
  }

  @Override
  public Optional<UserDto> getByEmailAddress(String emailAddress) {
    DBObject query = new BasicDBObject("emailAddress", emailAddress);
    UserDto user = usersCollection.findOne(query);
    return Optional.fromNullable(user);
  }
}
