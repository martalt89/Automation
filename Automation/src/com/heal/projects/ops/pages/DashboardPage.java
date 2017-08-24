package com.heal.projects.ops.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;

import java.util.List;

public class DashboardPage extends WebBase{
    public static final String URL = "https://ops.qa.heal.com/dashboard";

    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oOperationsViewTitle = new CommonWebElement("oOperationsViewTitle", "xpath=//h1[text()='Operations View']",oWebDriver);
    public CommonWebElement oVisitsContainer = new CommonWebElement("oVisitsContainer", "xpath=//*[@class='griddle-container']", oWebDriver);

    //city names
    public CommonWebElement oLosAngelesTitle = new CommonWebElement("oLosAngelesTitle", "xpath=//*[contains(@class, 'market-name') and text()='LOS ANGELES']",oWebDriver);
    public CommonWebElement oLongBeachTitle = new CommonWebElement("oLongBeachTitle", "xpath=//*[contains(@class, 'market-name') and text()='LONG BEACH']",oWebDriver);
    public CommonWebElement oOrangeCountyTitle = new CommonWebElement("oOrangeCountyTitle", "xpath=//*[contains(@class, 'market-name') and text()='ORANGE COUNTY']",oWebDriver);
    public CommonWebElement oSanDiegoTitle = new CommonWebElement("oSanDiegoTitle", "xpath=//*[contains(@class, 'market-name') and text()='SAN DIEGO']",oWebDriver);
    public CommonWebElement oSanFranciscoTitle = new CommonWebElement("oSanFranciscoTitle", "xpath=//*[contains(@class, 'market-name') and text()='SAN FRANCISCO']",oWebDriver);
    public CommonWebElement oSiliconValleyTitle = new CommonWebElement("oSiliconValleyTitle", "xpath=//*[contains(@class, 'market-name') and text()='SILICON VALLEY']",oWebDriver);
    public CommonWebElement oWashingtonDCTitle = new CommonWebElement("oWashingtonDCTitle", "xpath=//*[contains(@class, 'market-name') and text()='WASHINGTON D.C.']",oWebDriver);
    public CommonWebElement oLasVegasTitle = new CommonWebElement("oLasVegasTitle", "xpath=//*[contains(@class, 'market-name') and text()='LAS VEGAS']",oWebDriver);

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
    public CommonWebElement oAllBtn = new CommonWebElement("oAllBtn", "xpath=//*[contains(@class,'btn-default') and text()='ALL']",oWebDriver);
    public CommonWebElement oActiveBtn = new CommonWebElement("oActiveBtn", "xpath=//*[contains(@class,'btn-default') and text()='Active']",oWebDriver);
    public CommonWebElement oScheduledBtn = new CommonWebElement("oScheduledBtn", "xpath=//*[contains(@class,'btn-default') and text()='Scheduled']",oWebDriver);
    public CommonWebElement oPreviousBtn = new CommonWebElement("oPreviousBtn", "xpath=//*[contains(@class,'btn-default') and text()='Previous']",oWebDriver);
    public CommonWebElement oTodayBtn = new CommonWebElement("oTodayBtn", "xpath=//*[contains(@class,'btn-default') and text()='Today']",oWebDriver);
    public CommonWebElement oTomorrowBtn = new CommonWebElement("oTomorrowBtn", "xpath=//*[contains(@class,'btn-default') and text()='Tomorrow']",oWebDriver);
    public CommonWebElement oPaginationPreviousBtn = new CommonWebElement("oPaginationPreviousBtn", "xpath=//*[@class='griddle-previous']",oWebDriver);
    public CommonWebElement oPaginationSelectBtn = new CommonWebElement("oPaginationSelectBtn", "xpath=//*[@class='griddle-page']",oWebDriver);
    public CommonWebElement oPaginationNextBtn = new CommonWebElement("oPaginationNextBtn", "xpath=//*[@class='griddle-next']",oWebDriver);

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

    // icons
    public CommonWebElement oCancelledIcon = new CommonWebElement("oCancelledIcon", "xpath=//*[@class='griddle-icon']//b[text()='CANCELED']",oWebDriver);
    public CommonWebElement oNewIcon = new CommonWebElement("oNewIcon", "xpath=//*[@class='griddle-icon']//b[text()='NEW']",oWebDriver);
    public CommonWebElement oFullyPaidIcon = new CommonWebElement("oFullyPaidIcon", "xpath=//*[@class='griddle-icon']//b[text()='FULLY_PAID']",oWebDriver);

    // badges
    public CommonWebElement oTestGroupBadge = new CommonWebElement("oTestGroupBadge", "xpath=//*[@class='label-as-badge purple-badge']",oWebDriver);
    public CommonWebElement oInsuredBadge = new CommonWebElement("oInsuredBadge", "xpath=//*[@class='label-as-badge green-badge']",oWebDriver);
    public CommonWebElement oALFacilityBadge = new CommonWebElement("oALFacilityBadge", "xpath=//*[@class='label-as-badge red-badge']",oWebDriver);

