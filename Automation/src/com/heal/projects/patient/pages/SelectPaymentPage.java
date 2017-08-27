package com.heal.projects.patient.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/19/2017.
 */
public class SelectPaymentPage extends WebBase {
    public static final String URL = "https://patient" + baseUrl + "/book-visit/visit-summary/select-payment";

    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oBookVisitTitle = new CommonWebElement( "oBookVisitTitle", "xpath=//*[text()='Book Visit']", oWebDriver );
    public CommonWebElement oPaymentDetailsTitle = new CommonWebElement( "oPaymentDetailsTitle", "xpath=//*[text()='Payment Details']", oWebDriver );
    public CommonWebElement oCreditCardDetailsTxt = new CommonWebElement( "oCreditCardDetailsTxt", "xpath=//*[text()='Credit Card Details']", oWebDriver );
    public CommonWebElement oNeedDetailsTxt = new CommonWebElement( "oNeedDetailsTxt", "xpath=//*[contains(text(),'*We need your credit')]", oWebDriver );
    public CommonWebElement oSelectCard = new CommonWebElement( "oSelectCard", "xpath=//*[@ng-click='vm.selectCard()']", oWebDriver );
    public CommonWebElement oEditPaymentBtn = new CommonWebElement( "oEditPaymentBtn", "xpath=//*[text()='Edit payment']", oWebDriver );
    public CommonWebElement oInsuranceInfoTxt = new CommonWebElement( "oInsuranceInfoTxt", "xpath=//*[text()='Edit Payment']", oWebDriver );
    public CommonWebElement oUseInsuranceTxt = new CommonWebElement( "oUseInsuranceTxt", "xpath=//*[contains(text(),'use insurance?')]", oWebDriver );
    public CommonWebElement oAddInsuranceBtn = new CommonWebElement( "oAddInsuranceBtn", "xpath=//*[text()='+ Add Insurance']", oWebDriver );
    public CommonWebElement oPromoCodeLink = new CommonWebElement( "oPromoCodeLink", "xpath=//*[text()='Have a promo code?']", oWebDriver );
    public CommonWebElement oPromoCodeInput = new CommonWebElement( "oPromoCodeInput", "xpath=//*[@ng-model='vm.service.pricing.promoCode']", oWebDriver );
    public CommonWebElement oTotalText = new CommonWebElement( "oTotalText", "xpath=//*[text()='Total']", oWebDriver );
    public CommonWebElement oCompleteBtn = new CommonWebElement( "oCompleteBtn", "xpath=//*[text()='Complete Booking'] | //*[text()='Request doctor']", oWebDriver );
    public CommonWebElement oPriceInfoText = new CommonWebElement( "oPriceInfoText", "xpath=//*[contains(@class,'float-right md-primary ng-binding')]", oWebDriver );
    public CommonWebElement oApplyCardBtn = new CommonWebElement("oApplyCardBtn", "xpath=//button[text()='Apply Card']", oWebDriver);
    public CommonWebElement oCompleteBookingBtn = new CommonWebElement("oCompleteBookingBtn", "xpath=//*[text()='Complete Booking']", oWebDriver);
    public CommonWebElement oCheckCircleEnabled = new CommonWebElement("oCheckCircleEnabled", "xpath=//*[@md-icon='ic_check_circle_24px' and @class='md-primary']"); // in progress, need to find out how to take the icon
    // when is disabled and also when is enabled

    //////////////////
    // Constructors //
    //////////////////

    @Parameters({ "url" })
    public SelectPaymentPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public SelectPaymentPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }
    public SelectPaymentPage(){

    }

}