package com.framework.scripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.modules.SearchStayModule;
import com.framework.modules.SearchflightModule;
import com.framework.utility.DataReader;
import com.framework.utility.ExcelReader;
import com.framework.utility.ExtentManager;
import com.framework.utility.Log;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class SearchFlightScript {
    private WebDriver driver;
    private SearchflightModule searchflightModule;
    private String excelSheetPath = "src/test/resources/data/Testdata.xlsx";

    public SearchFlightScript(WebDriver driver) {
        this.driver = driver;
        searchflightModule = new SearchflightModule(driver);

    }

    public void findFlight() {
        ExtentTest test = ExtentManager.getTest();
        String flightPageHeader = searchflightModule.getHeaderText();
        Log.info("Flight page header text is: " + flightPageHeader);
    }
}
