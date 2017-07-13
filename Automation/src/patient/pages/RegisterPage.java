package patient.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import com.heal.foundation.SysTools;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mihai.muresan on 7/10/2017.
 */
public class RegisterPage extends WebBase {

    public static SysTools sysTools = new SysTools();

    public static final String sRedirectUrlAfterRegister = "https://patient.qa.heal.com/book-visit";
    public static final String URL = "https://patient.qa.heal.com/register";
    public static final String sRegisterFirstName = "QA_fn";
    public static final String sRegisterLastName = "QA_ln";
    public static final String sRegisterUserEmail = "qa_auto_test_" + sysTools.getTimestamp("yyyy_MM_dd_HH-mm") +"@heal.com";
    public static final String sRegisterPassword = "Heal4325";
    public static final String sRegisterConfirmPassword = "Heal4325";
    public static final String sRegisterPhoneNo = "1201-555-5555";
    public static final String sRegisterZipCode = "4325";

    ///////////////////
    // Page Elements //
    ///////////////////

    public CommonWebElement oFirstNameInput = new CommonWebElement( "oFirstNameInput", "name=firstname", oWebDriver );
    public CommonWebElement oLastNameNameInput = new CommonWebElement( "oLastNameInput", "name=lastname", oWebDriver );
    public CommonWebElement oEmailInput = new CommonWebElement( "oFirstNameLabel", "name=username", oWebDriver );
    public CommonWebElement oPasswordInput = new CommonWebElement( "oPasswordInput", "name=password", oWebDriver );
    public CommonWebElement oConfirmPasswordInput = new CommonWebElement( "oConfirmPasswordInput", "name=password2", oWebDriver );
    public CommonWebElement oPhoneNmbFlag = new CommonWebElement( "oPhoneNmbFlag", "className=selected-flag", oWebDriver );
    public CommonWebElement oPhoneNmbInput = new CommonWebElement( "oPhoneNmbInput", "name=phonenumber", oWebDriver );
    public CommonWebElement oZipcodeInput = new CommonWebElement( "oZipcodeInput", "name=zipcode", oWebDriver );
    public CommonWebElement oCreateAcctBtn = new CommonWebElement( "oCreateAcctBtn", "xpath=//*[@id=\"register-view\"]/form/div[9]/button", oWebDriver );
    public CommonWebElement oAlreadyHaveAccLnk = new CommonWebElement( "oAlreadyHaveAccLnk", "xpath=//*[@id=\"register-view\"]/form/div[9]/a", oWebDriver );
    public CommonWebElement oRegisterAlert = new CommonWebElement( "oRegisterAlert", "className=alert-warning", oWebDriver );


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
    //Please create a method

    public static String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH-mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
