package tests;

import models.Contact;
import models.DietType;
import models.TelephoneType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by Patrick A. Karjala on 4/21/15.
 */
public class ModelTest {

  /**
   * Create and save an instance and see if it can be retrieved.
   * NOTE: Running this test within IntelliJ 14.1 fails with a PersistenceException.
   * Use 'activator test' for correct operation.
   */
  @Test
  public void test() {
    running(fakeApplication(inMemoryDatabase()), new Runnable() {
      public void run() {
        assertThat(TelephoneType.find().all().size()).isEqualTo(3);

        TelephoneType telephoneType = TelephoneType.find().byId(1L);
        DietType dietType = DietType.find().byId(1L);
        List<DietType> dietTypes = new ArrayList<>();
        dietTypes.add(dietType);
        Contact contact = new Contact("Aaron", "Wilson", "808-954-9388", telephoneType, dietTypes);
        contact.save();

        // Test bi-directional relationships.
        assertThat(DietType.find().byId(1L).getContacts().size()).isEqualTo(2);
        assertThat(TelephoneType.find().byId(1L).getContacts().size()).isEqualTo(3);
      }
    });
  }
}
