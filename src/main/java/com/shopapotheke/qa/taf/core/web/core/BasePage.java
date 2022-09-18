package com.shopapotheke.qa.taf.core.web.core;

import com.shopapotheke.qa.taf.core.base.Session;
import com.shopapotheke.qa.taf.core.verifications.Verify;
import com.shopapotheke.qa.taf.core.web.element.base.ElementLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Web Application BasePage
 */
public class BasePage {
    private static Logger log = LogManager.getLogger(BasePage.class);
    private RemoteWebDriver driver;
    public BasePage() {
        this.driver = Session.getWebDriver();
    }


    /**
     * Return the webElementLocator Object
     * Eg. elementLocator().textField(By.id("firstName")).enterText(firstName);
     *
     */
    protected ElementLocator elementLocator() {
        return new ElementLocator(driver);
    }


    /**
     * Return common Verify Object
     * Eg. verify().verifyEquals(contextLabel, context, "Expected and actual values for Context is NOT matched. ");
     *
     */
    protected Verify verify() {
        return new Verify();
    }


}
