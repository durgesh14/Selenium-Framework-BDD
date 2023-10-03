package com.framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.Set;

public class HotelDetailsPageObject {

    private WebDriver driver;

    public HotelDetailsPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Integer.parseInt("10")), this);
    }

    @FindBy(css = "[id='hp_hotel_name'] h2")
    private WebElement hotelNameCss;

    public String getHotelName() {
        // Get the window handles (IDs of all open tabs/windows)
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(handle);
                break; // Switched to the new tab, exit the loop
            }
        }
        return hotelNameCss.getText();
    }
}
