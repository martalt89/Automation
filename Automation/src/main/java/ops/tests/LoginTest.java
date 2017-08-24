package ops.tests;

import framework.test.TestBase;
import framework.web.CommonWebElement;
import ops.pages.DashboardPage;
import ops.pages.OpsLoginPage;
import ops.pages.OpsMenu;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @Test(groups = {"dev", "critical"})
    public void loginWithValidCredentials() {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        OpsMenu menu = new OpsMenu(dr);
        DashboardPage dashboardPage = new DashboardPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        verifyVisible("Verify Menu", menu.oMenuArea);
        verifyVisible("Verify Zipcode box", menu.oZipcodeSearch);
        //check that Dashboard is displayed after login
        verifyVisible("Verify Dashboard page title is displayed", dashboardPage.oOperationsViewTitle);
    }
}
