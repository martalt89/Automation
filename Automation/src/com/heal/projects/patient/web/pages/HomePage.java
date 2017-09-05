package com.heal.projects.patient.web.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Created by vahanmelikyan on 7/5/17.
 */
public class HomePage extends WebBase {

    public static final String URL = "https://patient" + baseUrl + "/visits";
    ///////////////////
    // Shared Pages  //
    ///////////////////
    Menu menu = new Menu(oWebDriver);

    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oAccountOwnerName = new CommonWebElement("oAccountOwnerName","xpath=//*[contains(@class,'primary-blue hide-xs ng-binding')]",oWebDriver );
    public CommonWebElement oAccountOwnerFirstName = new CommonWebElement("oAccountOwnerName","xpath=//*[contains(@class,'primary-blue hide-gt-xs show-xs ng-binding')]",oWebDriver );
<<<<<<< HEAD:Automation/src/com/heal/projects/patient/pages/HomePage.java
    public CommonWebElement oPageTitle = new CommonWebElement("oPageTitle", "xpath=//div[@class='layout-wrap layout-align-start-center layout-row']//h2",oWebDriver);
=======
    public CommonWebElement oPageTitle = new CommonWebElement("oPageTitle", "xpath=(//h2)[1]",oWebDriver);
>>>>>>> d4041fb3a6fba971fec30819fb317ee7d3c7fd3c:Automation/src/com/heal/projects/patient/web/pages/HomePage.java
    public CommonWebElement oAccountOwnerAvatar = new CommonWebElement("oAccountOwnerAvatar", "css=profile-image[url='vm.user.avatarUrl'] ", oWebDriver);
    public CommonWebElement oSelectReason = new CommonWebElement("oSelectReason", "xpath=(//md-select-value[@class='md-select-value'])[1]", oWebDriver );
//    public CommonWebElement oCancelVisitLnk = new CommonWebElement("oCancelVisitLnk", "xpath=(//button[contains(.,'Cancel')])[1]", oWebDriver);
//    public CommonWebElement oSelectReasonLabel = new CommonWebElement("oSelectReasonLabel", "xpath=(//*[@class='card-content'])[1]//md-select-value[@class='md-select-value']", oWebDriver );
//    public CommonWebElement oNotes = new CommonWebElement("oNotes", "xpath=(//*[@class='card-content'])[1]//input[@ng-model='vm.cancelVisitNote']", oWebDriver );
//    public CommonWebElement oSubmitBtn = new CommonWebElement("oSubmitBtn", "xpath=(//*[@class='card-content'])[1]//button[contains(.,'Submit')]", oWebDriver );
//    public CommonWebElement oVisitCard = new CommonWebElement("oVisitCard", "xpath=(//*[@class='card-content'])[1]", oWebDriver );



    //////////////////
    // Constructors //
    //////////////////
    public HomePage(WebDriver oTargetDriver){
        super(oTargetDriver, URL);
    }
    public HomePage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    public HomePage(){

    }

    /////////////
    // Methods //
    /////////////
    public void selectFromMenu(String sMenuItem)
    {
        menu.selectFromMenu(sMenuItem);
    }

    public void selectFromMenu(CommonWebElement menuItem)
    {
        menu.selectFromMenu(menuItem);
    }

    /**
     * Cancel a single visit based on index of the visit
     *
     * @param iVisitsIndex - The index of the visit to cancel
     */
    public void cancelVisit(int iVisitsIndex){

        cancelVisit(iVisitsIndex, "Other", "automation");
    }

    /**
     * Cancel a single visit based on index of the visit and provide the reason and notes
     *
     * @param iVisitsIndex - The index of the visit to cancel
     * @param sReason - Value should be a valid value from "Select a reason" list
     * @param sNotes - The text to type in Notes field
     */
    public void cancelVisit(int iVisitsIndex, String sReason, String sNotes){
        CommonWebElement oCancelVisitLnk = new CommonWebElement("oCancelVisitLnk", "xpath=(//button[contains(.,'Cancel')])[" + String.valueOf(iVisitsIndex) + "]", oWebDriver);
        CommonWebElement oSelectReasonLabel = new CommonWebElement("oSelectReasonLabel", "xpath=(//*[@class='card-content'])[" + String.valueOf(iVisitsIndex) + "]//md-select-value[@class='md-select-value']", oWebDriver );
        CommonWebElement oNotes = new CommonWebElement("oNotes", "xpath=(//*[@class='card-content'])[" + String.valueOf(iVisitsIndex) + "]//input[@ng-model='vm.cancelVisitNote']", oWebDriver );
        CommonWebElement oSubmitBtn = new CommonWebElement("oSubmitBtn", "xpath=(//*[@class='card-content'])[" + String.valueOf(iVisitsIndex) + "]//button[contains(.,'Submit')]", oWebDriver );
        CommonWebElement oVisitCard = new CommonWebElement("oVisitCard", "xpath=(//*[@class='card-content'])[" + String.valueOf(iVisitsIndex) + "]", oWebDriver );

        if(menu.oLoadingBar.exists()) menu.oLoadingBar.waitForInvisible();
        oCancelVisitLnk.click();
        oSelectReasonLabel.waitForVisible();
        oSelectReasonLabel.selectFromContextByVisibleTextAngular(sReason, oVisitCard);
        oNotes.sendKeys(sNotes);
        oSubmitBtn.clickAndWait(menu.oLoadingBar, false);
    }

    /**
     * A handy method to cancel all of the visits
     */
    public void cancelAllVisits(){
        List<CommonWebElement> visit = findAllElements(By.xpath("//*[@class='card-content']"));
        int visits = 0;
        for (int i = 0; i < visit.size(); i++) {
            cancelVisit(1);
            visits++;
            System.out.println(visits + " Visits canceled.");
        }
        System.out.println(visits + " Total visits have been canceled.");
    }


}
