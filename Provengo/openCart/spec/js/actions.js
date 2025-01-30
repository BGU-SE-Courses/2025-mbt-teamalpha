/* @provengo summon selenium */
//@provengo summon textassertion
// @provengo summon ctrl

// Function to log in as an admin
function adminLogin(session) {
  session.writeText(xpaths.admin.usernameField, 'admin'); // Adjust for your admin credentials
  session.writeText(xpaths.admin.passwordField, '1234'); // Adjust for your admin credentials
  session.click(xpaths.admin.loginButton);
}

// Function to set product quantity
function setProductQuantity(session, data) {
  try {
    bp.log.info("Navigating to Catalog > Products...");
    session.scrollToElement(xpaths.admin.catalogMenu);
    session.waitForClickability(xpaths.admin.catalogMenu, 5000);
    session.click(xpaths.admin.catalogMenu);
    session.waitForClickability(xpaths.admin.productsSubmenu, 5000);
    session.click(xpaths.admin.productsSubmenu);

    bp.log.info("Opening first product for editing...");
    session.waitForClickability(xpaths.admin.firstProductEdit, 5000);
    session.click(xpaths.admin.firstProductEdit);

    bp.log.info("Switching to Data tab...");
    session.waitForClickability(xpaths.admin.dataTab, 5000);
    session.click(xpaths.admin.dataTab);

    bp.log.info("Setting product quantity to: " + data.quantity);
    session.writeText(xpaths.admin.productQuantityField, data.quantity, true);
    session.writeText(xpaths.admin.productQuantityField, "\n");
    bp.log.info("Product quantity updated successfully.");

  } catch (error) {
    bp.log.warn("Failed to update product quantity: " + error.message);
    throw error;
  }
}

function loginUser(session, userData){
  bp.log.info("Login a user...");

  session.click(xpaths.user.myAccountButton);
  session.click(xpaths.user.loginLink);

  session.writeText(xpaths.user.emailField, userData.email);
  session.writeText(xpaths.user.passwordField, userData.password);

  session.scrollToElement(xpaths.user.loginCheckBox);
  session.click(xpaths.user.loginCheckBox);

  bp.log.info("User login successful!");
}

// Function to add a product to the wishlist
function addToWishlist(session) {
  bp.log.info("Adding product to wishlist...");
  session.scrollToElement(xpaths.wishlist.DesktopButton);
  session.waitForVisibility(xpaths.wishlist.DesktopButton, 1000);
  session.click(xpaths.wishlist.DesktopButton);

  session.waitForVisibility(xpaths.wishlist.showAllDesktopButton, 1000);
  session.click(xpaths.wishlist.showAllDesktopButton);

  session.waitForVisibility(xpaths.wishlist.macButton, 5000);
  session.scrollToElement(xpaths.wishlist.macButton);
  session.click(xpaths.wishlist.macButton);

  session.scrollToElement(xpaths.wishlist.addTowishListButton);
  session.waitForVisibility(xpaths.wishlist.addTowishListButton, 5000);
  session.click(xpaths.wishlist.addTowishListButton);

  session.waitForVisibility(xpaths.wishlist.exitSuccessWindow, 5000);
  session.click(xpaths.wishlist.exitSuccessWindow);
  bp.log.info("Product successfully added to the wishlist!");
}

// Function to check wishlist stock status
function checkWishlistStockStatus(session, expectedStatus) {
  bp.log.info("Checking product stock status in wishlist...");

  // Wait for the wishlist link to be visible before clicking
  session.scrollToElement(xpaths.wishlist.seeWishList);
  session.waitForVisibility(xpaths.wishlist.seeWishList, 10000);
  session.click(xpaths.wishlist.seeWishList);

  // Assert the expected stock status
  session.assertText(xpaths.wishlist.stockStatusField, expectedStatus);
}

function removeFromWishlist(session)
{
  bp.log.info("remove from wishlist...");
  session.click(xpaths.wishlist.removeElement);
}

