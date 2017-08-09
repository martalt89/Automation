package patient.tests;

import framework.test.TestBase;
import framework.test.TestData;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import patient.pages.*;

/**
 *  Test suite for Payments page
 */
public class PaymentsTest extends TestBase {
    private TestData addCardInputData = new TestData(TestData.CARD_SHEET);
    private String sExpirationMonth = Integer.toString(addCardInputData.iExpiryMonth);
    private String sExpirationYear = Integer.toString(addCardInputData.iExpiryYear);


    @Test (groups = { "dev" , "critical" })
    public void editPayments() throws Exception {

        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();
        CommonWebValidate validate = new CommonWebValidate(dr);
        LoginPage loginPage = new LoginPage(dr);
        loginPage.goTo();
        loginPage.waitForPageReady();
        HomePage homePage = new HomePage(dr);
        PaymentsPage paymentsPage = new PaymentsPage(dr);
        AddCardPage addCardPage = new AddCardPage(dr);

        Menu menu = new Menu(dr);

        // Login on patient web app
        loginPage.login();
        // Select Payments from Menu
        homePage.selectFromMenu(menu.oPaymentsLnk);
        paymentsPage.oEditPaymentBtn.click();

        // Add card details from excel test data
        addCardPage.oCardNumberInput.sendKeys(addCardInputData.sCardNumber);
        addCardPage.oCardExpirationInput.sendKeys(sExpirationMonth + sExpirationYear);
        addCardPage.oCVCInput.sendKeys(addCardInputData.sCVC);

        // Apply changes then wait for Payments page to load with new card details
        addCardPage.oApplyCardBtn.clickAndWait(paymentsPage.oPaymentsLabel, true);

        // Validate the new card details
        validate.assertEquals("Verifying card expiration date ", paymentsPage.oCardExpDate.getText(), sExpirationMonth + "/" + sExpirationYear);
    }
}
