package patient.pages;

import framework.web.CommonWebElement;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/13/2017.
 */
public class ChooseProfilePage extends WebBase {
    public static final String URL = "https://patient.qa.heal.com/profiles";

    ///////////////////
    // Page Elements //
    ///////////////////

    //TODO: Also add objects for selected/unselected profile(s) icon(s)
    public CommonWebElement oManageProfilesLabel = new CommonWebElement( "oManageProfilesLabel", "xpath=//*[text()='Manage Profiles']", oWebDriver );
    public CommonWebElement oChooseProfileLabel = new CommonWebElement( "oChooseProfileLabel", "xpath=//*[text()='Choose Profile']", oWebDriver );
    public CommonWebElement oProfileName = new CommonWebElement( "oProfileName", "xpath=//*[@id='choose-profile']//h[text()='Mihai']", oWebDriver );
    /*
    Note1: on below object - oMainProfileLink
    1. By oMainProfileLink i mean to the user that created the account, in my case Mihai. It's the first profile that it's displayed in Choose profile section
    2. It is currently hardcoded, but after we decide where we will keep some account data, I will get that value from there
     */
    public CommonWebElement oMainProfileLink = new CommonWebElement( "oMainProfileLink", "xpath=//*[@id='choose-profile']//span[text()='Mihai']", oWebDriver );
    public CommonWebElement oAddPatientLogo = new CommonWebElement( "oAddPatientLogo", "xpath=//*[@id='ic_add_circle_24px_cache150']", oWebDriver );
    public CommonWebElement oAddPatientLabel = new CommonWebElement( "oAddPatientLabel", "xpath=//*[text()='Add Patient']", oWebDriver );
    public CommonWebElement oContinueBtn = new CommonWebElement( "oContinueBtn", "xpath=//*[@id='manage-profiles']//button", oWebDriver );

    //////////////////
    // Constructors //
    //////////////////

    @Parameters({ "url" })
    public ChooseProfilePage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public ChooseProfilePage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////

    public void selectMainProfile()
    {
        //Note2: this method can be optimized to select a given profile as argument
        this.oMainProfileLink.click();
        this.oContinueBtn.click();
    }
}