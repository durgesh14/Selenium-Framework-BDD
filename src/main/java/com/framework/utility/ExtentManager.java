package com.framework.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtentManager {

    private static ExtentReports extent;
    static Map<Integer, ExtentTest> extentThreadMap = new HashMap<>();

    private static String extentConfigPath = System.getProperty("user.dir") + "/extent-config.xml";

    /**
     * Creates a new instance of {@link ExtentReports}, configuring it with system
     * information and attaching a {@link ExtentSparkReporter} for output.
     * The path for the report and configuration for the reporter are defined within this method.
     *
     * @return The configured {@link ExtentReports} instance.
     * @throws IOException If there is an issue loading the XML configuration for the reporter.
     */
    private static ExtentReports createInstance() throws IOException {
        String reportPath = System.getProperty("user.dir") + "/test-output/report/test-report.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentSparkReporter.loadXMLConfig(new File(extentConfigPath));
        extent = new ExtentReports();
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("Tester", "Durgesh Singh");

        extent.attachReporter(extentSparkReporter);
        return extent;
    }


    /**
     * Provides a singleton instance of {@link ExtentReports}.
     * If the instance is null, a new instance is created by invoking {@code createInstance()}.
     *
     * @return The singleton instance of {@link ExtentReports}.
     * @throws IOException If there is an issue creating a new instance.
     */
    static ExtentReports getInstance() throws IOException {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    /**
     * Retrieves the {@link ExtentTest} object associated with the current thread.
     *
     * @return The {@link ExtentTest} object for the current thread.
     */
    public synchronized static ExtentTest getTest() {
        return (ExtentTest) extentThreadMap.get((int) (long) (Thread.currentThread().getId()));
    }

    /**
     * Starts a new test within the extent report, associating it with the current thread.
     * The test name and method name are used to create and categorize the test.
     *
     * @param testname The fully qualified name of the test.
     * @param methodName The name of the test method.
     * @return The created {@link ExtentTest} object.
     * @throws IOException If there is an issue accessing the {@link ExtentReports} instance.
     */
    public synchronized static ExtentTest startTest(String testname, String methodName) throws IOException {
        ExtentTest test = getInstance().createTest(testname.substring(testname.lastIndexOf('.') + 1)+"."+methodName);

        extentThreadMap.put((int) (long) (Thread.currentThread().getId()), test);
//        test.createNode(testname);
        test.assignCategory(testname);
        return test;
    }

    /**
     * Ends the current test by flushing the {@link ExtentReports} instance,
     * ensuring that all information is written out to the report.
     *
     * @throws IOException If there is an issue flushing the report.
     */
    public synchronized static void endTest() throws IOException {
        getInstance().flush();
    }

}
