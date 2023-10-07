package com.framework.testCases;

import com.framework.listeners.Listener;
import com.framework.scripts.SearchFlightScript;
import com.framework.scripts.SearchStayScript;
import com.framework.scripts.SelectHotelsScript;
import com.framework.utility.DriverManager;
import com.framework.utility.TestCaseBase;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;


@Listeners({Listener.class})
@Epic("Booking Test")
@Feature("To test the functionality of flight booking flow")
public class FlightBooking extends TestCaseBase {

    @BeforeClass
    public void setup() throws IOException {
        browserSetup("https://www.booking.com/flights/");
    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify the Title of the page")
    @Story("Search flights")
    public void checkPageTitle(){
        String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        SearchFlightScript searchFlightScript = new SearchFlightScript(DriverManager.getInstance().getDriver());
        searchFlightScript.getTitle();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify the user can select one-way flight")
    @Story("Search flights")
    public void selectOneWay(){
        String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        SearchFlightScript searchFlightScript = new SearchFlightScript(DriverManager.getInstance().getDriver());
        searchFlightScript.selectOneWay();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify the user can enter destination")
    @Story("Search flights")
    public void addDestination() throws Exception {
        String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        SearchFlightScript searchFlightScript = new SearchFlightScript(DriverManager.getInstance().getDriver());
        searchFlightScript.addDestination(testCaseName);
    }
}
