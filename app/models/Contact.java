package models;

/**
 * A contact object that holds a First Name, Last Name, and Telephone Number.
 */
public class Contact {
  private String firstName;
  private String lastName;
  private String telephone;
  private long id;

  /**
   * Create new Contact object.
   *
   * @param id        the user id string.
   * @param firstName the user first name.
   * @param lastName  the user last name.
   * @param telephone the user telephone number.
   */
  public Contact(long id, String firstName, String lastName, String telephone) {

    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.telephone = telephone;
  }

  /**
   * Returns the first name value to the caller.
   *
   * @return firstName String.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the last name value to the caller.
   *
   * @return lastName String.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Returns the telephone number value to the caller.
   *
   * @return telephone String.
   */
  public String getTelephone() {
    return telephone;
  }

  /**
   * Returns the ID value to the caller.
   *
   * @return id long value.
   */
  public long getId() {
    return id;
  }
}
