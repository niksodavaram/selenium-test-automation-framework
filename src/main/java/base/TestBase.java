package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.testng.annotations.*;
import reportgenerator.ExtentFactory;
import reportgenerator.ExtentManager;
import util.YamlConfigLoader;

import java.sql.Driver;
import java.time.Duration;

public class TestBase {
    BrowserFactory browserFactory = new BrowserFactory();

    // Singleton Holder pattern for ExtentReport ensures that a singleton object (ExtentReports) is created
    // in a thread-safe and lazy-initialized manner
    // The ExtentReportsHolder class is not loaded into memory until ExtentReportsHolder.extentReports is accessed
    // for the first time
    private static final class ExtentReportsHolder {
        public static final ExtentReports extentReports;

        static {
            try {
                extentReports = ExtentManager.getExtentReport();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @BeforeMethod
    @Parameters({"browser", "isRemote"})
    public void launchBrowser(String browser, boolean isRemote) throws Exception {

        String url = YamlConfigLoader.getUrl();

        if (isRemote) {
            DriverFactory.getInstance().setDriverThreadLocal(browserFactory.createBrowserInstance(browser, true));
        } else {
            DriverFactory.getInstance().setDriverThreadLocal(browserFactory.createBrowserInstance(browser, false));
        }

        // Maximize the browser and set timeouts
        DriverFactory.getInstance().getDriverThreadLocal().manage().window().maximize();

        DriverFactory.getInstance().getDriverThreadLocal().get(url);
        DriverFactory.getInstance().getDriverThreadLocal().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        // Ensure extentReports is initialized before creating ExtentTest
        if (ExtentReportsHolder.extentReports == null) {
            throw new IllegalStateException("ExtentReports object is not initialized. Check the @BeforeSuite method.");
        }
        // Create a new ExtentTest instance for the current test and set it in ExtentFactory
        // You need to initialize the ExtentTEst object (via ExtentFactory) in the TestBase because:
        // - Every test case needs its own ExtentTest instance for logging
        // - The TestBase class is responsible for setting up the environment for test execution
        ExtentTest extentTest = ExtentReportsHolder.extentReports.createTest(getClass().getName());
        ExtentFactory.getInstance().setExtentTestThreadLocal(extentTest);

    }

    @AfterMethod
    public void tearDown() {
        // Quit the browser and remove the WebDriver Instance
        DriverFactory.getInstance().quitThreadLocal();

        // Remove the ExtentTest object for the current thread
        ExtentFactory.getInstance().removeExtentObject();
    }

    @AfterSuite
    public void flushExtentReports() {
        // Flush the ExtentReports to finalize the report
        if (ExtentReportsHolder.extentReports != null) {
            ExtentReportsHolder.extentReports.flush();
        }
    }

}

