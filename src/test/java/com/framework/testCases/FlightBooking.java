package com.framework.testCases;

import com.framework.listeners.Listener;
import com.framework.scripts.SearchFlightScript;
import com.framework.scripts.SearchStayScript;
import com.framework.scripts.SelectHotelsScript;
import com.framework.utility.DriverManager;
import com.framework.utility.TestCaseBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;


@Listeners({Listener.class})
public class FlightBooking extends TestCaseBase {

    @BeforeClass
    public void setup() throws IOException {
        browserSetup("https://www.booking.com/flights/");
    }
    @Test
    public void searchFlights(){
        String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        SearchFlightScript searchFlightScript = new SearchFlightScript(DriverManager.getInstance().getDriver());
        searchFlightScript.findFlight();


    }
}
