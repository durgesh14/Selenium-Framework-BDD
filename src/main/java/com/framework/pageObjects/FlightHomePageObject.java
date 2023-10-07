package com.framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class FlightHomePageObject {
    private WebDriver driver;

    public FlightHomePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Integer.parseInt("10")), this);
    }

    @FindBy(tagName = "h1")
    private WebElement headerTextTag;

    @FindBy(xpath = "//div[contains(text(),'One-way')]")
    private WebElement oneWayX;

    @FindBy(xpath = "//span[contains(text(),'Where to?')]")
    private WebElement destinationBtn;

    @FindBy(css = "input[placeholder='Airport or city']")
    private WebElement destination;

    @FindBy(css = "#flights-searchbox_suggestions span:nth-child(2) div")
    private WebElement destinationRes;

    public String getheading() {
    return headerTextTag.getText();
    }

    public void selectOneWayOption() {
        oneWayX.click();
    }

    public String enterDestination(String destinationColumn) {
        destinationBtn.click();
        destination.sendKeys(destinationColumn);
        return destinationRes.getText();

    }
}
