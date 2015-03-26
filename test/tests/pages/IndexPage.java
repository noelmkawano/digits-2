package tests.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by Patrick A. Karjala on 3/25/15.
 */
public class IndexPage extends FluentPage {

  private String url;

  /**
   * Generates an IndexPage object for testing.
   * @param webDriver The driver object for the web page.
   * @param port The port number to test at.
   */
  public IndexPage(WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port;
  }

  @Override
  public String getUrl() {
    return this.url;
  }

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Home - digits");
  }

}
