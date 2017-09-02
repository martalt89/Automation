package com.heal.projects.patient.mobile.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by vahanmelikyan on 9/2/2017.
 */
public class M_HomePage extends WebBase {

    public CommonWebElement oRegisterBtn = new CommonWebElement("oRegisterBtn", "xpath=//android.widget.Button[@resource-id='com.getheal.patient.debug:id/register_btn']", oWebDriver);
    public CommonWebElement oLoginBtn = new CommonWebElement("oLoginBtn", "xpath=//android.widget.Button[@resource-id='com.getheal.patient.debug:id/login_btn']", oWebDriver);


    //////////////////
    // Constructors //
    //////////////////
    public M_HomePage(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }


    //////////////////
    //    Methods   //
    //////////////////

    public void clickRegisterButton(){
        oRegisterBtn.click();
    }

    public void clickLoginButton(){
        oLoginBtn.click();
    }
}
