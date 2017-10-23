package com.heal.projects.patient.web.pages;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import com.heal.framework.foundation.SysTools;
import org.openqa.selenium.WebDriver;

/**
 * Created by mihai.muresan on 7/10/2017.
 */
public class RegisterPage extends WebBase {


//    public static final String URL = "https://patient" + baseUrl + "/register";
    public static final String URL = "http://localhost:3000/register";

    public static final String sRegisterFirstName = "QA_fn";
    public static final String sRegisterLastName = "QA_ln";
    public static final String sRegisterUserEmail = "qa_auto_test_" + SysTools.getTimestamp("yyyy_MM_dd_HH-mm") +"@heal.com";
    public static final String sRegisterPassword = "Heal4325";
    public static final String sRegisterConfirmPassword = "Heal4325";
    public static final String sRegisterPhoneNo = "1201-555-5555";
    public static final String sRegisterZipCode = "4325";

    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oPageTitle = new CommonWebElement("oPageTitle", "xpath=//*[@data-tid='txt_title'] | //span[contains(.,'Book a doctor in 4 easy steps:')]",oWebDriver);
    public CommonWebElement oFirstNameInput = new CommonWebElement( "oFirstNameInput", "xpath=//*[@data-tid='inp_firstName'] | //*[@name='firstname']", oWebDriver );
    public CommonWebElement oLastNameNameInput = new CommonWebElement( "oLastNameInput", "xpath=//*[@data-tid='inp_lastName'] | //*[@name='lastname']", oWebDriver );
    public CommonWebElement oEmailInput = new CommonWebElement( "oFirstNameLabel", "xpath=//*[@data-tid='inp_email'] | //*[@name='username']", oWebDriver );
    public CommonWebElement oPasswordInput = new CommonWebElement( "oPasswordInput", "xpath=//*[@data-tid='inp_password'] | //*[@name='password']", oWebDriver );
    public CommonWebElement oConfirmPasswordInput = new CommonWebElement( "oConfirmPasswordInput", "xpath=//*[@data-tid='inp_password2'] | //*[@name='password2']", oWebDriver );
    public CommonWebElement oPhoneNmbFlag = new CommonWebElement( "oPhoneNmbFlag", "xpath=//*[@type='tel'] | //*[@class='selected-flag']", oWebDriver );
    public CommonWebElement oPhoneNmbInput = new CommonWebElement( "oPhoneNmbInput", "xpath=//*[@type='tel'] | //*[@name='phonenumber']", oWebDriver );
    public CommonWebElement oZipcodeInput = new CommonWebElement( "oZipcodeInput", "name=zipcode", oWebDriver );
    public CommonWebElement oRememberMeLabel = new CommonWebElement( "oRememberMeLabel", "xpath=//*[text()='Remember Me']", oWebDriver );
    public CommonWebElement oCreateAcctBtn = new CommonWebElement( "oCreateAcctBtn", "xpath=//*[@data-tid='btn_createAccount'] | //button[@type='submit']", oWebDriver );
    public CommonWebElement oAlreadyHaveAccLnk = new CommonWebElement( "oAlreadyHaveAccLnk", "xpath=//*[@ui-sref='authentication.login’]", oWebDriver );
    //error messages
    public CommonWebElement oRegisterAlert = new CommonWebElement( "oRegisterAlert", "className=alert-warning", oWebDriver );
    public CommonWebElement oRegisterFirstnameError = new CommonWebElement( "oRegisterFirstnameError", "xpath=//*[@ng-messages='registerForm.firstname.$error']", oWebDriver );
    public CommonWebElement oRegisterLastnameError = new CommonWebElement( "oRegisterLastnameError", "xpath=//*[@ng-messages='registerForm.lastname.$error']", oWebDriver );
    public CommonWebElement oRegisterEmailError = new CommonWebElement( "oRegisterEmailError", "xpath=//*[@ng-messages='registerForm.username.$error']", oWebDriver );
    public CommonWebElement oRegisterPasswordError = new CommonWebElement( "oRegisterPasswordError", "xpath=//*[@ng-messages='registerForm.password.$error']", oWebDriver );
    public CommonWebElement oRegisterPhoneNumberError = new CommonWebElement( "oRegisterPhoneNumberError", "xpath=//*[@ng-messages='registerForm.phonenumber.$error']", oWebDriver );
    public CommonWebElement oRegisterZipcodeError = new CommonWebElement( "oRegisterZipcodeError", "xpath=//*[@ng-messages='registerForm.zipcode.$error']", oWebDriver );

    //////////////////
    // Constructors //
    //////////////////
    public RegisterPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }

    ////////////////
    // Validation //
    ////////////////
    public void validateTitle(){
        TestBase testBase = new TestBase();
        String title = oPageTitle.getText();
        testBase.assertEquals("Validate page title", title, "Book a doctor in 4 easy steps:");
    }


    /////////////
    // Methods //
    /////////////


}
