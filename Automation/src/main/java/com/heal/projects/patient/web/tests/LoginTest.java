package com.heal.projects.patient.web.tests;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.CommonWebValidate;
import com.heal.projects.patient.web.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {


    @Test (groups = {"smoke", "regression", "critical" })
    public void loginWithValidCredentials() throws Exception {

        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        HomePage homePage = new HomePage(dr);
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        assertEquals("Verifying page url ", loginPage.getCurrentUrl(), LoginPage.URL);

        loginPage.login();

        homePage.oSelectLocation.waitForElement();

        assertEquals("Verifying page url ", homePage.getCurrentUrl(), HomePage.URL);
        homePage.validateTitle();
        Menu menu = new Menu(dr);

        menu.selectFromMenu(menu.oSignOutLnk);
        loginPage.waitForPageReady();
        loginPage.validateTitle();
        assertEquals("Verifying page url ", loginPage.getCurrentUrl(), LoginPage.URL);
    }

    @Test (groups = { "regression"})
//    @Parameters ({"url"})
    public void checkMenuLinksLoggedIn() throws Exception {
        CommonWebElement.setbMonitorMode(false);

        WebDriver dr = getDriver();
        LoginPage loginPage = new LoginPage(dr);
        HomePage homePage = new HomePage(dr);
        VisitsPage visitsPage = new VisitsPage(dr);
        ProfilePage profilePage = new ProfilePage(dr);
        PaymentsPage paymentsPage = new PaymentsPage(dr);
        Menu menu = new Menu(dr);
        loginPage.goTo();
        loginPage.waitForPageLoad();

        loginPage.login();

        homePage.oSelectLocation.waitForElement();
        homePage.validateTitle();
        homePage.selectFromMenu(menu.oVisitsLnk);
        homePage.selectFromMenu(menu.oProfilesLnk);
        profilePage.validateTitle();
        homePage.selectFromMenu(menu.oPaymentsLnk);
        paymentsPage.validateTitle();
        homePage.selectFromMenu(menu.oSignOutLnk);
        loginPage.validateTitle();

    }
}