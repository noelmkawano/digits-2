import models.ContactDB;
import play.Application;
import play.GlobalSettings;
import views.formdata.ContactFormData;

import java.util.ArrayList;
import java.util.List;

/**
 * Initializes test data for the application.
 */
public class Global extends GlobalSettings {

  @Override
  public void onStart(Application application) {
    super.onStart(application);

    List<String> testDiet = new ArrayList<>();
    testDiet.add(0, "Beef");
    ContactDB.addContacts(new ContactFormData("Patrick", "Karjala", "808-222-3333", "Mobile", testDiet));
    List<String> testDiet2 = new ArrayList<>();
    testDiet2.add(0, "Chicken");
    testDiet2.add(1, "Gluten");
    ContactDB.addContacts(new ContactFormData("Brooke", "Jones", "808-111-2222", "Mobile", testDiet2));
    List<String> testDiet3 = new ArrayList<>();
    testDiet3.add(0, "Fish");
    testDiet3.add(1, "Gluten");
    ContactDB.addContacts(new ContactFormData("Brad", "Rhines", "301-869-8377", "Home", testDiet3));
    List<String> testDiet4 = new ArrayList<>();
    testDiet4.add(0, "Dairy");
    testDiet4.add(1, "Beef");
    testDiet4.add(2, "Fish");
    ContactDB.addContacts(new ContactFormData("Bud", "Jones", "808-123-4567", "Work", testDiet4));
  }
}
