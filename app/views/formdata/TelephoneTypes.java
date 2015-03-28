package views.formdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Patrick A. Karjala on 3/27/15.
 */
public class TelephoneTypes {


  public void setName(String name) {
    this.name = name;
  }


  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public long getId() {
    return id;
  }

  private long id;
  private String name;

  public TelephoneTypes(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public static Map<String, Boolean> getTypes(ContactFormData contact) {
    Map<String, Boolean> contactMap = new HashMap<String, Boolean>();
    for (TelephoneTypes telephonetypes : allTelephoneTypes ) {
      contactMap.put(telephonetypes.getName(), (contact != null && contact.telephoneType.contains(telephonetypes.getName()));
    }
    return contactMap;
  }


  public static boolean isType(String type) {

  }


  public static Map<String, Boolean> getTypes(String types) {

  }

  /** Fake a database of Telephone Types */
  private static List<TelephoneTypes> allTelephoneTypes = new ArrayList<>();


}
