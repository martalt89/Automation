package patient.tests;

import foundation.SysTools;
import framework.test.TestBase;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import patient.pages.*;
import utilities.DriverManager;

public class BookVisitTest extends TestBase {
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


    @Test (groups = {"dev", "critical"})
    //@Parameters({ "url" })
    public void bookVisit() throws Exception {

            CommonWebElement.setbMonitorMode(false);
            WebDriver dr = getDriver();
            CommonWebValidate validate = new CommonWebValidate(dr);
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
            Menu menu = new Menu(dr);


            loginPage.login(); // Login on patient web app
            homePage.selectFromMenu(menu.oBookVisitLnk); // Select Book Visit from Menu
            // Verify page title
            if (!validate.verifyMatches("Verifying Book goTo page title ", bookVisitPage.oPageTitle.getText(), "Book a house call")){
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
    @Test (groups = {"dev", "critical"})
    public void BookVisitWith50PercentPromo() throws Exception {
        CommonWebElement.setbMonitorMode(true);
        WebDriver dr = getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
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
        Menu menu = new Menu(dr);

        loginPage.login(); // Login on patient web app
        homePage.selectFromMenu(menu.oBookVisitLnk); // Select Book Visit from Menu
        bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false); // Select a non life-threatening medical emergency
        chooseProfilePage.selectMainProfile();
        addressPage.selectFirstSavedAddress();
        addressPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);

        visitDetailsPage.oSickOrInjuredText.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.oSymptomsInput.sendKeys("headache");
        visitDetailsPage.selectFirstAvailableTimeSlot();
        visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);

        validate.assertEquals("Verifying full price ", paymentPage.oPriceInfoText.getText(), sFullPrice);

        paymentPage.oPromoCodeLink.click();
        paymentPage.oPromoCodeInput.sendKeys("50PERCENT", Keys.TAB);
        paymentPage.oPriceInfoText.waitForVisible();

        validate.assertEquals("Verifying 50% promo price ", paymentPage.oPriceInfoText.getText(), sPromo50PercentOffPrice);
    }
    @Test (groups = {"dev", "critical"})
    public void BookVisitWith100PercentPromo() throws Exception {
        CommonWebElement.setbMonitorMode(true);
        WebDriver dr = getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
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
        Menu menu = new Menu(dr);

        loginPage.login(); // Login on patient web app
        homePage.selectFromMenu(menu.oBookVisitLnk); // Select Book Visit from Menu
        bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false); // Select a non life-threatening medical emergency
        chooseProfilePage.selectMainProfile();
        addressPage.selectFirstSavedAddress();
        addressPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);

        visitDetailsPage.oSickOrInjuredText.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.oSymptomsInput.sendKeys("headache");
        visitDetailsPage.selectFirstAvailableTimeSlot();
        visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);

        validate.assertEquals("Verifying full price ", paymentPage.oPriceInfoText.getText(), sFullPrice);

        paymentPage.oPromoCodeLink.click();
        paymentPage.oPromoCodeInput.sendKeys("100PERCENT", Keys.TAB);
        paymentPage.oPriceInfoText.waitForVisible();

        validate.assertEquals("Verifying 100% promo price ", paymentPage.oPriceInfoText.getText(), sPromo100PercentOffPrice);
    }

    @Test (groups = {"dev", "critical"})
    public void BookVisitWithInsurance() throws Exception {
        CommonWebElement.setbMonitorMode(true);
        WebDriver dr = getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
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

        homePage.selectFromMenu(menu.oProfilesLnk);
        validate.verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
        manageProfilePage.oAddPatientbtn.click();
        //manageProfilePage.oContiuneButton.clickAndWait(menu.oLoadingBar, false);
        manageProfilePage.oFirstNameInput.sendKeys(firstName);
        manageProfilePage.oLastNameInput.sendKeys(lastaName);
        manageProfilePage.oEmailInput.sendKeys(email);
        manageProfilePage.oPhoneNmbInput.sendKeys(phoneNumber);
        manageProfilePage.oDateOfBirthInput.sendKeys("09/08/1984");
        manageProfilePage.oRelationshipInput.selectByVisibleTextAngular(relationship);
        manageProfilePage.oGenderInput.selectByVisibleTextAngular(gender);
        manageProfilePage.oInsuranceProviderInput.selectByVisibleTextAngular(insuranceProvider);
        manageProfilePage.oMemberIdInput.sendKeys(insuranceID);  //insurance ID
        manageProfilePage.oGroupIdInput.sendKeys(insuranceGroup);  //group ID
        manageProfilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);

        homePage.selectFromMenu(menu.oBookVisitLnk); // Select Book Visit from Menu
        bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false); // Select a non life-threatening medical emergency
        chooseProfilePage.selectMainProfile();
        addressPage.selectFirstSavedAddress();
        addressPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);

        visitDetailsPage.oSickOrInjuredText.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.oSymptomsInput.sendKeys("headache");
        visitDetailsPage.selectFirstAvailableTimeSlot();
        visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);

        validate.assertEquals("Verifying full price ", paymentPage.oPriceInfoText.getText(), sFullPrice);
        //validate.verifyTextEquals("Verifying 'Verified' insurance tag", paymentPage.oVerifiedInsuranceText, "Verified");
        validate.verifyVisible("Verify 'Verified Icon' is displayed", paymentPage.oCheckCircleEnabled);

        paymentPage.oCompleteBtn.clickAndWait(menu.oLoadingBar, false);

        validate.assertEquals("Verifying 'what To Expect' text ", whatToExpectPage.oWhatToExpectTitle.getText(), "What to Expect");
    }

}