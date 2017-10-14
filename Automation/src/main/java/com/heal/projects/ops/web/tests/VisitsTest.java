package com.heal.projects.ops.web.tests;

import com.heal.framework.foundation.SysTools;
import com.heal.framework.restAPI.OpsAPI;
import com.heal.framework.restAPI.PatientAPI;
import com.heal.framework.restAPI.VisitsAPI;
import com.heal.projects.ops.web.pages.CreateVisitPage;
import com.heal.projects.ops.web.pages.OpsLoginPage;
import com.heal.projects.ops.web.pages.OpsMenu;
import com.heal.projects.ops.web.pages.OpsVisitsPage;
import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *  Created by adrian.rosu on 18/08/2017.
 */
public class VisitsTest extends TestBase{
    /**
     * This will run a test in loop for validation purposes
     */
    private static  String sRandomUserEmail = "qa_auto_test_" + SysTools.getTimestamp("yyyy_MM_dd_HH-mm") +"@heal.com";
    @Test
    public void testLoop(){
        int numberOfVisitsToBook = 1;
        int passedRuns = 0;
        int failedRuns = 0;
        for (int i = 0; i < numberOfVisitsToBook; i++) {
            try {
//                System.out.println(map);
                bookVisitWithInsurance();
                //bookVisitWithCreditCard();
                passedRuns++;
            } catch (Exception e) {
                failedRuns++;
                e.printStackTrace();
            }
            System.out.println("Passed " + passedRuns);
            System.out.println("Failed " + failedRuns);
        }
        System.out.println(passedRuns + " Passed Runs"); // display how many times the test passed on given visits booked
        System.out.println(failedRuns + " Failed Runs"); // display how many times the test failed on given visits booked
    }

    @Test(groups = {"dev", "critical"})
    public void bookVisitWithInsurance() throws Exception {

        WebDriver dr =getDriver();
        dr.manage().window().maximize();
        OpsLoginPage loginPage= new OpsLoginPage(dr);
        OpsMenu menu=new OpsMenu(dr);
        OpsVisitsPage visitsPage=new OpsVisitsPage(dr);
        CreateVisitPage createVisit =new CreateVisitPage(dr);

        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login();
        menu.selectFromMenu("visits");
        visitsPage.waitForPageLoad();
        visitsPage.oAddVisitBtn.clickAndWait(createVisit.oEnterKeywordField, true);
        createVisit.oEnterKeywordField.sendKeys("vahan+qa");
        createVisit.oSearchingSuggestion.clickAndWait(createVisit.oSaveUserBtn,true);
        createVisit.oPhoneField.sendKeys("(818)212-3842");
        createVisit.selectPatientProfile("insurance");
        menu.verifyToastMessage("Verify patient profile was selected and saved", "Successfully Updated Patient");
        createVisit.saveAddress();
        menu.verifyToastMessage("Verify address is saved",  "Successfully Updated Address");
        createVisit.addVisitDetailsWithSickAdult();
        menu.verifyToastMessage("Verify visit details are added",  "Updated Visit Details");
        createVisit.scrollPage("Down");
        createVisit.oSelectPaymentMenu.click();
        createVisit.oFirstCardOption.click();
        createVisit.scrollPage("Down");

        createVisit.oVisitSummaryMenu.click();
        createVisit.oBookVisitBtn.clickAndWait(menu.oLoadingBar,false);
        menu.verifyToastMessage("Verify book visit success message",  "Successfully Created Visit");

    }


    @Test(groups = {"dev", "critical"})
    public void bookVisitWithCreditCard() throws Exception {
        WebDriver dr =getDriver();
        dr.manage().window().maximize();
        OpsLoginPage loginPage= new OpsLoginPage(dr);
        OpsMenu menu=new OpsMenu(dr);
        OpsVisitsPage visitsPage=new OpsVisitsPage(dr);
        CreateVisitPage createVisit =new CreateVisitPage(dr);

        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login();

        menu.selectFromMenu("visits");

        visitsPage.waitForPageLoad();
        visitsPage.oAddVisitBtn.clickAndWait(createVisit.oEnterKeywordField, true);

        createVisit.oEnterKeywordField.sendKeys("vahan+qa");
        createVisit.oSearchingSuggestion.clickAndWait(createVisit.oSaveUserBtn,true);
        createVisit.oPhoneField.sendKeys("(818)182-1238");

        createVisit.selectPatientProfile("credit card");
        menu.verifyToastMessage("Verify patient profile was selected and saved", "Successfully Updated Patient");

        createVisit.saveAddress();
        menu.verifyToastMessage("Verify address is saved",  "Successfully Updated Address");

        createVisit.addVisitDetailsWithSickAdult();
        menu.verifyToastMessage("Verify visit details are added",  "Updated Visit Details");
        createVisit.scrollPage("Down");
        createVisit.oSelectPaymentMenu.jsClick();
        createVisit.oFirstCardOption.click();
        createVisit.scrollPage("Down");

        createVisit.oVisitSummaryMenu.jsClick();
        createVisit.oBookVisitBtn.clickAndWait(menu.oLoadingBar,false);
        menu.verifyToastMessage("Verify book visit success message",  "Successfully Created Visit");
    }

    @Test(groups = {"dev", "critical"})
    public void bookVisitWithPromoCode() throws Exception {
        WebDriver dr =getDriver();
        dr.manage().window().maximize();
        OpsLoginPage loginPage= new OpsLoginPage(dr);
        OpsMenu menu=new OpsMenu(dr);
        OpsVisitsPage visitsPage=new OpsVisitsPage(dr);
        CreateVisitPage createVisit =new CreateVisitPage(dr);

        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login();

        menu.selectFromMenu("visits");

        visitsPage.waitForPageLoad();
        visitsPage.oAddVisitBtn.clickAndWait(createVisit.oEnterKeywordField, true);

        createVisit.oEnterKeywordField.sendKeys("vahan+qa");
        createVisit.oSearchingSuggestion.clickAndWait(createVisit.oSaveUserBtn,true);
        createVisit.oPhoneField.sendKeys("(818)182-1238");

        createVisit.selectPatientProfile("credit card");
        menu.verifyToastMessage("Verify patient profile was selected and saved", "Successfully Updated Patient");

        createVisit.saveAddress();
        menu.verifyToastMessage("Verify address is saved",  "Successfully Updated Address");

        createVisit.addVisitDetailsWithSickAdult();
        menu.verifyToastMessage("Verify visit details are added",  "Updated Visit Details");

        createVisit.scrollPage("Down");
        createVisit.oSelectPaymentMenu.jsClick();
        createVisit.oFirstCardOption.click();
        createVisit.scrollPage("Down");

        createVisit.oVisitSummaryMenu.jsClickAndWait(createVisit.oPromoCodeField,true);
        createVisit.oPromoCodeField.sendKeys("100PERCENT");
        createVisit.oApplyPromoBtn.click();

        verifyTextEquals("Verify correct promo code", createVisit.oPromo, "100PERCENT");
        verifyTextEquals("Verify correct visit price", createVisit.oPrice, "$99");
        verifyTextEquals("Verify correct discount price", createVisit.oDiscount, "-$99");
        verifyTextEquals("Verify total amount of visit price", createVisit.oTotal, "$0");
        createVisit.oBookVisitBtn.clickAndWait(menu.oLoadingBar,false);
        menu.verifyToastMessage("Verify book visit success message",  "Successfully Created Visit");
    }

}
