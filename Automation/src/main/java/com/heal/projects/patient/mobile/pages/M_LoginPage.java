package com.heal.projects.patient.mobile.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by vahanmelikyan on 9/2/2017.
 */
public class M_LoginPage extends WebBase {


    public CommonWebElement oEmailInput = new CommonWebElement("oEmailInput", "xpath=//android.widget.EditText[1]", oWebDriver);
    public CommonWebElement oPasswordInput = new CommonWebElement("oPasswordInput", "xpath=//android.widget.EditText[2]", oWebDriver);
    public CommonWebElement oLoginBtn = new CommonWebElement("oLoginBtn", "xpath=//android.widget.Button", oWebDriver);
    public CommonWebElement oForgotPasswordText = new CommonWebElement("oForgotPasswordText", "xpath=//android.widget.TextView[@text='Forgot password']", oWebDriver);
    public CommonWebElement oAllowPermissionToAccessLocationOnConnectedDevice=new CommonWebElement("oAllowPermissionToAccessLocationOnConnectedDevice","xpath=//android.widget.Button[@text='Allow']",oWebDriver);
    public CommonWebElement oProgressBar= new CommonWebElement("oProgressBar", "xpath=//android.widget.ProgressBar", oWebDriver);
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

    public void login() {

        //will have username & password passed from test data, hardcode here for temp solution.
        login("vahan+qa@heal.com", "Heal4325!", true);
        if (this.oAllowPermissionToAccessLocationOnConnectedDevice.isDisplayed(2)) {
            this.oAllowPermissionToAccessLocationOnConnectedDevice.click();
        }
    }
    private void login(String userName, String password, boolean needSubmit){

        oEmailInput.sendKeys(userName);
        oPasswordInput.sendKeys(password);

        if(needSubmit){
            oLoginBtn.click();
        }
        if(this.oProgressBar.exists()) {
            this.oProgressBar.waitForInvisible();
        }
    }

    public void fillLoginInfoWithoutSubmit(){

        //will have username & password passed from test data, hardcode here for temp solution.
        login("vahan+qa@heal.com","Heal4325!",false);
    }

}
