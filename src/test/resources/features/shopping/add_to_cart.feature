Feature: The customer adds to cart some articles

  @FTShopping @Id005
  Scenario Outline: Add to cart - Happy Path
    Given the customer is on the shopping page
    When the customer clicks on the buy button of the product id "<ProductId>"
    Then the customer sees the product "<ProductId>" id the shopping cart
    When the customer selects the quantity "<Quantity>"
    Then the customer sees the correct Total price without VAT
    And the customer sees the correct VAT value
    And the customer sees the correct Total price
    When the customer clicks the button "Done"
    Then the customer is redirected to the home page
    Examples:
      | ProductId | Quantity |
      | 5214      | 2        |
      | 5656      | 4        |
      | 5214      | 4        |