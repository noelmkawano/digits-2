import models.ContactDB;
import play.Application;
import play.GlobalSettings;
import views.formdata.ContactFormData;

/**
 * Initializes test data for the application.
 */
public class Global extends GlobalSettings {

  @Override
  public void onStart(Application application) {
    super.onStart(application);

    ContactDB.addContacts(new ContactFormData("Patrick", "Karjala", "808-222-3333", "Mobile"));
    ContactDB.addContacts(new ContactFormData("Brooke", "Jones", "808-111-2222", "Mobile"));
    ContactDB.addContacts(new ContactFormData("Brad", "Rhines", "301-869-8377", "Home"));
    ContactDB.addContacts(new ContactFormData("Bud", "Jones", "808-123-4567", "Work"));
  }
}
