let eventOccurred = false;

bthread('Admin sets product quantity to 0', function () {
    let session;
    try {
        session = new SeleniumSession('adminSession', 'chrome');
        session.start(adminURL);

        adminLogin(session);

        bp.log.info('Setting product quantity to 0...');

        // Request to start setting product quantity and block user from checking it
        sync({
            request: Event("tryToSetProductQuantityToZero"),
            block: Event("checkQuantity")  // Prevents the user from checking until this completes
        });

        setProductQuantity(session, { product: 'Product Name', quantity: '0' });
        eventOccurred = true;
        // Emit event after successful update
        sync({ request: Event("productQuantityZero") });

        bp.log.info('Admin set product quantity to 0. Waiting for user to complete wishlist check...');

        // Wait for the user to complete the wishlist check
        sync({ waitFor: Event("wishlistCheckCompleted") });

        // Reload the product quantity to a higher number
        sync({ request: Event("reloadData") });
        session.writeText(xpaths.admin.productQuantityField, '999', true);
        session.writeText(xpaths.admin.productQuantityField, "\n");

    } catch (error) {
        bp.log.warn(`An error occurred in the admin session: ${error.message}`);
    } finally {
        bp.log.info('Closing admin session...');
        if (session) {
            session.close();
        }
    }
});


bthread('Register, Login, and Add Product to Wishlist', function () {
    let s;
    try {
        s = new SeleniumSession('userSession');
        s.start(URL);

        loginUser(s, {
            firstname: 'Test',
            lastname: 'User',
            email: 'aa@example.com',
            password: '123456',
        });
        addToWishlist(s);

        bp.log.info("Starting wishlist check...");

        // Prevent the admin from modifying the quantity while the user is checking it
        sync({
            request: Event("checkQuantity"),
            block: Event("tryToSetProductQuantityToZero")  // Blocks the admin from updating
        });

        if (eventOccurred) {
            bp.log.info("Admin updated product quantity, checking for empty stock...");
            checkWishlistStockStatus(s, ""); // Check for empty stock
        } else {
            bp.log.info("Product quantity was not updated, checking for 'In Stock'...");
            checkWishlistStockStatus(s, "In Stock"); // Check for "In Stock"
        }

        // Signal the admin that the wishlist check is complete
        sync({ request: Event("wishlistCheckCompleted") });

        // Wait for admin to reload data
        sync({ waitFor: Event("reloadData") });
        eventOccurred = false;
        removeFromWishlist(s);

    } catch (error) {
        bp.log.warn(`An error occurred during the user session: ${error.message}`);
    } finally {
        if (s) {
            bp.log.info("Closing user session...");
            s.close();
        }
    }
});

