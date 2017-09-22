package com.heal.projects.patient.mobile.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;

public class M_PaymentDetailsPage extends WebBase {
    public CommonWebElement oWhosPlan=new CommonWebElement("oWhichPlan","xpath=(//*[contains(@resource-id,\"pricing_plan_view\")]//android.widget.TextView)[1]",oWebDriver);
    public CommonWebElement  oWhichPlan=new CommonWebElement("oWhichPlan","xpath=(//*[contains(@resource-id,\"pricing_plan_view\")]//android.widget.TextView)[2]",oWebDriver);
    public CommonWebElement  oPlanDeductibleAmount=new CommonWebElement("oPlanDeductibleAmount","xpath=(//*[contains(@resource-id,\"pricing_plan_view\")]//android.widget.TextView)[4]",oWebDriver);
    public CommonWebElement  oDeductibleRemaining=new CommonWebElement("oDeductibleRemaining","xpath=(//*[contains(@resource-id,\"pricing_plan_view\")]//android.widget.TextView)[6]",oWebDriver);
    public CommonWebElement oFlatFee=new CommonWebElement("oFlatFee","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/visit_price\"]",oWebDriver);
    public CommonWebElement oRequestDoctorButton=new CommonWebElement("oRequestDoctorButton","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/btn_request_doctor\"]",oWebDriver);
    public CommonWebElement oPromoCodeLink=new CommonWebElement("oPromoCodeLink","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/promo_code_link\"]",oWebDriver);
    public CommonWebElement oPromoCodeInputTab=new CommonWebElement("oPromoCodeInputTab","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/promo_code_dialog_input\"]",oWebDriver);
    public CommonWebElement oPromoCodeApplyButton=new CommonWebElement("oPromoCodeApplyButton","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/promo_apply\"]",oWebDriver);
    public CommonWebElement oFlatFeePromo=new CommonWebElement("oFlatFeePromo","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/visit_price_promo\"]",oWebDriver);
    public CommonWebElement oTapToAddInsuranceInput=new CommonWebElement("oTapToAddInsuranceInput","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/add_insurance\"]",oWebDriver);
    //////////////////
    // Constructors //
    //////////////////
    public M_PaymentDetailsPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }
}
