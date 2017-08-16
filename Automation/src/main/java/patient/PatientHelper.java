package patient;

import foundation.SysTools;
import framework.test.TestBase;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;
import patient.pages.*;

public class PatientHelper extends TestBase {

    String visitCode = "";

    public void bookVisit(Boolean bReturnVisitCode){

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
        SelectPaymentPage paymentPage = new SelectPaymentPage(dr);
        WhatToExpectPage whatToExpectPage = new WhatToExpectPage(dr);
        BookVisitPage bookVisitPage = new BookVisitPage(dr);
        Menu menu = new Menu(dr);


        loginPage.login(); // Login on patient web app
        homePage.selectFromMenu(menu.oBookVisitLnk); // Select Book Visit from Menu
        bookVisitPage.oEmergencyNoBtn.clickAndWait(menu.oLoadingBar, false);
        chooseProfilePage.selectMainProfile();
        addressPage.selectFirstSavedAddress();
        addressPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.oSickOrInjuredText.clickAndWait(menu.oLoadingBar, false);
        visitDetailsPage.oSymptomsInput.sendKeys("Creating a visit for Automation");
        visitDetailsPage.selectFirstAvailableTimeSlot();
        visitDetailsPage.oContinueBtn.clickAndWait(menu.oLoadingBar, false);
        paymentPage.oCompleteBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oNextBtn.clickAndWait(menu.oLoadingBar, false);
        whatToExpectPage.oGotItBtn.click();

        if (bReturnVisitCode) visitCode = SysTools.getVisitCodeFromURL(dr);
        menu.selectFromMenu(menu.oSignOutLnk);
        loginPage.oUserNameInput.waitForElement();


    }

    public String getNewVisitCode(){
        bookVisit(true);
        return visitCode;
    }

}
