package com.heal.projects.patient.mobile.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;

public class M_VisitDetailspage extends WebBase{
    public CommonWebElement oSelectSickOrInjuredService=new CommonWebElement("oSelectSickOrInjuredService","xpath=//android.widget.TextView[@text='Sick or Injured']",oWebDriver);
    public CommonWebElement oSelectDateAndTime=new CommonWebElement("oSelectDateAndTime","xpath=//android.widget.TextView[@text='Date and time']",oWebDriver);
    public CommonWebElement oSelectTimeForVisit=new CommonWebElement("oSelectTimeForVisit","xpath=//android.widget.TextView[@instance='1']",oWebDriver);
    public CommonWebElement oSelectDateDoneButton= new CommonWebElement("oSelectDateDoneButton","xpath=//android.widget.TextView[@text='DONE']",oWebDriver);
    public CommonWebElement oSymptomsField=new CommonWebElement("oSymptomsField","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/symptoms\"]",oWebDriver);
    public CommonWebElement oContiueBtn=new CommonWebElement("oContinueBtn","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/btn_continue\"]",oWebDriver);


    public M_VisitDetailspage(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }
}
