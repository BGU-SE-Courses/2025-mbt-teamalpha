/* @provengo summon selenium */
/*
 *  Common test data, project-wide constants, etc.
 */
// Export constants and paths for reuse
const URL = 'http://localhost/opencartsite'; // Replace with your OpenCart URL
const adminURL = 'http://localhost/opencartsite/admin-new';

const xpaths = {
  admin: {
    usernameField: '//*[@id="input-username"]',
    passwordField: '//*[@id="input-password"]',
    loginButton: '//*[@id="form-login"]/div[3]/button',
    catalogMenu: '//*[@id="menu-catalog"]/a',
    productsSubmenu: '//*[@id="collapse-1"]/li[2]/a',
    firstProductEdit: '//*[@id="form-product"]/div[1]/table/tbody/tr[1]/td[7]/div/a/i',
    dataTab: '//*[@id="form-product"]/ul/li[2]/a',
    productQuantityField: '//*[@id="input-quantity"]',
    saveButton: '//*[@id="content"]/div[1]/div/div/button/i'
  },
  user: {
    myAccountButton:'//div[1]/div[2]/ul[1]/li[2]/div[1]/a[1]',
    registerLink: '//li[2]/div[1]/ul[1]/li[1]/a[1]',
    firstnameField: '//*[@id="input-firstname"]',
    lastnameField: '//*[@id="input-lastname"]',
    emailField: '//*[@id="input-email"]',
    passwordField: '//*[@id="input-password"]',
    agreeCheckbox: '//form[1]/div[1]/div[1]/input[1]',
    continueButton: '//form[1]/div[1]/button[1]',
    continueAfterSuccessButton: '//div[2]/div[1]/div[1]/div[1]/a[1]',
    loginCheckBox: '//*[@id="form-login"]/div[3]/button',
    loginLink: '//*[@id="top"]/div/div[2]/ul/li[2]/div/ul/li[2]/a'
  },
  wishlist: {
    DesktopButton: '//*[@id="narbar-menu"]/ul/li[1]/a',
    showAllDesktopButton: '//*[@id="narbar-menu"]/ul/li[1]/div/a',
    macButton: '//*[@id="product-list"]/div[1]/div/div[2]/div/h4/a',
    addTowishListButton: '//*[@id="content"]/div[1]/div[2]/form/div/button[1]/i',
    seeWishList: '//*[@id="wishlist-total"]/span',
    stockStatusField: '//*[@id="wishlist"]/div/table/tbody/tr/td[4]',  // New XPath to check stock status
    exitSuccessWindow: '//*[@id="alert"]/div/button',
    removeElement: '//*[@id="wishlist"]/div/table/tbody/tr/td[6]/form/button[2]/i'
  }
};
