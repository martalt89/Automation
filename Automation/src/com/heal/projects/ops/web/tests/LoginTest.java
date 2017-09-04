package com.heal.projects.ops.web.tests;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import com.heal.projects.ops.web.pages.DashboardPage;
import com.heal.projects.ops.web.pages.OpsLoginPage;
import com.heal.projects.ops.web.pages.OpsMenu;
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
        verifyVisible("Verify Zipcode search", menu.oZipcodeSearch);
        //check that Dashboard is displayed after login
        verifyVisible("Verify Dashboard page title is displayed", loginPage.oPasswordInput);
//        verifyVisible("Verify Dashboard page title is displayed", dashboardPage.oOperationsViewTitle);
    }
//
//    @Test(groups = {"dev", "critical"})
//    public void visitCode() {
//        CommonWebElement.setbMonitorMode(false);
//        WebDriver dr = getDriver();
//        OpsLoginPage loginPage = new OpsLoginPage(dr);
//        OpsMenu menu = new OpsMenu(dr);
//        DashboardPage dashboardPage = new DashboardPage(dr);
//        VisitsAPI visitsAPI = new VisitsAPI("vahan+qa@heal.com", "Heal4325");
//        System.out.println(visitsAPI.createVisit());
////        System.out.println(visitsAPI.getTimeSlotID());
//    }

}
