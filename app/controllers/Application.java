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
   *
   * @return The resulting home page.
   */
  public static Result index() {
    return ok(Index.render("Welcome to the home page."));
  }

  /**
   * Renders the newContact page with a form to add new contacts.
   *
   * @return The newContact page.
   */
  public static Result newContact() {
    Form<ContactFormData> formData = Form.form(ContactFormData.class);
    return ok(NewContact.render(formData));
  }

  /**
   * Processes the form data passed in by newContact.
   *
   * @return The newContact page, either with errors or with form data.
   */
  public static Result postContact() {
    Form<ContactFormData> formData = Form.form(ContactFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      System.out.println("HTTP Form Error.");
      return badRequest(NewContact.render(formData));
    }
    else {
      ContactFormData data = formData.get();
      System.out.printf("HTTP OK; Form Data:  %s, %s, %s, %n", data.firstName, data.lastName, data.telephone);
      return ok(NewContact.render(formData));
    }
  }
}
