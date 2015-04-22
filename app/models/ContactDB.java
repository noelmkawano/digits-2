package models;

import views.formdata.ContactFormData;

import java.util.ArrayList;
import java.util.List;

/**
 * An in-memory array of Contacts that have been gathered from input form data.
 */
public class ContactDB {

//  private static Map<Long, Contact> contacts = new HashMap<>();
//  private static Map<String, TelephoneType> telephoneTypes = new HashMap<>();
//  private static Map<String, DietType> dietTypes = new HashMap<>();
//  private static long currentId = 1;

  /**
   * Adds a formData input to the Contacts list.
   *
   * @param formData Input data from the form.
   */
  public static void addContacts(ContactFormData formData) {
    if (formData.id == 0) {
      // Contact does not exist, create new.
      TelephoneType telephoneType = getTelephoneType(formData.telephoneType);
      List<DietType> dietTypes = new ArrayList<>();
      for (String diet : formData.dietTypes) {
        dietTypes.add(getDietType(diet));
      }
      Contact contactFromForm = new Contact(formData.firstName, formData.lastName, formData.telephone, telephoneType,
          dietTypes);
      // Make bi-directional relationships work.
      telephoneType.addContact(contactFromForm);
      for (DietType dietType : dietTypes) {
        dietType.addContact(contactFromForm);
      }
      contactFromForm.save();
    }
    else {
      // Contact exists, update.
      Contact contactFromForm = Contact.find().byId(formData.id);
      contactFromForm.setFirstName(formData.firstName);
      contactFromForm.setLastName(formData.lastName);
      contactFromForm.setTelephone(formData.telephone);
      TelephoneType telephoneType = getTelephoneType(formData.telephoneType);
      contactFromForm.setTelephoneType(telephoneType);
      List<DietType> dietTypes = new ArrayList<>();
      for (String diet : formData.dietTypes) {
        dietTypes.add(getDietType(diet));
      }
      contactFromForm.setDietTypes(dietTypes);
      contactFromForm.save();
    }
  }

  /**
   * Adds a Telephone Type to the TelephoneTypes HashMap.
   *
   * @param telephoneType The TelephoneType to add.
   */
  public static void addTelephoneType(TelephoneType telephoneType) {
    // telephoneTypes.put(telephoneType.getTelephoneType(), telephoneType);
    telephoneType.save();
  }

  /**
   * Adds a Diet Type to the DietTypes HashMap.
   *
   * @param dietType The DietType to add.
   */
  public static void addDietType(DietType dietType) {
    // dietTypes.put(dietType.getDietType(), dietType);
    dietType.save();
  }

  /**
   * Get a specific Telephone type based on a String, or throw a RuntimeException if not found.
   *
   * @param telephoneString The String to search by.
   * @return The telephoneType object if found.
   */
  public static TelephoneType getTelephoneType(String telephoneString) {
    // TelephoneType telephoneType = telephoneTypes.get(telephoneString);
    TelephoneType telephoneType = TelephoneType.find().where().eq("telephoneType", telephoneString).findUnique();
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
    // DietType dietType = dietTypes.get(dietString);
    DietType dietType = DietType.find().where().eq("dietType", dietString).findUnique();
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
    //Contact contact = contacts.get(id);
    Contact contact = Contact.find().byId(id);
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
    //Contact contact = contacts.get(id);
    Contact contact = Contact.find().byId(id);
    if (contact == null) {
      throw new RuntimeException("Unable to find contact with given ID value.");
    }
    //contacts.remove(id);
  }

  /**
   * Gets a list of all contacts currently stored.
   *
   * @return the full list of contacts.
   */
  public static List<Contact> getContacts() {
    // return new ArrayList<>(contacts.values());
    return Contact.find().all();
  }
}
