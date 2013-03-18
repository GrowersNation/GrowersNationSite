package org.growersnation.site.service;


import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.tests.MongodForTestsFactory;
import org.junit.*;
import uk.co.froot.coinapull.model.security.User;
import uk.co.froot.coinapull.model.security.UserFaker;
import uk.co.froot.coinapull.repository.mongo.MongoUserRepository;
import uk.co.froot.coinapull.service.mongo.DefaultUserService;
import uk.co.froot.coinapull.service.mongo.MongoUserReadService;

import java.io.IOException;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserServiceFunctionalTest {

  private static UserService userService;
  private static UserReadService userReadService;
  private static DB db;
  private static MongodForTestsFactory factory;

  @BeforeClass
  public static void setUp() throws IOException {
    factory = MongodForTestsFactory.with(Version.Main.V2_2);
    Mongo mongo = factory.newMongo();
    db = mongo.getDB("test");

    userService = new DefaultUserService(new MongoUserRepository(db));
    userReadService = new MongoUserReadService(db);
  }

  @AfterClass
  public static void tearDown() {
    if (factory != null) {
      factory.shutdown();
    }
  }

  @Before
  public void before() {
    dropAllCollections(db);
  }

  private void dropAllCollections(DB db) {
    Set<String> collectionNames = db.getCollectionNames();
    for (String name : collectionNames) {
      DBCollection collection = db.getCollection(name);
      try {
        collection.drop();
      } catch (RuntimeException e) {
        // ignore
      }
    }
  }

  // TODO Determine why this test is failing (probably an @Valid issue)
  @Ignore
  public void readUsers_oneValidUser() {
    // Setup
    long expectedUserCount = 1;
    User user = UserFaker.createSessionUser();
    userService.create(user);

    // Execute
    long userCount = userReadService.getUserCount();

    // Assert
    assertThat(userCount).isEqualTo(expectedUserCount);
  }

  @Test
  public void readUsers_zeroUsers() {
    // setup
    long expectedUserCount = 0;

    // execute
    long userCount = userReadService.getUserCount();

    // assert
    assertThat(userCount).isEqualTo(expectedUserCount);
  }

}
