package TestCases;

import com.shopapotheke.qa.taf.core.base.BaseTest;
import com.shopapotheke.qa.taf.core.exceptions.FrameworkException;
import com.shopapotheke.qa.taf.pageObjects.LoginPage;
import org.testng.annotations.Test;

import javax.script.ScriptException;

public class SA_L_01 extends BaseTest {

    @Test
    public void verify_user_can_login_with_valid_credentials() throws FrameworkException, ScriptException {

        String email = "vidushan.senevirathna@gmail.com";
        String password = "shopApotheke@123";
        String loginPageTittle = "Anmelden";
        String accountPageTittle = "Mein Konto / Ihre Übersicht";
        String loginPanelHeader = "Mit Kundenkonto einloggen";


        new LoginPage().
                verifyWebSiteHeaderLabel(loginPageTittle).
                verifyLoginPanelHeaderLabel(loginPanelHeader).
                enterEmail(email).
                enterPassword(password).
                clickLoginButton().
                verifyWebSiteHeaderLabel(accountPageTittle);
    }
}
