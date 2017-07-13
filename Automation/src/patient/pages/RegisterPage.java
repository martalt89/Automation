package patient.pages;

import com.heal.framework.web.HealWebElement;
import com.heal.framework.web.HealWebValidate;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;
import utilities.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mihai.muresan on 7/10/2017.
 */
public class RegisterPage extends WebBase {

    public static final String sRedirectUrlAfterRegister = "https://patient.qa.heal.com/book-visit";
    public static final String URL = "https://patient.qa.heal.com/register";
    public static final String sRegisterFirstName = "QA_fn";
    public static final String sRegisterLastName = "QA_ln";
    public static final String sRegisterUserEmail = "qa_auto_test_" + getCurrentDateTime()+"@heal.com";
    public static final String sRegisterPassword = "Heal4325";
    public static final String sRegisterConfirmPassword = "Heal4325";
    public static final String sRegisterPhoneNo = "1201-555-5555";
    public static final String sRegisterZipCode = "4325";

    ///////////////////
    // Page Elements //
    ///////////////////

    public HealWebElement oFirstNameInput = new HealWebElement( "oFirstNameInput", "name=firstname", oWebDriver );
    public HealWebElement oLastNameNameInput = new HealWebElement( "oLastNameInput", "name=lastname", oWebDriver );
    public HealWebElement oEmailInput = new HealWebElement( "oFirstNameLabel", "name=username", oWebDriver );
    public HealWebElement oPasswordInput = new HealWebElement( "oPasswordInput", "name=password", oWebDriver );
    public HealWebElement oConfirmPasswordInput = new HealWebElement( "oConfirmPasswordInput", "name=password2", oWebDriver );
    public HealWebElement oPhoneNmbFlag = new HealWebElement( "oPhoneNmbFlag", "className=selected-flag", oWebDriver );
    public HealWebElement oPhoneNmbInput = new HealWebElement( "oPhoneNmbInput", "name=phonenumber", oWebDriver );
    public HealWebElement oZipcodeInput = new HealWebElement( "oZipcodeInput", "name=zipcode", oWebDriver );
    public HealWebElement oCreateAcctBtn = new HealWebElement( "oCreateAcctBtn", "xpath=//*[@id=\"register-view\"]/form/div[9]/button", oWebDriver );
    public HealWebElement oAlreadyHaveAccLnk = new HealWebElement( "oAlreadyHaveAccLnk", "xpath=//*[@id=\"register-view\"]/form/div[9]/a", oWebDriver );
    public HealWebElement oRegisterAlert = new HealWebElement( "oRegisterAlert", "className=alert-warning", oWebDriver );


    //////////////////
    // Constructors //
    //////////////////
    public RegisterPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver, URL);
    }

    /////////////
    // Methods //
    /////////////
    public static String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH-mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
