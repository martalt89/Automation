package patient.pages;

import framework.web.CommonWebElement;
import framework.web.WebBase;
import foundation.SysTools;
import org.openqa.selenium.WebDriver;
import framework.test.TestData;
import org.testng.annotations.Test;

/**
 * Created by mihai.muresan on 7/10/2017.
 */
public class RegisterPage extends WebBase {

    public static SysTools sysTools = new SysTools();

    public static final String URL = "https://patient.qa.heal.com/register";
    public static final String sRegisterFirstName = "QA_fn";
    public static final String sRegisterLastName = "QA_ln";
    public static final String sRegisterUserEmail = "qa_auto_test_" + sysTools.getTimestamp("yyyy_MM_dd_HH-mm") +"@heal.com";
    public static final String sRegisterPassword = "Heal4325";
    public static final String sRegisterConfirmPassword = "Heal4325";
    public static final String sRegisterPhoneNo = "1201-555-5555";
    public static final String sRegisterZipCode = "4325";

    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oFirstNameInput = new CommonWebElement( "oFirstNameInput", "name=firstname", oWebDriver );
    public CommonWebElement oLastNameNameInput = new CommonWebElement( "oLastNameInput", "name=lastname", oWebDriver );
    public CommonWebElement oEmailInput = new CommonWebElement( "oFirstNameLabel", "name=username", oWebDriver );
    public CommonWebElement oPasswordInput = new CommonWebElement( "oPasswordInput", "name=password", oWebDriver );
    public CommonWebElement oConfirmPasswordInput = new CommonWebElement( "oConfirmPasswordInput", "name=password2", oWebDriver );
    public CommonWebElement oPhoneNmbFlag = new CommonWebElement( "oPhoneNmbFlag", "className=selected-flag", oWebDriver );
    public CommonWebElement oPhoneNmbInput = new CommonWebElement( "oPhoneNmbInput", "name=phonenumber", oWebDriver );
    public CommonWebElement oZipcodeInput = new CommonWebElement( "oZipcodeInput", "name=zipcode", oWebDriver );
    public CommonWebElement oRememberMeLabel = new CommonWebElement( "oRememberMeLabel", "xpath=//*[text()='Remember Me']", oWebDriver );
    public CommonWebElement oCreateAcctBtn = new CommonWebElement( "oCreateAcctBtn", "xpath=//button[@type='submit']", oWebDriver );
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

    /////////////
    // Methods //
    /////////////

    //below is a method to demonstrate how input fields are populated with data extracted from a sheet document
    TestData testData = new TestData();
    public void registerValidData() throws InterruptedException {
        this.oFirstNameInput.sendKeys(testData.getFirstName());
        this.oLastNameNameInput.sendKeys(testData.getLastName());
        this.oEmailInput.sendKeys(testData.getEmail());
        this.oPasswordInput.sendKeys(testData.getPassword());
        this.oConfirmPasswordInput.sendKeys(testData.getPassword());
        Thread.sleep(3000); //just to verify that the correct data is written in input fields


    }
}
