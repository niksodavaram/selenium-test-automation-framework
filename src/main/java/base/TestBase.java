package base;

import org.testng.annotations.*;
import reportgenerator.ExtentFactory;
import util.YamlConfigLoader;

import java.sql.Driver;
import java.time.Duration;

public class TestBase {
    BrowserFactory browserFactory = new BrowserFactory();

    @BeforeMethod
    @Parameters({"browser", "isRemote", "platformName"})
    public void launchBrowser(@Optional("chrome") String browser,
                              @Optional("false") boolean isRemote,
                              @Optional("Linux") String platformName) throws Exception {

        String url = YamlConfigLoader.getUrl();

        if (isRemote) {
            DriverFactory.getInstance().setDriverThreadLocal(browserFactory.createBrowserInstance(browser, true, platformName));
        } else {
            DriverFactory.getInstance().setDriverThreadLocal(browserFactory.createBrowserInstance(browser, false, platformName));
        }

        // Maximize the browser and set timeouts
        DriverFactory.getInstance().getDriverThreadLocal().manage().window().maximize();

        DriverFactory.getInstance().getDriverThreadLocal().get(url);
        DriverFactory.getInstance().getDriverThreadLocal().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

    }

    @AfterMethod
    public void tearDown() {
        // Quit the browser and remove the WebDriver Instance
        DriverFactory.getInstance().quitThreadLocal();

        // Remove the ExtentTest object for the current thread
        ExtentFactory.getInstance().removeExtentObject();
    }

}

