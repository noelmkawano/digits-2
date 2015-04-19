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
  private static long currentId = 1;

  private static Map<String, TelephoneType> telephoneTypes = new HashMap<>();

  private static Map<String, DietType> dietTypes = new HashMap<>();

  /**
   * Adds a formData input to the Contacts list.
   *
   * @param formData Input data from the form.
   */
  public static void addContacts(ContactFormData formData) {
    long idVal = (formData.id == 0) ? currentId++ : formData.id;
    TelephoneType telephoneFromForm = new TelephoneType(formData.telephoneType);
    List<DietType> dietFromForm = new ArrayList<>();
    for (String diet : formData.dietTypes) {
      DietType thisDiet = new DietType(diet);
      dietFromForm.add(thisDiet);
    }

    Contact contactFromForm = new Contact(idVal, formData.firstName, formData.lastName, formData.telephone,
        telephoneFromForm, dietFromForm);
    contacts.put(idVal, contactFromForm);
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

  /**
   * Add a new Diet Type to the storage Map.
   *
   * @param dietType The Diet Type object to add.
   */
  public static void addDietType(DietType dietType) {
    dietTypes.put(dietType.getDietType(), dietType);
  }

  /**
   * Get a Diet Type from the in-memory database.
   *
   * @param dietTypeName The Name of the Diet Type to retrieve.
   * @return A retrieved Diet Type.
   */
  public static DietType getDietType(String dietTypeName) {
    DietType dietType = dietTypes.get(dietTypeName);
    if (dietType == null) {
      throw new RuntimeException("Unable to find diet type with the given name.");
    }
    return dietType;
  }

  /**
   * Add a new Telephone Type to the storage Map.
   *
   * @param telephoneType The Telephone Type object to add.
   */
  public static void addTelephoneType(TelephoneType telephoneType) {
    telephoneTypes.put(telephoneType.getTelephoneType(), telephoneType);
  }

  /**
   * Return the Telephone Type based on an input Name.
   * @param telephoneTypeName The Name of the Telephone Type to return.
   * @return The Telephone Type.
   */
  public static TelephoneType getTelephoneType(String telephoneTypeName) {
    TelephoneType telephoneType = telephoneTypes.get(telephoneTypeName);
    if (telephoneType == null) {
      throw new RuntimeException("Unable to find telephone type with the given name.");
    }
    return telephoneType;
  }
}
