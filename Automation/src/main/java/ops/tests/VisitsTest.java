package ops.tests;

import foundation.SysTools;
import framework.test.TestBase;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import ops.pages.*;
import org.testng.annotations.Test;


/**
 *  Created by adrian.rosu on 18/08/2017.
 */
public class VisitsTest extends TestBase{
    @Test (groups = {"smoke", "regression", "critical" })

    public void viewAllVisitsTest() throws Exception{
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        CreateVisitPage createVisitPage = new CreateVisitPage(dr);

        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login("mayur+oc@heal.com", "Heal@123");

        dr.navigate().to("https://ops.qa.heal.com/visits"); // temporary until Navigation Menu page object is done

        verifyVisible("Checking if the correct page was loaded", visitsPage.oAllVisitsTitle);
    }

    @Test (groups = {"smoke", "regression", "critical" })
    public void createNewVisitTest() throws Exception{
        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        OpsLoginPage loginPage = new OpsLoginPage(dr);
        OpsVisitsPage visitsPage = new OpsVisitsPage(dr);
        CreateVisitPage createVisitPage = new CreateVisitPage(dr);

        loginPage.goTo();
        loginPage.waitForPageLoad();
        loginPage.login("mayur+oc@heal.com", "Heal@123");

        dr.navigate().to("https://ops.qa.heal.com/visits"); // temporary until Navigation Menu page object is done

        visitsPage.oAddVisitBtn.clickAndWait(createVisitPage.oPageTitle, true);

        // method that creates user in Create user card
        createVisitPage.createUser("Adrian", "Rosu", "2015555555", "adrian.rosu@heal.com", "90210");

        // todo create patient -> create address -> add visit details -> add payments -> visit summary

    }
}
