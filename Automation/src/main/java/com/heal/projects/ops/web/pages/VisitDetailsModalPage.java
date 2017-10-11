package com.heal.projects.ops.web.pages;

import com.heal.framework.foundation.SysTools;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.xml.xpath.XPath;
import java.util.List;

public class VisitDetailsModalPage extends WebBase{
    public static final String URL = "https://ops"+ baseUrl +"/dashboard";
    ///////////////////
    // Page Elements //
    ///////////////////

    //cancel reasons
    public static final String HEAL_LACK_TRIAGE = "HEAL: Lack of medical triage";
    public static final String HEAL_LEGAL_GUARDIAN = "HEAL: Legal Guardian not present";
    public static final String HEAL_NO_CAPACITY = "HEAL: No capacity";
    public static final String HEAL_OTHER_REASON = "HEAL: Other";
    public static final String HEAL_PAT_NOT_RESPOND = "HEAL: Patient did not respond";
    public static final String HEAL_PAT_NO_SHOW = "HEAL: Patient no show";
    public static final String HEAL_PAT_SURROUNDING = "HEAL: Patient surrounding is unsafe OR not private";
    public static final String HEAL_ER = "HEAL: Referred to ER by Heal";
    public static final String HEAL_SERVICE = "HEAL: Service not offered";
    public static final String PAT_BOOKED_MISTAKE = "PATIENT: Booked by mistake";
    public static final String PAT_DOCTOR_LATE = "PATIENT: Doctor arrived late or no-show";
    public static final String PAT_DOCTOR_GENDER = "PATIENT: Doctor gender preference not met";
    public static final String PAT_WRONG_INFO = "PATIENT: Entered wrong information";
    public static final String PAT_GOING_ER = "PATIENT: Going to ER or Doctor’s office";
    public static final String PAT_OTHER = "PATIENT: Other";
    public static final String PAT_BETTER = "PATIENT: Patient feels better";
    public static final String PAT_PRIMARY_DOC = "PATIENT: Primary Doctor not available";
    public static final String PAT_REBOOKED = "PATIENT: Rebooked";
    public static final String PAT_UNKNOWN = "PATIENT: Unknown (User refused to provide a reason)";
    //doctors
    public static final String DR_ABE = "Dr. Abe Malkin";
    public static final String DR_KIM = "Dr. Kim Samuel";
    public static final String DR_MITTON = "Dr. Mitton Bryan";
    public static final String DR_NILES = "Dr. Niles Gabriel";
    public static final String DR_VAHAN = "Dr. Melikyan Vahan";
    //medical assistants
    public static final String MA_KETTEL = "Kettelborough Michael";
    public static final String MA_WONG = "Wong James";
    //insurances
    public static final String AETNA = "aetna";
    public static final String ANTHEM = "Anthem Blue Cross";
    public static final String BLUE = "BlueShield";
    public static final String CIGNA = "Cigna";
    public static final String MERITAIN = "Meritain Health (aetna)";
    public static final String NIPPON = "Nippon Life Benefits (aetna)";
    public static final String PREMERA = "Premera BlueCross";
    public static final String UMR = "UMR (United Healthcare)";
    public static final String UNITED = "United Healthcare";
    public static final String NALC = "NALC (Cigna)";
    public static final String GEHA = "GEHA";


