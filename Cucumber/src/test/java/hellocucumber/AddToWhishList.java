package opencart;

import io.cucumber.java.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddToWishlist {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void initSession(String webDriver, String driverPath) {
        System.setProperty(webDriver, driverPath);

        // Initialize WebDriver and WebDriverWait
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // Launch OpenCart site and maximize window
        driver.get("http://localhost/OpenCartFile/");
        driver.manage().window().setPosition(new Point(700, 5));

        System.out.println("Driver initialized. Page title: " + driver.getTitle());
    }

    public void loginToAccount(String username, String password) {
        // Navigate to login page
        driver.findElement(By.xpath("//*[@id='top']/div[1]/div[2]/ul[1]/li[2]/div[1]/a[1]/span[1]")).click();
        driver.findElement(By.xpath("//*[@id='top']/div[1]/div[2]/ul[1]/li[2]/div[1]/ul[1]/li[2]/a[1]")).click();

        // Enter login credentials and submit
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-email']"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-password']"))).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='form-login']/div/button[1]")).click();

        System.out.println("User logged in successfully.");
    }

    public void searchAndAddToWishlist(String productName, int quantity) {
        // Search for the product
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div[1]/input[1]"))).sendKeys(productName);
        driver.findElement(By.xpath("//*[@id='search']/button[1]")).click();

        // Select the product
        driver.findElement(By.xpath("//*[@id='product-list']/div/div/div/a/img[1]")).click();

        // Set quantity
        WebElement quantityField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-quantity']")));
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(quantity));

        // Add to wishlist
        driver.findElement(By.xpath("//*[@id='button-wishlist']")).click();
        System.out.println(productName + " added to wishlist with quantity: " + quantity);

        // Wait briefly for the wishlist update
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void verifyProductInWishlist(String productName, int expectedQuantity) {
        // Navigate to wishlist page
        driver.findElement(By.xpath("//*[@id='wishlist-total']")).click();

        // Locate product details in the wishlist
        WebElement wishlistProduct = driver.findElement(By.xpath("//a[contains(text(),'" + productName + "')]"));
        WebElement wishlistQuantity = driver.findElement(By.xpath("//input[contains(@name, 'quantity')]"));

        // Validate product details and quantity
        assertEquals(productName, wishlistProduct.getText(), "Product name mismatch in wishlist.");
        assertEquals(String.valueOf(expectedQuantity), wishlistQuantity.getAttribute("value"), "Product quantity mismatch in wishlist.");

        System.out.println("Wishlist verified: " + productName + " with quantity: " + expectedQuantity);
    }

    public void closeSession() {
        // Close the browser session
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Browser session closed.");
    }
}
