package com.shopapotheke.qa.taf.core.web.element.actions;

import com.shopapotheke.qa.taf.core.exceptions.FrameworkException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.script.ScriptException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.shopapotheke.qa.taf.core.config.Configurations.TIMEOUT_GLOBAL_ELEMENT;

/**
 * Type Action related methods are wrapped under this class
 */
public class TypeAction {
    private RemoteWebDriver driver;

    public TypeAction(RemoteWebDriver driver) {
        this.driver = driver;
    }


    /**
     * Append text in the given WebElement
     *
     * @param locator Element Locator
     * @param text    Required text to enter
     * @throws FrameworkException if any error occurred
     */
    public void appendText(By locator, String text)  {
            driver.findElement(locator).sendKeys(text);
    }


    /**
     * Clear the text field and enter text in the given WebElement
     *
     * @param locator Element Locator
     * @param text    Required text to enter
     * @throws FrameworkException if any error occurred
     */
    public void enterText(By locator, String text) {
        clear(locator);
        appendText(locator, text);
    }



    /**
     * Clear the text in given WebElement
     *
     * @param locator Element Locator to clear text
     * @throws FrameworkException if any error occurred
     */
    public void clear(By locator) {
            driver.findElement(locator).clear();
    }

}
