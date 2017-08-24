package com.heal.projects.patient.pages;

import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/20/2017.
 */
public class CreatePatientPage extends WebBase{
    TestData patientData = new TestData(TestData.PATIENT_SHEET);
    public static final String URL = "https://patient.qa.heal.com/create-patient";

    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oManageProfilesLabel = new CommonWebElement( "oManageProfilesLabel", "xpath=//*[text()='Manage Profiles']", oWebDriver );
    public CommonWebElement oPatientDetailsLabel = new CommonWebElement( "oPatientDetailsLabel", "xpath=//*[text()='Patient Details']", oWebDriver );
    public CommonWebElement oProfileImage = new CommonWebElement( "oProfileImage", "className=profile-image", oWebDriver );
    public CommonWebElement oPatientInfoLabel = new CommonWebElement( "oPatientInfoLabel", "xpath=//*[text()='Patient Info']", oWebDriver );
    public CommonWebElement oInsuranceLabel = new CommonWebElement( "oInsuranceLabel", "xpath=//*[text()='Insurance (Optional)']", oWebDriver );
    //Patient Info input
    public CommonWebElement oFirstNameInput = new CommonWebElement( "oFirstNameInput", "name=firstname", oWebDriver );
    public CommonWebElement oLastNameInput = new CommonWebElement( "oLastNameInput", "name=lastname", oWebDriver );
    public CommonWebElement oEmailInput = new CommonWebElement( "oFirstNameLabel", "name=username", oWebDriver );
    public CommonWebElement oPhoneNmbFlag = new CommonWebElement( "oPhoneNmbFlag", "className=selected-flag", oWebDriver );
    public CommonWebElement oPhoneNmbInput = new CommonWebElement( "oPhoneNmbInput", "name=phonenumber", oWebDriver );
    public CommonWebElement oRelationshipInput = new CommonWebElement( "oRelationshipInput", "name=relationship", oWebDriver );
    public CommonWebElement oDateOfBirthInput = new CommonWebElement( "oDateOfBirthInput", "name=dateOfBirth", oWebDriver );
    public CommonWebElement oGenderInput = new CommonWebElement( "oGenderInput", "name=gender", oWebDriver );

    //Insurance input
    public CommonWebElement oInsuranceProviderInput = new CommonWebElement( "oInsuranceProviderInput", "name=payer", oWebDriver );
    public CommonWebElement oMemberIdInput = new CommonWebElement( "oMemberIdInput", "name=memberId", oWebDriver );
    public CommonWebElement oGroupIdInput = new CommonWebElement( "oGroupIdInput", "name=groupId", oWebDriver );
    public CommonWebElement oSaveAndContinueBtn = new CommonWebElement( "oSaveAndContinueBtn", "xpath=//*[@type='submit']", oWebDriver );

    //Error messages
    public CommonWebElement oFirstnameError = new CommonWebElement( "oFirstnameError", "xpath=//*[@ng-messages='patientForm.firstname.$error']", oWebDriver );
    public CommonWebElement oLastnameError = new CommonWebElement( "oLastnameError", "xpath=//*[@ng-messages='patientForm.lastname.$error']", oWebDriver );
    public CommonWebElement oEmailAddressError = new CommonWebElement( "oEmailAddressError", "xpath=//*[@ng-messages='patientForm.email.$error']", oWebDriver );
    public CommonWebElement oPhoneNmbError = new CommonWebElement( "oPhoneNmbError", "xpath=//*xpath=//*[@ng-messages='patientForm.phonenumber.$error']", oWebDriver );
    public CommonWebElement oBirthDateError = new CommonWebElement( "oBirthDateError", "xpath=//*[@ng-messages='patientForm.dateOfBirth.$error']", oWebDriver );
    public CommonWebElement oRelationshipError = new CommonWebElement( "oRelationshipError", "xpath=//*[@ng-messages='patientForm.relationship.$error']", oWebDriver );
    public CommonWebElement oGenderError = new CommonWebElement( "oProviderGeha", "xpath=//*[@ng-messages='patientForm.gender.$error']", oWebDriver );

    //////////////////
    // Constructors //
    //////////////////

    @Parameters({ "url" })
    public CreatePatientPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public CreatePatientPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /**
     * Populates Patient info section from /profiles/create-patient page using data from Excel test data file
     */
    public void typePatientInfoFromExcel(TestData testData){
        this.oFirstNameInput.sendKeys(testData.sFirstname);
        this.oLastNameInput.sendKeys(testData.sLastname);
        this.oEmailInput.sendKeys(testData.sEmail);
        this.oPhoneNmbInput.sendKeys(testData.sPhoneNumber);
        this.oDateOfBirthInput.sendKeys(testData.sDateOfBirth);
        this.oRelationshipInput.selectByVisibleTextAngular(testData.sRelationship);
        this.oGenderInput.selectByVisibleTextAngular(testData.sGender);
    }

    /**
     * Types patient data from the Excel file in the input fields for creating/editing a profile
     */
    public void populateInsurance(String sInsurance, String sMemberId, String sGroupId){
        this.oInsuranceProviderInput.selectByVisibleTextAngular(sInsurance);
        this.oMemberIdInput.sendKeys(sMemberId);
        this.oGroupIdInput.sendKeys(sGroupId);
    }
}
