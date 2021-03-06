package com.heal.projects.patient.web.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/19/2017.
 */
public class VisitDetailsPage extends WebBase {
    public static final String URL = "https://patient" + baseUrl + "/book-visit/visit-details";
    public static final String SICK_SERVICE = "Sick or Injured";
    public static final String ANNUAL_SERVICE = "Annual Physical";
    public static final String OTHER_SERVICE = "Other";

    ///////////////////
    // Shared Pages  //
    ///////////////////
    Menu menu = new Menu(oWebDriver);


    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oBookVisitTitle = new CommonWebElement( "oBookVisitTitle", "xpath=//*[text()='Book Visit']", oWebDriver );
    public CommonWebElement oVisitDetailsTitle = new CommonWebElement( "oVisitDetailsTitle", "xpath=//*[text()='Visit Details']", oWebDriver );
    public CommonWebElement oSelectServiceText = new CommonWebElement( "oSelectServiceText", "xpath=//*[contains(text(),'Select a Service')]", oWebDriver );
    public CommonWebElement oSickOrInjuredText = new CommonWebElement( "oSickOrInjuredText", "xpath=//*[@role='button' and contains(.,'Sick or Injured')]", oWebDriver );
    //public CommonWebElement oSickOrInjuredText = new CommonWebElement( "oSickOrInjuredText", "xpath=(//*[@class='ng-isolate-scope'])[2]", oWebDriver );
    public CommonWebElement oAnnualPhysicalText = new CommonWebElement( "oAnnualPhysicalText", "xpath=//*[text()='Annual Physical']", oWebDriver );
    public CommonWebElement oOtherText = new CommonWebElement( "oOtherText", "xpath=//*[text()='Other']", oWebDriver );
    public CommonWebElement oAditionalInfoText = new CommonWebElement( "oAditionalInfoText", "xpath=//*[contains(text(),'Additional Info')]", oWebDriver );
    public CommonWebElement oSelectDateText = new CommonWebElement( "oSelectDateText", "xpath=//*[text()='Select Date']", oWebDriver );
    public CommonWebElement oSelectTimeText = new CommonWebElement( "oSelectDateText", "xpath=//*[text()='Select a Time']", oWebDriver );
//    public CommonWebElement oSymptomsInput = new CommonWebElement( "oSymptomsInput", "xpath=//textarea[@name='symptoms']", oWebDriver );
    public CommonWebElement oSymptomsInput = new CommonWebElement( "oSymptomsInput", "xpath=(//*[@name='symptoms'])[1]", oWebDriver );
    public CommonWebElement oSymptomsInput2 = new CommonWebElement( "oSymptomsInput2", "xpath=(//*[@name='symptoms'])[2]", oWebDriver );
    public CommonWebElement oSelectDateInput = new CommonWebElement( "oSelectDateInput", "className=md-datepicker-input", oWebDriver );
    //public CommonWebElement oFirstAvailableTimeSlot = new CommonWebElement( "oFirstAvailableTimeSlot", "xpath=(//button[contains(@class,'time-slot')]/span)[1]", oWebDriver );
    public CommonWebElement oFirstAvailableTimeSlot = new CommonWebElement( "oFirstAvailableTimeSlot", "xpath=(//*[@class='layout-wrap ng-scope layout-align-center-start layout-row']/button/span)[1]", oWebDriver );






    public CommonWebElement oTimeRangeButton1 = new CommonWebElement( "oTimeRangeButton1", "xpath=//*[text()='08:00 am - 10:00 am']", oWebDriver );
    public CommonWebElement oTimeRangeButton2 = new CommonWebElement( "oTimeRangeButton2", "xpath=//*[text()='10:00 am - 12:00 pm']", oWebDriver );
    public CommonWebElement oTimeRangeButton3 = new CommonWebElement( "oTimeRangeButton3", "xpath=//*[text()='12:00 pm - 02:00 pm']", oWebDriver );
    public CommonWebElement oContinueBtn = new CommonWebElement( "oContinueBtn", "xpath=//*[text()='Continue']", oWebDriver );

    //////////////////
    // Constructors //
    //////////////////

    @Parameters({ "url" })
    public VisitDetailsPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public VisitDetailsPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    public VisitDetailsPage() {

    }

    //////////////////
    //// Methods /////
    //////////////////

    public void setSymptoms(String sSymptoms){
        if (oSymptomsInput.isDisplayed(1)){
            oSymptomsInput.sendKeys(sSymptoms);
        }else {
            oSymptomsInput2.sendKeys(sSymptoms);
        }
    }

    public void selectFirstAvailableTimeSlot(){
        oFirstAvailableTimeSlot.jsClick();
        //oFirstAvailableTimeSlot.click();

    }

    /**
     * Select a service for visit
     * @param sService (String) Type of service
     * @throws InvalidArgumentException if other argument except the ones in the switch
     */
    public void selectServiceForVisit(String sService) throws InvalidArgumentException{
        try {
            switch (sService) {
                case SICK_SERVICE:
                    Thread.sleep(400);
                    this.oSickOrInjuredText.clickAndWait(menu.oLoadingBar, false);
                case ANNUAL_SERVICE:
                    Thread.sleep(400);
                    this.oAnnualPhysicalText.clickAndWait(menu.oLoadingBar, false);
                case OTHER_SERVICE:
                    Thread.sleep(400);
                    this.oOtherText.clickAndWait(menu.oLoadingBar, false);
                default:
                    throw new InvalidArgumentException("Invalid argument");
            }
        } catch (Exception ignored){

        }
    }

}