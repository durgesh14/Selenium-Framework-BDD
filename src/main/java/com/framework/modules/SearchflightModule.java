package com.framework.modules;

import com.framework.pageObjects.FlightHomePageObject;
import org.openqa.selenium.WebDriver;

public class SearchflightModule {

    private FlightHomePageObject flightHomePageObject;

    public SearchflightModule(WebDriver driver) {
        flightHomePageObject = new FlightHomePageObject(driver);
    }

    public String getHeaderText() {
        return flightHomePageObject.getheading();
    }

    public void selectOneWay() {
        flightHomePageObject.selectOneWayOption();
    }

    public String addDestination(String destinationColumn) {
        return flightHomePageObject.enterDestination(destinationColumn);
    }
}
