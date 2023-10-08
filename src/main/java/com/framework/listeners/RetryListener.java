package com.framework.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
//        Class<? extends IRetryAnalyzer> retry = annotation.getRetryAnalyzerClass();
//        System.out.println("Retrying called from RetryListener"+ retry.getSimpleName());
//        if (retry == null){
//            System.out.println("RetryListener inside IF");
//            annotation.setRetryAnalyzer(RetryAnalyzer.class);
//        }
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
