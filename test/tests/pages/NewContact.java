package tests.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withId;

/**
 * Provides testing support for the NewContact page.
 */
public class NewContact extends FluentPage {

  private String url;

  /**
   * Create the NewContact Page.
   *
   * @param webDriver The driver.
   * @param port      The port.
   */
  public NewContact(WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/newcontact";
  }

  @Override
  public String getUrl() {
    return this.url;
  }

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("NewContact - digits");
  }

  /**
   * Tests the form on the newContact page with given data.
   *
   * @param firstName The First Name.
   * @param lastName  The Last Name.
   * @param telephone The telephone number.
   * @param telephoneType The telephone type.
   */
  public void createContact(String firstName, String lastName, String telephone, String telephoneType) {
    fill("#firstName").with(firstName);
    fill("#lastName").with(lastName);
    fill("#telephone").with(telephone);
    find("select", withId("telephoneType")).find("option", withId(telephoneType)).click();
    submit("#submit");
  }

}

