package patient.tests;

import framework.test.TestBase;
import framework.test.TestData;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import patient.pages.*;

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
        ManageProfilePage manageProfilePage = new ManageProfilePage(dr);
        Menu menu = new Menu(dr);

        //Test steps
        loginPage.login();
        homePage.selectFromMenu(menu.oProfilesLnk);
        verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
        manageProfilePage.oAddPatientbtn.click();
        //manageProfilePage.oContinueButton.clickAndWait(menu.oLoadingBar, false);
        manageProfilePage.oFirstNameInput.sendKeys(firstName);
        manageProfilePage.oLastNameInput.sendKeys(lastaName);
        manageProfilePage.oEmailInput.sendKeys(email);
        manageProfilePage.oPhoneNmbInput.sendKeys(phoneNumber);
        manageProfilePage.oDateOfBirthInput.sendKeys(dob);
        manageProfilePage.oRelationshipInput.selectByVisibleTextAngular(relationship);
        manageProfilePage.oGenderInput.selectByVisibleTextAngular(gender);
        manageProfilePage.oInsuranceProviderInput.selectByVisibleTextAngular(insuranceProvider);
        manageProfilePage.oMemberIdInput.jsSendKeys(insuranceID);  //insurance ID
        manageProfilePage.oGroupIdInput.jsSendKeys(insuranceGroup);  //group ID
        manageProfilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);
        if (validate.verifyMatches("Checking if the 'Choose profile' is displayed", manageProfilePage.oSubtitle.getText(), "Choose Profile")) {
            System.out.println("Successfully added REAL insurance.");
        } else {
            System.out.println("Could not add REAL insurance. Trying with TEST insurance...");
            manageProfilePage.oMemberIdInput.sendKeys("COST_ESTIMATES_025");  //insurance ID
            manageProfilePage.oGroupIdInput.sendKeys("X0001004");  //group ID
            manageProfilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);
            validate.verifyMatches("Checking if the 'Choose profile' is displayed", manageProfilePage.oSubtitle.getText(), "Choose profile");
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
        ManageProfilePage manageProfilePage = new ManageProfilePage(dr);
        Menu menu = new Menu(dr);
        //Test steps
        loginPage.login();
        homePage.selectFromMenu(menu.oProfilesLnk);
            verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
        //add patient
        manageProfilePage.oAddPatientbtn.click();
        manageProfilePage.typePatientDataFromExcel(testData);
        manageProfilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);

            assertEquals("Verify if added patient name is in patients profiles list", manageProfilePage.getPatientByText(testData.sFirstname).getText(), testData.sFirstname);
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
        ManageProfilePage manageProfilePage = new ManageProfilePage(dr);
        Menu menu = new Menu(dr);

        //Test steps
        loginPage.login();
        homePage.selectFromMenu(menu.oProfilesLnk);
        verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
        //add patient
        manageProfilePage.oAddPatientbtn.click();
        manageProfilePage.typePatientDataFromExcel(testData);
        manageProfilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);
        //edit patient
        manageProfilePage.clickPatientByText(testData.sFirstname);
        manageProfilePage.oContinueButton.click();
        manageProfilePage.oFirstNameInput.sendKeys(sFnUpdated);
        manageProfilePage.oLastNameInput.sendKeys(sLnUpdated);
        manageProfilePage.oEmailInput.sendKeys(sEmailUpdated);
        manageProfilePage.oPhoneNmbInput.sendKeys(sPhoneUpdated);
        manageProfilePage.oDateOfBirthInput.sendKeys(sDateOfBirthUpdated);
        manageProfilePage.oRelationshipInput.selectByVisibleTextAngular(sRelationshipUpdated);
        manageProfilePage.oGenderInput.selectByVisibleTextAngular(sGenderUpdated);
        manageProfilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);
        //check that changes have been made
        //NOTE:
        // There is currently a web bug here: When updating Firstname, it doesn't reflect only after reloading the page


            manageProfilePage.reload();
        manageProfilePage.clickPatientByText(sFnUpdated);
        manageProfilePage.oContinueButton.click();
            //TODO: Need to figure out how to get the text values of firstname, lastname, email, phoneNumber, dateOfBirth
//        verifyEquals("Verify Firstname was updated",manageProfilePage.oFirstNameInput.getText(), sFnUpdated);
//        verifyEquals("Verify Lastname was updated",manageProfilePage.oLastNameInput.getText(), sLnUpdated);
//        verifyEquals("Verify Email was updated",manageProfilePage.oEmailInput.getText(), sEmailUpdated);
//        verifyEquals("Verify Phone was updated",manageProfilePage.oPhoneNmbInput.getText(), sPhoneUpdated);
//        verifyEquals("Verify Date of birth was updated",manageProfilePage.oDateOfBirthInput.getText(), sPhoneUpdated);
        verifyEquals("Verify Relationship was updated",manageProfilePage.oRelationshipInput.getText(), sRelationshipUpdated);
        verifyEquals("Verify Gender was updated",manageProfilePage.oGenderInput.getText(), sGenderUpdated);
//            assertEquals("Verify Firstname was updated",manageProfilePage.oFirstNameInput.getText(), sFnUpdated);
//            assertEquals("Verify Lastname was updated",manageProfilePage.oLastNameInput.getText(), sLnUpdated);
//            assertEquals("Verify Email was updated",manageProfilePage.oEmailInput.getText(), sEmailUpdated);
//            assertEquals("Verify Phone was updated",manageProfilePage.oPhoneNmbInput.getText(), sPhoneUpdated);
//            assertEquals("Verify Date of birth was updated",manageProfilePage.oDateOfBirthInput.getText(), sPhoneUpdated);
            assertEquals("Verify Relationship was updated",manageProfilePage.oRelationshipInput.getText(), sRelationshipUpdated);
            assertEquals("Verify Gender was updated",manageProfilePage.oGenderInput.getText(), sGenderUpdated);

    }
}