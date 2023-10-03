package com.framework.testCases;

import com.framework.pageObjects.HomePageObject;
import com.framework.utility.DriverManager;
import com.framework.utility.ExcelReader;
import com.framework.utility.TestCaseBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.Map;

import static java.sql.DriverManager.getDriver;

public class LoginCheck  extends TestCaseBase{

    WebDriver driver;
    private String excelSheetPath = "src/test/resources/data/Testdata.xlsx";

    @Test
    public void TC001_NavigateToHomage() throws Exception {
        driver = DriverManager.getInstance().getDriver();
        System.out.println(driver.getTitle());
        HomePageObject hp = new HomePageObject(driver);
        Map<String, String> testDataMap = new ExcelReader().getTestData("TC001_CreateAccount", excelSheetPath, "Account");
        hp.createAccount();
        hp.enterdetail(testDataMap);
    }
}
