package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.math3.geometry.spherical.twod.Edge;
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
import java.net.URI;
import java.net.URL;

public class BrowserFactory {
    private static final String GRID_URL = System.getProperty("grid.url", "http://localhost:4444/wd/hub");

    //Create WebDriver Object for a given Browser;
    public WebDriver createBrowserInstance(String browser, boolean isRemote) throws MalformedURLException {
        if (isRemote) {
            return createRemoteDriver(browser);
        } else {
            return createLocalDriver(browser);
        }
    }

    private WebDriver createRemoteDriver(String browser) throws MalformedURLException {
        return switch (browser.toLowerCase()) {
            case "chrome" -> new RemoteWebDriver(URI.create(GRID_URL).toURL(), setChromeOptions());
            case "firefox" -> new RemoteWebDriver(URI.create(GRID_URL).toURL(), setFirefoxOptions());
            case "edge" -> new RemoteWebDriver(URI.create(GRID_URL).toURL(), setEdgeOptions());
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private WebDriver createLocalDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver(setChromeOptions());
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver(setFirefoxOptions());
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver(setEdgeOptions());
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private ChromeOptions setChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");

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
}



