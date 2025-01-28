package hellocucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

public class adminSetQuantity {
    private WebDriver driver;
    private String expectedQuantity;
    private String originalQuantity;

    @Given("the admin is logged into the OpenCart admin panel")
    public void adminIsLoggedIn() {
        driver = new ChromeDriver();
        driver.get("http://localhost/opencartsite/admin-new");
        
        // Fill username
        WebElement usernameField = driver.findElement(By.xpath("//*[@id='input-username']"));
        usernameField.sendKeys("admin");
        
        // Fill password
        WebElement passwordField = driver.findElement(By.xpath("//*[@id='input-password']"));
        passwordField.sendKeys("1234");

        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click login button
        driver.findElement(By.xpath("//button[1]")).click();
    }

    @When("the admin navigates to the product list")
    public void navigateToProductList() {
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click catalog to open the menu
        driver.findElement(By.xpath("//*[@id='menu-catalog']/a[1]")).click();

        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click products button
        driver.findElement(By.xpath("//*[@id='collapse-1']/li[2]/a[1]")).click();
    }

    @When("the admin edits a product quantity to {string}")
    public void editProductQuantity(String quantity) {
        this.expectedQuantity = quantity;

        try {
            // Wait for 0.5 seconds
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the edit icon
        driver.findElement(By.xpath("//tr[1]/td[7]/div[1]/a[1]/i[1]")).click();
        
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click on the "Data" tab
        driver.findElement(By.xpath("//form[1]/ul[1]/li[2]/a[1]")).click();
        
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Scroll down until the quantity field is visible
        WebElement quantityField = driver.findElement(By.xpath("//*[@id='input-quantity']"));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 350);");
        
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Store the original quantity
        originalQuantity = quantityField.getAttribute("value");

        // Edit the quantity
        quantityField.clear();
        quantityField.sendKeys(quantity);
        
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Scroll to the top of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click the save button
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("the product quantity should be updated successfully")
    public void verifyQuantityUpdate() {
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify success message
        String successMessage = driver.findElement(By.xpath("//div[@id='alert']//div[contains(@class, 'alert alert-success alert-dismissible')]")).getText();
        Assertions.assertTrue(successMessage.contains("Success: You have modified products!"));

        // Click the "back" button
        driver.findElement(By.xpath("//div[1]/div[1]/a[1]")).click();
        
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify the quantity presented
        String displayedQuantity = driver.findElement(By.xpath("//tr[1]/td[6]/span[1]")).getText();
        Assertions.assertEquals(expectedQuantity, displayedQuantity);

        driver.quit();
    }

    @After("@AdminSetQuantity")
    public void resetProductQuantity() {
        driver = new ChromeDriver();
        driver.get("http://localhost/opencartsite/admin-new");
        
        // Fill username
        WebElement usernameField = driver.findElement(By.xpath("//*[@id='input-username']"));
        usernameField.sendKeys("admin");
        
        // Fill password
        WebElement passwordField = driver.findElement(By.xpath("//*[@id='input-password']"));
        passwordField.sendKeys("1234");

        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click login button
        driver.findElement(By.xpath("//button[1]")).click();

        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click catalog to open the menu
        driver.findElement(By.xpath("//*[@id='menu-catalog']/a[1]")).click();

        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click products button
        driver.findElement(By.xpath("//*[@id='collapse-1']/li[2]/a[1]")).click();

        // Click the edit icon
        driver.findElement(By.xpath("//tr[1]/td[7]/div[1]/a[1]/i[1]")).click();
        
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click on the "Data" tab
        driver.findElement(By.xpath("//form[1]/ul[1]/li[2]/a[1]")).click();
        
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Scroll down until the quantity field is visible
        WebElement quantityField = driver.findElement(By.xpath("//*[@id='input-quantity']"));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 350);");
        
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Edit the quantity back to the original value
        quantityField.clear();
        quantityField.sendKeys(originalQuantity);
        
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Scroll to the top of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        
        try {
            // Wait for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Click the save button
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.quit();
    }
}
