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

    public CommonWebElement oManageProfilesLabel = new CommonWebElement( "oManageProfilesLabel", "xpath=//*[text()='Manage Profiles']", oWebDriver );
    public CommonWebElement oPatientDetailsLabel = new CommonWebElement( "oPatientDetailsLabel", "xpath=//*[text()='Patient Details']", oWebDriver );
    public CommonWebElement oProfileImage = new CommonWebElement( "oProfileImage", "className=profile-image", oWebDriver );
    public CommonWebElement oPatientInfoLabel = new CommonWebElement( "oPatientInfoLabel", "xpath=//*[text()='Patient Info']", oWebDriver );
    public CommonWebElement oInsuranceLabel = new CommonWebElement( "oInsuranceLabel", "xpath=//*[text()='Insurance (Optional)']", oWebDriver );
    //Patient Info input
    public CommonWebElement oFirstNameInput = new CommonWebElement( "oFirstNameInput", "name=firstname", oWebDriver );
    public CommonWebElement oLastNameNameInput = new CommonWebElement( "oLastNameInput", "name=lastname", oWebDriver );
    public CommonWebElement oEmailInput = new CommonWebElement( "oFirstNameLabel", "name=username", oWebDriver );
    public CommonWebElement oPhoneNmbFlag = new CommonWebElement( "oPhoneNmbFlag", "className=selected-flag", oWebDriver );
    public CommonWebElement oPhoneNmbInput = new CommonWebElement( "oPhoneNmbInput", "name=phonenumber", oWebDriver );
    public CommonWebElement oDateOfBirthInput = new CommonWebElement( "oDateOfBirthInput", "name=dateOfBirth", oWebDriver );
    public CommonWebElement oGenderInput = new CommonWebElement( "oGenderInput", "name=gender", oWebDriver );
    //Insurance input
    public CommonWebElement oInsuranceProviderInput = new CommonWebElement( "oInsuranceProviderInput", "name=payer", oWebDriver );
    public CommonWebElement oMemberIdInput = new CommonWebElement( "oMemberIdInput", "name=memberId", oWebDriver );
    public CommonWebElement oGroupIdInput = new CommonWebElement( "oGroupIdInput", "name=groupId", oWebDriver );
    public CommonWebElement oSaveAndContinueBtn = new CommonWebElement( "oSaveAndContinueBtn", "xpath=//*[@type='submit']", oWebDriver );

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
}
