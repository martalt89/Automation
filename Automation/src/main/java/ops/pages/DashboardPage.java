package ops.pages;

import framework.web.CommonWebElement;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends WebBase{
    public static final String URL = "https://ops.qa.heal.com/dashboard";

    ///////////////////
    // Page Elements //
    ///////////////////

    //city names
    public CommonWebElement oLosAngelesTitle = new CommonWebElement("oLosAngelesTitle", "//*[contains(@class, 'market-name') and text()='LOS ANGELES']",oWebDriver);
    public CommonWebElement oLongBeachTitle = new CommonWebElement("oLongBeachTitle", "//*[contains(@class, 'market-name') and text()='LONG BEACH']",oWebDriver);
    public CommonWebElement oOrangeCountyTitle = new CommonWebElement("oOrangeCountyTitle", "//*[contains(@class, 'market-name') and text()='ORANGE COUNTY']",oWebDriver);
    public CommonWebElement oSanDiegoTitle = new CommonWebElement("oSanDiegoTitle", "//*[contains(@class, 'market-name') and text()='SAN DIEGO']",oWebDriver);
    public CommonWebElement oSanFranciscoTitle = new CommonWebElement("oSanFranciscoTitle", "//*[contains(@class, 'market-name') and text()='SAN FRANCISCO']",oWebDriver);
    public CommonWebElement oSiliconValleyTitle = new CommonWebElement("oSiliconValleyTitle", "//*[contains(@class, 'market-name') and text()='SILICON VALLEY']",oWebDriver);
    public CommonWebElement oWashingtonDCTitle = new CommonWebElement("oWashingtonDCTitle", "//*[contains(@class, 'market-name') and text()='WASHINGTON D.C.']",oWebDriver);
    public CommonWebElement oLasVegasTitle = new CommonWebElement("oLasVegasTitle", "//*[contains(@class, 'market-name') and text()='LAS VEGAS']",oWebDriver);

    //city checkboxes
    public CommonWebElement oLACheckBx = new CommonWebElement("oLACheckBx", "xpath=//*[@type='checkbox' and @name='LA']",oWebDriver);
    public CommonWebElement oLBCheckBx = new CommonWebElement("oLBCheckBx", "xpath=//*[@type='checkbox' and @name='LB']",oWebDriver);
    public CommonWebElement oOCCheckBx = new CommonWebElement("oOCCheckBx", "xpath=//*[@type='checkbox' and @name='OC']",oWebDriver);
    public CommonWebElement oSDCheckBx = new CommonWebElement("oSDCheckBx", "xpath=//*[@type='checkbox' and @name='SD']",oWebDriver);
    public CommonWebElement oSFCheckBx = new CommonWebElement("oSFCheckBx", "xpath=//*[@type='checkbox' and @name='SF']",oWebDriver);
    public CommonWebElement oSVCheckBx = new CommonWebElement("oSVCheckBx", "xpath=//*[@type='checkbox' and @name='SV']",oWebDriver);
    public CommonWebElement oDCCheckBx = new CommonWebElement("oSDCCheckBx", "xpath=//*[@type='checkbox' and @name='DC']",oWebDriver);
    public CommonWebElement oLVCheckBx = new CommonWebElement("oLVCheckBx", "xpath=//*[@type='checkbox' and @name='LV']",oWebDriver);

    //table buttons
    public CommonWebElement oOperationsViewTitle = new CommonWebElement("oOperationsViewTitle", "xpath=//h1[text()='Operations View']",oWebDriver);
    public CommonWebElement oAllBtn = new CommonWebElement("oAllBtn", "xpath=//*[contains(@class,'btn-default') and text()='ALL']",oWebDriver);
    public CommonWebElement oActiveBtn = new CommonWebElement("oActiveBtn", "xpath=//*[contains(@class,'btn-default') and text()='Active']",oWebDriver);
    public CommonWebElement oScheduledBtn = new CommonWebElement("oScheduledBtn", "xpath=//*[contains(@class,'btn-default') and text()='Scheduled']",oWebDriver);
    public CommonWebElement oPreviousBtn = new CommonWebElement("oPreviousBtn", "xpath=//*[contains(@class,'btn-default') and text()='Previous']",oWebDriver);
    public CommonWebElement oTodayBtn = new CommonWebElement("oTodayBtn", "xpath=//*[contains(@class,'btn-default') and text()='Today']",oWebDriver);
    public CommonWebElement oTomorrowBtn = new CommonWebElement("oTomorrowBtn", "xpath=//*[contains(@class,'btn-default') and text()='Tomorrow']",oWebDriver);
    //table headers
    public CommonWebElement oStatusHeader = new CommonWebElement("oStatusHeader", "xpath=//th[@data-title='visitStatus']",oWebDriver);
    public CommonWebElement oVisitCodeHeader = new CommonWebElement("oVisitCodeHeader", "xpath=//th[@data-title='visitCode']",oWebDriver);
    public CommonWebElement oServiceHeader = new CommonWebElement("oServiceHeader", "xpath=//th[@data-title='visitServiceName']",oWebDriver);
    public CommonWebElement oPatientHeader = new CommonWebElement("oPatientHeader", "xpath=//th[@data-title='patientName']",oWebDriver);
    public CommonWebElement oAddressHeader = new CommonWebElement("oAddressHeader", "xpath=//th[@data-title='addressLong']",oWebDriver);
    public CommonWebElement oDoctorHeader = new CommonWebElement("oDoctorHeader", "xpath=//th[@data-title='doctorName']",oWebDriver);
    public CommonWebElement oAssistantHeader = new CommonWebElement("oAssistantHeader", "xpath=//th[@data-title='assistantName']",oWebDriver);
    public CommonWebElement oRequestedTimeSlotHeader = new CommonWebElement("oRequestedTimeSlotHeader", "xpath=//th[@data-title='requestedTimeSlotClientFormatted']",oWebDriver);
    public CommonWebElement oScheduledDateHeader = new CommonWebElement("oScheduledDateHeader", "xpath=//th[@data-title='scheduledTimeClientFormatted']",oWebDriver);
    public CommonWebElement oRescheduledHeader = new CommonWebElement("oRescheduledHeader", "xpath=//th[@data-title='reschedules']",oWebDriver);


    //////////////////
    // Constructors //
    //////////////////

    public DashboardPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public DashboardPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }
    public DashboardPage(){
        super();
    }

    /////////////
    // Methods //
    /////////////

    /**
     * Gets a city title by text
     * @param sText (String) City title
     */
    public CommonWebElement getCityTitle(String sText)
    {
        return new CommonWebElement("o"+sText+"Title", "xpath=//*[contains(@class, 'market-name') and text()='"+sText.toUpperCase() +"']",oWebDriver);
    }

    /**
     * Clicks a city checkbox
     * @param sCityInitials (String) City initials
     */
    public void clickCityCheckbox(String sCityInitials)
    {
        CommonWebElement oCityCheckbox = new CommonWebElement("oCityCheckbox", "xpath=//*[@type='checkbox' and @name='" + sCityInitials + "']",oWebDriver);
        oCityCheckbox.click();
    }
}
