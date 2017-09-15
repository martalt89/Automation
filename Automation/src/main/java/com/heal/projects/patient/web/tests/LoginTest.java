package com.heal.projects.patient.web.tests;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.CommonWebValidate;
import com.heal.projects.patient.web.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {


    @Test (groups = {"smoke", "regression", "critical" })
//    @Test (retryAnalyzer = RetryAnalyzer.class)
//    @Parameters({ "url" })
    public void loginWithValidCredentials() throws Exception {

        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        assertEquals("Verifying page url ", loginPage.getCurrentUrl(), LoginPage.URL + "s");

        loginPage.login();

        HomePage homePage = new HomePage(dr);
        homePage.waitForPageReady();

        assertEquals("Verifying page url ", homePage.getCurrentUrl(), HomePage.URL);
        verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
        assertEquals("Verifying Visits page title ", homePage.oPageTitle.getText(), "Visits");

        Menu menu = new Menu(dr);

        menu.selectFromMenu(menu.oSignOutLnk);
        loginPage.waitForPageReady();
        assertEquals("Verifying page url ", loginPage.getCurrentUrl(), LoginPage.URL);


    }

    @Test (groups = { "regression"})
//    @Parameters ({"url"})
    public void checkMenuLinksLoggedIn() throws Exception {
        CommonWebElement.setbMonitorMode(false);

        WebDriver dr = getDriver();
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageLoad();
        HomePage homePage = new HomePage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        VisitsPage visitsPage = new VisitsPage(dr);
        Menu menu = new Menu(dr);

        loginPage.login();
        homePage.selectFromMenu(menu.oHomeLnk);
        homePage.selectFromMenu(menu.oBookVisitLnk);
        verifyMatches("Verifying Visits page title ", bookVisitPage.oPageTitle.getText(), "Book Visit");

        homePage.selectFromMenu(menu.oVisitsLnk);
        verifyMatches("Verifying Visits page title ", visitsPage.oPageTitle.getText(), "Visits");
        homePage.selectFromMenu(menu.oProfilesLnk);
        homePage.selectFromMenu(menu.oPaymentsLnk);
        homePage.selectFromMenu(menu.oSignOutLnk);

    }
}