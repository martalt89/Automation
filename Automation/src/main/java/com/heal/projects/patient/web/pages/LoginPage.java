package com.heal.projects.patient.web.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;

import org.openqa.selenium.WebDriver;


/**
 * Created by vahanmelikyan on 7/3/17.
 */

public class LoginPage extends WebBase{
    public static final String URL = "https://patient" + baseUrl + "/login";
    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oUserNameInput = new CommonWebElement("oUserNameInput", "name=username",oWebDriver);
    public CommonWebElement oPasswordInput = new CommonWebElement("oPasswordInput", "name=password", oWebDriver);
    public CommonWebElement oLoginBtn = new CommonWebElement("oLoginBtn", "xpath=//button[@type='submit']", oWebDriver);
    public CommonWebElement oRememberMe = new CommonWebElement("oRememberMe", "className=md-icon", oWebDriver);
    public CommonWebElement oForgotYourPasswordLnk = new CommonWebElement("oForgotYourPasswordLnk", "linkText=Forgot Password", oWebDriver);
    public CommonWebElement oWarningMsg = new CommonWebElement("oWarningMsg","className=error-messages",oWebDriver);
    public CommonWebElement oRegisterBtn = new CommonWebElement("oRegisterNtm", "xpath=//*[text()='Register']", oWebDriver);

    //////////////////
    // Constructors //
    //////////////////
   // @Parameters({ "url" })
    public LoginPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public LoginPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }
    public LoginPage(){
        super();
    }

    /////////////
    // Methods //
    /////////////

    //Login with the default username and password
    public void login()
    {
        this.oUserNameInput.sendKeys("vahan+qa@heal.com");
        this.oPasswordInput.sendKeys("Heal4325!");
        this.oLoginBtn.click();
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
    }

}