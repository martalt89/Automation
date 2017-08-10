package patient.pages;

import framework.web.CommonWebElement;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/13/2017.
 */
public class ManageProfilePage extends WebBase{
    public static final String URL = "https://patient.qa.heal.com/profiles";

    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oManageProfilesLabel = new CommonWebElement( "oManageProfilesLabel", "xpath=//*[contains(@class,'title')]", oWebDriver );
    public CommonWebElement oPatientDetailsLabel = new CommonWebElement( "oPatientDetailsLabel", "xpath=//*[text()='Patient Details']", oWebDriver );
    public CommonWebElement oProfileImage = new CommonWebElement( "oProfileImage", "className=profile-image", oWebDriver );
    public CommonWebElement oPatientInfoLabel = new CommonWebElement( "oPatientInfoLabel", "xpath=//*[text()='Patient Info']", oWebDriver );
    public CommonWebElement oInsuranceLabel = new CommonWebElement( "oInsuranceLabel", "xpath=//*[text()='Insurance (Optional)']", oWebDriver );
    public CommonWebElement oContinueButton = new CommonWebElement("oContinueButton", "xpath=//*[text()='Continue']", oWebDriver);
    public CommonWebElement oErrorBox = new CommonWebElement("oErrorBox", "xpath=//*[contains(@class,'error')]/div", oWebDriver);
    public CommonWebElement oSubtitile = new CommonWebElement("oSubtitile", "xpath=//h5", oWebDriver);
    public CommonWebElement oAddPatientbtn = new CommonWebElement("oAddPatientbtn", "xpath=//*[@class='create-patient-link']", oWebDriver);
    //Patient Info input
    public CommonWebElement oFirstNameInput = new CommonWebElement( "oFirstNameInput", "name=firstname", oWebDriver );
    public CommonWebElement oLastNameInput = new CommonWebElement( "oLastNameInput", "name=lastname", oWebDriver );
    public CommonWebElement oEmailInput = new CommonWebElement( "oFirstNameLabel", "name=email", oWebDriver );
    public CommonWebElement oPhoneNmbFlag = new CommonWebElement( "oPhoneNmbFlag", "className=selected-flag", oWebDriver );
    public CommonWebElement oPhoneNmbInput = new CommonWebElement( "oPhoneNmbInput", "name=phonenumber", oWebDriver );
    public CommonWebElement oRelationshipInput = new CommonWebElement( "oRelationshipInput", "name=relationship", oWebDriver );
    public CommonWebElement oDateOfBirthInput = new CommonWebElement( "oDateOfBirthInput", "name=dateOfBirth", oWebDriver );
    public CommonWebElement oGenderInput = new CommonWebElement( "oGenderInput", "name=gender", oWebDriver );
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
    public CommonWebElement oInsuranceProviderInput = new CommonWebElement( "oInsuranceProviderInput", "name=payer", oWebDriver );
    public CommonWebElement oMemberIdInput = new CommonWebElement( "oMemberIdInput", "name=memberId", oWebDriver );
    public CommonWebElement oGroupIdInput = new CommonWebElement( "oGroupIdInput", "name=groupId", oWebDriver );
    public CommonWebElement oSaveAndContinueBtn = new CommonWebElement( "oSaveAndContinueBtn", "xpath=//*[@type='submit']", oWebDriver );
    //Insurance provider dropdown items
    public CommonWebElement oProviderAetna = new CommonWebElement( "oProviderAetna", "xpath=//*[text()='aetna']", oWebDriver );
    public CommonWebElement oProviderAnthem = new CommonWebElement( "oProviderAnthem", "xpath=//*[text()='Anthem']", oWebDriver );
    public CommonWebElement oProviderBlueShield = new CommonWebElement( "oProviderBlueShield", "xpath=//*[text()='BlueShield']", oWebDriver );
    public CommonWebElement oProviderCigna = new CommonWebElement( "oProviderCigna", "xpath=//*[text()='Cigna']", oWebDriver );
    public CommonWebElement oProviderMeritainHealth = new CommonWebElement( "oProviderMeritainHealth", "xpath=//*[text()='Meritain Health (aetna)']", oWebDriver );
    public CommonWebElement oProviderNippon = new CommonWebElement( "oProviderNippon", "xpath=//*[text()='Nippon Life Benefits (aetna)']", oWebDriver );
    public CommonWebElement oProviderBlueCross = new CommonWebElement( "oProviderBlueCross", "xpath=//*[text()='Premera BlueCross']", oWebDriver );
    public CommonWebElement oProviderUmr = new CommonWebElement( "oProviderUmr", "xpath=//*[text()='UMR (United Healthcare)']", oWebDriver );
    public CommonWebElement oProviderNalc = new CommonWebElement( "oProviderNalc", "xpath=//*[text()='NALC (Cigna)']", oWebDriver );
    public CommonWebElement oProviderGeha = new CommonWebElement( "oProviderGeha", "xpath=//*[text()='GEHA']", oWebDriver );
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
    public ManageProfilePage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public ManageProfilePage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////

    public void clickPatientByText(String sText)
    {
        CommonWebElement oPatient = new CommonWebElement( "oPatient", "xpath=//*[text()='"+sText+"']", oWebDriver );
        oPatient.click();
    }

    public CommonWebElement getElementByText(String sText)
    {
        return new CommonWebElement( "oElement", "xpath=//*[text()='"+sText+"']", oWebDriver );

    }
}
