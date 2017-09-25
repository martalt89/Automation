package com.heal.projects.patient.mobile.pages;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.WebBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Calendar;

public class M_ProfilesPage extends WebBase {

    /**
     * Relationship enumeration to list out relation for new patient profile
     *
     *
     */
    public enum Relationship{
        Spouse,Partner,Grandparent,Grandchild,Child,Parent,Sibling,Other,You,Friend,Coworker,AssistedLivingResident,;


    }
    /**
     * Insurance Provider enumeration to list out insurance provider for to add insurance to profile
     *
     *
     */
    public enum InsuranceProviderCompany{
        AETNA("aetna"),
        ANTHEMBLUECROSS("Anthem Blue Cross"),
        BLUESHIELD("BlueShield"),
        CIGNA("Cigna"),
        MRITAINHEALTH("MeriTain Health(aetna)"),
        NIPPONLIFEBENEFITS("Nippon Life Benefits(aetna"),
        PREMERABLUECROSS("Premera BlueCross"),
        UMR("UMR(United Healthcare)"),
        UNITEDHEALTHCARE("United Healthcare"),
        NALC("NALC(Cigna)"),
        GEHA("GEHA");

        private final String message;

        private InsuranceProviderCompany(String message)
        {
            this.message = message;
        }

        public String getMessage()
        {
            return this.message;
        }
    }

    public CommonWebElement oGotItButton=new CommonWebElement("oGotItButton","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/btn_affirmative\"]",oWebDriver);

    public CommonWebElement oAddProfilesButton=new CommonWebElement("oAddProfilesButton","xpath=//*[contains(@text,'Add')]",oWebDriver);
    public CommonWebElement oFirstNameField=new CommonWebElement("oFirstNameField","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/first_name\"]",oWebDriver);
    public CommonWebElement oLastNameField=new CommonWebElement("oLastNameField","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/last_name\"]",oWebDriver);
    public CommonWebElement oDOBField=new CommonWebElement("oDOBField","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/date_of_birth\"]",oWebDriver);
    public CommonWebElement oEmailField=new CommonWebElement("oEmailField","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/email\"]",oWebDriver);
    public CommonWebElement oPhoneNumberField=new CommonWebElement("oPhoneNumberField","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/phone\"]",oWebDriver);
    public CommonWebElement oRelationShipField=new CommonWebElement("oRelationShipField","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/relationship\"]",oWebDriver);
    public CommonWebElement oGenderField=new CommonWebElement("oGenderField","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/gender\"]",oWebDriver);





    public CommonWebElement oMonthPicker=new CommonWebElement("oMonthPicker","xpath=//android.widget.EditText[@instance='0']",oWebDriver);
    public CommonWebElement oMonthToBeSelected(String sMonth)
    {
        CommonWebElement oSelectMonth=new CommonWebElement("oSelectMonth","xpath=//android.widget.EditText[@text='"+sMonth+"']",oWebDriver);
        return oSelectMonth;
    }
    public CommonWebElement oDayPicker=new CommonWebElement("oDayPicker","xpath=//android.widget.EditText[@instance='1']",oWebDriver);
    public CommonWebElement oYearPicker=new CommonWebElement("oYearPicker","xpath=//android.widget.EditText[@instance='2']",oWebDriver);
    public CommonWebElement oDateOkButton=new CommonWebElement("oDateOkButton","xpath=//android.widget.Button[@text='OK']",oWebDriver);

    public CommonWebElement oGenderFemale=new CommonWebElement("oGenderFemale","xpath=//android.widget.CheckedTextView[@text='Female']",oWebDriver);
    public CommonWebElement oGenderMale=new CommonWebElement("oGenderMale","xpath=//android.widget.CheckedTextView[@text='Male']",oWebDriver);
    public CommonWebElement oGenderOther=new CommonWebElement("oGenderOther","xpath=//android.widget.CheckedTextView[@text='Male']",oWebDriver);

    public CommonWebElement oUseInsuranceSwitch=new CommonWebElement("oUseInsuranceSwitch","xpath=//android.widget.Switch[@instance='1']",oWebDriver);

    public CommonWebElement oEnterMemberNumber=new CommonWebElement("oEnterMemberNumber","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/insurance_member\"]",oWebDriver);
    public CommonWebElement oEnterGroupNumber=new CommonWebElement("oEnterGroupNumber","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/insurance_group\"]",oWebDriver);
    public CommonWebElement oInsuranceProviderField=new CommonWebElement("oInsuranceProviderField","xpath=//android.widget.TextView[@text='Select insurance provider']",oWebDriver);

    public CommonWebElement oSelectInsuranceProviderAetna=new CommonWebElement("oSelectInsuranceProviderAetna","xpath=//android.widget.CheckedTextView[@text='aetna']",oWebDriver);
    public CommonWebElement oDropDownArea=new CommonWebElement("oDropDownArea","xpath=//android.widget.CheckedTextView[@instance='1']",oWebDriver);



    public CommonWebElement oSaveButton=new CommonWebElement("oSaveButton","xpath=//*[@resource-id=\"com.getheal.patient.debug:id/save_button\"]",oWebDriver);




    public M_ProfilesPage(WebDriver oTargetDriver)
    {
        super(oTargetDriver);
    }

