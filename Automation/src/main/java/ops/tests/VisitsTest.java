package ops.tests;

import foundation.SysTools;
import framework.test.TestBase;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import ops.pages.*;
import org.testng.annotations.Test;


/**
 *  Created by adrian.rosu on 18/08/2017.
 */
public class VisitsTest extends TestBase{
    /**
     * This will run a test in loop for validation purposes
     */
    @Test
    public void testLoop(){
        int numberOfVisitsToBook = 10;
        int passedRuns = 0;
        int failedRuns = 0;
        for (int i = 0; i < numberOfVisitsToBook; i++) {
            try {
                createNewVisitTest(); // put here the desired test to be run on loop
                passedRuns++;
            } catch (Exception e) {
                failedRuns++;
                e.printStackTrace();
            }
            System.out.println("Passed " + passedRuns);
            System.out.println("Failed " + failedRuns);
        }
        System.out.println(passedRuns + " Passed Runs"); // display how many times the test passed on given visits booked
        System.out.println(failedRuns + " Failed Runs"); // display how many times the test failed on given visits booked
    }

    @Test (groups = {"smoke", "regression", "critical" })

    public void viewAllVisitsTest() throws Exception{
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        CreateVisitPage createVisitPage = new CreateVisitPage(dr);

        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login("mayur+oc@heal.com", "Heal@123");

        dr.navigate().to("https://ops.qa.heal.com/visits"); // temporary until Navigation Menu page object is done

        verifyVisible("Checking if the correct page was loaded", visitsPage.oAllVisitsTitle);
    }

    @Test (groups = {"smoke", "regression", "critical" })
    public void createNewVisitTest() throws Exception{
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        CreateVisitPage createVisitPage = new CreateVisitPage(dr);

        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login("mayur+oc@heal.com", "Heal@123");

        dr.navigate().to("https://ops.qa.heal.com/visits"); // temporary until Navigation Menu page object is done

        visitsPage.oAddVisitBtn.clickAndWait(createVisitPage.oPageTitle, true);

        // end-to-end flow for creating a visit in OPS
        createVisitPage.createUser();
        createVisitPage.createPatient(false);
        createVisitPage.createAddress();
        createVisitPage.addVisitDetails();
        createVisitPage.selectPayment();
        createVisitPage.visitSummary();

        verifyVisible("Checking if title was displayed", createVisitPage.oVisitPriceTitle);
        verifyEquals("Verify correct visit price", createVisitPage.oVisitPriceLabel, "99$");

    }
    // todo visit with insurance, promo codes
}
