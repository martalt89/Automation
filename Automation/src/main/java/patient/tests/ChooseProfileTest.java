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
    public void checkPofilesPageElements(String url) throws Exception {
        WebDriver dr = DriverManager.getDriver();
        ChooseProfilePage profile = new ChooseProfilePage(dr);
        HomePage homePage = new HomePage(dr);
        Menu menu = new Menu(dr);
        CommonWebValidate validate = new CommonWebValidate(dr);
        CommonWebElement.setbMonitorMode(true);

        //we need a login method
        LoginPage loginPage = new LoginPage(dr, url);
        loginPage.oUserNameInput.sendKeys("mihai.muresan@heal.com");
        loginPage.oPasswordInput.sendKeys("Heal4325");
        loginPage.oLoginBtn.click();


        homePage.selectFromMenu(menu.oProfilesLnk);
        validate.verifyVisible("Verify Manage Profiles label is displayed", profile.oManageProfilesLabel);
        validate.verifyVisible("Verify Choose Profile label is displayed", profile.oChooseProfileLabel);
        validate.verifyVisible("Verify Profile name is displayed", profile.oProfileName);
        validate.verifyVisible("Verify Add Patient logo is displayed", profile.oAddPatientLogo);
        validate.verifyVisible("Verify Add Patient label is displayed", profile.oAddPatientLabel);
        validate.verifyVisible("Verify Continue button is displayed", profile.oContinueBtn);

    }
}
