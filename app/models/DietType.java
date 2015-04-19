package models;

import java.util.ArrayList;
import java.util.List;

/**
 * The Diet Type class holds various Diet Types such as Chicken, Beef, etc.
 */
public class DietType {
  private long id;
  private String dietType;
  private List<Contact> contacts = new ArrayList<>();

  /**
   * Get the ID.
   *
   * @return The ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Set the ID for the dietType.
   *
   * @param id The ID to set.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Get the diet Type.
   *
   * @return the dietType String.
   */
  public String getDietType() {
    return dietType;
  }

  /**
   * Set the diet Type String.
   *
   * @param dietType The diet Type to set.
   */
  public void setDietType(String dietType) {
    this.dietType = dietType;
  }

  /**
   * Get the list of Contacts associated with this diet Type.
   *
   * @return A list of Contacts.
   */
  public List<Contact> getContacts() {
    return contacts;
  }

  /**
   * Set the list of contacts associated with this diet Type.
   *
   * @param contacts A list of Contacts.
   */
  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  /**
   * Add a new Contact to the list of Contacts associated with this diet Type.
   *
   * @param contact The Contact to add.
   */
  public void addContact(Contact contact) {
    contacts.add(contact);
  }

  /**
   * Create a new dietType.
   *
   * @param dietType The name String of the dietType.
   */
  public DietType(String dietType) {
    this.dietType = dietType;
  }
}
