package patient.pages;

import com.heal.framework.web.HealWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by vahanmelikyan on 7/10/17.
 */
public class BookVisitPage extends WebBase {

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
    public BookVisitPage(WebDriver oTargetDriver){
        super(oTargetDriver);
    }
    /////////////
    // Methods //
    /////////////
    public void SelectFromMenu(HealWebElement menuItem)
    {
        menu.SelectFromMenu(menuItem);
    }


}
