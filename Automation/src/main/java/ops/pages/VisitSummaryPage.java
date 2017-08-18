package ops.pages;

import framework.web.CommonWebElement;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;

public class VisitSummaryPage extends WebBase{
    public static final String URL = "https://ops.qa.heal.com/dashboard";

    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oCloseVisitSummaryBtn = new CommonWebElement("oCloseVisitSummaryBtn", "xpath=//*[contains(@class,'close-card-button')]",oWebDriver);
    public CommonWebElement oEmail = new CommonWebElement("oEmail", "xpath=//*[@class='email']",oWebDriver);
    //actions
    public CommonWebElement oActionsBtn = new CommonWebElement("oActionsBtn", "xpath=//button[text()='Actions']",oWebDriver);
    public CommonWebElement oActionsMenuStartVisit = new CommonWebElement("oActionsMenuStartVisit", "xpath=//li/a[text()='Start Visit']",oWebDriver);
    public CommonWebElement oActionsMenuCancelVisit = new CommonWebElement("oActionsMenuCancelVisit", "xpath=//li/a[text()='Cancel Visit']",oWebDriver);
    public CommonWebElement oActionsMenuChangeProvider = new CommonWebElement("oActionsMenuChangeProvider", "xpath=//li/a[text()='Change Provider']",oWebDriver);
    public CommonWebElement oActionsMenuAddInsurance = new CommonWebElement("oActionsMenuAddInsurance", "xpath=//li/a[text()='Add Insurance']",oWebDriver);

    public CommonWebElement oVisitId = new CommonWebElement("oVisitId", "xpath=//*[@class='status-container']/div[1]",oWebDriver);
    public CommonWebElement oVisitStatus = new CommonWebElement("oVisitStatus", "xpath=//*[@class='status-container']/div[3]",oWebDriver);
    public CommonWebElement oServiceName = new CommonWebElement("oServiceName", "xpath=//*[@class='service-name-container']",oWebDriver);
    //details
    public CommonWebElement oDetailsBtn = new CommonWebElement("oDetailsBtn", "xpath=//*[@class='card-section-header']//*[text()='Details']",oWebDriver);
    public CommonWebElement oDetailsDateReqestedText = new CommonWebElement("oDetailsDateReqestedText", "xpath=//label[text()='Date Requested']",oWebDriver);
    public CommonWebElement oDetailsDateReqested = new CommonWebElement("oDetailsDateReqested", "xpath=//div[@class='collapsible-section opened'][1]//li[1]/div",oWebDriver);
    public CommonWebElement oDetailsRequestedTimeText = new CommonWebElement("oDetailsRequestedTimeText", "xpath=//label[text()='Requested Time Slot']",oWebDriver);
    public CommonWebElement oDetailsRequestedTimeSlot = new CommonWebElement("oDetailsRequestedTimeSlot", "xpath=//div[@class='collapsible-section opened'][1]//li[2]/div",oWebDriver);
    public CommonWebElement oDetailsTimeScheduledText = new CommonWebElement("oDetailsTimeScheduledText", "xpath=//label[text()='System Time Scheduled For']",oWebDriver);
    public CommonWebElement oDetailsTimeScheduled = new CommonWebElement("oDetailsTimeScheduled", "xpath=//div[@class='collapsible-section opened'][1]//li[3]/div",oWebDriver);
    public CommonWebElement oDetailsSymptomsText = new CommonWebElement("oDetailsSymptomsText", "xpath=//label[text()='Symptoms']",oWebDriver);
    public CommonWebElement oDetailsSymptoms = new CommonWebElement("oDetailsSymptoms", "xpath=//div[@class='collapsible-section opened'][1]//li[4]/div",oWebDriver);
    public CommonWebElement oDetailsVisitNotesText = new CommonWebElement("oDetailsVisitNotesText", "xpath=//label[text()='Visit Notes']",oWebDriver);
    public CommonWebElement oDetailsVisitNotes = new CommonWebElement("oDetailsVisitNotes", "xpath=//div[@class='collapsible-section opened'][1]//li[5]/div",oWebDriver);
    public CommonWebElement oDetailsVisitLocationText = new CommonWebElement("oDetailsVisitLocationText", "xpath=//label[text()='Location']",oWebDriver);
    public CommonWebElement oDetailsVisitLocation = new CommonWebElement("oDetailsVisitLocation", "xpath=//div[@class='collapsible-section opened'][1]//li[5]/div",oWebDriver);

