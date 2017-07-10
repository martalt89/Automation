package patient.tests;

import com.heal.framework.web.HealWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import patient.pages.HomePage;
import patient.pages.LoginPage;
import patient.pages.Menu;
import utilities.DriverManager;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends WebBase {

    WebDriver dr = DriverManager.getDriver();

    @Test
    @Parameters({ "url" })
    public void loginWithValidCredentials(String url) throws Exception {

        WebDriver dr = DriverManager.getDriver();

       // lib.browserGoTo(url);
        LoginPage loginPage = new LoginPage(dr, url);
        HomePage homePage = new HomePage(dr);
        Menu menu = new Menu(dr);

        HealWebElement.setbMonitorMode(false);
        loginPage.oUserNameInput.sendKeys("mayur+qatest@heal.com");
        loginPage.oPasswordInput.sendKeys("Heal4325");

       // loginPage.oRememberMe.click(1,1);

        //loginPage.oRegisterBtn.click();

        loginPage.oLoginBtn.click();

        homePage.SelectFromMenu(menu.oHomeLnk);
        homePage.SelectFromMenu(menu.oBookVisitLnk);
        homePage.SelectFromMenu(menu.oVisitsLnk);
        homePage.SelectFromMenu(menu.oProfilesLnk);
        homePage.SelectFromMenu(menu.oPamentMethodLnk);
        homePage.SelectFromMenu(menu.oSignOutLnk);
//        Thread.sleep(4000);
//        loginPage.LoginPage();


    }
}