    public CommonWebElement oCloseVisitSummaryBtn = new CommonWebElement("oCloseVisitSummaryBtn", "xpath=//*[contains(@class,'close-card-button')]",oWebDriver);
    public CommonWebElement oEmail = new CommonWebElement("oEmail", "xpath=//*[@class='email']",oWebDriver);
    //actions
    public CommonWebElement oActionsBtn = new CommonWebElement("oActionsBtn", "xpath=//button[text()='Actions']",oWebDriver);
    public CommonWebElement oActionsMenuStartVisit = new CommonWebElement("oActionsMenuStartVisit", "xpath=//li/a[text()='Start Visit']",oWebDriver);
    public CommonWebElement oActionsMenuEndVisit = new CommonWebElement("oActionsMenuEndVisit", "xpath=//li/a[text()='End Visit']",oWebDriver);
    public CommonWebElement oActionsMenuCancelVisit = new CommonWebElement("oActionsMenuCancelVisit", "xpath=//li/a[text()='Cancel Visit']",oWebDriver);
    public CommonWebElement oActionsMenuChangeProvider = new CommonWebElement("oActionsMenuChangeProvider", "xpath=//*[text()='Change Provider']",oWebDriver);
    public CommonWebElement oActionsMenuAddInsurance = new CommonWebElement("oActionsMenuAddInsurance", "xpath=//a[text()='Add Insurance']",oWebDriver);
    public CommonWebElement oActionsRefundVisit = new CommonWebElement("oActionsRefundVisit", "xpath=//*[text()='Refund Visit']",oWebDriver);
    public CommonWebElement oActionDropDown = new CommonWebElement("oActionDropDown", "xpath=//ul[@role='menu']",oWebDriver);
    //actions - start visit modal
    public CommonWebElement oSelectStartTimeTitle = new CommonWebElement("oSelectStartTimeTitle", "xpath=//*[@class='modal-title']",oWebDriver);
    public CommonWebElement oStartTimeXBtn = new CommonWebElement("oStartTimeXBtn", "xpath=//*[@class='close']",oWebDriver);
    public CommonWebElement oStartTimeInput = new CommonWebElement("oStartTimeInput", "xpath=//*[@class='modal-body']//input",oWebDriver);
    public CommonWebElement oStarVisitCancelBtn = new CommonWebElement("oStartVisitCancelBtn", "xpath=//*[@class='modal-footer']//button[1]",oWebDriver);
    public CommonWebElement oStartVisitSubmitBtn = new CommonWebElement("oStartVisitSubmitBtn", "xpath=//button[contains(@class,'btn-success')]",oWebDriver);
    //actions - cancel visit modal
    public CommonWebElement oCancelVisitTitle = new CommonWebElement("oCancelVisitTitle", "xpath=//*[@class='modal-title']",oWebDriver);
    public CommonWebElement oReasonText = new CommonWebElement("oReasonText", "xpath=//*[@class='row-label'][text()='Reason for Cancellation:']",oWebDriver);
    public CommonWebElement oReasonInput = new CommonWebElement("oReasonInput", "xpath=//*[@class='modal-row-content']/select",oWebDriver);
    public CommonWebElement oNotesText = new CommonWebElement("oNotesText", "xpath=//*[@class='row-label'][text()='Notes (optional):']",oWebDriver);
    public CommonWebElement oNotesInput = new CommonWebElement("oNotesInput", "xpath=//*[@class='modal-row-content']/input",oWebDriver);
    public CommonWebElement oReebookingText = new CommonWebElement("oReebookingText", "xpath=//*[@class='row-label'][text()='Rebooking:']",oWebDriver);
    public CommonWebElement oReebookingOCAdminRadioBtn = new CommonWebElement("oReebookingOCAdminRadioBtn", "xpath=//*[@class='radio-btn'][1]",oWebDriver);
    public CommonWebElement oReebookingPatientRadioBtn = new CommonWebElement("oReebookingPatientRadioBtn", "xpath=//*[@class='radio-btn'][2]",oWebDriver);
    public CommonWebElement oReebookingNARadioBtn = new CommonWebElement("oReebookingNARadioBtn", "xpath=//*[@class='radio-btn'][3]",oWebDriver);
    public CommonWebElement oCancelVisitBtn = new CommonWebElement("oCancelVisitBtn", "xpath=//*[@class='modal-footer']/button",oWebDriver);
    //status badge
    public CommonWebElement oQeuedIcon = new CommonWebElement("oQeuedIcon", "xpath=//*[@class='status badge' and .='QUEUED']",oWebDriver);
    public CommonWebElement oAssignedIcon = new CommonWebElement("oAssignedIcon", "xpath=//*[@class='status badge' and .='DOCTOR_ASSIGNED']",oWebDriver);
    public CommonWebElement oStartedIcon = new CommonWebElement("oStartedIcon", "xpath=//*[@class='status badge' and .='STARTED']",oWebDriver);
    public CommonWebElement oCancelledIcon = new CommonWebElement("oCancelledIcon", "xpath=//*[@class='status badge' and .='CANCELLED']",oWebDriver);
    public CommonWebElement oFullyPaidIcon = new CommonWebElement("oFullyPaidIcon", "xpath=//*[@class='status badge' and .='FULLY_PAID']",oWebDriver);
    public CommonWebElement oRefundeddIcon = new CommonWebElement("oRefundeddIcon", "xpath=//*[@class='status badge' and .='REFUNDED']",oWebDriver);

