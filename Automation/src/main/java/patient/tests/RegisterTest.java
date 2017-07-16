package patient.tests;

import framework.web.CommonWebValidate;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import patient.pages.HomePage;
import patient.pages.RegisterPage;
import utilities.DriverManager;
import org.testng.annotations.Test;
/**
 * Created by mihai.muresan on 7/10/2017.
 */
public class RegisterTest extends WebBase {

    @Test
    public void checkRegisterPageElements() throws Exception {
        WebDriver dr = DriverManager.getDriver();
        RegisterPage registerPage = new RegisterPage(dr);
        CommonWebValidate validate = new CommonWebValidate(dr);
        validate.verifyVisible("Verify register First name input is displayed", registerPage.oFirstNameInput);
        validate.verifyVisible("Verify register Last name input is displayed is displayed", registerPage.oLastNameNameInput);
        validate.verifyVisible("Verify register Email name input is displayed", registerPage.oEmailInput);
        validate.verifyVisible("Verify register Password input is displayed", registerPage.oPasswordInput);
        validate.verifyVisible("Verify register Confirm Password input is displayed", registerPage.oConfirmPasswordInput);
        validate.verifyVisible("Verify register Phone number flag is displayed", registerPage.oPhoneNmbFlag);
        validate.verifyVisible("Verify register Phone number input is displayed", registerPage.oPhoneNmbInput);
        validate.verifyVisible("Verify register Zipcode input is displayed", registerPage.oZipcodeInput);
        validate.verifyVisible("Verify register Create Account button is displayed", registerPage.oCreateAcctBtn);
        validate.verifyVisible("Verify register Already have account link is displayed",registerPage.oAlreadyHaveAccLnk);
    }

    @Test
    public void registerInvalidInput() throws Exception {
        WebDriver dr = DriverManager.getDriver();
        RegisterPage registerPage = new RegisterPage(dr);
        CommonWebValidate validate = new CommonWebValidate(dr);
        registerPage.oCreateAcctBtn.click();
        validate.verifyVisible("Verify that field validation error message is displayed", registerPage.oRegisterAlert);
    }

    @Test
    public void registerAccount() throws Exception {
        WebDriver dr = DriverManager.getDriver();
        RegisterPage registerPage = new RegisterPage(dr);
        HomePage homePage = new HomePage(dr);
        registerPage.oFirstNameInput.sendKeys(RegisterPage.sRegisterFirstName);
        registerPage.oLastNameNameInput.sendKeys(RegisterPage.sRegisterLastName);
        registerPage.oEmailInput.sendKeys(RegisterPage.sRegisterUserEmail);
        registerPage.oPasswordInput.sendKeys(RegisterPage.sRegisterPassword);
        registerPage.oConfirmPasswordInput.sendKeys(RegisterPage.sRegisterConfirmPassword);
        registerPage.oPhoneNmbInput.sendKeys(RegisterPage.sRegisterPhoneNo);
        registerPage.oZipcodeInput.sendKeys(RegisterPage.sRegisterZipCode);
        registerPage.oCreateAcctBtn.click();
        homePage.validateTitle();
    }

}