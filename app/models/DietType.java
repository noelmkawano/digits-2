package models;

import java.util.List;

/**
 * Model class for Diet Type objects.
 */
public class DietType {

  private long id;
  private String dietType;
  private List<Contact> contacts;

  /**
   * Get the ID of the Diet Type.
   *
   * @return The ID of the Diet Type.
   */
  public long getId() {
    return id;
  }

  /**
   * Get the Type of the Diet Type.
   *
   * @return The String value Type of the Diet Type.
   */
  public String getDietType() {
    return dietType;
  }

  /**
   * Get the list of Contacts that utilize this Diet Type.
   *
   * @return A list of Contacts.
   */
  public List<Contact> getContacts() {
    return contacts;
  }

  /**
   * Set the ID of this Diet Type.
   *
   * @param id The ID to set for this Diet Type.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Set the Type of this Diet Type.
   *
   * @param dietType The Type String for the Diet Type.
   */
  public void setDietType(String dietType) {
    this.dietType = dietType;
  }

  /**
   * Set the list of Contacts that use this Diet Type.
   *
   * @param contacts The list of Contacts that use this Diet Type.
   */
  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  /**
   * Adds another Contact to the Diet Type Contact List.
   *
   * @param contact A Contact to add to the List.
   */
  public void addContact(Contact contact) {
    this.contacts.add(contact);
  }

  /**
   * Create a new instance of Diet Type.
   *
   * @param dietType The Type String of the Diet Type.
   */
  public DietType(String dietType) {
    this.dietType = dietType;
  }
}
