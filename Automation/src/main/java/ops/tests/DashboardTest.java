package ops.tests;

import framework.test.TestBase;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import ops.pages.DashboardPage;
import ops.pages.OpsLoginPage;
import ops.pages.OpsMenu;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class DashboardTest extends TestBase {
    @Test(groups = {"dev", "critical"})
    public void checkDashboardElements() {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        DashboardPage dashboardPage = new DashboardPage(dr);
        OpsMenu menu = new OpsMenu(dr);

        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        menu.selectFromMenu(menu.oDashboardLink);
        verifyVisible("Verify dashboard title", dashboardPage.oOperationsViewTitle);
        verifyVisible("Verify All Button", dashboardPage.oAllBtn);
        verifyVisible("Verify Active Button", dashboardPage.oActiveBtn);
        verifyVisible("Verify Scheduled Button", dashboardPage.oScheduledBtn);
        verifyVisible("Verify Previous Button", dashboardPage.oPreviousBtn);
        verifyVisible("Verify Today Button", dashboardPage.oTodayBtn);
        verifyVisible("Verify Tomorrow Button", dashboardPage.oTomorrowBtn);
        verifyVisible("Verify Status table header", dashboardPage.oStatusHeader);
        verifyVisible("Verify Visit code table header", dashboardPage.oVisitCodeHeader);
        verifyVisible("Verify Service table header", dashboardPage.oServiceHeader);
        verifyVisible("Verify Patient table header", dashboardPage.oPatientHeader);
        verifyVisible("Verify Address table header", dashboardPage.oAddressHeader);
        verifyVisible("Verify Doctor table header", dashboardPage.oDoctorHeader);
        verifyVisible("Verify Assistant table header", dashboardPage.oAssistantHeader);
        verifyVisible("Verify Requested Time Slot table header", dashboardPage.oRequestedTimeSlotHeader);
        verifyVisible("Verify Scheduled Date table header", dashboardPage.oScheduledDateHeader);
        verifyVisible("Verify Reschedules table header", dashboardPage.oRescheduledHeader);
    }

    @Test(groups = {"dev", "critical"})
    public void cityCheckboxes() throws InterruptedException {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        DashboardPage dashboardPage = new DashboardPage(dr);
        OpsMenu menu = new OpsMenu(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        menu.selectFromMenu(menu.oDashboardLink);
        dashboardPage.selectOnlyOneCity("LA");
        Thread.sleep(3000);
        verifyVisible("test",dashboardPage.oLosAngelesTitle);
    }

    @Test(groups = {"dev", "critical"})
    public void searchZipcode() {
        CommonWebElement.setbMonitorMode(true);
        WebDriver dr = getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        DashboardPage dashboardPage = new DashboardPage(dr);
        OpsMenu menu = new OpsMenu(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();

        menu.searchZipcode("90201");
        verifyTextMatches("Verify Correct Zipcode Message", menu.oToastMessage, OpsMenu.ZIPCODE_CORRECT);
        menu.searchZipcode("123");
        verifyTextMatches("Verify incorrect Zipcode message", menu.oToastMessage, OpsMenu.ZIPCODE_INVALID);
        menu.searchZipcode("88888");
        verifyTextMatches("Verify", menu.oToastMessage, OpsMenu.ZIPCODE_NOT_IN_AREA);
    }

}
