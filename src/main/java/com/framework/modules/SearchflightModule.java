package com.framework.modules;

import com.framework.pageObjects.Booking_HomePageObject;
import com.framework.pageObjects.FlightHomePageObject;
import com.framework.utility.DataReader;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Map;

public class SearchflightModule {

    private FlightHomePageObject flightHomePageObject;

    public SearchflightModule(WebDriver driver) {
        flightHomePageObject = new FlightHomePageObject(driver);
    }

    public String getHeaderText() {
        return flightHomePageObject.getheading();
    }
}
