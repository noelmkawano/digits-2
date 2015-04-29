package controllers;

import models.ContactDB;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.ContactFormData;
import views.formdata.DietTypes;
import views.formdata.LoginFormData;
import views.formdata.TelephoneTypes;
import views.html.Index;
import views.html.Login;
import views.html.NewContact;

import java.util.List;
import java.util.Map;

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
    return ok(Index.render("Home", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), ContactDB.getContacts()));
  }

  /**
   * Provides the Login page (only to unauthenticated users).
   *
   * @return The Login page.
   */
  public static Result login() {
    Form<LoginFormData> formData = Form.form(LoginFormData.class);
    return ok(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData));
  }

  /**
   * Processes a login form submission from an unauthenticated user.
   * First we bind the HTTP POST data to an instance of LoginFormData.
   * The binding process will invoke the LoginFormData.validate() method.
   * If errors are found, re-render the page, displaying the error data.
   * If errors not found, render the page with the good data.
   *
   * @return The index page with the results of validation.
   */
  public static Result postLogin() {

    // Get the submitted form data from the request object, and run validation.
    Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();

    if (formData.hasErrors()) {
      for (String key : formData.errors().keySet()) {
        List<ValidationError> currentError = formData.errors().get(key);
        for (play.data.validation.ValidationError error : currentError) {
          if (!error.message().equals("")) {
            flash(key, error.message());
          }
        }
      }
      return badRequest(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData));
    }
    else {
      // email/password OK, so now we set the session variable and only go to authenticated pages.
      session().clear();
      session("email", formData.get().email);
      return redirect(routes.Application.index());
    }
  }

  /**
   * Logs out (only for authenticated users) and returns them to the Index page.
   *
   * @return A redirect to the Index page.
   */
  @Security.Authenticated(Secured.class)
  public static Result logout() {
    session().clear();
    return redirect(routes.Application.index());
  }

  /**
   * Renders the newContact page with a form to add new contacts if the ID is 0; otherwise updates the existing contact.
   *
   * @param id The ID value passed in.
   * @return The newContact page.
   */
  @Security.Authenticated(Secured.class)
  public static Result newContact(long id) {
    ContactFormData data = (id == 0) ? new ContactFormData() : new ContactFormData(ContactDB.getContact(id));
    Form<ContactFormData> formData = Form.form(ContactFormData.class).fill(data);
    Map<String, Boolean> telephoneTypeMap = TelephoneTypes.getTypes(data.telephoneType);
    Map<String, Boolean> dietTypes = DietTypes.getDietTypes(data.dietTypes);
    return ok(NewContact.render("NewContact", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()),
        formData, telephoneTypeMap, dietTypes));
  }

  /**
   * Renders the index page with the given record deleted from the in-memory database.
   *
   * @param id The ID value passed in.
   * @return The Index page.
   */
  @Security.Authenticated(Secured.class)
  public static Result deleteContact(long id) {
    ContactDB.deleteContact(id);
    return ok(Index.render("Home", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), ContactDB.getContacts()));
  }

  /**
   * Handles the request to post form data from the newContact page.
   *
   * @return The newContact page, either with errors or with form data.
   */
  @Security.Authenticated(Secured.class)
  public static Result postContact() {
    Form<ContactFormData> formData = Form.form(ContactFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      System.out.println("HTTP Form Error.");
      return badRequest(NewContact.render("NewContact", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()),
          formData, TelephoneTypes.getTypes(), DietTypes.getDietTypes()));
    }
    else {
      ContactFormData data = formData.get();
      ContactDB.addContacts(data);
      System.out.printf("HTTP OK; Form Data:  %s, %s, %s, %s %n", data.firstName, data.lastName, data.telephone,
          data.telephoneType);
      System.out.println(data.dietTypes);
      return ok(NewContact.render("NewContact", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()),
          formData, TelephoneTypes.getTypes(data.telephoneType), DietTypes.getDietTypes(data.dietTypes)));
    }
  }
}
