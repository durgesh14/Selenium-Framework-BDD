package com.framework.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class PageManager {

    private WebDriver driver;
    private int waitTime = 30;
    public PageManager(WebDriver driver){
        this.driver = driver;
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public void click(By locator) {
        findElement(locator).click();
    }

    public void click(WebElement element) {
        element.click();
    }

    public void clickByJavascript(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",element);
    }

    public void type(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void type(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return findElement(locator).getText();
    }
    public String getText(WebElement element) {
        return element.getText();
    }

    public void selectByVisibleText(WebElement element, String visibleText){
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public void selectByIndex(WebElement element, int index){
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public String getSelectedValue(WebElement element) {
        String value = new Select(element).getFirstSelectedOption().getText();
        Log.info("Selected Value : "+ value);
        return value;
    }

    public List<String> getAllSelectedValues(WebElement locator) {
        Select select = new Select(locator);
        List<WebElement> elementList = select.getAllSelectedOptions();
        return getElementTextList(elementList);
    }

    public List<String> getAllDropDownValues(WebElement locator) {
        Select select = new Select(locator);
        List<WebElement> elementList = select.getOptions();
        return getElementTextList(elementList);
    }

    private List<String> getElementTextList(List<WebElement> elementList) {
        List<String> valueList = new LinkedList<String>();

        for (WebElement element : elementList) {
            Log.info(element.getText());
            valueList.add(element.getText());
        }
        return valueList;
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            element.getTagName();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForElementVisible(By locator, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementVisible(WebElement element, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementClickable(By locator, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementClickable(WebElement element, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void selectDropdownByValue(By locator, String value) {
        new Select(findElement(locator)).selectByValue(value);
    }

    public void selectDropdownByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    public void selectDropdownByIndex(By locator, int index) {
        new Select(findElement(locator)).selectByIndex(index);
    }

    public void selectDropdownByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public void selectDropdownByText(By locator, String text) {
        new Select(findElement(locator)).selectByVisibleText(text);
    }

    public void selectDropdownByText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    public String getAttributeValue(By locator, String attributeName) {
        return findElement(locator).getAttribute(attributeName);
    }

    public String getAttributeValue(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public void scrollToElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", findElement(locator));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(findElement(sourceLocator), findElement(targetLocator)).perform();
    }

    public void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }

    public void moveToElement(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(locator)).perform();
    }

    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void rightClick(By locator) {
        Actions actions = new Actions(driver);
        actions.contextClick(findElement(locator)).perform();
    }

    public void rightClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }

    public void doubleClick(By locator) {
        Actions actions = new Actions(driver);
        actions.doubleClick(findElement(locator)).perform();
    }

    public void doubleClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    public void executeJavaScript(String script) {
        ((JavascriptExecutor) driver).executeScript(script);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    public WebElement waitForVisibilityOfElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement fluentWait(WebElement element, int timeoutInSeconds, int pollingEveryInSeconds) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(pollingEveryInSeconds))
                .ignoring(NoSuchElementException.class);

        return wait.until(driver -> element.isDisplayed() ? element : null);

    }

    public void sleep(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    public boolean waitForJSLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").toString().equals("complete");
        return wait.until(jsLoad);
    }

    public void waitForJQueryLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) driver)
                .executeScript("return jQuery.active") == 0);
        wait.until(jQueryLoad);
    }

}



