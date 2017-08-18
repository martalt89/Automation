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


    }
}
