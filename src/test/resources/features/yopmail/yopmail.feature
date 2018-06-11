Feature: YopMail smoke test


  @ImdUserMgt
  Scenario: Smoke test - Check email on yopmail
    When the customer checks his email address "imd-rt"
    Then the customer sees his email interface with "imd-rt@yopmail.com" as label