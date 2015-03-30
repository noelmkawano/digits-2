
import models.ContactDB;
import play.GlobalSettings;
import views.formdata.ContactFormData;


/**
 * Initialize system with four contacts in DB.
 */
public class Global extends GlobalSettings {


  @Override
  public void onStart(play.Application application) {
    super.onStart(application);

    ContactDB.addContacts(new ContactFormData("Patrick", "Karjala", "808-497-6327", "Mobile"));
    ContactDB.addContacts(new ContactFormData("Brooke", "Jones", "808-644-3333", "Mobile"));
    ContactDB.addContacts(new ContactFormData("Aaron", "Wilson", "808-742-5263", "Home"));
    ContactDB.addContacts(new ContactFormData("Karen", "Gally", "808-245-6327", "Work"));
  }
}
