package base;

import io.qameta.allure.Allure;
import org.testng.annotations.*;
import reportgenerator.ExtentFactory;
import util.YamlConfigLoader;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

public class TestBase {
    BrowserFactory browserFactory = new BrowserFactory();

    @BeforeMethod
    @Parameters({"browser", "isRemote", "platformName"})
    public void launchBrowser(@Optional("chrome") String browser,
                              @Optional("false") boolean isRemote,
                              @Optional("Linux") String platformName) throws Exception {

        AtomicReference<String> url = new AtomicReference<>(YamlConfigLoader.getUrl());

        if (isRemote) {
            DriverFactory.getInstance().setDriverThreadLocal(browserFactory.createBrowserInstance(browser, true, platformName));
        } else {
            DriverFactory.getInstance().setDriverThreadLocal(browserFactory.createBrowserInstance(browser, false, platformName));
        }

        // Maximize the browser and set timeouts
        DriverFactory.getInstance().getDriverThreadLocal().manage().window().maximize();

        DriverFactory.getInstance().getDriverThreadLocal().get(url.get());
        DriverFactory.getInstance().getDriverThreadLocal().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
//
//        if (browser.equalsIgnoreCase("chrome") && !isRemote) {
//            DevToolsFactory.initDevTools();
//
//            DevToolsFactory.enableNetworkMonitoring(
//                    requestWillBeSent -> {
//                        url.set(requestWillBeSent.getRequest().getUrl());
//                        String method = requestWillBeSent.getRequest().getMethod();
//                        String msg = "CDP Request: " + method + " " + url.get();
//                        ExtentFactory.getInstance().getExtent().info(msg);
//                        Allure.step(msg);
//                    },
//                    responseReceived -> {
//                        int status = responseReceived.getResponse().getStatus();
//                        url.set(responseReceived.getResponse().getUrl()); // <-- FIXED
//                        String msg = "CDP Response: " + status + " " + url.get();
//                        ExtentFactory.getInstance().getExtent().info(msg);
//                        Allure.step(msg);
//                    }
//            );
//
//            DevToolsFactory.enableDOMDomain(
//                    unused -> {
//                        String msg = "CDP Event: DOM updated!";
//                        ExtentFactory.getInstance().getExtent().info(msg);
//                        Allure.step(msg);
//                    }
//            );
//        }
    }

    @AfterMethod
    public void tearDown() {
        // Quit the browser and remove the WebDriver Instance
        DriverFactory.getInstance().quitThreadLocal();

        // Remove the ExtentTest object for the current thread
        ExtentFactory.getInstance().removeExtentObject();

//        DevToolsFactory.disableAll();
//        DevToolsFactory.removeDevTools();
    }

}

