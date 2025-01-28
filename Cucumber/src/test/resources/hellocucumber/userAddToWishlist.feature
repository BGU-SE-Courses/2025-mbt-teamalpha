Feature: Adding products to wishlist with a specified quantity

  @UserAddToWishlist
  Scenario Outline: User adds a product to the wishlist with a specific quantity of x
    Given the user exists in the OpenCart database
    And the user is on the OpenCart homepage
    And the user is logged in to their account
    When the user navigates to a product page
    And the user selects a quantity of "<quantity>"
    And the user clicks on the "Add to Wishlist" button
    Then the product should be added to the wishlist with the specified quantity

    Examples:
      | quantity |
      |        1 |
