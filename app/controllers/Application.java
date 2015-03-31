package controllers;

import models.ContactDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.ContactFormData;
import views.formdata.DietTypes;
import views.formdata.TelephoneTypes;
import views.html.Index;
import views.html.NewContact;

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
    return ok(Index.render(ContactDB.getContacts()));
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
