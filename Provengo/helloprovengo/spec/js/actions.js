/* @provengo summon selenium */
//@provengo summon textassertion
// @provengo summon ctrl

function adminLogin(session) {
  session.writeText(xpaths.admin.usernameField, 'admin'); // Adjust for your admin credentials
  session.writeText(xpaths.admin.passwordField, '1234'); // Adjust for your admin credentials
  session.click(xpaths.admin.loginButton);
}

// Function to set product quantity
function setProductQuantity(session, data) {
  try {
    bp.log.info("Navigating to Catalog > Products...");
    session.waitForClickability(xpaths.admin.catalogMenu, 5000);
    session.click(xpaths.admin.catalogMenu);
    session.waitForClickability(xpaths.admin.productsSubmenu, 5000);
    session.click(xpaths.admin.productsSubmenu); // Navigate to Products

    bp.log.info("Navigated to Products page.");
    bp.log.info("Opening first product for editing...");
    session.waitForClickability(xpaths.admin.firstProductEdit, 5000);
    session.click(xpaths.admin.firstProductEdit); // Click Edit for the first product

    bp.log.info("Switching to Data tab...");
    session.waitForClickability(xpaths.admin.dataTab, 5000);
    session.click(xpaths.admin.dataTab); // Navigate to Data

    bp.log.info("Setting product quantity to: " + data.quantity);
    session.writeText(xpaths.admin.productQuantityField, data.quantity, true); // Clear before writing
    session.writeText(xpaths.admin.productQuantityField, "\n"); // Press Enter key - save the value
    bp.log.info("Product quantity updated successfully.");

  } catch (error) {
    bp.log.warn("Failed to update product quantity: " + error.message);
    throw error;
  }
}

function registerUser(session, userData) {
  bp.log.info("Registering a new user...");

  // Click the my account button
  bp.log.info("Clicking the my account button...");
  session.click(xpaths.user.myAccountButton);

  // Click the registration link
  bp.log.info("Navigating to the registration page...");
  session.click(xpaths.user.registerLink);

  // seleniumSession.waitForVisibility(xpaths.user.firstnameField, 10000);

  // Fill in the first name
  bp.log.info(`Entering first name: ${userData.firstname}`);
  session.writeText(xpaths.user.firstnameField, userData.firstname);

  // Fill in the last name
  bp.log.info(`Entering last name: ${userData.lastname}`);
  session.writeText(xpaths.user.lastnameField, userData.lastname);

  // Fill in the email
  bp.log.info(`Entering email: ${userData.email}`);
  session.writeText(xpaths.user.emailField, userData.email);

  // Fill in the password
  bp.log.info("Entering password...");
  session.writeText(xpaths.user.passwordField, userData.password + "\uE00F");

  // Agree to terms and conditions
  bp.log.info("Ensuring the 'Agree to Terms and Conditions' checkbox is visible...");
  session.click(xpaths.user.agreeCheckbox);

  // Submit the registration form
  bp.log.info("Submitting the registration form...");
  session.click(xpaths.user.continueButton);

  session.waitForVisibility(xpaths.user.continueAfterSuccessButton, 5000); // Wait for the button to appear

  // Wait for success message
  bp.log.info("Pressing on continue bottom after registration...");
  session.click(xpaths.user.continueAfterSuccessButton);

  bp.log.info("User registration successful!");
}

function addToWishlist(session, data) {
  bp.log.info("Adding product to wishlist...");

  // Press the Desktop category button
  bp.log.info("Navigating to the Desktop category...");
  session.click(xpaths.wishlist.DesktopButton);

  // Press the Mac button
  bp.log.info("Navigating to the Mac category...");
  session.click(xpaths.wishlist.macButton);

  bp.log.info("Enter to the Mac product...");
  session.click(xpaths.wishlist.macEnterToProductButton);

  session.click(xpaths.wishlist.addTowishListButton);
  bp.log.info("Product successfully added to the wishlist!");

}
