package com.framework.utility;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private WebDriver driver;
    public void setDriver(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver() {
        if(driver == null) {
            throw new IllegalStateException("Driver is null!!");
        }
        return driver;
    }
}
