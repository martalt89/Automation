package com.heal.projects.ops.tests;

import com.heal.framework.restAPI.VisitsAPI;
import com.heal.framework.test.TestBase;
import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.CommonWebValidate;
import com.heal.projects.ops.pages.VisitDetailsModalPage;
import com.heal.projects.ops.pages.OpsLoginPage;
import com.heal.projects.ops.pages.OpsMenu;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

//NOTE: Work in progress
public class VisitDetailsModalTest extends TestBase  {
        private TestData testData = new TestData(TestData.PATIENT_SHEET);
        private VisitsAPI visitsAPI = new VisitsAPI("mihai.muresan@heal.com", "Heal4325");
        private String visit_id = visitsAPI.createVisit();
        private String visit_url = "https://ops.qa.heal.com/dashboard#"+visit_id;
        private String visit_time = "08/24/2017 8:32 AM";

        @Test(groups = {"dev", "critical"})
        public void startVisit() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(visit_url);
            visit.startVisit();
            //todo: verify is test was successful
        }

        @Test(groups = {"dev", "critical"})
        public void cancelVisit() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(visit_url);
            visit.cancelVisit(VisitDetailsModalPage.HEAL_OTHER_REASON,"Cancel visit with auto tests");
            //todo: verify is test was successful
        }

        @Test(groups = {"dev", "critical"})
        public void changeProvider() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(visit_url);
            visit.chooseDoctorAndMA(VisitDetailsModalPage.DR_NILES, VisitDetailsModalPage.MA_KETTEL);
            verifyTextMatches("Verify Doctor field value", visit.oChooseDoctorInput, VisitDetailsModalPage.DR_NILES);
            verifyTextMatches("Verify Doctor field value", visit.oChooseMedicalAssistantInput, VisitDetailsModalPage.MA_KETTEL);
            visit.oChangetBtn.click();
            //todo: verify is test was successful
        }

        @Test(groups = {"dev", "critical"})
        public void editVisitSymptoms() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            String sSymptoms = "Added with auto tests";
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            dr.navigate().to(visit_url);
            visit.editSymptoms(sSymptoms);
            verifyTextMatches("Verify symptoms are saved", visit.oDetailsEditSymptomsField, sSymptoms);
        }

        @Test(groups = {"dev", "critical"})
        public void addInsurance() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            String sSymptoms = "Added with auto tests";
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            visit.switchToUrlWithVisitCode(visit_url);
            visit.updateInsurance(VisitDetailsModalPage.BLUE, "COST_ESTIMATES_025", "BC001");
            //todo: verify is test was successful
        }

        @Test(groups = {"dev", "critical"})
        public void refundVisitTotalRefund() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            dr.navigate().to(visit_url);
            visit.selectTotalRefund("Automated test");
        }

        @Test(groups = {"dev", "critical"})
        public void refundVisitPartialRefund() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitDetailsModalPage visit = new VisitDetailsModalPage(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            dr.navigate().to(visit_url);
            visit.selectPartialRefund("50","Automated test");
        }

}