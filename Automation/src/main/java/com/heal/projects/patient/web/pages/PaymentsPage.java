package com.heal.projects.patient.web.pages;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/17/2017.
 */

//todo need to combine AddCardPage and PaymentsPage into one since the new patient webapp has only one page for both
public class PaymentsPage extends WebBase {
//    public static final String URL = "https://patient" + baseUrl + "/payments";
    public static final String URL = "http://localhost:3000/payments";

    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oPageTitle = new CommonWebElement( "oPageTitle", "xpath=//*[@data-tid='txt_title']", oWebDriver );
    public CommonWebElement oPaymentsLabel = new CommonWebElement( "oPaymentsLabel", "xpath=//*[text()='Payments']", oWebDriver );
    public CommonWebElement oPaymentDetailLabel = new CommonWebElement( "oPaymentDetailLabel", "xpath=//*[text()='Payment details']", oWebDriver );
    public CommonWebElement oCardExpDate = new CommonWebElement( "oCardExpDate", "className=credit-card-expiry", oWebDriver );
    public CommonWebElement oEditPaymentBtn = new CommonWebElement( "oEditPaymentBtn", "xpath=//*[@data-tid='btn_creditCard'] | //*[text()='Edit payment']", oWebDriver );

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

    ////////////////
    // Validation //
    ////////////////
    public void validateTitle(){
        TestBase testBase = new TestBase();
        String title = oPageTitle.getText();
        testBase.assertEquals("Validate page title", title, "Manage payment methods");
    }

}