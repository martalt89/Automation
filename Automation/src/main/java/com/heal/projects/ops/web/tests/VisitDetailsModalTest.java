package com.heal.projects.ops.web.tests;

import com.heal.framework.foundation.SysTools;
import com.heal.framework.restAPI.PatientAPI;
import com.heal.framework.restAPI.VisitsAPI;
import com.heal.framework.test.TestBase;
import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.projects.ops.web.pages.OpsVisitsPage;
import com.heal.projects.ops.web.pages.VisitDetailsModalPage;
import com.heal.projects.ops.web.pages.OpsLoginPage;
import com.heal.projects.ops.web.pages.OpsMenu;
import java.lang.IllegalArgumentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

//NOTE: Work in progress
public class VisitDetailsModalTest extends TestBase  {
        private TestData testDataPatient = new TestData(TestData.PATIENT_SHEET);
        private TestData testDataAccount = new TestData(TestData.ACCOUNT_SHEET);

        private VisitsAPI visitsAPI = new VisitsAPI(testDataAccount.sEmail, testDataAccount.sPassword);
//        private VisitsAPI visitsAPI = new VisitsAPI("mihai.muresan@heal.com", "Heal4325");
        private String visit_id = visitsAPI.createVisit();
        private String sDashboardAndVisitCodeURL = "https://ops.qa.heal.com/dashboard#"+visit_id;
        private String sVisitsAndVisitCodeURL = "https://ops.qa.heal.com/visits#"+visit_id;
        private String visit_time = "08/24/2017 8:32 AM";

