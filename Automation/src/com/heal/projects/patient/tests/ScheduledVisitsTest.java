package com.heal.projects.patient.tests;

import com.heal.framework.restAPI.AccountAPI;
import com.heal.framework.test.TestBase;
import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.CommonWebValidate;
import com.heal.projects.patient.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ScheduledVisitsTest extends TestBase {
    private TestData patientTestData = new TestData(TestData.PATIENT_SHEET);
    private AccountAPI accountAPI = new AccountAPI("mihaix5@heal.com", "Heal4325");
    private TestData addCardInputData = new TestData(TestData.CARD_SHEET);
    private String sExpirationMonth = Integer.toString(addCardInputData.iExpiryMonth);
    private String sExpirationYear = Integer.toString(addCardInputData.iExpiryYear);

    @Test
    @Parameters({ "url" })
    public void accessVisitPage() throws Exception {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
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


    @Test(groups = {"dev", "critical"})
    public void cancelVisit() {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        //pages
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        HomePage homePage = new HomePage(dr);
        VisitsPage visitsPage = new VisitsPage(dr);
        ChooseProfilePage chooseProfilePage = new ChooseProfilePage(dr);
        BookVisitAddressPage addressPage = new BookVisitAddressPage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        VisitDetailsPage visitDetailsPage = new VisitDetailsPage(dr);
        ManageProfilePage manageProfilePage = new ManageProfilePage(dr);
        CreatePatientPage createPatientPage = new CreatePatientPage(dr);
        AddCardPage addCardPage = new AddCardPage(dr);
        SelectPaymentPage selectPaymentPage = new SelectPaymentPage(dr);
        WhatToExpectPage whatToExpectPage = new WhatToExpectPage(dr);
        Menu menu = new Menu(dr);
        //Test steps
        loginPage.login("mihaix5@heal.com", "Heal4325");
        homePage.selectFromMenu(menu.oBookVisitLnk);
        validate.verifyVisible("Check the 'Scheduled Visits' title is displayed", bookVisitPage.oPageTitle);
        //book visit
        bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false); // Select a non life-threatening medical emergency
        //check if account has patients. if not, add one patient
        if(accountAPI.getPatientsNumber()==0){
            chooseProfilePage.oAddPatientLabel.click();
            manageProfilePage.typePatientDataFromExcel(patientTestData);
            manageProfilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);
        } else {
            chooseProfilePage.selectProfileByName(patientTestData.sFirstname);
            chooseProfilePage.oContinueBtn.click();
        }
        //TODO: Update test data file with address details
        //addressPage.populateAddressDetails(false,"12846 Woodley Ave, Granada Hills, CA 91344, USA", "12", "Some instructions", "Home");
        addressPage.oContinueBtn.click();
        visitDetailsPage.selectServiceForVisit(VisitDetailsPage.SICK_SERVICE);
        //TODO - does not find this element. Will investigate more
        //visitDetailsPage.oSymptomsInput.sendKeys("I'm testing this...");
        visitDetailsPage.selectFirstAvailableTimeSlot();
        visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
        //Check if account has Card
        if(validate.verifyVisible("Verify if Card number input is visible", addCardPage.oCardNumberInput)){
            addCardPage.oCardNumberInput.sendKeys(addCardInputData.sCardNumber);
            addCardPage.oCardExpirationInput.sendKeys(sExpirationMonth + sExpirationYear);
            addCardPage.oCVCInput.sendKeys(addCardInputData.sCVC);
            selectPaymentPage.oApplyCardBtn.click();
        }
        selectPaymentPage.oCompleteBookingBtn.clickAndWait(menu.oLoadingBar, false);
        validate.verifyVisible("Redirected on What to expect page after successful booking", whatToExpectPage.oThankYouTitle);
        //cancel visits
        menu.selectFromMenu(menu.oVisitsLnk);
        while(validate.verifyVisible("Check if Cancel visit button is visible",visitsPage.oCancelVisitBtn)) {
            visitsPage.cancelVisit(); //TODO Fix. Not working properly when selecting menu item
        }
        validate.verifyVisible("Check that there are no open visits", visitsPage.oScheduledVisitsInfo);
    }
}