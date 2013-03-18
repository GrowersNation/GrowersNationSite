package org.growersnation.site.dao.soil;

import com.github.javafaker.Faker;
import org.bson.types.ObjectId;
import org.growersnation.site.model.soil.SoilData;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>SoilDataFaker provides test implementations of {@link SoilData} for use in test cases</p>
 *
 * See the <a href="https://github.com/DiUS/java-faker">GitHub Java Faker project</a> more details
 */
public class SoilDataFaker {

  private static Faker faker = new Faker();

  public static SoilData createValidSoilData() {
    return createSoilData(createValidTitle());
  }

  public static SoilData createSoilData(String titlePrefix) {
    return new SoilData(ObjectId.get().toString(), titlePrefix + faker.sentence(5), faker.paragraph(5), false);
  }

  public static List<SoilData> createSoilDatas(String titlePrefix, int count) {
    // Pre-allocate for efficiency at larger scale
    List<SoilData> list = new ArrayList<SoilData>(count);
    for (int i = 0; i < count; i++) {
      list.add(SoilDataFaker.createSoilData(titlePrefix + i));
    }
    return list;
  }

  public static SoilData createValidFeaturedSoilData() {
    return new SoilData(createValidId(), createValidTitle(), createValidDescription(), true);
  }

  public static SoilData createSoilDataWithId(String id) {
    return new SoilData(id, createValidTitle(), createValidDescription(), createValidFeaturedFlag());
  }

  public static SoilData createSoilDataWithTitle(String title) {
    return new SoilData(createValidId(), title, createValidDescription(), createValidFeaturedFlag());
  }

  public static String createValidId() {
    return new ObjectId().toString();
  }

  public static boolean createValidFeaturedFlag() {
    return false;
  }

  public static String createValidDescription() {
    return "example description";
  }

  public static String createValidTitle() {
    return "example title";
  }
}
