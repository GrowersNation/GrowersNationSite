package org.growersnation.site.infrastructure.dto.thirdparty.bgs.soil;

import java.util.List;

/**
 * <p>Interface to provide the following to implementers:</p>
 * <ul>
 * <li>Common method</li>
 * </ul>
 * <p>This is required to overcome the issue of prematurely closing an input stream
 * in the DAOs.</p>
 *
 * @since 0.0.1
 *         
 */
public interface FieldAccessor<T> {

  List<T> getFields();

}
