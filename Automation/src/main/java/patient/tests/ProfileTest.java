package patient.tests;

import foundation.SysTools;
import framework.exception.CommonException;
import framework.test.TestBase;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import patient.pages.*;
import utilities.DriverManager;

/**
 * Created by vahanmelikyan on 8/1/17.
 */
public class ProfileTest extends TestBase {
    String firstName = "Priyanka";
    String lastaName = "Halder";
    String insuranceID = "JQU397M89484";
    String insuranceGroup = "A45878";
    String insuranceProvider = "Anthem";


    @Test(groups = {"devv", "critical"})
    public void addInsuranceToExistingPatient(){
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = DriverManager.getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr);
        HomePage homePage = new HomePage(dr);
        ManageProfilePage manageProfilePage = new ManageProfilePage(dr);
        Menu menu = new Menu(dr);

        //Test steps
        try {
            loginPage.login();
            homePage.selectFromMenu(menu.oProfilesLnk);
            validate.assertMatches("Checking if the 'Manage Profile' is displayed", manageProfilePage.oManageProfilesLabel.getText(), "Manage profiles");
            manageProfilePage.oAddPatientbtn.click();
            //manageProfilePage.oContiuneButton.clickAndWait(menu.oLoadingBar, false);
            manageProfilePage.oFirstNameInput.sendKeys(firstName);
            manageProfilePage.oLastNameInput.sendKeys(lastaName);
            manageProfilePage.oDateOfBirthInput.sendKeys("09/08/1984");

            manageProfilePage.oMemberIdInput.sendKeys(insuranceID);  //insurance ID

            manageProfilePage.oInsuranceProviderInput.selectByVisibleTextAngular(insuranceProvider);
            manageProfilePage.oGroupIdInput.sendKeys(insuranceGroup);  //group ID

            manageProfilePage.oSaveAndContinueBtn.clickAndWait(menu.oLoadingBar, false);

            //SysTools.sleepFor(1);
            if(validate.verifyMatches("Checking if the 'Choose profile' is displayed", manageProfilePage.oSubtitile.getText(), "Choose profile")) {
                System.out.println("Successfully added REAL insurance.");
            }else {
                manageProfilePage.oMemberIdInput.sendKeys("COST_ESTIMATES_025");  //insurance ID
                manageProfilePage.oGroupIdInput.sendKeys("X0001004");  //group ID
                System.out.println("Successfully added TEST insurance.");
            }

            //SysTools.sleepFor(15);
        } catch (CommonException e) {
            e.printStackTrace();
        }


    }
}
