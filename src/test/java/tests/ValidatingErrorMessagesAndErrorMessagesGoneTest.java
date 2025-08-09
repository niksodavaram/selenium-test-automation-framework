package tests;

import annotations.Framework;
import base.TestBase;
import com.aventstack.extentreports.Status;
import enums.AuthorType;
import enums.CategoryType;
import pages.homepage.HomePage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reportgenerator.ExtentFactory;
import util.TestNGListeners;

@Listeners(TestNGListeners.class)

public class ValidatingErrorMessagesAndErrorMessagesGoneTest extends TestBase {

    @Framework(author={AuthorType.NIK}, category= {CategoryType.REGRESSION})
    @Test
    public void ValidateErrorMessagesOnContactPage() throws InterruptedException {
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Error Messages and Error Messages Gone Test Started ");
        HomePage homePage=new HomePage();
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"HomePage instance has been instantiated");
        homePage.clickContactPage().validatingErrorMessages();
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Validating Error Messages");
        homePage.clickContactPage().validatingErrorMessagesGone();
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Validating Error Messages Gone");
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Error Messages and Error Messages Gone Test Ended");

    }
}
