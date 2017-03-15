@Project=DemoWebsite @Feature=TermsAndConditions @Customer=Ziridis @TestType=Selenium @ProjectId=ft1 @TestSuite=TBD
Feature: Read and agree the Terms and Conditions

  Background:
    Given the web server is running under PHP "7.0.16"
    And the user is authenticated with the username "test" and the password "test"

  @Id=A.1.1
  Scenario: Accept Terms and Conditions - Happy Path
    Given the user is on the licence page "licence.html"
    When the user clicks the checkbox for agreement "I agree the Terms and Conditions"
    And the user clicks the button "Submit!"
    Then the user is redirected to the home page "?MyCheckbox=Agree"

  @Id=A.1.2
  Scenario: Accept Terms and Conditions - User forgot to check the agreement
    Given the user is on the licence page "licence.html"
    When the user clicks the button "Submit!"
    Then the user sees the error message "You need to agree the Terms and Conditions"
    When the user clicks the checkbox for agreement "I agree the Terms and Conditions"
    And the user clicks the button "Submit!"
    Then the user is redirected to the home page "?MyCheckbox=Agree"
