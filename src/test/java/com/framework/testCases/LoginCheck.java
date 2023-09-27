package com.framework.testCases;

import com.framework.pageObjects.HomePageObject;
import com.framework.utility.DriverManager;
import com.framework.utility.TestCaseBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static java.sql.DriverManager.getDriver;

public class LoginCheck  extends TestCaseBase{

    WebDriver driver;

    @Test
    public void TC001_NavigateToHomage(){
        driver = DriverManager.getInstance().getDriver();
        System.out.println(driver.getTitle());
        HomePageObject hp = new HomePageObject(driver);
        hp.createAccount();
        hp.enterdetail("first", "last", "test12@gmail.com", "Password@123", "Password@123");
    }
}
