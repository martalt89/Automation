package com.heal.projects.patient.mobile.pages;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.WebBase;
import com.heal.framework.web.CommonWebElement;
import org.openqa.selenium.WebDriver;

public class M_DashboardPage extends WebBase {


    public CommonWebElement oAllowPermissionToAccessLocationOnConnectedDevice=new CommonWebElement("oAllowPermissionToAccessLocationOnConnectedDevice","xpath=//android.widget.Button[@text='Allow']",oWebDriver);
    public CommonWebElement oNoMedicalEmergencyButton= new CommonWebElement("oNoMedicalEmergencyButton","xpath=//android.widget.Button[@text='No']",oWebDriver);
    public CommonWebElement oLocationTextField=new CommonWebElement("oLocationTextField","xpath=//android.widget.EditText[@index='0']",oWebDriver);
    public CommonWebElement oUnitOrAptField=new CommonWebElement("oUnitOrAptField","xpath=//android.widget.EditText[@index='1']",oWebDriver);
    public CommonWebElement oSelectHomeAddress=new CommonWebElement("oSelectHomeAddress","xpath=(//*[@resource-id=\"com.getheal.patient.debug:id/place_icon\"])[1]",oWebDriver);
    public CommonWebElement oSelectPatientProfile=new CommonWebElement("oSelectPatientProfile","xpath=//android.widget.TextView[@text='You']",oWebDriver);
    public CommonWebElement oContinueButton= new CommonWebElement("oContinueButton","xpath=//android.widget.Button[@text='Continue']",oWebDriver);




    //////////////////
    // Constructors //
    //////////////////
    public M_DashboardPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }
}
