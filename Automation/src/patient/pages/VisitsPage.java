package patient.pages;

import com.heal.framework.web.HealWebElement;
import com.heal.framework.web.WebBase;
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

    public HealWebElement oPageTitle = new HealWebElement("oPageTitle", "xpath=//*[contains(@class,'title')]",oWebDriver);
    public HealWebElement oCancelVisitBtn = new HealWebElement("oCancelVisitBtn", "xpath=//*[text()='Cancel Visit']", oWebDriver);
    public HealWebElement oWhatToExpectBtn = new HealWebElement("oWhatToExpectBtn", "xpath=//*[text()='What To Expect']", oWebDriver);
    public HealWebElement oScheduledVisitsTitle = new HealWebElement("oScheduledVisitsTitle", "xpath=//*[text()='Scheduled Visits']", oWebDriver);
    public HealWebElement oIconAll = new HealWebElement("oIconAll", "xpath=//*[text()='All']", oWebDriver);
    public HealWebElement oIconPatient = new HealWebElement("oIconPatient", "css=profile-image[url='patient.avatarUrl'] ", oWebDriver);


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
