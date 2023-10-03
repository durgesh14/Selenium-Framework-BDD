package com.framework.utility;

import com.framework.listeners.EventListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestCaseBase {

    @BeforeClass
    public void browserSetup() throws IOException {
        launchBrowser(DataReader.getProperty("url"));
    }
    @AfterClass
    public void tearDown(){
        WebDriver driver = DriverManager.getInstance().getDriver();
        DriverManager.getInstance().getEventFiringWebDriver().unregister(DriverManager.getInstance().getEventListener());
        closeBrowser(driver);
    }

    public WebDriver launchBrowser(String url){
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

    public void closeBrowser(WebDriver driver){
        System.out.println("Quiting Browser");
        driver.quit();
    }
}
