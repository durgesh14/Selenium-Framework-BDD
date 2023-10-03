package com.framework.scripts;

import com.aventstack.extentreports.ExtentTest;
import com.framework.pageObjects.HotelDetailsPageObject;
import com.framework.pageObjects.HotelSearchResultPageObject;
import com.framework.utility.ExtentManager;
import com.framework.utility.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SelectHotelsScript {
    private WebDriver driver;
    private HotelSearchResultPageObject hotelSearchResultPageObject;
    private HotelDetailsPageObject hotelDetailsPageObject;

    public SelectHotelsScript(WebDriver driver) {
        this.driver = driver;
        hotelSearchResultPageObject = new HotelSearchResultPageObject(driver);
        hotelDetailsPageObject = new HotelDetailsPageObject(driver);
    }

    public void selectHotel(String testCaseName) {
        ExtentTest test = ExtentManager.getTest();
        String res = hotelSearchResultPageObject.getResultText();
        Log.info("Header text is: " + res);
        String hotelName = hotelSearchResultPageObject.clickOnFirstHotel();
        Log.info("Clicked on Hotel with name:  " + hotelName);
        String hotelNameExpected = hotelDetailsPageObject.getHotelName();
        Log.info("Hotel Name is :  " + hotelNameExpected);
        Assert.assertEquals(hotelName, hotelNameExpected);
    }
}
