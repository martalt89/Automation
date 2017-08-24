package ops.pages;

import framework.exception.CommonException;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;

public class OpsVisitsPage extends WebBase {

    public static final String URL = "https://"+ baseUrl +"/visits";

    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oPageTitle = new CommonWebElement("oPageTitle", "xpath=//h1[text()='Visits']", oWebDriver);
    public CommonWebElement oAllVisitsTitle = new CommonWebElement("oAllVisitsTitle", "xpath=//*[contains(@class,'market-name___tuNGU')]", oWebDriver);

    // buttons
    public CommonWebElement oAddVisitBtn = new CommonWebElement("oAddVisitBtn", "xpath=//*[contains(@class,'btn btn-default add-visit___pK_7n')]", oWebDriver);
    public CommonWebElement oSettingsBtn = new CommonWebElement("oSettingsBtn", "xpath=//*[contains(@class,'btn btn-default') and text()='Settings']", oWebDriver);
    public CommonWebElement oNextBtn = new CommonWebElement("oNextBtn", "xpath=//*[contains(@class,'griddle-next')]", oWebDriver);
    public CommonWebElement oPreviousBtn = new CommonWebElement("oPreviousBtn", "xpath=//*[contains(@class,'griddle-previous')]", oWebDriver);

    // table
    public CommonWebElement oFirstRow = new CommonWebElement("oFirstRow", "xpath=//tr[1][contains(@class, 'new-patient')]|//tr[1][contains(@class, 'standard-row')]", oWebDriver);
    public CommonWebElement oPageNumberSelector = new CommonWebElement("oPageNumberSelector", "xpath=//*[contains(@class,'griddle-page')]/select", oWebDriver);

    // filter results
    public CommonWebElement oFilterResultsTextInput = new CommonWebElement("oFilterResultsTextInput", "xpath=//*[contains(@placeholder,'Filter Results')]", oWebDriver);

    // table headers
    public CommonWebElement oInsuranceHeader = new CommonWebElement("oInsuranceHeader", "xpath=//th[@data-title='insuranceGroupId']",oWebDriver);
    public CommonWebElement oVisitStatusHeader = new CommonWebElement("oVisitStatusHeader", "xpath=//th[@data-title='visitStatus']",oWebDriver);
    public CommonWebElement oVisitCodeHeader = new CommonWebElement("oVisitCodeHeader", "xpath=//th[@data-title='visitCode']",oWebDriver);
    public CommonWebElement oVisitServiceHeader = new CommonWebElement("oVisitServiceHeader", "xpath=//th[@data-title='visitServiceName']",oWebDriver);
    public CommonWebElement oPatientHeader = new CommonWebElement("oPatientHeader", "xpath=//th[@data-title='patientName']",oWebDriver);
    public CommonWebElement oAddressHeader = new CommonWebElement("oAddressHeader", "xpath=//th[@data-title='addressLong']",oWebDriver);
    public CommonWebElement oDoctorHeader = new CommonWebElement("oDoctorHeader", "xpath=//th[@data-title='doctorName']",oWebDriver);
    public CommonWebElement oAssistantHeader = new CommonWebElement("oAssistantHeader", "xpath=//th[@data-title='assistantName']",oWebDriver);
    public CommonWebElement oMarketHeader = new CommonWebElement("oMarketHeader", "xpath=//th[@data-title='marketCode']",oWebDriver);
    public CommonWebElement oReqTimeSlotHeader = new CommonWebElement("oReqTimeSlotHeader", "xpath=//th[@data-title='requestedTimeSlotClientFormatted']",oWebDriver);
    public CommonWebElement oScheduledDateHeader = new CommonWebElement("oScheduledDateHeader", "xpath=//th[@data-title='scheduledTimeClientFormatted']",oWebDriver);
    public CommonWebElement oReschedulesHeader = new CommonWebElement("oReschedulesHeader", "xpath=//th[@data-title='reschedules']",oWebDriver);
    public CommonWebElement oRequestedOnHeader = new CommonWebElement("oRequestedOnHeader", "xpath=//th[@data-title='dateRequested']",oWebDriver);
    public CommonWebElement oPhoneHeader = new CommonWebElement("oPhoneHeader", "xpath=//th[@data-title='patientPhone']",oWebDriver);
    public CommonWebElement oEmailHeader = new CommonWebElement("oEmailHeader", "xpath=//th[@data-title='patientEmail']",oWebDriver);
    public CommonWebElement oAccountOwnerHeader = new CommonWebElement("oAccountOwnerHeader", "xpath=//th[@data-title='buildUserAccountFullName']",oWebDriver);

