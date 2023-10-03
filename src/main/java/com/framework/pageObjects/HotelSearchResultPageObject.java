package com.framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HotelSearchResultPageObject {

    private WebDriver driver;

    public HotelSearchResultPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Integer.parseInt("10")), this);
    }

    @FindBy(tagName = "h1")
    private WebElement hotelRes;
    @FindBy(css = "[data-testid = 'title']")
    private WebElement hotelNameCss;

    public String getResultText() {
        return hotelRes.getText();
    }

    public String clickOnFirstHotel() {
        String hotelName = hotelNameCss.getText();
        hotelNameCss.click();
        return hotelName;
    }
}