    //actions - change provider modal
    public CommonWebElement oChangeProviderTitle = new CommonWebElement("oChangeProviderTitle", "xpath=//*[@class='modal-header']//h4",oWebDriver);
    public CommonWebElement oChooseDoctorText = new CommonWebElement("oChooseDoctorText", "xpath=//*[@class='modal-body']//label[text()='Choose Doctor']",oWebDriver);
    public CommonWebElement oChooseMedicalAssistantText = new CommonWebElement("oChooseMedicalAssistantText", "xpath=//*[@class='modal-body']//label[text()='Choose Medical Assistant]",oWebDriver);
    public CommonWebElement oSelectTimeSlotText = new CommonWebElement("oSelectTimeSlotText", "xpath=//*[@class='modal-body']//label[text()='Select Time Slot']",oWebDriver);
    public CommonWebElement oEnterManualTimeText = new CommonWebElement("oEnterManualTimeText", "xpath=//*[@class='modal-body']//label[text()='Enter Manual Time']",oWebDriver);
    public CommonWebElement oChooseDoctorInput = new CommonWebElement("oChooseDoctorInput", "xpath=//*[@class='modal-body']/div[1]/select",oWebDriver);
    public CommonWebElement oChooseMedicalAssistantInput = new CommonWebElement("oChooseMedicalAssistantInput", "xpath=//*[@class='modal-body']/div[2]/select",oWebDriver);
    public CommonWebElement oSelectTimeSlot = new CommonWebElement("oSelectTimeSlot", "xpath=//*[@class='timeSection']//select",oWebDriver);
    public CommonWebElement oEnterTimeInput = new CommonWebElement("oEnterTimeInput", "xpath=//*[@class='timeSection']//input",oWebDriver);
    public CommonWebElement oPatientConsentCheckbox = new CommonWebElement("oPatientConsentCheckbox", "xpath=//*[@class='modal-body']//input[@type='checkbox']",oWebDriver);
    public CommonWebElement oForceAssignBtn = new CommonWebElement("oForceAssignBtn", "xpath=//*[@class='modal-footer']/button[1]",oWebDriver);
    public CommonWebElement oCancelProviderBtn = new CommonWebElement("oCancelProviderBtn", "xpath=//*[@class='modal-footer']/button[2]",oWebDriver);
    public CommonWebElement oChangetBtn = new CommonWebElement("oChangetBtn", "xpath=//*[@class='modal-footer']/button[3]",oWebDriver);
    public CommonWebElement oProviderListLoading = new CommonWebElement("oChangetBtn", "xpath=//select[contains(.,'Loading')]",oWebDriver);


