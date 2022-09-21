package com.shopapotheke.qa.taf.pageObjects;

import com.shopapotheke.qa.taf.core.base.Session;
import com.shopapotheke.qa.taf.core.exceptions.AssertionException;
import com.shopapotheke.qa.taf.core.exceptions.FrameworkException;
import com.shopapotheke.qa.taf.core.web.core.BasePage;
import com.shopapotheke.qa.taf.core.web.element.base.ElementLocator;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.script.ScriptException;

public class LoginPage extends BasePage {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(LoginPage.class);
    private static ElementLocator elementLocator;
    private RemoteWebDriver driver;


    public LoginPage() {

        this.driver = Session.getWebDriver();
        this.elementLocator = new ElementLocator(this.driver);

    }


    //private final By userName = By.id("login-form-username");
    private final By userName = By.name("username");
    private final By userPassword = By.name("password");
    private final By loginButton = By.name("loginForm-submit-button");

    private final By loginPanelHeader = By.xpath(".//*[@id=\"content\"]/article/div/div/div[1]/div/h2");


    /**
     * Action: Enter email address to text field
     * @param email - (eg- testApotheke@gmail.com)
     */
    public LoginPage enterEmail(String email) throws ScriptException, FrameworkException {

        elementLocator().textField(userName).enterText(email);

        return this;

    }

    /**
     * Action: Enter password to the text field
     * @param password - (eg- apotheke@123)
     */
    public LoginPage enterPassword(String password) throws FrameworkException, ScriptException {

        elementLocator().textField(userPassword).enterText(password);

        return this;

    }

    /**
     * Action: Click Login button in the login panel
     */
    public LoginPage clickLoginButton() throws FrameworkException, ScriptException {

        elementLocator().button(loginButton).click();
        verify().verifyTrue(true,"test");

        return this;

    }

    /**
     * Action: Click Anti Robot button
     */
    public LoginPage clickAntiRobot() throws FrameworkException, ScriptException {

        elementLocator().button(loginButton).click();
        verify().verifyTrue(true,"test");

        return this;

    }

    /**
     * Verify: Verify web site header
     * @param expectedTittle (eg- Shop Apotheke)
     */
    public LoginPage verifyWebSiteHeaderLabel(String expectedTittle) throws AssertionException {
        String actualTittle =  driver.getTitle();
        verify().verifyEquals(actualTittle,expectedTittle,"Expected tittle is not visible");
        return this;
    }

    /**
     * Verify: Verify Login panel header
     * @param expectedTittle - (eg- Login Shop Apotheke)
     */
    public LoginPage verifyLoginPanelHeaderLabel(String expectedTittle) throws FrameworkException {
        String actualTittle =  elementLocator().label(loginPanelHeader).getText();
        verify().verifyEquals(actualTittle,expectedTittle,"Expected tittle is not visible");
        return this;
    }
}
