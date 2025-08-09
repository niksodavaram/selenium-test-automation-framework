package tests;

import annotations.Framework;
import base.DriverFactory;
import base.TestBase;
import com.aventstack.extentreports.Status;
import enums.AuthorType;
import enums.CategoryType;
import pages.homepage.HomePage;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.pagecomponents.CartPage;
import reportgenerator.ExtentFactory;
import util.TestNGListeners;

@Listeners(TestNGListeners.class)

public class ValidatingShopAndCartPageTest extends TestBase {
    By header = By.cssSelector(".navbar-inner .container-fluid");

    @Framework(author={AuthorType.NIK}, category= {CategoryType.REGRESSION})
    @Test
    public void ValidateShopAndCartPage() throws InterruptedException {
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Shop and Cart Page validation Test Started ");
        HomePage homePage=new HomePage();
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"HomePage instance has been instantiated ");
        homePage.clickShopPage().shopItems();
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Validating Shopping page ");
        homePage.clickShopPage().getCartPage();
        CartPage cartPage=new CartPage(DriverFactory.getInstance().getDriverThreadLocal(), header);
        cartPage.checkCart();
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Validating Cart Items ");
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Shop and Cart Page validation Test Ended");

    }
}
