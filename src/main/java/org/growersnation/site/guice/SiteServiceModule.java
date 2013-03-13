package org.growersnation.site.guice;

import com.google.inject.AbstractModule;
import org.growersnation.site.dao.security.UserDao;
import org.growersnation.site.dao.security.mem.InMemoryUserDao;

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

  @Override
  protected void configure() {

    // The in-memory UserDao is best treated as a singleton
    bind(UserDao.class).to(InMemoryUserDao.class).asEagerSingleton();

  }

}
