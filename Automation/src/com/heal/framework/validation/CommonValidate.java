package com.heal.framework.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.heal.foundation.SysTools;


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

//    public static final String SCREENSHOT_LOCATION = "/out/screenshots";
    public static final String SCREENSHOT_LOCATION = System.getProperty("user.dir") + System.getProperty("file.separator") + "out" + System.getProperty("file.separator");
    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CommonValidate.class);
    public java.util.Vector<String> vFailures = new java.util.Vector<String>(10);
    public boolean bTakeShots = false;
    public int iVerificationsExecuted = 0;

    public CommonValidate()
    {
        bTakeShots = false;
    }

    public CommonValidate(boolean bTakeScreenshot)
    {
        bTakeShots = bTakeScreenshot;
    }

    public void takeScreenshots(boolean bTakeScreenshot)
    {
        this.bTakeShots = bTakeScreenshot;
    }

    public String getScreenshot()
    {
        if (bTakeShots)
            return SysTools.getScreenshot(SCREENSHOT_LOCATION);
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
            org.testng.Assert.assertEquals(oActual, oExpected);
            logger.info("{} - verifyEquals(\"{}\", \"{}\") success!", oArray);
            return true;
        }
        catch(AssertionError ex)
        {
            logger.error("{} - verifyEquals() failed! Actual: \"{}\"  Expected: \"{}\"", oArray);
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
            org.testng.Assert.assertEquals(oActual, oExpected);
            logger.info("{} - assertEquals(\"{}\", \"{}\") success!", oArray);
        }
        catch(AssertionError ex)
        {
            logger.error("{} - assertEquals() failed! Actual: \"{}\"  Expected: \"{}\"", oArray);
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
            logger.info("{} - verifyMatches(\"{}\", \"{}\") success!", sArray);
            return true;
        }
        else
        {
            logger.error("{} - verifyMatches() failed! Actual: \"{}\"  Expected: \"{}\"", sArray);
            vFailures.add(sComment + "- verifyMatches() failed!  Actual: \"" + sActual + "\"  Expected: \"" + sExpected + "\"  [Screenshot:  " + getScreenshot() + "]");
            vFailures.add(sComment + "- verifyMatches() failed!  Actual: \"" + sActual + "\"  Expected: \"" + sExpected + "\"  [Screenshot:  " +  "]");
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

        if(matcher.matches())
            logger.info("{} - assertMatches(\"{}\", \"{}\") success!", sArray);
        else
        {
            logger.error("{} - assertMatches() failed! Actual: \"{}\"  Expected: \"{}\"", sArray);
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
        org.testng.Assert.fail(message);
    }

    /**
     * Wraps TestNG fail method.
     */
    public void fail()
    {
        org.testng.Assert.fail();
    }

    public void logFailure(String message)
    {
        logger.error("Failure:  {}", message);
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
