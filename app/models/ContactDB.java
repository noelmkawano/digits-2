package models;

import views.formdata.ContactFormData;

import java.util.ArrayList;
import java.util.List;

/**
 * A mockup of the internal data structure for Contacts.
 */
public class ContactDB {

  private static List<Contact> contacts = new ArrayList<>();

  /**
   * Adds a new object of type Contact built from form data to our local storage contacts list.
   * @param formData The contact form data.
   */
  public static void addContact(ContactFormData formData) {
    Contact thiscontact = new Contact(formData.firstName, formData.lastName, formData.telephone);
//    thiscontact.firstName = formData.firstName;
//    thiscontact.lastName = formData.lastName;
//    thiscontact.telephone = formData.telephone;
    contacts.add(thiscontact);
  }

  /**
   * Gets the list of contacts in memory.
   * @return The list of contacts.
   */
  public static List<Contact> getContacts() {
    return contacts;
  }


}
