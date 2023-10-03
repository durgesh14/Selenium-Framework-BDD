package com.framework.scripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.modules.SearchStayModule;
import com.framework.utility.DataReader;
import com.framework.utility.ExcelReader;
import com.framework.utility.ExtentManager;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class SearchStayScript {
    private WebDriver driver;
    private SearchStayModule searchStayModule;
    private String excelSheetPath = "src/test/resources/data/Testdata.xlsx";

    public SearchStayScript(WebDriver driver) {
        this.driver = driver;
        searchStayModule = new SearchStayModule(driver);

    }

    public void addStayDetails(String testCaseName) throws Exception {
        ExtentTest test = ExtentManager.getTest();
        Map<String, String> testDataMap = new ExcelReader().getTestData(testCaseName, excelSheetPath, "Booking");
        searchStayModule.addDestination(testDataMap.get(DataReader.getProperty("destinationColumn")));
        test.log(Status.INFO, "Added Destination");
        searchStayModule.addDates(testDataMap);
        test.log(Status.INFO, "Added Dates");
        searchStayModule.addperson(testDataMap);
        test.log(Status.INFO, "Added Person Data");
    }
}
