package models;

import java.util.ArrayList;
import java.util.List;

/**
 * A contact object that holds a First Name, Last Name, and Telephone Number.
 */
public class Contact {
  private long id;
  private String firstName;
  private String lastName;
  private String telephone;
  private TelephoneType telephoneType;
  private List<DietType> dietTypes = new ArrayList<>();


  /**
   * Create new Contact object.
   *
   * @param id            the id value.
   * @param firstName     the user first name.
   * @param lastName      the user last name.
   * @param telephone     the user telephone number.
   * @param telephoneType the user telephone type.
   * @param dietTypes     A list of dietary preferences.
   */
  public Contact(long id, String firstName, String lastName, String telephone, TelephoneType telephoneType,
                 List<DietType> dietTypes) {

    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.telephone = telephone;
    this.telephoneType = telephoneType;
    this.dietTypes = dietTypes;
  }

  /**
   * Returns the id value to the caller.
   *
   * @return id long value.
   */
  public long getId() {
    return id;
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
   * Returns the telephone type object to the caller.
   *
   * @return Telephone type string.
   */
  public String getTelephoneType() {
    return telephoneType.getTelephoneType();
  }

  /**
   * Returns the diet Type object to the caller.
   *
   * @return the Diet type value list.
   */
  public List<String> getDietTypes() {
    List<String> returnedDietTypes = new ArrayList<>();
    for (DietType diet : dietTypes) {
      returnedDietTypes.add(diet.getDietType());
    }
    return returnedDietTypes;
  }
}
