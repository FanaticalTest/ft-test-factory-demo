package com.fanaticaltest.test_factory_demo.cucumber;

import com.fanaticaltest.test_factory_demo.api.BooksApi;
import com.fanaticaltest.test_factory_demo.lib.Property;
import com.fanaticaltest.test_factory_demo.pages.DemoWebsite;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.net.MalformedURLException;

public class DemoWebSteps extends DemoWebsite {

  private Property prop = new Property();
  private String ft_demo_website_url = prop.read("ft_demo_website_url");
  private BooksApi books = new BooksApi();

  @Before("@TestType=Selenium")
  public void before_scenario(Scenario scenario) {
    try {
      beforeScenario(browserNameOS.CHROME_LINUX, scenario);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @After("@TestType=Selenium")
  public void after_scenario(Scenario scenario) {
    afterScenario(scenario);
  }

  @Given("^the user is on the shopping page \"([^\"]*)\"$")
  public void the_user_is_on_the_shopping_page(String url) throws Throwable {
    loadPage(ft_demo_website_url + url);
    assertPageTitle("Demo Website - Shopping", url);
  }

  @When("^the user clicks on the buy button of the product id \"([^\"]*)\"$")
  public void the_user_clicks_on_the_buy_button_of_the_product_id(String id) throws Throwable {
    clickOnBuy(id);
  }

  @Then("^the user sees the product \"([^\"]*)\" id the shopping cart$")
  public void the_user_sees_the_product_id_the_shopping_cart(String id) throws Throwable {
    checkValueInShoppringCart(id);
  }

  @Then("^the user is redirected to the home page \"([^\"]*)\"$")
  public void the_user_is_redirected_to_the_home_page(String url) throws Throwable {
    checkRedirection(ft_demo_website_url + url);
  }

  @Given("^the user is on the licence page \"([^\"]*)\"$")
  public void the_user_is_on_the_licence_page(String url) throws Throwable {
    loadPage(ft_demo_website_url + url);
    assertPageTitle("Demo Website - Terms and Conditions", url);
  }

  @When("^the user clicks the checkbox for agreement \"([^\"]*)\"$")
  public void the_user_clicks_the_checkbox_for_agreement(String agreementText) throws Throwable {
    clickOnAgreementLic(agreementText);
  }

  @When("^the user clicks the button \"([^\"]*)\"$")
  public void the_user_clicks_the_button(String buttonName) throws Throwable {
    clickOnSubmit(buttonName);
  }

  @Then("^the user sees the error message \"([^\"]*)\"$")
  public void the_user_sees_the_error_message(String errorMessage) throws Throwable {
    seeErrorMessage(errorMessage);
  }

  @Given("^the web server is running under PHP (\\d+)$")
  public void the_web_server_is_running_under_PHP(int arg1) throws Throwable {
    checkPHPVersion();
  }

  @Given("^the user is authenticated with the username \"([^\"]*)\" and the password \"([^\"]*)\"$")
  public void the_user_is_authenticated_with_the_username_and_the_password(String username, String password) throws Throwable {
    athentifyUser(username, password);
  }

  @When("^the user selects the quantity \"([^\"]*)\"$")
  public void the_user_selects_the_quantity(String qt) throws Throwable {
    shoppingSelectQt(qt);
  }

  @Then("^the user sees the correct Total price without VAT$")
  public void the_user_sees_the_correct_Total_price_without_VAT() throws Throwable {
    checkTotalPriceWoVat();
  }

  @Then("^the user sees the correct VAT value$")
  public void the_user_sees_the_correct_VAT_value() throws Throwable {
    checkVatValue();
  }

  @Then("^the user sees the correct Total price$")
  public void the_user_sees_the_correct_Total_price() throws Throwable {
    checkTotalPrice();
  }

  @Given("^the user is requested to login$")
  public void the_user_is_requested_to_login() throws Throwable {
    userRedirectedToLoginPage();
  }

  @When("^the user types \"([^\"]*)\" in username field$")
  public void the_user_types_in_username_field(String username) throws Throwable {
    userEnterUsername(username);
  }

  @When("^the user types \"([^\"]*)\" in the password field$")
  public void the_user_types_in_the_password_field(String password) throws Throwable {
    userenterPassword(password);
  }

  @Given("^reset book list schema and data$")
  public void reset_book_list_schema_and_data() throws Throwable {
    books.createTable();
  }

  @Given("^the system is inserting a new book using post \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
  public void the_system_is_inserting_a_new_book_using_post(String title, String author, String edition) throws Throwable {
    books.addBookByPost(title, author, edition);
  }

  @Then("^the system is displaying the list of book containing \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
  public void the_system_is_displaying_the_list_of_book_containing(String title, String author, String edition) throws Throwable {
    books.checkBookInList(title, author, edition);
  }

  @Given("^the system is inserting a new book using get \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
  public void the_system_is_inserting_a_new_book_using_get(String title, String author, String edition) throws Throwable {
    books.addBookByGet(title, author, edition);
  }

}
