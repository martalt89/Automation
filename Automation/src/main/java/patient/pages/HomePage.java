package patient.pages;

import framework.exception.CommonException;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;

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

}
