package ops.pages;

import framework.web.WebBase;
import framework.exception.CommonException;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;

/**
 *  Created by adrian.rosu on 21/08/2017.
 */
public class CreateVisitPage extends WebBase {
    public static final String URL = "https://"+ baseUrl +"/visits";

    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oPageTitle = new CommonWebElement("oPageTitle", "xpath=//h1[text()='Create Visit']", oWebDriver);

    // Select / create user card

        // title
    public CommonWebElement oSelectUserTitle = new CommonWebElement("oSelectUserTitle", "xpath=//h2[text()='Select User']", oWebDriver);
    public CommonWebElement oCreateUserTitle = new CommonWebElement("oCreateUserTitle", "xpath=//*[@id='react-create-visit-component']//text()[. = 'Create User']", oWebDriver);

        //labels
    public CommonWebElement oFullNameLabel = new CommonWebElement("oFullNameLabel", "xpath=//label[text()='Full Name']", oWebDriver);
    public CommonWebElement oFirstNameLabel = new CommonWebElement("oFirstNameLabel", "xpath=//label[text()='First Name']", oWebDriver);
    public CommonWebElement oLastNameLabel = new CommonWebElement("oLastNameLabel", "xpath=//label[text()='Last Name']", oWebDriver);
    public CommonWebElement oPhoneLabel = new CommonWebElement("oPhoneLabel", "xpath=//label[text()='Phone']", oWebDriver);
    public CommonWebElement oEmailLabel = new CommonWebElement("oEmailLabel", "xpath=//label[text()='Email']", oWebDriver);
    public CommonWebElement oZipCodeLabel = new CommonWebElement("oZipCodeLabel", "xpath=//label[text()='Zipcode']", oWebDriver);

        // text fields
    public CommonWebElement oEnterKeywordField = new CommonWebElement("oEnterKeywordField", "xpath=//*[contains(@class,'form-control tt-input')]", oWebDriver);
    public CommonWebElement oFirstNameField = new CommonWebElement("oFirstNameField", "xpath=//*[contains(@placeholder,'Enter First Name')]", oWebDriver);
    public CommonWebElement oLastNameField = new CommonWebElement("oLastNameField", "xpath=//*[contains(@placeholder,'Enter Last Name')]", oWebDriver);
    public CommonWebElement oPhoneField = new CommonWebElement("oPhoneField", "xpath=//*[contains(@placeholder,'Enter Phone Number')]", oWebDriver);
    public CommonWebElement oEmailField = new CommonWebElement("oEmailField", "xpath=//*[contains(@placeholder,'Enter Email')]", oWebDriver);
    public CommonWebElement oZipCodeField = new CommonWebElement("oZipCodeField", "xpath=//*[contains(@placeholder,'Enter Zipcode')]", oWebDriver);

        // buttons
    public CommonWebElement oSelectUserBtn = new CommonWebElement("oSelectUserBtn", "xpath=//*[contains(@class,'no-data') and text()='Select User']", oWebDriver);
    public CommonWebElement oCreateUserBtn = new CommonWebElement("oCreateUserBtn", "xpath=//button[text()='Create User']", oWebDriver);

    // Select / Create patient card

        // title
    public CommonWebElement oSelectPatientTitle = new CommonWebElement("oSelectPatientTitle", "xpath=//h2[text()='Select Patient']", oWebDriver);
    public CommonWebElement oCreatePatientTitle = new CommonWebElement("oCreatePatientTitle", "xpath=//*[@id='react-create-visit-component']//text()[. = 'Create Patient']", oWebDriver);
    public CommonWebElement oInsuranceInfoTitle = new CommonWebElement("oInsuranceInfoTitle", "xpath=//*[@id='react-create-visit-component']//text()[. = 'Insurance Info']", oWebDriver);
    public CommonWebElement oEditPatientTitle = new CommonWebElement("oEditPatientTitle", "xpath=//h2[text()='Edit' and text()=' Patient']", oWebDriver);

