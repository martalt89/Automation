package com.heal.projects.patient.tests;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.CommonWebValidate;
import com.heal.projects.patient.pages.AddCardPage;
import com.heal.projects.patient.pages.HomePage;
import com.heal.projects.patient.pages.LoginPage;
import com.heal.projects.patient.pages.Menu;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by mihai.muresan on 7/17/2017.
 */
public class AddCardTest extends TestBase {

    @Test
    @Parameters({ "url" })
    public void checkPofilesPageElements() throws Exception {
        WebDriver dr = getDriver();
        AddCardPage payment = new AddCardPage(dr);
        HomePage homePage = new HomePage(dr);
        LoginPage login = new LoginPage(dr);
        Menu menu = new Menu(dr);
        CommonWebValidate validate = new CommonWebValidate(dr);
        CommonWebElement.setbMonitorMode(false);
        login.login("mihaiqa1@heal.com", "Heal4325");
        homePage.selectFromMenu(menu.oPaymentsLnk);
        validate.verifyVisible("Verify Payments label is displayed", payment.oPaymentsLabel);


    }
}
