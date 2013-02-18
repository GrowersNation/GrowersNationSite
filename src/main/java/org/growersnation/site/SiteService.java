package org.growersnation.site;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;
import com.yammer.dropwizard.views.ViewMessageBodyWriter;
import org.eclipse.jetty.server.session.SessionHandler;
import org.growersnation.site.auth.openid.OpenIDAuthenticator;
import org.growersnation.site.auth.openid.OpenIDRestrictedToProvider;
import org.growersnation.site.health.SiteHealthCheck;
import org.growersnation.site.model.security.User;
import org.growersnation.site.resources.PublicHomeResource;

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
   * Main entry point to the application
   *
   * @param args CLI arguments
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    new SiteService().run(args);
  }

  @Override
  public void initialize(Bootstrap<SiteConfiguration> siteConfigurationBootstrap) {

    // Bundles
    siteConfigurationBootstrap.addBundle(new ViewBundle());
    siteConfigurationBootstrap.addBundle(new AssetsBundle("/assets/images", "/images"));
    siteConfigurationBootstrap.addBundle(new AssetsBundle("/assets/jquery", "/jquery"));

  }

  @Override
  public void run(SiteConfiguration siteConfiguration, Environment environment) throws Exception {
    // Configure authenticator
    OpenIDAuthenticator authenticator = new OpenIDAuthenticator();

    // Configure environment
    environment.scanPackagesForResourcesAndProviders(PublicHomeResource.class);

    // Health checks
    environment.addHealthCheck(new SiteHealthCheck());

    // Providers
    environment.addProvider(new ViewMessageBodyWriter());
    environment.addProvider(new OpenIDRestrictedToProvider<User>(authenticator, "OpenID"));

    // Session handler
    environment.setSessionHandler(new SessionHandler());

  }

}
