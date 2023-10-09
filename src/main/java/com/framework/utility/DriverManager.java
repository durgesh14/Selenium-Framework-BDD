package com.framework.utility;

import com.framework.listeners.EventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * A singleton class responsible for managing WebDriver instances and associated event listeners.
 */
public class DriverManager {

    private static DriverManager driverManager;
    private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
    private static ThreadLocal<EventFiringWebDriver> tEventFiringWebDriver = new ThreadLocal<>();
    private static ThreadLocal<EventListener> tEventListener = new ThreadLocal<>();

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private DriverManager() {

    }


    /**
     * Provides a thread-safe singleton instance of DriverManager.
     *
     * @return The singleton instance of DriverManager.
     */
    public static DriverManager getInstance() {
        if (driverManager == null) {
            synchronized (DriverManager.class) {
                driverManager = new DriverManager();
            }
        }
        return driverManager;
    }


    /**
     * Sets the WebDriver instance for the current thread.
     *
     * @param driver The WebDriver instance to set.
     */
    public synchronized void setDriver(WebDriver driver) {
        tDriver.set(driver);
    }

    /**
     * Retrieves the WebDriver instance for the current thread.
     *
     * @return The WebDriver instance.
     * @throws IllegalStateException If the WebDriver instance is null.
     */

    public synchronized WebDriver getDriver() {
        WebDriver driver = tDriver.get();
        if (driver == null) {
            throw new IllegalStateException("Driver is null!!");
        }
        return driver;
    }


    /**
     * Sets the EventFiringWebDriver instance for the current thread.
     *
     * @param eventFiringWebDriver The EventFiringWebDriver instance to set.
     */
    public synchronized void setEventFiringWebDriver(EventFiringWebDriver eventFiringWebDriver) {
        tEventFiringWebDriver.set(eventFiringWebDriver);
    }


    /**
     * Retrieves the EventFiringWebDriver instance for the current thread.
     *
     * @return The EventFiringWebDriver instance.
     * @throws IllegalStateException If the EventFiringWebDriver instance is null.
     */
    public synchronized EventFiringWebDriver getEventFiringWebDriver() {
        EventFiringWebDriver eventFiringWebDriver = tEventFiringWebDriver.get();
        if (eventFiringWebDriver == null) {
            throw new IllegalStateException("EventFiringWebDriver should have not been null!!");
        }

        return eventFiringWebDriver;
    }


    /**
     * Sets the EventListener instance for the current thread.
     *
     * @param eventListener The EventListener instance to set.
     */
    public synchronized void setEventListener(EventListener eventListener) {
        tEventListener.set(eventListener);
    }


    /**
     * Retrieves the EventListener instance for the current thread.
     *
     * @return The EventListener instance.
     * @throws IllegalStateException If the EventListener instance is null.
     */
    public synchronized EventListener getEventListener() {
        EventListener eventListener = tEventListener.get();
        if (eventListener == null) {
            throw new IllegalStateException("EventListener should have not been null!!");
        }
        return eventListener;
    }
}
