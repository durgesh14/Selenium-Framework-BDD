package com.framework.utility;

import com.framework.listeners.EventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverManager {

    private static DriverManager driverManager;
    private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
    private static ThreadLocal<EventFiringWebDriver> tEventFiringWebDriver = new ThreadLocal<>();
    private static ThreadLocal<EventListener> tEventListener = new ThreadLocal<>();

    private DriverManager() {

    }

    public static DriverManager getInstance() {
        if (driverManager == null) {
            synchronized (DriverManager.class) {
                driverManager = new DriverManager();
            }
        }
        return driverManager;
    }

    public synchronized void setDriver(WebDriver driver) {
        tDriver.set(driver);
    }

    public synchronized WebDriver getDriver() {
        WebDriver driver = tDriver.get();
        if (driver == null) {
            throw new IllegalStateException("Driver is null!!");
        }
        return driver;
    }

    public synchronized void setEventFiringWebDriver(EventFiringWebDriver eventFiringWebDriver) {
        tEventFiringWebDriver.set(eventFiringWebDriver);
    }

    public synchronized EventFiringWebDriver getEventFiringWebDriver() {
        EventFiringWebDriver eventFiringWebDriver = tEventFiringWebDriver.get();
        if (eventFiringWebDriver == null) {
            throw new IllegalStateException("EventFiringWebDriver should have not been null!!");
        }

        return eventFiringWebDriver;
    }

    public synchronized void setEventListener(EventListener eventListener){
        tEventListener.set(eventListener);
    }
    public  synchronized EventListener getEventListener(){
        EventListener eventListener = tEventListener.get();
        if(eventListener == null){
            throw new IllegalStateException("EventListener should have not been null!!");
        }
        return eventListener;
    }
}
