package models;

import views.formdata.ContactFormData;

import java.util.ArrayList;
import java.util.List;

/**
 * A temporary in-memory database for storing contact data.
 */
public class ContactDB {

  private static List<Contact> contacts = new ArrayList<>();

  /**
   * Adds a new contact to the ContactDB of type Contact from user-submitted form data.
   *
   * @param formData The data from the user-submitted form.
   */
  public static void addContacts(ContactFormData formData) {
    Contact contactFromForm = new Contact(formData.firstName, formData.lastName, formData.telephone);
    contacts.add(contactFromForm);
  }

  /**
   * Gets the full list of contacts currently in memory.
   *
   * @return The list of contacts.
   */
  public static List<Contact> getContacts() {
    return contacts;
  }
}
