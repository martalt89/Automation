package com.heal.projects.ops.tests;

import com.heal.framework.test.TestBase;
import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.CommonWebValidate;
import com.heal.projects.ops.pages.VisitSummaryPage;
import com.heal.projects.ops.pages.OpsLoginPage;
import com.heal.projects.ops.pages.OpsMenu;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

//NOTE: Work in progress
public class VisitSummaryTest extends TestBase  {
        TestData testData = new TestData(TestData.PATIENT_SHEET);
        private String visit_id = "SF-FCVBK";
        private String visit_url = "https://ops.qa.heal.com/dashboard#"+visit_id;
        private String visit_time = "08/24/2017 8:32 AM";

        @Test(groups = {"dev", "critical"})
        public void startVisit() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitSummaryPage visit = new VisitSummaryPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            dr.navigate().to(visit_url);
            visit.startVisit(visit_time);
        }

        @Test(groups = {"dev", "critical"})
        public void cancelVisit() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitSummaryPage visit = new VisitSummaryPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            dr.navigate().to(visit_url);
            visit.cancelVisit(VisitSummaryPage.HEAL_OTHER_REASON,"Cancel visit with auto tests");
        }

        @Test(groups = {"dev", "critical"})
        public void changeProvider() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitSummaryPage visit = new VisitSummaryPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            dr.navigate().to(visit_url);
            visit.chooseDoctor(VisitSummaryPage.DR_NILES);
            verifyTextMatches("Verify Doctor field value", visit.oChooseDoctorInput, VisitSummaryPage.DR_NILES);
            visit.chooseMedicalAssistant(VisitSummaryPage.MA_KETTEL);
            verifyTextMatches("Verify Doctor field value", visit.oChooseMedicalAssistantInput, VisitSummaryPage.MA_KETTEL);
            visit.oChangetBtn.click();
        }

        @Test(groups = {"dev", "critical"})
        public void editVisitSymptoms() {
            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitSummaryPage visit = new VisitSummaryPage(dr);
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
            VisitSummaryPage visit = new VisitSummaryPage(dr);
            String sSymptoms = "Added with auto tests";
            loginPage.goTo();
            loginPage.waitForPageReady();
            loginPage.login();
            dr.navigate().to(visit_url);
            visit.updateInsurance(VisitSummaryPage.BLUE, "COST_ESTIMATES_025", "BC001");
            verifyTextMatches("Verify symptoms are saved", visit.oDetailsEditSymptomsField, sSymptoms);
        }
}