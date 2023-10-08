package com.framework.utility;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.framework.listeners.EventListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestCaseBase {

    @BeforeSuite
    public void allureConfigurator() throws IOException {
        AllureConfig.configure();
    }

    @Parameters({"browserName"})
    @BeforeClass
    public void setup(String browserName) throws IOException {
        String url = DataReader.getUrlBasedOnTest(this.getClass().getSimpleName());
        launchBrowser(url, browserName);
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
            ExtentTest test = ExtentManager.getTest();
            test.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
        }
    }


    public WebDriver launchBrowser(String url, String browserName) {
        System.out.println("Opening Browser: "+ browserName);
        WebDriverManager webDriverManager = WebDriverManager.getInstance(DriverManagerType.valueOf(browserName.toUpperCase()));
        webDriverManager.setup();
        WebDriver driver;

        switch (browserName.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-infobars");
                driver = new ChromeDriver(options);
                break;
        }

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
