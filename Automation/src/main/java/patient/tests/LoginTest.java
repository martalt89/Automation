package patient.tests;

import framework.test.WebBaseTest;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import patient.pages.*;
import utilities.DriverManager;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends WebBaseTest {


    @Test (groups = { "smoke", "regression" })
    @Parameters({ "url" })
    public void loginWithValidCredentials(String url) throws Exception {
        CommonWebElement.setbMonitorMode(false);

        WebDriver dr = getDriver();
        LoginPage loginPage = new LoginPage(dr, url);
        HomePage homePage = new HomePage(dr);

//      loginPage.login("AutoTest_18-62Years@heal.com","Heal@123"); //dev username and password
        loginPage.login();
        homePage.validateTitle("Scheduled Visits");
    }

    @Test (groups = { "regression"})
    @Parameters ({"url"})
    public void checkMenuLinksLoggedIn(String url) throws Exception {
        CommonWebElement.setbMonitorMode(false);

        WebDriver dr = getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr, url);
        HomePage homePage = new HomePage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        VisitsPage visitsPage = new VisitsPage(dr);
        Menu menu = new Menu(dr);

        loginPage.login();
        homePage.selectFromMenu(menu.oHomeLnk);
        homePage.validateTitle("Scheduled Visits");
        homePage.selectFromMenu(menu.oBookVisitLnk);
        if (!validate.verifyMatches("Verifying Visits page title ", bookVisitPage.oPageTitle.getText(), "Book Visit")){
            System.out.println("cannot validate " + bookVisitPage.oPageTitle.getText());
        }
        homePage.selectFromMenu(menu.oVisitsLnk);
        if (!validate.verifyMatches("Verifying Visits page title ", visitsPage.oPageTitle.getText(), "Scheduled Visits")){
            System.out.println("cannot validate " + visitsPage.oPageTitle.getText());
        }
        homePage.selectFromMenu(menu.oProfilesLnk);
        homePage.selectFromMenu(menu.oPaymentMethodLnk);
        homePage.selectFromMenu(menu.oSignOutLnk);
        System.out.println("Total number of validations executed : " + validate.getTotalCount());
        int passed = validate.getTotalCount()-validate.getFailureCount();
        System.out.println("Passed validations " + passed);
        System.out.println("Failed validations " + validate.getFailureCount());
    }
}