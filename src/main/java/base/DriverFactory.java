package base;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DriverFActory class implements the Singleton Design Pattern
 * This class is responsible for managing the WebDriver instance for Selenium Framework
 * Key Features:
 * 1. **Singleton Pattern**: Ensures only one instance of the class is created
 * 2. **Thread-Safe Implementations**: Uses double-checked locking with `volatile` and
 * to ensure thread safety in a multithreaded environment
 * 3. **ThreadLocal WebDriver**: Provides separate WebDriver instance for each thread allowing
 * parallel executions without conflicts
 * Benefits of Multithreading:
 * - Guarantees that each thread gets its own independent WebDriver instance
 * - Prevents resource conflicts and ensures thread isolation
 * - Simplifies management of WebDriver lifecycle across multiple threads
 */
public class DriverFactory {

    //Private constructor to prevent installation of the class from outside
    // Ensures tha Singleton Pattern is upheld
    private DriverFactory() {
    }

    /**
     * Static reference variable of the class
     * Declared as `volatile` to ensure visibility of changes across threads
     * Ensures that only one instance if the class is created globally
     */
    private static volatile DriverFactory driverFactoryInstance = null;

    // Lock instance for thread-safe access to the Singleton instance
    // ReentrantLock provides flexibility compared to synchronized blocks
    private static final Lock lock = new ReentrantLock();

    // Static method to get the singe instance of this class
    public static DriverFactory getInstance() {
        if (driverFactoryInstance == null) {
            lock.lock();
            try {
                if (driverFactoryInstance == null) {
                    driverFactoryInstance = new DriverFactory();
                }
            } finally {
                lock.unlock();
            }
        }
        return driverFactoryInstance;
    }

    /**
     * Retrieves the WebDriver instance for the current thread
     * - ThreadLocal ensures thread isolation, so each thread has its own WebDriver
     * The WebDriver instance associated with the current thread
     */
    ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    //To get this threadLocal WebDriver for pageObjects
    public WebDriver getDriverThreadLocal() {
        return driverThreadLocal.get();
    }

    /**
     * Sets the WebDriver instance for the current thread.
     * - ThreadLocal ensures the WebDriver is bound to the specific thread
     * @param setDriverThreadLocal The WebDriver instance to be set for the current thread
     */
    public void setDriverThreadLocal(WebDriver setDriverThreadLocal) {
        driverThreadLocal.set(setDriverThreadLocal);
    }

    /**
     * Closes the threadLocal and removes the WebDriver instance for the current thread
     * - Ensures proper cleanup of resources after test execution
     * - Prevents memory leaks by removing the ThreadLocal reference
     */
    public void quitThreadLocal() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }

}


