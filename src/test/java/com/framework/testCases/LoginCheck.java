package com.framework.testCases;

import com.framework.utility.DriverManager;
import com.framework.utility.TestCaseBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static java.sql.DriverManager.getDriver;

public class LoginCheck  extends TestCaseBase{

    WebDriver driver;

    @Test
    public void TC001_NavigateToHomage(){

        driver = getDriver();
        System.out.println(driver.getTitle());
    }
}
