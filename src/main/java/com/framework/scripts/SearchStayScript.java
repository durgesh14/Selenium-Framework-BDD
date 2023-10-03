package com.framework.scripts;

import com.framework.modules.SearchStayModule;
import com.framework.utility.DataReader;
import com.framework.utility.ExcelReader;
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
        Map<String, String> testDataMap = new ExcelReader().getTestData(testCaseName, excelSheetPath, "Booking");
        searchStayModule.addDestination(testDataMap.get(DataReader.getProperty("destinationColumn")));
        searchStayModule.addDates(testDataMap);
        searchStayModule.addperson(testDataMap);
    }
}
