@Project=DemoWebsite @Feature=Shopping @Customer=FanaticalTest @TestType=Selenium
Feature: Shopping

  Background:
    Given the web server is running under PHP 7
    And the user is authenticated with the username "test" and the password "test"

  @Id=A.2.1
  Scenario Outline: Happy Path
    Given the user is on the shopping page "shopping.html"
    When the user clicks on the buy button of the product id "<ProductId>"
    Then the user sees the product "<ProductId>" id the shopping cart
    When the user selects the quantity "<Quantity>"
    Then the user sees the correct Total price without VAT
    And the user sees the correct VAT value
    And the user sees the correct Total price
    When the user clicks the button "Done"
    Then the user is redirected to the home page "index.html"
    Examples:
      | ProductId | Quantity |
      | 5214      | 2        |
      | 5656      | 4        |
      | 5214      | 4        |