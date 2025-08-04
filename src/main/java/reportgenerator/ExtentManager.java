package reportgenerator;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import util.DateUtil;
import util.ReadConfigProperties;

/**
 * The ExtentManager is responsible for managing and creating the ExtentReports object
 * Think of it as a utility class that:
 * - Initializes and configures the ExtentReports instance (e.g., setting the report file name, theme etc.)
 * - Ensures the report is created in the correct format and location
 * Why We need ExtentManager
 * - Central;ized initialization: If multiple tests/classes need the same ExtentReports object,
 * This centralizes its creation in ExtentManager to avoid duplicate code.
 * - Configuration: You can Configure the report
 * - The ExtentReports instance can be reused wherever required
 * What it does
 * - Create a single instance of ExtentReports
 * - Configures the report and attaches the proper reporter (e.g., ExtentSparkReporter)
 */
public class ExtentManager {

    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extentReports;

    public static ExtentReports getExtentReport() throws Exception {

        String reportName = System.getProperty("user.dir") +
                "/ExtentReports/ExecutionReport_" + DateUtil.getTimeStamp() + ".html";

        sparkReporter = new ExtentSparkReporter(reportName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        sparkReporter.config().setDocumentTitle("DocumentTitle");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setReportName("ReportName");

        extentReports.setSystemInfo("Executed on Environment: ", ReadConfigProperties.getPropertyValueByKey("url"));

        return extentReports;
    }


}