    //////////////////
    // Constructors //
    //////////////////
    @Parameters({ "url" })
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

    /**
     * Selects only one city checkbox and deselects all others
     * @param sCityInitials (String) City initials e.g. "LA"
     */
    public void selectOnlyOneCity(String sCityInitials)
    {
        List<WebElement> cityCheckboxes = oWebDriver.findElements(By.xpath("//*[contains(@class,'market-code-checkboxes')]//*[@type='checkbox']"));
        for(WebElement ckbox : cityCheckboxes){
            if(ckbox.isSelected() && !ckbox.getAttribute("name").equals(sCityInitials)){
                ckbox.click();
            }
        }
    }

    /**
     * Selects all city checkboxes
     */
    public void selectAllCities()
    {
        List<WebElement> cityCheckboxes = oWebDriver.findElements(By.xpath("//*[contains(@class,'market-code-checkboxes')]//*[@type='checkbox']"));
        for(WebElement ckbox : cityCheckboxes){
            if(!ckbox.isSelected()){
                ckbox.click();
            }
        }
    }

    /**
     * Gets Status element from the table row that contains the specified visit code
     * @param sVisitCode (String) Visit code
     * @return (CommonWebElement) Status element
     */
    public CommonWebElement getStatusByVisitCode(String sVisitCode){
        return new CommonWebElement("oStatus", "//tr[td[3]/a[text()='" + sVisitCode + "']]/td[2]",oWebDriver);
    }

    /**
     * Gets Service element from the table row that contains the specified visit code
     * @param sVisitCode (String) Visit code
     * @return (CommonWebElement) Service element
     */
    public CommonWebElement getServiceByVisitCode(String sVisitCode){
        return new CommonWebElement("oService", "//tr[td[3]/a[text()='" + sVisitCode + "']]/td[4]",oWebDriver);
    }

    /**
     * Gets Patient element from the table row that contains the specified visit code
     * @param sVisitCode (String) Visit code
     * @return (CommonWebElement) Patient element
     */
    public CommonWebElement getPatientByVisitCode(String sVisitCode){
        return new CommonWebElement("oPatient", "//tr[td[3]/a[text()='" + sVisitCode + "']]/td[5]",oWebDriver);
    }

    /**
     * Gets Address element from the table row that contains the specified visit code
     * @param sVisitCode (String) Visit code
     * @return (CommonWebElement) Address element
     */
    public CommonWebElement getAddressByVisitCode(String sVisitCode){
        return new CommonWebElement("oAddress", "//tr[td[3]/a[text()='" + sVisitCode + "']]/td[6]",oWebDriver);
    }

    /**
     * Gets Doctor element from the table row that contains the specified visit code
     * @param sVisitCode (String) Visit code
     * @return (CommonWebElement) Doctor element
     */
    public CommonWebElement getDoctorByVisitCode(String sVisitCode){
        return new CommonWebElement("oDoctor", "//tr[td[3]/a[text()='" + sVisitCode + "']]/td[7]",oWebDriver);
    }

    /**
     * Gets Assistant element from the table row that contains the specified visit code
     * @param sVisitCode (String) Visit code
     * @return (CommonWebElement) Assistant element
     */
    public CommonWebElement getAssistantByVisitCode(String sVisitCode){
        return new CommonWebElement("oAssistant", "//tr[td[3]/a[text()='" + sVisitCode + "']]/td[8]",oWebDriver);
    }

    /**
     * Gets Requested time slot element from the table row that contains the specified visit code
     * @param sVisitCode (String) Visit code
     * @return (CommonWebElement) Requested time slot element
     */
    public CommonWebElement getRequestedTimeSlotByVisitCode(String sVisitCode){
        return new CommonWebElement("oRequestedTimeSlot", "//tr[td[3]/a[text()='" + sVisitCode + "']]/td[9]",oWebDriver);
    }

    /**
     * Gets Scheduled date element from the table row that contains the specified visit code
     * @param sVisitCode (String) Visit code
     * @return (CommonWebElement) Scheduled date element
     */
    public CommonWebElement getScheduledDateByVisitCode(String sVisitCode){
        return new CommonWebElement("oScheduledDate", "//tr[td[3]/a[text()='" + sVisitCode + "']]/td[10]",oWebDriver);
    }

    /**
     * Gets Reschedules element from the table row that contains the specified visit code
     * @param sVisitCode (String) Visit code
     * @return (CommonWebElement) Reschedules element
     */
    public CommonWebElement getReschedulesByVisitCode(String sVisitCode){
        return new CommonWebElement("oReschedules", "//tr[td[3]/a[text()='" + sVisitCode + "']]/td[11]",oWebDriver);
    }

}
