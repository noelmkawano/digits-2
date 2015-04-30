import models.Contact;
import models.ContactDB;
import models.DietType;
import models.TelephoneType;
import models.DigitsUser;
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

    if (TelephoneType.find().all().isEmpty()) {
      ContactDB.addTelephoneType(new TelephoneType("Mobile"));
      ContactDB.addTelephoneType(new TelephoneType("Home"));
      ContactDB.addTelephoneType(new TelephoneType("Work"));
    }
    if (DietType.find().all().isEmpty()) {
      ContactDB.addDietType(new DietType("Chicken"));
      ContactDB.addDietType(new DietType("Fish"));
      ContactDB.addDietType(new DietType("Beef"));
      ContactDB.addDietType(new DietType("Dairy"));
      ContactDB.addDietType(new DietType("Gluten"));
    }

    if (DigitsUser.find().all().isEmpty()) {
      ContactDB.addNewDigitsUser("test@gmail.com", "password");
      ContactDB.addNewDigitsUser("best@hotmail.com", "logmein");
    }

    if (Contact.find().all().isEmpty()) {
      List<String> testDiet = new ArrayList<>();
      testDiet.add(0, "Beef");
      ContactDB.addContacts(new ContactFormData("Patrick", "Karjala", "808-222-3333", "Mobile", testDiet,
          ContactDB.getDigitsUser("test@gmail.com").getId()));
      List<String> testDiet2 = new ArrayList<>();
      testDiet2.add(0, "Chicken");
      testDiet2.add(1, "Gluten");
      ContactDB.addContacts(new ContactFormData("Brooke", "Jones", "808-111-2222", "Mobile", testDiet2,
          ContactDB.getDigitsUser("test@gmail.com").getId()));
      List<String> testDiet3 = new ArrayList<>();
      ContactDB.addContacts(new ContactFormData("Bradford", "Jones", "254-879-0744", "Home", testDiet3,
          ContactDB.getDigitsUser("best@hotmail.com").getId()));
      List<String> testDiet4 = new ArrayList<>();
      testDiet4.add(0, "Dairy");
      testDiet4.add(1, "Beef");
      testDiet4.add(2, "Fish");
      ContactDB.addContacts(new ContactFormData("Bud", "Jones", "808-123-4567", "Work", testDiet4,
          ContactDB.getDigitsUser("best@hotmail.com").getId()));
    }
  }
}
