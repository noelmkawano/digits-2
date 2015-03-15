package models;

import views.formdata.ContactFormData;

import java.util.ArrayList;
import java.util.List;

/**
 * An in-memory array of Contacts that have been gathered from input form data.
 */
public class ContactDB {

  private static List<Contact> contacts = new ArrayList<>();

  /**
   * Adds a formData input to the Contacts list.
   * @param formData Input data from the form.
   */
  public static void addContacts(ContactFormData formData) {
    Contact contactFromForm = new Contact(formData.firstName, formData.lastName, formData.telephone);
    contacts.add(contactFromForm);
  }

  /**
   * Gets a list of all contacts currently stored.
   * @return the full list of contacts.
   */
  public static List<Contact> getContacts() {
    return contacts;
  }
}
