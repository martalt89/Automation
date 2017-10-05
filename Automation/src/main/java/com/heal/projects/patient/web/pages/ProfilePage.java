package com.heal.projects.patient.web.pages;

import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/13/2017.
 */
public class ProfilePage extends WebBase{
    public static final String URL = "https://patient" + baseUrl + "/profiles";

    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oPageTitle = new CommonWebElement( "oPageTitle", "xpath=//*[contains(@class,'title')]", oWebDriver );
    public CommonWebElement oPatientDetailsLabel = new CommonWebElement( "oPatientDetailsLabel", "xpath=//*[text()='Patient Details']", oWebDriver );
    public CommonWebElement oProfileImage = new CommonWebElement( "oProfileImage", "className=profile-image", oWebDriver );
    public CommonWebElement oPatientInfoLabel = new CommonWebElement( "oPatientInfoLabel", "xpath=//*[text()='Patient Info']", oWebDriver );
    public CommonWebElement oInsuranceLabel = new CommonWebElement( "oInsuranceLabel", "xpath=//*[text()='Insurance (Optional)']", oWebDriver );
    public CommonWebElement oContinueButton = new CommonWebElement("oContinueButton", "xpath=//*[text()='Continue']", oWebDriver);
    public CommonWebElement oErrorBox = new CommonWebElement("oErrorBox", "xpath=//*[contains(@class,'error')]/div", oWebDriver);
    public CommonWebElement oSubtitle = new CommonWebElement("oSubtitle", "xpath=//h5", oWebDriver);
    public CommonWebElement oAddPatientbtn = new CommonWebElement("oAddPatientbtn", "xpath=//*[@class='create-patient-link']", oWebDriver);
    //Patient Info input
    public CommonWebElement oFirstNameInput = new CommonWebElement( "oFirstNameInput", "xpath=//*[@name='firstname']", oWebDriver );
    public CommonWebElement oLastNameInput = new CommonWebElement( "oLastNameInput", "xpath=//*[@name='lastname']", oWebDriver );
    public CommonWebElement oEmailInput = new CommonWebElement( "oFirstNameLabel", "xpath=//*[@name='email']", oWebDriver );
    public CommonWebElement oPhoneNmbFlag = new CommonWebElement( "oPhoneNmbFlag", "className=selected-flag", oWebDriver );
    public CommonWebElement oPhoneNmbInput = new CommonWebElement( "oPhoneNmbInput", "xpath=//*[@name='phonenumber']", oWebDriver );
    public CommonWebElement oRelationshipInput = new CommonWebElement( "oRelationshipInput", "xpath=//*[@name='relationship']", oWebDriver );
    public CommonWebElement oDateOfBirthInput = new CommonWebElement( "oDateOfBirthInput", "xpath=//*[@name='dateOfBirth']", oWebDriver );
    public CommonWebElement oGenderInput = new CommonWebElement( "oGenderInput", "xpath=//*[@name='gender']", oWebDriver );
    //Relationship dropdown items
    public CommonWebElement oRelationshipMenuSpouse = new CommonWebElement( "oRelationshipMenuSpouse", "xpath=//*[text()='Spouse']", oWebDriver );
    public CommonWebElement oRelationshipMenuPartner = new CommonWebElement( "oRelationshipMenuPartner", "xpath=//*[text()='Partner']", oWebDriver );
    public CommonWebElement oRelationshipMenuGrandparent = new CommonWebElement( "oRelationshipMenuGrandparent", "xpath=//*[text()='Grandparent']", oWebDriver );
    public CommonWebElement oRelationshipMenuGrandchild = new CommonWebElement( "oRelationshipMenuGrandchild", "xpath=//*[text()='Grandchild']", oWebDriver );
    public CommonWebElement oRelationshipMenuChild = new CommonWebElement( "oRelationshipMenuChild", "xpath=//*[text()='Child']", oWebDriver );
    public CommonWebElement oRelationshipMenuParent = new CommonWebElement( "oRelationshipMenuParent", "xpath=//*[text()='Parent']", oWebDriver );
    public CommonWebElement oRelationshipMenuSibling = new CommonWebElement( "oRelationshipMenuSibling", "xpath=//*[text()='Sibling']", oWebDriver );
    public CommonWebElement oRelationshipMenuOther = new CommonWebElement( "oRelationshipMenuOther", "xpath=//*[text()='Other']", oWebDriver );
    public CommonWebElement oRelationshipMenuYou = new CommonWebElement( "oRelationshipMenuYou", "xpath=//*[text()='You']", oWebDriver );
    public CommonWebElement oRelationshipMenuFriend = new CommonWebElement( "oRelationshipMenuFriend", "xpath=//*[text()='Friend']", oWebDriver );
    public CommonWebElement oRelationshipMenuCoworker = new CommonWebElement( "oRelationshipMenuCoworker", "xpath=//*[text()='Coworker']", oWebDriver );
    public CommonWebElement oRelationshipMenuAssistedLivingResident = new CommonWebElement( "oRelationshipMenuAssistedLivingResident",
            "xpath=//*[text()='Assisted Living Resident']", oWebDriver );
    public CommonWebElement oRelationshipMenuSkilledNursingFacility = new CommonWebElement( "oRelationshipMenuSkilledNursingFacility",
            "xpath=//*[text()='Skilled Nursing Facilityt']", oWebDriver );
    //Gender dropdown items
    public CommonWebElement oGenderMale = new CommonWebElement( "oGenderMale", "xpath=//*[text()='Male']", oWebDriver );
    public CommonWebElement oGenderFemale = new CommonWebElement( "oGenderFemale", "xpath=//*[text()='Female']", oWebDriver );
    public CommonWebElement oGenderOther = new CommonWebElement( "oGenderOther", "xpath=//*[text()='Other']", oWebDriver );
    //Insurance input
    public CommonWebElement oInsuranceProviderInput = new CommonWebElement( "oInsuranceProviderInput", "xpath=//*[@name='payer']", oWebDriver );
    public CommonWebElement oMemberIdInput = new CommonWebElement( "oMemberIdInput", "xpath=//*[@name='memberId']", oWebDriver );
    public CommonWebElement oGroupIdInput = new CommonWebElement( "oGroupIdInput", "xpath=//*[@name='groupId']", oWebDriver );
    public CommonWebElement oSaveAndContinueBtn = new CommonWebElement( "oSaveAndContinueBtn", "xpath=//*[@type='submit']", oWebDriver );

