package views.formdata;

import models.ContactDB;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * UserFormData creates a new account based on the submitted information.
 */
public class UserFormData {

  /**
   * The user's email address.
   */
  public String email = "";

  /**
   * The user's password.
   */
  public String password = "";

  /**
   * Default no-arg constructor required by Play.
   */
  public UserFormData() {
    // No content.
  }

  /**
   * Validates Form<UserFormData>.
   * Called automatically in the controller by bindFromRequest().
   * Checks to see that user does not already exist.
   * @return Null if valid, or a List[ValidationError] if problems found.
   */
  public List<ValidationError> validate() {

    List<ValidationError> errors = new ArrayList<>();

    if (!ContactDB.isUser(email)) {
      errors.add(new ValidationError("email", "Sorry, a user with this email address already exists.");
      errors.add(new ValidationError("password", ""));
    }

    return (errors.size() > 0) ? errors : null;
  }
}
