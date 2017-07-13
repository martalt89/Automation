package patient.pages;

import com.heal.framework.web.HealWebElement;
import com.heal.framework.web.WebBase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;


/**
 * Created by vahanmelikyan on 7/3/17.
 */

public class LoginPage extends WebBase{
    public static final String URL = "https://patient.qa.heal.com/login";
    ///////////////////
    // Page Elements //
    ///////////////////
    public HealWebElement oUserNameInput = new HealWebElement("oUserNameInput", "name=username",oWebDriver);
    public HealWebElement oPasswordInput = new HealWebElement("oPasswordInput", "name=password", oWebDriver);
    public HealWebElement oLoginBtn = new HealWebElement("oLoginBtn", "xpath=//button[@type='submit']", oWebDriver);
    public HealWebElement oRememberMe = new HealWebElement("oRememberMe", "className=md-icon", oWebDriver);
    public HealWebElement oForgotYourPasswordLnk = new HealWebElement("oForgotYourPasswordLnk", "linkText=Forgot Password", oWebDriver);
    public HealWebElement oWarningMsg = new HealWebElement("oWarningMsg","className=error-messages",oWebDriver);
    public HealWebElement oRegisterBtn = new HealWebElement("oRegisterNtm", "xpath=//*[text()='Register']", oWebDriver);

    //////////////////
    // Constructors //
    //////////////////
    @Parameters({ "url" })
    public LoginPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }
    public LoginPage(WebDriver oTargetDriver, String sUrl)
    {
        super(oTargetDriver, sUrl);
    }

    /////////////
    // Methods //
    /////////////

    //Login with the default username and password
    public void login()
    {
        this.oUserNameInput.sendKeys("mayur+qatest@heal.com");
        this.oPasswordInput.sendKeys("Heal4325");
        this.oLoginBtn.click();
    }

    /**
     * Logs in to heal with the provided username and password.
     *
     * @param sUsername (String) - Username to be used.
     * @param sPassword (String) - Password to be used.
     */
    public void login(String sUsername, String sPassword)
    {
        this.oUserNameInput.sendKeys(sUsername);
        this.oPasswordInput.sendKeys(sPassword);
        this.oLoginBtn.click();
    }

}