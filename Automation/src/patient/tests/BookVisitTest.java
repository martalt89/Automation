package patient.tests;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.CommonWebValidate;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import patient.pages.HomePage;
import patient.pages.LoginPage;
import patient.pages.Menu;
import patient.pages.VisitsPage;
import patient.pages.BookVisitPage;
import utilities.DriverManager;

public class BookVisitTest extends WebBase {

    WebDriver dr = DriverManager.getDriver();

    @Test
    @Parameters({ "url" })
    public void bookVisit(String url) throws Exception {
        CommonWebElement.setbMonitorMode(true);
        WebDriver dr = DriverManager.getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr, url);
        HomePage homePage = new HomePage(dr);
        VisitsPage visitsPage = new VisitsPage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        Menu menu = new Menu(dr);

        // Login on patient web app
        loginPage.login();

        // Select Book Visit from Menu
        homePage.selectFromMenu(menu.oBookVisitLnk);

        // Verify page title
        if (!validate.verifyMatches("Verifying Book visit page title ", bookVisitPage.oPageTitle.getText(), "Book Visit")){
            System.out.println("cannot validate " + bookVisitPage.oPageTitle.getText());
        }

        // Select a non life-threatening medical emergency
        bookVisitPage.oEmergencyNoBtn.click();

        // Go back to Book visit
        homePage.selectFromMenu(menu.oBookVisitLnk);

        // Select a life-threatening medical emergency
        bookVisitPage.oEmergencyYesBtn.click();

        // Click Ok
        bookVisitPage.oOkBtn.click();

        // Verify page title
        if (!validate.verifyMatches("Verifying Visits page title ", bookVisitPage.oPageTitle.getText(), "Book Visit")){
            System.out.println("cannot validate " + bookVisitPage.oPageTitle.getText());
        }

        // Sign out
        homePage.selectFromMenu(menu.oSignOutLnk);

    }
}