package patient.pages;

import com.heal.framework.web.HealWebElement;
import org.openqa.selenium.WebDriver;
import com.heal.framework.web.WebBase;


/**
 * Created by vahanmelikyan on 7/6/17.
 */
public class Menu extends WebBase{


    ///////////////////
    // Page Elements //
    ///////////////////
    public HealWebElement oHomeLnk = new HealWebElement("oHomeLnk", "xpath=//*[@ui-sref='main.body.home']",oWebDriver);
    public HealWebElement oBookVisitLnk = new HealWebElement("oBookVisitLnk", "xpath=//*[@ui-sref='main.body.bookVisit.emergency']",oWebDriver);
    public HealWebElement oVisitsLnk = new HealWebElement("oVisitsLnk", "xpath=//*[@ui-sref='main.body.visits']",oWebDriver);
    public HealWebElement oProfilesLnk = new HealWebElement("oProfilesLnk", "xpath=//*[@ui-sref='main.body.profiles.chooseProfile']",oWebDriver);
    public HealWebElement oPamentMethodLnk = new HealWebElement("oPamentMethodLnk", "xpath=//*[@ui-sref='main.body.payments.listCards']",oWebDriver);
    public HealWebElement oSignOutLnk = new HealWebElement("oSignOutLnk", "xpath=//*[@ui-sref='unauthenticate']",oWebDriver);
    public HealWebElement oMenuBtn = new HealWebElement("oMenuBtn", "xpath=//button[contains(@class,'md-icon-button')]",oWebDriver);
    public HealWebElement oLoadingBar = new HealWebElement("oLoadingBar", "className=md-container md-mode-indeterminate",oWebDriver);


    //////////////////
    // Constructors //
    //////////////////

    public Menu(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }

    /////////////
    // Methods //
    /////////////
    public void SelectFromMenu(HealWebElement menuItem)
    {
        if (oLoadingBar.exists()){
            oLoadingBar.waitForInvisible();
        }
        if (oMenuBtn.isDisplayed() && oMenuBtn.isEnabled()){
            oMenuBtn.waitForEnabled(1);
            oMenuBtn.click();
        }
        menuItem.click();
    }
}
