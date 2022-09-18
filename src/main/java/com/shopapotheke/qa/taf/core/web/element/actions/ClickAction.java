package com.shopapotheke.qa.taf.core.web.element.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.script.ScriptException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.shopapotheke.qa.taf.core.config.Configurations.TIMEOUT_GLOBAL_ELEMENT;

/**
 * Click Action related methods are wrapped under this class
 */
public class ClickAction {
    private static Logger log = LogManager.getLogger(ClickAction.class);
    private RemoteWebDriver driver;

    public ClickAction(RemoteWebDriver driver) {
        this.driver = driver;
    }


    /**
     * click on a WebElement using the given locator
     *
     * @param locator respective locator {@link By}
     */
    public void click(By locator) throws ScriptException {
        try {
            Duration d = Duration.of(TIMEOUT_GLOBAL_ELEMENT, ChronoUnit.SECONDS);
            new WebDriverWait(driver, d)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(locator).click();
        } catch (Exception e) {
            throw new ScriptException("Error When Clicking on the Element. ");
        }
    }

}
