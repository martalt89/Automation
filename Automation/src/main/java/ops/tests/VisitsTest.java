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
    /**
     * Book visit with card from OPS
     * This tests will create a new user, new patient, new address, adds payment and finally books visit
     * without insurance or promo codes
     */
    @Test (groups = {"smoke", "regression", "critical" })
    public void createNewVisitTest() throws Exception{
        CommonWebElement.setbMonitorMode(true);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        CreateVisitPage createVisitPage = new CreateVisitPage(dr);
        OpsMenu opsMenu = new OpsMenu(dr);

        // Log in Admin tool
        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login("mayur+oc@heal.com", "Heal@123");

        // Go to Visits page
        opsMenu.selectFromMenu(opsMenu.oVisitsLink);

        // Click Add Visit and go to Create visit page
        visitsPage.oAddVisitBtn.clickAndWait(createVisitPage.oPageTitle, true);

        // E2E flow for creating a visit in OPS
        createVisitPage.createUser();
        createVisitPage.createPatient(false);
        createVisitPage.createAddress();
        createVisitPage.addVisitDetails();
        createVisitPage.selectPayment();
        createVisitPage.visitSummary();

        // Verify page title and correct price amount
        verifyVisible("Checking if title was displayed", createVisitPage.oVisitPriceTitle);
        verifyTextEquals("Verify correct visit price", createVisitPage.oPrice, "$99");
        verifyTextEquals("Verify correct discount price", createVisitPage.oDiscount, "-$0");
        verifyTextEquals("Verify total amount of visit price", createVisitPage.oTotal, "$99");

        // Book visit
        createVisitPage.oBookVisitBtn.clickAndWait(createVisitPage.oPageTitle, true);

        // Verify success message
        verifyTextMatches("Verify book visit success message", opsMenu.oToastMessage, "Successfully Created Visit");
    }
    /**
     * Book visit with 50 percent promo code from OPS
     * This tests will search for a user, select existent patient, address, payment methods, applies promo code
     * and finally books home visit
     * Note: User account, patient, address and payment info will be loaded from test_data excel file
     */
    @Test (groups = {"smoke", "regression", "critical" })
    public void createVisitWith50PercentPromoTest() throws Exception{
        CommonWebElement.setbMonitorMode(true);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        CreateVisitPage createVisitPage = new CreateVisitPage(dr);
        OpsMenu opsMenu = new OpsMenu(dr);

        // Log in Admin tool
        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login("mayur+oc@heal.com", "Heal@123");

        // Go to Visits page
        opsMenu.selectFromMenu(opsMenu.oVisitsLink);

        // Click Add Visit and go to Create visit page
        visitsPage.oAddVisitBtn.clickAndWait(createVisitPage.oPageTitle, true);

        // search an existent user (from excel data file) - select address - add visit details and payment
        createVisitPage.searchExistentUser();
        createVisitPage.selectFirstPatient();
        createVisitPage.selectFirstAddress();
        createVisitPage.addVisitDetails();
        createVisitPage.select1stAvailablePayment();
        createVisitPage.visitSummary();

        // apply promo code
        createVisitPage.oPromoCodeField.sendKeys("50PERCENT");
        createVisitPage.oApplyPromoBtn.clickAndWait(createVisitPage.oPromoCodeLabel, true);

        // Verify page title and correct price amount
        verifyVisible("Checking if title was displayed", createVisitPage.oVisitPriceTitle);
        verifyTextEquals("Verify correct promo code", createVisitPage.oPromo, "50PERCENT");
        verifyTextEquals("Verify correct visit price", createVisitPage.oPrice, "$99");
        verifyTextEquals("Verify correct discount price", createVisitPage.oDiscount, "-$49.50");
        verifyTextEquals("Verify total amount of visit price", createVisitPage.oTotal, "$49.50");

        // Book visit
        createVisitPage.oBookVisitBtn.clickAndWait(createVisitPage.oPageTitle, true);

        // Verify success message
        verifyTextMatches("Verify book visit success message", opsMenu.oToastMessage, "Successfully Created Visit");

        }

    /**
     * Book visit with 50 percent promo code from OPS
     * This tests will search for a user, select existent patient, address, payment methods, applies promo code
     * and finally books home visit
     * Note: User account, patient, address and payment info will be loaded from test_data excel file
     */
    @Test (groups = {"smoke", "regression", "critical" })
    public void createVisitWith100PercentPromoTest() throws Exception{
        CommonWebElement.setbMonitorMode(true);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        CreateVisitPage createVisitPage = new CreateVisitPage(dr);
        OpsMenu opsMenu = new OpsMenu(dr);

        // Log in Admin tool
        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login("mayur+oc@heal.com", "Heal@123");

        // Go to Visits page
        opsMenu.selectFromMenu(opsMenu.oVisitsLink);

        // Click Add Visit and go to Create visit page
        visitsPage.oAddVisitBtn.clickAndWait(createVisitPage.oPageTitle, true);

        // search an existent user (from excel data file) - select address - add visit details and payment
        createVisitPage.searchExistentUser();
        createVisitPage.selectFirstPatient();
        createVisitPage.selectFirstAddress();
        createVisitPage.addVisitDetails();
        createVisitPage.select1stAvailablePayment();
        createVisitPage.visitSummary();

        // apply promo code
        createVisitPage.oPromoCodeField.sendKeys("100PERCENT");
        createVisitPage.oApplyPromoBtn.clickAndWait(createVisitPage.oPromoCodeLabel, true);

        // Verify page title and correct price amount
        verifyVisible("Checking if title was displayed", createVisitPage.oVisitPriceTitle);
        verifyTextEquals("Verify correct promo code", createVisitPage.oPromo, "100PERCENT");
        verifyTextEquals("Verify correct visit price", createVisitPage.oPrice, "$99");
        verifyTextEquals("Verify correct discount price", createVisitPage.oDiscount, "-$99");
        verifyTextEquals("Verify total amount of visit price", createVisitPage.oTotal, "$0");

        // Book visit
        createVisitPage.oBookVisitBtn.clickAndWait(createVisitPage.oPageTitle, true);

        // Verify success message
        verifyTextMatches("Verify book visit success message", opsMenu.oToastMessage, "Successfully Created Visit");

    }
    /**
     * Book visit with for a patient with insurance from OPS
     * This tests will search for a user, add new patient with insurance, address, payment method
     * and finally books home visit
     * Note: User account, patient, insurance, address and payment info will be loaded from test_data excel file
     */
    @Test (groups = {"smoke", "regression", "critical" })
    public void createVisitWithInsuranceTest() throws Exception {
        CommonWebElement.setbMonitorMode(true);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        CreateVisitPage createVisitPage = new CreateVisitPage(dr);
        OpsMenu opsMenu = new OpsMenu(dr);

        // Log in Admin tool
        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login("mayur+oc@heal.com", "Heal@123");

        // Go to Visits page
        opsMenu.selectFromMenu(opsMenu.oVisitsLink);

        // Click Add Visit and go to Create visit page
        visitsPage.oAddVisitBtn.clickAndWait(createVisitPage.oPageTitle, true);

        //createVisitPage.searchExistentUser(); // user from excel is not displayed anymore
        createVisitPage.createUser();
        createVisitPage.createPatient(true);
        //createVisitPage.selectFirstAddress();
        createVisitPage.createAddress();
        createVisitPage.addVisitDetails();  // for some reason Add Visit details remains disabled
        createVisitPage.selectPayment();
        createVisitPage.visitSummary();


        createVisitPage.oBookVisitBtn.clickAndWait(createVisitPage.oPageTitle, true);
        // todo finish visit with insurance (add assertions)
    }

}
