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

public class Booking_HomePageObject {
    private WebDriver driver;

    public Booking_HomePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Integer.parseInt("10")), this);
    }

    @FindBy(css = "[placeholder='Where are you going?']")
    private WebElement destination;
    @FindBy(css = "[aria-label='Dismiss sign-in info.']")
    private WebElement closePopup;

    @FindBy(css = "[data-testid='autocomplete-results']")
    WebElement autocomplete_res;

    @FindBy(css = "[data-testid='searchbox-datepicker-calendar'] button")
    List<WebElement> nextMonth;
    @FindBy(css = "[data-testid='occupancy-config']")
    WebElement personDataBtn;
    @FindBy(css = "div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) button:first-child")
    WebElement adultMinus;
    @FindBy(css = "div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) button:last-child")
    WebElement adultAdd;
    @FindBy(css = "div:nth-child(2) > div:nth-child(3) > button:nth-child(1)")
    WebElement childMinus;
    @FindBy(css = "div:nth-child(2) > div:nth-child(3) > button:nth-child(3)")
    WebElement childAdd;

    @FindBy(css = "div:nth-child(3) > div:nth-child(3) > button:nth-child(1)")
    WebElement roomMinus;
    @FindBy(css = "div:nth-child(3) > div:nth-child(3) > button:nth-child(3)")
    WebElement roomAdd;

    @FindBy(css = "button[type='submit']")
    WebElement searchBtn;

    public void enterDestination(String destinationColumn) {
        if (closePopup.isDisplayed()) {
            closePopup.click();
            Log.info("Pop up Closed");
        }
        destination.sendKeys(destinationColumn);
        if (autocomplete_res.isDisplayed()) {
            List<WebElement> elements = autocomplete_res.findElements(By.cssSelector("ul li div:nth-child(1) > div:nth-child(2) > div:nth-child(1)"));
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getText().trim().equalsIgnoreCase(destinationColumn)) {
                    elements.get(i).click();
                    break;
                }

            }
        }
        Log.info("Destination added: "+destinationColumn);

    }

    public void enterCheckInAndCheckOutDates(String checkinDateColumn, String checkoutDateColumn) {
        String[] checkinDateParts = checkinDateColumn.split("-");
        String[] checkoutDateParts = checkoutDateColumn.split("-");

        // Convert month and year to integers
        int checkinYear = Integer.parseInt(checkinDateParts[0]);
        int checkinMonth = Integer.parseInt(checkinDateParts[1]);
        int checkoutYear = Integer.parseInt(checkoutDateParts[0]);
        int checkoutMonth = Integer.parseInt(checkoutDateParts[1]);

        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();

        LocalDate date1 = LocalDate.parse(checkinDateColumn);
        LocalDate date2 = LocalDate.parse(checkoutDateColumn);

        // Calculate the month difference
        long monthDifference = ChronoUnit.MONTHS.between(currentDate.withDayOfMonth(1), date1.withDayOfMonth(1));
        System.out.println("monthDifference: " + monthDifference);
        while (monthDifference > 0) {
            System.out.println("monthDifference: " + monthDifference);
            monthDifference--;
            nextMonth.get(nextMonth.size() - 1).click();

        }
        Log.info("CheckIn Date selected: "+checkinDateColumn);


//        int diffCheckin = checkinMonth - currentMonth;
//        while(diffCheckin > 1){
//            System.out.println("Clicked next part 1");
//            nextMonth.click();
//            diffCheckin--;
//        }

        WebElement checkinDate = driver.findElement(By.xpath("//span[@data-date='" + checkinDateColumn + "']"));
        checkinDate.click();

        // Calculate the month difference
        long monthDifferenceCheckout = ChronoUnit.MONTHS.between(currentDate.withDayOfMonth(1), date2.withDayOfMonth(1));
        System.out.println("monthDifferenceCheckout: " + monthDifferenceCheckout);
        while (monthDifferenceCheckout > 0) {
            System.out.println("monthDifferenceCheckout: " + monthDifferenceCheckout);
            monthDifferenceCheckout--;
            nextMonth.get(nextMonth.size() - 1).click();

        }


//        int diffCheckout = checkoutMonth - checkinMonth;
//        while(diffCheckout > 1){
//
//            System.out.println("Clicked next part 2");
//            nextMonth.get(nextMonth.size()-1).click();
//            diffCheckout--;
//        }
        WebElement checkoutDate = driver.findElement(By.xpath("//span[@data-date='" + checkoutDateColumn + "']"));
        checkoutDate.click();
        System.out.println("Clicked CheckOutdate");
        Log.info("Checkout Date Selected: "+checkoutDateColumn);
    }

    public void addPersonData(Map<String, String> testDataMap) throws IOException {
        personDataBtn.click();
        //Adult
        int diffAdult = Integer.parseInt(testDataMap.get(DataReader.getProperty("numberOfAdultsColumn"))) - 2;
        if (diffAdult == -1) {
            //1-click
            adultMinus.click();

        } else if (diffAdult == -2) {
            //2-click
            adultMinus.click();
            adultMinus.click();
        }
        while (diffAdult > 0) {
            adultAdd.click();
            diffAdult--;
        }
        Log.info("Added # of adult"+Integer.parseInt(testDataMap.get(DataReader.getProperty("numberOfAdultsColumn"))));

        //Rooms
        int rooms = Integer.parseInt(testDataMap.get(DataReader.getProperty("numberOfRoomsColumn"))) - 1;
        for (int i = 0; i < rooms; i++) {
            roomAdd.click();
        }

        Log.info("Added # of rooms"+rooms+1);

    }

    public void searchRooms() {
        searchBtn.click();
        Log.info("Clicked on search");
    }
}
