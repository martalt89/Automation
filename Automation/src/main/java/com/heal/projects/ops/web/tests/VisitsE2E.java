package com.heal.projects.ops.web.tests;

import com.heal.framework.foundation.SysTools;
import com.heal.framework.restAPI.OpsAPI;
import com.heal.framework.restAPI.PatientAPI;
import com.heal.framework.restAPI.VisitsAPI;
import com.heal.framework.test.TestBase;
import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.projects.ops.web.pages.*;
import com.heal.projects.patient.web.pages.Menu;
import com.heal.projects.patient.web.pages.VisitsPage;
import com.relevantcodes.extentreports.LogStatus;
import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.text.NumberFormat;
import java.text.ParseException;


public class VisitsE2E extends TestBase  {

        private TestData testDataAccount = new TestData(TestData.ACCOUNT_SHEET);
//        private VisitsAPI visitsAPI = new VisitsAPI(testDataAccount.sEmail, testDataAccount.sPassword);
        private VisitsAPI visitsAPI = new VisitsAPI("vahan+" + getParameters().get("ENV").toString() + "@heal.com", testDataAccount.sPassword);
        private OpsAPI opsAPI = new OpsAPI("vahan+oc@heal.com", "Heal4325");
        private String visit_id = visitsAPI.createVisit();
        private int ilogCounter=1;

    @BeforeClass(alwaysRun = true)
    public void setup(){
        try {
            opsAPI.cancelVisit(opsAPI.getVisits("Dr. Vahan", "NOT_FINISHED", 7));
        }catch (JSONException e){
            System.out.println("Please make sure your OPS username and password is correct.");
        }
    }

    @AfterClass (alwaysRun = true)
    public void cleanUp(){
        try {
            opsAPI.cancelVisit(visit_id);
        } catch (Exception ex){
            //do nothing
        }
    }

    //********************* Test cases *********************
    @Test(groups = {"dev", "critical"})
    public void editVisitSymptoms() {
        if (visit_id=="") throw new SkipException("CreateVisit api did not provide a valid visit code.");

            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            String sSymptoms = "Added with auto tests";
            getExtentTest().log(LogStatus.INFO, visit_id + " booked");
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
            SysTools.sleepFor(3);
            visit.editSymptoms(sSymptoms);
            ilogCounter++;
            visit.waitForTextChange("Symptoms", 10);
            menu.verifyToastMessage("Verify Symptoms was updated ", "Symptoms updated successfully !");
            assertEquals("Symptoms field update", visit.getFieldValue("Symptoms"), sSymptoms, 20);
            visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
            assertEquals("comparing log details count for each event triggered", visit.LogDetailsCount(), ilogCounter);
    }

    @Test(groups = {"dev", "critical"}, priority=1)
    public void changeProviderManualTimeSet() {
        if (visit_id=="") throw new SkipException("CreateVisit api did not provide a valid visit code.");
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            getExtentTest().log(LogStatus.INFO, "Visit ID: " + visit_id);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
            visit.waitForPageReady(VisitDetailsModalPage.URL + "#" + visit_id);
            visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_VAHAN, VisitDetailsModalPage.MA_KETTEL);
            visit.editManualTime(SysTools.healTime10MinAhead());
            visit.oChangetBtn.click();
            ilogCounter++;
            menu.verifyToastTitle("Verifying toast message ", "OK:");
            visit.switchToUrlWithVisitCode(CreateVisitPage.URL + "#" + visit_id);
            visit.checkVisitStatusWithRefresh("DOCTOR_ASSIGNED", 10);
            visitsPage.filterVisits(visit_id);
            assertEquals("comparing log details count for each event triggered", visit.LogDetailsCount(), ilogCounter);
    }

    @Test(groups = {"dev", "critical"}, dependsOnMethods = { "changeProviderManualTimeSet" }, priority=1)
    public void startVisit() {
            String visitNotes = "ignore-Automation test running";
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
            getExtentTest().log(LogStatus.INFO, "Visit ID: " + visit_id);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
            visit.addVisitNotes(visitNotes);
            ilogCounter++;
            menu.verifyToastMessage("Verify visit notes updated successfully", "The visit notes updated successfully!");
            assertEquals("Symptoms field update", visit.getFieldValue("Visit Notes"), visitNotes, 10);
            visit.startVisit();
            ilogCounter++;
            visit.switchToUrlWithVisitCode(CreateVisitPage.URL + "#" + visit_id);
            visit.checkVisitStatusWithRefresh("STARTED", 10);
            assertMatches("Verify visit details modal contains 'STARTED' Status", visit.oVisitStatus.getText(), "STARTED");
            visitsPage.filterVisits(visit_id);
            visitsPage.getStatusByVisitCode(visit_id).waitForVisible();
            assertEquals("comparing log details count for each event triggered", visit.LogDetailsCount(), ilogCounter, 10);
    }

    @Test(groups = {"dev", "critical"}, dependsOnMethods = { "changeProviderManualTimeSet",  "startVisit" }, priority=1)
    public void endVisit() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            getExtentTest().log(LogStatus.INFO, "Visit ID: " + visit_id);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
            visit.endVisit();
            menu.verifyToastTitle("Verifying toast message ", "OK:");
            visit.switchToUrlWithVisitCode(CreateVisitPage.URL + "#" + visit_id);
            visit.checkVisitStatusWithRefresh("FULLY_PAID", 10);
            assertMatches("Verify visit details modal contains 'FULLY_PAID' Status", visit.oVisitStatus.getText(), "FULLY_PAID");
            visitsPage.filterVisits(visit_id);
            visitsPage.getStatusByVisitCode(visit_id).waitForVisible();
            verifyEquals("Verify specified visit code row contains 'FULLY_PAID' in status column", visitsPage.getStatusByVisitCode(visit_id).getText(), "FULLY PAID");
            assertEquals("comparing log details count for each event triggered", visit.LogDetailsCount(), ilogCounter);
    }

    @Test(groups = {"dev", "critical"}, dependsOnMethods = { "changeProviderManualTimeSet",  "startVisit", "endVisit"})
    public void refundVisitPartial() {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        OpsMenu opsMenu = new OpsMenu(dr);
        getExtentTest().log(LogStatus.INFO, "Visit ID: " + visit_id);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
//        getExtentTest().log(LogStatus.INFO, "tttteeeeesssssstttttt"); //ToDO add info logs to the tests
        visit.switchToUrlWithVisitCode(VisitDetailsModalPage.URL + "#" + visit_id);
        visit.selectPartialRefund("50","Automated test");
        opsMenu.verifyToastTitle("Verify toast title ", "OK:");
    }



}