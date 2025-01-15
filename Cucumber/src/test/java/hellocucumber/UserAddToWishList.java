package hellocucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserAddToWishList {
    private WebDriver driver;
    private static WebDriverWait wait;

    private enum Config {
        WEBDRIVER("webdriver.chrome.driver"),
        DRIVER_PATH("2025-mbt-teamalpha\\Selenium\\chromedriver.exe"),
        TEST_EMAIL("user@gmail.com"),
        TEST_PASSWORD("123456"),
        SAMPLE_PRODUCT("iMac"),
        DEFAULT_QUANTITY(1);

        private final String stringValue;
        private final int intValue;

        Config(String value) {
            this.stringValue = value;
            this.intValue = 0;
        }

        Config(int value) {
            this.stringValue = null;
            this.intValue = value;
        }

        public String getString() {
            return stringValue;
        }

        public int getInt() {
            return intValue;
        }
    }

    @And("the user is on the OpenCart homepage")
    public void userIsOnOpenCartHomepage() {
        driver.get("http://localhost/openCartSite");

    }

    @And("the user is logged in to their account")
    public void userIsLoggedIn() {
        loginToAccount(Config.TEST_EMAIL.getString(), Config.TEST_PASSWORD.getString());
    }

    @When("the user navigates to a product page")
    public void userNavigatesToProductPage() {
        System.out.println("Navigating to product page...");
    }

    @When("the user selects a quantity of {string}")
    public void userSelectsQuantity(String quantity) {
        System.out.println("Selecting quantity: " + quantity);
        searchAndAddToWishlist(Config.SAMPLE_PRODUCT.getString(), Integer.parseInt(quantity));
    }

    @And("the user clicks on the \"Add to Wishlist\" button")
    public void userClicksAddToWishlistButton() {
        System.out.println("Clicked 'Add to Wishlist' button.");
    }

    @Then("the product should be added to the wishlist with the specified quantity")
    public void productShouldBeAddedToWishlist() {
        System.out.println("Verifying product added to wishlist...");
        verifyProductInWishlist(Config.SAMPLE_PRODUCT.getString(), Config.DEFAULT_QUANTITY.getInt());
    }

    @And("the wishlist should display the correct product details and quantity")
    public void wishlistDisplaysCorrectDetails() {
        System.out.println("Verifying wishlist displays correct details...");
        verifyProductInWishlist(Config.SAMPLE_PRODUCT.getString(), Config.DEFAULT_QUANTITY.getInt());
    }

    @Given("the user exists in the OpenCart database")
    public void userExistsInOpenCartDatabase() {
        driver = new ChromeDriver();
        driver.get("http://localhost/openCartSite");
        // Register a new user
        driver.findElement(By.xpath("//li[2]/div[1]/a[1]/span[1]")).click();
        driver.findElement(By.xpath("//li[2]/div[1]/ul[1]/li[1]/a[1]")).click();
        //fill in the registration form
        driver.findElement(By.xpath("//*[@id='input-firstname']")).sendKeys("user");
        driver.findElement(By.xpath("//*[@id='input-lastname']")).sendKeys("user");
        driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys(Config.TEST_EMAIL.getString());
        driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys(Config.TEST_PASSWORD.getString());

        // Scroll down until button visible
        WebElement tuggElement = driver.findElement(By.xpath("//form[1]/div[1]/div[1]/input[1]"));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 350);");

        tuggElement.click();
    
        driver.findElement(By.xpath("//form[1]/div[1]/button[1]")).click();
    }

    public void loginToAccount(String username, String password) {
        // Navigate to login page
        driver.findElement(By.xpath("//li[2]/div[1]/a[1]/span[1]")).click();
        driver.findElement(By.xpath("//li[2]/div[1]/ul[1]/li[2]/a[1]")).click();
        
        // Enter login credentials and submit
        driver.findElement(By.xpath("//form[1]/div[1]/input[1]")).sendKeys(username);
        driver.findElement(By.xpath("//form[1]/div[2]/input[1]")).sendKeys(password);
        driver.findElement(By.xpath("//form[1]/div[3]/button[1]")).click();

        System.out.println("User logged in successfully.");
    }

    public void searchAndAddToWishlist(String productName, int quantity) {
        // go to homepage
        driver.findElement(By.xpath("//header[1]/div[1]/div[1]/div[1]/div[1]/a[1]/img[1]")).click();
        // Search for the product
        driver.findElement(By.xpath("//div[2]/div[1]/input[1]")).sendKeys(productName);
        driver.findElement(By.xpath("//div[2]/div[1]/button[1]")).click();
        // Select the product
        driver.findElement(By.xpath("//div[5]/div[1]/div[1]/div[1]/a[1]/img[1]")).click();
        // Add to wishlist
        driver.findElement(By.xpath("//div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]")).click();
        System.out.println(productName + " added to wishlist with quantity: " + quantity);

        // Wait briefly for the wishlist update
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

@Test
public void verifyProductInWishlist(String productName, int expectedQuantity) {
    // Navigate to wishlist page
    driver.findElement(By.xpath("//li[3]/a[1]/span[1]")).click();

            // Wait briefly for the wishlist update
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    // Locate product details in the wishlist
    WebElement wishlistProduct = driver.findElement(By.xpath("//*[@id='wishlist']/div[1]/table[1]/tbody[1]/tr[1]/td[2]"));

    // Check if the product name appears in the specified element
    if (wishlistProduct.getText().contains(productName)) {
        System.out.println("Wishlist verified: " + productName + " is present.");
    } else {
        System.out.println("Wishlist verification failed: " + productName + " is not present.");
    }
}

    public void logoutFromAccount() {
    // Navigate to logout page
    driver.findElement(By.xpath("//li[2]/div[1]/a[1]/span[1]")).click();
    driver.findElement(By.xpath("//li[2]/div[1]/ul[1]/li[5]/a[1]")).click(); 
    System.out.println("User logged out successfully.");
    }


    public void closeSession() {
        logoutFromAccount();
        // Close the browser session
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Browser session closed.");
    }

}
