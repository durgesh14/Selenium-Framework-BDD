package com.framework.utility;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.framework.listeners.EventListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestCaseBase {


    public void browserSetup(String url) throws IOException {
//        launchBrowser(DataReader.getProperty("url"));
        launchBrowser(url);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = DriverManager.getInstance().getDriver();
        DriverManager.getInstance().getEventFiringWebDriver().unregister(DriverManager.getInstance().getEventListener());
        closeBrowser(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void methodClose(ITestResult result) {
        WebDriver driver = DriverManager.getInstance().getDriver();
        String testCaseName = result.getMethod().getConstructorOrMethod().getName();
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenShotPath = ScreenshotUtility.takeFullScreenshot(driver, testCaseName + "_FAILED");
            try {
                ExtentManager.getTest().error("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
            } catch (IOException e) {
                Log.error("Error during capturing screenshot: " + e);
                throw new RuntimeException(e);
            }
        }
    }

    public WebDriver launchBrowser(String url) {
        System.out.println("Opening Browser");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        EventListener listener = new EventListener();
        eventFiringWebDriver.register(listener);
        driver = eventFiringWebDriver;
        DriverManager.getInstance().setEventFiringWebDriver(eventFiringWebDriver);
        DriverManager.getInstance().setEventListener(listener);
        DriverManager.getInstance().setDriver(driver);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public void closeBrowser(WebDriver driver) {
        System.out.println("Quiting Browser");
        driver.quit();
    }
}
