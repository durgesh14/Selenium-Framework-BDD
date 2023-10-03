package com.framework.listeners;

import com.framework.utility.DataReader;
import com.framework.utility.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Log.startTestCase(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testCaseName = result.getName();
        String status = testCaseName+"_PASS";
        Log.endTestCase(testCaseName, "PASSED");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testCaseName = result.getName();
        String status = testCaseName+ "_FAIL";
        Log.error(status);
        Log.endTestCase(testCaseName, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testCaseName = result.getName();
        String status = testCaseName+"_SKIP";
        Log.info(status);
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
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
