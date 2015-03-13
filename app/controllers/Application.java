package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.ContactFormData;
import views.html.Index;
import views.html.NewContact;

/**
 * Provides controllers for this application.
 */
public class Application extends Controller {

  /**
   * Returns the home page.
   * @return The resulting home page.
   */
  public static Result index() {
    return ok(Index.render("Welcome to the home page."));
  }

  /**
   * Returns newcontact, a simple example of a second page to illustrate navigation.
   * Handles form data as well.
   * @return The NewContact.
   */
  public static Result newcontact() {
    Form<ContactFormData> formData = Form.form(ContactFormData.class);
    return ok(NewContact.render(formData));
  }

  /**
   * Returns the results of the post data from NewContact submission.
   *
   * @return Post data from the form.
   */
  public static Result postContact() {
    System.out.println("In post Contact");
    Form<ContactFormData> formData = Form.form(ContactFormData.class).bindFromRequest();
    ContactFormData data = formData.get();
    System.out.format("%s, %s, %s%n", data.firstName, data.lastName, data.telephone);
    return ok(NewContact.render(formData));
  }

}
