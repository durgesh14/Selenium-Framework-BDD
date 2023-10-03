package com.framework.testCases;

import com.framework.listeners.Listener;
import com.framework.scripts.SearchStayScript;
import com.framework.scripts.SelectHotelsScript;
import com.framework.utility.DriverManager;
import com.framework.utility.TestCaseBase;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({Listener.class})
public class HotelBooking extends TestCaseBase {

    @Test
    public void searchRooms() throws Exception {
        String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        SearchStayScript searchStayScript = new SearchStayScript(DriverManager.getInstance().getDriver());
        searchStayScript.addStayDetails(testCaseName);
    }

    @Test(dependsOnMethods = "searchRooms")
    public void selectHotels(){
        String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        SelectHotelsScript selectHotelsScript = new SelectHotelsScript(DriverManager.getInstance().getDriver());
        selectHotelsScript.selectHotel(testCaseName);


    }
}