package com.heal.projects.patient.web.tests;

import com.heal.framework.test.TestBase;
import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.CommonWebValidate;
import com.heal.projects.patient.web.pages.HomePage;
import com.heal.projects.patient.web.pages.LoginPage;
import com.heal.projects.patient.web.pages.Menu;
import com.heal.projects.patient.web.pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by vahanmelikyan on 8/1/17.
 */
public class ProfileTest extends TestBase {
    TestData testData = new TestData(TestData.PATIENT_SHEET);

    String firstName = "Vahan";
    String lastaName = "Melikyan";
    String insuranceID = "JQU056M90707";
    String insuranceGroup = "A45878";
    String insuranceProvider = "Anthem";
    String email = "test@test.com";
    String dob = "06/10/1985";
    String phoneNumber = "18182123842";
    String relationship = "Friend";
    String gender = "Female";


    @Test(groups = {"dev", "critical"})
    public void addInsuranceToExistingPatient() {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
//        CommonWebValidate validate = new CommonWebValidate(dr);
        CommonWebValidate validate = getValidate();
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        HomePage homePage = new HomePage(dr);
        ProfilePage profilePage = new ProfilePage(dr);
        Menu menu = new Menu(dr);

        //Test steps
        loginPage.login();
        homePage.selectFromMenu(menu.oProfilesLnk);
        verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
        profilePage.oAddPatientbtn.click();
        //manageProfilePage.oContinueButton.clickAndWait(menu.oLoadingBar, false);
        profilePage.oFirstNameInput.sendKeys(firstName);
        profilePage.oLastNameInput.sendKeys(lastaName);
        profilePage.oEmailInput.sendKeys(email);
        profilePage.oPhoneNmbInput.sendKeys(phoneNumber);
        profilePage.oDateOfBirthInput.sendKeys(dob);
        profilePage.oRelationshipInput.selectByVisibleTextAngular(relationship);
        profilePage.oGenderInput.selectByVisibleTextAngular(gender);
        profilePage.oInsuranceProviderInput.selectByVisibleTextAngular(insuranceProvider);
        profilePage.oMemberIdInput.jsSendKeys(insuranceID);  //insurance ID
        profilePage.oGroupIdInput.jsSendKeys(insuranceGroup);  //group ID
        profilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);
        if (validate.verifyMatches("Checking if the 'Choose profile' is displayed", profilePage.oSubtitle.getText(), "Patient details")) {
            System.out.println("Successfully added REAL insurance.");
        } else {
            System.out.println("Could not add REAL insurance. Trying with TEST insurance...");
            profilePage.oMemberIdInput.sendKeys("COST_ESTIMATES_025");  //insurance ID
            profilePage.oGroupIdInput.sendKeys("X0001004");  //group ID
            profilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);
            validate.verifyMatches("Checking if the 'Choose profile' is displayed", profilePage.oSubtitle.getText(), "Choose profile");
            System.out.println("Successfully added TEST insurance.");
        }
    }


    @Test(groups = {"dev", "critical"})
    public void addProfile() {

        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        HomePage homePage = new HomePage(dr);
        ProfilePage profilePage = new ProfilePage(dr);
        Menu menu = new Menu(dr);
        //Test steps
        loginPage.login();
        homePage.selectFromMenu(menu.oProfilesLnk);
            verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
        //add patient
        profilePage.oAddPatientbtn.click();
        profilePage.typePatientDataFromExcel(testData);
        profilePage.oSaveAndContinueBtn.jsClickAndWait(menu.oLoadingBar, false);

            assertEquals("Verify if added patient name is in patients profiles list", profilePage.getPatientByText(testData.sFirstname).getText(), testData.sFirstname);
        menu.selectFromMenu("Sign out");



    }


        @Test(groups = {"dev", "critical"})
    public void editProfile() {

        String sFnUpdated = "updated" + testData.sFirstname;
        String sLnUpdated = "updated" + testData.sLastname;
        String sEmailUpdated = "updated" + testData.sEmail;
        String sPhoneUpdated = "1-541-967-0010";
        String sDateOfBirthUpdated = "01/01/1980";
        String sRelationshipUpdated = "Child";
        String sGenderUpdated = "Female";

        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();

        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        HomePage homePage = new HomePage(dr);
        ProfilePage profilePage = new ProfilePage(dr);
        Menu menu = new Menu(dr);

        //Test steps
        loginPage.login();
        homePage.selectFromMenu(menu.oProfilesLnk);
        verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
        //add patient
            profilePage.oAddPatientbtn.click();
            profilePage.typePatientDataFromExcel(testData);
            profilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);
        //edit patient
            profilePage.clickPatientByText(testData.sFirstname);
            profilePage.oContinueButton.click();
            profilePage.oFirstNameInput.sendKeys(sFnUpdated);
            profilePage.oLastNameInput.sendKeys(sLnUpdated);
            profilePage.oEmailInput.sendKeys(sEmailUpdated);
            profilePage.oPhoneNmbInput.sendKeys(sPhoneUpdated);
            profilePage.oDateOfBirthInput.sendKeys(sDateOfBirthUpdated);
            profilePage.oRelationshipInput.selectByVisibleTextAngular(sRelationshipUpdated);
            profilePage.oGenderInput.selectByVisibleTextAngular(sGenderUpdated);
            profilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);
        //check that changes have been made
        //NOTE:
        // There is currently a web bug here: When updating Firstname, it doesn't reflect only after reloading the page


            profilePage.reload();
            profilePage.clickPatientByText(sFnUpdated);
            profilePage.oContinueButton.click();
            //TODO: Need to figure out how to get the text values of firstname, lastname, email, phoneNumber, dateOfBirth
//        verifyEquals("Verify Firstname was updated",manageProfilePage.oFirstNameInput.getText(), sFnUpdated);
//        verifyEquals("Verify Lastname was updated",manageProfilePage.oLastNameInput.getText(), sLnUpdated);
//        verifyEquals("Verify Email was updated",manageProfilePage.oEmailInput.getText(), sEmailUpdated);
//        verifyEquals("Verify Phone was updated",manageProfilePage.oPhoneNmbInput.getText(), sPhoneUpdated);
//        verifyEquals("Verify Date of birth was updated",manageProfilePage.oDateOfBirthInput.getText(), sPhoneUpdated);
        verifyEquals("Verify Relationship was updated",profilePage.oRelationshipInput.getText(), sRelationshipUpdated);
        verifyEquals("Verify Gender was updated",profilePage.oGenderInput.getText(), sGenderUpdated);
//            assertEquals("Verify Firstname was updated",manageProfilePage.oFirstNameInput.getText(), sFnUpdated);
//            assertEquals("Verify Lastname was updated",manageProfilePage.oLastNameInput.getText(), sLnUpdated);
//            assertEquals("Verify Email was updated",manageProfilePage.oEmailInput.getText(), sEmailUpdated);
//            assertEquals("Verify Phone was updated",manageProfilePage.oPhoneNmbInput.getText(), sPhoneUpdated);
//            assertEquals("Verify Date of birth was updated",manageProfilePage.oDateOfBirthInput.getText(), sPhoneUpdated);
            assertEquals("Verify Relationship was updated",profilePage.oRelationshipInput.getText(), sRelationshipUpdated);
            assertEquals("Verify Gender was updated",profilePage.oGenderInput.getText(), sGenderUpdated);

    }
}