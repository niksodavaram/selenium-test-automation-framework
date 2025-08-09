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

public class ValidatingErrorMessagesWithInvalidDataTest extends TestBase {

    @Framework(author={AuthorType.NIK}, category= {CategoryType.REGRESSION})
    @Test
    public void ValidateErrorMessagesOnContactPage() throws InterruptedException {
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Error Messages With Invalid Data Test Started ");
        HomePage homePage=new HomePage();
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"HomePage instance has been instantiated ");
        homePage.clickContactPage().validateErrorMessageWithInvalidateData();
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Validating Error Messages ");
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Error Messages With Invalid Data Test Ended ");

    }
}
