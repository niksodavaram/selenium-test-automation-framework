package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.math3.geometry.spherical.twod.Edge;
import org.apache.groovy.json.internal.Chr;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    //Create WebDriver Object for a given Browser;
    public WebDriver createBrowserInstance(String browser) throws MalformedURLException {
        WebDriver driver = null;

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            System.setProperty("WebDriver.chrome.silenceOutput", "true");
            //ChromeOptions chromeOptions = setChromeOptions();
            ChromeOptions chromeOptions=setSauceChromeOptions();

            /**
             * setting up driver configuration for local machine
             */
            //driver=new ChromeDriver(chromeOptions);

            /**
             * setting up driver configuration for Selenium Grid4
             */
            //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
            /**
             * Setting driver configuration for AWS Ec2 instance
             */
            //driver = new RemoteWebDriver(new URL("http://54.206.101.220:4444/"), chromeOptions);

            /**
             * Setting driver configuration for Sauce Labs
             */

            URL url = new
                    URL("https://oauth-nireekshan8-82aaf:cccec85b-d396-4b18-b34a-0d1ef3f42dd3@ondemand.us-west-1.saucelabs.com:443/wd/hub");

            driver = new RemoteWebDriver(url, chromeOptions);

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            //FirefoxOptions firefoxOptions = setFirefoxOptions();
            FirefoxOptions firefoxOptions=setSauceFirefoxOptions();
            //driver=new FirefoxDriver(firefoxOptions);
            //driver = new RemoteWebDriver(new URL("http://54.206.101.220:4444/"), firefoxOptions);

            URL url = new URL("https://oauth-nireekshan8-82aaf:cccec85b-d396-4b18-b34a-0d1ef3f42dd3@ondemand.us-west-1.saucelabs.com:443/wd/hub");
            driver = new RemoteWebDriver(url, firefoxOptions);

        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            //EdgeOptions edgeOptions = setEdgeOptions();
            EdgeOptions edgeOptions=setSauceEdgeOptions();
            //driver=new EdgeDriver();
            //driver = new RemoteWebDriver(new URL("http://54.206.101.220:4444/"), edgeOptions);

            URL url = new URL("https://oauth-nireekshan8-82aaf:cccec85b-d396-4b18-b34a-0d1ef3f42dd3@ondemand.us-west-1.saucelabs.com:443/wd/hub");
            driver = new RemoteWebDriver(url, edgeOptions);

        }

        return driver;
    }

    private ChromeOptions setChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.getLogLevel();

        return chromeOptions;
    }

    private FirefoxOptions setFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.download.folderList", 1);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
        firefoxProfile.setPreference("browser.download.useDownloadDir", true);
        firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        firefoxProfile.setPreference("browser.download.manager.closeWhenDone", true);
        firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
        firefoxProfile.setPreference("browser.download.manager.useWindow", false);
        firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        // You will need to find the content-type of your app and set it here.
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
        firefoxOptions.setProfile(firefoxProfile);
        return firefoxOptions;
    }

    private EdgeOptions setEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("acceptSslCerts", "true");
        return edgeOptions;
    }


    private ChromeOptions setSauceChromeOptions(){

        ChromeOptions chromeBrowserOptions = new ChromeOptions();
        chromeBrowserOptions.setPlatformName("Windows 11");
        chromeBrowserOptions.setBrowserVersion("103");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("build", "<your build id>");
        sauceOptions.put("name", "<your test name>");
        chromeBrowserOptions.setCapability("sauce:options", sauceOptions);

       return chromeBrowserOptions;

    }

    private EdgeOptions setSauceEdgeOptions(){
        EdgeOptions edgeBrowserOptions = new EdgeOptions();
        edgeBrowserOptions.setPlatformName("Windows 11");
        edgeBrowserOptions.setBrowserVersion("103");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("build", "<your build id>");
        sauceOptions.put("name", "<your test name>");
        edgeBrowserOptions.setCapability("sauce:options", sauceOptions);
        return edgeBrowserOptions;
    }

    private FirefoxOptions setSauceFirefoxOptions(){
        FirefoxOptions firefoxBrowserOptions = new FirefoxOptions();
        firefoxBrowserOptions.setPlatformName("Windows 11");
        firefoxBrowserOptions.setBrowserVersion("103");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("build", "<your build id>");
        sauceOptions.put("name", "<your test name>");
        firefoxBrowserOptions.setCapability("sauce:options", sauceOptions);
        return firefoxBrowserOptions;
    }
}



