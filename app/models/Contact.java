package models;

/**
 * Mockup model for the back end database.
 */
public class Contact {

  private String firstName;
  private String lastName;
  private String telephone;

  /**
   * Creates a contact instance.
   * @param firstName The first name.
   * @param lastName The last name.
   * @param telephone The telephone.
   */
  public Contact(String firstName, String lastName, String telephone) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.telephone = telephone;
  }

  /**
   * Returns First Name.
   * @return The First Name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns Last Name.
   * @return The Last Name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Returns telephone number.
   * @return The telephone number.
   */
  public String getTelephone() {
    return telephone;
  }
}
