package com.heal.projects.ops.web.pages;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.heal.framework.web.WebBase;
import org.testng.Reporter;


public class OpsMenu extends WebBase{


    ///////////////////
    // Page Elements //
    ///////////////////
    public static final String ZIPCODE_NOT_IN_AREA = "is not in our coverage area";
    public static final String ZIPCODE_INVALID = "Enter a valid zipcode!";
    public static final String ZIPCODE_CORRECT = "Great news! Heal is available in your area.";


    public CommonWebElement oMenuArea = new CommonWebElement("oMenuArea", "xpath=//*[contains(@class,'sidebar')]",oWebDriver);
    public CommonWebElement oDashboardLink = new CommonWebElement("oDashboardLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='Dashboard']",oWebDriver);
    public CommonWebElement oUserAccountsLink = new CommonWebElement("oUserAccountsLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='User Accounts']",oWebDriver);
    public CommonWebElement oPatientsLink = new CommonWebElement("oPatientsLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='Patients']",oWebDriver);
    public CommonWebElement oVisitsLink = new CommonWebElement("oVisitsLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='Visits']|//*[contains(@id,'sidebar-nav')]//span[text()='Visits']",oWebDriver);
    public CommonWebElement oCampaignBookingsLink = new CommonWebElement("oCampaignBookingsLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='Campaign Bookings']",oWebDriver);
    public CommonWebElement oWebBookingsLink = new CommonWebElement("oWebBookingsLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='Web Bookings']",oWebDriver);
    public CommonWebElement oDoctorsLink = new CommonWebElement("oDoctorsLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='Doctors']",oWebDriver);
    public CommonWebElement oMedicalAssistantsLink = new CommonWebElement("oMedicalAssistantsLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='Medical Assistants']",oWebDriver);
    public CommonWebElement oMarketsLink = new CommonWebElement("oMarketsLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='Markets']",oWebDriver);
    public CommonWebElement oPromoCodesLink = new CommonWebElement("oPromoCodesLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='Promo Codes']",oWebDriver);
    public CommonWebElement oCustomerFeedbackLink = new CommonWebElement("oCustomerFeedbackLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='Customer Feedback']",oWebDriver);
    public CommonWebElement oEligibilityToolLink = new CommonWebElement("oEligibilityToolLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='Eligibility Tool']",oWebDriver);
    public CommonWebElement oProvidersScheduleLink = new CommonWebElement("oProvidersScheduleLink", "xpath=//*[contains(@class,'sidebar')]//span[text()='Providers Schedule']",oWebDriver);
    public CommonWebElement oZipcodeSearch = new CommonWebElement("oZipcodeSearch", "xpath=//*[contains(@class,'zipcode')]/input",oWebDriver);
    public CommonWebElement oToastContainer = new CommonWebElement("oToastContainer", "xpath=//*[@id='toast-container']",oWebDriver);
    public CommonWebElement oToastMessage = new CommonWebElement("oToastMessage", "xpath=//*[@class='toast-message']",oWebDriver);
    public CommonWebElement oToastTitle = new CommonWebElement("oToastTitle", "xpath=//*[@class='toast-title']",oWebDriver);
    public CommonWebElement oLoadingBar = new CommonWebElement("oLoadingBar", "xpath=//*[@class='loading-screen layout-fill ng-scope layout-column']",oWebDriver);
    public CommonWebElement oToastCloseButton = new CommonWebElement("oToastCloseButton", "xpath=//*[@class='toast-close-button']",oWebDriver);
    //////////////////
    // Constructors //
    //////////////////

    public OpsMenu(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }
    public OpsMenu(){

    }

    /////////////
    // Methods //
    /////////////

    public void selectFromMenu(String sMenu) {

        switch (sMenu.toUpperCase()){
            case "DASHBOARD":
                selectFromMenu(oDashboardLink);
                break;
            case "USER ACCOUNTS":
                selectFromMenu(oUserAccountsLink);
                break;
            case "PATIENTS":
                selectFromMenu(oPatientsLink);
                break;
            case "VISITS":
                selectFromMenu(oVisitsLink);
                break;
            case "CAMPAIGN BOOKINGS":
                selectFromMenu(oCampaignBookingsLink);
                break;
            case "WEB BOOKINGS":
                selectFromMenu(oWebBookingsLink);
                break;
            case "DOCTORS":
                selectFromMenu(oDoctorsLink);
                break;
            case "MEDICAL ASSISTANTS":
                selectFromMenu(oMedicalAssistantsLink);
                break;
            case "MARKETS":
                selectFromMenu(oMarketsLink);
                break;
            case "PROMO CODES":
                selectFromMenu(oPromoCodesLink);
                break;
            case "CUSTOMER FEEDBACK":
                selectFromMenu(oCustomerFeedbackLink);
                break;
            case "ELIGIBILITY TOOLS":
                selectFromMenu(oEligibilityToolLink);
                break;
            case "PROVIDERS SCHEDULE":
                selectFromMenu(oProvidersScheduleLink);
                break;
            default: Reporter.log(sMenu + " : is not a menu item.");
        }
    }

    /**
     * Selects a menu from menu items
     * @param menuItem (String) Menu item to be selected. e.g. Dashboard
     */
    public void selectFromMenu(CommonWebElement menuItem) {

        if (oLoadingBar.exists()){
            oLoadingBar.waitForInvisible();
        }
        if (oLoadingBar.exists()) oLoadingBar.waitForInvisible();

        //if the hamburger menu is visible perform javaScript click on the menu links
        //will not work on native pages apps
        if (oMenuArea.isDisplayed()){
            menuItem.jsClickAndWait(oLoadingBar, false);
        }else {
            menuItem.clickAndWait(oLoadingBar, false);
        }
    }

    /**
     * Searches a given zipcode in the Zipcode search bar
     * @param sZipcode (String) Zipcode
     */
    public void searchZipcode(String sZipcode){
        this.oZipcodeSearch.sendKeys(sZipcode, Keys.ENTER);

    }

    /**
     * method verifies the toast messages and closes them
     *
     * @param sComment - sets the string comment for describing verification
     * @param sExpectedMessage -shows expected string toast message details
     *
     */
    public void verifyToastMessage(String sComment, String sExpectedMessage) {
        TestBase testbase = new TestBase();
        this.oToastMessage.waitForVisible();
        testbase.verifyTextMatches(sComment, this.oToastMessage, sExpectedMessage);
        if (this.oToastCloseButton.isDisplayed()) {
            this.oToastCloseButton.jsClick();
        }
    }
}
