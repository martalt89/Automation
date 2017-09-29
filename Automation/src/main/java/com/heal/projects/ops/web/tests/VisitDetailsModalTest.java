package com.heal.projects.ops.web.tests;


import com.heal.framework.foundation.SysTools;
import com.heal.framework.restAPI.VisitsAPI;
import com.heal.framework.test.TestBase;
import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.projects.ops.web.pages.*;
import com.relevantcodes.extentreports.LogStatus;
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
        getExtentTest().log(LogStatus.INFO, visit_id + " visit booked");
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        OpsMenu opsMenu = new OpsMenu(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
        visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_VAHAN, VisitDetailsModalPage.MA_KETTEL);
        visit.oChangetBtn.click();
        opsMenu.oToastContainer.waitForVisible();
        verifyTextMatches("Verify the toast-box status is 'OK'", opsMenu.oToastTitle, "OK:");
        visit.switchToUrlWithVisitCode(CreateVisitPage.URL + "#" + visit_id);
        visit.checkVisitStatusWithRefresh( "QUEUED", 10);
        visitsPage.filterVisits(visit_id);
        visitsPage.getStatusByVisitCode(visit_id).waitForVisible();
        verifyTextMatches("Verify visit details modal contains 'QUEUED' Status", visit.oVisitStatus, "QUEUED");
    }

    @Test(groups = {"dev", "critical"} ,priority=1)
    public void cancelVisit() {
        String visit_id = visitsAPI.createVisit();
        getExtentTest().log(LogStatus.INFO, visit_id + " visit booked");
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
        //need to add a doctor before starting the visit
        visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_VAHAN, VisitDetailsModalPage.MA_KETTEL);
        visit.oChangetBtn.click();
        //todo add validation on the success green message
        visit.startVisit();
        visit.cancelVisit(VisitDetailsModalPage.HEAL_OTHER_REASON,"Cancel visit with auto tests");
        visit.switchToUrlWithVisitCode(CreateVisitPage.URL + "#" + visit_id);
        visit.checkVisitStatusWithRefresh( "CANCELLED", 10);
        visitsPage.filterVisits(visit_id);
        visitsPage.getStatusByVisitCode(visit_id).waitForVisible();
        verifyTextEquals("Verify visit details modal contains 'CANCELLED' Status", visit.oVisitStatus, "CANCELLED");
    }


    @Test(groups = {"dev", "critical"})
    public void refundVisitTotal() {
        String myNewVisit = visitsAPI.createVisit();
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        OpsMenu menu = new OpsMenu(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + myNewVisit);
        //visit.switchToUrlWithVisitCode(CreateVisitPage.URL + "#" + myNewVisit);
        visit.waitForPageReady(VisitDetailsModalPage.URL + "#" + myNewVisit);
        visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_VAHAN, VisitDetailsModalPage.MA_KETTEL);
        visit.editManualTime(SysTools.healTime10MinAhead());
        visit.oChangetBtn.click();

        menu.verifyToastTitle("Verifying toast message ", "OK:");
        visit.switchToUrlWithVisitCode(CreateVisitPage.URL + "#" + myNewVisit);
        visit.checkVisitStatusWithRefresh( "DOCTOR_ASSIGNED", 10);
        visit.startVisit();
        visit.switchToUrlWithVisitCode(CreateVisitPage.URL + "#" + myNewVisit);
        visit.checkVisitStatusWithRefresh( "STARTED", 10);
        assertMatches("Verify visit details modal contains 'STARTED' Status", visit.oVisitStatus.getText(), "STARTED");

        visit.endVisit();
        menu.verifyToastTitle("Verifying toast message ", "OK:");
        visit.switchToUrlWithVisitCode(CreateVisitPage.URL + "#" + myNewVisit);
        visit.checkVisitStatusWithRefresh( "FULLY_PAID", 10);
        assertMatches("Verify visit details modal contains 'FULLY_PAID' Status", visit.oVisitStatus.getText(), "FULLY_PAID");
        //  visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL);
        visit.selectTotalRefund("Automated test");
        visit.checkVisitStatusWithRefresh( "REFUNDED", 10);
        assertMatches("Verify visit details modal contains 'REFUNDED' Status", visit.oVisitStatus.getText(), "REFUNDED");
        visitsPage.filterVisits(myNewVisit);
        assertMatches("Verify specified visit code row contains 'REFUNDED' in status column", visitsPage.oStatusBadge.getText(), "REFUNDED");
    }

}