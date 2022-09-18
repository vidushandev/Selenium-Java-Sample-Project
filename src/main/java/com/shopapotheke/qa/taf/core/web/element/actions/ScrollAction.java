package com.shopapotheke.qa.taf.core.web.element.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.script.ScriptException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.shopapotheke.qa.taf.core.config.Configurations.TIMEOUT_GLOBAL_ELEMENT;

/**
 * Page Scroll action methods are wrapped under this class
 */
public class ScrollAction {
    private static Logger log = LogManager.getLogger(ScrollAction.class);
    private RemoteWebDriver driver;
    private Actions actions;
    Duration d = Duration.of(TIMEOUT_GLOBAL_ELEMENT, ChronoUnit.SECONDS);
    public ScrollAction(RemoteWebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    /**
     * Scroll to respective element
     *
     * @param locator locator of the respective element to scroll to
     */
    public void scrollToElement(By locator) throws ScriptException {
        try {

            WebElement element1 = (new WebDriverWait(driver, d)).until(ExpectedConditions.visibilityOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 4);", element1);
        } catch (StaleElementReferenceException e) {
            WebElement element1 = (new WebDriverWait(driver, d)).until(ExpectedConditions.visibilityOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 4);", element1);
        } catch (Exception e) {
            throw new ScriptException("Element Not Found. (locator: " + locator + ")");
        }
    }



}
