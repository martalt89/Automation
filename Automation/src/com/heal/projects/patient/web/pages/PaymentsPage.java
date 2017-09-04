package com.heal.projects.patient.web.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/17/2017.
 */
public class PaymentsPage extends WebBase {
    public static final String URL = "https://patient" + baseUrl + "/payments";

    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oPaymentsLabel = new CommonWebElement( "oPaymentsLabel", "xpath=//*[text()='Payments']", oWebDriver );
    public CommonWebElement oPaymentDetailLabel = new CommonWebElement( "oPaymentDetailLabel", "xpath=//*[text()='Payment details']", oWebDriver );
    public CommonWebElement oCardExpDate = new CommonWebElement( "oCardExpDate", "className=credit-card-expiry", oWebDriver );
    public CommonWebElement oEditPaymentBtn = new CommonWebElement( "oEditPaymentBtn", "xpath=//*[text()='Edit payment']", oWebDriver );

    //////////////////
    // Constructors //
    //////////////////

    @Parameters({ "url" })
    public PaymentsPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public PaymentsPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

}