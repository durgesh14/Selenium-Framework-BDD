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


    static ExtentReports getInstance() throws IOException {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    public synchronized static ExtentTest getTest() {
        return (ExtentTest) extentThreadMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public synchronized static ExtentTest startTest(String testname, String methodName) throws IOException {
        ExtentTest test = getInstance().createTest(testname.substring(testname.lastIndexOf('.') + 1)+"."+methodName);

        extentThreadMap.put((int) (long) (Thread.currentThread().getId()), test);
//        test.createNode(testname);
        test.assignCategory(testname);
        return test;
    }

    public synchronized static void endTest() throws IOException {
        getInstance().flush();
    }

}
