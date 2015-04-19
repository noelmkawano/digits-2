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
  private List<DietType> dietTypes;


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
   * Set the ID.
   * @param id The ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Set the First Name.
   * @param firstName The First Name String.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Set the Last Name.
   * @param lastName The Last Name String.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Set the Telephone Number.
   * @param telephone The Telephone Number string.
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  /**
   * Set the telephoneType.
   * @param telephoneType The TelephoneType object.
   */
  public void setTelephoneType(TelephoneType telephoneType) {
    this.telephoneType = telephoneType;
  }

  /**
   * Set the dietType.
   * @param dietTypes The DietType object.
   */
  public void setDietTypes(List<DietType> dietTypes) {
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
   * Returns the telephone type value to the caller.
   *
   * @return Telephone type string.
   */
  public TelephoneType getTelephoneType() {
    return telephoneType;
  }

  /**
   * Returns the diet Type values to the caller.
   *
   * @return the Diet type value list.
   */
  public List<DietType> getDietTypes() {
    return dietTypes;
  }

  /**
   * Returns the list of Diet Types as a string for display by the Scala Template.
   * @return A single string object.
   */
  public String getDietTypesString() {
    String diets = "";
    for (DietType diet : dietTypes) {
      diets += diet.getDietType() + ", ";
    }
    return diets.substring(0, (diets.length() == 0 ? 0 : (diets.length() - 2)));
  }

  /**
   * Returns a list of DietType Strings for this Contact.
   * @return The list of Diet Type Strings.
   */
  public List<String> getDietTypesList() {
    List<String> dietList = new ArrayList<>();
    for (DietType diet : this.dietTypes) {
      dietList.add(diet.getDietType());
    }
    return dietList;
  }
}
