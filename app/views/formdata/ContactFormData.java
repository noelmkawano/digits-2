package views.formdata;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * ContactFormData allows for the storage of Form Data that is input by the user.
 */
public class ContactFormData {

  /** Length of the telephone number */
  private static final int NUM_TELEPHONE_DIGITS = 12;

  /** Input data first name field. */
  public String firstName = "";

  /** Input data last name field. */
  public String lastName = "";

  /** Input data telephone field. */
  public String telephone = "";

  /**
   * Validates the form input for ContactFormData.
   * @return null if all valid, otherwise error with message.
   */
  public List<ValidationError> validate() {

    /** The holding array for a potential list of errors. */
    List<ValidationError> errors = new ArrayList<>();

    if ((firstName == null) || (firstName.length() == 0)) {
      errors.add(new ValidationError("firstName", "First Name is required."));
    }
    if ((lastName == null) || (lastName.length() == 0)) {
      errors.add(new ValidationError("lastName", "Last Name is required."));
    }
    if ((telephone == null) || (telephone.length() == 0)) {
      errors.add(new ValidationError("telephone", "Telephone number is required."));
    }
    if (telephone.length() != NUM_TELEPHONE_DIGITS) {
      errors.add(new ValidationError("telephone", "Telephone must be formatted as xxx-xxx-xxxx."));
    }

    return errors.isEmpty() ? null : errors;
  }

}
