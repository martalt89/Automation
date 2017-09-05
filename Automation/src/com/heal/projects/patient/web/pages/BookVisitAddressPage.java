package com.heal.projects.patient.web.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/19/2017.
 */
public class BookVisitAddressPage extends WebBase {
    public static final String URL = "https://patient" + baseUrl + "/book-goTo/address";

    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oBookVisitTitle = new CommonWebElement( "oBookVisitTitle", "xpath=//*[text()='Book Visit']", oWebDriver );
    public CommonWebElement oVisitLocationTitle = new CommonWebElement( "oVisitLocationTitle", "xpath=//*[text()='Visit Location']", oWebDriver );
    public CommonWebElement oSavedAddressText = new CommonWebElement( "oSavedAddressText", "xpath=//*[text()='My Saved Address']", oWebDriver );
    public CommonWebElement oSavedAddress = new CommonWebElement( "oSavedAddress", "xpath=(//*[@class='md-list-item-text address-label layout-column'])[1]", oWebDriver );
    public CommonWebElement oSearchAddressText = new CommonWebElement( "oSearchAddressText", "xpath=//*[text()='Search Address']", oWebDriver );
    public CommonWebElement oWhatAdressText = new CommonWebElement( "oWhatAdressText", "xpath=//*[contains(text(),'What kind')]", oWebDriver );
    public CommonWebElement oAddressInput = new CommonWebElement( "oAddressInput", "name=address1", oWebDriver );
    public CommonWebElement oAptSteInput = new CommonWebElement( "oAptSteInput", "name=address2", oWebDriver );
    public CommonWebElement oInstructionsInput = new CommonWebElement( "oInstructionsInput", "name=instructions", oWebDriver );
    public CommonWebElement oAddressTypeMenuButton = new CommonWebElement( "oAddressTypeMenu", "className=md-select-value", oWebDriver );
    public CommonWebElement oContinueBtn = new CommonWebElement( "oContinueBtn", "xpath=//*[text()='Continue']", oWebDriver );
    public CommonWebElement oAddressTypeMenu = new CommonWebElement(oAddressTypeMenuButton, "oAddressTypeMenu", "className=md-content", oWebDriver );

    //////////////////
    // Constructors //
    //////////////////

    @Parameters({ "url" })
    public BookVisitAddressPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public BookVisitAddressPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    public BookVisitAddressPage(){

    }

    /////////////
    // Methods //
    /////////////

    /**
     *
     * @param SavedAddress (Boolean) - If true, clicks on the saved address and automatically types
     *                    the Saved Address in the Address field
     * @param sAddress (String) - if !SavedAddress, the Address to be used
     * @param sAptSte (String) - Apt/Ste to be used
     * @param sInstructions (String) - Instructions to be used
     * @param sAddressType (String) - Address type to be used
     */
    public void typeAddressDetailsAndSubmit(Boolean SavedAddress, String sAddress, String sAptSte, String sInstructions, String sAddressType)
    {   //TODO: This is unstable. Needs more work
        this.oAddressInput.click();
        this.oAddressInput.sendKeys(sAddress);
        this.oAddressInput.sendKeys(Keys.ENTER);
        this.oAptSteInput.click();
        this.oAptSteInput.sendKeys(sAptSte);
        this.oInstructionsInput.sendKeys(sInstructions);
        this.oAddressTypeMenuButton.selectByVisibleTextAngular(sAddressType);
        this.oContinueBtn.click();
    }

    public void selectFirstSavedAddress(){
        oSavedAddress.click();
    }

}