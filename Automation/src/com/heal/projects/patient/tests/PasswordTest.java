package com.heal.projects.patient.tests;

import com.heal.framework.test.TestBase;
import com.heal.framework.web.CommonWebElement;
import com.heal.projects.patient.pages.HomePage;
import com.heal.projects.patient.pages.LoginPage;
import com.heal.projects.patient.pages.ResetPasswordPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class PasswordTest extends TestBase {


    /**
     * This will run a test in loop for validation purposes
     */
    @Test
    public void testLoop(){
        int numberOfVisitsToBook = 20;
        int passedRuns = 0;
        int failedRuns = 0;
        for (int i = 0; i < numberOfVisitsToBook; i++) {
            try {
                resetPassword(); // put here the desired test to be run on loop
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


    @Test
    public void resetPassword(){

        WebDriver dr = getDriver();
        CommonWebElement.setbMonitorMode(false);
        LoginPage loginPage = new LoginPage(dr);
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(dr);

        loginPage.goTo();
        loginPage.waitForPageReady();
        loginPage.oForgotYourPasswordLnk.click();
        resetPasswordPage.waitForPageReady();
        verifyVisible("Checking page title", resetPasswordPage.oForgotPasswordTitle);
        verifyVisible("Checking page subtitle", resetPasswordPage.oSendLinkText);
        resetPasswordPage.oEmailInput.sendKeys("vahan+qatest@heal.com");
        resetPasswordPage.oSubmitBtn.click();
        assertMatches("Check if the 'Check your email' text is displayed", resetPasswordPage.oForgotPasswordSubTitle.getText(), "We've sent you a link to reset your password." );
//        assertEquals("Check if the 'Check your email' text is diplayed", resetPasswordPage.oForgotPasswordSubTitle.getText(), "We've sent you a link to reset your password." );



    }

}
