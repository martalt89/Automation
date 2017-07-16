package patient.pages;

import framework.exception.CommonException;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by vahanmelikyan on 7/10/17.
 */
public class BookVisitPage extends WebBase {

    CommonWebValidate validate = new CommonWebValidate(oWebDriver);
    ///////////////////
    // Shared Pages  //
    ///////////////////
    Menu menu = new Menu(oWebDriver);

    ///////////////////
    // Page Elements //
    ///////////////////
    public CommonWebElement oPageTitle = new CommonWebElement("oPageTitle", "xpath=//*[contains(@class,'title')]",oWebDriver);

    //////////////////
    // Constructors //
    //////////////////
    public BookVisitPage(WebDriver oTargetDriver){
        super(oTargetDriver);
    }
    /////////////
    // Methods //
    /////////////
    public void SelectFromMenu(CommonWebElement menuItem)
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
