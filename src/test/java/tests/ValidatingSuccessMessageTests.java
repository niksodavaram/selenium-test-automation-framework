package tests;

import base.TestBase;
import com.aventstack.extentreports.Status;
import dto.SuccessMessageDTO;
import pages.homepage.HomePage;
import org.testng.annotations.Test;
import reportgenerator.ExtentFactory;

public class ValidatingSuccessMessageTests extends TestBase {
    private final SuccessMessageDTO data;

    public ValidatingSuccessMessageTests(SuccessMessageDTO data) {
        this.data = data;
    }

    @Test
    public void validateSuccessMessage() throws InterruptedException {
        ExtentFactory.getInstance().getExtent().log(Status.INFO, "Success Message Validating via Factory Started");
        HomePage homePage = new HomePage();
        ExtentFactory.getInstance().getExtent().log(Status.INFO, "HomePage instance has been instantiated");
        homePage.clickContactPage().validateSuccessMessage(data.username, data.email, data.message);
        ExtentFactory.getInstance().getExtent().log(Status.INFO, "Success Message Validating via Factory Ended");
    }
}
