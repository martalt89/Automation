package patient.pages;

import com.heal.framework.web.HealWebElement;
import com.heal.framework.web.WebBase;

import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

/**
 * Created by vahanmelikyan on 7/5/17.
 */
public class HomePage extends WebBase{

    public static final String URL = "https://patient.qa.heal.com/login";
    ///////////////////
    // Shared Pages  //
    ///////////////////
    Menu menu = new Menu(oWebDriver);

    ///////////////////
    // Page Elements //
    ///////////////////
    public HealWebElement oPageTitle = new HealWebElement("oPageTitle", "xpath=//*[contains(@class,'title')]",oWebDriver);


    //////////////////
    // Constructors //
    //////////////////
//    public HomePage(WebDriver oTargetDriver)
//    {
//        super(oTargetDriver, URL);
//    }

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

    public void SelectFromMenu(HealWebElement menuItem)
    {
        menu.SelectFromMenu(menuItem);
    }


}
