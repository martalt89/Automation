package patient.pages;

import com.heal.framework.web.CommonWebElement;
import org.openqa.selenium.WebDriver;
import com.heal.framework.web.WebBase;


/**
 * Created by vahanmelikyan on 7/6/17.
 */
public class Menu extends WebBase{


    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oHomeLnk = new CommonWebElement("oHomeLnk", "xpath=//*[@ui-sref='main.body.home']",oWebDriver);
    public CommonWebElement oBookVisitLnk = new CommonWebElement("oBookVisitLnk", "xpath=//*[@ui-sref='main.body.bookVisit.emergency']",oWebDriver);
    public CommonWebElement oVisitsLnk = new CommonWebElement("oVisitsLnk", "xpath=//*[@ui-sref='main.body.visits']",oWebDriver);
    public CommonWebElement oProfilesLnk = new CommonWebElement("oProfilesLnk", "xpath=//*[@ui-sref='main.body.profiles.chooseProfile']",oWebDriver);
    public CommonWebElement oPaymentMethodLnk = new CommonWebElement("oPaymentMethodLnk", "xpath=//*[@ui-sref='main.body.payments.listCards']",oWebDriver);
    public CommonWebElement oSignOutLnk = new CommonWebElement("oSignOutLnk", "xpath=//*[@ui-sref='unauthenticate']",oWebDriver);
    public CommonWebElement oMenuBtn = new CommonWebElement("oMenuBtn", "xpath=//button[contains(@class,'md-icon-button')]",oWebDriver);
    public CommonWebElement oLoadingBar = new CommonWebElement("oLoadingBar", "xpath=//*[@class='md-container md-mode-indeterminate']",oWebDriver);


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
    public void SelectFromMenu(CommonWebElement menuItem)
    {
        if (oLoadingBar.exists()){
            oLoadingBar.waitForInvisible();
        }

        //if the hamburger menu is visible perform javaScript click on the menu links
        //will not work on native mobile apps
        if (oMenuBtn.isDisplayed()){
            menuItem.jsClick();
        }else {
            menuItem.click();
        }

        if (oLoadingBar.exists()){
            oLoadingBar.waitForInvisible();
        }
    }
}
