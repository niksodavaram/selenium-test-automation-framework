package reportgenerator;

import com.aventstack.extentreports.ExtentTest;

/**
 * The ExtentFactory is responsible for managing ExtentTest Objects for each test method
 * It uses a ThreadLocal variable to ensure that each thread (or test case) gets its own ExtentTest instance
 * Why we need ExtentFactory
 * - Thread Safety: When running tests in parallel, each test must log its steps to itrs own ExtentTest object
 * - ExtentFactory ensures that each thread has its own isolated ExtentTest instance
 * - Test Logging: The ExtentTest object is used to log steps and status of the current test
 * - Lifecycle Management: It ensures that the ExtentTest object is removed after the test execution, preventing memory leaks
 * What it Does
 * - Provide thread-safe ExtentTest instance for parallel test using ThreadLocal
 * - Creates and stores ExtentTest objects for each test
 */
public class ExtentFactory {

    private ExtentFactory(){}

    private static ExtentFactory extentFactoryInstance=null;

    synchronized public static ExtentFactory getInstance(){
        if(extentFactoryInstance==null){
            extentFactoryInstance=new ExtentFactory();
        }
        return extentFactoryInstance;
    }

    ThreadLocal<ExtentTest> extentTestThreadLocal=new ThreadLocal<ExtentTest>();

    public ExtentTest getExtent(){
        return extentTestThreadLocal.get();
    }

    public void setExtentTestThreadLocal(ExtentTest extentTestObject){
        extentTestThreadLocal.set(extentTestObject);
    }

    public void removeExtentObject(){
        extentTestThreadLocal.remove();
    }
}
