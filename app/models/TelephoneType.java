package models;

import java.util.List;

/**
 * Telephone Type model class for storing Telephone Types.
 */
public class TelephoneType {

  private long id;
  private String telephoneType;
  private List<Contact> contacts;

  /**
   * Get the ID of the Telephone Type.
   *
   * @return The ID of the Telephone Type.
   */
  public long getId() {
    return id;
  }

  /**
   * Get the Type of the Telephone Type.
   *
   * @return The String value Type of the Telephone Type.
   */
  public String getTelephoneType() {
    return telephoneType;
  }

  /**
   * Get the list of Contacts that utilize this Telephone Type.
   *
   * @return A list of Contacts.
   */
  public List<Contact> getContacts() {
    return contacts;
  }

  /**
   * Set the ID of this Telephone Type.
   *
   * @param id The ID to set for this Telephone Type.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Set the Type of this Telephone Type.
   *
   * @param telephoneType The Type String for the Telephone Type.
   */
  public void setTelephoneType(String telephoneType) {
    this.telephoneType = telephoneType;
  }

  /**
   * Set the list of Contacts that use this Telephone Type.
   *
   * @param contacts The list of Contacts that use this Telephone Type.
   */
  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  /**
   * Adds another Contact to the Telephone Type Contact List.
   *
   * @param contact A Contact to add to the List.
   */
  public void addContact(Contact contact) {
    this.contacts.add(contact);
  }

  /**
   * Create a new Telephone Type instance with a String for the Type.
   *
   * @param telephoneType The Type of the Telephone to create.
   */
  public TelephoneType(String telephoneType) {
    this.telephoneType = telephoneType;
  }
}
