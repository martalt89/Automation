package com.heal.projects.patient.mobile.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

/**
 * Created by vahanmelikyan on 9/2/2017.
 */
public class M_MainPage extends WebBase {

    public CommonWebElement oSettingsBtn = new CommonWebElement("oSettingsBtn", "xpath=//android.widget.Button[1]", (AndroidDriver)oWebDriver);
    public CommonWebElement oRegisterBtn = new CommonWebElement("oRegisterBtn", "xpath=//android.widget.Button[2]", (AndroidDriver)oWebDriver);
    public CommonWebElement oLoginBtn = new CommonWebElement("oLoginBtn", "xpath=//android.widget.Button[3]", (AndroidDriver)oWebDriver);


    //////////////////
    // Constructors //
    //////////////////
    public M_MainPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }


    //////////////////
    //    Methods   //
    //////////////////


    public void clickSettingsButton(){
        oSettingsBtn.click();
    }

    public void clickRegisterButton(){
        oRegisterBtn.click();
    }

//    public void clickLoginButton(){
//        oLoginBtn.click();
//    }
}