    //patient
    public CommonWebElement oPatientBtn = new CommonWebElement("oPatientBtn", "xpath=//*[@class='card-section-header']//*[text()='Patient']",oWebDriver);
    public CommonWebElement oRelationshipText = new CommonWebElement("oRelationshipText", "xpath=//label[text()='Relationship']",oWebDriver);
    public CommonWebElement oPatientNameText = new CommonWebElement("oPatientNameText", "xpath=//label[text()='Patient Name']",oWebDriver);
    public CommonWebElement oPatientDateBirthText = new CommonWebElement("oPatientDateBirthText", "xpath=//label[text()='Patient Date Of Birth']",oWebDriver);
    public CommonWebElement oPhoneNumberText = new CommonWebElement("oPhoneNumberText", "xpath=//label[text()='Phone Number']",oWebDriver);
    public CommonWebElement oEmailText = new CommonWebElement("oEmailText", "xpath=//label[text()='Email']",oWebDriver);
    public CommonWebElement oProxyNumberText = new CommonWebElement("oProxyNumberText", "xpath=//label[text()='Proxy Number']",oWebDriver);
    public CommonWebElement oGenderText = new CommonWebElement("oGenderText", "xpath=//label[text()='Gender']",oWebDriver);
    public CommonWebElement oChartIdText = new CommonWebElement("oChartIdText", "xpath=//label[text()='Chart Id']",oWebDriver);
    public CommonWebElement oPatientIdText = new CommonWebElement("oPatientIdText", "xpath=//label[text()='Patient Id']",oWebDriver);
    public CommonWebElement oCompletedVisitsText = new CommonWebElement("oCompletedVisitsText", "xpath=//label[text()='Completed Visits']",oWebDriver);
    public CommonWebElement oCancelledVisitsText = new CommonWebElement("oCancelledVisitsText", "xpath=//label[text()='Cancelled Visits']",oWebDriver);
    //provider
    public CommonWebElement oProviderBtn = new CommonWebElement("oProviderBtn", "xpath=//*[@class='card-section-header']//*[text()='Provider']",oWebDriver);
    public CommonWebElement oDoctorText = new CommonWebElement("oDoctorText", "xpath=//label[text()='Doctor']",oWebDriver);
    public CommonWebElement oMedicalAssistantText = new CommonWebElement("oMedicalAssistantText", "xpath=//label[text()='Medical Assistant']",oWebDriver);
    //insurance
    public CommonWebElement oInsuranceBtn = new CommonWebElement("oInsuranceBtn", "xpath=//*[@class='card-section-header']//*[text()='Insurance']",oWebDriver);

    //billing
    public CommonWebElement oBillingBtn = new CommonWebElement("oBillingBtn", "xpath=//*[@class='card-section-header']//*[text()='Billing']",oWebDriver);
    public CommonWebElement oCHARGEDtext = new CommonWebElement("oCHARGEDtext", "xpath=//label[text()='CHARGED']",oWebDriver);
    public CommonWebElement oOriginalAmountText = new CommonWebElement("oOriginalAmountText", "xpath=//label[text()='Original Amount']",oWebDriver);
    public CommonWebElement oAmountDiscountedText = new CommonWebElement("oAmountDiscountedText", "xpath=//label[text()='Amount Discounted']",oWebDriver);
    public CommonWebElement oAmountChargedText = new CommonWebElement("oAmountChargedText", "xpath=//label[text()='Amount Charged']",oWebDriver);
    public CommonWebElement oRefundedText = new CommonWebElement("oRefundedText", "xpath=//label[text()='Refunded']",oWebDriver);

    //visit log
    public CommonWebElement oVisitLogBtn = new CommonWebElement("oVisitLogBtn", "xpath=//*[@class='card-section-header']//*[text()='VisitLog']",oWebDriver);
    public CommonWebElement oLogTextArea = new CommonWebElement("oLogTextArea", "xpath=//div[contains(@class,'visit-log')]/textarea",oWebDriver);

    //////////////////
    // Constructors //
    //////////////////

    public VisitSummaryPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public VisitSummaryPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }
    public VisitSummaryPage(){
        super();
    }

    /////////////
    // Methods //
    /////////////

}
