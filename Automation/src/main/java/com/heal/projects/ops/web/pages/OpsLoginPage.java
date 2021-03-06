package com.heal.projects.ops.web.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
/**
 *  Created by adrian.rosu on 18/08/2017.
 */
public class OpsLoginPage extends WebBase{

    public static final String URL = "https://ops"+ baseUrl +"/login";
    DashboardPage dashboardPage = new DashboardPage(oWebDriver);

    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oUserNameInput = new CommonWebElement("oUserNameInput", "xpath=//*[contains(@id,'username')]",oWebDriver);
    public CommonWebElement oPasswordInput = new CommonWebElement("oPasswordInput", "xpath=//*[contains(@id,'password')]", oWebDriver);
    public CommonWebElement oLoginBtn = new CommonWebElement("oLoginBtn", "xpath=//button[@type='submit']", oWebDriver);


    //////////////////
    // Constructors //
    //////////////////
    public OpsLoginPage(WebDriver oTargetDriver){
        super(oTargetDriver, URL);
    }

    public OpsLoginPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////
    /**
     * Log in with default ops credentials
     */

    public void login()
    {
        this.oUserNameInput.sendKeys("vahan+oc@heal.com");
        this.oPasswordInput.sendKeys("Heal4325");
        this.oLoginBtn.click();
        dashboardPage.oVisitsContainer.waitForElement();
    }

    /**
     * Logs in to heal with the provided username and password.
     *
     * @param sUsername (String) - Username to be used.
     * @param sPassword (String) - Password to be used.
     */
    public void login(String sUsername, String sPassword)
    {
        this.oUserNameInput.sendKeys(sUsername);
        this.oPasswordInput.sendKeys(sPassword);
        this.oLoginBtn.click();
        dashboardPage.oVisitsContainer.waitForElement();
    }

}
