package com.heal.projects.patient.web.tests;

import com.heal.framework.foundation.SysTools;
import com.heal.framework.test.TestBase;
import com.heal.projects.patient.web.pages.HomePage;
import com.heal.projects.patient.web.pages.LoginPage;
import com.heal.projects.patient.web.pages.Menu;
import com.heal.projects.patient.web.pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class sandbox extends TestBase {

    @Test
    public void sandboxLogin(){
        WebDriver dr = getDriver();
        LoginPage loginPage = new LoginPage(dr);
        HomePage homePage = new HomePage(dr);
        Menu menu = new Menu(dr);
        ProfilePage profilePage = new ProfilePage(dr);

        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.oUserNameInput.sendKeys("vahan+qa@heal.com");
        loginPage.oPasswordInput.sendKeys("Heal4325!");
        loginPage.oLoginBtn.clickAndWait(menu.oLoadingBar, false);
        menu.selectFromMenu("profiles");
        assertMatches("Verifying the page titile", profilePage.oPageTitle.getText(), "Manage profilessss");



    }

}
