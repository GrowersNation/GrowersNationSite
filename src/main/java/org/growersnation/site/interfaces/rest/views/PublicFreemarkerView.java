package org.growersnation.site.interfaces.rest.views;

import com.yammer.dropwizard.views.View;

/**
 * <p>View to provide the following to resources:</p>
 * <ul>
 * <li>Representation provided by a Freemarker template with a given model</li>
 * </ul>
 *
 * @since 0.0.1
 *
 */
public class PublicFreemarkerView<T extends BaseModel> extends View {

  private final T model;

  public PublicFreemarkerView(String templateName, T model) {
    super("/views/ftl/"+templateName);
    this.model = model;
  }

  public T getModel() {
    return model;
  }
}
