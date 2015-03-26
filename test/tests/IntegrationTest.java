package tests;

import org.junit.Test;
import play.libs.F.Callback;
import play.test.TestBrowser;
import tests.pages.IndexPage;
import tests.pages.NewContactPage;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

/**
 * Runs a server with a fake in-memory database to test the system.
 */
public class IntegrationTest {

  private final int port = 3333;
  /**
   * Check to see that the index page can be retrieved.
   */
  @Test
  public void testRetrieveIndexPage() {
    running(testServer(port, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        browser.maximizeWindow();
        IndexPage indexPage = new IndexPage(browser.getDriver(), port);
        browser.goTo(indexPage);
        indexPage.isAt();
      }
    });
  }


  /**
   * Check to make sure that a newly created contact can be submitted.
   */
  @Test
  public void testCreateNewContact() {
    running(testServer(port, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        browser.maximizeWindow();
        NewContactPage contactPage = new NewContactPage(browser.getDriver(), port);
        browser.goTo(contactPage);
        String firstName = "John";
        String lastName = "Smith";
        String telephone = "808-867-5309";
        contactPage.submitForm(firstName, lastName, telephone);
        assertThat(browser.pageSource()).contains(firstName);
        assertThat(browser.pageSource()).contains(lastName);
        assertThat(browser.pageSource()).contains(telephone);
      }
    });
  }
}
