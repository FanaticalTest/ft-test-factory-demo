@Project=DemoWebsite @Feature=Authentication @Customer=Ziridis @TestType=Selenium @ProjectId=ft1 @TestSuite=TBD
Feature: User authentication

  @Id=A.3.1
  Scenario: Happy path
    Given the user is requested to login
    When the user types "test" in username field
    And the user types "test" in the password field
    And the user clicks the button "Login"
    Then the user is redirected to the home page "?"

  @Id=A.3.2
  Scenario: Wrong credentials
    Given the user is requested to login
    When the user types "test-wrong" in username field
    And the user types "test-wrong" in the password field
    And the user clicks the button "Login"
    Then the user sees the error message "Wrong username and password"
