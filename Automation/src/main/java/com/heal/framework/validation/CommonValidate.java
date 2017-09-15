package com.heal.framework.validation;

import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.heal.framework.test.TestBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.heal.framework.foundation.SysTools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * The Validation class verification methods are essentially wrappers around the
 * methods in TestNG Assert class to provide missing functionalities, additional logging, and
 * better control of test verification.  Test scripts should use the Validation class instead
 * of the Assert class.
 *
 *
 * */
public class CommonValidate
{
    private Logger logger = LoggerFactory.getLogger(CommonValidate.class);

    public static final String SCREENSHOT_LOCATION = "out/screenshots";

    public java.util.Vector<String> vFailures = new java.util.Vector<String>(10);
    public boolean bTakeShots = false;
    public int iVerificationsExecuted = 0;
    public ExtentTest oExtentTest = null;


    public CommonValidate()
    {
        bTakeShots = false;
    }

    public CommonValidate(boolean bTakeScreenshot)
    {
        bTakeShots = bTakeScreenshot;
    }

    public CommonValidate(boolean bTakeScreenshot, ExtentTest test)
    {
        bTakeShots = bTakeScreenshot;
        oExtentTest = test;
    }

    public void takeScreenshots(boolean bTakeScreenshot)
    {
        this.bTakeShots = bTakeScreenshot;
    }

    public String getScreenshot()
    {
        if (bTakeShots)
            return SysTools.getScreenshot(Paths.get(TestBase.projDir, SCREENSHOT_LOCATION).toString());
        else
            return "";
    }

    /**
     * This method wraps the TestNG assertEquals method to provide fail-but-continue functionality.
     *
     * @param sComment
     * (String) - Comment
     *
     * @param oActual
     * (Object) - Any type of input
     *
     * @param oExpected
     * (Object) - Any type of input
     */
    public boolean verifyEquals(String sComment, Object oActual, Object oExpected)
    {
        iVerificationsExecuted++;

        Object[] oArray = {sComment, oActual, oExpected};

        try
        {
            Assert.assertEquals(oActual, oExpected);
            oExtentTest.log(LogStatus.PASS, String.format("{%s} - verifyEquals(\"{%s}\", \"{%s}\") success!", oArray));
            return true;
        }
        catch(AssertionError ex)
        {
            oExtentTest.log(LogStatus.FAIL, String.format("{%s} - verifyEquals() failed! Actual: {%s}  Expected: {%s}", oArray));
            vFailures.add(sComment + " - verifyEquals() failed!  Actual: \"" + oActual + "\"  Expected: \"" + oExpected + "\"  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
    }

    /**
     * Wraps TestNG assertEquals method to provide more logging.
     *
     * @param sComment
     * (String) - Comment
     *
     * @param oActual
     * (Object) - Any type of input
     *
     * @param oExpected
     * (Object) - Any type of input
     */
    public void assertEquals(String sComment, Object oActual, Object oExpected)
    {
        iVerificationsExecuted++;

        Object[] oArray = {sComment, oActual, oExpected};

        try
        {
            Assert.assertEquals(oActual, oExpected);
            oExtentTest.log(LogStatus.PASS, String.format("{%s} - assertEquals({%s}, {%s}) success!", oArray));
        }
        catch(AssertionError ex)
        {
            oExtentTest.log(LogStatus.FAIL, String.format("{%s} - assertEquals() failed! Actual: {%s} Expected: {%s} ", oArray));
            fail("assertEquals() failed!  Actual: \"" + oActual + "\"  Expected: \"" + oExpected + "\"");
        }

    }
    /**
     * This method verifies that two string matches using regular expression
     *
     * @param sComment
     * (String) - Comment
     *
     * @param sActual
     * (String) - Actual string.
     *
     * @param sExpected
     * (String) - Regular expression.
     */
    public boolean verifyMatches(String sComment, String sActual, String sExpected)
    {
        iVerificationsExecuted++;

        String[] sArray = {sComment, sActual, sExpected};

        Pattern pattern = Pattern.compile(sExpected, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(sActual);

        if(matcher.matches())
        {
            oExtentTest.log(LogStatus.PASS, String.format("{%s} - verifyMatches({%s}, {%s}) success! ", sArray));
            return true;
        }
        else
        {
            oExtentTest.log(LogStatus.FAIL, String.format("{%s} - verifyMatches() failed! Actual: {%s}  Expected: {%s} <br>", sArray));
            vFailures.add(sComment + "- Verification failed!  Actual: \"" + sActual + "\"  Expected: \"" + sExpected + "\"  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
    }

    /**
     * This method asserts that two string matches using regular expression
     *
     * @param sComment
     * (String) - Comment
     *
     * @param sActual
     * (String) - Actual string.
     *
     * @param sExpected
     * (String) - Regular expression.
     */
    public void assertMatches(String sComment, String sActual, String sExpected)
    {
        iVerificationsExecuted++;

        String[] sArray = {sComment, sActual, sExpected};

        Pattern pattern = Pattern.compile(sExpected, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(sActual);

        if(matcher.matches()){
            oExtentTest.log(LogStatus.PASS, String.format("{%s} - assertMatches( Actual \"{%s}\",  \"{%s}\") success!", sArray));
        }
        else
        {
            fail("assertMatches() failed!  Actual: \"" + sActual + "\"  Expected: \"" + sExpected + "\"");
        }
    }

    /**
     * Wraps TestNG fail method.
     *
     * @param message
     * (String) - Error message.
     */
    public void fail(String message)
    {
        oExtentTest.log(LogStatus.FAIL, message);
        Assert.fail(message);
    }

    /**
     * Wraps TestNG fail method.
     */
    public void fail()
    {
        Assert.fail();
    }

    public void logFailure(String message)
    {
        oExtentTest.log(LogStatus.FAIL, String.format("Failure:  {%s} ", message));
        vFailures.add("Failure:  " + message);
    }

    /**
     * Wraps TestNG fail method.
     */
    public void fail(String message, Exception ex)
    {
        org.testng.Assert.fail(message, ex);
    }

    public void clearFailures()
    {
        vFailures.clear();
    }

    public java.util.Vector<String> getFailures()
    {
        return vFailures;
    }

    public int getFailureCount()
    {
        return vFailures.size();
    }

    public int getTotalCount()
    {
        return iVerificationsExecuted;
    }
}