    //actions - Update Insurance modal
    public CommonWebElement oPayerText = new CommonWebElement("oPayerText", "xpath=//*[@class='modal-body']//label[text()='Payer']",oWebDriver);
    public CommonWebElement oPayerInput = new CommonWebElement("oPayerInput", "xpath=//*[@class='modal-body']//select",oWebDriver);
    public CommonWebElement oMemberId = new CommonWebElement("oMemberId", "xpath=//*[@class='modal-body']//label[text()='Member Id']",oWebDriver);
    public CommonWebElement oMemberIdInput = new CommonWebElement("oMemberIdInput", "xpath=//input[@placeholder='Enter Member Id']",oWebDriver);
    public CommonWebElement oGroupId = new CommonWebElement("oGroupId", "xpath=//*[@class='modal-body']//label[text()='Group Id']",oWebDriver);
    public CommonWebElement oGroupIdInput = new CommonWebElement("oGroupIdInput", "xpath=//input[@placeholder='Enter Group Id']",oWebDriver);
    public CommonWebElement oCancelInsuranceBtn = new CommonWebElement("oCancelInsuranceBtn", "xpath=//*[@class='modal-footer']/button[1]",oWebDriver);
    public CommonWebElement oSubmitBtn = new CommonWebElement("oSubmitBtn", "xpath=//*[contains(@class,'btn-success') and text()='Submit']",oWebDriver);

    //actions - Refund visit
    public CommonWebElement oPatientInformationText = new CommonWebElement("oPatientInformationText", "xpath=//*[@class='modal-body']//div[text()='Patient Information']",oWebDriver);
    public CommonWebElement oTotalRefundCheckbox = new CommonWebElement("oPatientName", "xpath=//input[@type='radio' and @id='TOTAL']",oWebDriver);
    public CommonWebElement oPartialRefundCheckbox = new CommonWebElement("oPartianRefundCheckbox", "xpath=//input[@type='radio' and @id='PARTIAL']",oWebDriver);
    public CommonWebElement oRefundReasonInput = new CommonWebElement("oRefundReasonInput", "xpath=//*[contains(@class,'refund-reason')]//input",oWebDriver);
    public CommonWebElement oRefundAmountInput = new CommonWebElement("oRefundAmountInput", "xpath=//*[contains(@class,'partial-amount')]//input",oWebDriver);
    public CommonWebElement oProcessRefundBtn = new CommonWebElement("oProcessRefundBtn", "xpath=//button[contains(.,'Refund')]",oWebDriver);
    public CommonWebElement oConfirmRefundBtn = new CommonWebElement("oConfirmRefundBtn", "xpath=//button[contains(.,'Confirm')]",oWebDriver);


    public CommonWebElement oVisitId = new CommonWebElement("oVisitId", "xpath=//*[@class='status-container']/div[1]",oWebDriver);
    public CommonWebElement oVisitStatus = new CommonWebElement("oVisitStatus", "xpath=//*[contains(@class,'status badge')]/span",oWebDriver);
    public CommonWebElement oServiceName = new CommonWebElement("oServiceName", "xpath=//*[@class='service-name-container']",oWebDriver);
    //details

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
    public CommonWebElement oChartID = new CommonWebElement("Chart Id", "xpath=//li[contains(.,'Chart Id #')]/div/a",oWebDriver);
    public CommonWebElement oPatientID = new CommonWebElement("Dr. Chrono Patient Id", "xpath=//li[contains(.,'Patient Id')]/div/a",oWebDriver);

