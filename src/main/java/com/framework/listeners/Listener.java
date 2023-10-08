package com.framework.listeners;

import com.aventstack.extentreports.Status;
import com.framework.utility.DataReader;
import com.framework.utility.ExtentManager;
import com.framework.utility.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
//        ExtentManager.startTest(result.getName());
        try {
            ExtentManager.startTest(result.getTestClass().getName(), result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Log.startTestCase(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testCaseName = result.getName();
        String status = testCaseName+"_PASS";
        Log.endTestCase(testCaseName, "PASSED");
        ExtentManager.getTest().log(Status.PASS, status);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testCaseName = result.getName();
        String status = testCaseName+ "_FAIL";
        Log.error(status);
        Log.endTestCase(testCaseName, "FAILED");
        ExtentManager.getTest().log(Status.FAIL, status+"\n"+result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testCaseName = result.getName();
        String status = testCaseName+"_SKIP";
        Log.info(status);
        ExtentManager.getTest().log(Status.SKIP, status);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        try {
            ExtentManager.endTest();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
