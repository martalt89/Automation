package patient.pages;

import com.heal.framework.web.HealWebElement;
import org.openqa.selenium.WebDriver;
import com.heal.framework.web.WebBase;


/**
 * Created by vahanmelikyan on 7/6/17.
 */
public class Menu extends WebBase{

    public static final String URL = "https://patient.qa.heal.com/login";
    ///////////////////
    // Page Elements //
    ///////////////////
    public HealWebElement oUserNameInput = new HealWebElement("oUserNameInput", "name=username",oWebDriver);
    public HealWebElement oPasswordInput = new HealWebElement("oPasswordInput", "name=password", oWebDriver);
    public HealWebElement oLoginBtn = new HealWebElement("oLoginBtn", "xpath=//*[text()='Log In']", oWebDriver);
    public HealWebElement oRememberMe = new HealWebElement("oRememberMe", "className=md-icon", oWebDriver);
    public HealWebElement oForgotYourPasswordLnk = new HealWebElement("oForgotYourPasswordLnk", "linkText=Forgot Password", oWebDriver);
    public HealWebElement oWarningMsg = new HealWebElement("oWarningMsg","className=error-messages",oWebDriver);
    public HealWebElement oRegisterBtn = new HealWebElement("oRegisterNtm", "xpath=//*[text()='Register']", oWebDriver);

    //////////////////
    // Constructors //
    //////////////////
    public Menu(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public Menu(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }
    /////////////
    // Methods //
    /////////////
    public void Login()
    {
        this.oUserNameInput.sendKeys("mayur+qatest@heal.com");
        this.oPasswordInput.sendKeys("Heal4325");
        this.oLoginBtn.click();
    }
}
