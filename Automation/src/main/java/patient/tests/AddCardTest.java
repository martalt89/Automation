package patient.tests;

import framework.test.TestBase;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import patient.pages.*;

/**
 * Created by mihai.muresan on 7/17/2017.
 */
public class AddCardTest extends TestBase {

    @Test
    @Parameters({ "url" })
    public void checkPofilesPageElements() throws Exception {
        WebDriver dr = getDriver();
        AddCardPage payment = new AddCardPage(dr);
        HomePage homePage = new HomePage(dr);
        LoginPage login = new LoginPage(dr);
        Menu menu = new Menu(dr);
        CommonWebValidate validate = new CommonWebValidate(dr);
        CommonWebElement.setbMonitorMode(false);
        login.login("mihaiqa1@heal.com", "Heal4325");
        homePage.selectFromMenu(menu.oPaymentsLnk);
        validate.verifyVisible("Verify Payments label is displayed", payment.oPaymentsLabel);


    }
}