    //patient
    public CommonWebElement oPatientBtn = new CommonWebElement("oPatientBtn", "xpath=//*[@class='card-section-header']//*[text()='Patient']",oWebDriver);
    public CommonWebElement oRelationshipText = new CommonWebElement("oRelationshipText", "xpath=//label[text()='Relationship']",oWebDriver);
    public CommonWebElement oPatientNameText = new CommonWebElement("oPatientNameText", "xpath=//label[text()='Patient Name']",oWebDriver);
    public CommonWebElement oPatientName = new CommonWebElement("oPatientName", "xpath=//*[contains(@class,'collapsible-section')][2]//li[2]/div",oWebDriver);
    public CommonWebElement oPatientEditNameBtn = new CommonWebElement("oPatientEditNameBtn", "xpath=((//div[contains(@class,'collapsible-section')]//span[text()='Patient'])/../following-sibling::div[contains(@class,'collapsible-container')]//*[@class='edit-field-buttons'])[1]",oWebDriver);
    public CommonWebElement oPatientEditNameCheckBtn = new CommonWebElement("oPatientEditNameCheckBtn", "xpath=(((//div[contains(@class,'collapsible-section')]//span[text()='Patient'])/../following-sibling::div[contains(@class,'collapsible-container')]//*[@class='edit-field-buttons'])[1])/i[1]",oWebDriver);
    public CommonWebElement oPatientEditNameCircleBtn = new CommonWebElement("oPatientEditNameCircleBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[2]//i[contains(@class,'fa-times-circle-o')]",oWebDriver);
    public CommonWebElement oPatientEditFirstNameField = new CommonWebElement("oPatientEditFirstNameField", "xpath=((//div[contains(@class,'collapsible-section')]//span[text()='Patient'])/../following-sibling::div[contains(@class,'collapsible-container')]//*[@class='edit-field-inputs'])[1]/div/input[1]",oWebDriver);
    public CommonWebElement oPatientEditLastNameField = new CommonWebElement("oPatientEditLastNameField", "xpath=((//div[contains(@class,'collapsible-section')]//span[text()='Patient'])/../following-sibling::div[contains(@class,'collapsible-container')]//*[@class='edit-field-inputs'])[1]/div/input[2]",oWebDriver);

    public CommonWebElement oPatientDateBirthText = new CommonWebElement("oPatientDateBirthText", "xpath=//label[text()='Patient Date Of Birth']",oWebDriver);
    public CommonWebElement oPatientDateBirth = new CommonWebElement("oPatientDateBirth", "xpath=((//div[contains(@class,'collapsible-section')]//span[text()='Patient'])/../following-sibling::div[contains(@class,'collapsible-container')]//*[@class='edit-field-inputs'])[2]/input",oWebDriver);
    public CommonWebElement oPatientEditDateBirthBtn = new CommonWebElement("oPatientEditDateBirthBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[3]//i[contains(@class,'fa-pencil')]",oWebDriver);
    public CommonWebElement oPatientEditDateBirthCheckBtn = new CommonWebElement("oPatientEditDateBirthCheckBtn", "xpath=(((//div[contains(@class,'collapsible-section')]//span[text()='Patient'])/../following-sibling::div[contains(@class,'collapsible-container')]//*[@class='edit-field-buttons'])[2])/i[1]",oWebDriver);
    public CommonWebElement oPatientEditDateBirthCircleBtn = new CommonWebElement("oPatientEditDateBirthCircleBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[3]//i[contains(@class,'fa-times-circle-o')]",oWebDriver);
    public CommonWebElement oPatientEditDateBirthField = new CommonWebElement("oPatientEditDateBirthField", "xpath=//*[contains(@class,'collapsible-section')][2]//li[3]//input",oWebDriver);

    public CommonWebElement oPhoneNumberText = new CommonWebElement("oPhoneNumberText", "xpath=//label[text()='Phone Number']",oWebDriver);
    public CommonWebElement oPhoneNumber = new CommonWebElement("oPhoneNumber", "xpath=//*[contains(@class,'collapsible-section')][2]//li[4]/div",oWebDriver);
    public CommonWebElement oPatientEdiPhoneNoBtn = new CommonWebElement("oPatientEditPhoneNoBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[4]//i[contains(@class,'fa-pencil')]",oWebDriver);
    public CommonWebElement oPatientEditPhoneNoCheckBtn = new CommonWebElement("oPatientEditPhoneNoCheckBtn", "xpath=(((//div[contains(@class,'collapsible-section')]//span[text()='Patient'])/../following-sibling::div[contains(@class,'collapsible-container')]//*[@class='edit-field-buttons'])[3])/i[1]",oWebDriver);
    public CommonWebElement oPatientEditPhoneNoCircleBtn = new CommonWebElement("oPatientEdiPhoneNoCircleBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[4]//i[contains(@class,'fa-times-circle-o')]",oWebDriver);
    public CommonWebElement oPatientEditPhoneNoField = new CommonWebElement("oPatientEditPhoneNoField", "xpath=((//div[contains(@class,'collapsible-section')]//span[text()='Patient'])/../following-sibling::div[contains(@class,'collapsible-container')]//*[@class='edit-field-inputs'])[3]/input",oWebDriver);


