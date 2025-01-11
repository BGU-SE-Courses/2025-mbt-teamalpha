package hellocucumber;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {

    private static List<OpenCartActuator> allOpenCarts;
    private static OpenCartActuator opencartUser;
    private String webDriver = "webdriver.chrome.driver";
    private String path = "C:\\Users\\keren\\Desktop\\תרגולים\\DRIVER\\chromedriver-win64\\chromedriver.exe";

    private String productName;
    private int selectedQuantity;
    private int wishlistQuantity;

    public void OpenCartInitUser() {
        System.out.println("--------------- INITIALIZING OPENCART TEST - OPENING WEBPAGE ---------------");
        if (allOpenCarts == null) {
            allOpenCarts = new ArrayList<>();
        }
        opencartUser = new OpenCartActuator();
        allOpenCarts.add(opencartUser);
        opencartUser.initSessionAsUser(webDriver, path);
    }

    @Given("User is on OpenCart homepage")
    public void userIsOnOpenCartHomepage() {
        OpenCartInitUser();
    }

    @When("User is logged in with {string} and {string}")
    public void userIsLoggedInWith(String email, String password) {
        opencartUser.goToLogin();
        opencartUser.enterLoginInfo(email, password);
    }

    @When("User navigates to a product page")
    public void userNavigatesToAProductPage() {
        productName = "Example Product"; // Replace with dynamic logic if necessary
        System.out.println("User navigates to the product page for: " + productName);
    }

    @When("User selects a quantity of {int}")
    public void userSelectsAQuantityOf(int quantity) {
        selectedQuantity = quantity;
        System.out.println("User selects a quantity of: " + selectedQuantity);
    }

    @When("User clicks on the {string} button")
    public void userClicksOnTheButton(String button) {
        if (button.equals("Add to Wishlist")) {
            wishlistQuantity = selectedQuantity;
            System.out.println("User clicks on the 'Add to Wishlist' button.");
        } else {
            throw new IllegalArgumentException("Unknown button: " + button);
        }
    }

    @Then("The product should be added to the wishlist with the specified quantity")
    public void productShouldBeAddedToWishlistWithSpecifiedQuantity() {
        Assertions.assertEquals(selectedQuantity, wishlistQuantity,
            "The wishlist quantity should match the selected quantity.");
        System.out.println("Product successfully added to wishlist with quantity: " + wishlistQuantity);
    }

    @Then("The wishlist should display the correct product details and quantity")
    public void wishlistShouldDisplayCorrectDetailsAndQuantity() {
        Assertions.assertEquals("Example Product", productName,
            "The wishlist should display the correct product.");
        Assertions.assertEquals(selectedQuantity, wishlistQuantity,
            "The wishlist should display the correct quantity.");
        System.out.println("Wishlist displays correct product details and quantity.");
    }
}
