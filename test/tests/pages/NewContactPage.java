package tests.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by Patrick A. Karjala on 3/25/15.
 */
public class NewContactPage extends FluentPage {

  private String url;

  /**
   * Construct the page. Note that you must always pass a studentID.
   * @param webDriver The driver.
   * @param port The port.
   */
  public NewContactPage(WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/newcontact";
  }

  @Override
  public String getUrl() {
    return this.url;
  }

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("play-example-form");
  }

  /**
   * Run a test form submission to the form.
   * @param firstName The submitted test firstName.
   * @param lastName The submitted test lastName.
   * @param telephone The submitted Telephone number.
   */
  public void submitForm(String firstName, String lastName, String telephone) {
    // Fill the input field with id "name" with the passed name string.
    fill("#firstName").with(firstName);
    // Find the menu with id "gender", and click the menu item equal to the passed gender string.
    fill("#lastName").with(lastName);
    // Fill the input field with id "favoriteFood" with the passed string.
    fill("#telephone").with(telephone);
    //Submit the form whose id is "submit"
    submit("#submit");
  }

}
