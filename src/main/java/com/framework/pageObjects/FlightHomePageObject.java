package com.framework.pageObjects;

import com.framework.utility.DataReader;
import com.framework.utility.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

public class FlightHomePageObject {
    private WebDriver driver;

    public FlightHomePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Integer.parseInt("10")), this);
    }

    @FindBy(tagName = "h1")
    private WebElement headerTextTag;

    public String getheading() {
    return headerTextTag.getText();
    }
}
