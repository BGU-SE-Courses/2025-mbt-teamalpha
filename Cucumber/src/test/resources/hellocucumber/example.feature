Feature: Adding products to wishlist with a specified quantity

  Scenario Outline: User adds a product to the wishlist with a specific quantity of x
    Given the user is on the OpenCart hompage
    And the user is logged in to their account
    When the user navigates to a product page
    And the user selects a quantity of "<quantity>"
    And the user clicks on the "Add to Wishlist" button
    Then the product should be added to the wishlist with the specified quantity
    And the wishlist should display the correct product details and quantity

    Examples:
      | quantity |
      | 1        |
      | 2        |
      | 5        |
      | 10       |