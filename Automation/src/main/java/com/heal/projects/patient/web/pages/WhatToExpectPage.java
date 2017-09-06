package com.heal.projects.patient.web.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/18/2017.
 */
public class WhatToExpectPage extends WebBase {
    public static final String URL = "https://patient" + baseUrl + "/login";

    ///////////////////
    // Page Elements //
    ///////////////////

    //screen 1
    public CommonWebElement oNextBtn = new CommonWebElement( "oNextBtn", "xpath=//*[text()='Next']", oWebDriver );
    public CommonWebElement oBackBtn = new CommonWebElement( "oBackBtn", "xpath=//*[text()='Back']", oWebDriver );
    public CommonWebElement oGotItBtn = new CommonWebElement( "oGotItBtn", "xpath=//*[text()='Got It']", oWebDriver );

    public CommonWebElement oThankYouTitle = new CommonWebElement( "oThankYouTitle", "xpath=//*[contains(text(),'Thank you for choosing Heal.')]", oWebDriver );
    public CommonWebElement oHeartHandsLogo = new CommonWebElement( "oHeartHandsLogo", "xpath=//img", oWebDriver );
    public CommonWebElement oWhatToExpectTitle = new CommonWebElement( "oWhatToExpectTitle", "xpath=//*[text()='What to Expect']", oWebDriver );
    public CommonWebElement oScreen1Row1Text = new CommonWebElement( "oScreen1Row1Text", "xpath=//*[contains(text(),'Thanks for booking')]", oWebDriver );
    public CommonWebElement oScreen1Row2Text = new CommonWebElement( "oScreen1Row2Text", "xpath=//*[contains(text(),'Prepare a clean')]", oWebDriver );
    public CommonWebElement oScreen1Row3Text = new CommonWebElement( "oScreen1Row3Text", "xpath=//*[contains(text(),'We love your pets')]", oWebDriver );
    public CommonWebElement oScreen1Row4Text = new CommonWebElement( "oScreen1Row4Text", "xpath=//*[contains(text(),'call 844-644-HEAL (4325).')]", oWebDriver );
    //screen 2
    public CommonWebElement oDoctorLogo = new CommonWebElement( "oDoctorLogo", "xpath=//img", oWebDriver );
    public CommonWebElement oWhoToExpectTitle = new CommonWebElement( "oWhoToExpectTitle", "xpath=//*[text()='Who to Expect']", oWebDriver );
    public CommonWebElement oScreen2Row1Text = new CommonWebElement( "oScreen2Row1Text", "xpath=//*[contains(text(),'We hand-pick licensed,')]", oWebDriver );
    public CommonWebElement oScreen2Row2Text = new CommonWebElement( "oScreen2Row2Text", "xpath=//*[contains(text(),'Expect two guests:')]", oWebDriver );
    //screen 3
    public CommonWebElement oBandageLogo = new CommonWebElement( "oBandageLogo", "xpath=//img", oWebDriver );
    public CommonWebElement oAfterVisitTitle = new CommonWebElement( "oAfterVisitTitle", "xpath=//*[text()='After the Visit']", oWebDriver );
    public CommonWebElement oScreen3Row1Text = new CommonWebElement( "oScreen3Row1Text", "xpath=//*[contains(text(),'Prescriptions are sent')]", oWebDriver );
    public CommonWebElement oScreen3Row2Text = new CommonWebElement( "oScreen3Row2Text", "xpath=//*[contains(text(),'Heal doctor may recommend')]", oWebDriver );
    public CommonWebElement oScreen3Row3Text = new CommonWebElement( "oScreen3Row3Text", "xpath=//*[contains(text(),'To schedule a follow up, ')]", oWebDriver );
    public CommonWebElement oScreen3Row4Text = new CommonWebElement( "oScreen3Row4Text", "xpath=//*[contains(text(),'at support@heal.com.')]", oWebDriver );
    //screen 4
    public CommonWebElement oCalendarLogo = new CommonWebElement( "oCalendarLogo", "xpath=//img", oWebDriver );
    public CommonWebElement oScheduleVisitTitle = new CommonWebElement( "oScheduleVisitTitle", "xpath=//*[contains(text(),'Scheduling')]", oWebDriver );
    public CommonWebElement oScreen4Row1Text = new CommonWebElement( "oScreen4Row1Text", "xpath=//*[contains(text(),'primary care doctor')]", oWebDriver );
    public CommonWebElement oScreen4Row2Text = new CommonWebElement( "oScreen4Row2Text", "xpath=//*[contains(text(),'Pediatricians for')]", oWebDriver );
    public CommonWebElement oScreen4Row3Text = new CommonWebElement( "oScreen4Row3Text", "xpath=//*[contains(text(),'Internists for physical')]", oWebDriver );
    public CommonWebElement oScreen4Row4Text = new CommonWebElement( "oScreen4Row4Text", "xpath=//*[contains(text(),'Family practice doctors)]", oWebDriver );

    //////////////////
    // Constructors //
    //////////////////

    @Parameters({ "url" })
    public WhatToExpectPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public WhatToExpectPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }
    public WhatToExpectPage(){

    }
}