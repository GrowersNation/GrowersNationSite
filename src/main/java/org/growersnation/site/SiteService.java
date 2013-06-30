package org.growersnation.site;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.yammer.dropwizard.ConfiguredBundle;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;
import org.growersnation.site.infrastructure.guice.SiteServiceModule;

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
public class SiteService extends Service<SiteConfiguration> {

  /**
   * The command line arguments to allow DB configuration to take place by Guice
   */
  private String[] args;

  /**
   * Main entry point to the application
   *
   * @param args CLI arguments
   *
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    new SiteService(args).run(args);
  }

  /**
   * @param args The command line arguments to allow DB configuration to take place by Guice
   */
  private SiteService(String[] args) {
    this.args = args;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void initialize(Bootstrap<SiteConfiguration> bootstrap) {

    // Configure Guice first
    // TODO The intermediate call to initialize() can be removed after DW 0.6.2+
    // This will fix the unchecked warning
    ConfiguredBundle guiceBundle = GuiceBundle
      .newBuilder()
      .addModule(new SiteServiceModule(args)) // The main Guice module with bindings
      .enableAutoConfig(getClass().getPackage().getName()) // Scan application classes
      .build();
    guiceBundle.initialize(bootstrap);
    bootstrap.addBundle(guiceBundle);

    // Add asset bundles
    bootstrap.addBundle(new AssetsBundle("/assets/images", "/images"));
    bootstrap.addBundle(new AssetsBundle("/assets/jquery", "/jquery"));

    // Add view bundle
    bootstrap.addBundle(new ViewBundle());

  }

  @Override
  public void run(SiteConfiguration configuration, Environment environment) throws Exception {

    // Add any extra configuration here
    //environment.addFilter(new GuiceFilter(),"/*");

  }

}
