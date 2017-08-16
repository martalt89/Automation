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

    String firstName = "Priyanka";
    String lastaName = "Halder";
    String insuranceID = "JQU397M89484";
    String insuranceGroup = "A45878";
    String insuranceProvider = "Anthem";
    String email = "test@test.com";
    String phoneNumber = "18182123842";
    String relationship = "Friend";
    String gender = "Female";


    @Test(groups = {"dev", "critical"})
    public void addInsuranceToExistingPatient() {
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        HomePage homePage = new HomePage(dr);
        ManageProfilePage manageProfilePage = new ManageProfilePage(dr);
        Menu menu = new Menu(dr);

        //Test steps
        loginPage.login();
        homePage.selectFromMenu(menu.oProfilesLnk);
        validate.verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
        manageProfilePage.oAddPatientbtn.click();
        //manageProfilePage.oContinueButton.clickAndWait(menu.oLoadingBar, false);
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
        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        HomePage homePage = new HomePage(dr);
        ManageProfilePage manageProfilePage = new ManageProfilePage(dr);
        Menu menu = new Menu(dr);
        //Test steps
        loginPage.login("mihaix3@heal.com", "Heal4325");
        homePage.selectFromMenu(menu.oProfilesLnk);
        validate.verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
        //add patient
        manageProfilePage.oAddPatientbtn.click();
        manageProfilePage.typePatientDataFromExcel(testData);
        manageProfilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);
        validate.verifyVisible("Verify if added patient name is in patients profiles list", manageProfilePage.getPatientByText(testData.sFirstname));
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
        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        HomePage homePage = new HomePage(dr);
        ManageProfilePage manageProfilePage = new ManageProfilePage(dr);
        Menu menu = new Menu(dr);

        //Test steps
        loginPage.login();
        homePage.selectFromMenu(menu.oProfilesLnk);
        validate.verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
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
        // We do a check first that the element is displayed
        //TODO: remove element check after bug is fixed
        if (validate.verifyMatches("Checking if the updated patient firstname is displayed in profiles list", manageProfilePage.getPatientByText(sFnUpdated).getText(), sFnUpdated)) {
            manageProfilePage.clickPatientByText(sFnUpdated);
        } else {
            manageProfilePage.reload();
        }
        manageProfilePage.oContinueButton.click();
        validate.verifyEquals("Verify Firstname was updated",manageProfilePage.oFirstNameInput.getText(), sFnUpdated);
        validate.verifyEquals("Verify Lastname was updated",manageProfilePage.oLastNameInput.getText(), sLnUpdated);
        validate.verifyEquals("Verify Email was updated",manageProfilePage.oEmailInput.getText(), sEmailUpdated);
        validate.verifyEquals("Verify Phone was updated",manageProfilePage.oPhoneNmbInput.getText(), sPhoneUpdated);
        validate.verifyEquals("Verify Date of birth was updated",manageProfilePage.oDateOfBirthInput.getText(), sPhoneUpdated);
        validate.verifyEquals("Verify Relationship was updated",manageProfilePage.oRelationshipInput.getText(), sRelationshipUpdated);
        validate.verifyEquals("Verify Gender was updated",manageProfilePage.oGenderInput.getText(), sGenderUpdated);
    }
}