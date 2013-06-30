package org.growersnation.site.infrastructure.thirdparty.metoffice.gdd;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * <p>Value object to provide the following to application:</p>
 * <ul>
 * <li>Provision of monthly growing degree day data for a given location over 30 years</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class GrowingDegreeDays30Year {


  private String name;
  private String description;

  private String valueUnit="GDD";

  private List<GrowingDegreeDays30YearLocation> locations = Lists.newArrayList();



}
