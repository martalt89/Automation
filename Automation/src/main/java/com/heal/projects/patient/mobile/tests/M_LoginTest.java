package com.heal.projects.patient.mobile.tests;

import com.heal.framework.test.TestBase;
import com.heal.projects.patient.mobile.pages.M_DashboardPage;
import com.heal.projects.patient.mobile.pages.M_MainPage;
import com.heal.projects.patient.mobile.pages.M_LoginPage;
import com.heal.projects.patient.mobile.pages.M_SettingsPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by vahanmelikyan on 9/2/2017.
 */
public class M_LoginTest  extends TestBase {


    @Test
    public void launchTest(){
        WebDriver dr = getDriver();
        M_MainPage homePage = new M_MainPage(dr);
        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
    }

    @Test
    public void loginPageValidations(){
        WebDriver dr = getDriver();
        M_MainPage homePage = new M_MainPage(dr);
        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
        homePage.clickSettingsButton();

        M_SettingsPage settingsPage = new M_SettingsPage(dr);
        settingsPage.setEnv("qa");

        homePage.clickLoginButton();
        M_LoginPage loginPage = new M_LoginPage(dr);
        assertEquals("verify email field is displayed", loginPage.oEmailInput.isDisplayed(), true);
        assertEquals("verify password field is displayed", loginPage.oPasswordInput.isDisplayed(), true);
        assertEquals("verify Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");
        assertEquals("verify login button is displayed", loginPage.oLoginBtn.isDisplayed(), true);
    }

    @Test
    public void loginWithValidCredentials(){

        WebDriver dr = getDriver();
        M_MainPage homePage = new M_MainPage(dr);
        M_DashboardPage dashboard= new M_DashboardPage(dr);
        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
        verifyVisible("Verify Login Button is displayed", homePage.oLoginBtn);
        homePage.clickSettingsButton();

        M_SettingsPage settingsPage = new M_SettingsPage(dr);
        settingsPage.setEnv("qa");

        homePage.clickLoginButton();
        M_LoginPage loginPage = new M_LoginPage(dr);
        assertEquals("verify Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");

        loginPage.login();
        loginPage.oProgressBar.sync(60);


        dashboard.oAllowPermissionToAccessLocationOnConnectedDevice.clickAndWait(dashboard.oNoMedicalEmergencyButton,true);
        dashboard.oNoMedicalEmergencyButton.click();
        dashboard.oLocationTextField.clickAndWait(dashboard.oSelectHomeAddress,true);
        dashboard.oSelectHomeAddress.clickAndWait(dashboard.oUnitOrAptField,true);
        dashboard.oUnitOrAptField.sendKeys("#711");
        dr.navigate().back();
        dashboard.oSelectPatientProfile.click();

        dashboard.oContinueButton.click();

    }

}
