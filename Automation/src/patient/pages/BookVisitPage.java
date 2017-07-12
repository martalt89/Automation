package patient.pages;

import com.heal.framework.exception.HealException;
import com.heal.framework.web.HealWebElement;
import com.heal.framework.web.HealWebValidate;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by vahanmelikyan on 7/10/17.
 */
public class BookVisitPage extends WebBase {

    HealWebValidate validate = new HealWebValidate(oWebDriver);
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
