package org.growersnation.site.interfaces.rest.api.sensor;

import com.github.javafaker.Faker;
import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.growersnation.site.infrastructure.thirdparty.bgs.soil.SoilData;
import org.growersnation.site.infrastructure.thirdparty.bgs.soil.carbon.TopsoilCarbonFields;
import org.growersnation.site.infrastructure.thirdparty.bgs.soil.nutrients.TopsoilNutrientsFields;
import org.growersnation.site.infrastructure.thirdparty.bgs.soil.ph.PHBulkDensityFields;
import org.growersnation.site.infrastructure.thirdparty.bgs.soil.texture.SoilTextureFields;

import java.util.List;

import static com.google.common.collect.Lists.newArrayListWithCapacity;

/**
 * <p>SoilDataFaker provides test implementations of {@link SoilData} for use in test cases</p>
 * <p/>
 * See the <a href="https://github.com/DiUS/java-faker">GitHub Java Faker project</a> more details
 */
public class SensorDataFaker {

  private static Faker faker = new Faker();

  public static SoilData createValidSoilData() {
    return new SoilData();
  }

  public static List<SoilData> createSoilDatas(String titlePrefix, int count) {
    // Pre-allocate for efficiency at larger scale
    List<SoilData> list = newArrayListWithCapacity(count);
    for (int i = 0; i < count; i++) {
      list.add(SensorDataFaker.createValidSoilData());
    }
    return list;
  }

  public static String createValidId() {
    return new ObjectId().toString();
  }

  public static List<TopsoilCarbonFields> createTopsoilCarbonFields() {
    List<TopsoilCarbonFields> topsoilCarbonFieldsList = Lists.newArrayList();

    for (int i = 0; i < 2; i++) {

      TopsoilCarbonFields topsoilCarbonFields = new TopsoilCarbonFields();

      topsoilCarbonFields.setDominantBroadHabitat("Arable");
      topsoilCarbonFields.setDominantBroadHabitat2000("Arable");
      topsoilCarbonFields.setConcentration1978(faker.numerify("3#.#"));
      topsoilCarbonFields.setConcentration1998(faker.numerify("3#.#"));
      topsoilCarbonFields.setConcentration2007(faker.numerify("3#.#"));
      topsoilCarbonFields.setCnRatio1998(faker.numerify("11.#"));
      topsoilCarbonFields.setCnRatio2007(faker.numerify("11.#"));
      topsoilCarbonFields.setCnRatio1998_2007(faker.numerify("-0.#"));
      topsoilCarbonFields.setnConcentration1998_2007(faker.numerify("-0.0#"));
      topsoilCarbonFields.setMiteSpringtailRatio1998(faker.numerify("1.##"));
      topsoilCarbonFields.setMiteSpringtailRatio2007(faker.numerify("5.##"));
      topsoilCarbonFields.setMiteSpringtailRatio9807(faker.numerify("3.##"));
      topsoilCarbonFields.setNumBroadtaxa1998(faker.numerify("4.##"));
      topsoilCarbonFields.setNumBroadtaxa2007(faker.numerify("3.#"));
      topsoilCarbonFields.setNumBroadtaxa9807(faker.numerify("-0.##"));

      topsoilCarbonFieldsList.add(topsoilCarbonFields);
    }

    return topsoilCarbonFieldsList;
  }

  public static List<TopsoilNutrientsFields> createTopsoilNutrientsFields() {
    List<TopsoilNutrientsFields> topsoilNutrientsFieldsList = Lists.newArrayList();

    for (int i = 0; i < 2; i++) {

      TopsoilNutrientsFields topsoilNutrientsFields = new TopsoilNutrientsFields();

      topsoilNutrientsFields.setDominantBroadHabitat("Arable");
      topsoilNutrientsFields.setMinerisableN2007("8.#######");

      topsoilNutrientsFieldsList.add(topsoilNutrientsFields);
    }
    return topsoilNutrientsFieldsList;
  }

  public static List<PHBulkDensityFields> createPHBulkDensityFields() {
    List<PHBulkDensityFields> pHBulkDensityFieldsList = Lists.newArrayList();

    for (int i = 0; i < 2; i++) {

      PHBulkDensityFields pHBulkDensityFields = new PHBulkDensityFields();

      pHBulkDensityFields.setDominantBroadHabitat("Arable");
      pHBulkDensityFields.setPh1978(faker.numerify("6.##"));
      pHBulkDensityFields.setPh1998(faker.numerify("6.##"));
      pHBulkDensityFields.setPh2007(faker.numerify("6.##"));
      pHBulkDensityFields.setCnRatio1998(faker.numerify("11.#"));
      pHBulkDensityFields.setCnRatio2007(faker.numerify("11.#"));
      pHBulkDensityFields.setCnRatio1998_2007(faker.numerify("-0.#"));
      pHBulkDensityFields.setnConc1998_2007(faker.numerify("-0.0#"));
      pHBulkDensityFields.setMiteSpringtailRatio1998(faker.numerify("1.##"));
      pHBulkDensityFields.setMiteSpringtailRatio2007(faker.numerify("5.##"));
      pHBulkDensityFields.setMiteSpringtailRatio9807(faker.numerify("3.##"));
      pHBulkDensityFields.setNumBroadtaxa1998(faker.numerify("4.##"));
      pHBulkDensityFields.setNumBroadtaxa2007(faker.numerify("3.#"));
      pHBulkDensityFields.setNumBroadtaxa1998_2007(faker.numerify("-0.##"));

      pHBulkDensityFieldsList.add(pHBulkDensityFields);
    }


    return pHBulkDensityFieldsList;
  }

  public static List<SoilTextureFields> createSoilTextureFields() {
    List<SoilTextureFields> soilTextureFieldsList = Lists.newArrayList();

    for (int i = 0; i < 2; i++) {

      SoilTextureFields soilTextureFields = new SoilTextureFields();

      soilTextureFields.setObjectId("######");
      soilTextureFields.setShape("Polygon");
      soilTextureFields.setGenPMLith("CLAY-SILT-SAND-GRAVEL");
      soilTextureFields.setEsbDescription("RIVERINE CLAY AND FLOODPLAIN SANDS AND GRAVEL");
      soilTextureFields.setGrainSize(null);
      soilTextureFields.setSoilGroup("ALL");
      soilTextureFields.setSoilDepth("DEEP");
      soilTextureFields.setCac03Rank(null);
      soilTextureFields.setPmmUID(faker.numerify("bgsn:PMMv6_pk_######"));
      soilTextureFields.setEsbCode(faker.numerify("###_###"));
      soilTextureFields.setLexRCS(faker.letterify("???-?????"));
      soilTextureFields.setShapeLength(faker.numerify("3###.####"));
      soilTextureFields.setShapeArea(faker.numerify("999###.######"));

      soilTextureFieldsList.add(soilTextureFields);
    }

    return soilTextureFieldsList;
  }

}
