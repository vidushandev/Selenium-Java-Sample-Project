package com.shopapotheke.qa.taf.core.web.element.base;

import com.shopapotheke.qa.taf.core.config.Configurations;
import com.shopapotheke.qa.taf.core.exceptions.FrameworkException;
import com.shopapotheke.qa.taf.core.web.element.Button;
import com.shopapotheke.qa.taf.core.web.element.Label;
import com.shopapotheke.qa.taf.core.web.element.TextField;
import com.shopapotheke.qa.taf.core.web.wait.WebDriverWaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Common Web ElementLocator to get the wrapped elements
 */
public class ElementLocator {
    private RemoteWebDriver driver;
    private final int DEFAULT_TIMEOUT = Configurations.TIMEOUT_GLOBAL_ELEMENT;

    public ElementLocator(RemoteWebDriver driver) {
        this.driver = driver;
    }

    /**
     * Return the Button wrapped in the CTAF
     *
     * @param findBy By locator to find the element. This will use the DEFAULT_ELEMENT_TIMEOUT
     * @return Button
     */
    public Button button(By findBy) throws FrameworkException {
        WebElement element = findElement(findBy, DEFAULT_TIMEOUT);
        return new Button(driver, findBy, element);
    }

    /**
     * Return the Label wrapped in the CTAF
     *
     * @param findBy By locator to find the element. This will use the DEFAULT_ELEMENT_TIMEOUT
     * @return Button
     */
    public Label label(By findBy) throws FrameworkException {
        WebElement element = findElement(findBy, DEFAULT_TIMEOUT);
        return new Label(driver, findBy, element);
    }


    /**
     * Return the TextField wrapped in the CTAF
     *
     * @param findBy By locator to find the element. This will use the DEFAULT_ELEMENT_TIMEOUT
     * @return TextField
     */
    public TextField textField(By findBy) throws FrameworkException {
        WebElement element = findElement(findBy, DEFAULT_TIMEOUT);
        return new TextField(driver, findBy, element);
    }

    /**
     * Find Required WebElement with FluentWait until element is available in dom
     *
     * @param by               By locator to find the element
     * @param timeoutInSeconds maximum time to wait till component available in the dom
     * @return WebElement
     */
    private WebElement findElement(By by, int timeoutInSeconds)  {
        // wait until element is available in the dom
        return new WebDriverWaitUtil(driver).waitForPresenceOfElementLocated(by, timeoutInSeconds);
    }

}
