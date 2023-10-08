package com.framework.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

   private int initialCount = 0;

    private int maxCount = 1;
    @Override
    public boolean retry(ITestResult iTestResult) {
        System.out.println("Retrying called");
        if(initialCount < maxCount){
            System.out.println("Retrying failed testcases");
            initialCount++;
            return true;
        }
        return false;
    }
}
