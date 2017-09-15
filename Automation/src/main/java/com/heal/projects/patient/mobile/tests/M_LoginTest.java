package com.heal.projects.patient.mobile.tests;

import com.heal.framework.test.TestBase;
import com.heal.projects.patient.mobile.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by vahanmelikyan on 9/2/2017.
 */
public class M_LoginTest  extends TestBase {

    @Test
    public void testLoop(){
        int numberOfVisitsToBook = 20;
        int passedRuns = 0;
        int failedRuns = 0;
        for (int i = 0; i < numberOfVisitsToBook; i++) {
            try {
                //createVisitWith50PercentPromoTest(); // put here the desired test to be run on loop
                //createVisitWith100PercentPromoTest();
                //createVisitWithInsuranceTest();
                //bookVisitWithPromoCode();
                BookVisitWithInsurance();
                //bookVisitWithCreditCard();
                passedRuns++;
            } catch (Exception e) {
                failedRuns++;
                //quitDriver();
                e.printStackTrace();
            }
            System.out.println("Passed " + passedRuns);
            System.out.println("Failed " + failedRuns);
        }
        System.out.println(passedRuns + " Passed Runs"); // display how many times the test passed on given visits booked
        System.out.println(failedRuns + " Failed Runs"); // display how many times the test failed on given visits booked
    }



    @Test
    public void launchTest(){
        WebDriver dr = getDriver();
        M_MainPage homePage = new M_MainPage(dr);
        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
    }

    @Test
    public void loginPageValidations(){
        WebDriver dr = getDriver();
        M_MainPage homePage = new M_MainPage(dr);
        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
        homePage.clickSettingsButton();

        M_SettingsPage settingsPage = new M_SettingsPage(dr);
        settingsPage.setEnv("qa");

        homePage.clickLoginButton();
        M_LoginPage loginPage = new M_LoginPage(dr);
        assertEquals("verify email field is displayed", loginPage.oEmailInput.isDisplayed(), true);
        assertEquals("verify password field is displayed", loginPage.oPasswordInput.isDisplayed(), true);
        assertEquals("verify Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");
        assertEquals("verify login button is displayed", loginPage.oLoginBtn.isDisplayed(), true);
    }

    @Test
    public void loginWithValidCredentials(){

        WebDriver dr = getDriver();
        M_MainPage homePage = new M_MainPage(dr);

        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
        verifyVisible("Verify Login Button is displayed", homePage.oLoginBtn);
        homePage.clickSettingsButton();

        M_SettingsPage settingsPage = new M_SettingsPage(dr);
        settingsPage.setEnv("qa");

        homePage.clickLoginButton();
        M_LoginPage loginPage = new M_LoginPage(dr);
        assertEquals("verify Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");

        loginPage.login();
        loginPage.oProgressBar.sync(60);




    }
    @Test
    public void BookVisitWithInsurance(){
        WebDriver dr = getDriver();
        M_MainPage homePage = new M_MainPage(dr);
        M_BookVisitPage bookVisitPage= new M_BookVisitPage(dr);
        M_VisitDetailspage visitDetails=new M_VisitDetailspage(dr);
        M_PaymentDetailsPage paymentDetailsPage=new M_PaymentDetailsPage(dr);
        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
        verifyVisible("Verify Login Button is displayed", homePage.oLoginBtn);
        homePage.clickSettingsButton();

        M_SettingsPage settingsPage = new M_SettingsPage(dr);
        settingsPage.setEnv("qa");

        homePage.clickLoginButton();
        M_LoginPage loginPage = new M_LoginPage(dr);
        assertEquals("verify Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");

        loginPage.login();

        bookVisitPage.oNoMedicalEmergencyButton.click();
        bookVisitPage.fillAddressDetails();
        dr.navigate().back();
        bookVisitPage.oSelectPatientProfile("Insurance");
        bookVisitPage.oContinueButton.clickAndWait(visitDetails.oContiueBtn,true);

        visitDetails.oSelectSickOrInjuredService.click();
        visitDetails.oSelectDateAndTime.clickAndWait(loginPage.oProgressBar,false);
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
    public void bookVisitWithPromoCode()
    {
        WebDriver dr = getDriver();
        M_MainPage homePage = new M_MainPage(dr);
        M_BookVisitPage bookVisitPage= new M_BookVisitPage(dr);
        M_VisitDetailspage visitDetails=new M_VisitDetailspage(dr);
        M_PaymentDetailsPage paymentDetailsPage=new M_PaymentDetailsPage(dr);
        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
        verifyVisible("Verify Login Button is displayed", homePage.oLoginBtn);
        homePage.clickSettingsButton();

        M_SettingsPage settingsPage = new M_SettingsPage(dr);
        settingsPage.setEnv("qa");

        homePage.clickLoginButton();
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
        visitDetails.oSelectDateAndTime.clickAndWait(loginPage.oProgressBar,false);
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


}
