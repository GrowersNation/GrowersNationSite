package org.growersnation.site;

import com.fiestacabin.dropwizard.guice.AutoConfigService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;
import org.eclipse.jetty.server.session.SessionHandler;
import org.growersnation.site.guice.SiteServiceModule;

/**
 * <p>Service to provide the following to application:</p>
 * <ul>
 * <li>Provision of access to resources</li>
 * </ul>
 * <p>Use <code>java -jar site-develop-SNAPSHOT.jar server gn.yml</code> to start the site</p>
 *
 * @since 0.0.1
 *         
 */
public class SiteService extends AutoConfigService<SiteConfiguration> {

  /**
   * Main entry point to the application
   *
   * @param args CLI arguments
   *
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    new SiteService().run(args);
  }

  @Override
  public void initialize(Bootstrap<SiteConfiguration> bootstrap) {

    // Bundles
    bootstrap.addBundle(new ViewBundle());
    bootstrap.addBundle(new AssetsBundle("/assets/images", "/images"));
    bootstrap.addBundle(new AssetsBundle("/assets/jquery", "/jquery"));

  }

  @Override
  protected Injector createInjector(SiteConfiguration configuration) {
    return Guice.createInjector(new SiteServiceModule());
  }

  @Override
  protected void runWithInjector(SiteConfiguration configuration,
                                 Environment environment,
                                 Injector injector) throws Exception {
    // Auto-config will locate
    // * Health Checks
    // * Providers
    // * Injectable Providers
    // * Resources
    // * Tasks
    // * Managed
    super.runWithInjector(configuration,environment,injector);

    // Add application specific configuration

    // Configure authenticator
    //OpenIDAuthenticator authenticator = new OpenIDAuthenticator();

    // Providers
    //environment.addProvider(new ViewMessageBodyWriter());
    //environment.addProvider(new OpenIDRestrictedToProvider<User>(authenticator, "OpenID"));

    // Session handler
    environment.setSessionHandler(new SessionHandler());
  }

}
