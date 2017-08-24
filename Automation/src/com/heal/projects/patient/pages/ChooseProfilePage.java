package com.heal.projects.patient.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

/**
 * Created by mihai.muresan on 7/13/2017.
 */
public class ChooseProfilePage extends WebBase {
    public static final String URL = "https://patient" + baseUrl + "/profiles";
    Menu menu = new Menu(oWebDriver);

    ///////////////////
    // Page Elements //
    ///////////////////

    //TODO: Also add objects for selected/unselected profile(s) icon(s)
    public CommonWebElement oProfileName = new CommonWebElement( "oProfileName", "xpath=//*[@id='choose-profile']//h[text()='Mayur']", oWebDriver );
    public CommonWebElement oManageProfilesLabel = new CommonWebElement( "oManageProfilesLabel", "xpath=//*[text()='Manage Profiles']", oWebDriver );
    public CommonWebElement oChooseProfileLabel = new CommonWebElement( "oChooseProfileLabel", "xpath=//*[text()='Choose Profile']", oWebDriver );
    //public CommonWebElement oMainProfileLink = new CommonWebElement( "oMainProfileLink", "xpath=//*[@id='choose-profile']//span[1]", oWebDriver );
    public CommonWebElement oMainProfileLink = new CommonWebElement( "oMainProfileLink", "xpath=(//*[contains(@class,'choose-profile-first-name')])[1]", oWebDriver );
    public CommonWebElement oAddPatientLogo = new CommonWebElement( "oAddPatientLogo", "className='create-patient-link", oWebDriver );
    public CommonWebElement oAddPatientLabel = new CommonWebElement( "oAddPatientLabel", "xpath=//*[text()='Add Patient']", oWebDriver );
    public CommonWebElement oContinueBtn = new CommonWebElement( "oContinueBtn", "xpath=//*[text()='Continue']", oWebDriver );

    //////////////////
    // Constructors //
    //////////////////

    @Parameters({ "url" })
    public ChooseProfilePage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public ChooseProfilePage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    public ChooseProfilePage(){

    }

    /////////////
    // Methods //
    /////////////

    public void selectMainProfile()
    {
        //Note2: this method can be optimized to select a given profile as argument
        this.oMainProfileLink.click();
        this.oContinueBtn.click();
    }

    /**
     * Clicks an element that has the provided text
     * @param sText Patient firstname
     */
    public void selectProfileByName(String sText)
    {
        CommonWebElement oPatient = new CommonWebElement( "oPatient", "xpath=(//div[@role='button' and contains(.,'" + sText +"')])[1]", oWebDriver );
        oPatient.click();
        oContinueBtn.clickAndWait(menu.oLoadingBar, false);
    }

    /**
     * Gets patient object from the patients list by providing patient name
     * @param sText Patient firstname
     * @return Patient object
     */
    public CommonWebElement getPatientByText(String sText)
    {
        return new CommonWebElement( "oElement", "xpath=//*[text()='"+sText+"']", oWebDriver );

    }
}