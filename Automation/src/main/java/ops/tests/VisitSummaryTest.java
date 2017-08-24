package ops.tests;

import foundation.SysTools;
import framework.restAPI.VisitsAPI;
import framework.test.TestBase;
import framework.test.TestData;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import ops.pages.OpsLoginPage;
import ops.pages.OpsMenu;
import ops.pages.VisitSummaryPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

//NOTE: Work in progress
public class VisitSummaryTest extends TestBase  {
        TestData testData = new TestData(TestData.PATIENT_SHEET);
        private String visit_id = "LA-QVRUS";
        private String  visit_url = "https://ops.qa.heal.com/dashboard#"+visit_id;
        private String visit_time = "08/24/2017 8:32 AM";

        @Test(groups = {"dev", "critical"})
        public void startVisit() {
            CommonWebElement.setbMonitorMode(true);
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
            SysTools.sleepFor(10);
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
            OpsLoginPage loginPage = new OpsLoginPage(dr);
            VisitSummaryPage visit = new VisitSummaryPage(dr);
            OpsMenu menu = new OpsMenu(dr);
            loginPage.goTo();
            loginPage.waitForPageReady();
            VisitsAPI visitsAPI = new VisitsAPI("vahan+qa@heal.com", "Heal4325");
            visitsAPI.createVisit();
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
            visit.selectVisit(visit_id);
            //dr.navigate().to(visit_url);
            visit.editSymptoms(sSymptoms);
            verifyTextMatches("Verify symptoms are saved", visit.oDetailsSymptoms, sSymptoms);
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