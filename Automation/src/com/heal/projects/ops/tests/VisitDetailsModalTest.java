package com.heal.projects.ops.tests;

import com.heal.framework.restAPI.VisitsAPI;
import com.heal.framework.test.TestBase;
import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.CommonWebValidate;
import com.heal.projects.ops.pages.OpsVisitsPage;
import com.heal.projects.ops.pages.VisitDetailsModalPage;
import com.heal.projects.ops.pages.OpsLoginPage;
import com.heal.projects.ops.pages.OpsMenu;
import com.heal.projects.patient.pages.VisitsPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

//NOTE: Work in progress
public class VisitDetailsModalTest extends TestBase  {
        private TestData testData = new TestData(TestData.PATIENT_SHEET);
        private VisitsAPI visitsAPI = new VisitsAPI("mihaix12@heal.com", "Heal4325");
        private String visit_id = visitsAPI.createVisit();
        private String sDashboardAndVisitCodeURL = "https://ops.qa.heal.com/dashboard#"+visit_id;
        private String sVisitsAndVisitCodeURL = "https://ops.qa.heal.com/visits#"+visit_id;
        private String visit_time = "08/24/2017 8:32 AM";

        @Test(groups = {"dev", "critical"})
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
            visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_NILES, VisitDetailsModalPage.MA_KETTEL);
            visit.startVisit();
            visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL);
            verifyTextEquals("Verify visit details modal contains 'STARTED' Status", visit.oVisitStatus, "STARTED");
            visitsPage.filterVisits(visit_id);
            verifyTextEquals("Verify specified visit code row contains 'STARTED' in status column", visitsPage.getStatusByVisitCode(visit_id), "STARTED");
        }

        @Test(groups = {"dev", "critical"})
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
            visit.switchToUrlWithVisitCode(sVisitsAndVisitCodeURL);
            visitsPage.filterVisits(visit_id);
            verifyTextMatches("Verify Doctor column from the row containing specified visit code", visitsPage.getDoctorByVisitCode(visit_id), VisitDetailsModalPage.DR_NILES);
            verifyTextMatches("Verify Medical Assistant column from the row containing specified visit code", visitsPage.getDoctorByVisitCode(visit_id), VisitDetailsModalPage.MA_KETTEL);
        }

        @Test(groups = {"dev", "critical"})
        public void editVisitSymptoms() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            String sSymptoms = "Added with auto tests";
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(sDashboardAndVisitCodeURL);
            visit.editSymptoms(sSymptoms);
            verifyTextMatches("Verify symptoms are saved", visit.oDetailsSymptoms, sSymptoms);
        }

        @Test(groups = {"dev", "critical"})
        public void addInsurance() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
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

}