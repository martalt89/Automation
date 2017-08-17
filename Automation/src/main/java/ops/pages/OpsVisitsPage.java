package ops.pages;

import framework.exception.CommonException;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;

public class OpsVisitsPage extends WebBase {

    public static final String URL = "https://"+ baseUrl +"/visits";

    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oPageTitle = new CommonWebElement("oPageTitle", "xpath=//*[contains(@class,'market-name___tuNGU')]", oWebDriver);
    public CommonWebElement oAddVisitBtn = new CommonWebElement("oAddVisitBtn", "xpath=//*[contains(@class,'btn btn-default add-visit___pK_7n')]", oWebDriver);
    public CommonWebElement oSettingsBtn = new CommonWebElement("oSettingsBtn", "xpath=//*[contains(@class,'btn btn-default') and text()='Settings']", oWebDriver);
    public CommonWebElement oNextBtn = new CommonWebElement("oNextBtn", "xpath=//*[contains(@class,'griddle-next') and text()='Next']", oWebDriver);

    //////////////////
    // Constructors //
    //////////////////
    public OpsVisitsPage(WebDriver oTargetDriver){
        super(oTargetDriver, URL);
    }

    public OpsVisitsPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////


}
