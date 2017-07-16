package framework.web;

import java.util.regex.Pattern;

import framework.exception.CommonException;
import framework.validation.CommonValidate;
import org.openqa.selenium.WebDriver;
import foundation.SysTools;

/**
 * The Validation class verification methods are essentially wrappers around the
 * methods in TestNG Assert class to provide missing functionalities, additional logging, and
 * better control of test verification.  Test scripts should use the Validation class instead
 * of the Assert class.
 *
 *
 * */
public class CommonWebValidate extends CommonValidate
{
    private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CommonWebValidate.class);
    private WebDriver oWebDriver = null;
    private CommonWebElement oCommonWebElement = null;

    public CommonWebValidate(WebDriver oDriver)
    {
        super();
        oWebDriver = oDriver;
    }

    public CommonWebValidate(WebDriver oDriver, boolean bTakeScreenshot)
    {
        super(bTakeScreenshot);
        oWebDriver = oDriver;
    }

    public void takeScreenshots(boolean bTakeScreenshot)
    {
        bTakeShots = bTakeScreenshot;
    }

    @Override
    public String getScreenshot()
    {
        String screenshot = "";

        if (bTakeShots && oCommonWebElement != null && oCommonWebElement.getWebElement() != null)
        {
            try
            {
                oCommonWebElement.highlightMe();
                SysTools.sleepFor(1);
                screenshot = WebBase.getScreenshot(oWebDriver, SCREENSHOT_LOCATION);
                oCommonWebElement.unHighlightMe();
                oCommonWebElement = null;
            }
            catch(Exception ex)
            {
                screenshot = WebBase.getScreenshot(oWebDriver, SCREENSHOT_LOCATION);
            }
        }
        else if (bTakeShots)
        {
            screenshot = WebBase.getScreenshot(oWebDriver, SCREENSHOT_LOCATION);
        }

        return screenshot;
    }

    /**
     * Verifies a page object is visible.
     *
     * @param sComment
     * (String) - Comment.
     *
     * @param oTarget
     * (CommonWebElement) - Web element to check for visibility.
     */
    public boolean verifyVisible(String sComment, CommonWebElement oTarget)
    {
        iVerificationsExecuted++;
        oCommonWebElement = oTarget;

        try
        {
            org.testng.Assert.assertEquals(oTarget.isDisplayed(), true);
            logger.info("{} - verifyVisible(\"{}\") success!", sComment, oTarget.getElementName());
            return true;
        }
        catch(AssertionError ex)
        {
            logger.error("{} - verifyVisible(\"{}\") failed!", sComment, oTarget.getElementName());
            vFailures.add(sComment + " - verifyVisible(" + oTarget.getElementName() + ") failed!  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
        catch(CommonException ex)
        {
            logger.error("{} - verifyVisible() failed! Target element not found!");
            vFailures.add(sComment + " - verifyVisible() failed!  Target element not found!  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
    }

    /**
     * Verifies a page object is invisible.
     *
     * @param sComment
     * (String) - Comment.
     *
     * @param oTarget
     * (CommonWebElement) - Web element to check for visibility.
     */
    public boolean verifyInvisible(String sComment, CommonWebElement oTarget)
    {
        iVerificationsExecuted++;
        oCommonWebElement = oTarget;

        try
        {
            org.testng.Assert.assertEquals(oTarget.isDisplayed(3), false);
            logger.info("{} - verifyInvisible(\"{}\") success!", sComment, oTarget.getElementName());
            return true;
        }
        catch(AssertionError ex)
        {
            logger.error("{} - verifyInvisible(\"{}\") failed!", sComment, oTarget.getElementName());
            vFailures.add(sComment + "- verifyInvisible(" + oTarget.getElementName() + ") failed!  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
    }

    /**
     * Verifies a page object is in view.
     *
     * @param sComment
     * (String) - Comment.
     *
     * @param oTarget
     * (CommonWebElement) - Web element to check for.
     */
    public boolean verifyViewable(String sComment, CommonWebElement oTarget)
    {
        iVerificationsExecuted++;
        oCommonWebElement = oTarget;

        try
        {
            org.testng.Assert.assertEquals(oTarget.isViewable(), true);
            logger.info("{} - verifyViewable(\"{}\") success!", sComment, oTarget.getElementName());
            return true;
        }
        catch(AssertionError ex)
        {
            logger.error("{} - verifyViewable(\"{}\") failed!", sComment, oTarget.getElementName());
            vFailures.add(sComment + " - verifyViewable(" + oTarget.getElementName() + ") failed!  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
        catch(CommonException ex)
        {
            logger.error("{} - verifyViewable() failed! Target element not found!");
            vFailures.add(sComment + " - verifyViewable() failed!  Target element not found!  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
    }

    /**
     * Verifies a page link navigates successful to new page with the expected URL within the same window/tab.
     *
     * @param sComment
     * (String) - Comment.
     *
     * @param oStartPage
     * (WebBase) - Starting page where the link is located.
     *
     * @param oLink
     * (CommonWebElement) - Link object to test.
     *
     * @param sExpectedURL
     * (String) - Expected URL of the new page.
     */
    public boolean verifyLinkNavigation(String sComment, WebBase oStartPage, CommonWebElement oLink, String sExpectedURL)
    {
        iVerificationsExecuted++;
        oCommonWebElement = oLink;
        String sActualURL = "";

        try
        {
            oLink.click();
            oStartPage.waitForPageLoad();
            sActualURL = oStartPage.getCurrentUrl();
            org.testng.Assert.assertEquals(Pattern.matches(sExpectedURL, sActualURL), true);
            logger.info(sComment + " - verifyLink(\"{}\", \"{}\") success!", oLink.getElementName(), sExpectedURL);
            return true;
        }
        catch(AssertionError ex)
        {
            logger.error(sComment + " - verifyLink failed! Actual: \"{}\"  Expected: \"{}\"", sActualURL, sExpectedURL);
            vFailures.add(sComment + " - verifyLink failed! Actual: \"" + sActualURL + "\"  Expected: \"" + sExpectedURL + "\"   [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
        catch(CommonException ex)
        {
            logger.error("{} - verifyLinkNavigation() failed! Target element not found!");
            vFailures.add(sComment + " - verifyLinkNavigation() failed!  Target element not found!  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
    }

    /**
     * Verifies a page link opens a new window/tab/popup with the expected URL.
     *
     * @param sComment
     * (String) - Comment.
     *
     * @param oStartPage
     * (WebBase) - Starting page where the link is located.
     *
     * @param oLink
     * (CommonWebElement) - Link object to test.
     *
     * @param sExpectedURL
     * (String) - Expected URL of newly open window/tab/popup.
     */
    public boolean verifyLinkPopup(String sComment, WebBase oStartPage, CommonWebElement oLink, String sExpectedURL)
    {
        iVerificationsExecuted++;
        oCommonWebElement = oLink;
        String sActualURL = "";

        try
        {
            oLink.click();
            oStartPage.getPopup();
            oStartPage.waitForUrlNot("");
            sActualURL = oStartPage.getCurrentUrl();
            org.testng.Assert.assertEquals(Pattern.matches(sExpectedURL, sActualURL), true);
            logger.info(sComment + " - verifyLink(\"{}\", \"{}\") success!", oLink.getElementName(), sExpectedURL);
            oStartPage.closePopup();
            return true;
        }
        catch(AssertionError ex)
        {
            logger.error(sComment + " - verifyLink failed! Actual: \"{}\"  Expected: \"{}\"", sActualURL, sExpectedURL);
            vFailures.add(sComment + " - verifyLink failed! Actual: \"" + sActualURL + "\"  Expected: \"" + sExpectedURL + "\"   [Screenshot:  " + getScreenshot() + "]");
            oStartPage.closePopup();
            return false;
        }
        catch(CommonException ex)
        {
            logger.error("{} - verifyLinkPopup() failed! Target element not found!");
            vFailures.add(sComment + " - verifyLinkPopup() failed!  Target element not found!  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
    }

    /**
     * Verifies attribute value of an element.  We strip all spaces and quotes and convert all text to lower case before comparison.  This is done to accommodate different
     * browsers.
     *
     * @param sComment
     *  (String) - Comment.
     *
     * @param oTarget
     * (CommonWebElement) - Target element.
     *
     * @param sAttribName
     * (String) - Attribute name.
     *
     * @param sExpectedValue
     * (String) - Expected attribute value.
     */
    public boolean verifyAttribute(String sComment, CommonWebElement oTarget, String sAttribName, String sExpectedValue)
    {
        oCommonWebElement = oTarget;

        try
        {
            return verifyMatches(sComment + " - Verify Attribute '" + sAttribName + "'", oTarget.getAttribute(sAttribName).replaceAll("\\s", "").replaceAll("\"", "").replaceAll("'", "").toLowerCase(), sExpectedValue.replaceAll("\\s", "").toLowerCase());
        }
        catch(CommonException ex)
        {
            logger.error("{} - verifyAttribute() failed! Target element not found!");
            vFailures.add(sComment + " - verifyAttribute() failed!  Target element not found!  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
    }

    /**
     * Verifies CSS property value of an element.   We strip all spaces and quotes and convert all text to lower case before comparison.  This is done to accommodate different
     * browsers.
     *
     * @param sComment
     *  (String) - Comment.
     *
     * @param oTarget
     * (CommonWebElement) - Target element.
     *
     * @param sCssProp
     * (String) - CSS property name.
     *
     * @param sExpectedValue
     * (String) - Expected CSS property value.
     */
    public boolean verifyCssValue(String sComment, CommonWebElement oTarget, String sCssProp, String sExpectedValue)
    {
        oCommonWebElement = oTarget;

        try
        {
            return verifyMatches(sComment + " - Verify CSS property '" + sCssProp + "'", oTarget.getCssValue(sCssProp).replaceAll("\\s", "").replaceAll("\"", "").replaceAll("'", "").toLowerCase(), sExpectedValue.replaceAll("\\s", "").toLowerCase());
        }
        catch(CommonException ex)
        {
            logger.error("{} - verifyCssValue() failed! Target element not found!");
            vFailures.add(sComment + " - verifyCssValue() failed!  Target element not found!  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
    }

    /**
     * Verifies the CSS properties font, font-size, color, and the text of an element.
     *
     * @param sComment
     *  (String) - Comment.
     *
     * @param oTarget
     * (CommonWebElement) - Target element.
     *
     * @param sExpectedText
     * (String) - Expected text.
     *
     * @param sExpectedFont
     * (String) - Expected font.
     *
     * @param sExpectedSize
     * (String) - Expected font-size.  Represented in px.  Different browser could return different numbers.  E.g., 13px vs 13.33px.  So use a pattern match.
     *
     * @param sExpectedColor
     * (String) - Expected color.  Represented in rgba(r, g, b, a).  E.g., the opaque color red is rgba(256, 0, 0, 1).  The "(" and ")" need to be escaped such as "\\(".
     */
    public boolean verifyElementText(String sComment, CommonWebElement oTarget, String sExpectedText, String sExpectedFont, String sExpectedSize, String sExpectedColor)
    {
        oCommonWebElement = oTarget;

        return verifyVisible(sComment, oTarget) && verifyMatches(sComment, oTarget.getText(), sExpectedText) & verifyCssValue(sComment, oTarget, "font-family", sExpectedFont) & verifyCssValue(sComment, oTarget, "font-size", sExpectedSize) & verifyCssValue(sComment, oTarget, "color", sExpectedColor);
    }

    /**
     * Verify the text contain in element equal to expected text.
     *
     * @param sComment
     * (String) - Comment.
     *
     * @param oTarget
     * (CommonWebElement) - Target element.
     *
     * @param sExpected
     * (String) - Expected text.
     *
     * @return
     * (boolean) - true if equals, else false.
     */
    public boolean verifyTextEquals(String sComment, CommonWebElement oTarget, String sExpected)
    {
        oCommonWebElement = oTarget;

        try
        {
            return verifyEquals(sComment, oTarget.getText(), sExpected);
        }
        catch(CommonException ex)
        {
            logger.error("{} - verifyTextEquals() failed! Target element not found!");
            vFailures.add(sComment + " - verifyTextEquals() failed!  Target element not found!  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
    }

    /**
     * Verify the text contain in element matches to expected pattern.
     *
     * @param sComment
     * (String) - Comment.
     *
     * @param oTarget
     * (CommonWebElement) - Target element.
     *
     * @param sExpected
     * (String) - Expected pattern.
     *
     * @return
     * (boolean) - true if matches, else false.
     */
    public boolean verifyTextMatches(String sComment, CommonWebElement oTarget, String sExpected)
    {
        oCommonWebElement = oTarget;

        try
        {
            return verifyMatches(sComment, oTarget.getText(), sExpected);
        }
        catch(CommonException ex)
        {
            logger.error("{} - verifyTextMatches() failed! Target element not found!");
            vFailures.add(sComment + " - verifyTextMatches() failed!  Target element not found!  [Screenshot:  " + getScreenshot() + "]");
            return false;
        }
    }

    public void logFailure(String message)
    {
        logger.error("Failure:  {}", message);
        vFailures.add("Failure:  " + message + "  [Screenshot:  " + getScreenshot() + "]");
    }
}
