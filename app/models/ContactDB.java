package models;

import views.formdata.ContactFormData;

import java.util.ArrayList;
import java.util.List;

/**
 * An in-memory array of Contacts that have been gathered from input form data.
 */
public class ContactDB {

  //private static Map<Long, Contact> contacts = new HashMap<>();
  //private static Map<String, TelephoneType> telephoneTypes = new HashMap<>();
  //private static Map<String, DietType> dietTypes = new HashMap<>();
  //private static long currentId = 1;

  /**
   * Adds a formData input to the Contacts list.
   *
   * @param formData Input data from the form.
   */
  public static void addContact(ContactFormData formData) {
    //long idVal = (formData.id == 0) ? currentId++ : formData.id;

    if (formData.id == 0) {
      TelephoneType telephoneType = getTelephoneType(formData.telephoneType);
      List<DietType> dietTypes = new ArrayList<>();
      for (String diet : formData.dietTypes) {
        dietTypes.add(getDietType(diet));
      }
      Contact contactFromForm = new Contact(formData.firstName, formData.lastName, formData.telephone,
          telephoneType, dietTypes);
      // Make Relationships bi-directional
      telephoneType.addContact(contactFromForm);
      for (DietType dietType : dietTypes) {
        dietType.addContact(contactFromForm);
      }
      contactFromForm.save();
    }
    else {
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
    //contacts.put(idVal, contactFromForm);
  }

  /**
   * Adds a Telephone Type to the TelephoneTypes HashMap.
   *
   * @param telephoneType The TelephoneType to add.
   */
  public static void addTelephoneType(TelephoneType telephoneType) {
    telephoneType.save();
    //telephoneTypes.(telephoneType.getTelephoneType(), telephoneType);
  }

  /**
   * Adds a Diet Type to the DietTypes HashMap.
   *
   * @param dietType The DietType to add.
   */
  public static void addDietType(DietType dietType) {
    dietType.save();
    //dietTypes.put(dietType.getDietType(), dietType);
  }

  /**
   * Get a specific Telephone type based on a String, or throw a RuntimeException if not found.
   *
   * @param telephoneString The String to search by.
   * @return The telephoneType object if found.
   */
  public static TelephoneType getTelephoneType(String telephoneString) {
    TelephoneType telephoneType = TelephoneType.find().where().eq("telephoneType", telephoneString).findUnique();
    //TelephoneType telephoneType = telephoneTypes.get(telephoneString);
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
    DietType dietType = DietType.find().where().eq("dietType", dietString).findUnique();
    //DietType dietType = dietTypes.get(dietString);
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
    contact.delete();
  }

  /**
   * Gets a list of all contacts currently stored.
   *
   * @return the full list of contacts.
   */
  public static List<Contact> getContacts() {
    return Contact.find().all();
  }
}
