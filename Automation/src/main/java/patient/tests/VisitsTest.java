package patient.tests;

import framework.web.CommonWebElement;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;
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
    public void loginWithValidCredentials(String url) throws Exception {
        CommonWebElement.setbMonitorMode(true);
        WebDriver dr = DriverManager.getDriver();

       // lib.browserGoTo(url);
        LoginPage loginPage = new LoginPage(dr, url);
        HomePage homePage = new HomePage(dr);
        VisitsPage visitsPage = new VisitsPage(dr);
        Menu menu = new Menu(dr);

        CommonWebElement.setbMonitorMode(true);
        loginPage.oUserNameInput.sendKeys("mayur+qatest@heal.com");
        loginPage.oPasswordInput.sendKeys("Heal4325");


        loginPage.oLoginBtn.click();


        homePage.selectFromMenu(menu.oVisitsLnk);
//        Assert.assertTrue(visitsPage.oWhatToExpectBtn.isDisplayed());
//        Assert.assertTrue(visitsPage.oCancelVisitBtn.isDisplayed());
//        Assert.assertTrue(visitsPage.oScheduledVisitsTitle.isDisplayed());
//        visitsPage.oScheduledVisitsTitle.highlightMe();
//
        CommonWebElement.setbMonitorMode(true);
//        Assert.assertTrue(visitsPage.oIconAll.isDisplayed());
        visitsPage.oIconAll.click();

        CommonWebElement.setbMonitorMode(true);
        //Assert.assertTrue(visitsPage.oIconPatient.isDisplayed());
        visitsPage.oIconPatient.click();

//        visitsPage.SelectWhatToExpect();
//        homePage.selectFromMenu(menu.oVisitsLnk);
//
//
//        homePage.selectFromMenu(menu.oSignOutLnk);

//        Thread.sleep(4000);
//        loginPage.LoginPage();


    }
}