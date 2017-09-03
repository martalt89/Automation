package com.heal.projects.patient.mobile.pages;

import com.heal.framework.exception.CommonException;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by vahanmelikyan on 9/3/2017.
 */
public class M_SettingsPage  extends WebBase {

    public CommonWebElement oBackBtn = new CommonWebElement("oBackBtn", "xpath=//android.widget.ImageView", oWebDriver);
    public CommonWebElement oDevRadioBtn = new CommonWebElement("oDevRadioBtn", "xpath=//android.widget.RadioButton[1]", oWebDriver);
    public CommonWebElement oDev1RadioBtn = new CommonWebElement("oDev1RadioBtn", "xpath=//android.widget.RadioButton[2]", oWebDriver);
    public CommonWebElement oQARadioBtn = new CommonWebElement("oQARadioBtn", "xpath=//android.widget.RadioButton[3]", oWebDriver);



    //////////////////
    // Constructors //
    //////////////////
    public M_SettingsPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }


    //////////////////
    //    Methods   //
    //////////////////

    public void setEnv(String env){

        switch (env.toLowerCase()){

            case "qa":
                oQARadioBtn.click();
                break;
            case "dev":
                oDevRadioBtn.click();
                break;
            case "dev1":
                oDev1RadioBtn.click();
                break;
            default:
                throw new CommonException(String.format("invalid Env: ", env));
        }

        oBackBtn.click();
    }
}
