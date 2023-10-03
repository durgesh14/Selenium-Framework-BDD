package com.framework.testCases;

import com.framework.scripts.SearchStayScript;
import com.framework.utility.DriverManager;
import com.framework.utility.TestCaseBase;
import org.testng.annotations.Test;

import java.sql.Driver;

public class SearchForStay extends TestCaseBase {

    @Test
    public void searchForStay() throws Exception {
        String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        SearchStayScript searchStayScript = new SearchStayScript(DriverManager.getInstance().getDriver());
        searchStayScript.addStayDetails(testCaseName);
    }
}
