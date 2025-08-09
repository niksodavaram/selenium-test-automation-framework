package pages.pagecomponents;

import abstractcomponents.*;
import annotations.Page;
import base.DriverFactory;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import reportgenerator.ExtentFactory;

import java.util.ArrayList;
import java.util.function.Consumer;

@Page
/**
 * This is contact page. All the locators, attributes and methods related to this page are declared here.
 * It extends Abstract Components abstract class, which has all the custom action methods and a custom findElement
 * that searches for elements in a specific section.
 */
public class ContactPage extends AbstractComponents implements
        ErrorMessageValidation, SuccessMessageValidation, ErrorMessageValidationWithInvalidData{

    //Constructor
    public ContactPage(WebDriver driverThreadLocal, By header) {
        super(DriverFactory.getInstance().getDriverThreadLocal(), header);
    }

    //Locators

    By contactLnk = By.cssSelector("li#nav-contact > a");

    By submitBtn = By.cssSelector(".btn.btn-contact.btn-primary");//Locator for submit button
    By enterForeName = By.id("forename"); //Locator for sending keys into ForeName box

    By enterEmail = By.id("email"); //Locator for sending keys into email box

    By enterMessage = By.id("message"); //Locator for sending keys into message box

    //Locator for validating error message that pops up on the header section when mandatory details are not populated
    By errorHeaderMessage =
            By.xpath("//div[@class='alert alert-error ng-scope']");

    //Locator for validating error message that pops up next to ForeName
    By errorForeNameMessage =
            By.id("forename-err");
    //Locator for validating error message that pops up next to the Email
    By errorEmailMessage = By.id("email-err");
    //Locator for validating error message that pops up next to message
    By errorMessage = By.id("message-err");

    //Locator for validating success message alert after contact form submission was successful
    By successMessage=By.cssSelector(".alert.alert-success");

    //Locator for clicking back button
    By backBtn=By.xpath("//a[@class='btn']");

    //Locator for validating the
    By headerErrorMessage=By.cssSelector("div#header-message > .alert.alert-error.ng-scope");

    //Locator for validating the change in the header message when mandatory details are populated
    By headerSuccessMessage=By.cssSelector("div#header-message");



    @Override
    public void validatingErrorMessages() throws InterruptedException {

        //click(contactLnk,"Accessing Contact Page",30);

        //Clicking the submit button without populating the mandatory fields on the contact form

        click(submitBtn,"Submitted without populating mandatory fields", 30);

        /**Validate all the error messages that pop up using testNG Assert methods*/

        //Using ArrayList to store all the expected error messages
        ArrayList<String> expectedErrorMessages = new ArrayList<String>();
        expectedErrorMessages.add("We welcome your feedback - but we won't get it unless you complete the form correctly.");
        expectedErrorMessages.add("Forename is required");
        expectedErrorMessages.add("Email is required");
        expectedErrorMessages.add("Message is required");

        //Logging all the information using ExtentFactory (I don't usually prefer log4J)
        ExtentFactory.getInstance().getExtent()
                .log(Status.INFO, "The expected error messages are as follows: " + expectedErrorMessages);

        //using ArrayList to store all the actual error messages that pop up as a result of not populating the fields
        ArrayList<String> actualErrorMessages = new ArrayList<String>();

        WebElement errorMessage1 = DriverFactory.getInstance().getDriverThreadLocal().findElement(errorHeaderMessage);
        WebElement errorMessage2 = DriverFactory.getInstance().getDriverThreadLocal().findElement(errorForeNameMessage);
        WebElement errorMessage3 = DriverFactory.getInstance().getDriverThreadLocal().findElement(errorEmailMessage);
        WebElement errorMessage4 = DriverFactory.getInstance().getDriverThreadLocal().findElement(errorMessage);
        if (errorMessage1.isDisplayed()) {
            actualErrorMessages.add(errorMessage1.getText());
        }
        if (errorMessage2.isDisplayed()) {
            actualErrorMessages.add(errorMessage2.getText());
        }
        if (errorMessage3.isDisplayed()) {
            actualErrorMessages.add(errorMessage3.getText());
        }
        if (errorMessage4.isDisplayed()) {
            actualErrorMessages.add(errorMessage4.getText());
        }

        //using try catch block to validate the expected and actual messages are same using AssertEquals method

        try {
            Assert.assertEquals(expectedErrorMessages, actualErrorMessages);
            ExtentFactory.getInstance().getExtent()
                    .log(Status.PASS, "All Error Messages logged earlier are Validated");
        } catch (Exception e) {
            e.printStackTrace();
            ExtentFactory.getInstance().getExtent()
                    .log(Status.FAIL, "Error Messages" + "We welcome your feedback - but we won't get it unless you +" +
                            "complete the form correctly." + "Validation failed due to: " + e);
        }

        //Validating the error message on the header section that is required to verify whether it changes when fields
        //are populated in the second test case.
        String
                expectedHeaderErrorMessage=
                "We welcome your feedback - but we won't get it unless you complete the form correctly.";


        WebElement headerErrorMessage=DriverFactory.getInstance().getDriverThreadLocal()
                        .findElement(errorHeaderMessage);
        if(headerErrorMessage.isDisplayed()){

        }

        String actualErrorHeaderMessage=headerErrorMessage.getText();

        try{
            Assert.assertEquals(expectedHeaderErrorMessage,actualErrorHeaderMessage);
            ExtentFactory.getInstance().getExtent().log(Status.PASS,"Header Error Message change " +
                    "WWe welcome your feedback - but we won't get it unless you complete the form correctly."+ "validated");
        }catch(Exception e){
            ExtentFactory.getInstance().getExtent().log(Status.FAIL,"Header Error message change couldn't be validated");
        }
        //Refreshing the contact page for validating the error messages are gone using succeeding overloaded method
       refresh();

    }

    @Override
    public void validatingErrorMessagesGone() throws InterruptedException {

        /**After refresh Now populate the mandatory fields with information*/

        //Sending keys into ForeName box using custom sendKeys method
        sendKeys(enterForeName, "ForeName entered successfully", "Nireekshan", 20);
        Thread.sleep(2000);
        //Sending keys into Email box using custom sendKeys method
        sendKeys(enterEmail, "Email entered successfully", "n.kumar9@outlook.com", 20);
        Thread.sleep(2000);
        //Sending keys into Message box using custom sendKeys method
        sendKeys(enterMessage, "Message Entered successfully", "Shopping is amazing", 20);
        Thread.sleep(2000);

        //Validating the header message change after populating the details
        String expectedHeaderMessageChange="We welcome your feedback - tell it how it is.";
        WebElement changeInHeaderMessage=DriverFactory.getInstance().getDriverThreadLocal().findElement(headerSuccessMessage);
        if(changeInHeaderMessage.isDisplayed()){

        }
        String actualChangeInHeaderMessage=changeInHeaderMessage.getText();

        try{
            Assert.assertEquals(expectedHeaderMessageChange,actualChangeInHeaderMessage);
            ExtentFactory.getInstance().getExtent().log(Status.PASS,"Error message" + "We welcome your feedback - tell it how it is." +
                    "We welcome your feedback - tell it how it is." + "validated");
        }catch(Exception e){
            ExtentFactory.getInstance().getExtent().log(Status.FAIL,"Header error message couldn't be Validated");
        }
        click(submitBtn, "Submit Button Clicked Successfully", 20);
        Thread.sleep(2000);



    }
    @Override
    public void validateSuccessMessage(String fName, String email, String message) throws InterruptedException {

        /** Validating the success Message that pops up after successful submission of contact form
         * by parametrizing the entries into the mandatory fields
         * Data is available externally in Excel file located in the resource directory
         * ReadExcel file class will help to parse the data and pass it on to the Data Provider class
         * The getTestData method in DataProvider method assists in passing the data to the test case
         * */

        sendKeys(enterForeName, "ForeName entered successfully", fName, 20);
        Thread.sleep(1000);
        sendKeys(enterEmail, "Email entered successfully", email, 20);
        Thread.sleep(1000);
        sendKeys(enterMessage, "Message Entered successfully", message, 20);
        Thread.sleep(1000);
        click(submitBtn, "Submit Button Clicked Successfully", 50);
        Thread.sleep(1000);

        //Asserting the success message
        String expectedSuccessMessage = "Thanks "  + fName + ", we appreciate your feedback.";
        WebElement successMessage1 = DriverFactory.getInstance().getDriverThreadLocal().findElement(successMessage);
        if (successMessage1.isDisplayed()) {
        }
        String actualSuccessMessage = successMessage1.getText();

        try {
            Assert.assertEquals(expectedSuccessMessage, actualSuccessMessage);
            ExtentFactory.getInstance().getExtent()
                    .log(Status.PASS, "Success message Thanks by fName, we appreciate your feedback. ");
        } catch (Exception e) {
            e.printStackTrace();
            ExtentFactory.getInstance().getExtent()
                    .log(Status.FAIL, "Success Message couldn't be validated: " + e);
        }

        //clicking back button
        click(backBtn,"back button clicked successfully",30);

    }


    @Override
    public void validateErrorMessageWithInvalidateData() throws InterruptedException {
        /**
         * Validating the error messages as a result of populating inValid Data*/
        //click(contactLnk,"Accessing Contact Page",30);

        sendKeys(enterForeName, "ForeName entered successfully", "T67G", 20);
        Thread.sleep(2000);
        sendKeys(enterEmail, "Email entered successfully", "asd", 20);
        Thread.sleep(2000);
        String expectedEmailErrorMessage="Please enter a valid email";

        WebElement emailError=DriverFactory.getInstance().getDriverThreadLocal().findElement(errorEmailMessage);
        if(emailError.isDisplayed()){
        }
        String actualEmailErrorMessage=emailError.getText();

        try{
            Assert.assertEquals(expectedEmailErrorMessage,actualEmailErrorMessage);
            ExtentFactory.getInstance().getExtent().log(Status.PASS,"Error Message validated due to not entering a valid email");
        }catch (Exception e){
            e.printStackTrace();
        }
        sendKeys(enterMessage, "Message Entered successfully", "Shopping is amazing", 20);
        Thread.sleep(2000);
        click(submitBtn, "Submit Button Clicked Successfully", 20);
        Thread.sleep(2000);

    }



}
