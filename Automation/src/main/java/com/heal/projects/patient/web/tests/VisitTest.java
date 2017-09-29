package com.heal.projects.patient.web.tests;

import com.heal.framework.test.TestData;
import com.heal.projects.patient.web.pages.*;
import com.heal.framework.foundation.SysTools;
import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class VisitTest extends TestBase {
    private String sFullPrice = "$99";
    private String sPromo50PercentOffPrice = "$49.50";
    private String sPromo100PercentOffPrice = "$0";
    private String firstName = "Priyanka";
    private String lastaName = "Halder";
    private String insuranceID = "JQU397M89484";
    private String insuranceGroup = "A45878";
    private String insuranceProvider = "Anthem";
    private String email = "test@test.com";
    private String phoneNumber = "18182123842";
    private String relationship = "Friend";
    private String gender = "Female";
    private String symptoms = "IGNORE - Booked by automation test..";

    /**
     * This will run a test in loop for validation purposes
     */
    @Test
    public void testLoop(){
        int numberOfVisitsToBook = 20;
        int passedRuns = 0;
        int failedRuns = 0;
        for (int i = 0; i < numberOfVisitsToBook; i++) {
            try {
                bookVisit(); // put here the desired test to be run on loop
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

    /**
     * Cancel an active visit
     */
    @Test (groups = {"dev", "critical"})
    public void cancelVisit(){

        WebDriver dr = getDriver();
        CommonWebElement.setbMonitorMode(false);
        LoginPage loginPage = new LoginPage(dr);
        HomePage homePage = new HomePage(dr);
        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login();
        homePage.selectFromMenu("visits");
        homePage.cancelVisit(1);
//        homePage.cancelAllVisits();
        homePage.selectFromMenu("sign out");
            verifyVisible("Checking if sign out was completed", loginPage.oUserNameInput);
            assertMatches("Is register button visible?", loginPage.oRegisterBtn.getText(), "Register");

    }

    /**
     * Book visit
     */
    @Test (groups = {"dev", "critical"})
    public void bookVisit() throws Exception {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageLoad();
        HomePage homePage = new HomePage(dr);
        ChooseProfilePage chooseProfilePage = new ChooseProfilePage(dr);
        BookVisitAddressPage addressPage = new BookVisitAddressPage(dr);
        VisitDetailsPage visitDetailsPage = new VisitDetailsPage(dr);
        SelectPaymentPage paymentPage = new SelectPaymentPage(dr);
        WhatToExpectPage whatToExpectPage = new WhatToExpectPage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        Menu menu = new Menu(dr);

            loginPage.login(); // Login on patient web app
            homePage.selectFromMenu(menu.oBookVisitLnk); // Select Book Visit from Menu
                verifyMatches("Verifying Book goTo page title ", bookVisitPage.oPageTitle.getText(), "Book Visit"); // Verify page title
            bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false); // Select a non life-threatening medical emergency
            chooseProfilePage.selectMainProfile();
            addressPage.selectFirstSavedAddress();
            addressPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
            visitDetailsPage.oSickOrInjuredText.clickAndWait(menu.oLoadingBar, false);

            visitDetailsPage.setSymptoms(symptoms);
            visitDetailsPage.selectFirstAvailableTimeSlot();
            visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
            paymentPage.oCompleteBtn.clickAndWait(menu.oLoadingBar, false);
            whatToExpectPage.oNextBtn.waitForElement();
                assertEquals("Verifying 'Thank you' message text ", whatToExpectPage.oThankYouTitle.getText(), "Thank you for choosing Heal.");
                assertEquals("Verifying 'what To Expect' text ", whatToExpectPage.oWhatToExpectTitle.getText(), "What to Expect");
            whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
            whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
            whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
            whatToExpectPage.oGotItBtn.click();
            getExtentTest().log(LogStatus.INFO, SysTools.getVisitCodeFromURL(dr) + " visit booked");
//            System.out.println("The house call was booked successfully. House call code: " + SysTools.getVisitCodeFromURL(dr));
            menu.selectFromMenu(menu.oSignOutLnk);
            loginPage.oUserNameInput.waitForElement();
    }

    /**
     * Book visit with credit card
     * @throws Exception
     */
    @Test (groups = {"dev", "critical"})
    //@Parameters({ "url" })
    public void bookVisitWithCreditCard() throws Exception {

        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageLoad();
        HomePage homePage = new HomePage(dr);
        ChooseProfilePage chooseProfilePage = new ChooseProfilePage(dr);
        BookVisitAddressPage addressPage = new BookVisitAddressPage(dr);
        VisitDetailsPage visitDetailsPage = new VisitDetailsPage(dr);
        SelectPaymentPage paymentPage = new SelectPaymentPage(dr);
        WhatToExpectPage whatToExpectPage = new WhatToExpectPage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        Menu menu = new Menu(dr);

        loginPage.login(); // Login on patient web app
        homePage.selectFromMenu(menu.oBookVisitLnk); // Select Book Visit from Menu
        verifyMatches("Verifying Book goTo page title ", bookVisitPage.oPageTitle.getText(), "Book Visit"); // Verify page title
        bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false); // Select a non life-threatening medical emergency
        chooseProfilePage.selectProfileByName("Credit Card");
        addressPage.selectFirstSavedAddress();
        addressPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.oSickOrInjuredText.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.setSymptoms(symptoms);
        visitDetailsPage.selectFirstAvailableTimeSlot();
        visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
        paymentPage.oCompleteBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.waitForElement();
            assertEquals("Verifying 'Thank you' message text ", whatToExpectPage.oThankYouTitle.getText(), "Thank you for choosing Heal.");
            assertEquals("Verifying 'what To Expect' text ", whatToExpectPage.oWhatToExpectTitle.getText(), "What to Expect");
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oGotItBtn.click();
        getExtentTest().log(LogStatus.INFO, SysTools.getVisitCodeFromURL(dr) + " visit booked");
//        System.out.println("The house call was booked successfully. House call code: " + SysTools.getVisitCodeFromURL(dr));
        menu.selectFromMenu(menu.oSignOutLnk);
        loginPage.oUserNameInput.waitForElement();
    }

    /**
     * Book visit with promo code 50% percent off
     * @throws Exception
     */
    @Test (groups = {"dev", "critical"})
    public void BookVisitWith50PercentPromo() throws Exception {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
//        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageLoad();
        HomePage homePage = new HomePage(dr);
        ChooseProfilePage chooseProfilePage = new ChooseProfilePage(dr);
        BookVisitAddressPage addressPage = new BookVisitAddressPage(dr);
        VisitDetailsPage visitDetailsPage = new VisitDetailsPage(dr);
        SelectPaymentPage paymentPage = new SelectPaymentPage(dr);
        WhatToExpectPage whatToExpectPage = new WhatToExpectPage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        Menu menu = new Menu(dr);

        loginPage.login(); // Login on patient web app
        homePage.selectFromMenu(menu.oBookVisitLnk); // Select Book Visit from Menu
        bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false); // Select a non life-threatening medical emergency
        chooseProfilePage.selectProfileByName("Credit Card");
        addressPage.selectFirstSavedAddress();
        addressPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.oSickOrInjuredText.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.setSymptoms(symptoms);
        visitDetailsPage.selectFirstAvailableTimeSlot();
        visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);

            assertEquals("Verifying full price ", paymentPage.oPriceInfoText.getText(), sFullPrice);

//        paymentPage.oPromoCodeLink.click(); // no more promo code link
        paymentPage.oPromoCodeInput.sendKeys("50PERCENT", Keys.TAB);
        menu.oLoadingBar.waitForInvisible();

            assertEquals("Verifying 50% promo price ", paymentPage.oPriceInfoText.getText(), sPromo50PercentOffPrice);

        paymentPage.oCompleteBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.waitForElement();
            assertEquals("Verifying 'Thank you' message text ", whatToExpectPage.oThankYouTitle.getText(), "Thank you for choosing Heal.");
            assertEquals("Verifying 'what To Expect' text ", whatToExpectPage.oWhatToExpectTitle.getText(), "What to Expect");
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oGotItBtn.click();
        getExtentTest().log(LogStatus.INFO, SysTools.getVisitCodeFromURL(dr) + " visit booked");
//        System.out.println("The house call was booked successfully. House call code: " + SysTools.getVisitCodeFromURL(dr));
        menu.selectFromMenu(menu.oSignOutLnk);
        loginPage.oUserNameInput.waitForElement();
    }

    /**
     * Book visit with promo code 100% off
     * @throws Exception
     */
    @Test (groups = {"dev", "critical"})
    public void BookVisitWith100PercentPromo() throws Exception {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
//        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageLoad();
        HomePage homePage = new HomePage(dr);
        ChooseProfilePage chooseProfilePage = new ChooseProfilePage(dr);
        BookVisitAddressPage addressPage = new BookVisitAddressPage(dr);
        VisitDetailsPage visitDetailsPage = new VisitDetailsPage(dr);
        SelectPaymentPage paymentPage = new SelectPaymentPage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        WhatToExpectPage whatToExpectPage = new WhatToExpectPage(dr);
        Menu menu = new Menu(dr);

        loginPage.login(); // Login on patient web app
        homePage.selectFromMenu(menu.oBookVisitLnk); // Select Book Visit from Menu
        bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false); // Select a non life-threatening medical emergency
        chooseProfilePage.selectProfileByName("Credit Card");
        addressPage.selectFirstSavedAddress();
        addressPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.oSickOrInjuredText.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.setSymptoms(symptoms);
        visitDetailsPage.selectFirstAvailableTimeSlot();
        visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);

            assertEquals("Verifying full price ", paymentPage.oPriceInfoText.getText(), sFullPrice);

