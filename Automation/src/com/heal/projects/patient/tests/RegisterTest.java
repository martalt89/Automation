package com.heal.projects.patient.tests;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebValidate;
import com.heal.projects.patient.pages.HomePage;
import org.openqa.selenium.WebDriver;
import com.heal.projects.patient.pages.RegisterPage;
import org.testng.annotations.Test;
/**
 * Created by mihai.muresan on 7/10/2017.
 */
public class RegisterTest extends TestBase {

    @Test
    public void checkRegisterPageElements() throws Exception {
        WebDriver dr = getDriver();
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
        WebDriver dr = getDriver();
        RegisterPage registerPage = new RegisterPage(dr);
        CommonWebValidate validate = new CommonWebValidate(dr);
        registerPage.oCreateAcctBtn.click();
        validate.verifyVisible("Verify that field validation error message is displayed", registerPage.oRegisterAlert);
    }

    @Test
    public void registerAccount() throws Exception {
        WebDriver dr = getDriver();
        HomePage homePage = new HomePage(dr);
        RegisterPage registerPage = new RegisterPage(dr);
        registerPage.goTo();
        registerPage.waitForPageLoad();

        registerPage.oFirstNameInput.sendKeys(RegisterPage.sRegisterFirstName);
        registerPage.oLastNameNameInput.sendKeys(RegisterPage.sRegisterLastName);
        registerPage.oEmailInput.sendKeys(RegisterPage.sRegisterUserEmail);
        registerPage.oPasswordInput.sendKeys(RegisterPage.sRegisterPassword);
        registerPage.oConfirmPasswordInput.sendKeys(RegisterPage.sRegisterConfirmPassword);
        registerPage.oPhoneNmbInput.sendKeys(RegisterPage.sRegisterPhoneNo);
        registerPage.oZipcodeInput.sendKeys(RegisterPage.sRegisterZipCode);
        registerPage.oCreateAcctBtn.click();
        assertEquals("Verifying page url ", homePage.getCurrentUrl(), RegisterPage.URL);
        verifyVisible("Check the profile avatar icon.", homePage.oAccountOwnerAvatar);
        assertEquals("Verifying Visits page title ", homePage.oPageTitle.getText(), "Your activity");
    }
}