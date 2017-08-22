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

        // title
    public CommonWebElement oSelectAddressTitle = new CommonWebElement("oSelectAddressTitle", "xpath=//h2[text()='Select Address']", oWebDriver);
    public CommonWebElement oCreateAddressTitle = new CommonWebElement("oCreatePatientTitle", "xpath=//*[@id='react-create-visit-component']//text()[. = 'Create Address']", oWebDriver);

        // buttons
    public CommonWebElement oSelectFirstAddressBtn = new CommonWebElement("oSelectFirstAddressBtn", "xpath=//button[@class='address-option btn btn-default btn-block'][1]", oWebDriver);
    public CommonWebElement oCancelBtn = new CommonWebElement("oCancelBtn", "xpath=//button[text()='Cancel']", oWebDriver);
    public CommonWebElement oSaveAddressBtn = new CommonWebElement("oSaveAddressBtn", "xpath=//button[text()='Save Address']", oWebDriver);

        //labels
    public CommonWebElement oSearchAddressLabel = new CommonWebElement("oSearchAddressLabel", "xpath=//label[text()='Search Address']", oWebDriver);
    public CommonWebElement oAddressTypeLabel = new CommonWebElement("oAddressTypeLabel", "xpath=//label[text()='Address Type']", oWebDriver);
    public CommonWebElement oAddress1Label = new CommonWebElement("oAddress1Label", "xpath=//label[text()='Address 1']", oWebDriver);
    public CommonWebElement oAddress2Label = new CommonWebElement("oAddress2Label", "xpath=//label[text()='Address 2']", oWebDriver);
    public CommonWebElement oCityLabel = new CommonWebElement("oCityLabel", "xpath=//label[text()='City']", oWebDriver);
    public CommonWebElement oStateLabel = new CommonWebElement("oStateLabel", "xpath=//label[text()='State']", oWebDriver);
    public CommonWebElement oCountryLabel = new CommonWebElement("oCountryLabel", "xpath=//label[text()='Country']", oWebDriver);
    public CommonWebElement oEstablishmentLabel = new CommonWebElement("oEstablishmentLabel", "xpath=//label[text()='Establishment']", oWebDriver);
    public CommonWebElement oInstructionsLabel = new CommonWebElement("oInstructionsLabel", "xpath=//label[text()='Instructions']", oWebDriver);

        // fields
    public CommonWebElement oSearchAddressField = new CommonWebElement("oSearchAddressField", "xpath=//*[contains(@placeholder,'Search Address')]", oWebDriver);
    public CommonWebElement oAddress1Field = new CommonWebElement("oAddress1Field", "xpath=//*[contains(@placeholder,'Enter Address 1')]", oWebDriver);
    public CommonWebElement oAddress2Field = new CommonWebElement("oAddress2Field", "xpath=//*[contains(@placeholder,'Enter Address (Unit, Apt#, etc.)')]", oWebDriver);
    public CommonWebElement oCityField = new CommonWebElement("oCityField", "xpath=//*[contains(@placeholder,'Enter City')]", oWebDriver);
    public CommonWebElement oStateField = new CommonWebElement("oStateField", "xpath=//*[contains(@placeholder,'Enter State')]", oWebDriver);
    public CommonWebElement oCountryField = new CommonWebElement("oCountryField", "xpath=//*[contains(@placeholder,'Enter Country')]", oWebDriver);
    public CommonWebElement oEstablishmentField = new CommonWebElement("oEstablishmentField", "xpath=//*[contains(@placeholder,'Enter Establishment')]", oWebDriver);
    public CommonWebElement oInstructionsField = new CommonWebElement("oInstructionsField", "xpath=//*[contains(@placeholder,'Enter Instructions')]", oWebDriver);

        // radio buttons
    public CommonWebElement oHomeOption = new CommonWebElement("oHomeOption", "xpath=//*[@class='radio-group']/input[1]", oWebDriver);
    public CommonWebElement oWorkOption = new CommonWebElement("oWorkOption", "xpath=//*[@class='radio-group']/input[2]", oWebDriver);
    public CommonWebElement oOtherOption = new CommonWebElement("oOtherOption", "xpath=//*[@class='radio-group']/input[3]", oWebDriver);

    // Select / Create payment card

        // title
    public CommonWebElement oSelectPaymentTitle = new CommonWebElement("oSelectPaymentTitle", "xpath=//h2[text()='Select Payment']", oWebDriver);
    public CommonWebElement oCreatePaymentTitle = new CommonWebElement("oCreatePatientTitle", "xpath=//*[@id='react-create-visit-component']//text()[. = 'Create Payment']", oWebDriver);

        // buttons
    public CommonWebElement oNewPaymentBtn = new CommonWebElement("oNewPaymentBtn", "xpath=//button[text()='New Payment']", oWebDriver);
    public CommonWebElement oCreatePaymentBtn = new CommonWebElement("oCreatePaymentBtn", "xpath=//button[text()='Create Payment']", oWebDriver);

        // radio buttons
    public CommonWebElement oFirstCardOption = new CommonWebElement("oFirstCardOption", "xpath=//*[@class='payment-selection'][1]//input", oWebDriver);

        // fields
    public CommonWebElement oCardNumberField = new CommonWebElement("oCardNumberField", "xpath=//*[contains(@placeholder,'Enter Card Number')]", oWebDriver);
    public CommonWebElement oExpDateField = new CommonWebElement("oExpDateField", "xpath=//*[contains(@placeholder,'Enter Expiration Date')]", oWebDriver);
    public CommonWebElement oCVCField = new CommonWebElement("oCVCField", "xpath=//*[contains(@placeholder,'Enter CVC')]", oWebDriver);

        //labels
    public CommonWebElement oCardNumberLabel = new CommonWebElement("oCardNumberLabel", "xpath=//label[text()='Card Number']", oWebDriver);
    public CommonWebElement oExpDateLabel = new CommonWebElement("oExpDateLabel", "xpath=//label[text()='Expiration Date']", oWebDriver);
    public CommonWebElement oCVCLabel = new CommonWebElement("oCVCLabel", "xpath=//label[text()='Enter CVC']", oWebDriver);

    // Select Visit Service card

        // title
    public CommonWebElement oSelectVisitServiceTitle = new CommonWebElement("oSelectVisitServiceTitle", "xpath=//h2[text()='Select Visit Service']", oWebDriver);
    public CommonWebElement oEnterVisitDetailsTitle = new CommonWebElement("oEnterVisitDetailsTitle", "xpath=//h2[text()='Enter Visit Details']", oWebDriver);

        // service buttons
    public CommonWebElement oSickAdultService = new CommonWebElement("oSickAdultService", "xpath=//*[contains(@class,'patient-name') and text()='sick adult']", oWebDriver);
    public CommonWebElement oANPSAdultService = new CommonWebElement("oANPSAdultService", "xpath=//*[contains(@class,'patient-name') and text()='anps adult']", oWebDriver);
    public CommonWebElement oPCPService = new CommonWebElement("oPCPService", "xpath=//*[contains(@class,'patient-name') and text()='pcp']", oWebDriver);

        // fields
    public CommonWebElement oSymptomsField = new CommonWebElement("oSymptomsField", "xpath=//*[contains(@placeholder,'Enter Symptoms')]", oWebDriver);
    public CommonWebElement oTimeSlotDropDown = new CommonWebElement("oRelationShipDropDown", "xpath=//select[@class='form-control']/option']", oWebDriver);

    // Visit Price Summary card

        // title
    public CommonWebElement oVisitPriceTitle = new CommonWebElement("oVisitPriceTitle", "xpath=//h2[text()='Visit Price Summary']", oWebDriver);

        // labels
    public CommonWebElement oNoPromoLabel = new CommonWebElement("oNoPromoLabel", "xpath=//div[text()='No Promo Code.']", oWebDriver);
    public CommonWebElement oVisitPriceLabel = new CommonWebElement("oVisitPriceLabel", "xpath=//div[text()='Visit Price']", oWebDriver);
    public CommonWebElement oDiscountLabel = new CommonWebElement("oDiscountLabel", "xpath=//div[text()='Discount']", oWebDriver);
    public CommonWebElement oTotalFeeLabel = new CommonWebElement("oTotalFeeLabel", "xpath=//div[text()='Your visit has a flat fee of']", oWebDriver);

    public CommonWebElement oPrice = new CommonWebElement("oPrice", "xpath=//*[contains(@class,'visit-details')]/div[2]/div[2]", oWebDriver);
    public CommonWebElement oDiscount = new CommonWebElement("oDiscount", "xpath=//*[contains(@class,'visit-details')]/div[3]/div[2]", oWebDriver);
    public CommonWebElement oTotal = new CommonWebElement("oTotal", "xpath=//*[contains(@class,'visit-details')]/div[4]/div[2]", oWebDriver);

        // buttons
    public CommonWebElement oApplyPromoBtn = new CommonWebElement("oApplyPromoBtn", "xpath=//button[text()='Apply Promo Code']", oWebDriver);
    public CommonWebElement oBookVisitBtn = new CommonWebElement("oBookVisitBtn", "xpath=//button[text()='Book Visit']", oWebDriver);

        // fields
    public CommonWebElement oPromoCodeField = new CommonWebElement("oPromoCodeField", "xpath=//*[contains(@placeholder,'Promo Code')]", oWebDriver);

    // LEFT MENU BUTTONS
    public CommonWebElement oSelectUserMenu = new CommonWebElement("oSelectUserMenu", "xpath=//*[contains(@class,'no-data') and text()='Select User']", oWebDriver);
    public CommonWebElement oSelectPatientMenu = new CommonWebElement("oSelectPatientMenu", "xpath=//*[contains(@class,'no-data') and text()='Select Patient']", oWebDriver);
    public CommonWebElement oSelectAddressMenu = new CommonWebElement("oSelectAddressMenu", "xpath=//*[contains(@class,'no-data') and text()='Select Address']", oWebDriver);
    public CommonWebElement oAddVisitDetailsMenu = new CommonWebElement("oAddVisitDetailsMenu", "xpath=//*[contains(@class,'no-data') and text()='Add Visit Details']", oWebDriver);
    public CommonWebElement oSelectPaymentMenu = new CommonWebElement("oSelectPaymentMenu", "xpath=//*[contains(@class,'no-data') and text()='Select Payment']", oWebDriver);
    public CommonWebElement oVisitSumaryMenu = new CommonWebElement("oVisitSumaryMenu", "xpath=//*[contains(@class,'no-data') and text()='Visit Summary']", oWebDriver);


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
    /**
     * Creates new user when adding a visit.
     *
     * @param sFirstName (String) - First name to be used.
     * @param sLastName (String) - Last name to be used.
     * @param sEmail (String) - User's email address.
     * @param sPhone (String) - User's phone number.
     * @param sZipCode (String) - Zip Code to be used.
     */
    public void createUser(String sFirstName, String sLastName, String sPhone, String sEmail, String sZipCode)
    {
        this.oFirstNameField.sendKeys(sFirstName);
        this.oLastNameField.sendKeys(sLastName);
        this.oPhoneField.sendKeys(sPhone);
        this.oEmailField.sendKeys(sEmail);
        this.oZipCodeField.sendKeys(sZipCode);
        this.oCreateUserBtn.click();
    }
    // todo methods for: create patient, create address, add visit details, add payments and visit summary

}
