package org.growersnation.site.views;

import com.yammer.dropwizard.views.View;
import org.growersnation.site.model.view.AuthenticatedModel;

/**
 * <p>View to provide the following to resources:</p>
 * <ul>
 * <li>Representation provided by a Freemarker template with a given model</li>
 * </ul>
 *
 * @since 0.0.1
 *
 */
public class PrivateFreemarkerView<T extends AuthenticatedModel> extends View {

  private final T model;

  public PrivateFreemarkerView(String templateName, T model) {
    super("/views/ftl/"+templateName);
    this.model = model;
  }

  public T getModel() {
    return model;
  }
}
