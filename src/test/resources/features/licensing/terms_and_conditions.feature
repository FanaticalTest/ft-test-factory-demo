Feature: The customer need to accept the Terms and Conditions

  @FTLicensing @Id003 @FTDemoWebsite
  Scenario: The customer does not agree Terms and Conditions Page
    Given the customer arrives on the Terms and Conditions Page
    When the customer submits the form without agreeing the Terms and Conditions
    Then the customers sees the error message in Terms and Conditions


  @FTLicensing @Id004 @FTDemoWebsite
  Scenario: The customer agrees Terms and Conditions Page
    Given the customer arrives on the Terms and Conditions Page
    When the customer submits the form with agreeing the Terms and Conditions
    Then the customer is redirected to the home page