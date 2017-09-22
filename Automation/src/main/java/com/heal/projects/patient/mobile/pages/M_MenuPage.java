package com.heal.projects.patient.mobile.pages;

import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import org.openqa.selenium.WebDriver;

public class M_MenuPage extends WebBase{

    public CommonWebElement oMenuButton=new CommonWebElement("oMenuButton","xpath=//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]",oWebDriver);

    public CommonWebElement oProgressBar= new CommonWebElement("oProgressBar", "xpath=//android.widget.ProgressBar", oWebDriver);
    public CommonWebElement oBookNowLink=new CommonWebElement("oBookNowLink","xpath=//*[contains(@text,'Book now')]",oWebDriver);
    public CommonWebElement oVisits=new CommonWebElement("oBookNowLink","xpath=//*[contains(@text,'Visits')]",oWebDriver);
    public CommonWebElement oProfiles=new CommonWebElement("oBookNowLink","xpath=//*[contains(@text,'Profiles')]",oWebDriver);
    public CommonWebElement oPayment=new CommonWebElement("oPayment","xpath=//*[contains(@text,'Payment')]",oWebDriver);
    public CommonWebElement oInviteFriends=new CommonWebElement("oInviteFriends","xpath=//*[contains(@text,'Invite friends')]",oWebDriver);
    public CommonWebElement oHelp=new CommonWebElement("oHelp","xpath=//*[contains(@text,'Help')]",oWebDriver);
    public CommonWebElement oContact=new CommonWebElement("oBookNowLink","xpath=//*[contains(@text,'Contact')]",oWebDriver);
    public CommonWebElement oSignOut=new CommonWebElement("oBookNowLink","xpath=//*[contains(@text,'Sign Out')]",oWebDriver);

    public M_MenuPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }
    /**
    *Method to specify which option to select from menu in mobile
    *@param sMenuOption->string to specify which item to select from menu
    *
    */
    public void selectFromMenu(String sMenuOption){

        if(sMenuOption.equalsIgnoreCase("book now"))
            oBookNowLink.clickAndWait(this.oProgressBar,false);

        else if(sMenuOption.equalsIgnoreCase("visits"))
            oVisits.clickAndWait(this.oProgressBar,false);

        else if(sMenuOption.equalsIgnoreCase("profiles"))
            oProfiles.clickAndWait(this.oProgressBar,false);

        else if(sMenuOption.equalsIgnoreCase("payment"))
            oPayment.clickAndWait(this.oProgressBar,false);

        else if(sMenuOption.equalsIgnoreCase("Invite friends"))
            oInviteFriends.clickAndWait(this.oProgressBar,false);

        else if(sMenuOption.equalsIgnoreCase("Help"))
            oHelp.clickAndWait(this.oProgressBar,false);

        else if(sMenuOption.equalsIgnoreCase("Contact"))
            oContact.clickAndWait(this.oProgressBar,false);

        else if(sMenuOption.equalsIgnoreCase("Sign Out"))
            oSignOut.clickAndWait(this.oProgressBar,false);

        else
            oBookNowLink.clickAndWait(oProgressBar,false);



    }




}
