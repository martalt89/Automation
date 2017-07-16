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
public class ManageProfileTest {

    @Test
    @Parameters({ "url" })
    public void manageProfile(String url) throws Exception {
        //Small test for Manage Profile page
        WebDriver dr = DriverManager.getDriver();
        LoginPage login = new LoginPage(dr);
        HomePage homePage = new HomePage(dr);
        Menu menu = new Menu(dr);
        ChooseProfilePage chooseProfile = new ChooseProfilePage(dr);
        ManageProfilePage profile = new ManageProfilePage(dr);
        CommonWebValidate validate = new CommonWebValidate(dr);
        CommonWebElement.setbMonitorMode(true);
        login.login("mihai.muresan@heal.com", "Heal4325");
        homePage.selectFromMenu(menu.oProfilesLnk);
        chooseProfile.selectMainProfile();

        validate.verifyVisible("Verify Patient Details label is displayed", profile.oPatientDetailsLabel);
        validate.verifyVisible("Verify Patient Info label is displayed", profile.oPatientInfoLabel);

    }
}
