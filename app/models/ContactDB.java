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

  /**
   * Adds a formData input to the Contacts list.
   * If the ID to add is 0, creates a new entry.  If not, updates the existing entry.
   *
   * @param formData Input data from the form.
   */
  public static void addContact(ContactFormData formData) {
    long idVal = (formData.id == 0) ? currentId++ : formData.id;
    Contact contactToStore = new Contact(idVal, formData.firstName, formData.lastName, formData.telephone);
    contacts.put(idVal, contactToStore);
  }

  /**
   * Returns the contact associated with a specific ID, or throws an exception.
   *
   * @param id the ID of the contact to fetch.
   * @return The contact.
   */
  public static Contact getContact(long id) {
    Contact contact = contacts.get(id);
    if (contact == null) {
      throw new RuntimeException("Could not find the contact with the associated ID.");
    }
    return contact;
  }

  /**
   * Returns the full map of contacts.
   *
   * @return the full list of contacts.
   */
  public static List<Contact> getContacts() {

    return new ArrayList<>(contacts.values());
  }
}
