@Project=DemoWebsite @Feature=BookList @Customer=FanaticalTest @TestType=RestAssured
Feature: Book List

  Background:
    Given reset book list schema and data

  @Id=A.4.1
  Scenario Outline: Happy Path - insert book using post
    Given the system is inserting a new book using post "<Title>" "<Author>" "<Edition>"
    Then the system is displaying the list of book containing "<Title>" "<Author>" "<Edition>"
    Examples:
      | Title                | Author   | Edition    |
      | Toto                 | Tata     | Atlas Sud  |
      | Selenium for Dummies | John Doe | Guntenberg |

  @Id=A.4.2
  Scenario Outline: Happy Path - insert book using get
    Given the system is inserting a new book using get "<Title>" "<Author>" "<Edition>"
    Then the system is displaying the list of book containing "<Title>" "<Author>" "<Edition>"
    Examples:
      | Title                | Author   | Edition    |
      | Toto                 | Tata     | Atlas Sud  |
      | Selenium for Dummies | John Doe | Guntenberg |

