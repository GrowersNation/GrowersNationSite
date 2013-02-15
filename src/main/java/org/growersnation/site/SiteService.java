package org.growersnation.site;

import com.google.common.cache.CacheBuilderSpec;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.bundles.AssetsBundle;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;
import com.yammer.dropwizard.views.ViewMessageBodyWriter;
import org.eclipse.jetty.server.session.SessionHandler;
import org.growersnation.site.auth.openid.OpenIDAuthenticator;
import org.growersnation.site.auth.openid.OpenIDRestrictedToProvider;
import org.growersnation.site.health.SiteHealthCheck;
import org.growersnation.site.model.User;
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

  private SiteService() {
    super("store");
    CacheBuilderSpec cacheBuilderSpec = (System.getenv("FILE_CACHE_ENABLED") == null) ? CacheBuilderSpec.parse("maximumSize=0") : AssetsBundle.DEFAULT_CACHE_SPEC;
    addBundle(new AssetsBundle("/assets/images", cacheBuilderSpec, "/images"));
    addBundle(new AssetsBundle("/assets/jquery", cacheBuilderSpec, "/jquery"));

  }

  @Override
  protected void initialize(SiteConfiguration configuration,
                            Environment environment) {

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

    // Bundles
    addBundle(new ViewBundle());

  }

}
