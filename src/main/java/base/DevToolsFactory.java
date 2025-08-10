package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.dom.DOM;
import org.openqa.selenium.devtools.v138.network.Network;
import org.openqa.selenium.devtools.v138.network.model.RequestWillBeSent;
import org.openqa.selenium.devtools.v138.network.model.ResponseReceived;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.Consumer;

public class DevToolsFactory {

    // Thread-Local DevTools instance tied to the thread's driver
    private static final ThreadLocal<DevTools> devToolsThreadLocal = new ThreadLocal<>();

    public static void initDevTools() {
        WebDriver driver = DriverFactory.getInstance().getDriverThreadLocal();
        if (driver instanceof ChromeDriver chromeDriver) {
            DevTools devTools = chromeDriver.getDevTools();
            devTools.createSession();
            devToolsThreadLocal.set(devTools);
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

    // Enable Network and log requests/responses
    public static void enableNetworkMonitoring(
            Consumer<RequestWillBeSent> onRequest,
            Consumer<ResponseReceived> onResponse) {
        DevTools devTools = getDevTools();
        if (devTools == null) throw new IllegalStateException("DevTools not initialized for this thread.");

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(), onRequest);
        devTools.addListener(Network.responseReceived(), onResponse);
    }

    // Enable DOM domain (example: log when document updated)
    public static void enableDOMDomain(Consumer<Void> onDocumentUpdated) {
        DevTools devTools = getDevTools();
        if (devTools == null) throw new IllegalStateException("DevTools not initialized for this thread.");

        devTools.send(DOM.enable(Optional.empty()));
        devTools.addListener(DOM.documentUpdated(), event -> onDocumentUpdated.accept(null));
    }

    // Turn off Network and DOM listeners
    public static void disableAll() {
        DevTools devTools = getDevTools();
        if (devTools != null) {
            devTools.clearListeners();
        }
    }
}
