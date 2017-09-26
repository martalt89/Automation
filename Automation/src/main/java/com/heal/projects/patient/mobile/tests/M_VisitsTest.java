package com.heal.projects.patient.mobile.tests;

import com.heal.framework.test.TestBase;
import com.heal.projects.patient.mobile.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class M_VisitsTest extends TestBase{
    @Test
    public void BookVisitWithInsurance(){
        WebDriver dr = getDriver();
        M_MenuPage menu=new M_MenuPage(dr);
        M_MainPage homePage = new M_MainPage(dr);
        M_BookVisitPage bookVisitPage= new M_BookVisitPage(dr);
        M_VisitDetailspage visitDetails=new M_VisitDetailspage(dr);
        M_PaymentDetailsPage paymentDetailsPage=new M_PaymentDetailsPage(dr);


        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
        verifyVisible("Verify Login Button is displayed", homePage.oLoginBtn);
        homePage.clickSettingsButton();

        M_SettingsPage settingsPage = new M_SettingsPage(dr);
        settingsPage.setEnv("qa");
        homePage.oLoginBtn.click();
//        homePage.clickLoginButton();
        M_LoginPage loginPage = new M_LoginPage(dr);
        assertEquals("verify Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");

        loginPage.login();

        bookVisitPage.oNoMedicalEmergencyButton.click();
        bookVisitPage.fillAddressDetails();
        dr.navigate().back();
        bookVisitPage.oSelectPatientProfile("Insurance");
        bookVisitPage.oContinueButton.clickAndWait(visitDetails.oContiueBtn,true);

        visitDetails.oSelectSickOrInjuredService.click();
        visitDetails.oSelectDateAndTime.clickAndWait(menu.oProgressBar,false);
        visitDetails.oSelectTimeForVisit.click();
        visitDetails.oSelectDateDoneButton.clickAndWait(visitDetails.oSymptomsField,true);
        visitDetails.oSymptomsField.sendKeys("IGNORE - Booked by automation test..");
        dr.navigate().back();
        visitDetails.oContiueBtn.clickAndWait(paymentDetailsPage.oWhosPlan,true);

        assertMatches("person name for registered plan",paymentDetailsPage.oWhosPlan.getText(),"Insurance's Plan");
        assertMatches("details of plan ",paymentDetailsPage.oWhichPlan.getText(),"SILVER 70 PPO JAN16");
        assertEquals("deductible amount for plan",paymentDetailsPage.oPlanDeductibleAmount.getText(),"$2250");
        assertEquals("deductible amount for plan remaining",paymentDetailsPage.oDeductibleRemaining.getText(),"$1337");
        assertEquals("flatFee for visit with insurance",paymentDetailsPage.oFlatFee.getText(),"$99");


        paymentDetailsPage.oRequestDoctorButton.click();


    }

    @Test
    public void bookVisitWith50PercentPromoCode()
    {
        WebDriver dr = getDriver();
        M_MenuPage menu=new M_MenuPage(dr);
        M_MainPage homePage = new M_MainPage(dr);
        M_BookVisitPage bookVisitPage= new M_BookVisitPage(dr);
        M_VisitDetailspage visitDetails=new M_VisitDetailspage(dr);
        M_PaymentDetailsPage paymentDetailsPage=new M_PaymentDetailsPage(dr);
        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
        verifyVisible("Verify Login Button is displayed", homePage.oLoginBtn);
        homePage.clickSettingsButton();

        M_SettingsPage settingsPage = new M_SettingsPage(dr);
        settingsPage.setEnv("qa");

        homePage.oLoginBtn.click();
        M_LoginPage loginPage = new M_LoginPage(dr);
        assertMatches("verify Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");

        loginPage.login();

        //filling out required data for book vist page
        bookVisitPage.oNoMedicalEmergencyButton.click();
        bookVisitPage.fillAddressDetails();
        dr.navigate().back();
        bookVisitPage.oSelectPatientProfile("Credit Card");
        bookVisitPage.oContinueButton.clickAndWait(visitDetails.oContiueBtn,true);
        //filling out details about visit detils page
        visitDetails.oSelectSickOrInjuredService.click();
        visitDetails.oSelectDateAndTime.clickAndWait(menu.oProgressBar,false);
        visitDetails.oSelectTimeForVisit.click();
        visitDetails.oSelectDateDoneButton.clickAndWait(visitDetails.oSymptomsField,true);
        visitDetails.oSymptomsField.sendKeys("IGNORE - Booked by automation test..");
        dr.navigate().back();
        visitDetails.oContiueBtn.clickAndWait(paymentDetailsPage.oPromoCodeLink,true);
        //filling out details of payment page and validations
        paymentDetailsPage.oPromoCodeLink.clickAndWait(paymentDetailsPage.oPromoCodeInputTab,true);
        paymentDetailsPage.oPromoCodeInputTab.sendKeys("50PERCENT");

        paymentDetailsPage.oPromoCodeApplyButton.clickAndWait(paymentDetailsPage.oTapToAddInsuranceInput,true);
        dr.navigate().back();

        assertEquals("verification flatFee after applying promo code",paymentDetailsPage.oFlatFeePromo.getText(),"$49.50");

        paymentDetailsPage.oRequestDoctorButton.click();

    }

    @Test
    public void bookVisitWith100PercentPromoCode()
    {
        WebDriver dr = getDriver();
        M_MenuPage menu=new M_MenuPage(dr);
        M_MainPage homePage = new M_MainPage(dr);
        M_BookVisitPage bookVisitPage= new M_BookVisitPage(dr);
        M_VisitDetailspage visitDetails=new M_VisitDetailspage(dr);
        M_PaymentDetailsPage paymentDetailsPage=new M_PaymentDetailsPage(dr);
        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
        verifyVisible("Verify Login Button is displayed", homePage.oLoginBtn);
        homePage.clickSettingsButton();

        M_SettingsPage settingsPage = new M_SettingsPage(dr);
        settingsPage.setEnv("qa");
        homePage.oLoginBtn.click();
//        homePage.clickLoginButton();
        M_LoginPage loginPage = new M_LoginPage(dr);
        assertMatches("verify Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");

        loginPage.login();

        //filling out required data for book vist page
        bookVisitPage.oNoMedicalEmergencyButton.click();
        bookVisitPage.fillAddressDetails();
        dr.navigate().back();
        bookVisitPage.oSelectPatientProfile("Credit Card");
        bookVisitPage.oContinueButton.clickAndWait(visitDetails.oContiueBtn,true);
        //filling out details about visit detils page
        visitDetails.oSelectSickOrInjuredService.click();
        visitDetails.oSelectDateAndTime.clickAndWait(menu.oProgressBar,false);
        visitDetails.oSelectTimeForVisit.click();
        visitDetails.oSelectDateDoneButton.clickAndWait(visitDetails.oSymptomsField,true);
        visitDetails.oSymptomsField.sendKeys("IGNORE - Booked by automation test..");
        dr.navigate().back();
        visitDetails.oContiueBtn.clickAndWait(paymentDetailsPage.oPromoCodeLink,true);
        //filling out details of payment page and validations
        paymentDetailsPage.oPromoCodeLink.clickAndWait(paymentDetailsPage.oPromoCodeInputTab,true);
        paymentDetailsPage.oPromoCodeInputTab.sendKeys("100PERCENT");

        paymentDetailsPage.oPromoCodeApplyButton.clickAndWait(paymentDetailsPage.oTapToAddInsuranceInput,true);
        dr.navigate().back();

        assertEquals("verification flatFee after applying promo code",paymentDetailsPage.oFlatFeePromo.getText(),"$0");

        paymentDetailsPage.oRequestDoctorButton.click();

    }

    @Test
    public void bookVisitWithCreditCard()
    {
        WebDriver dr = getDriver();

        M_MainPage homePage = new M_MainPage(dr);
        M_BookVisitPage bookVisitPage= new M_BookVisitPage(dr);
        M_VisitDetailspage visitDetails=new M_VisitDetailspage(dr);
        M_PaymentDetailsPage paymentDetailsPage=new M_PaymentDetailsPage(dr);
        M_MenuPage menu=new M_MenuPage(dr);
        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
        verifyVisible("Verify Login Button is displayed", homePage.oLoginBtn);
        homePage.clickSettingsButton();

        M_SettingsPage settingsPage = new M_SettingsPage(dr);
        settingsPage.setEnv("qa");
        homePage.oLoginBtn.click();
//        homePage.clickLoginButton();
        M_LoginPage loginPage = new M_LoginPage(dr);
        assertMatches("verify Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");

        loginPage.login();

        //filling out required data for book vist page
        bookVisitPage.oNoMedicalEmergencyButton.click();
        bookVisitPage.fillAddressDetails();
        dr.navigate().back();
        bookVisitPage.oSelectPatientProfile("Credit Card");
        bookVisitPage.oContinueButton.clickAndWait(visitDetails.oContiueBtn,true);
        //filling out details about visit detils page
        visitDetails.oSelectSickOrInjuredService.click();
        visitDetails.oSelectDateAndTime.clickAndWait(menu.oProgressBar,false);
        visitDetails.oSelectTimeForVisit.click();
        visitDetails.oSelectDateDoneButton.clickAndWait(visitDetails.oSymptomsField,true);
        visitDetails.oSymptomsField.sendKeys("IGNORE - Booked by automation test..");
        dr.navigate().back();
        visitDetails.oContiueBtn.clickAndWait(paymentDetailsPage.oRequestDoctorButton,true);
        //filling out details of payment page and validations
        //paymentDetailsPage.oPromoCodeLink.clickAndWait(paymentDetailsPage.oPromoCodeInputTab,true);
        //paymentDetailsPage.oPromoCodeInputTab.sendKeys("50PERCENT");

        //paymentDetailsPage.oPromoCodeApplyButton.clickAndWait(paymentDetailsPage.oTapToAddInsuranceInput,true);
        //dr.navigate().back();

        assertEquals("verification flatFee for credit card payment",paymentDetailsPage.oFlatFee.getText(),"$99");

        paymentDetailsPage.oRequestDoctorButton.click();

    }
}
