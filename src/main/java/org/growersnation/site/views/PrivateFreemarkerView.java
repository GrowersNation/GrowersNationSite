package org.growersnation.site.views;

import com.google.common.base.Preconditions;
import org.growersnation.site.model.view.BaseModel;

/**
 * <p>View to provide the following to resources:</p>
 * <ul>
 * <li>Representation provided by a Freemarker template with a given model</li>
 * <li>Additional checks to ensure that an authenticated user is in place</li>
 * </ul>
 *
 * @since 0.0.1
 *
 */
public class PrivateFreemarkerView<T extends BaseModel> extends PublicFreemarkerView<T> {

  public PrivateFreemarkerView(String templateName, T model) {
    super(templateName, model);
    Preconditions.checkNotNull(templateName);
    Preconditions.checkNotNull(model);
    Preconditions.checkNotNull(model.getUser());
  }

}
