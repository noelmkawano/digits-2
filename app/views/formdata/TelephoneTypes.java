package views.formdata;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for handling Telephone Types, such as Work, Home, Cell, etc.
 */
public class TelephoneTypes {

  private static String[] types = {"Work", "Home", "Mobile"};

  /**
   * Generates a new Map of types with all type pairs set to false.
   *
   * @return The newly generated TypeMap.
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> typeMap = new HashMap<String, Boolean>();
    for (String type : types) {
      typeMap.put(type, false);
    }
    return typeMap;
  }

  /**
   * Verify if a given string is in our typemap of Telephone Types.
   *
   * @param type The type to check.
   * @return Boolean value of whether type is member of Telephone Types.
   */
  public static boolean isType(String type) {
    return getTypes().containsKey(type);
  }

  /**
   * Generates a new Map of Telephone Types with the provided type set to true (if present).
   *
   * @param type The type to get from the list.
   * @return A type Map with the associated type set to true if present.
   */
  public static Map<String, Boolean> getTypes(String type) {
    Map<String, Boolean> typeMap = getTypes();
    if (isType(type)) {
      typeMap.put(type, true);
    }
    return typeMap;
  }

}
