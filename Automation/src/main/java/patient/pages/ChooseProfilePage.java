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
    public CommonWebElement oProfileName = new CommonWebElement( "oProfileName", "xpath=//*[@id='choose-profile']//h[text()='Mihai']", oWebDriver );
    public CommonWebElement oManageProfilesLabel = new CommonWebElement( "oManageProfilesLabel", "xpath=//*[text()='Manage Profiles']", oWebDriver );
    public CommonWebElement oChooseProfileLabel = new CommonWebElement( "oChooseProfileLabel", "xpath=//*[text()='Choose Profile']", oWebDriver );
    public CommonWebElement oMainProfileLink = new CommonWebElement( "oMainProfileLink", "xpath=//*[@id='choose-profile']//span[1]", oWebDriver );
    public CommonWebElement oAddPatientLogo = new CommonWebElement( "oAddPatientLogo", "className='create-patient-link", oWebDriver );
    public CommonWebElement oAddPatientLabel = new CommonWebElement( "oAddPatientLabel", "xpath=//*[text()='Add Patient']", oWebDriver );
    public CommonWebElement oContinueBtn = new CommonWebElement( "oContinueBtn", "xpath=//*[text()='Continue']", oWebDriver );

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