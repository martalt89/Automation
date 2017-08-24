package com.heal.projects.patient.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/17/2017.
 */
public class AddCardPage extends WebBase {
    public static final String URL = "https://patient" + baseUrl + "/payments/add-card";

    ///////////////////
    // Page Elements //
    ///////////////////


    public CommonWebElement oPaymentsLabel = new CommonWebElement( "oPaymentsLabel", "xpath=//*[text()='Payments']", oWebDriver );
    public CommonWebElement oAddPaymentMethod = new CommonWebElement( "oAddPaymentMethod", "xpath=//*[text()='Add Payment Method']", oWebDriver );
    public CommonWebElement oCardNumberInput = new CommonWebElement( "oCardNumberInput", "name=number", oWebDriver );
    public CommonWebElement oCardExpirationInput = new CommonWebElement( "oCardNumberImput", "name=expiry", oWebDriver );
    public CommonWebElement oCVCInput = new CommonWebElement( "oCVCInput", "name=cvc", oWebDriver );
    public CommonWebElement oApplyCardBtn = new CommonWebElement("oApplyCardBtn", "xpath=//button[@type='submit']", oWebDriver);
    //Error messages
    public CommonWebElement oEnterCardNo = new CommonWebElement( "oEnterCardNo", "xpath=//*[@ng-messages='cardForm.number.$error']", oWebDriver );
    public CommonWebElement oInvalidCardNo = new CommonWebElement( "oInvalidCardNo", "xpath=//*[@ng-messages='cardForm.number']", oWebDriver );
    public CommonWebElement oEnterExpDate = new CommonWebElement( "oEnterExpDate", "xpath=//*[@ng-messages='cardForm.expiry.$error']", oWebDriver );
    public CommonWebElement oInvalidExpDate = new CommonWebElement( "oInvalidExpDate", "xpath=//*[@ng-messages='cardForm.expiry']", oWebDriver );
    public CommonWebElement oEnterCVC = new CommonWebElement( "oEnterSecurityCode", "xpath=//*[@ng-messages='cardForm.cvc.$error']", oWebDriver );
    public CommonWebElement oInvalidCVC = new CommonWebElement( "oEnterCardNo", "xpath=//*[@ng-messages='cardForm.cvc']", oWebDriver );
    //////////////////
    // Constructors //
    //////////////////

    @Parameters({ "url" })
    public AddCardPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public AddCardPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////

    /**
     *
     * @param sCardNumber (String) - Card number to be used
     * @param sCardExpirationDate (String) - Card expiration date
     * @param sCardCVC (String) - Card CVC
     */
    public void typeCardDetailsAndSubmit(String sCardNumber, String sCardExpirationDate, String sCardCVC)
    {
        this.oCardNumberInput.sendKeys(sCardNumber);
        this.oCardExpirationInput.sendKeys(sCardExpirationDate);
        this.oCVCInput.sendKeys(sCardCVC);
        this.oApplyCardBtn.click();
    }

}