package com.heal.projects.patient.tests;

import com.heal.projects.patient.pages.*;
import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.CommonWebValidate;
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

        assertEquals("Verifying page url ", loginPage.getCurrentUrl(), LoginPage.URL);

        loginPage.login();

        HomePage homePage = new HomePage(dr);
        homePage.waitForPageReady();

        assertEquals("Verifying page url ", homePage.getCurrentUrl(), HomePage.URL);
        verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
        assertEquals("Verifying Visits page title ", homePage.oPageTitle.getText(), "Your activity");

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
        CommonWebValidate validate = new CommonWebValidate(dr);
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
        if (!validate.verifyMatches("Verifying Visits page title ", bookVisitPage.oPageTitle.getText(), "Book Visit")){
            System.out.println("cannot validate " + bookVisitPage.oPageTitle.getText());
        }
        homePage.selectFromMenu(menu.oVisitsLnk);
        if (!validate.verifyMatches("Verifying Visits page title ", visitsPage.oPageTitle.getText(), "Scheduled Visits")){
            System.out.println("cannot validate " + visitsPage.oPageTitle.getText());
        }
        homePage.selectFromMenu(menu.oProfilesLnk);
        homePage.selectFromMenu(menu.oPaymentsLnk);
        homePage.selectFromMenu(menu.oSignOutLnk);
        System.out.println("Total number of validations executed : " + validate.getTotalCount());
        int passed = validate.getTotalCount()-validate.getFailureCount();
        System.out.println("Passed validations " + passed);
        System.out.println("Failed validations " + validate.getFailureCount());
    }
}