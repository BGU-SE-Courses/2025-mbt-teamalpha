Feature: Product Management

  @AdminSetQuantity
  Scenario: Admin sets product quantity to zero
    Given the admin is logged into the OpenCart admin panel
    When the admin navigates to the product list
    And the admin edits a product quantity to "0"
    Then the product quantity should be updated successfully
