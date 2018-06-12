Feature: Login and logout for different type of user


  @FTUserMgt @Id001 @FTDemoWebsite
  Scenario: Login with a registered user with a wrong password
    When the customer with "imd-rt@yopmail.com" and password "wrong-password" logs in on the LoginPage
    Then the customer gets a error message "Wrong username and password"

  @FTUserMgt @Id002 @FTDemoWebsite
  Scenario: Login with a registered user with a good password
    When the customer with "test" and password "test" logs in on the LoginPage
    Then the customer is redirected to the home page