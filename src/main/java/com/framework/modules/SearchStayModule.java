package com.framework.modules;

import com.framework.pageObjects.Booking_HomePageObject;
import com.framework.pageObjects.HomePageObject;
import com.framework.utility.DataReader;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Map;

public class SearchStayModule {

    private Booking_HomePageObject bookingHomePageObject;

    public SearchStayModule(WebDriver driver) {
        bookingHomePageObject = new Booking_HomePageObject(driver);
    }
    public void addDestination(String destinationColumn) {
        bookingHomePageObject.enterDestination(destinationColumn);
    }

    public void addDates(Map<String, String> testDataMap) throws IOException {
        bookingHomePageObject.enterCheckInAndCheckOutDates(testDataMap.get(DataReader.getProperty("checkinDateColumn")), testDataMap.get(DataReader.getProperty("checkoutDateColumn")));
    }

    public void addperson(Map<String, String> testDataMap) throws IOException {
        bookingHomePageObject.addPersonData(testDataMap);
        bookingHomePageObject.searchRooms();
    }
}
