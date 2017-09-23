package com.heal.projects.patient.mobile.tests;

import com.heal.framework.foundation.SysTools;
import com.heal.framework.test.TestBase;
import com.heal.projects.patient.mobile.pages.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.http.protocol.HTTP;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.management.relation.Relation;

/**
 * Created by vahanmelikyan on 9/2/2017.
 */
public class M_LoginTest  extends TestBase {

    @Test
    public void testLoop(){
        int numberOfVisitsToBook = 5;
        int passedRuns = 0;
        int failedRuns = 0;
        for (int i = 0; i < numberOfVisitsToBook; i++) {
            try {

//                bookVisitWith50PercentPromoCode();
//                BookVisitWithInsurance();
                addPatientProfileWithInsurance();

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

        homePage.oLoginBtn.click();
//        homePage.clickLoginButton();
        M_LoginPage loginPage = new M_LoginPage(dr);
        assertEquals("verify email field is displayed", loginPage.oEmailInput.isDisplayed(), true);
        assertEquals("verify password field is displayed", loginPage.oPasswordInput.isDisplayed(), true);
        assertEquals("verify Text forgot password", loginPage.oForgotPasswordText.getText(), "Forgot password");
        assertEquals("verify login button is displayed", loginPage.oLoginBtn.isDisplayed(), true);
    }

    @Test
    public void loginWithValidCredentials(){

        WebDriver dr = getDriver();
        M_MenuPage menu=new M_MenuPage(dr);
        M_MainPage homePage = new M_MainPage(dr);

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
        menu.oProgressBar.sync(60);




    }

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

    @Test
    public void addPatientProfileWithInsurance(){

        WebDriver dr = getDriver();
        AppiumDriver mobileDriver = getMobileDriver();
        M_MainPage homePage = new M_MainPage(dr);
        M_MenuPage menu=new M_MenuPage(dr);
        M_BookVisitPage bookVisitPage= new M_BookVisitPage(dr);
        M_ProfilesPage profilesPage=new M_ProfilesPage(dr);
        M_LoginPage loginPage = new M_LoginPage(dr);
        M_SettingsPage settingsPage = new M_SettingsPage(dr);
//
//        mobileDriver.closeApp();
//        mobileDriver.launchApp();
//        verifyVisible("Verify Register Button is displayed", homePage.oRegisterBtn);
//        verifyVisible("Verify Login Button is displayed", homePage.oLoginBtn);
        homePage.clickSettingsButton();

        profilesPage.mobileDeviceScrollPage(50,85,50,25,2000);
       // mobileDriver.hideKeyboard();
        settingsPage.setEnv("qa");
        homePage.oLoginBtn.click();
        //homePage.clickLoginButton();

        loginPage.login();

        //filling out required data for book vist page
        bookVisitPage.oNoMedicalEmergencyButton.click();
        menu.oMenuButton.clickAndWait(menu.oProfiles,true);
        menu.oProfiles.click();

        profilesPage.oAddProfilesButton.clickAndWait(profilesPage.oGotItButton,true);
        profilesPage.oGotItButton.click();
        profilesPage.oFirstNameField.sendKeys("vahan1");
        profilesPage.oLastNameField.sendKeys("Melikyan");
        profilesPage.oDOBField.click();
        profilesPage.DatePicker("Dec","12","1991");


        System.out.println("10");


        profilesPage.oDateOkButton.click();

        profilesPage.oPhoneNumberField.sendKeys("2132949306");
        mobileDriver.hideKeyboard();
        profilesPage.oRelationShipField.click();
        //if(profilesPage.selectFromDropdown(M_ProfilesPage.Relationship.Child))
        profilesPage.selectFromDropdownRelation(M_ProfilesPage.Relationship.Child);
        profilesPage.oGenderField.click();
        profilesPage.oGenderMale.click();

        profilesPage.oUseInsuranceSwitch.click();

        profilesPage.mobileDeviceScrollPage(50,85,50,25,2000);

        profilesPage.oInsuranceProviderField.click();
        profilesPage.selectFromDropdownInsurance(M_ProfilesPage.InsuranceProviderCompany.CIGNA);
        profilesPage.oEnterMemberNumber.waitForVisible();

        profilesPage.mobileDeviceScrollPage(50,85,50,25,2000);


        // mobileDriver.swipe();
        profilesPage.oEnterMemberNumber.sendKeys("COST_ESTIMATES_025");
        mobileDriver.hideKeyboard();
        profilesPage.oEnterGroupNumber.sendKeys("BC001");
        mobileDriver.hideKeyboard();
        profilesPage.oSaveButton.click();


    }




}
