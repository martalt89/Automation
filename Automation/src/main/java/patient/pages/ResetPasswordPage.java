package patient.pages;

import framework.web.CommonWebElement;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/20/2017.
 */
public class ResetPasswordPage extends WebBase {
    public static final String URL = "https://resetpassword.qa.heal.com/";

    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oRequestDoctorLnk = new CommonWebElement( "oRequestDoctorLnk", "xpath=//a[text()='Request doctor']", oWebDriver );
    //request doctor modal
    public CommonWebElement oMedEmergencyText = new CommonWebElement( "oMedEmergencyText", "xpath=//*[contains(text(),'medical emergency?')]", oWebDriver );
    public CommonWebElement oYesBtn = new CommonWebElement( "oYesBtn", "xpath=//button[text()='Yes']", oWebDriver );
    public CommonWebElement oNoBtn = new CommonWebElement( "oNoBtn", "xpath=//button[text()='No']", oWebDriver );
    //modal after clicking Yes from Request doctor modal
    public CommonWebElement oNotSuitableTitle = new CommonWebElement( "oNotSuitableTitle", "xpath=//*[contains(text(),'Heal is not suitable')]", oWebDriver );
    public CommonWebElement oDial911Text = new CommonWebElement( "oDial911Text", "xpath=//button[text()='Please dial 911']", oWebDriver );
    public CommonWebElement oGotItBtn = new CommonWebElement( "oGotItBtn", "xpath=//button[text()='Got it']", oWebDriver );
    //modal after clicking No from Request doctor modal
    public CommonWebElement oRequestStepsFlow = new CommonWebElement( "oRequestStepsFlow", "xpath=//*[@id='request-steps", oWebDriver );
    public CommonWebElement oNameInput = new CommonWebElement( "oNameInput", "xpath=//*[@id='name", oWebDriver );
    public CommonWebElement oMobileInput = new CommonWebElement( "oMobileInput", "xpath=//*[@id='mobile", oWebDriver );
    public CommonWebElement oReqDoctorBtn = new CommonWebElement( "oReqDoctorBtn", "xpath=//button[text()='Request doctor']", oWebDriver );
    //forgot password section
    public CommonWebElement oForgotPasswordTitle = new CommonWebElement( "oForgotPasswordTitle", "xpath=//*[text()='Forgot Password']", oWebDriver );
    public CommonWebElement oSendLinkText = new CommonWebElement( "oSendLinkText", "xpath=//*[contains(text(),'We'll send you')]", oWebDriver );
    public CommonWebElement oEmailInput = new CommonWebElement( "oEmailInput", "name=email", oWebDriver );
    public CommonWebElement oSubmitBtn = new CommonWebElement( "oSubmitBtn", "xpath=//*[text()='Forgot Password']", oWebDriver );
    public CommonWebElement oInvalidEmailError = new CommonWebElement( "oInvalidEmailError", "xpath=//*[contains(text(),'enter a valid email')]", oWebDriver );

    //////////////////
    // Constructors //
    //////////////////

    @Parameters({ "url" })
    public ResetPasswordPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public ResetPasswordPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

}