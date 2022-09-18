package com.shopapotheke.qa.taf.core.web.wait;

//import com.cinglevue.qa.taf.core.exceptions.TimeoutException;
import com.shopapotheke.qa.taf.core.config.Configurations;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.shopapotheke.qa.taf.core.config.Configurations.TIMEOUT_GLOBAL_ELEMENT;

/**
 * Custom wait methods created using WebDriverWait
 */
public class WebDriverWaitUtil {
    private RemoteWebDriver driver;
    private final int DEFAULT_WAIT = Configurations.TIMEOUT_GLOBAL_ELEMENT;
    Duration d = Duration.of(TIMEOUT_GLOBAL_ELEMENT, ChronoUnit.SECONDS);

    public WebDriverWaitUtil(RemoteWebDriver driver) {
        this.driver = driver;
    }


    /**
     * An expectation for checking that an element is present on the DOM of a page. This does not
     * necessarily mean that the element is visible.
     *
     * @param findBy           element locator
     * @param timeoutInSeconds expected timeout in seconds
     * @return the WebElement once it is located
     * @throws TimeoutException if element not available in dom before the given timeout
     */
    public WebElement waitForPresenceOfElementLocated(By findBy, int timeoutInSeconds) throws TimeoutException {
        try {
            return (new WebDriverWait(driver, d)).until(ExpectedConditions.presenceOfElementLocated(findBy));
        } catch (Exception e) {
            throw new TimeoutException("Time out while waiting for element to be present. " + e.getMessage(), e);
        }
    }





    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param findBy           element locator
     * @param timeoutInSeconds expected timeout in seconds
     * @return the WebElement once it is located
     * @throws TimeoutException if element not clickable
     */
    public WebElement waitForElementToBeClickable(By findBy, int timeoutInSeconds) throws TimeoutException {
        try {
            return (new WebDriverWait(driver, d)).until(ExpectedConditions.elementToBeClickable(findBy));
        } catch (Exception e) {
            throw new TimeoutException("Time out while waiting for element to be Clickable. " + e.getMessage(), e);
        }
    }


    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param findBy element locator
     * @return the WebElement once it is located
     * @throws TimeoutException if element not clickable
     */
    public WebElement waitForElementToBeClickable(By findBy) throws TimeoutException {
        return waitForElementToBeClickable(findBy, DEFAULT_WAIT);
    }


    /**
     * An expectation for checking that an element is present on the DOM of a page and visible.
     * Visibility means that the element is not only displayed but also has a height and width that is
     * greater than 0.
     *
     * @param findBy           element locator
     * @param timeoutInSeconds expected timeout in seconds
     * @return the WebElement once it is located
     * @throws TimeoutException if element not visible
     */
    public WebElement waitForVisibilityOfElementLocated(By findBy, int timeoutInSeconds) throws TimeoutException {
        try {
            return (new WebDriverWait(driver, d)).until(ExpectedConditions.visibilityOfElementLocated(findBy));
        } catch (Exception e) {
            throw new TimeoutException("Time out while waiting for element to be Visible. " + e.getMessage(), e);
        }
    }


    /**
     * An expectation for checking that an element is present on the DOM of a page and visible.
     * Visibility means that the element is not only displayed but also has a height and width that is
     * greater than 0.
     *
     * @param findBy element locator
     * @return the WebElement once it is located
     * @throws TimeoutException if element not visible
     */
    public WebElement waitForVisibilityOfElementLocated(By findBy) throws TimeoutException {
        return waitForVisibilityOfElementLocated(findBy, DEFAULT_WAIT);
    }


    /**
     * An expectation for checking that an element is either invisible or not present on the DOM.
     *
     * @param findBy           element locator
     * @param timeoutInSeconds expected timeout in seconds
     * @throws TimeoutException if element either visible or present on dom
     */
    public void waitForInvisibilityOfElementLocated(By findBy, int timeoutInSeconds) throws TimeoutException {
        try {
            (new WebDriverWait(driver, d)).until(ExpectedConditions.invisibilityOfElementLocated(findBy));
        } catch (Exception e) {
            throw new TimeoutException("Time out while waiting for element to be Disappear. " + e.getMessage(), e);
        }
    }


    /**
     * An expectation for checking that an element is either invisible or not present on the DOM.
     *
     * @param findBy element locator
     * @throws TimeoutException if element either visible or present on dom
     */
    public void waitForInvisibilityOfElementLocated(By findBy) throws TimeoutException {
        waitForInvisibilityOfElementLocated(findBy, DEFAULT_WAIT);
    }

}
