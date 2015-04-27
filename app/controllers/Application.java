package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.providers.password.UsernamePasswordAuthProvider;
import com.feth.play.module.pa.user.AuthUser;
import models.ContactDB;
import models.User;
import play.Routes;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import providers.MyUsernamePasswordAuthProvider;
import views.formdata.ContactFormData;
import views.formdata.DietTypes;
import views.formdata.TelephoneTypes;
import views.html.Index;
import views.html.Login;
import views.html.NewContact;
import views.html.Profile;
import views.html.Restricted;
import views.html.Signup;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Provides controllers for this application.
 */
public class Application extends Controller {

  /**
   * Message key static info.
   */
  public static final String FLASH_MESSAGE_KEY = "message";

  /**
   * Error key static.
   */
  public static final String FLASH_ERROR_KEY = "error";

  /**
   * User Role static.
   */
  public static final String USER_ROLE = "user";

  /**
   * Returns the home page.
   *
   * @return The resulting home page.
   */
  public static Result index() {
    return ok(Index.render(ContactDB.getContacts()));
  }


  /**
   * Gets the currently logged in user based on Session information.
   *
   * @param session The current logged in session.
   * @return The user object.
   */
  public static User getLocalUser(final Http.Session session) {
    final AuthUser currentAuthUser = PlayAuthenticate.getUser(session);
    //final User localUser = User.findByAuthUserIdentity(currentAuthUser);
    return User.findByAuthUserIdentity(currentAuthUser);
  }

  /**
   * Restricted blanket result for pages that should not have access.
   *
   * @return Restricted local user info.
   */
  @Restrict(@Group(Application.USER_ROLE))
  public static Result restricted() {
    final User localUser = getLocalUser(session());
    return ok(Restricted.render(localUser));
  }

  @Restrict(@Group(Application.USER_ROLE))
  public static Result profile() {
    final User localUser = getLocalUser(session());
    return ok(Profile.render(localUser));
  }

  /**
   * Render the login page.
   *
   * @return The login page.
   */
  public static Result login() {
    return ok(Login.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
  }

  /**
   * Process a login.
   *
   * @return Whether the login attempt was successful.
   */
  public static Result doLogin() {
    com.feth.play.module.pa.controllers.Authenticate.noCache(response());
    final Form<MyUsernamePasswordAuthProvider.MyLogin> filledForm = MyUsernamePasswordAuthProvider.LOGIN_FORM
        .bindFromRequest();
    if (filledForm.hasErrors()) {
      // User did not fill everything properly
      return badRequest(Login.render(filledForm));
    } else {
      // Everything was filled
      return UsernamePasswordAuthProvider.handleLogin(ctx());
    }
  }

  /**
   * Render the signup page.
   *
   * @return The rendered signup page.
   */
  public static Result signup() {
    return ok(Signup.render(MyUsernamePasswordAuthProvider.SIGNUP_FORM));
  }

  /**
   * The jsRoutes page.
   * @return Rendered jsRoutes.
   */
  public static Result jsRoutes() {
    return ok(
        Routes.javascriptRouter("jsRoutes",
            controllers.routes.javascript.Signup.forgotPassword()))
        .as("text/javascript");
  }

  /**
   * Sign up the user based on the input information.
   *
   * @return Failure if there are errors, success if the user filled in the form correctly.
   */
  public static Result doSignup() {
    com.feth.play.module.pa.controllers.Authenticate.noCache(response());
    final Form<MyUsernamePasswordAuthProvider.MySignup> filledForm = MyUsernamePasswordAuthProvider.SIGNUP_FORM
        .bindFromRequest();
    if (filledForm.hasErrors()) {
      // User did not fill everything properly
      return badRequest(Signup.render(filledForm));
    } else {
      // Everything was filled
      // do something with your part of the form before handling the user
      // signup
      return UsernamePasswordAuthProvider.handleSignup(ctx());
    }
  }

  /**
   * Formats a timestamp for the current Date and Time.
   *
   * @param t The integer to be associated with the timestamp.
   * @return The current date and time.
   */
  public static String formatTimestamp(final long t) {
    return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date(t));
  }

  /**
   * Renders the newContact page with a form to add new contacts if the ID is 0; otherwise updates the existing contact.
   *
   * @param id The ID value passed in.
   * @return The newContact page.
   */
  public static Result newContact(long id) {
    ContactFormData data = (id == 0) ? new ContactFormData() : new ContactFormData(ContactDB.getContact(id));
    Form<ContactFormData> formData = Form.form(ContactFormData.class).fill(data);
    Map<String, Boolean> telephoneTypeMap = TelephoneTypes.getTypes(data.telephoneType);
    Map<String, Boolean> dietTypes = DietTypes.getDietTypes(data.dietTypes);
    return ok(NewContact.render(formData, telephoneTypeMap, dietTypes));
  }

  /**
   * Renders the index page with the given record deleted from the in-memory database.
   *
   * @param id The ID value passed in.
   * @return The Index page.
   */
  public static Result deleteContact(long id) {
    ContactDB.deleteContact(id);
    return ok(Index.render(ContactDB.getContacts()));
  }

  /**
   * Handles the request to post form data from the newContact page.
   *
   * @return The newContact page, either with errors or with form data.
   */
  public static Result postContact() {
    Form<ContactFormData> formData = Form.form(ContactFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      System.out.println("HTTP Form Error.");
      return badRequest(NewContact.render(formData, TelephoneTypes.getTypes(), DietTypes.getDietTypes()));
    }
    else {
      ContactFormData data = formData.get();
      ContactDB.addContacts(data);
      System.out.printf("HTTP OK; Form Data:  %s, %s, %s, %s %n", data.firstName, data.lastName, data.telephone,
          data.telephoneType);
      System.out.println(data.dietTypes);
      return ok(NewContact.render(formData, TelephoneTypes.getTypes(data.telephoneType),
          DietTypes.getDietTypes(data.dietTypes)));
    }
  }
}
