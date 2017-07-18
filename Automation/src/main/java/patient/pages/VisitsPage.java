package patient.pages;

import framework.web.CommonWebElement;
import framework.web.WebBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Created by Adrian Rosu on 7/10/17.
 */
public class VisitsPage extends WebBase {

    public static final String URL = "https://patient.qa.heal.com/login";
    ///////////////////
    // Shared Pages  //
    ///////////////////
    Menu menu = new Menu(oWebDriver);
    HomePage homePage = new HomePage(oWebDriver);
    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oPageTitle = new CommonWebElement("oPageTitle", "xpath=//*[contains(@class,'title')]", oWebDriver);
    public CommonWebElement oCancelVisitBtn = new CommonWebElement("oCancelVisitBtn", "xpath=//*[text()='Cancel Visit']", oWebDriver);
    public CommonWebElement oWhatToExpectBtn = new CommonWebElement("oWhatToExpectBtn", "xpath=//*[text()='What To Expect']", oWebDriver);
    public CommonWebElement oScheduledVisitsTitle = new CommonWebElement("oScheduledVisitsTitle", "xpath=//*[text()='Scheduled Visits']", oWebDriver);
    public CommonWebElement oIconAll = new CommonWebElement("oIconAll", "xpath=//*[text()='All']", oWebDriver);
    public CommonWebElement oIcon1stPatient = new CommonWebElement("oIcon1stPatient", "xpath=//*[contains(@class,'patient')][1]", oWebDriver); // this works
    public CommonWebElement oIcon2ndPatient = new CommonWebElement("oIcon2ndPatient", "xpath=//*[contains(@class,'patient')][2]", oWebDriver); // this works
    public CommonWebElement oVisitCard = new CommonWebElement("oVisitCard", "className=card-content", oWebDriver);




    //////////////////
    // Constructors //
    //////////////////
    public VisitsPage(WebDriver oTargetDriver) {
        super(oTargetDriver, URL);
    }

    public VisitsPage(WebDriver oTargetDriver, String sUrl) {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////
    // in progress
}