    /**
     * method to select date from android widget
     * @param sMonth ->passing in the month to be selected
     * @param sDay->passing in the day to be selected
     * @param sYear->passing in the year to be selected
     */
    public void DatePicker(String sMonth,String sDay,String sYear){
        TestBase t1=new TestBase();
        selectScroller(this.oMonthPicker,sMonth);
        Assert.assertTrue(Integer.valueOf(sDay)<=31,"select date less than 31");

        selectScroller(this.oDayPicker,sDay);
        Assert.assertTrue(Integer.valueOf(sYear)>=(Calendar.getInstance().get(Calendar.YEAR)-100));
        selectScroller(this.oYearPicker,sYear);
    }

    /**
     * method to select relationship from dropdown from provided list
     * @param r->passing in the relationship enum to select the desired relationship from given list
     *
     */
    public void selectFromDropdownRelation(Relationship r){
        TestBase t1=new TestBase();
        AppiumDriver dr=t1.getMobileDriver();
        CommonWebElement oRelationshipMenu= new CommonWebElement( "oRelationshipMenu", "xpath=//android.widget.CheckedTextView[@text='" + r.toString() + "']", oWebDriver );

        if(oRelationshipMenu.isDisplayed())
        {
            oRelationshipMenu.click();

        }
        else
        {
            Point pDropDownArea =this.oDropDownArea.getCoordinates().onPage();
            dr.swipe((pDropDownArea.x+600),(pDropDownArea.y),(pDropDownArea.x+600),(pDropDownArea.y+1000),2000);

//
            if(oRelationshipMenu.isDisplayed())
            {
                oRelationshipMenu.click();
            }
            else {
                dr.swipe((pDropDownArea.x+600),(pDropDownArea.y+1000),(pDropDownArea.x+600),(pDropDownArea.y),2000);
                oRelationshipMenu.click();
            }

        }


        //oRelationshipMenuSpouse.click();
    }

    /**
     * method to select insurance provider from dropdown from provided list
     * @param ic->passing in the insurance provider company enum to select the desired insurance provider company from given list
     *
     */
    public void selectFromDropdownInsurance(InsuranceProviderCompany ic){
        TestBase t1=new TestBase();
        AppiumDriver dr=t1.getMobileDriver();
        CommonWebElement oInsuranceProviderName = new CommonWebElement( "oInsuranceProviderName", "xpath=//android.widget.CheckedTextView[@text='" + ic.getMessage() + "']", oWebDriver );
        if(oInsuranceProviderName.isDisplayed())
        {
            oInsuranceProviderName.click();
        }
        else
        {
            Point pDropDownArea =this.oDropDownArea.getCoordinates().onPage();
            dr.swipe((pDropDownArea.x+600),(pDropDownArea.y),(pDropDownArea.x+600),(pDropDownArea.y+1000),2000);

            if(oInsuranceProviderName.isDisplayed())
            {
                oInsuranceProviderName.click();
            }
            else {
                dr.swipe((pDropDownArea.x+600),(pDropDownArea.y+1000),(pDropDownArea.x+600),(pDropDownArea.y),2000);
                oInsuranceProviderName.click();
            }
        }



        //oRelationshipMenuSpouse.click();
    }

    /**
     * method to scroll and search using text value
     * @param oElement - the element to perform the scroll
     * @param sValue - text value of the search term
     */
    public void selectScroller(CommonWebElement oElement, String sValue) {
       Point pElementPoint = oElement.getCoordinates().onPage();
       TestBase testBase = new TestBase();
       AppiumDriver dr = testBase.getMobileDriver();
       while (!oElement.getText().contentEquals(sValue)) {
            dr.swipe((pElementPoint.x+20), (pElementPoint.y+20), (pElementPoint.x+20), (pElementPoint.y+320), 2000);
        }
    }

    /**
     *
     * @param iStartXPercentage ->Start coordinate percentage on X-AXIS for scroll
     * @param iStartYPercentage ->Start coordinate percentage on Y-AXIS for scroll
     * @param iEndXPercentage   ->End coordinate percentage on X-AXIS for scroll
     * @param iEndYPercentage   ->End coordinate percentage on Y-AXIS for scroll
     * @param iDuration         ->Duration in milliseconds for scrolling from start to end coordinate
     */


    public void mobileDeviceScrollPage(int iStartXPercentage ,int iStartYPercentage, int iEndXPercentage, int iEndYPercentage ,int iDuration){

        TestBase testBase = new TestBase();
        AppiumDriver mobileDriver=testBase.getMobileDriver();

        //getting the height and width of screen window
        int windowWidth=mobileDriver.manage().window().getSize().getWidth();
        int windowHeight=mobileDriver.manage().window().getSize().getHeight();

        int pStartX =(int)(windowWidth*((iStartXPercentage)/100.0f));
        int pStartY =(int)(windowHeight*((iStartYPercentage)/100.0f));
        int pEndX   =(int)(windowWidth*((iEndXPercentage)/100.0f));
        int pEndY   =(int)(windowHeight*((iEndYPercentage)/100.0f));


        mobileDriver.swipe(pStartX, pStartY, pEndX ,pEndY ,iDuration);


    }

}