        @Test(groups = {"dev", "critical"}
        ,dependsOnMethods = { "changeProvider" })
//        @Test(groups = {"dev", "critical"})
        public void startVisit() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(sDashboardAndVisitCodeURL);
            //need to add a doctor before starting the visit
            visit.startVisit();
            visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL);
            assertMatches("Verify visit details modal contains 'STARTED' Status", visit.oVisitStatus.getText(), "STARTED");
            visitsPage.filterVisits(visit_id);
            verifyTextEquals("Verify specified visit code row contains 'STARTED' in status column", visitsPage.getStatusByVisitCode(visit_id), "STARTED");
        }

        @Test(groups = {"dev", "critical"}
            ,priority=1)
        public void cancelVisit() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(sDashboardAndVisitCodeURL);
            //need to add a doctor before starting the visit
            visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_NILES, VisitDetailsModalPage.MA_KETTEL);
            visit.oChangetBtn.click();
            //todo add validation on the success green message
            visit.startVisit();
            visit.cancelVisit(VisitDetailsModalPage.HEAL_OTHER_REASON,"Cancel visit with auto tests");
            visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL);
            visitsPage.filterVisits(visit_id);
            verifyTextEquals("Verify visit details modal contains 'CANCELLED' Status", visit.oVisitStatus, "CANCELLED");
        }

        @Test(groups = {"dev", "critical"})
        public void changeProvider() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(sDashboardAndVisitCodeURL);
            visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_NILES, VisitDetailsModalPage.MA_KETTEL);
            visit.editManualTime(SysTools.healTime10MinAhead()); // setting the visit schedule time 10 minutes ahead of the current time
            visit.oChangetBtn.click();
            visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL);
            visitsPage.filterVisits(visit_id);
            assertMatches("Verify visit details modal contains 'CANCELLED' Status", visit.oVisitStatus.getText(), "DOCTOR_ASSIGNED");
            verifyTextMatches("Verify Doctor column from the row containing specified visit code", visitsPage.getDoctorByVisitCode(visit_id), VisitDetailsModalPage.DR_NILES);
            verifyTextMatches("Verify Medical Assistant column from the row containing specified visit code", visitsPage.getDoctorByVisitCode(visit_id), VisitDetailsModalPage.MA_KETTEL);
        }

    @Test(groups = {"dev", "critical"})
    public void changeProviderManualTimeSet() {

        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.login();
        visit.switchToUrlWithVisitCode(sDashboardAndVisitCodeURL);
        visit.waitForPageReady(sDashboardAndVisitCodeURL);
        visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_VAHAN, VisitDetailsModalPage.MA_KETTEL);
        visit.editManualTime(SysTools.healTime10MinAhead());
        visit.oChangetBtn.click();
        visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL);
        visit.checkVisitStatusWithAfterRefresh(visit_id, "DOCTOR_ASSIGNED", 10);
        //visit.oQeuedIcon.waitForElement(15);
        visitsPage.filterVisits(visit_id);
        assertMatches("Verify visit details modal contains 'CANCELLED' Status", visit.oVisitStatus.getText(), "DOCTOR_ASSIGNED");
        verifyTextMatches("Verify Doctor column from the row containing specified visit code", visitsPage.getDoctorByVisitCode(visit_id), VisitDetailsModalPage.DR_NILES);
        verifyTextMatches("Verify Medical Assistant column from the row containing specified visit code", visitsPage.getDoctorByVisitCode(visit_id), VisitDetailsModalPage.MA_KETTEL);
    }

        @Test(groups = {"dev", "critical"})
        public void editVisitSymptoms() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            String sSymptoms = "Added with auto tests";
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(sDashboardAndVisitCodeURL);
            visit.editSymptoms(sSymptoms);
            menu.oToastContainer.waitForVisible();
            assertMatches("Successfully updated visit notes", menu.oToastTitle.getText(), "OK:");
            assertMatches("Successfully updated visit notes", menu.oToastMessage.getText(), "Symptoms updated successfully !");
            //verifyTextMatches("Verify symptoms are saved", visit.oDetailsEditSymptomsField, sSymptoms);
        }

        @Test(groups = {"dev", "critical"})
        public void addInsurance() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            PatientAPI patientAPI = new PatientAPI(testDataAccount.sEmail, testDataAccount.sPassword);
            try {
                patientAPI.addPatientFromExcel();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(sDashboardAndVisitCodeURL);
            visit.updateInsurance(VisitDetailsModalPage.BLUE, "COST_ESTIMATES_025", "BC001");
            //todo: verify is test was successful
        }

        @Test(groups = {"dev", "critical"})
        public void refundVisitTotalRefund() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(sDashboardAndVisitCodeURL);
            visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_NILES, VisitDetailsModalPage.MA_KETTEL);
            dr.navigate().refresh();
            visit.startVisit();
            dr.navigate().refresh();
            visit.endVisit();
            visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL);
            visit.selectTotalRefund("Automated test");
            visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL);
            verifyTextEquals("Verify visit details modal contains 'STARTED' Status", visit.oVisitStatus, "REFUNDED");
            visitsPage.filterVisits(visit_id);
            verifyTextEquals("Verify specified visit code row contains 'REFUNDED' in status column", visitsPage.getStatusByVisitCode(visit_id), "REFUNDED");
        }

        @Test(groups = {"dev", "critical"})
        public void refundVisitPartialRefund() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            dr.navigate().to(sDashboardAndVisitCodeURL);
            visit.selectPartialRefund("50","Automated test");
        }

//todo: these can be invoked only if we have a FULL_PAID visit
//        @Test(groups = {"dev", "critical"})
//        public void refundVisitTotalRefund() {
//            CommonWebElement.setbMonitorMode(false);
//            WebDriver dr = getDriver();
//            CommonWebValidate validate = new CommonWebValidate(dr);
//            OpsLoginPage loginPage = new OpsLoginPage(dr);
//            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
//            loginPage.goTo();
//            loginPage.waitForPageReady();
//            loginPage.login();
//            visit.switchToUrlWithVisitCode(visit_url);
//            visit.selectTotalRefund("Automated test");
//        }
//
//        @Test(groups = {"dev", "critical"})
//        public void refundVisitPartialRefund() {
//            CommonWebElement.setbMonitorMode(false);
//            WebDriver dr = getDriver();
//            CommonWebValidate validate = new CommonWebValidate(dr);
//            OpsLoginPage loginPage = new OpsLoginPage(dr);
//            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
//            loginPage.goTo();
//            loginPage.waitForPageReady();
//            loginPage.login();
//            dr.navigate().to(visit_url);
//            visit.selectPartialRefund("50","Automated test");
//        }

}