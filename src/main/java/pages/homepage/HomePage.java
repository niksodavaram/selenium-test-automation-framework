package pages.homepage;

import base.DriverFactory;
import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.pagecomponents.CartPage;
import pages.pagecomponents.ContactPage;
import pages.pagecomponents.LoginPage;
import pages.pagecomponents.ShopPage;

import java.time.Duration;

/**
 * This is a homepage class that follows single responsibility principle (SRP) design pattern
 * The main function of this page is to redirect the user to different page components;
 * (1) ContactPage
 * (2) ShopPage
 * (3) LoginPage
 * (4) CartPage
 * (5) HomePage
 * (6) Start Shopping
 */
public class HomePage extends TestBase {

    //Constructor


    //locator
    By header = By.cssSelector(".navbar-inner .container-fluid");

    By contactLnk = By.cssSelector("li#nav-contact > a");

    By shopLnk=By.cssSelector("li#nav-shop > a");

    By cartLnk=By.cssSelector("li#nav-cart > a");

    //Methods for entering different page objects on the homePage;


    //Method for Clicking ContactPage
    public ContactPage clickContactPage() {
        DriverFactory.getInstance().getDriverThreadLocal().findElement(contactLnk).click();
        new WebDriverWait(DriverFactory.getInstance().getDriverThreadLocal(), Duration.ofSeconds(20))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(contactLnk)).click();
        return new ContactPage(DriverFactory.getInstance().getDriverThreadLocal(), header);
    }

    //Method for clicking ShopPage
    public ShopPage clickShopPage() {
        new WebDriverWait(DriverFactory.getInstance().getDriverThreadLocal(), Duration.ofSeconds(20))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(shopLnk)).click();
        return new ShopPage(DriverFactory.getInstance().getDriverThreadLocal(), header);
    }

    //Method for clicking CartPage
    public CartPage clickCartPage() {
        new WebDriverWait(DriverFactory.getInstance().getDriverThreadLocal(), Duration.ofSeconds(20))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(cartLnk)).click();
        return new CartPage(DriverFactory.getInstance().getDriverThreadLocal(), header);
    }

    public LoginPage clickLoginPage() {
        return new LoginPage(DriverFactory.getInstance().getDriverThreadLocal(), header);
    }
}


