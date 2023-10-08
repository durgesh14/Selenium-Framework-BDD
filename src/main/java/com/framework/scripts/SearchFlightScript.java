package com.framework.scripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.modules.SearchflightModule;
import com.framework.utility.DataReader;
import com.framework.utility.ExcelReader;
import com.framework.utility.ExtentManager;
import com.framework.utility.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Map;

public class SearchFlightScript {
    private WebDriver driver;
    private SearchflightModule searchflightModule;
    private String excelSheetPath = "src/test/resources/data/Testdata.xlsx";

    public SearchFlightScript(WebDriver driver) {
        this.driver = driver;
        searchflightModule = new SearchflightModule(driver);

    }

    public void getTitle() {
        ExtentTest test = ExtentManager.getTest();
        String flightPageHeader = searchflightModule.getHeaderText();
        test.log(Status.INFO, "Flight page header text is: " + flightPageHeader);

    }

    public void selectOneWay() {
        ExtentTest test = ExtentManager.getTest();
         searchflightModule.selectOneWay();
        test.log(Status.INFO, "Selected One Way Flight");
    }

    public void addDestination(String testCaseName) throws Exception {
        ExtentTest test = ExtentManager.getTest();
        Map<String, String> testDataMap = new ExcelReader().getTestData(testCaseName, excelSheetPath, "flight");
        String destinationExpected = testDataMap.get(DataReader.getProperty("destinationColumn"));
        String destinationActual = searchflightModule.addDestination(destinationExpected);
        test.log(Status.INFO, "Actual Destination is "+ destinationActual);
        test.log(Status.INFO, "Expected Destination is "+ destinationExpected);
        Assert.assertTrue(false);


    }
}
