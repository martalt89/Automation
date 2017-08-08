package patient.tests;

import foundation.SysTools;
import framework.test.TestBase;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import patient.pages.*;
import utilities.DriverManager;

public class VisitTests extends TestBase {


    @Test (groups = {"devv", "critical"})
    //@Parameters({ "url" })
    public void bookVisit() throws Exception {

            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
            LoginPage loginPage = new LoginPage(dr);
            HomePage homePage = new HomePage(dr);
            ChooseProfilePage chooseProfilePage = new ChooseProfilePage(dr);
            BookVisitAddressPage addressPage = new BookVisitAddressPage(dr);
            VisitDetailsPage visitDetailsPage = new VisitDetailsPage(dr);
            VisitsPage visitsPage = new VisitsPage(dr);
            SelectPaymentPage paymentPage = new SelectPaymentPage(dr);
            WhatToExpectPage whatToExpectPage = new WhatToExpectPage(dr);
            BookVisitPage bookVisitPage = new BookVisitPage(dr);
            Menu menu = new Menu(dr);


            loginPage.login(); // Login on patient web app
            homePage.selectFromMenu(menu.oBookVisitLnk); // Select Book Visit from Menu
            // Verify page title
            if (!validate.verifyMatches("Verifying Book visit page title ", bookVisitPage.oPageTitle.getText(), "Book a house call")){
                System.out.println("cannot validate " + bookVisitPage.oPageTitle.getText());
            }
            bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false); // Select a non life-threatening medical emergency
            chooseProfilePage.selectMainProfile();
            //addressPage.typeAddressDetailsAndSubmit(false,"12846 Woodley ave", "", "Some instructions", "Home");
            addressPage.selectFirstSavedAddress();
            addressPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
            visitDetailsPage.oSickOrInjuredText.clickAndWait(menu.oLoadingBar, false);
            visitDetailsPage.oSymptomsInput.sendKeys("I'm testing this...");
            //visitDetailsPage.oSelectDateInput.sendKeys("07/29/2017");
            visitDetailsPage.selectFirstAvailableTimeSlot();
            visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
            paymentPage.oCompleteBtn.clickAndWait(menu.oLoadingBar, false);
            validate.assertEquals("Verifying 'Thank you' message text ", whatToExpectPage.oThankYouTitle.getText(), "Thank you for choosing Heal.");
            validate.assertEquals("Verifying 'what To Expect' text ", whatToExpectPage.oWhatToExpectTitle.getText(), "What to Expect");
            whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
            whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
            whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
            whatToExpectPage.oGotItBtn.click();

            System.out.println("Total number of validations executed : " + validate.getTotalCount());
            int passed = validate.getTotalCount()-validate.getFailureCount();
            System.out.println("Passed validations " + passed);
            System.out.println("Failed validations " + validate.getFailureCount());
            System.out.println("The house call was booked successfully. House call code: " + SysTools.getVisitCodeFromURL(dr));
    }
}