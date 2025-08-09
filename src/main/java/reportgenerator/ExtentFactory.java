package reportgenerator;

import com.aventstack.extentreports.ExtentTest;

/**
 * Thread-safe factory for ExtentTest objects.
 * One ExtentTest per test method (thread) using ThreadLocal.
 */
public class ExtentFactory {

    private ExtentFactory() {}

    // Eager singleton instance
    private static final ExtentFactory INSTANCE = new ExtentFactory();

    public static ExtentFactory getInstance() {
        return INSTANCE;
    }

    // ThreadLocal to store ExtentTest per thread
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public ExtentTest getExtent() {
        return extentTestThreadLocal.get();
    }

    public void setExtentTestThreadLocal(ExtentTest extentTestObject) {
        extentTestThreadLocal.set(extentTestObject);
    }

    public void removeExtentObject() {
        extentTestThreadLocal.remove();
    }
}