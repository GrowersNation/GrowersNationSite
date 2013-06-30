package org.growersnation.site.infrastructure.guice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import org.growersnation.site.SiteConfiguration;
import org.growersnation.site.application.SoilDataReadService;
import org.growersnation.site.application.SoilDataService;
import org.growersnation.site.application.UserReadService;
import org.growersnation.site.application.UserService;
import org.growersnation.site.application.mongo.DefaultSoilDataService;
import org.growersnation.site.application.mongo.DefaultUserService;
import org.growersnation.site.application.mongo.MongoSoilDataReadService;
import org.growersnation.site.application.mongo.MongoUserReadService;
import org.growersnation.site.domain.repositories.SoilDataRepository;
import org.growersnation.site.domain.repositories.UserRepository;
import org.growersnation.site.infrastructure.persistence.dao.security.UserDao;
import org.growersnation.site.infrastructure.persistence.dao.security.mem.InMemoryUserDao;
import org.growersnation.site.infrastructure.persistence.mongo.MongoSoilDataRepository;
import org.growersnation.site.infrastructure.persistence.mongo.MongoUserRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * <p>Guice module to provide the following to application:</p>
 * <ul>
 * <li>Bindings between injected interfaces and their implementations</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class SiteServiceModule extends AbstractModule {

  private final SiteConfiguration configuration;

  public SiteServiceModule(String[] args) {

    Preconditions.checkNotNull(args);
    Preconditions.checkElementIndex(1,args.length);

    // Read the YAML configuration
    ObjectMapper om = new ObjectMapper(new YAMLFactory());
    FileInputStream fis;
    try {
      fis = new FileInputStream(args[1]);
      // Stream will be closed on completion
      this.configuration = om.readValue(fis, SiteConfiguration.class);
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot read external configuration from '"+args[1]+"'",e);
    }
  }

  @Override
  protected void configure() {

    // User support
    bind(UserDao.class).to(InMemoryUserDao.class).asEagerSingleton();
    bind(UserReadService.class).to(MongoUserReadService.class).asEagerSingleton();
    bind(UserService.class).to(DefaultUserService.class).asEagerSingleton();
    bind(UserRepository.class).to(MongoUserRepository.class).asEagerSingleton();

    // SoilData support
    bind(SoilDataReadService.class).to(MongoSoilDataReadService.class).asEagerSingleton();
    bind(SoilDataService.class).to(DefaultSoilDataService.class).asEagerSingleton();
    bind(SoilDataRepository.class).to(MongoSoilDataRepository.class).asEagerSingleton();

  }

  @Provides
  @Singleton
  public Mongo getMongo() {

    return getDB().getMongo();

  }


  @Provides
  @Singleton
  public DB getDB() {

    // MongoDB Setup
    final MongoURI mongoUri = new MongoURI(configuration.getMongoUri());
    final DB db;
    try {
      db = mongoUri.connectDB();
    } catch (UnknownHostException e) {
      throw new IllegalArgumentException("Cannot connect to MongoDB",e);
    }

    // Authenticate
    if (mongoUri.getUsername() != null && mongoUri.getPassword() != null) {
      db.authenticate(mongoUri.getUsername(), mongoUri.getPassword());
    }

    return db;

  }
}
