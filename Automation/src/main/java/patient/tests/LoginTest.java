package patient.tests;

import foundation.SysTools;
import framework.test.TestBase;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;
import patient.pages.*;
import utilities.DriverManager;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {


    @Test (groups = { "devv","smoke", "regression", "critical" })
//    @Parameters({ "url" })
    public void loginWithValidCredentials() throws Exception {

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
        assertEquals("Verifying Visits page title ", homePage.oPageTitle.getText(), "Scheduled Visits");

        Menu menu = new Menu(dr);
        menu.selectFromMenu(menu.oVisitsLnk);
        //you can choose which visits to cancel, start from 1, count from left to right
        homePage.cancelVisit(1, "Other", "automation");
        menu.oLoadingBar.sync(10);
        menu.selectFromMenu(menu.oSignOutLnk);
        loginPage.waitForPageReady();
        assertEquals("Verifying page url ", loginPage.getCurrentUrl(), LoginPage.URL);


    }

    @Test (groups = { "regression"})
//    @Parameters ({"url"})
    public void checkMenuLinksLoggedIn() throws Exception {
        CommonWebElement.setbMonitorMode(false);

        WebDriver dr = DriverManager.getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr);
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