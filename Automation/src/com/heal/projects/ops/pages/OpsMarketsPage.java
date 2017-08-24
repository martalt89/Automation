package com.heal.projects.ops.pages;


import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;

/**
 *  Created by david.wheeler on 24/08/2017.
 */

public class OpsMarketsPage extends WebBase {

    public static final String URL = "https://ops"+ baseUrl +"/markets";

    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oPageTitle = new CommonWebElement("oPageTitle", "xpath=//h1[text()='Markets']", oWebDriver);
    public CommonWebElement oMarketName = new CommonWebElement("oMarketName", "xpath=//*[contains(@class,'market-name___nBpbn')]", oWebDriver);
    public CommonWebElement oMarketDate = new CommonWebElement("oMarketDate", "xpath=//*[contains(@class,'market-date___1lIJK')]", oWebDriver);

    //table
    public CommonWebElement oFirstRow = new CommonWebElement("oTableRow", "xpath=//tr[1][contains(@class, 'standard-row')]", oWebDriver);

    // table headers
    public CommonWebElement oTimeslotHeader = new CommonWebElement("oTimeslotHeader", "xpath=//th[@data-title='Timeslot']",oWebDriver);
    public CommonWebElement oAdultHeader = new CommonWebElement("oAdultHeader", "xpath=//th[@data-title='Adult']",oWebDriver);
    public CommonWebElement oChildHeader = new CommonWebElement("oChildHeader", "xpath=//th[@data-title='Child']",oWebDriver);
    public CommonWebElement oFluShotHeader = new CommonWebElement("oFluShotHeader", "xpath=//th[@data-title='Flu Shot']",oWebDriver);

    //button
    public CommonWebElement oApplyChangesBtn = new CommonWebElement("oApplyChangesBtn", "xpath=//*[contains(@class,'saveSection___3W-VU')]", oWebDriver);

    //switch
    public CommonWebElement oSwitchOff = new CommonWebElement("oSwitchOff", "xpath=//td[contains(@class,'switch-off___5llFq')]", oWebDriver);
    public CommonWebElement oSwitchOn = new CommonWebElement("oSwitchOn", "xpath=//td[contains(@class,'switch-on___3td_H')]", oWebDriver);
    public CommonWebElement oSwitchToggle = new CommonWebElement("oSwitchToggle", "xpath=//td[contains(@class,'switch-toggle___2mU96')]", oWebDriver);

    //////////////////
    // Constructors //
    //////////////////
    public OpsMarketsPage(WebDriver oTargetDriver){
        super(oTargetDriver, URL);
    }
    public OpsMarketsPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////
    public CommonWebElement findRowByTimeslot(String sTimeSlot)
    {
        return new CommonWebElement("oRow"+sTimeSlot, "xpath=//tr//text()[contains(.,'" + sTimeSlot + "')]" ,oWebDriver);
    }

}