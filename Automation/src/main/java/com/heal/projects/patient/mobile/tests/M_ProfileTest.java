package com.heal.projects.patient.mobile.tests;

import com.heal.framework.foundation.SysTools;
import com.heal.framework.test.TestBase;
import com.heal.projects.patient.mobile.pages.*;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class M_ProfileTest extends TestBase {
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
        homePage.clickSettingsButton();
        settingsPage.setEnv("qa");
        homePage.oLoginBtn.click();
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
        profilesPage.oDateOkButton.click();
        profilesPage.oPhoneNumberField.sendKeys("2132949306");
        mobileDriver.hideKeyboard();
        profilesPage.oRelationShipField.click();
        profilesPage.selectFromDropdownRelation(M_ProfilesPage.Relationship.Child);
        profilesPage.oGenderField.click();
        profilesPage.oGenderMale.click();
        profilesPage.oUseInsuranceSwitch.click();
        profilesPage.mobileDeviceScrollPage(50,85,50,25,2000);
        profilesPage.oInsuranceProviderField.click();
        profilesPage.selectFromDropdownInsurance(M_ProfilesPage.InsuranceProviderCompany.CIGNA);
        profilesPage.oEnterMemberNumber.waitForVisible();
        profilesPage.mobileDeviceScrollPage(50,85,50,25,2000);
        profilesPage.oEnterMemberNumber.sendKeys("COST_ESTIMATES_025");
        mobileDriver.hideKeyboard();
        profilesPage.oEnterGroupNumber.sendKeys("BC001");
        mobileDriver.hideKeyboard();
        profilesPage.oSaveButton.click();


    }

    @Test
    public void addPatientProfileWithoutInsurance(){
        WebDriver dr = getDriver();
        AppiumDriver mobileDriver = getMobileDriver();
        M_MainPage homePage = new M_MainPage(dr);
        M_MenuPage menu=new M_MenuPage(dr);
        M_BookVisitPage bookVisitPage= new M_BookVisitPage(dr);
        M_ProfilesPage profilesPage=new M_ProfilesPage(dr);
        M_LoginPage loginPage = new M_LoginPage(dr);
        M_SettingsPage settingsPage = new M_SettingsPage(dr);
        homePage.clickSettingsButton();
        settingsPage.setEnv("qa");
        homePage.oLoginBtn.click();
        loginPage.login();
        //filling out required data for book vist page
        bookVisitPage.oNoMedicalEmergencyButton.click();
        menu.oMenuButton.clickAndWait(menu.oProfiles,true);
        menu.oProfiles.click();
        profilesPage.oAddProfilesButton.clickAndWait(profilesPage.oGotItButton,true);
        profilesPage.oGotItButton.click();
        profilesPage.oFirstNameField.sendKeys("Jay");
        profilesPage.oLastNameField.sendKeys("Purohit");
        profilesPage.oDOBField.click();
        profilesPage.DatePicker("Dec","12","1991");
        profilesPage.oDateOkButton.click();
        profilesPage.oEmailField.sendKeys("jay.purohit@heal.com");
        mobileDriver.hideKeyboard();
        profilesPage.oPhoneNumberField.sendKeys("2132949306");
        mobileDriver.hideKeyboard();
        profilesPage.oRelationShipField.click();
        profilesPage.selectFromDropdownRelation(M_ProfilesPage.Relationship.Child);
        profilesPage.oGenderField.waitForVisible();
        profilesPage.oGenderField.click();
        profilesPage.oGenderMale.click();
        SysTools.sleepFor(2);
        profilesPage.mobileDeviceScrollPage(50,85,50,25,2000);
        profilesPage.oSaveButton.click();

    }
}
