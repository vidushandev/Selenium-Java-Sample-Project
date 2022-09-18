package com.shopapotheke.qa.taf.core.web.element.base;

import com.shopapotheke.qa.taf.core.exceptions.FrameworkException;
import com.shopapotheke.qa.taf.core.web.element.actions.ClickAction;
import com.shopapotheke.qa.taf.core.web.element.actions.ScrollAction;
import com.shopapotheke.qa.taf.core.web.element.actions.TypeAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.script.ScriptException;

/**
 * Common Web element related methods are implemented in ElementBase
 */
public class ElementBase  {
    private static Logger log = LogManager.getLogger(ElementBase.class);
    private RemoteWebDriver driver;
    private WebElement webElement;
    private By elementLocator;
    private String elementLocatorString;
    private ClickAction clickAction;
    private ScrollAction scrollAction;
    private TypeAction typeAction;


    protected ElementBase(RemoteWebDriver webDriver, By locator, WebElement element) {
        this.driver = webDriver;
        this.elementLocator = locator;
        this.elementLocatorString = locator.toString();
        this.clickAction = new ClickAction(webDriver);
        this.scrollAction = new ScrollAction(webDriver);
        this.typeAction = new TypeAction(webDriver);
        this.webElement = element;

    }


    /**
     * click on a WebElement using the given locator
     *
     */
    public void click() throws  ScriptException {
        scrollAction.scrollToElement(this.elementLocator);
        clickAction.click(this.elementLocator);
        log.info("Click Web Element.  #Locator: " + this.elementLocatorString);
    }


    /**
     * Clear the text field and enter text in the given WebElement
     *
     * @param text Required text to enter
     */
    public void enterText(String text) throws ScriptException {
        scrollAction.scrollToElement(this.elementLocator);
        typeAction.enterText(this.elementLocator, text);
        log.info("Clear and Enter text in Web Element.  #Text: " + text + "  #Locator: " + this.elementLocatorString);
    }

    /**
     * Get Text in the Element
     *
     * @return String element text
     * @throws FrameworkException if any error occurred
     */
    public String getText() throws FrameworkException {
        String text = this.webElement.getText();
        log.info("Get text in Web Element.  #Text: " + text + "  #Locator: " + this.elementLocatorString);
        return text;
    }

}
