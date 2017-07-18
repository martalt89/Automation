package patient.tests;

import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import patient.pages.HomePage;
import patient.pages.LoginPage;
import patient.pages.Menu;
import patient.pages.VisitsPage;
import utilities.DriverManager;

public class VisitsTest extends WebBase {

    WebDriver dr = DriverManager.getDriver();

    @Test
    @Parameters({ "url" })
    public void accessVisitPage(String url) throws Exception {
        CommonWebElement.setbMonitorMode(true);
        WebDriver dr = DriverManager.getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr, url);
        HomePage homePage = new HomePage(dr);
        VisitsPage visitsPage = new VisitsPage(dr);
        Menu menu = new Menu(dr);

        // Login on patient web app
        loginPage.login();

        // Select Visits from Menu
        homePage.selectFromMenu(menu.oVisitsLnk);

        // Verify page title
        if (!validate.verifyMatches("Verifying Visits page title ", visitsPage.oPageTitle.getText(), "Scheduled Visits")){
            System.out.println("cannot validate " + visitsPage.oPageTitle.getText());
        }

        // select all visits
        visitsPage.oIconAll.click();

        // select only visits of first profile
        visitsPage.oIcon1stPatient.click();

        // assert that visit card of first profile is displayed
        Assert.assertTrue(visitsPage.oVisitCard.isDisplayed());

        // click what to expect button
        visitsPage.oWhatToExpectBtn.jsClick();

        // sign out
        homePage.selectFromMenu(menu.oSignOutLnk);
    }
}