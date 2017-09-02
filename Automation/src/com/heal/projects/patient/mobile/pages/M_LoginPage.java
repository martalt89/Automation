package com.heal.projects.patient.mobile.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by vahanmelikyan on 9/2/2017.
 */
public class M_LoginPage extends WebBase {

    public CommonWebElement oEmailInput = new CommonWebElement("oEmailInput", "xpath=//android.widget.EditText[@resource-id='com.getheal.patient.debug:id/email']", oWebDriver);
    public CommonWebElement oPasswordInput = new CommonWebElement("oPasswordInput", "xpath=//android.widget.EditText[@resource-id='com.getheal.patient.debug:id/password']", oWebDriver);
    public CommonWebElement oLoginBtn = new CommonWebElement("oLoginBtn", "xpath=//android.widget.Button[@resource-id='com.getheal.patient.debug:id/log_in_button']", oWebDriver);
    public CommonWebElement oForgotPasswordText = new CommonWebElement("oForgotPasswordText", "xpath=//android.widget.TextView[@resource-id='com.getheal.patient.debug:id/forgot_password_text']", oWebDriver);

    //////////////////
    // Constructors //
    //////////////////
    public M_LoginPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }


    //////////////////
    //    Methods   //
    //////////////////

    public void login(){

        //will have username & password passed from test data, hardcode here for temp solution.
        login("vahan+qa@heal.com","Heal4325!",true);
    }

    public void login(String userName, String password, boolean needSubmit){

        oEmailInput.sendKeys(userName);
        oPasswordInput.sendKeys(password);

        if(needSubmit){
            oLoginBtn.click();
        }
    }

    public void fillLoginInfoWithoutSubmit(){

        //will have username & password passed from test data, hardcode here for temp solution.
        login("vahan+qa@heal.com","Heal4325!",false);
    }

}
