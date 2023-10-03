package com.framework.utility;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ExtentManager {

    private static ExtentReports extent;
    static Map<Integer, ExtentTest> extentThreadMap = new HashMap<>();

    private static String extentConfigPath = System.getProperty("user.dir")+"/extent-config.xml";

    private static ExtentReports createInstance(){
        System.out.println("jhghhkjhkjhjhhkjh");
        String reportPath = System.getProperty("user.dir")+"/test-output/report/test-report.html";
        System.out.println("jhghhkjhkjhjhhkjh: "+ reportPath);
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportPath);
        extentHtmlReporter.loadXMLConfig(new File(extentConfigPath));
        extent = new ExtentReports();
        extent.attachReporter(extentHtmlReporter);
        return extent;
    }

    static ExtentReports getInstance(){
        if(extent == null){
            createInstance();
        }
        return extent;
    }

    public synchronized static ExtentTest getTest(){
        return (ExtentTest) extentThreadMap.get((int)(long)(Thread.currentThread().getId()));
    }

    public synchronized static ExtentTest startTest(String testname){
            ExtentTest test = getInstance().createTest(testname);
            extentThreadMap.put((int)(long)(Thread.currentThread().getId()), test);
            return test;
    }

    public synchronized static void endTest(){
        getInstance().flush();
    }

}