    // settings checkboxes
    public CommonWebElement oInsuranceCheckBox = new CommonWebElement("oInsuranceCheckBox", "xpath=//*[@data-name='insuranceGroupId']", oWebDriver);
    public CommonWebElement oVisitStatusCheckBox = new CommonWebElement("oVisitStatusCheckBox", "xpath=//*[@data-name='visitStatus']", oWebDriver);
    public CommonWebElement oVisitCodeCheckBox = new CommonWebElement("oVisitCodeCheckBox", "xpath=//*[@data-name='visitCode']", oWebDriver);
    public CommonWebElement oVisitServiceCheckBox = new CommonWebElement("oVisitServiceCheckBox", "xpath=//*[@data-name='visitServiceName']", oWebDriver);
    public CommonWebElement oPatientCheckBox = new CommonWebElement("oPatientCheckBox", "xpath=//*[@data-name='patientName']", oWebDriver);
    public CommonWebElement oAddressCheckBox = new CommonWebElement("oAddressCheckBox", "xpath=//*[@data-name='addressLong']", oWebDriver);
    public CommonWebElement oDoctorCheckBox = new CommonWebElement("oDoctorCheckBox", "xpath=//*[@data-name='doctorName']", oWebDriver);
    public CommonWebElement oAssistantCheckBox = new CommonWebElement("oAssistantCheckBox", "xpath=//*[@data-name='assistantName']", oWebDriver);
    public CommonWebElement oMarketCheckBox = new CommonWebElement("oMarketCheckBox", "xpath=//*[@data-name='marketCode']", oWebDriver);
    public CommonWebElement oRequestedOnCheckBox = new CommonWebElement("oRequestedOnCheckBox", "xpath=//*[@data-name='dateRequested']", oWebDriver);
    public CommonWebElement oReqTimeSlotCheckBox = new CommonWebElement("oReqTimeSlotCheckBox", "xpath=//*[@data-name='requestedTimeSlotClientFormatted']", oWebDriver);
    public CommonWebElement oScheduledDateCheckBox = new CommonWebElement("oScheduledDateCheckBox", "xpath=//*[@data-name='scheduledTimeClientFormatted']", oWebDriver);
    public CommonWebElement oReschedulesCheckBox = new CommonWebElement("oReschedulesCheckBox", "xpath=//*[@data-name='reschedules']", oWebDriver);
    public CommonWebElement oPhoneCheckBox = new CommonWebElement("oPhoneCheckBox", "xpath=//*[@data-name='patientPhone']", oWebDriver);
    public CommonWebElement oEmailCheckBox = new CommonWebElement("oEmailCheckBox", "xpath=//*[@data-name='patientEmail']", oWebDriver);
    public CommonWebElement oAccountOwnerCheckBox = new CommonWebElement("oAccountOwnerCheckBox", "xpath=//*[@data-name='buildUserAccountFullName']", oWebDriver);


    // icons
    public CommonWebElement oCancelledIcon = new CommonWebElement("oCancelledIcon", "xpath=//*[contains(@id,'svg-icon')]//text()[. = 'CANCELLED']",oWebDriver);
    public CommonWebElement oNewIcon = new CommonWebElement("oNewIcon", "xpath=//*[contains(@id,'svg-icon')]//text()[. = 'NEW']",oWebDriver);
    public CommonWebElement oFullyPaidIcon = new CommonWebElement("oFullyPaidIcon", "xpath=//*[contains(@id,'svg-icon')]//text()[. = 'FULLY_PAID']",oWebDriver);

    // badges
    public CommonWebElement oTestGroupBadge = new CommonWebElement("oTestGroupBadge", "xpath=//*[@class='label-as-badge purple-badge']",oWebDriver);
    public CommonWebElement oInsuredBadge = new CommonWebElement("oInsuredBadge", "xpath=//*[@class='label-as-badge green-badge']",oWebDriver);
    public CommonWebElement oALFacilityBadge = new CommonWebElement("oALFacilityBadge", "xpath=//*[@class='label-as-badge red-badge']",oWebDriver);



    //////////////////
    // Constructors //
    //////////////////
    public OpsVisitsPage(WebDriver oTargetDriver){
        super(oTargetDriver, URL);
    }

    public OpsVisitsPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////
    /**
     * Finds a row by visit code
     * @param sVisitCode (String) Visit code
     */
    public CommonWebElement findRowByVisitCode(String sVisitCode)
    {
        return new CommonWebElement("oRow"+sVisitCode, "xpath=//tr//text()[contains(.,'" + sVisitCode + "')]" ,oWebDriver);
    }


    public CommonWebElement findRowByNumber(Integer iNumber){
        return new CommonWebElement("oRow" + Integer.toString(iNumber), "xpath=//tbody/tr["+iNumber+"]", oWebDriver);
    }

    public void filterVisits(String sFilterText){
        this.oFilterResultsTextInput.sendKeys(sFilterText);
    }

    public String getVisitCodeBy(String sStatus){
        CommonWebElement oVisitCode = new CommonWebElement("oVisitCode", "xpath=(//tr[td[2]//b[text()='"+sStatus+"']]/td[3])[1]" ,oWebDriver);
        return oVisitCode.getText();
    }

    public String getVisitCodeBy(String sStatus, String sPatientName){
        CommonWebElement oVisitCode = new CommonWebElement("oVisitCode", "xpath=//tr[td//b='"+sStatus+"' and td='"+sPatientName+"']/td[3]" ,oWebDriver);
        return oVisitCode.getText();
    }

    public String getStatusByVisitCode(String sVisitCode){
        CommonWebElement oVisitCode = new CommonWebElement("oVisitCode", "xpath=//tr[td[3]/a='"+sVisitCode+"']/td[2]//b" ,oWebDriver);
        return oVisitCode.getText();
    }

}
