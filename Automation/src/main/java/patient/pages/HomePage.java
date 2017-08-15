package patient.pages;

import foundation.SysTools;
import framework.exception.CommonException;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Created by vahanmelikyan on 7/5/17.
 */
public class HomePage extends WebBase {

    public static final String URL = "https://" + baseUrl + "/visits";
    ///////////////////
    // Shared Pages  //
    ///////////////////
    Menu menu = new Menu(oWebDriver);

    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oAccountOwnerName = new CommonWebElement("oAccountOwnerName","xpath=//*[contains(@class,'primary-blue hide-xs ng-binding')]",oWebDriver );
    public CommonWebElement oAccountOwnerFirstName = new CommonWebElement("oAccountOwnerName","xpath=//*[contains(@class,'primary-blue hide-gt-xs show-xs ng-binding')]",oWebDriver );
    public CommonWebElement oPageTitle = new CommonWebElement("oPageTitle", "xpath=//*[contains(@class,'title')]",oWebDriver);
    public CommonWebElement oAccountOwnerAvatar = new CommonWebElement("oAccountOwnerAvatar", "css=profile-image[url='vm.user.avatarUrl'] ", oWebDriver);

    public CommonWebElement oCancelVisitBtn = new CommonWebElement("oCancelVisitBtn", "xpath=//*[text()='Cancel Visit']", oWebDriver);
    //public CommonWebElement oCancelVisitSlideUp = new CommonWebElement("oCancelVisitSlideUp", "css=.card-cancel-visit.slide-up.open", oWebDriver );
    CommonWebElement oVisitCard = new CommonWebElement("oVisitCard", "xpath=(//*[@class='card-content'])[1]", oWebDriver );


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
     * @param sNotes
     */
    public void cancelVisit(int iVisitsIndex, String sReason, String sNotes){
        CommonWebElement oCancelVisitLnk = new CommonWebElement("oCancelVisitLnk", "xpath=(//button[contains(.,'Cancel Visit')])[" + String.valueOf(iVisitsIndex) + "]", oWebDriver);
        oCancelVisitLnk.click();

        CommonWebElement oSelectReasonLabel =  oVisitCard.findElement(By.xpath("//md-select-value[@class='md-select-value']"));
        oSelectReasonLabel.selectFromContextByVisibleTextAngular(sReason, oVisitCard);
        CommonWebElement oNotes = oVisitCard.findElement(By.xpath("//input[@ng-model='vm.cancelVisitNote']"));

        oNotes.jsSendKeys(sNotes);

        CommonWebElement oSubmitBtn = oVisitCard.findElement(By.xpath("//button[contains(.,'Submit')]"));

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
