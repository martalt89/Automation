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
    public CommonWebElement oDetailsDateReqested = new CommonWebElement("oDetailsDateReqested", "xpath=//*[contains(@class,'collapsible-section')][1]//li[1]/div",oWebDriver);
    public CommonWebElement oDetailsRequestedTimeText = new CommonWebElement("oDetailsRequestedTimeText", "xpath=//label[text()='Requested Time Slot']",oWebDriver);
    public CommonWebElement oDetailsRequestedTimeSlot = new CommonWebElement("oDetailsRequestedTimeSlot", "xpath=//*[contains(@class,'collapsible-section')][1]//li[2]/div",oWebDriver);
    public CommonWebElement oDetailsTimeScheduledText = new CommonWebElement("oDetailsTimeScheduledText", "xpath=//label[text()='System Time Scheduled For']",oWebDriver);
    public CommonWebElement oDetailsTimeScheduled = new CommonWebElement("oDetailsTimeScheduled", "xpath=//*[contains(@class,'collapsible-section')][1]//li[3]/div",oWebDriver);
    public CommonWebElement oDetailsSymptomsText = new CommonWebElement("oDetailsSymptomsText", "xpath=//label[text()='Symptoms']",oWebDriver);
    public CommonWebElement oDetailsSymptoms = new CommonWebElement("oDetailsSymptoms", "xpath=//*[contains(@class,'collapsible-section')][1]//li[4]/div",oWebDriver);
    public CommonWebElement oDetailsEditSymptomsBtn = new CommonWebElement("oDetailsEditSymptomsBtn", "xpath=//*[contains(@class,'collapsible-section')][1]//li[4]//i[contains(@class,'fa-pencil')]",oWebDriver);
    public CommonWebElement oDetailsEditSymptomsCheckBtn = new CommonWebElement("oDetailsEditSymptomsCheckBtn", "xpath=//*[contains(@class,'collapsible-section')][1]//li[4]//i[contains(@class,'fa-check')]",oWebDriver);
    public CommonWebElement oDetailsEditSymptomsCircleBtn = new CommonWebElement("oDetailsEditSymptomsCircleBtn", "xpath=//*[contains(@class,'collapsible-section')][1]//li[4]//i[contains(@class,'fa-times-circle-o')]",oWebDriver);
    public CommonWebElement oDetailsEditSymptomsField = new CommonWebElement("oDetailsEditSymptomsField", "xpath=//*[contains(@class,'collapsible-section')][1]//li[4]//input",oWebDriver);
    public CommonWebElement oDetailsVisitNotesText = new CommonWebElement("oDetailsVisitNotesText", "xpath=//label[text()='Visit Notes']",oWebDriver);
    public CommonWebElement oDetailsVisitNotes = new CommonWebElement("oDetailsVisitNotes", "xpath=//*[contains(@class,'collapsible-section')][1]//li[5]/div",oWebDriver);
    public CommonWebElement oDetailsEditVisitNotesBtn = new CommonWebElement("oDetailsEditVisitNotesBtn", "xpath=//*[contains(@class,'collapsible-section')][1]//li[5]//i[contains(@class,'fa-pencil')]",oWebDriver);
    public CommonWebElement oDetailsEditVisitNotesCheckBtn = new CommonWebElement("oDetailsEditVisitNotesCheckBtn", "xpath=//*[contains(@class,'collapsible-section')][1]//li[5]//i[contains(@class,'fa-check')]",oWebDriver);
    public CommonWebElement oDetailsEditVisitNotesCircleBtn = new CommonWebElement("oDetailsEditVisitNotesCircleBtn", "xpath=//*[contains(@class,'collapsible-section')][1]//li[5]//i[contains(@class,'fa-times-circle-o')]",oWebDriver);
    public CommonWebElement oDetailsEditVisitNotesField = new CommonWebElement("oDetailsEditVisitNotesField", "xpath=//*[contains(@class,'collapsible-section')][1]//li[5]//input",oWebDriver);
    public CommonWebElement oDetailsLocationText = new CommonWebElement("oDetailsLocationText", "xpath=//label[text()='Location']",oWebDriver);
    public CommonWebElement oDetailsLocation = new CommonWebElement("oDetailsLocation", "xpath=//*[contains(@class,'collapsible-section')][1]//li[6]/div",oWebDriver);

    //patient
    public CommonWebElement oPatientBtn = new CommonWebElement("oPatientBtn", "xpath=//*[@class='card-section-header']//*[text()='Patient']",oWebDriver);
    public CommonWebElement oRelationshipText = new CommonWebElement("oRelationshipText", "xpath=//label[text()='Relationship']",oWebDriver);
    public CommonWebElement oPatientNameText = new CommonWebElement("oPatientNameText", "xpath=//label[text()='Patient Name']",oWebDriver);
    public CommonWebElement oPatientName = new CommonWebElement("oPatientName", "xpath=//*[contains(@class,'collapsible-section')][2]//li[2]/div",oWebDriver);
    public CommonWebElement oPatientEditNameBtn = new CommonWebElement("oPatientEditNameBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[2]//i[contains(@class,'fa-pencil')]",oWebDriver);
    public CommonWebElement oPatientEditNameCheckBtn = new CommonWebElement("oPatientEditNameCheckBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[2]//i[contains(@class,'fa-check')]",oWebDriver);
    public CommonWebElement oPatientEditNameCircleBtn = new CommonWebElement("oPatientEditNameCircleBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[2]//i[contains(@class,'fa-times-circle-o')]",oWebDriver);
    public CommonWebElement oPatientEditFirstNameField = new CommonWebElement("oPatientEditFirstNameField", "xpath=//*[contains(@class,'collapsible-section')][2]//li[2]//input[1]",oWebDriver);
    public CommonWebElement oPatientEditLastNameField = new CommonWebElement("oPatientEditLastNameField", "xpath=//*[contains(@class,'collapsible-section')][2]//li[2]//input[2]",oWebDriver);

    public CommonWebElement oPatientDateBirthText = new CommonWebElement("oPatientDateBirthText", "xpath=//label[text()='Patient Date Of Birth']",oWebDriver);
    public CommonWebElement oPatientDateBirth = new CommonWebElement("oPatientDateBirth", "xpath=//*[contains(@class,'collapsible-section')][2]//li[3]/div",oWebDriver);
    public CommonWebElement oPatientEditDateBirthBtn = new CommonWebElement("oPatientEditDateBirthBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[3]//i[contains(@class,'fa-pencil')]",oWebDriver);
    public CommonWebElement oPatientEditDateBirthCheckBtn = new CommonWebElement("oPatientEditDateBirthCheckBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[3]//i[contains(@class,'fa-check')]",oWebDriver);
    public CommonWebElement oPatientEditDateBirthCircleBtn = new CommonWebElement("oPatientEditDateBirthCircleBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[3]//i[contains(@class,'fa-times-circle-o')]",oWebDriver);
    public CommonWebElement oPatientEditDateBirthField = new CommonWebElement("oPatientEditDateBirthField", "xpath=//*[contains(@class,'collapsible-section')][2]//li[3]//input",oWebDriver);

    public CommonWebElement oPhoneNumberText = new CommonWebElement("oPhoneNumberText", "xpath=//label[text()='Phone Number']",oWebDriver);
    public CommonWebElement oPhoneNumber = new CommonWebElement("oPhoneNumber", "xpath=//*[contains(@class,'collapsible-section')][2]//li[4]/div",oWebDriver);
    public CommonWebElement oPatientEdiPhoneNoBtn = new CommonWebElement("oPatientEditPhoneNoBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[4]//i[contains(@class,'fa-pencil')]",oWebDriver);
    public CommonWebElement oPatientEditPhoneNoCheckBtn = new CommonWebElement("oPatientEditPhoneNoCheckBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[4]//i[contains(@class,'fa-check')]",oWebDriver);
    public CommonWebElement oPatientEditPhoneNoCircleBtn = new CommonWebElement("oPatientEdiPhoneNoCircleBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[4]//i[contains(@class,'fa-times-circle-o')]",oWebDriver);
    public CommonWebElement oPatientEditPhoneNoField = new CommonWebElement("oPatientEditPhoneNoField", "xpath=//*[contains(@class,'collapsible-section')][2]//li[4]//input",oWebDriver);


    public CommonWebElement oPatientEmailText = new CommonWebElement("oPatientEmailText", "xpath=//label[text()='Email']",oWebDriver);
    public CommonWebElement oPatientEmail = new CommonWebElement("oPatientEmail", "xpath=//*[contains(@class,'collapsible-section')][2]//li[5]/div",oWebDriver);
    public CommonWebElement oPatientEditEmailBtn = new CommonWebElement("oPatientEditEmailBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[5]//i[contains(@class,'fa-pencil')]",oWebDriver);
    public CommonWebElement oPatientEditEmailCheckBtn = new CommonWebElement("oPatientEditEmailCheckBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[5]//i[contains(@class,'fa-check')]",oWebDriver);
    public CommonWebElement oPatientEditEmailCircleBtn = new CommonWebElement("oPatientEdiEmailCircleBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[5]//i[contains(@class,'fa-times-circle-o')]",oWebDriver);
    public CommonWebElement oPatientEditEmailField = new CommonWebElement("oPatientEditEmailField", "xpath=//*[contains(@class,'collapsible-section')][2]//li[5]//input",oWebDriver);

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

    /**
     * Edits patient symptoms from visit summary modal, Details section
     * @param sSymptom (String) Symptoms
     */
    public void editSymptoms(String sSymptom){
        this.oDetailsEditSymptomsBtn.click();
        this.oDetailsEditSymptomsField.sendKeys(sSymptom);
        this.oDetailsEditSymptomsCheckBtn.click();

    }

    /**
     * Edits patient visit notes from visit summary modal, Details section
     * @param sNotes (String) Visit notes
     */
    public void editVisitNotes(String sNotes){
        this.oDetailsEditVisitNotesBtn.click();
        this.oDetailsEditVisitNotesField.sendKeys(sNotes);
        this.oDetailsEditVisitNotesCheckBtn.click();
    }

    /**
     * Edits patient firstname, lastname from visit summary modal, Patient section
     * @param sFirstname (String) Patient Firstname
     * @param sLastname (String) Patient Lastname
     */
    public void editPatientName(String sFirstname, String sLastname){
        this.oPatientEditNameBtn.click();
        this.oPatientEditFirstNameField.sendKeys(sFirstname);
        this.oPatientEditLastNameField.sendKeys(sLastname);
        this.oPatientEditNameCheckBtn.click();
    }

    /**
     * Edits patient date of birth from visit summary modal, Patient section
     * @param sDateOfBirth (String) Patient date of birth
     */
    public void editPatientDateOfBirth(String sDateOfBirth){
        this.oPatientEditDateBirthBtn.click();
        this.oPatientEditDateBirthField.sendKeys(sDateOfBirth);
        this.oPatientEditDateBirthCheckBtn.click();
    }

    /**
     * Edits patient phone number from visit summary modal, Patient section
     * @param sPhoneNo (String) Patient phone number
     */
    public void editPatientPhoneNo(String sPhoneNo){
        this.oPatientEdiPhoneNoBtn.click();
        this.oPatientEditPhoneNoField.sendKeys(sPhoneNo);
        this.oPatientEditPhoneNoCheckBtn.click();
    }

    /**
     * Edits patient email from visit summary modal, Patient section
     * @param sEmail (String) Patient email
     */
    public void editPatientEmail(String sEmail){
        this.oPatientEditEmailBtn.click();
        this.oPatientEditEmailField.sendKeys(sEmail);
        this.oPatientEditEmailCheckBtn.click();
    }
}
