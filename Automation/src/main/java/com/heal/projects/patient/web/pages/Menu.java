package com.heal.projects.patient.web.pages;

import com.heal.framework.foundation.SysTools;
import com.heal.framework.web.CommonWebElement;
import org.openqa.selenium.WebDriver;
import com.heal.framework.web.WebBase;
import org.testng.Reporter;


/**
 * Created by vahanmelikyan on 7/6/17.
 */
public class Menu extends WebBase{


    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oHomeLnk = new CommonWebElement("oHomeLnk", "xpath=//*[@ui-sref='main.body.home']",oWebDriver);
    public CommonWebElement oBookVisitLnk = new CommonWebElement("oBookVisitLnk", "xpath=//*[@ui-sref='main.body.bookVisit.emergency']",oWebDriver);
    public CommonWebElement oVisitsLnk = new CommonWebElement("oVisitsLnk", "xpath=//*[@data-tid='menu_visits'] | //*[@ui-sref='main.body.visits'] ",oWebDriver);
    public CommonWebElement oProfilesLnk = new CommonWebElement("oProfilesLnk", "xpath=//*[@data-tid='menu_patients'] | //*[@ui-sref='main.body.profiles.chooseProfile']",oWebDriver);
    public CommonWebElement oPaymentsLnk = new CommonWebElement("oPaymentsLnk", "xpath=//*[@data-tid='menu_payment'] | //*[@ui-sref='main.body.payments.listCards']",oWebDriver);
    public CommonWebElement oSignOutLnk = new CommonWebElement("oSignOutLnk", "xpath=//*[@data-tid='menu_signOut'] | //*[@ui-sref='unauthenticate']",oWebDriver);
    public CommonWebElement oMenuBtn = new CommonWebElement("oMenuBtn", "xpath=//button[contains(@class,'md-icon-button')] | //*[@data-tid='btn_menuOpen']",oWebDriver);
//    public CommonWebElement oLoadingBar = new CommonWebElement("oLoadingBar", "xpath=//*[@class='md-accent']",oWebDriver);
//    public CommonWebElement oLoadingBar = new CommonWebElement("oLoadingBar", "xpath=//*[@class='md-container md-mode-indeterminate']",oWebDriver);
//    public CommonWebElement oLoadingBar = new CommonWebElement("oLoadingBar", "xpath=//*[@class='loading-screen layout-fill ng-scope layout-column']",oWebDriver);
    public CommonWebElement oLoadingBar = new CommonWebElement("oLoadingBar", "xpath=//*[contains(@class,'loading-screen')]",oWebDriver);


    //////////////////
    // Constructors //
    //////////////////

    public Menu(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }
    public Menu(){

    }

    /////////////
    // Methods //
    /////////////
    public void selectFromMenu(String sMenu) {

        switch (sMenu.toUpperCase()){
            case "HOME":
                selectFromMenu(oHomeLnk);
                break;
            case "BOOK VISIT":
                selectFromMenu(oBookVisitLnk);
                break;
            case "VISITS":
                selectFromMenu(oVisitsLnk);
                break;
            case "PROFILES":
                selectFromMenu(oProfilesLnk);
                break;
            case "PAYMENT METHODS":
                selectFromMenu(oPaymentsLnk);
                break;
            case "SIGN OUT":
                selectFromMenu(oSignOutLnk);
                break;
            default: Reporter.log(sMenu + " : is not a menu item.");
        }
    }
    public void selectFromMenu(CommonWebElement menuItem) {

        if (oLoadingBar.exists()){
            oLoadingBar.waitForInvisible();
        }
        if (menuItem.getElementName().equalsIgnoreCase(oVisitsLnk.getElementName()) || menuItem.getElementName().equalsIgnoreCase(oHomeLnk.getElementName())){
            SysTools.sleepFor(1);
        }
        if (oLoadingBar.exists()) oLoadingBar.waitForInvisible();

        //if the hamburger menu is visible perform javaScript click on the menu links
        //will not work on native pages apps
        if (oMenuBtn.isDisplayed()){
            oMenuBtn.click();
            menuItem.clickAndWait(oLoadingBar, false);
            //menuItem.jsClick();
        }else {
            menuItem.clickAndWait(oLoadingBar, false);
        }
    }
}
