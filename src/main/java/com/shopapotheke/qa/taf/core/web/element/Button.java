package com.shopapotheke.qa.taf.core.web.element;

import com.shopapotheke.qa.taf.core.web.element.actions.ClickAction;
import com.shopapotheke.qa.taf.core.web.element.actions.ScrollAction;
import com.shopapotheke.qa.taf.core.web.element.base.ElementBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * All Button related actions are implemented in this class
 */
public class Button extends ElementBase {
    private static Logger log = LogManager.getLogger(Button.class);
    private RemoteWebDriver driver;
    private WebElement webElement;
    private By elementLocator;
    private String elementLocatorString;
    private ClickAction clickAction;
    private ScrollAction scrollAction;


    public Button(RemoteWebDriver webDriver, By locator, WebElement element) {
        super(webDriver, locator, element);
        this.driver = webDriver;
        this.elementLocator = locator;
        this.elementLocatorString = locator.toString();
        this.clickAction = new ClickAction(webDriver);
        this.scrollAction = new ScrollAction(webDriver);
        this.webElement = element;
    }

}
