package com.shopapotheke.qa.taf.core.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Storing all the elements required for the current session
 * Can store multiple drivers and browser instances in separate Threads, required for the parallel execution
 */
public class Session {
    private static Logger log = LogManager.getLogger(Session.class);
    private static ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<>();
    private static ThreadLocal<String> browser = new ThreadLocal<>();

    private Session() {
        // making constructor private to restrict unwanted instantiation
    }

    /**
     * Return the WebDriver instance in the current Thread
     *
     * @return RemoteWebDriver
     */
    public static RemoteWebDriver getWebDriver() {
        log.debug(String.format("getDriver Instance for %s: %s", Thread.currentThread().toString(), webDriver.get().toString()));
        return webDriver.get();
    }


    /**
     * Set a RemoteWebDriver instance for the current Thread
     *
     * @param driver - driver instance
     */
    public static void setWebDriver(RemoteWebDriver driver) {
        webDriver.set(driver);
    }


    /**
     * Ger Browser in the Current Thread
     *
     * @return Browser Name
     */
    public static String getBrowser() {
        return browser.get();
    }


    /**
     * Set Browser instance for the current Thread
     *
     * @param browserName Browser Name
     */
    public static void setBrowser(String browserName) {
        browser.set(browserName);
    }

}
