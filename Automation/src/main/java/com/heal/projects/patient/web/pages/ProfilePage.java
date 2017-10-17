package com.heal.projects.patient.web.pages;

import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;

import java.util.List;

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

    //Insurance input
    public CommonWebElement oInsuranceProviderInput = new CommonWebElement( "oInsuranceProviderInput", "xpath=//*[@name='payer']", oWebDriver );
    public CommonWebElement oMemberIdInput = new CommonWebElement( "oMemberIdInput", "xpath=//*[@name='memberId']", oWebDriver );
    public CommonWebElement oGroupIdInput = new CommonWebElement( "oGroupIdInput", "xpath=//*[@name='groupId']", oWebDriver );
    public CommonWebElement oSaveAndContinueBtn = new CommonWebElement( "oSaveAndContinueBtn", "xpath=//*[@type='submit']", oWebDriver );

    //Error messages

    public CommonWebElement oPayerOfflineMsg=new CommonWebElement("oPayerOfflineMsg","xpath=//*[@class='md-padding']",oWebDriver);
    public CommonWebElement oPayerOfflineDialogOkButton = new CommonWebElement("oPayerOfflineDialogOkButton","xpath=//span[contains(text(),'OK')]",oWebDriver);
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
        CommonWebElement oPatient = new CommonWebElement( "oPatient", "xpath=//*[text()='"+sText+"']", oWebDriver );
        List<WebElement> list = oWebDriver.findElements(oPatient.getBy());
        if (list.size() > 1) {
             oPatient = new CommonWebElement( "oPatient", "xpath=(//*[text()='"+sText+"'])[2]", oWebDriver );
        }else
             oPatient = new CommonWebElement( "oPatient", "xpath=(//*[text()='"+sText+"'])[1]", oWebDriver );

        oPatient.click();
    }

    /**
     * Gets patient object from the patients list by providing patient name
     * @param sText Patient firstname
     * @return Patient object
     */
    public CommonWebElement getPatientByText(String sText)
    {
        return new CommonWebElement( "oElement", "xpath=//*[contains(text(),'"+sText+"')]", oWebDriver );

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