        // labels
    public CommonWebElement oDateOfBirthLabel = new CommonWebElement("oDateOfBirthLabel", "xpath=//label[text()='Date Of Birth']", oWebDriver);
    public CommonWebElement oRelationshipLabel = new CommonWebElement("oRelationshipLabel", "xpath=//label[text()='Patient Relationship']", oWebDriver);
    public CommonWebElement oGenderLabel = new CommonWebElement("oGenderLabel", "xpath=//label[text()='Gender']", oWebDriver);
    public CommonWebElement oPayerLabel = new CommonWebElement("oPayerLabel", "xpath=//label[text()='Payer']", oWebDriver);
    public CommonWebElement oMemberIdLabel = new CommonWebElement("oMemberIdLabel", "xpath=//label[text()='Member Id']", oWebDriver);
    public CommonWebElement oGroupIdLabel = new CommonWebElement("oGroupIdLabel", "xpath=//label[text()='Group Id']", oWebDriver);
    public CommonWebElement oMaleLabel = new CommonWebElement("oMaleLabel", "xpath=//label[text()='Male']", oWebDriver);
    public CommonWebElement oFemaleLabel = new CommonWebElement("oFemaleLabel", "xpath=//label[text()='Female']", oWebDriver);
    public CommonWebElement oOtherLabel = new CommonWebElement("oOtherLabel", "xpath=//label[text()='Other']", oWebDriver);

        // fields
    public CommonWebElement oDateOfBirdField = new CommonWebElement("oDateOfBirdField", "xpath=//*[contains(@placeholder,'Enter Birth Date')]", oWebDriver);
    public CommonWebElement oMemberIdField = new CommonWebElement("oMemberIdField", "xpath=//*[contains(@placeholder,'Enter Member Id')]", oWebDriver);
    public CommonWebElement oGroupIdField = new CommonWebElement("oGroupIdField", "xpath=//*[contains(@placeholder,'Enter Group Id')]", oWebDriver);

        // drop-down menu
    public CommonWebElement oRelationShipDropDown = new CommonWebElement("oRelationShipDropDown", "xpath=//div[6]/*/select[@class='form-control']", oWebDriver);
    public CommonWebElement oPayerDropDown = new CommonWebElement("oPayerDropDown", "xpath=//div[10]/*/select[@class='form-control']", oWebDriver);

        // radio buttons
    public CommonWebElement oMaleGenderOption = new CommonWebElement("oMaleGenderOption", "xpath=//*[@class='radio-group']/input[1]", oWebDriver);
    public CommonWebElement oFemaleGenderOption = new CommonWebElement("oFemaleGenderOption", "xpath=//*[@class='radio-group']/input[2]", oWebDriver);
    public CommonWebElement oOtherGenderOption = new CommonWebElement("oOtherGenderOption", "xpath=//*[@class='radio-group']/input[3]", oWebDriver);

        // buttons
    public CommonWebElement oSelectPatientProfile = new CommonWebElement("oSelectPatientProfile", "xpath=//*[@id='react-create-visit-component']//*[@class='patient-name']", oWebDriver);
    public CommonWebElement oCreatePatientBtn = new CommonWebElement("oCreateUserBtn", "xpath=//button[text()='Create Patient']", oWebDriver);
    public CommonWebElement oChooseDifferentPatientBtn = new CommonWebElement("oChooseDifferentPatientBtn", "xpath=//button[text()='Choose Different Patient']", oWebDriver);
    public CommonWebElement oSavePatientBtn = new CommonWebElement("oCreateUserBtn", "xpath=//button[text()='Save Patient']", oWebDriver);

    // Select / Create address card


    //////////////////
    // Constructors //
    //////////////////
    public CreateVisitPage(WebDriver oTargetDriver){
        super(oTargetDriver, URL);
    }

    public CreateVisitPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////


}
