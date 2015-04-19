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
 * Check the model implementation.
 *
 * @author Philip Johnson
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

        // Test Bi-directional links
        TelephoneType telephoneType = TelephoneType.find().byId(1L);
        DietType dietType = DietType.find().byId(1L);
        List<DietType> dietTypes = new ArrayList<DietType>();
        dietTypes.add(dietType);
        Contact contact = new Contact("Aaron", "Paul", "111-222-3333", telephoneType, dietTypes);
        contact.save();

        assertThat(DietType.find().byId(1L).getContacts().size()).isEqualTo(2);
        assertThat(TelephoneType.find().byId(1L).getContacts().size()).isEqualTo(3);
        //assertThat(DietType.find().all().size().isEqualTo(5));
      }
    });
  }

}
