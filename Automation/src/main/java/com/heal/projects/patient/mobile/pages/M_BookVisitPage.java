package com.heal.projects.patient.mobile.pages;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.WebBase;
import com.heal.framework.web.CommonWebElement;
import org.openqa.selenium.WebDriver;

public class M_BookVisitPage extends WebBase {



    public CommonWebElement oNoMedicalEmergencyButton= new CommonWebElement("oNoMedicalEmergencyButton","xpath=//android.widget.Button[@text='No']",oWebDriver);
    public CommonWebElement oLocationTextField=new CommonWebElement("oLocationTextField","xpath=//android.widget.EditText[@index='0']",oWebDriver);
    public CommonWebElement oUnitOrAptField=new CommonWebElement("oUnitOrAptField","xpath=//android.widget.EditText[@index='1']",oWebDriver);
    public CommonWebElement oSelectHomeAddress=new CommonWebElement("oSelectHomeAddress","xpath=(//*[@resource-id=\"com.getheal.patient.debug:id/place_icon\"])[1]",oWebDriver);
   // public CommonWebElement oMenuButton=new CommonWebElement("oMenuButton","xpath=//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]",oWebDriver);
    public CommonWebElement oContinueButton= new CommonWebElement("oContinueButton","xpath=//android.widget.Button[@text='Continue']",oWebDriver);




    //////////////////
    // Constructors //
    //////////////////
    public M_BookVisitPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }


    public void oSelectPatientProfile(String sProfileType)
    {
        CommonWebElement oMainProfile=new CommonWebElement("oMainProfile","xpath=//*[contains(@text,'"+sProfileType+"')]",oWebDriver);
        oMainProfile.click();
    }

    public void fillAddressDetails()
    {

        this.oLocationTextField.clickAndWait(this.oSelectHomeAddress,true);
        this.oSelectHomeAddress.clickAndWait(this.oUnitOrAptField,true);
        this.oUnitOrAptField.sendKeys("#711");

    }
}
