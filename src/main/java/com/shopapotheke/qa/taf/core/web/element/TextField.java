package com.shopapotheke.qa.taf.core.web.element;

import com.shopapotheke.qa.taf.core.web.element.base.ElementBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * All TextField Specific methods are implemented in this Class
 */
public class TextField extends ElementBase {
    private static Logger log = LogManager.getLogger(TextField.class);
    private RemoteWebDriver driver;
    private WebElement webElement;
    private By elementLocator;
    private String elementLocatorString;


    public TextField(RemoteWebDriver webDriver, By locator, WebElement element) {
        super(webDriver, locator, element);
        this.driver = webDriver;
        this.elementLocator = locator;
        this.elementLocatorString = locator.toString();
        this.webElement = element;
    }

}
