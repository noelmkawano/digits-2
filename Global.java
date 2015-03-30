import controllers.Application;
import models.Contact;
import models.ContactDB;
import play.GlobalSettings;
import play.Logger;
import views.formdata.ContactFormData;

/**
 * Created by Patrick A. Karjala on 3/29/15.
 */
public class Global extends GlobalSettings{


  @Override
  public void onStart(Application app) {
    Logger.info("Application has started");

    Contact testContact = new Contact(1, "Patrick", "Karjala", "808-822-2601", "Home");
    ContactFormData formData = new ContactFormData(testContact);
    ContactDB.addContacts(formData);

    Contact testContact2 = new Contact(2, "Brooke", "Jones", "808-497-6326", "Mobile");
    ContactFormData formData2 = new ContactFormData(testContact2);
    ContactDB.addContacts(formData2);

    Contact testContact3 = new Contact(3, "Brad", "Rhines", "301-869-8377", "Home");
    ContactFormData formData3 = new ContactFormData(testContact3);
    ContactDB.addContacts(formData3);


    Contact testContact4 = new Contact(4, "Aaron", "Wilson", "808-742-5263", "Work");
    ContactFormData formData4 = new ContactFormData(testContact4);
    ContactDB.addContacts(formData4);


  }
}
