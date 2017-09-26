package com.heal.projects.patient.mobile.tests;

import com.heal.framework.foundation.SysTools;
import com.heal.framework.test.TestBase;
import com.heal.projects.patient.mobile.pages.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.http.protocol.HTTP;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.management.relation.Relation;

/**
 * Created by vahanmelikyan on 9/2/2017.
 */
public class M_LoginTest  extends TestBase {

    @Test
    public void testLoop(){
        int numberOfVisitsToBook = 5;
        int passedRuns = 0;
        int failedRuns = 0;
        for (int i = 0; i < numberOfVisitsToBook; i++) {
            try {


                passedRuns++;
            } catch (Exception e) {
                failedRuns++;

                e.printStackTrace();
            }
            System.out.println("Passed " + passedRuns);
            System.out.println("Failed " + failedRuns);
        }
        System.out.println(passedRuns + " Passed Runs"); // display how many times the test passed on given visits booked
        System.out.println(failedRuns + " Failed Runs"); // display how many times the test failed on given visits booked
    }



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

        homePage.oLoginBtn.click();
//        homePage.clickLoginButton();
        M_LoginPage loginPage = new M_LoginPage(dr);
        assertEquals("verify email field is displayed", loginPage.oEmailInput.isDisplayed(), true);
        assertEquals("verify password field is displayed", loginPage.oPasswordInput.isDisplayed(), true);
        assertEquals("verify Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");
        assertEquals("verify login button is displayed", loginPage.oLoginBtn.isDisplayed(), true);
    }

    @Test
    public void loginWithValidCredentials(){

        WebDriver dr = getDriver();
        M_MenuPage menu=new M_MenuPage(dr);
        M_MainPage homePage = new M_MainPage(dr);

        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
        verifyVisible("Verify Login Button is displayed", homePage.oLoginBtn);
        homePage.clickSettingsButton();

        M_SettingsPage settingsPage = new M_SettingsPage(dr);
        settingsPage.setEnv("qa");
        homePage.oLoginBtn.click();
//        homePage.clickLoginButton();
        M_LoginPage loginPage = new M_LoginPage(dr);
        assertEquals("verify Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");

        loginPage.login();
        menu.oProgressBar.sync(60);




    }

  




}