//        paymentPage.oPromoCodeLink.click(); no more promo code link
        paymentPage.oPromoCodeInput.sendKeys("100PERCENT", Keys.TAB);
        menu.oLoadingBar.waitForInvisible();

            assertEquals("Verifying 100% promo price ", paymentPage.oPriceInfoText.getText(), sPromo100PercentOffPrice);

        paymentPage.oCompleteBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.waitForElement();
        assertEquals("Verifying 'Thank you' message text ", whatToExpectPage.oThankYouTitle.getText(), "Thank you for choosing Heal.");
        assertEquals("Verifying 'what To Expect' text ", whatToExpectPage.oWhatToExpectTitle.getText(), "What to Expect");
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oGotItBtn.click();
        getExtentTest().log(LogStatus.INFO, SysTools.getVisitCodeFromURL(dr) + " visit booked");
//        System.out.println("The house call was booked successfully. House call code: " + SysTools.getVisitCodeFromURL(dr));
        menu.selectFromMenu(menu.oSignOutLnk);
        loginPage.oUserNameInput.waitForElement();
    }

    /**
     * Book visit with insurance. this will use real eligible insurance or can be used for also adding a new test insurance
     * @throws Exception
     */
    @Test (groups = {"dev", "critical"})
    public void BookVisitWithInsurance() throws Exception {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
//        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageLoad();
        HomePage homePage = new HomePage(dr);
        ChooseProfilePage chooseProfilePage = new ChooseProfilePage(dr);
        BookVisitAddressPage addressPage = new BookVisitAddressPage(dr);
        VisitDetailsPage visitDetailsPage = new VisitDetailsPage(dr);
        VisitsPage visitsPage = new VisitsPage(dr);
        SelectPaymentPage paymentPage = new SelectPaymentPage(dr);
        WhatToExpectPage whatToExpectPage = new WhatToExpectPage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        ManageProfilePage manageProfilePage = new ManageProfilePage(dr);

        Menu menu = new Menu(dr);

        loginPage.login(); // Login on patient web app

        homePage.selectFromMenu(menu.oBookVisitLnk); // Select Book Visit from Menu
        bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false); // Select a non life-threatening medical emergency

        chooseProfilePage.selectProfileByName("Insurance");
        addressPage.selectFirstSavedAddress();
        addressPage.oContinueBtn.jsClickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.selectServiceForVisit("SICK_SERVICE");
        visitDetailsPage.setSymptoms(symptoms);
        visitDetailsPage.selectFirstAvailableTimeSlot();
        visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);

            assertEquals("Verifying full price ", paymentPage.oPriceInfoText.getText(), sFullPrice);

        paymentPage.oCompleteBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.waitForElement();

            assertEquals("Verifying 'Thank you' message text ", whatToExpectPage.oThankYouTitle.getText(), "Thank you for choosing Heal.");
            assertEquals("Verifying 'What To Expect' text ", whatToExpectPage.oWhatToExpectTitle.getText(), "What to Expect");

        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oGotItBtn.click();
        getExtentTest().log(LogStatus.INFO, SysTools.getVisitCodeFromURL(dr) + " visit booked");
