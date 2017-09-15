package com.heal.projects.ops.web.tests;


import com.heal.framework.restAPI.VisitsAPI;
import com.heal.framework.test.TestBase;
import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.projects.ops.web.pages.OpsVisitsPage;
import com.heal.projects.ops.web.pages.VisitDetailsModalPage;
import com.heal.projects.ops.web.pages.OpsLoginPage;
import com.heal.projects.ops.web.pages.OpsMenu;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class VisitDetailsModalTest extends TestBase  {
        private TestData testDataAccount = new TestData(TestData.ACCOUNT_SHEET);
        private VisitsAPI visitsAPI = new VisitsAPI(testDataAccount.sEmail, testDataAccount.sPassword);
        private String sDashboardAndVisitCodeURL = "https://ops.qa.heal.com/dashboard#";
        private String sVisitsAndVisitCodeURL = "https://ops.qa.heal.com/visits#";

        //********************* Test cases *********************

    @Test(groups = {"dev", "critical"})
    public void changeProviderNoTime() {
        String visit_id = visitsAPI.createVisit();
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        OpsMenu opsMenu = new OpsMenu(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        visit.switchToUrlWithVisitCode(sDashboardAndVisitCodeURL+visit_id);
        visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_VAHAN, VisitDetailsModalPage.MA_KETTEL);
        visit.oChangetBtn.click();
        opsMenu.oToastContainer.waitForVisible();
        verifyTextMatches("Verify the toast-box status is 'OK'", opsMenu.oToastTitle, "OK");
        visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL);
        visit.checkVisitStatusWithRefresh( "QUEUED", 10);
        visitsPage.filterVisits(visit_id);
        visitsPage.getStatusByVisitCode(visit_id).waitForVisible();
        verifyTextMatches("Verify visit details modal contains 'QUEUED' Status", visit.oVisitStatus, "QUEUED");
        verifyTextMatches("Verify Doctor column from the row containing specified visit code", visitsPage.getDoctorByVisitCode(visit_id), VisitDetailsModalPage.DR_NILES);
        verifyTextMatches("Verify Medical Assistant column from the row containing specified visit code", visitsPage.getDoctorByVisitCode(visit_id), VisitDetailsModalPage.MA_KETTEL);
    }

    @Test(groups = {"dev", "critical"} ,priority=1)
    public void cancelVisit() {
        String visit_id = visitsAPI.createVisit();
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        visit.switchToUrlWithVisitCode(sDashboardAndVisitCodeURL+visit_id);
        //need to add a doctor before starting the visit
        visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_VAHAN, VisitDetailsModalPage.MA_KETTEL);
        visit.oChangetBtn.click();
        //todo add validation on the success green message
        visit.startVisit();
        visit.cancelVisit(VisitDetailsModalPage.HEAL_OTHER_REASON,"Cancel visit with auto tests");
        visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL);
        visit.checkVisitStatusWithRefresh( "CANCELLED", 10);
        visitsPage.filterVisits(visit_id);
        visitsPage.getStatusByVisitCode(visit_id).waitForVisible();
        verifyTextEquals("Verify visit details modal contains 'CANCELLED' Status", visit.oVisitStatus, "CANCELLED");
    }

    @Test(groups = {"dev", "critical"})
    public void refundVisitPartial() {
        String visit_id = visitsAPI.createVisit();
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        dr.navigate().to(sDashboardAndVisitCodeURL+visit_id);
        visit.selectPartialRefund("50","Automated test");
    }

    @Test(groups = {"dev", "critical"}, dependsOnMethods = { "changeProviderManualTimeSet",  "startVisit", "endVisit" }, priority=3)
    public void refundVisitTotal() {
        String visit_id = visitsAPI.createVisit();
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        visit.switchToUrlWithVisitCode(sDashboardAndVisitCodeURL+visit_id);
        visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL+visit_id);
        visit.selectTotalRefund("Automated test");
        visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL);
        visit.checkVisitStatusWithRefresh( "REFUNDED", 10);
        assertMatches("Verify visit details modal contains 'REFUNDED' Status", visit.oVisitStatus.getText(), "REFUNDED");
        visitsPage.filterVisits(visit_id);
        assertMatches("Verify specified visit code row contains 'REFUNDED' in status column", visitsPage.getStatusByVisitCode(visit_id).getText(), "REFUNDED");
    }
}