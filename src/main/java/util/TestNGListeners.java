package util;

import base.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reportgenerator.ExtentFactory;
import reportgenerator.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNGListeners implements ITestListener {
    private static ExtentReports report;

    @Override
    public void onStart(ITestContext context) {
        try {
            report = ExtentManager.getExtentReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Unique test name: class.method
        String testName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
        ExtentTest test = report.createTest(testName);

        // Assign categories (browser/platform) for filtering in the HTML report
        String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
        String platform = result.getTestContext().getCurrentXmlTest().getParameter("platformName");
        if (browser != null) test.assignCategory(browser);
        if (platform != null) test.assignCategory(platform);
        ExtentFactory.getInstance().setExtentTestThreadLocal(report.createTest(testName));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case: " + result.getMethod().getMethodName() + " is Passed.");
        ExtentFactory.getInstance().removeExtentObject();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case: " + result.getMethod().getMethodName() + " is Failed.");
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());

        // Screenshot on failure
        File src = ((TakesScreenshot) DriverFactory.getInstance().getDriverThreadLocal()).getScreenshotAs(OutputType.FILE);
        String actualDate = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + actualDate + ".jpeg";
        File dest = new File(screenshotPath);

        try {
            FileUtils.copyFile(src, dest);
            ExtentFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshotPath, "Test case failure screenshot");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExtentFactory.getInstance().removeExtentObject();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentFactory.getInstance().getExtent().log(Status.SKIP, "Test Case: " + result.getMethod().getMethodName() + " is skipped");
        ExtentFactory.getInstance().removeExtentObject();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case: " + result.getMethod().getMethodName() + " Failed but with success percentage");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case: " + result.getMethod().getMethodName() + " timeOut Exception: Element couldn't be found");
        ExtentFactory.getInstance().removeExtentObject();
    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();
        try {
            AllureReportGenerator.generateReport();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}