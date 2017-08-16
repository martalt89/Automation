package patient.pages;

import framework.exception.CommonException;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by vahanmelikyan on 7/10/17.
 */
public class BookVisitPage extends WebBase {

    public static final String URL = "https://"+ baseUrl +"/book-visit";

    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oPageTitle = new CommonWebElement("oPageTitle", "xpath=//*[contains(@class,'title')]", oWebDriver);
    public CommonWebElement oEmergencyNoBtn = new CommonWebElement("oEmergencyNoBtn", "xpath=//*[text()='No']", oWebDriver);
    public CommonWebElement oEmergencyYesBtn = new CommonWebElement("oEmergencyYesBtn", "xpath=//*[text()='Yes']", oWebDriver);
    public CommonWebElement oOkBtn = new CommonWebElement("oOkBtn", "xpath=//*[text()='Ok']", oWebDriver);

    //////////////////
    // Constructors //
    //////////////////
    public BookVisitPage(WebDriver oTargetDriver){
        super(oTargetDriver, URL);
    }

    public BookVisitPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////


}
