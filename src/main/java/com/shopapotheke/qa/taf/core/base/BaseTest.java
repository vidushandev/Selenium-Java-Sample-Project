package com.shopapotheke.qa.taf.core.base;

import com.shopapotheke.qa.taf.core.config.Configurations;
import com.shopapotheke.qa.taf.core.exceptions.FrameworkException;
import com.shopapotheke.qa.taf.core.listener.TestNgListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.shopapotheke.qa.taf.core.config.Configurations.*;

/**
 * All base level test case functionalities are written here
 * All Tests must be extended with this class
 */
@Listeners({TestNgListener.class})
public class BaseTest extends TestNgListener {
    private static Logger log = LogManager.getLogger(BaseTest.class);
    private final By closeButton = By.xpath("//div[@class='gcd__close']");

    /**
     * Executes before each @Test method
     * create the driver and navigate to the given url.
     *
     * @param browser required browser to execute tests
     * @param url     required url to navigate to
     * @throws FrameworkException if failed to setup the driver
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"test.browser", "test.url"})
    public void beforeMethod(@Optional String browser, @Optional String url) throws FrameworkException, InterruptedException {
        DriverManager.createDriver(Configurations.TEST_BROWSER);
        RemoteWebDriver driver = Session.getWebDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(TIMEOUT_PAGE_LOAD, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TIMEOUT_IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(TEST_URL);

        // Handling shadow element
        try{
            WebElement shadowHost = driver.findElement(By.cssSelector("#usercentrics-root"));
            SearchContext shadowRoot = shadowHost.getShadowRoot();
            shadowRoot.findElements(By.cssSelector("button")).get(1).click();

            driver.findElement(closeButton).click();

        }catch (Exception e){
        }




        log.info("************************* @BeforeMethod *************************");
    }


    /**
     * Executes after each @Test method
     * quits the diver and does the cleanup task
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        RemoteWebDriver driver = Session.getWebDriver();
        if (driver != null) {
            log.debug(String.format("Quit diver Instance for %s: %s", Thread.currentThread().toString(), driver.toString()));
            driver.quit();
            driver = null;
        }
        log.info("************************* @AfterMethod *************************");
    }


    /**
     * Executes after the TestSuite
     */
    @AfterSuite
    public void afterSuite() {
        log.info("************************* @AfterSuite *************************");
    }

    private class Exceptione extends Throwable {
    }
}