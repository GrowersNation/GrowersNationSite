package org.growersnation.site.service;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.growersnation.site.application.SoilDataReadService;
import org.growersnation.site.application.mongo.DefaultSoilDataService;
import org.growersnation.site.application.mongo.MongoSoilDataReadService;
import org.growersnation.site.domain.repositories.SoilDataRepository;
import org.growersnation.site.infrastructure.persistence.mongo.MongoSoilDataRepository;
import org.growersnation.site.infrastructure.thirdparty.bgs.soil.SoilData;
import org.growersnation.site.interfaces.rest.api.soil.SoilDataFaker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.fest.assertions.api.Assertions.assertThat;

public class SoilDataServiceFunctionalTest {

  /**
   * Provides logging for this class
   */

  static {

    //System.setProperty("http.proxyHost", "exewpa");
    //System.setProperty("http.proxyPort", "8080");

  }

  private static final String DATABASE_NAME = "embedded";

  private MongodExecutable mongodExecutable;
  private MongodProcess mongodProcess;
  private DB db;

  @Before
  public void beforeEach() throws Exception {

    MongodStarter runtime = MongodStarter.getDefaultInstance();
    mongodExecutable = runtime.prepare(new MongodConfig(Version.V2_2_1, 12345, Network.localhostIsIPv6()));
    mongodProcess = mongodExecutable.start();
    Mongo mongo = new Mongo("localhost", 12345);
    db = mongo.getDB(DATABASE_NAME);

    // Create collections
    db.createCollection("soilData", new BasicDBObject());

  }

  @After
  public void afterEach() throws Exception {
    if (this.mongodProcess != null) {
      this.mongodProcess.stop();
      this.mongodExecutable.stop();
    }
  }

  @Test
  public void shouldCreateNewObjectInEmbeddedMongoDb() {

    // Given
    DBCollection col = db.createCollection("testCollection", new BasicDBObject());

    // When
    col.save(new BasicDBObject("testDoc", new Date()));

    // Then
    assertThat(col.getCount()).isEqualTo(1L);
  }

  @Test
  public void createSoilData_oneValidSoilData() {

    // Given
    SoilDataRepository soilDataRepository = new MongoSoilDataRepository(db);
    DefaultSoilDataService soilDataService = new DefaultSoilDataService(soilDataRepository);
    SoilDataReadService soilDataReadService = new MongoSoilDataReadService(db);

    SoilData soilData = SoilDataFaker.createValidSoilData();
    String objectId = soilDataService.save(soilData);

    // When
    long count = soilDataReadService.getNewestSoilDataCount();

    // Then
    assertThat(objectId).isNotNull();
    assertThat(count).isEqualTo(1);
  }

  @Test
  public void saveSoilData_oneValidSoilData() {

    // Given
    SoilDataRepository soilDataRepository = new MongoSoilDataRepository(db);
    DefaultSoilDataService soilDataService = new DefaultSoilDataService(soilDataRepository);
    SoilDataReadService soilDataReadService = new MongoSoilDataReadService(db);

    SoilData soilData = SoilDataFaker.createValidSoilData();

    // When
    String createdObjectId = soilDataService.save(soilData);
    String updatedObjectId = soilDataService.save(soilData);
    long count = soilDataReadService.getNewestSoilDataCount();

    // Then
    assertThat(createdObjectId).isNotNull();
    assertThat(updatedObjectId).isNotNull();
    assertThat(updatedObjectId).isEqualTo(createdObjectId);
    assertThat(count).isEqualTo(1);

  }

}