    public CommonWebElement oPatientEmailText = new CommonWebElement("oPatientEmailText", "xpath=//label[text()='Email']",oWebDriver);
    public CommonWebElement oPatientEmail = new CommonWebElement("oPatientEmail", "xpath=//*[contains(@class,'collapsible-section')][2]//li[5]/div",oWebDriver);
    public CommonWebElement oPatientEditEmailBtn = new CommonWebElement("oPatientEditEmailBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[5]//i[contains(@class,'fa-pencil')]",oWebDriver);
    public CommonWebElement oPatientEditEmailCheckBtn = new CommonWebElement("oPatientEditEmailCheckBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[5]//i[contains(@class,'fa-check')]",oWebDriver);
    public CommonWebElement oPatientEditEmailCircleBtn = new CommonWebElement("oPatientEdiEmailCircleBtn", "xpath=//*[contains(@class,'collapsible-section')][2]//li[5]//i[contains(@class,'fa-times-circle-o')]",oWebDriver);
    public CommonWebElement oPatientEditEmailField = new CommonWebElement("oPatientEditEmailField", "xpath=//*[contains(@class,'collapsible-section')][2]//li[5]//input",oWebDriver);


    //////////////////
    // Constructors //
    //////////////////

    public VisitDetailsModalPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public VisitDetailsModalPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }
    public VisitDetailsModalPage(){
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

    public void openActionsMenu(){
        this.oActionsBtn.click();
        SysTools.sleepFor(1); //This will wait for a small animation to complete
    }
    //Actions methods
    //start visit methods
    /**
     * Opens Visit start time modal from Actions -> Start Visit
     */
    public void openStartVisitModal(){
        openActionsMenu();
        this.oActionsMenuStartVisit.click();
    }

    public void startVisit(String sDateTime){
        openStartVisitModal();
        SysTools.sleepFor(1);
        this.oStartTimeInput.sendKeys(sDateTime);
        this.oStartVisitSubmitBtn.click();
    }

    public void startVisit(){
        openStartVisitModal();
        SysTools.sleepFor(1);
        this.oStartVisitSubmitBtn.click();
        SysTools.sleepFor(3);
    }
    //todo: also add methods for selecting date/time from the calendar
    /**
     * Sets a visit start time on Actions -> Start Visit modal
     * @param sStartTime (String) Start time in "mm/dd/yyyy hh:mm AM/PM" format. e.g. 08/22/2017 1:47 AM
     */
    public void editVisitStartTime(String sStartTime){
        openStartVisitModal();
        this.oStartTimeInput.sendKeys(sStartTime);
        this.oStartVisitSubmitBtn.click();
    }

    //end visit methods
    public void openEndVisitModal(){
        this.oActionsBtn.click();
        SysTools.sleepFor(1); //This will wait for a small animation to complete
        this.oActionsMenuEndVisit.click();
    }

    public void endVisit(){
        openEndVisitModal();
        SysTools.sleepFor(1);
        this.oStartVisitSubmitBtn.click();
    }

    public void endVisit(String sStartTime){
        openStartVisitModal();
        SysTools.sleepFor(1);
        this.oStartTimeInput.sendKeys(sStartTime);
        this.oStartVisitSubmitBtn.click();
    }

    //cancel visit methods
    public void selectCancelReason(String sReason){
        this.oReasonInput.select(sReason,false);
    }

    public void editCancelNotes(String sNotes){
        this.oNotesInput.sendKeys(sNotes);
    }

    /**
     * Cancels a visit from Actions -> Cancel Visit
     * @param sReason (String) Visit Reason (From dropdown menu)
     * @param sNotes (String) Aditional notes/comments
     */
    public void cancelVisit(String sReason, String sNotes){
        this.oActionsBtn.click();
        this.oActionsMenuCancelVisit.click();
        this.selectCancelReason(sReason);
        this.editCancelNotes(sNotes);
        this.oCancelVisitBtn.click();
    }
    //change provider methods

    public void openChangeProviderModal(){
        this.oActionsBtn.click();
        this.oActionDropDown.waitForVisible();
        this.oActionsMenuChangeProvider.click();
    }

    /**
     * Selects a doctor on Actions -> Change Provider modal
     * @param sDoctorName (String) Provider name
     */
    public void chooseDoctorAndMA(String sDoctorName, String sMAName){
        //CommonWebElement selectTiems = new CommonWebElement("selectTiems", );

        openChangeProviderModal();
        if (this.oProviderListLoading.exists()) {
            this.oProviderListLoading.waitForInvisible(15);
        }
        this.oChooseDoctorInput.waitForVisible();
        this.oChooseDoctorInput.select(sDoctorName,false);
        this.oChooseMedicalAssistantInput.select("MA " + sMAName,false);
    }
    public void chooseDoctor(String sDoctorName){
        openChangeProviderModal();
        this.oChooseDoctorInput.select(sDoctorName,false);
    }

    /**
     * Types data in Enter Manual Time input from Change Provider modal
     * @param sDateTime (String) Date and time in "mm/dd/yyyy hh:mm AM/PM" format. e.g. 08/22/2017 1:47 AM
     */
    public void editManualTime(String sDateTime){
        this.oEnterTimeInput.sendKeys(sDateTime);
    }

    //update insurance methods
    public void openAddInsuranceModal(){
        openActionsMenu();
        this.oActionsMenuAddInsurance.click();
    }
    /**
     * Selects a payer on Actions -> Add Insurance modal
     * @param sPayer (String) Payer
     */
    public void selectPayer(String sPayer){
        this.oPayerInput.select(sPayer,false);
    }

    /**
     * Edits Member Id field from Actions -> Add Insurance modal
     * @param sMemberId (String) Member Id
     */
    public void editMemberId(String sMemberId){
        this.oMemberIdInput.sendKeys(sMemberId);
    }

    /**
     * Edits Group Id field from Actions -> Add Insurance modal
     * @param sGroupId (String) Group Id
     */
    public void editGroupId(String sGroupId){
        this.oGroupIdInput.sendKeys(sGroupId);
    }

    /**
     * Adds/Updates insurance from Actions -> Add insurance
     * @param sPayer (String) Insurance payer
     * @param sMemberId (String) Member ID
     * @param sGroupId (String) Group ID
     */
    public void updateInsurance(String sPayer, String sMemberId, String sGroupId){
        this.openAddInsuranceModal();
        this.selectPayer(sPayer);
        this.editMemberId(sMemberId);
        this.editGroupId(sGroupId);
        this.oSubmitBtn.click();
    }

    /**
     * Mehtod to add visit notes
     * @param sNotesMessage ->takes string message for entering note details
     */
    public void addVisitNotes(String sNotesMessage){
        this.oDetailsEditVisitNotesBtn.clickAndWait(this.oDetailsEditVisitNotesField,true);
        this.oDetailsEditVisitNotesField.sendKeys("Ignore-Automation test running");
        this.oDetailsEditVisitNotesCheckBtn.click();
    }
    //refund methods
    public void openRefundVisitModal(){
        this.oActionsBtn.click();
        SysTools.sleepFor(1); //This will wait for a small animation to complete
        this.oActionsRefundVisit.click();
    }

    public CommonWebElement getModalElementByText(String sText) {
        return new CommonWebElement("oElement", "xpath=//*[@class='modal-body']//div[text()='" + sText + "']",oWebDriver);
    }

    public void selectTotalRefund(String sReason){
        this.openRefundVisitModal();
        SysTools.sleepFor(1); //This will wait for a small animation to complete
        this.oTotalRefundCheckbox.click();
        this.oRefundReasonInput.sendKeys(sReason);
        this.oProcessRefundBtn.click();
        this.oConfirmRefundBtn.click();
    }

    public void selectPartialRefund(String sAmount, String sReason){
        this.openRefundVisitModal();
        SysTools.sleepFor(1); //This will wait for a small animation to complete
        this.oPartialRefundCheckbox.click();
        this.oRefundAmountInput.sendKeys(sAmount);
        this.oRefundReasonInput.sendKeys(sReason);
        this.oProcessRefundBtn.click();
        this.oConfirmRefundBtn.click();
    }

    public void switchToUrlWithVisitCode(String sUrlWithVisitCode){
        oWebDriver.navigate().to(sUrlWithVisitCode);
        oWebDriver.navigate().refresh();
        waitForPageReady(sUrlWithVisitCode);
    }


    public void checkVisitStatus(CommonWebElement status, int duration){
        int seconds = 0;
            while (duration>seconds) {
                try {
                    status.waitForVisible(3);
                    break;
                }catch (Exception e){
                    //do nothing
                }
                oWebDriver.navigate().refresh();
                waitForPageLoad();
                seconds++;
            }
    }


    public void checkVisitStatusWithRefresh(String sStatus, int iSeconds) {

        switch (sStatus.toUpperCase()) {
            case "QUEUED":
                checkVisitStatus(oQeuedIcon, iSeconds);
                break;
            case "DOCTOR_ASSIGNED":
                checkVisitStatus(oAssignedIcon, iSeconds);
                break;
            case "STARTED":
                checkVisitStatus(oStartedIcon, iSeconds);
                break;
            case "CANCELLED":
                checkVisitStatus(oCancelledIcon, iSeconds);
                break;
            case "FULLY_PAID":
                checkVisitStatus(oFullyPaidIcon, iSeconds);
                break;
            case "REFUNDED":
                checkVisitStatus(oRefundeddIcon, iSeconds);
                break;
            default:
                break;
        }
    }

    public CommonWebElement getChangeProviderToastMessage(String sVisitId, String sDoctorName) {

        return new CommonWebElement("oToastMessage", "xpath=//*[text()='Successfully changed the provider for Visit '"+sVisitId+"' to '"+sDoctorName+"', MD']",oWebDriver);
    }

    public int LogDetailsCount(){
        //CommonWebElement oElement = new CommonWebElement("oElement","xpath=//*[@class='log-details']",oWebDriver);
        By logDetailsList = By.xpath("//*[@class='log-details']");
        List<WebElement> commonWebElementList= getWebDriver().findElements(logDetailsList);
        return commonWebElementList.size();
    }

    /**
     * Method expands the desired card section header on visit details side card
     * @param sCardToOpen --> Card that you want to open on visitDetails page
     */
    public void expandCardSectionHeader(String sCardToOpen){
        CommonWebElement cardSectionHeader = new CommonWebElement("cardSectionHeader","xpath=(//div[contains(@class,'collapsible-section')]//span[text()='" +sCardToOpen+ "'])",oWebDriver);
        CommonWebElement cardSectionToBeExpanded = new CommonWebElement("cardSectionToBeExpanded","xpath=(//div[contains(@class,'collapsible-section')]//span[text()='" +sCardToOpen+ "'])/../..",oWebDriver);
        Boolean classOpened = cardSectionToBeExpanded.getAttribute("class").contains("opened");

        if(!classOpened){
            cardSectionHeader.click();
        }






    }
}
