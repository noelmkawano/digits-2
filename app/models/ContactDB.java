package models;

import views.formdata.ContactFormData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An in-memory array of Contacts that have been gathered from input form data.
 */
public class ContactDB {

  private static Map<Long, Contact> contacts = new HashMap<>();
  private static Map<String, TelephoneType> telephoneTypes = new HashMap<>();
  private static Map<String, DietType> dietTypes = new HashMap<>();
  private static long currentId = 1;

  /**
   * Adds a formData input to the Contacts list.
   *
   * @param formData Input data from the form.
   */
  public static void addContacts(ContactFormData formData) {
    long idVal = (formData.id == 0) ? currentId++ : formData.id;
    TelephoneType telephoneType = getTelephoneType(formData.telephoneType);
    List<DietType> dietTypes = new ArrayList<>();
    for (String diet : formData.dietTypes) {
      dietTypes.add(getDietType(diet));
    }
    Contact contactFromForm = new Contact(idVal, formData.firstName, formData.lastName, formData.telephone,
        telephoneType, dietTypes);
    contacts.put(idVal, contactFromForm);
  }

  /**
   * Adds a Telephone Type to the TelephoneTypes HashMap.
   *
   * @param telephoneType The TelephoneType to add.
   */
  public static void addTelephoneType(TelephoneType telephoneType) {
    telephoneTypes.put(telephoneType.getTelephoneType(), telephoneType);
  }

  /**
   * Adds a Diet Type to the DietTypes HashMap.
   *
   * @param dietType The DietType to add.
   */
  public static void addDietType(DietType dietType) {
    dietTypes.put(dietType.getDietType(), dietType);
  }

  /**
   * Get a specific Telephone type based on a String, or throw a RuntimeException if not found.
   *
   * @param telephoneString The String to search by.
   * @return The telephoneType object if found.
   */
  public static TelephoneType getTelephoneType(String telephoneString) {
    TelephoneType telephoneType = telephoneTypes.get(telephoneString);
    if (telephoneType == null) {
      throw new RuntimeException("Invalid Telephone Type: " + telephoneString);
    }
    return telephoneType;
  }

  /**
   * Get a specific Diet Type object based on a String, or throw a RuntimeException if not found.
   *
   * @param dietString The Diet Type String passed in.
   * @return The dietType object if found.
   */
  public static DietType getDietType(String dietString) {
    DietType dietType = dietTypes.get(dietString);
    if (dietType == null) {
      throw new RuntimeException("Invalid Diet Type: " + dietString);
    }
    return dietType;
  }

  /**
   * Gets a contact from the contacts in-memory database with a matching ID value.
   *
   * @param id The ID value to match.
   * @return the contact associated with the ID.
   */
  public static Contact getContact(long id) {
    Contact contact = contacts.get(id);
    if (contact == null) {
      throw new RuntimeException("Unable to find contact with given ID value.");
    }
    return contact;
  }

  /**
   * Deletes a contact from the in-memory database with a matching ID value.
   *
   * @param id The ID value of the contact to delete.
   */
  public static void deleteContact(long id) {
    Contact contact = contacts.get(id);
    if (contact == null) {
      throw new RuntimeException("Unable to find contact with given ID value.");
    }
    contacts.remove(id);
  }

  /**
   * Gets a list of all contacts currently stored.
   *
   * @return the full list of contacts.
   */
  public static List<Contact> getContacts() {
    return new ArrayList<>(contacts.values());
  }
}
