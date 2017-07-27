package patient.tests;

import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import patient.pages.*;
import utilities.DriverManager;

public class BookVisitTest extends WebBase {

    WebDriver dr = DriverManager.getDriver();

    @Test (groups = {"dev"})
    @Parameters({ "url" })
    public void bookVisit(String url) throws Exception {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = DriverManager.getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr, url);
        HomePage homePage = new HomePage(dr);
        ChooseProfilePage chooseProfilePage = new ChooseProfilePage(dr);
        BookVisitAddressPage addressPage = new BookVisitAddressPage(dr);
        VisitDetailsPage visitDetailsPage = new VisitDetailsPage(dr);
        VisitsPage visitsPage = new VisitsPage(dr);
        SelectPaymentPage paymentPage = new SelectPaymentPage(dr);
        WhatToExpectPage whatToExpectPage = new WhatToExpectPage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        Menu menu = new Menu(dr);

        // Login on patient web app
        loginPage.login();

        // Select Book Visit from Menu
        homePage.selectFromMenu(menu.oBookVisitLnk);

        // Verify page title
        if (!validate.verifyMatches("Verifying Book visit page title ", bookVisitPage.oPageTitle.getText(), "Book Visit")){
            System.out.println("cannot validate " + bookVisitPage.oPageTitle.getText());
        }

        // Select a non life-threatening medical emergency
        bookVisitPage.oEmergencyNoBtn.click();
        chooseProfilePage.selectMainProfile();
        addressPage.selectFirstSavedAddress();
        addressPage.oContinueBtn.click();
        visitDetailsPage.oSickOrInjuredText.click();
        visitDetailsPage.oSymptomsInput.sendKeys("I'm testing this...");
        visitDetailsPage.oSelectDateInput.sendKeys("07/27/2017");
        visitDetailsPage.oFirstAvailableTimeSlot.jsClick();
        visitDetailsPage.oContinueBtn.click();
        paymentPage.oCompleteBtn.click();
        validate.assertEquals("Verifying 'Thank you' message text ", whatToExpectPage.oThankYouTitle.getText(), "Thank you for choosing Heal.");
        validate.assertEquals("Verifying 'what To Expect' text ", whatToExpectPage.oWhatToExpectTitle.getText(), "What to Expect");
        whatToExpectPage.oNextBtn.click();
        whatToExpectPage.oNextBtn.click();
        whatToExpectPage.oNextBtn.click();
        whatToExpectPage.oGotItBtn.click();


        System.out.println("Total number of validations executed : " + validate.getTotalCount());
        int passed = validate.getTotalCount()-validate.getFailureCount();
        System.out.println("Passed validations " + passed);
        System.out.println("Failed validations " + validate.getFailureCount());





//        // Go back to Book visit
//        homePage.selectFromMenu(menu.oBookVisitLnk);
//
//        // Select a life-threatening medical emergency
//        bookVisitPage.oEmergencyYesBtn.click();
//
//        // Click Ok
//        bookVisitPage.oOkBtn.click();
//
//        // Verify page title
//        if (!validate.verifyMatches("Verifying Visits page title ", bookVisitPage.oPageTitle.getText(), "Book Visit")){
//            System.out.println("cannot validate " + bookVisitPage.oPageTitle.getText());
//        }
//
//        // Sign out
//        homePage.selectFromMenu(menu.oSignOutLnk);

    }
}