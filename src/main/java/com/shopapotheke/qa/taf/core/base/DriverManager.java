package com.shopapotheke.qa.taf.core.base;

import com.shopapotheke.qa.taf.core.config.Browser;
import com.shopapotheke.qa.taf.core.exceptions.FrameworkException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;

/**
 * Managing all diver related tasks and assign it to the Session variable
 *
 */
public class DriverManager {
    private static Logger log = LogManager.getLogger(DriverManager.class);

    private DriverManager() {
        // making constructor private to restrict unwanted instantiation
    }

    /**
     * Create the driver based on the given browser and execution mode
     *
     * @param browserName   browser name
     * @throws FrameworkException Error when creating the web driver instance
     */
    public static void createDriver(String browserName) throws FrameworkException {
        Session.setBrowser(browserName);
        setStandaloneMode(browserName);

 }

    /**
     * Create the driver instance for the Standalone mode
     *
     * @param browserName browser name
     * @throws FrameworkException Error when creating the web driver instance
     */
    private static void setStandaloneMode(String browserName) throws FrameworkException {
        switch (browserName) {

            case Browser.CHROME:

                ChromeOptions options = new ChromeOptions();

                // Disable chrome identified as automated browser
                options.addArguments("start-maximized");
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

                WebDriverManager.chromedriver().setup();
                Session.setWebDriver(new ChromeDriver(options));
                break;
            case Browser.FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                Session.setWebDriver(new FirefoxDriver(firefoxOptions));
                break;
            case Browser.IEXPLORE:
                WebDriverManager.iedriver().setup();
                Session.setWebDriver(new InternetExplorerDriver());
                break;
            case Browser.EDGE:
                WebDriverManager.edgedriver().setup();
                Session.setWebDriver(new EdgeDriver());
                break;
            default:
                throw new FrameworkException("Unknown browser: " + Session.getBrowser());
        }
    }


}
