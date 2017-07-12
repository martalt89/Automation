package patient.pages;

import com.heal.framework.exception.HealException;
import com.heal.framework.web.HealWebElement;
import com.heal.framework.web.HealWebValidate;
import com.heal.framework.web.WebBase;

import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

/**
 * Created by vahanmelikyan on 7/5/17.
 */
public class HomePage extends WebBase{

    HealWebValidate validate = new HealWebValidate(oWebDriver);
    public static final String URL = "https://patient.qa.heal.com/login";
    ///////////////////
    // Shared Pages  //
    ///////////////////
    Menu menu = new Menu(oWebDriver);

    ///////////////////
    // Page Elements //
    ///////////////////
    public HealWebElement oAccountOwnerName = new HealWebElement("oAccountOwnerName","xpath=//*[contains(@class,'primary-blue hide-xs ng-binding')]",oWebDriver );
    public HealWebElement oAccountOwnerFirstName = new HealWebElement("oAccountOwnerName","xpath=//*[contains(@class,'primary-blue hide-gt-xs show-xs ng-binding')]",oWebDriver );
    public HealWebElement oPageTitle = new HealWebElement("oPageTitle", "xpath=//*[contains(@class,'title')]",oWebDriver);
    public HealWebElement oAccountOwnerAvatar = new HealWebElement("oAccountOwnerAvatar", "css=profile-image[url='vm.user.avatarUrl'] ", oWebDriver);

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
    public void selectFromMenu(HealWebElement menuItem)
    {
        menu.SelectFromMenu(menuItem);
    }
    /////////////////
    // validations //
    /////////////////
    public void validateTitle(){
        try {
            validate.assertEquals("Verifying Visits page title ", oPageTitle.getText(), "Book Visit");
        }catch (HealException e) {
            System.out.println("cannot validate " + oPageTitle.getText());
        }
    }
    public void validateTitle(String sTitle){
    try {
        validate.assertEquals("Verifying Visits page title ", oPageTitle.getText(), sTitle);
        }catch (HealException e) {
            System.out.println("cannot validate " + oPageTitle.getText());
        }
    }
}
