package com.heal.projects.patient.mobile.tests;

import com.heal.framework.test.TestBase;
import com.heal.projects.patient.mobile.pages.M_HomePage;
import com.heal.projects.patient.mobile.pages.M_LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by vahanmelikyan on 9/2/2017.
 */
public class M_LoginTest  extends TestBase {


    @Test
    public void loginWithValidCredentials(){

        WebDriver dr = getDriver();
        M_HomePage homePage = new M_HomePage(dr);
        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
        verifyVisible("Verify Login Button is displayed", homePage.oLoginBtn);

        homePage.clickLoginButton();
        M_LoginPage loginPage = new M_LoginPage(dr);
        loginPage.fillLoginInfoWithoutSubmit();

        assertEquals("verfiy Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");

    }

}
