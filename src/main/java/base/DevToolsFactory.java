package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class DevToolsFactory {

    // Thread-Local DevTools instance tied to the thread's driver
    private static final ThreadLocal<DevTools> devToolsThreadLocal = new ThreadLocal<>();

    public static void initDevTools() {
        WebDriver driver = DriverFactory.getInstance().getDriverThreadLocal();
        if (driver instanceof ChromeDriver chromeDriver) {
            DevTools devTools = chromeDriver.getDevTools();
            devTools.createSession();
        } else {
            throw new IllegalStateException("DevTools is only supported for ChromeDriver");
        }
    }

    // Get DevTools for current method
    public static DevTools getDevTools() {
        return devToolsThreadLocal.get();
    }

    // Clean up after test
    public static void removeDevTools() {
        devToolsThreadLocal.remove();
    }
}
