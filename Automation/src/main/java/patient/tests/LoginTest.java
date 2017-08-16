package patient.tests;

import foundation.SysTools;
import framework.test.TestBase;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import patient.pages.*;
import utilities.DriverManager;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {


    @Test (groups = { "devv","smoke", "regression", "critical" })
    public void loginWithValidCredentials() throws Exception {

        WebDriver dr = getDriver();
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        assertEquals("Verifying page url ", loginPage.getCurrentUrl(), LoginPage.URL + "-fail");

//        loginPage.login();
//
//        HomePage homePage = new HomePage(dr);
//        homePage.waitForPageReady();
//        assertEquals("Verifying page url ", homePage.getCurrentUrl(), HomePage.URL);
//        verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
//        assertEquals("Verifying Visits page title ", homePage.oPageTitle.getText(), "Scheduled Visits");
//
//        Menu menu = new Menu(dr);
//        menu.selectFromMenu(menu.oVisitsLnk);
//        //you can choose which visits to cancel, start from 1, count from left to right
//        String reason = "Other";
//        String notes = "automation";
//        homePage.cancelVisit(1, "Other", "automation");
//        Reporter.log("cancel visit with reason: "+ reason +", notes: " + notes);
//        menu.oLoadingBar.sync(10);
//        menu.selectFromMenu(menu.oSignOutLnk);
//        loginPage.waitForPageReady();
//        assertEquals("Verifying page url ", loginPage.getCurrentUrl(), LoginPage.URL);


    }

    @Test (groups = { "regression"})
    public void checkMenuLinksLoggedIn() throws Exception {
        WebDriver dr = getDriver();
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();

        assertEquals("Verifying page url ", loginPage.getCurrentUrl(), LoginPage.URL);
//        Menu menu = new Menu(dr);
//
//        loginPage.login();
//        HomePage homePage = new HomePage(dr);
//        homePage.waitForPageReady();
//        menu.selectFromMenu(menu.oBookVisitLnk);
//
//        BookVisitPage bookVisitPage = new BookVisitPage(dr);
//        bookVisitPage.waitForPageReady();
//        verifyMatches("Verifying Book Visits page title ", bookVisitPage.oPageTitle.getText(), "Book Visit");

//        menu.selectFromMenu(menu.oVisitsLnk);
//        VisitsPage visitsPage = new VisitsPage(dr);
//        visitsPage.waitForPageReady();
//        verifyMatches("Verifying Visits page title ", visitsPage.oPageTitle.getText(), "Scheduled Visit");
    }
}