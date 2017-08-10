package patient.tests;

import framework.test.TestBase;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import patient.pages.*;

/**
 * Created by vahanmelikyan on 8/1/17.
 */
public class ProfileTest extends TestBase {
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
    public void addInsuranceToExistingPatient(){
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        //CommonWebValidate validate = new CommonWebValidate(dr);
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
            if(validate.verifyMatches("Checking if the 'Choose profile' is displayed", manageProfilePage.oSubtitile.getText(), "Choose profile")) {
                System.out.println("Successfully added REAL insurance.");
            }else {
                System.out.println("Could not add REAL insurance. Trying with TEST insurance...");
                manageProfilePage.oMemberIdInput.sendKeys("COST_ESTIMATES_025");  //insurance ID
                manageProfilePage.oGroupIdInput.sendKeys("X0001004");  //group ID
                manageProfilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);
                verifyMatches("Checking if the 'Choose profile' is displayed", manageProfilePage.oSubtitile.getText(), "Choose profile");
                System.out.println("Successfully added TEST insurance.");
            }
    }
}
