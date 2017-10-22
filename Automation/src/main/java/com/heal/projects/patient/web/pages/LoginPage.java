package com.heal.projects.patient.web.pages;

import com.heal.framework.test.RunTestSuite;
import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by vahanmelikyan on 7/3/17.
 */

public class LoginPage extends WebBase{

//    public static final String URL = "https://patient" + baseUrl + "/login";
    public static final String URL = "http://localhost:3000/login";
    RunTestSuite runTestSuite = new RunTestSuite();
    public HashMap<String, String> testDataMap = runTestSuite.getExcelParams();
    private String password = "Heal4325!";


    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oPageTitle = new CommonWebElement( "oPageTitle", "xpath=//*[@data-tid='txt_title'] |//*[contains(@class,'title')]", oWebDriver );
    public CommonWebElement oUserNameInput = new CommonWebElement("oUserNameInput", "xpath=//input[@name='username' or @data-tid='inp_username']",oWebDriver);
    public CommonWebElement oPasswordInput = new CommonWebElement("oPasswordInput", "xpath=//input[@name='password' or @data-tid='inp_password']", oWebDriver);
    public CommonWebElement oLoginBtn = new CommonWebElement("oLoginBtn", "xpath=//*[@type='submit' or @data-tid='btn_login']", oWebDriver);
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

    public void validateTitle(){
        TestBase testBase = new TestBase();
        String title = oPageTitle.getText();
        testBase.assertEquals("Validate page title", title, "Sign In");
    }

    /////////////
    // Methods //
    /////////////
    //Login with the default username and password
    public void login()
    {
        if (testDataMap == null) {
            this.oUserNameInput.sendKeys("vahan+qa@heal.com");
        } else {
            this.oUserNameInput.sendKeys("vahan+" + testDataMap.get("ENV").toString() + "@heal.com");
        }
        this.oPasswordInput.sendKeys(password);
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

    /**
     * Logs in to heal with the provided username and defautl password.
     *
     * @param sUsername (String) - Username to be used.
     */
    public void login(String sUsername){
        this.oUserNameInput.sendKeys(sUsername);
        this.oPasswordInput.sendKeys(password);
        this.oLoginBtn.click();
    }


}