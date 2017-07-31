package patient.tests;

import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import patient.pages.*;
import utilities.DriverManager;

/**
 * Created by mihai.muresan on 7/13/2017.
 */
public class ChooseProfileTest {

    @Test
    @Parameters({ "url" })
    public void checkPofilesPageElements() throws Exception {
        WebDriver dr = DriverManager.getDriver();
        ChooseProfilePage profile = new ChooseProfilePage(dr);
        HomePage homePage = new HomePage(dr);
        Menu menu = new Menu(dr);
        LoginPage login = new LoginPage(dr);
        CommonWebValidate validate = new CommonWebValidate(dr);
        CommonWebElement.setbMonitorMode(false);
        login.login("mihai.muresan@heal.com","Heal4325");
        homePage.selectFromMenu(menu.oProfilesLnk);
        validate.verifyVisible("Verify Manage Profiles label is displayed", profile.oManageProfilesLabel);
        validate.verifyVisible("Verify Choose Profile label is displayed", profile.oChooseProfileLabel);
        validate.verifyVisible("Verify Profile name is displayed", profile.oProfileName);
        validate.verifyVisible("Verify Add Patient logo is displayed", profile.oAddPatientLogo);
        validate.verifyVisible("Verify Add Patient label is displayed", profile.oAddPatientLabel);
        validate.verifyVisible("Verify Continue button is displayed", profile.oContinueBtn);

    }
}
