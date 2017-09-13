package com.heal.projects.ops.web.tests;

import com.heal.framework.foundation.SysTools;
import com.heal.framework.restAPI.PatientAPI;
import com.heal.framework.restAPI.VisitsAPI;
import com.heal.framework.test.TestBase;
import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.projects.ops.web.pages.*;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.text.ParseException;


public class VisitsE2E extends TestBase  {

        private TestData testDataAccount = new TestData(TestData.ACCOUNT_SHEET);
        private VisitsAPI visitsAPI = new VisitsAPI(testDataAccount.sEmail, testDataAccount.sPassword);
        private String visit_id = visitsAPI.createVisit();

    //********************* Test cases *********************
    @Test(groups = {"dev", "critical"})
    public void editVisitSymptoms() {
        if (visit_id=="") throw new SkipException("CreateVisit api did not provide a valid visit code.");
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        OpsMenu menu = new OpsMenu(dr);
        String sSymptoms = "Added with auto tests";
        getExtentTest().log(LogStatus.INFO,"");
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
        visit.editSymptoms(sSymptoms);
        menu.oToastContainer.waitForVisible();
        assertMatches("Successfully updated visit notes", menu.oToastTitle.getText(), "OK:");
        assertMatches("Successfully updated visit notes", menu.oToastMessage.getText(), "Symptoms updated successfully !");
        //verifyTextMatches("Verify symptoms are saved", visit.oDetailsEditSymptomsField, sSymptoms);
    }

    @Test(groups = {"dev", "critical"}, priority=1)
    public void changeProviderManualTimeSet() {
        if (visit_id=="") throw new SkipException("CreateVisit api did not provide a valid visit code.");
        System.out.println(visit_id);
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
        visit.waitForPageReady(VisitDetailsModalPage.URL + "#" + visit_id);
        visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_VAHAN, VisitDetailsModalPage.MA_KETTEL);
        visit.editManualTime(SysTools.healTime10MinAhead());
        visit.oChangetBtn.click();

        visit.switchToUrlWithVisitCode(CreateVisitPage.URL + "#" + visit_id);
        visit.checkVisitStatusWithRefresh( "DOCTOR_ASSIGNED", 10);
        visitsPage.filterVisits(visit_id);
        assertMatches("Verify visit details modal contains 'CANCELLED' Status", visit.oVisitStatus.getText(), "DOCTOR_ASSIGNED");
        verifyTextMatches("Verify Doctor column from the row containing specified visit code", visitsPage.getDoctorByVisitCode(visit_id), "Dr. Vahan Melikyan");
        verifyTextMatches("Verify Medical Assistant column from the row containing specified visit code", visitsPage.getMedicalAssistantByVisitCode(visit_id), "Michael Kettelborough");
    }

    @Test(groups = {"dev", "critical"}, dependsOnMethods = { "changeProviderManualTimeSet" }, priority=1)
    public void startVisit() {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
        visit.startVisit();
        visit.switchToUrlWithVisitCode(CreateVisitPage.URL + "#" + visit_id);
        visit.checkVisitStatusWithRefresh( "STARTED", 10);
        assertMatches("Verify visit details modal contains 'STARTED' Status", visit.oVisitStatus.getText(), "STARTED");
//        visitsPage.filterVisits(visit_id);
//        visitsPage.getStatusByVisitCode(visit_id).waitForVisible();
        //verifyTextEquals("Verify specified visit code row contains 'STARTED' in status column", visitsPage.getStatusByVisitCode(visit_id), "STARTED");
    }

    @Test(groups = {"dev", "critical"}, dependsOnMethods = { "changeProviderManualTimeSet",  "startVisit" }, priority=1)
    public void endVisit() {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
        visit.endVisit();
        visit.switchToUrlWithVisitCode(CreateVisitPage.URL + "#" + visit_id);
        visit.checkVisitStatusWithRefresh( "FULLY_PAID", 10);
        assertMatches("Verify visit details modal contains 'STARTED' Status", visit.oVisitStatus.getText(), "FULLY_PAID");
        visitsPage.filterVisits(visit_id);
        visitsPage.getStatusByVisitCode(visit_id).waitForVisible();
        verifyTextEquals("Verify specified visit code row contains 'FULLY_PAID' in status column", visitsPage.getStatusByVisitCode(visit_id), "FULLY PAID");
    }

    @Test(groups = {"dev", "critical"}, dependsOnMethods = { "changeProviderManualTimeSet",  "startVisit", "endVisit"})
    public void refundVisitPartial() {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        OpsMenu opsMenu = new OpsMenu(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
//        getExtentTest().log(LogStatus.INFO, "tttteeeeesssssstttttt"); //ToDO add info logs to the tests
        visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
        visit.selectPartialRefund("50","Automated test");
        opsMenu.verifyToastTitle("Verify toast title ", "OK:");
        opsMenu.verifyToastMessage("Verify toast message", "This visit has successfully been refunded. The patient will receive a refund from their bank in 10-14 days");
    }

}