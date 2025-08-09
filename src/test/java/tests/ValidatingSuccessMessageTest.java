package tests;

import annotations.Framework;
import base.TestBase;
import com.aventstack.extentreports.Status;
import dataprovider.DataProviderForReadingExcelFile;
import enums.AuthorType;
import enums.CategoryType;
import pages.homepage.HomePage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reportgenerator.ExtentFactory;
import util.TestNGListeners;

@Listeners(TestNGListeners.class)

public class ValidatingSuccessMessageTest extends TestBase {

    @Framework(author={AuthorType.NIK}, category= {CategoryType.REGRESSION})
    @Test(dataProviderClass = DataProviderForReadingExcelFile.class, dataProvider = "getTestData")
    public void ValidateErrorMessagesOnContactPage(String fName, String email, String message) throws InterruptedException {
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Success Message Validating via DataProvider Started ");
        HomePage homePage=new HomePage();
        ExtentFactory.getInstance().getExtent().log(Status.INFO,"HomePage instance has been instantiated ");
        homePage.clickContactPage().validateSuccessMessage(fName, email, message);

        ExtentFactory.getInstance().getExtent().log(Status.INFO,"Success Message Validating via DataProvider Ended ");

    }
}
