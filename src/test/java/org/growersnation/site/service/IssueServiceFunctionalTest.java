package org.growersnation.site.service;


import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.tests.MongodForTestsFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.co.froot.coinapull.model.issue.SoilData;
import uk.co.froot.coinapull.model.issue.SoilDataFaker;
import uk.co.froot.coinapull.repository.mongo.MongoSoilDataRepository;
import uk.co.froot.coinapull.service.mongo.DefaultSoilDataService;
import uk.co.froot.coinapull.service.mongo.MongoSoilDataReadService;

import java.io.IOException;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

public class SoilDataServiceFunctionalTest {

  private static SoilDataService issueService;
  private static SoilDataReadService issueReadService;
  private static DB db;
  private static MongodForTestsFactory factory;

  @BeforeClass
  public static void setUp() throws IOException {
    factory = MongodForTestsFactory.with(Version.Main.V2_2);
    Mongo mongo = factory.newMongo();
    db = mongo.getDB("test");

    issueService = new DefaultSoilDataService(new MongoSoilDataRepository(db));
    issueReadService = new MongoSoilDataReadService(db);
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

  @Test
  public void readNewestSoilDatas_oneValidSoilData() {
    // setup
    long expectedSoilDataCount = 1;
    SoilData issue = SoilDataFaker.createValidSoilData();
    issueService.createSoilData(issue);

    // execute
    long issueCount = issueReadService.getNewestSoilDataCount();

    // assert
    assertThat(issueCount).isEqualTo(expectedSoilDataCount);
  }

  @Test
  public void readNewestSoilDatas_oneFeaturedSoilData() {
    // setup
    long expectedSoilDataCount = 1;
    SoilData issue = SoilDataFaker.createValidFeaturedSoilData();
    issueService.createSoilData(issue);

    // execute
    long issueCount = issueReadService.getNewestSoilDataCount();

    // assert
    assertThat(issueCount).isEqualTo(expectedSoilDataCount);
  }

  @Test
  public void readNewestSoilDatas_zeroSoilDatas() {
    // setup
    long expectedSoilDataCount = 0;

    // execute
    long issueCount = issueReadService.getNewestSoilDataCount();

    // assert
    assertThat(issueCount).isEqualTo(expectedSoilDataCount);
  }

  @Test
  public void readFeaturedSoilDatas_zeroSoilDatas() {
    // setup
    long expectedSoilDataCount = 0;

    // execute
    long issueCount = issueReadService.getFeaturedSoilDataCount();

    // assert
    assertThat(issueCount).isEqualTo(expectedSoilDataCount);
  }

  @Test
  public void readFeaturedSoilDatas_twoFeaturedSoilData_oneNonFeaturedSoilData() {
    // setup
    long expectedSoilDataCount = 2;
    issueService.createSoilData(SoilDataFaker.createValidFeaturedSoilData());
    issueService.createSoilData(SoilDataFaker.createValidFeaturedSoilData());
    issueService.createSoilData(SoilDataFaker.createValidSoilData());

    // execute
    long issueCount = issueReadService.getFeaturedSoilDataCount();

    // assert
    assertThat(issueCount).isEqualTo(expectedSoilDataCount);
  }

  @Test
  public void readNewestSoilDatas_twoFeaturedSoilData_oneNonFeaturedSoilData() {
    // setup
    long expectedSoilDataCount = 3;
    issueService.createSoilData(SoilDataFaker.createValidFeaturedSoilData());
    issueService.createSoilData(SoilDataFaker.createValidFeaturedSoilData());
    issueService.createSoilData(SoilDataFaker.createValidSoilData());

    // execute
    long issueCount = issueReadService.getNewestSoilDataCount();

    // assert
    assertThat(issueCount).isEqualTo(expectedSoilDataCount);
  }

}
