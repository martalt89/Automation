package patient.tests;

import framework.restAPI.AccountAPI;
import framework.test.TestBase;
import framework.test.TestData;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import patient.pages.*;
import utilities.DriverManager;

public class ScheduledVisitsTest extends TestBase {
    WebDriver dr = DriverManager.getDriver();
    TestData testData = new TestData(TestData.PATIENT_SHEET);
    private TestData addCardInputData = new TestData(TestData.CARD_SHEET);
    private String sExpirationMonth = Integer.toString(addCardInputData.iExpiryMonth);
    private String sExpirationYear = Integer.toString(addCardInputData.iExpiryYear);

    @Test
    @Parameters({ "url" })
    public void accessVisitPage() throws Exception {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = DriverManager.getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr);
        HomePage homePage = new HomePage(dr);
        VisitsPage visitsPage = new VisitsPage(dr);
        Menu menu = new Menu(dr);
        loginPage.login();
        homePage.selectFromMenu(menu.oVisitsLnk);
        if (!validate.verifyMatches("Verifying Visits page title ", visitsPage.oPageTitle.getText(), "Scheduled Visits")){
            System.out.println("cannot validate " + visitsPage.oPageTitle.getText());
        }
        // select all visits
        visitsPage.oIconAll.click();
        // select only visits of first profile
        visitsPage.oIcon1stPatient.click();
        Assert.assertTrue(visitsPage.oVisitCard.isDisplayed());
        visitsPage.oWhatToExpectBtn.jsClick();
        homePage.selectFromMenu(menu.oSignOutLnk);
    }

    //NOTE: TODO: Work in progress.
    @Test(groups = {"dev", "critical"})
    public void cancelVisit() {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        //pages
        HomePage homePage = new HomePage(dr);
        VisitsPage visitsPage = new VisitsPage(dr);
        ChooseProfilePage chooseProfilePage = new ChooseProfilePage(dr);
        BookVisitAddressPage addressPage = new BookVisitAddressPage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        VisitDetailsPage visitDetailsPage = new VisitDetailsPage(dr);
        ManageProfilePage manageProfilePage = new ManageProfilePage(dr);
        AddCardPage addCardPage = new AddCardPage(dr);
        SelectPaymentPage selectPaymentPage = new SelectPaymentPage(dr);
        WhatToExpectPage whatToExpectPage = new WhatToExpectPage(dr);

        Menu menu = new Menu(dr);
        //Test steps
        loginPage.login("mihaix1@heal.com", "Heal4325");
        homePage.selectFromMenu(menu.oBookVisitLnk);
        validate.verifyVisible("Check the 'Scheduled Visits' title is displayed", bookVisitPage.oPageTitle);
        //book visit
        bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false); // Select a non life-threatening medical emergency
        //TODO: before continue, if account is new and doesn't have patients, add a patient
        //manageProfilePage.clickPatientByText(testData.sFirstname);
        manageProfilePage.clickPatientByText("asd");
        manageProfilePage.oContinueButton.click();
        addressPage.typeAddressDetailsAndSubmit(false,"12846 Woodley ave", "", "Some instructions", "Home");
        //addressPage.selectFirstSavedAddress();
//        addressPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
        //visitDetailsPage.oSickOrInjuredText.click();
        visitDetailsPage.oSymptomsInput.sendKeys("I'm testing this...");
        //visitDetailsPage.oSelectDateInput.sendKeys("07/29/2017");
        visitDetailsPage.selectFirstAvailableTimeSlot();
        visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
        //TODO before continue, check if payment is added or not
        if(validate.verifyVisible("Check the 'Edit payment' button is displayed", addCardPage.oCardNumberInput)){
            addCardPage.oCardNumberInput.sendKeys(addCardInputData.sCardNumber);
            addCardPage.oCardExpirationInput.sendKeys(sExpirationMonth + sExpirationYear);
            addCardPage.oCVCInput.sendKeys(addCardInputData.sCVC);
            selectPaymentPage.oApplyCardBtn.click();
        }
        selectPaymentPage.oCompleteBookingBtn.click();
        validate.verifyVisible("Redirected on What to expect page after successful booking", whatToExpectPage.oThankYouTitle);

        //cancel visit
        //TODO: finish cancel visit flow
        menu.selectFromMenu(menu.oVisitsLnk);
        visitsPage.cancelVisit();

    }
}