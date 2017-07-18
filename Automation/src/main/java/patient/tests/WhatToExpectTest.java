package patient.tests;

import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import patient.pages.*;
import utilities.DriverManager;

/**
 * Created by mihai.muresan on 7/17/2017.
 */
public class WhatToExpectTest {

    @Test
    @Parameters({ "url" })
    public void checkWhatToExpectScreens(String url) throws Exception {
        //small tests for some page objects
        WebDriver dr = DriverManager.getDriver();
        HomePage homePage = new HomePage(dr);
        LoginPage login = new LoginPage(dr);
        Menu menu = new Menu(dr);
        VisitsPage visits = new VisitsPage(dr);
        WhatToExpectPage wte = new WhatToExpectPage(dr);
        CommonWebValidate validate = new CommonWebValidate(dr);
        CommonWebElement.setbMonitorMode(true);
        login.login("mihai.muresa@heal.com", "Heal4325");
        homePage.selectFromMenu(menu.oVisitsLnk);
        visits.oWhatToExpectBtn.click();
        validate.verifyVisible("Verify text is displayed", wte.oScreen1Row1Text);
        validate.verifyVisible("Verify text is displayed", wte.oScreen2Row1Text);
        validate.verifyVisible("Verify button is displayed", wte.oNextBtn);
        validate.verifyVisible("Verify text label is displayed", wte.oHeartHandsLogo);


    }
}
