package pages.pagecomponents;

import abstractcomponents.AbstractComponents;
import annotations.Page;
import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Page
public class ShopPage extends AbstractComponents {

    //Constructor

    public ShopPage(WebDriver driverThreadLocal, By header) {
        super(DriverFactory.getInstance().getDriverThreadLocal(), header);

    }

    //Locators
    By header = By.cssSelector(".navbar-inner .container-fluid");
    By shopLnk=By.cssSelector("li#nav-shop > a");

    By buyFluffyBunnyBtn=By.cssSelector("li:nth-of-type(4) .btn.btn-success");

    By buyFunnyCowBtn=By.cssSelector("li:nth-of-type(6) .btn.btn-success");

    By cart=By.cssSelector("li#nav-cart > a");

    By funnyCowQty=By.xpath("//input[@value='2']");

    By fluffyBunnyQty=By.xpath("//input[@value='1']");


    public void shopItems() throws InterruptedException {
    click(shopLnk,"Accessing shop Link", 30);
    Thread.sleep(3000);
    click(buyFluffyBunnyBtn,"selecting Fluffy Bunny", 30);
    Thread.sleep(3000);
    click(buyFunnyCowBtn,"Selecting Funny Cow",30);
    Thread.sleep(3000);
    click(buyFunnyCowBtn,"selecting Funny Cow second time",30);
    Thread.sleep(3000);
    }

    //This method redirects to the cart page for further processing

    public CartPage getCartPage(){
        click(cart,"Accessing Cart Page", 30);
        return new CartPage(DriverFactory.getInstance().getDriverThreadLocal(), header);
    }
}


