package patient.pages;

import com.heal.framework.exception.CommonException;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.CommonWebValidate;
import com.heal.framework.web.WebBase;

import org.openqa.selenium.WebDriver;

/**
 * Created by vahanmelikyan on 7/5/17.
 */
public class HomePage extends WebBase{

    CommonWebValidate validate = new CommonWebValidate(oWebDriver);
    public static final String URL = "https://patient.qa.heal.com/login";
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
        super(oTargetDriver);
    }
    public HomePage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////
    public void selectFromMenu(CommonWebElement menuItem)
    {
        menu.SelectFromMenu(menuItem);
    }
    /////////////////
    // validations //
    /////////////////
    public void validateTitle(){
        try {
            validate.assertEquals("Verifying Visits page title ", oPageTitle.getText(), "Book Visit");
        }catch (CommonException e) {
            System.out.println("cannot validate " + oPageTitle.getText());
        }
    }
    public void validateTitle(String sTitle){
    try {
        validate.assertEquals("Verifying Visits page title ", oPageTitle.getText(), sTitle);
        }catch (CommonException e) {
            System.out.println("cannot validate " + oPageTitle.getText());
        }
    }
}
