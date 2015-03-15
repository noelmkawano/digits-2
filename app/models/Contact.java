package models;

/**
 * Model for the Contact information that is entered in the form data.
 */
public class Contact {
  private String firstName;
  private String lastName;
  private String telephone;

  /**
   * Creates a new Contact object from form data.
   *
   * @param firstName The first name.
   * @param lastName  The last name.
   * @param telephone The telephone number.
   */
  public Contact(String firstName, String lastName, String telephone) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.telephone = telephone;
  }

  /**
   * Gets the firstName string.
   * @return The First Name value.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Gets the lastName string.
   * @return The Last Name value.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Gets the telephone number string.
   * @return The Telephone value.
   */
  public String getTelephone() {
    return telephone;
  }
}
