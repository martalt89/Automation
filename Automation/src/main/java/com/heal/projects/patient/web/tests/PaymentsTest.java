package com.heal.projects.patient.web.tests;

import com.heal.framework.test.TestBase;
import com.heal.framework.test.TestData;
import com.heal.framework.web.CommonWebElement;
import com.heal.projects.patient.web.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 *  Test suite for Payments page
 */
public class PaymentsTest extends TestBase {
    private TestData addCardInputData = new TestData(TestData.CARD_SHEET);
    private String sExpirationMonth = Integer.toString(addCardInputData.iExpiryMonth);
    private String sExpirationYear = Integer.toString(addCardInputData.iExpiryYear);

    /**
     * Add / edit payment details
     * @throws Exception
     */
    @Test (groups = { "dev" , "critical" })
    public void editPayments() throws Exception {

        CommonWebElement.setbMonitorMode(false);
        WebDriver dr = getDriver();

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
        assertEquals("Verifying card expiration date ", paymentsPage.oCardExpDate.getText(), sExpirationMonth + "/" + sExpirationYear);
    }
}