//        System.out.println("The house call was booked successfully. House call code: " + SysTools.getVisitCodeFromURL(dr));
        menu.selectFromMenu(menu.oSignOutLnk);
        loginPage.oUserNameInput.waitForElement();
    }

    @Test
    public void bookVisitWithPromoCodeNoCreditCard(){
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
//        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageLoad();
        HomePage homePage = new HomePage(dr);
        ChooseProfilePage chooseProfilePage = new ChooseProfilePage(dr);
        BookVisitAddressPage addressPage = new BookVisitAddressPage(dr);
        VisitDetailsPage visitDetailsPage = new VisitDetailsPage(dr);
        SelectPaymentPage paymentPage = new SelectPaymentPage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        WhatToExpectPage whatToExpectPage = new WhatToExpectPage(dr);
        TestData testdata=new TestData(TestData.PATIENT_SHEET);
        Menu menu = new Menu(dr);

        loginPage.login(testdata.sNo_Credit_Card_Id,"Heal4325"); // Login on patient web app
        homePage.selectFromMenu(menu.oBookVisitLnk); // Select Book Visit from Menu
        bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false); // Select a non life-threatening medical emergency
        chooseProfilePage.selectProfileByName("vahan");
        addressPage.selectFirstSavedAddress();
        addressPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.oSickOrInjuredText.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.setSymptoms(symptoms);
        visitDetailsPage.selectFirstAvailableTimeSlot();
        visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);

        assertEquals("Verifying full price ", paymentPage.oPriceInfoText.getText(), sFullPrice);

//        paymentPage.oPromoCodeLink.click(); // no more promo code link
        paymentPage.oPromoCodeInput.sendKeys("100PERCENT", Keys.TAB);
        menu.oLoadingBar.waitForInvisible();

        assertEquals("Verifying 100% promo price ", paymentPage.oPriceInfoText.getText(), sPromo100PercentOffPrice);

        paymentPage.oCompleteBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.waitForElement();
        assertEquals("Verifying 'Thank you' message text ", whatToExpectPage.oThankYouTitle.getText(), "Thank you for choosing Heal.");
        assertEquals("Verifying 'what To Expect' text ", whatToExpectPage.oWhatToExpectTitle.getText(), "What to Expect");
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oGotItBtn.click();
        getExtentTest().log(LogStatus.INFO, SysTools.getVisitCodeFromURL(dr) + " visit booked");
        menu.selectFromMenu(menu.oSignOutLnk);
        loginPage.oUserNameInput.waitForElement();

    }


}