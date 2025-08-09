package reportgenerator;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import util.DateUtil;
import util.YamlConfigLoader;

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

        if (extentReports == null) {
            String url = YamlConfigLoader.getUrl();

            // Get Surefire fork number (null if not forking)
            String forkNum = System.getProperty("surefire.forkNumber");
            String timestamp = DateUtil.getTimeStamp();
            String reportName;

            if (forkNum != null) {
                // Forked run: make file unique per fork
                reportName = System.getProperty("user.dir") +
                        "/ExtentReports/ExecutionReport_Fork" + forkNum + "_" + timestamp + ".html";
            } else {
                // Regular run
                reportName = System.getProperty("user.dir") +
                        "/ExtentReports/ExecutionReport_" + timestamp + ".html";
            }
            sparkReporter = new ExtentSparkReporter(reportName);
            sparkReporter.config().setDocumentTitle("DocumentTitle");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setReportName("ReportName");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Executed on Environment: ", url);
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
            extentReports.setSystemInfo("Browser", System.getProperty("browser", "N/A"));
            extentReports.setSystemInfo("Platform", System.getProperty("platformName", "N/A"));

        }
        return extentReports;
    }


}
