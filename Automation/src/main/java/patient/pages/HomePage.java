package patient.pages;

import framework.exception.CommonException;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vahanmelikyan on 7/5/17.
 */
public class HomePage extends WebBase {

    public static final String URL = "https://patient.qa.heal.com/visits";
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

    public CommonWebElement oCancelVisitLnk = new CommonWebElement("oCancelVisitLnk", "xpath=//button[contains(.,'Cancel Visit')]", oWebDriver);
    public CommonWebElement oCancelVisitSlideUp = new CommonWebElement("oCancelVisitSlideUp", "css=.card-cancel-visit.slide-up.open", oWebDriver );

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


    public void cancelVisit(int visitsIndex){
        cancelVisit(visitsIndex, "Other", "automation");
    }

    public void cancelVisit(int visitsIndex, String reason, String notes){

        CommonWebElement oCancelVisitLnk = new CommonWebElement("oCancelVisitLnk", "xpath=(//button[contains(.,'Cancel Visit')])" +  "["+ String.valueOf(visitsIndex) +"]", oWebDriver);

        oCancelVisitLnk.click();

        //find first visible cancel visit slide up
        CommonWebElement oCancelVisitSlideUp = findElement("css=.card-cancel-visit.slide-up.open");
        CommonWebElement oSelectReasonLabel =  oCancelVisitSlideUp.findElement(By.xpath("//div[text()='Select a reason']"));
        oSelectReasonLabel.click();
        CommonWebElement oSelectReasonOption = findElement(By.xpath("//md-select-menu[@class='_md md-overflow']//md-option//div[text()='"+ reason +"']"));

        oSelectReasonOption.jsClick();
        CommonWebElement oNotes = oCancelVisitSlideUp.findElement(By.xpath("//input[@ng-model='vm.cancelVisitNote']"));
        oNotes.sendKeys(notes);

        CommonWebElement oSubmitBtn = oCancelVisitSlideUp.findElement(By.xpath("//button[contains(.,'Submit')]"));
        oSubmitBtn.click();
    }
}
