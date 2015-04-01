package views.formdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for handling Diet Types, such as Chicken, Fish, Beef, etc.
 */
public class DietTypes {

  private static String[] types = {"Chicken", "Fish", "Beef", "Dairy", "Gluten"};

  /**
   * Generates a new Map of types with all type pairs set to false.
   *
   * @return The newly generated TypeMap.
   */
  public static Map<String, Boolean> getDietTypes() {
    Map<String, Boolean> dietTypeMap = new HashMap<String, Boolean>();
    for (String type : types) {
      dietTypeMap.put(type, false);
    }
    return dietTypeMap;
  }

  /**
   * Verify if a given Diet is in our typemap of Diet Types.
   *
   * @param dietName The type to check.
   * @return Boolean value of whether type is member of Diet Types.
   */
  public static boolean isType(String dietName) {
    return getDietTypes().containsKey(dietName);
  }

  /**
   * Generates a new Map of Diet Types with the provided types set to true (if present).
   *
   * @param dietNames The list of diets to add to our map.
   * @return A type Map with the associated type set to true if present.
   */
  public static Map<String, Boolean> getDietTypes(List<String> dietNames) {
    Map<String, Boolean> dietTypeMap = getDietTypes();
    for (String diet : dietNames) {
      if (dietTypeMap.containsKey(diet)) {
        dietTypeMap.put(diet, true);
      }
    }
    return dietTypeMap;
  }
}
