Feature: Login and logout for different type of user


  @ImdUserMgt
  Scenario: Login with a registered user with a wrong password
    When the customer with "imd-rt@yopmail.com" and password "wrong-password" logs in on the LoginPage
    Then the customer gets a error message "Wrong username and password"