    //Error messages
    public CommonWebElement oFirstnameError = new CommonWebElement( "oFirstnameError", "xpath=//*[@ng-messages='patientForm.firstname.$error']", oWebDriver );
    public CommonWebElement oLastnameError = new CommonWebElement( "oLastnameError", "xpath=//*[@ng-messages='patientForm.lastname.$error']", oWebDriver );
    public CommonWebElement oEmailAddressError = new CommonWebElement( "oEmailAddressError", "xpath=//*[@ng-messages='patientForm.email.$error']", oWebDriver );
    public CommonWebElement oPhoneNmbError = new CommonWebElement( "oPhoneNmbError", "xpath=//*xpath=//*[@ng-messages='patientForm.phonenumber.$error']", oWebDriver );
    public CommonWebElement oBirthDateError = new CommonWebElement( "oBirthDateError", "xpath=//*[@ng-messages='patientForm.dateOfBirth.$error']", oWebDriver );
    public CommonWebElement oRelationshipError = new CommonWebElement( "oRelationshipError", "xpath=//*[@ng-messages='patientForm.relationship.$error']", oWebDriver );
    public CommonWebElement oGenderError = new CommonWebElement( "oProviderGeha", "xpath=//*[@ng-messages='patientForm.gender.$error']", oWebDriver );
    public CommonWebElement oPayerOfflineMsg=new CommonWebElement("oPayerOfflineMsg","xpath=//*[@class=\"md-padding\"]",oWebDriver);
    public CommonWebElement oPayerOfflineDialogOkButton = new CommonWebElement("oPayerOfflineDialogOkButton","xpath=//md-dialog-content//span[contains(text(),'OK')]",oWebDriver);
    //////////////////
    // Constructors //
    //////////////////

    @Parameters({ "url" })
    public ProfilePage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public ProfilePage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////

    /**
     * Clicks an element that has the provided text
     * @param sText Patient firstname
     */
    public void clickPatientByText(String sText)
    {
        CommonWebElement oPatient = new CommonWebElement( "oPatient", "xpath=(//*[text()='"+sText+"'])[1]", oWebDriver );
        oPatient.click();
    }

    /**
     * Gets patient object from the patients list by providing patient name
     * @param sText Patient firstname
     * @return Patient object
     */
    public CommonWebElement getPatientByText(String sText)
    {
        return new CommonWebElement( "oElement", "xpath=//*[text()='"+sText+"']", oWebDriver );

    }

    /**
     * Types patient data from the Excel file in the input fields for creating/editing a profile
     */
    public void typePatientDataFromExcel(TestData testData){
        this.oFirstNameInput.sendKeys(testData.sFirstname);
        this.oLastNameInput.sendKeys(testData.sLastname);
        this.oEmailInput.sendKeys(testData.sEmail);
        this.oPhoneNmbInput.sendKeys(testData.sPhoneNumber);
        this.oDateOfBirthInput.sendKeys(testData.sDateOfBirth);
        this.oRelationshipInput.selectByVisibleTextAngular(testData.sRelationship);
        this.oGenderInput.selectByVisibleTextAngular(testData.sGender);
    }


}
