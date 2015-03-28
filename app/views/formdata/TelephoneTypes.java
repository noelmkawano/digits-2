package views.formdata;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to support manipulation of Telephone Type maps.
 */
public class TelephoneTypes {

  /** The list of types. */
  private static String[] types = {"Home", "Work", "Mobile"};

  /**
   * Builds a new map of telephone types, initialized to false.
   * @return The newly created telephone typeMap.
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> typeMap = new HashMap<String, Boolean>();
    for (String type : types) {
      typeMap.put(type, false);
    }
    return typeMap;
  }

  /**
   * Determines if the passed in type is in the list of types.
   * @param type The type to check.
   * @return True or False based on whether the type is in the list.
   */
  public static boolean isType(String type) {
    return getTypes().containsKey(type);
  }

  /**
   * Builds a new map of telephone types where map entry with type is set to true.
   * @param type The type to set to true.
   * @return The map of Telephone Types.
   */
  public static Map<String, Boolean> getTypes(String type) {
    Map<String, Boolean> typeMap = getTypes();
    if (isType(type)) {
      typeMap.put(type, true);
    }
    return typeMap;
  }
}
