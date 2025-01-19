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
    modalCloseButton: '//*[@id="modal-security"]/div/div/div[1]/button',
    catalogMenu: '//*[@id="menu-catalog"]/a',
    productsSubmenu: '//*[@id="collapse-1"]/li[2]/a',
    firstProductEdit: '//*[@id="form-product"]/div[1]/table/tbody/tr[1]/td[7]/div/a/i',
    dataTab: '//*[@id="form-product"]/ul/li[2]/a',
    productQuantityField: '//*[@id="input-quantity"]',
    saveButton: '//*[@id="content"]/div[1]/div/div/button/i'
  },
  user: {
    myAccountButton:'//div[1]/div[2]/ul[1]/li[2]/div[1]/a[1]',
    registerLink: '//li[2]/div[1]/ul[1]/li[1]/a[1]', // Link to registration page
    firstnameField: '//*[@id="input-firstname"]',
    lastnameField: '//*[@id="input-lastname"]',
    emailField: '//*[@id="input-email"]',
    passwordField: '//*[@id="input-password"]',
    confirmPasswordField: '//*[@id="input-confirm"]',
    agreeCheckbox: '//form[1]/div[1]/div[1]/input[1]',
    continueButton: '//form[1]/div[1]/button[1]',
    continueAfterSuccessButton: '//div[2]/div[1]/div[1]/div[1]/a[1]',
  },
  wishlist: {
    DesktopButton: '//nav[1]/div[2]/ul[1]/li[1]/a[1]',
    macButton: '//li[1]/div[1]/div[1]/ul[1]/li[2]/a[1]',
    macEnterToProductButton: '//*[@id="product-list"]/div/div/div[1]/a/img',
    addTowishListButton: '//*[@id="content"]/div[1]/div[2]/form/div/button[1]',
    seeWishList: '//li[3]/a[1]/span[1]',
    productQuantityField: '//*[@id="input-quantity"]',
    amount: '10'
  }
};
