package com.heal.projects.patient.web.tests;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import com.heal.projects.patient.web.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {


    /**
     * This will run a test in loop for validation purposes
     */
    @Test
    public void testLoop(){
        int numberOfVisitsToBook = 20;
        int passedRuns = 0;
        int failedRuns = 0;
        for (int i = 0; i < numberOfVisitsToBook; i++) {
            try {
                loginWithValidCredentials();
                checkMenuLinksLoggedIn();
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