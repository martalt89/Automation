package patient.pages;

import framework.web.CommonWebElement;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by Adrian Rosu on 7/10/17.
 */
public class VisitsPage extends WebBase{

    public static final String URL = "https://patient.qa.heal.com/login";
    ///////////////////
    // Shared Pages  //
    ///////////////////
    Menu menu = new Menu(oWebDriver);
    HomePage homePage = new HomePage(oWebDriver);
    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oPageTitle = new CommonWebElement("oPageTitle", "xpath=//*[contains(@class,'title')]",oWebDriver);
    public CommonWebElement oCancelVisitBtn = new CommonWebElement("oCancelVisitBtn", "xpath=//*[text()='Cancel Visit']", oWebDriver);
    public CommonWebElement oWhatToExpectBtn = new CommonWebElement("oWhatToExpectBtn", "xpath=//*[text()='What To Expect']", oWebDriver);
    public CommonWebElement oScheduledVisitsTitle = new CommonWebElement("oScheduledVisitsTitle", "xpath=//*[text()='Scheduled Visits']", oWebDriver);
    public CommonWebElement oIconAll = new CommonWebElement("oIconAll", "xpath=//*[text()='All']", oWebDriver);
    public CommonWebElement oIconPatient = new CommonWebElement("oIconPatient", "css=profile-image[url='patient.avatarUrl'] ", oWebDriver);


    //////////////////
    // Constructors //
    //////////////////
    public VisitsPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public VisitsPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////
    public void SelectCancelVisit()
    {
        this.oCancelVisitBtn.jsClick();
    }
    public void SelectWhatToExpect()
    {
        this.oWhatToExpectBtn.jsClick();
    }
    public void SelectAllVisits()
    {
        this.oIconAll.click();
    }
    public void SelectPatientVisits()
    {
        this.oIconPatient.click();
    }

}
