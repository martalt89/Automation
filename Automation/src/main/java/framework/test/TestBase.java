package framework.test;

import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import framework.exception.CommonException;
import framework.web.CommonWebElement;
import framework.web.CommonWebValidate;
import framework.web.WebBase;
import framework.validation.*;
import foundation.*;
import org.apache.commons.codec.binary.Base64;
import com.google.gson.JsonObject;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.ScreenshotException;
import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.*;

/**
 * This is the base class for all web tests to provide common/convenient methods
 *
 *
 */

public class TestBase
{

    ////////////////////////
    //  Class members     //
    ////////////////////////	

    private boolean bGridMode = false;
    private String sGridServerUrl = "";
    //	private int iPageLoadTimeout = 90;
    private boolean bMaximizeBrowser = false;
    private String[] aBrowsers;

    /**
     * InheritableThreadLocal variables are needed when tests are ran in parallel.  They ensure threads do not share/contaminate each other's data.  
     * oCurrentDriver is used to store the WebDriver instance per test thread.  
     * oCommonValidate stores CommonValidate object per test thread.
     * oRemoteNode stores information about the remote machine the test thread is running against.
     * oException stores exceptions per test thread.
     */

    private static final InheritableThreadLocal oCurrentDriver = new InheritableThreadLocal();
    private static final InheritableThreadLocal oValidate = new InheritableThreadLocal();
    private static final InheritableThreadLocal oRemoteNode = new InheritableThreadLocal();
    private static final InheritableThreadLocal oException = new InheritableThreadLocal();

    ////////////////////////
    //  Constructors      //
    ////////////////////////

    public TestBase()
    {
    }

    ////////////////////////////
    //  Member access methods //
    ////////////////////////////

    /**
     * Sets GridMode class member.
     *
     * @param sGridServer
     * (Boolean)
     */
    public void setGridServer(String sGridServer)
    {
        sGridServerUrl = sGridServer;
    }

    public String[] getBrowsers()
    {
        return aBrowsers;
    }

    /**
     * Stores WebDriver instance in InheritableThreadLocal variable.
     *
     * @param oDriver
     * (WebDriver)
     */
    public void setDriver(WebDriver oDriver)
    {
        oCurrentDriver.set(oDriver);
    }

    /**
     * Removes value from InheritableThreadLocal variable.
     */
    public void unsetDriver()
    {
        oCurrentDriver.remove();
    }

    /**
     * Retrieves WebDriver instance stored in InheritableThreadLocal variable or throws an exception if
     * any thread exception is found.
     *
     * @return
     * (WebDriver)
     */
    public WebDriver getDriver()
    {
        if (getException() == null)
            return (WebDriver)oCurrentDriver.get();
        else
            throw getException();
    }

    /**
     * Stores CommonValidate instance in InheritableThreadLocal variable.
     *
     * @param oV
     * (CommonValidate)
     */
    public void setValidate(CommonValidate oV)
    {
        oV.iVerificationsExecuted = 0;
        oValidate.set(oV);
    }

    /**
     * Removes value from InheritableThreadLocal variable.
     */
    public void unsetValidate()
    {
        oValidate.remove();
    }

    /**
     * Retrieves CommonValidate instance stored in InheritableThreadLocal variable.
     *
     * @return
     * (CommonValidate)
     */
    public CommonWebValidate getValidate()
    {
        return (CommonWebValidate)oValidate.get();
    }

    /**
     * Stores remote node info in InheritableThreadLocal variable.
     *
     * @param sInfo
     * (String)
     */
    public void setRemoteNode(String sInfo)
    {
        oRemoteNode.set(sInfo);
    }

    /**
     * Removes value from InheritableThreadLocal variable.
     */
    public void unsetRemoteNode()
    {
        oRemoteNode.remove();
    }

    /**
     * Retrieves remote node info stored in InheritableThreadLocal variable.
     *
     * @return
     * (String)
     */
    public String getRemoteNode()
    {
        return (String)oRemoteNode.get();
    }

    public void setException(Exception ex)
    {
        oException.set(ex);
    }

    public CommonException getException()
    {
        return (CommonException)oException.get();
    }

    public void unsetException()
    {
        oException.remove();
    }


    ////////////////////////
    //  TestNG methods    //
    ////////////////////////	

    /**
     * Responsible for setting all the test class properties and instantiating an HealEntityManager to be shared by all tests.
     */
    @BeforeClass(alwaysRun=true)
    @Parameters({ "grid_mode", "grid_server_url", "target_browsers", "firefox_profile", "element_action_throttle", "element_implicit_wait", "monitor_mode", "environment", "maximize_browser" })
    public void setup(@Optional("false") String grid_mode, @Optional("") String grid_server_url, @Optional("") String target_browsers, @Optional("") String firefox_profile, @Optional("0") String element_action_throttle, @Optional("60") String element_implicit_wait, @Optional("false") String monitor_mode, @Optional("config") String environment, @Optional("false") String maximizeBrowser)
    {
        MDC.put("threadID", String.valueOf(Thread.currentThread().getId()));

        try
        {
            // Grid mode
            bGridMode = Boolean.parseBoolean(grid_mode);
            //logger.info("setup():  Grid mode:  {}", bGridMode);

            // Grid server url
            if (bGridMode)
            {
                if (grid_server_url.equals(""))
                    throw new SkipException("Parameter 'grid_server_url' must be provided when running in grid mode!");
                sGridServerUrl = grid_server_url;
                //logger.info("setup():  Grid server URL:  {}", sGridServerUrl);
            }

            // Target browsers
            target_browsers = target_browsers.replaceAll("\\s", "");
            aBrowsers = target_browsers.split(",");
            //logger.info("setup():  Target browsers:  {}", target_browsers);

            // Maximize browser
            if (maximizeBrowser.equalsIgnoreCase("true"))
                bMaximizeBrowser = true;

            // Set time to wait for a page to load
            //iPageLoadTimeout = Integer.parseInt(oProp.getProperty("page_load_timeout", "90"));

            // Set global throttling value for CommonWebElement
            framework.web.CommonWebElement.setThrottle(Integer.parseInt(element_action_throttle));
            //logger.info("setup():  Element action throttle:  {} sec", element_action_throttle);

            // Set global implicit wait value for CommonWebElement
            framework.web.CommonWebElement.setImplicitWait(Integer.parseInt(element_implicit_wait));
            //logger.info("setup():  Element implicit wait:  {} sec", element_implicit_wait);

            // Turn on monitor mode if set
            if (monitor_mode.equalsIgnoreCase("true"))
                CommonWebElement.setbMonitorMode(true);

        }
        catch(Exception ex)
        {
            throw new CommonException(ex);
        }

    }

    /**
     * Post test class clean up.  Close HealEntityManager.
     */
    @AfterClass(alwaysRun=true)
    public void teardown()
    {

    }

    /**
     * This TestNG BeforeMethod is responsible for creating the WebDriver/RemoteWebDriver instance and making it available to
     * the test method.  Also stores the instance in InheritableThreadLocal.
     *
     * This method must never fail.  By design, TestNG will skip all subsequent test methods if the BeforeMethod fails.  However,
     * we use BeforeMethod before each test to create separate instances of WebDriver for test.  So one BeforeMethod failure should
     * not cause subsequent tests to be skipped.  Our solution is to catch all exception and let the test method handle any failure to
     * create WebDriver.
     *
     * @param aParams
     * (Object[]) - Parameters that will be passed into the test method by DataProvider.
     *
     * @param oMethod
     * (Method) - The Method object for the target test method.
     *
     * @throws Exception
     */
    @BeforeMethod(alwaysRun=true)
    public void beforeMethod(Object[] aParams, java.lang.reflect.Method oMethod) throws Exception
    {
        MDC.put("threadID", String.valueOf(Thread.currentThread().getId()));

        //logger.info("Executing: {} Parameters: {}", oMethod.getName(), aParams);

        if (aParams.length == 0)
        {
            setValidate(new CommonValidate(false));
            return;
        }

        try
        {
            WebDriver oDriver = null;

            if (bGridMode)
                oDriver = StartRemoteWebDriver((String)aParams[0], sGridServerUrl);
            else
                oDriver = StartWebDriver((String)aParams[0]);

            // Only supported for Firefox in current release.
            //oDriver.manage().timeouts().pageLoadTimeout(iPageLoadTimeout, java.util.concurrent.TimeUnit.SECONDS);

            if (bMaximizeBrowser)
                oDriver.manage().window().maximize();

            // Store WebDriver, Validate, and RemoteNode instances in the InheritableThreadLocal variable so each thread has own copy.  A must for parallel execution.
            setDriver(oDriver);
            setValidate(new CommonWebValidate(oDriver, true));
//            if (bGridMode)
//                setRemoteNode(getRemoteWebDriverNode(((RemoteWebDriver)oDriver).getSessionId().toString(), sGridServerUrl));
        }
        //  We want to catch all exceptions here and return because if beforeMethod() fails (e.g., due to WebDriver instantiation error), subsequent tests in the same
        //  thread would fail also.  This affects DataProvider-driven tests.
        catch (Exception ex)
        {
            // Store any WebDriver related exception for the thread, so it can be retrieved later.
            setException(new CommonException(ex));
        }
    }

    /**
     * This TestNG AfterMethod provides cleanup for the test method.  Destroys the WebDriver instance and clears the failure queue.
     *
     * @param oResult
     * (ITestResult) - TestNG Result object.
     */
    @AfterMethod(alwaysRun=true)
    public void afterMethod(org.testng.ITestResult oResult)
    {
        MDC.put("threadID", String.valueOf(Thread.currentThread().getId()));

        try
        {
            //String sStatus = oResult.getStatus() == org.testng.ITestResult.SUCCESS?"SUCCESS":"FAILURE";
            //logger.trace("afterMethod():  ");
            unsetValidate();
            unsetRemoteNode();
            unsetException();
            quitDriver();
        }
        catch (Exception ex)
        {
            //logger.error("afterMethod():  Exception caught!  ", ex);
        }
    }

    @DataProvider(parallel = true)
    public Object[][] GetTestParameters(java.lang.reflect.Method oMethod, ITestContext context)
    {
        MDC.put("threadID", String.valueOf(Thread.currentThread().getId()));

        // Get the number of formal parameters this test method has
        int nbrOfFormalParams = oMethod.getParameterTypes().length;

        ///////////////////////////////
        // Target browser processing //
        ///////////////////////////////

        // Used to find browser type to exclude by looking at the groups element of Test annotation
        String[] groups = oMethod.getAnnotation(Test.class).groups();
        //logger.trace("GetTestParameters():  Target groups:  {}, {}, {}, {}", aBrowsers);

        String[] aBrowsers = getBrowsers();
        //logger.trace("GetTestParameters():  Target browsers:  {}, {}, {}, {}", aBrowsers);

        // Remove from target browswer array the browser that we need to skip
        boolean match = false;
        ArrayList<String> browsersToRun = new ArrayList<String>();
        for (String browser : aBrowsers)
        {
            //logger.trace("GetTestParameters():  Target browser: {}", browser);

            for (String group : groups)
            {
                //logger.trace("GetTestParameters():  Target group: {}", group);

                if(group.equalsIgnoreCase("Exclude" + browser.trim()))
                {
                    //logger.trace("GetTestParameters():  Found matching Exclude<Browser>", group);
                    match = true;
                    break;
                }
                else
                    match = false;
            }

            if (!match)
            {
                //logger.trace("GetTestParameters():  Add {} to target browsers to run", browser.trim());
                browsersToRun.add(browser.trim());
            }
        }

        Object[] targetBrowsers = browsersToRun.toArray();

        ///////////////////////////////
        // Test parameter processing //
        ///////////////////////////////

        // Get parameters for test case
        String sTestParams = "";
        List<Properties> listOfProperties = new ArrayList<Properties>();
        int iLength = targetBrowsers.length;  // Number of iteration based on number of target browsers.

        // If test method contains two parameters, then we know it's looking for a properties object as the second parameter.
        if (nbrOfFormalParams == 2)
        {
            // Read the parameters string from TestNG suite by looking for TestNG suite parameter with the name in form of <TestClass>.<TestMethod>  
            // For example, "<parameter name="patient.tests.LoginTest" value=""/>"
            sTestParams = context.getCurrentXmlTest().getParameter(context.getCurrentXmlTest().getName() + "." + oMethod.getName());

            if (sTestParams == null || sTestParams.equals(""))  // If no TestNG suite parameter name found.  Create an empty Properties object.
                listOfProperties.add(new Properties());
            else
                listOfProperties = strToProperties(sTestParams);  // Call strToProperties() to parse the parameter string.

            iLength = iLength * listOfProperties.size();  // If multiple property sets are found, number of iteration becomes number of property set times number of target browsers.
        }

        // Create and build object array to contain all the test parameters for each iteration. For example, 
        // 
        // with parameter string ""
        // 
        // input[0][0] = "Chrome"
        // input[0][1] = prop1
        //
        // input[1][0] = "Chrome"
        // input[1][1] = prop2
        //
        // input[2][0] = "Firefox"
        // input[2][1] = prop1
        //
        // input[3][0] = "Firefox"
        // input[3][1] = prop2
        //
        // where prop1 contains properties "" and prop2 contains properties "".  So the same test method would get
        // executed a total of four times:  (2 browsers) X (2 parameter sets) = 4.

        Object[][] input = new Object[iLength][nbrOfFormalParams];

        for (int i=0, j=0; i < targetBrowsers.length; i++)
        {
            if (nbrOfFormalParams == 2)
            {
                for (int k = 0; k < listOfProperties.size(); k++)
                {
                    input[j][0] = targetBrowsers[i];
                    input[j][1] = listOfProperties.get(k);
                    j++;
                }
            }
            else
                input[j++][0] = targetBrowsers[i];
        }

        return input;
    }

    //////////////////////////////
    //  Misc helpers methods    //
    //////////////////////////////

    /**
     * Generic exception handler.  Logs exception and creates screenshot.
     *
     * @param ex
     * (Throwable) - Source exception.
     */
    public String handleException(Throwable ex)
    {
        try
        {
            //logger.error("handleException(): Exception caught!  ", ex);

            if (!(ex instanceof NullPointerException))
            {
                if(ex instanceof framework.exception.CommonException)    		// No screenshot necessary.  
                    return "";

                Throwable cause = ex.getCause();
                if (cause instanceof ScreenshotException)
                    return getScreenshotFromException((ScreenshotException)cause);
                else
                    return getValidate().getScreenshot();
            }
            else
            {
                return getValidate().getScreenshot();
            }
        }
        catch(Exception e)
        {
        }
        return "";
    }

    public String getScreenshotFromException(ScreenshotException ex) throws java.io.FileNotFoundException, java.io.IOException
    {
        byte[] aBase64;
        aBase64 = Base64.decodeBase64(ex.getBase64EncodedScreenshot());
        String fullFilePath = "C:/QA/ATF/out/screenshots/" + SysTools.getTimestamp() + ".png";
        FileOutputStream fOut = new FileOutputStream(fullFilePath);
        fOut.write(aBase64);
        fOut.close();
        //logger.info("Screenshot sent to {}", fullFilePath);
        return fullFilePath;
    }

    /**
     * Checks if there is any verifyXXX failures.  Resets failure count and
     * fails current test.
     */
    public void checkVerificationFailure()
    {
        int iCount = getValidate().getFailureCount();

        if (iCount > 0)
            getValidate().fail("Found verification failures");
//		{
//			StringBuilder errorString = new StringBuilder();
//			
//			for(String sError : getValidate().getFailures())
//			{
//				errorString.append(sError);
//				errorString.append("\n");
//			}
//			
//			getValidate().fail("Found verification failures (" + iCount + ") out of total (" + getValidate().getTotalCount() + ")! \n\n" + errorString.toString());
//		}
    }

    /**
     * Instantiate browser specific WebDriver based on browser type.
     *
     * @param sBrowserType
     * (String)
     *
     * @return
     * (WebDriver)
     *
     * @throws Exception
     */
    public WebDriver StartWebDriver(String sBrowserType) throws Exception
    {
        try
        {
            switch (sBrowserType.toUpperCase())
            {
                case "CHROME":
                    return new org.openqa.selenium.chrome.ChromeDriver();
                case "IE":
                    DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
                    dc.setCapability("nativeEvents", true);
                    return new org.openqa.selenium.ie.InternetExplorerDriver(dc);
                case "FIREFOX":
//					FirefoxProfile profile = new FirefoxProfile(new java.io.File(sFirefoxProfile));
                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setEnableNativeEvents(false);
                    return new org.openqa.selenium.firefox.FirefoxDriver(profile);
//                case "ANDROID":
//                    return new org.openqa.selenium.android.AndroidDriver();
                case "SAFARI":
                    return new org.openqa.selenium.safari.SafariDriver();
                case "HTML":
                    DesiredCapabilities dCaps = DesiredCapabilities.phantomjs();
                    dCaps.setJavascriptEnabled(true);
                    dCaps.setCapability("takesScreenshot", false);
                    return new PhantomJSDriver(dCaps);
                default:
                    throw new CommonException("Browser type " + sBrowserType + " not found!");
            }
        }
        catch (Exception e)
        {
            throw new Exception("Failed to instantiate WebDriver!", e);
        }
    }

    /**
     * Instantiate RemoteWebDriver based on browser type.  This is used for Grid.
     *
     * @param sBrowser
     * (String)
     *
     * @return
     * (WebDriver)
     *
     * @throws Exception
     */
    public WebDriver StartRemoteWebDriver(String sBrowser, String sGridUrl) throws Exception
    {

        DesiredCapabilities oRequestCapability;
        RemoteWebDriver oRemoteWebDriver;
        String[] aBrowserInfo = sBrowser.split("\\|");   // Browser info consist of BrowserType|Version

        switch (aBrowserInfo[0].toUpperCase())
        {
            case "CHROME":
                oRequestCapability = DesiredCapabilities.chrome();
                oRequestCapability.setCapability("chrome.switches", java.util.Arrays.asList("--start-maximized"));
                break;
            case "IE":
                oRequestCapability = DesiredCapabilities.internetExplorer();
                oRequestCapability.setCapability("nativeEvents", false);
                break;
            case "FIREFOX":
                FirefoxProfile profile = new FirefoxProfile();
                oRequestCapability = DesiredCapabilities.firefox();
                oRequestCapability.setCapability(org.openqa.selenium.firefox.FirefoxDriver.PROFILE, profile);
                break;
            case "ANDROID":
                oRequestCapability = DesiredCapabilities.android();
                break;
            case "SAFARI":
                oRequestCapability = DesiredCapabilities.safari();
                break;
            case "HTML":
                oRequestCapability = DesiredCapabilities.htmlUnit();
                break;
            default:
                return null;
        }

        try
        {
            if (aBrowserInfo.length > 1)
                oRequestCapability.setCapability("version", aBrowserInfo[1]);
            oRequestCapability.setPlatform(Platform.ANY);
            oRemoteWebDriver =  new RemoteWebDriver(new URL(sGridUrl), oRequestCapability);
            Capabilities oTargetCapability = oRemoteWebDriver.getCapabilities();
            //logger.trace("Target driver capabilities:  {}", oTargetCapability.toString());
            if (!aBrowserInfo[0].equalsIgnoreCase("Chrome"))
                oRemoteWebDriver.manage().window().maximize();

            return oRemoteWebDriver;
        }
        catch (Exception e)
        {
            throw new CommonException("Failed to instantiate RemoteWebDriver!", e.getCause());
        }
    }

    /**
     * Destroys current WebDriver instance.
     */
    public void quitDriver()
    {
        try
        {
            WebDriver oDriver = getDriver();
            unsetDriver();

            if (oDriver != null)
                oDriver.quit();
        }
        catch(Exception ex)
        {
        }
    }

    /**
     * Method to mark a test as skip 
     * @param sSkipReason
     */
    public void skipTest(String sSkipReason)
    {
        throw new SkipException(sSkipReason);
    }

    /**
     * Parse a given input string of the form "<key>=<value>,<key>=<value>...|<key>=<value>|..." comma delimited key/value pairs delimited by pipe "|" character.  E.g.,
     *
     * where each properties set (e.g., ) is inserted into a new Properties object and the object inserted into a List.  Each property set of the list
     * is then use by the GetTestParameters() method as the properties for multiple iterations of the same test method.
     *
     * @param sInput
     * (String) - String containing set of comma delimited name/value pairs delimited by pipe character.
     *
     * @return
     * (List<Properties>) - return list of properties object.
     */
    public List<Properties> strToProperties(String sInput)
    {
        //logger.trace("Input:  {}", sInput);

        List<Properties> listOfProps= new ArrayList<Properties>();
        Properties prop;

        // Split parameter sets with "|"
        String[] propSets = sInput.split("\\|");
        String[] pairs;
        String[] nameValue;

        for (String propSet : propSets)
        {
            //logger.trace("Found properties set:  {}", propSet);

            prop = new Properties();
            propSet = propSet.trim();
            pairs = propSet.split(",");

            for (String pair : pairs)
            {
                //logger.trace("Found name/value pair:  {}", pair);

                pair = pair.trim();
                nameValue = pair.split("=");
                prop.put(nameValue[0], nameValue[1]);
            }

            listOfProps.add(prop);
        }

        return listOfProps;
    }

    //////////////////////////////
    //  Verification wrappers   //
    //////////////////////////////

    /**
     * Wrapper around Validate.verifyEquals method in ThreadLocal
     *
     * @param sComment
     * @param oActual
     * @param oExpected
     */
    public void verifyEquals(String sComment, Object oActual, Object oExpected)
    {
        getValidate().verifyEquals(sComment, oActual, oExpected);
    }

    /**
     * Wrapper around Validate.assertEquals method in ThreadLocal
     *
     * @param sComment
     * @param oActual
     * @param oExpected
     */
    public void assertEquals(String sComment, Object oActual, Object oExpected)
    {
        getValidate().assertEquals(sComment, oActual, oExpected);
    }

    /**
     * Wrapper around Validate.verifyMatches method in ThreadLocal
     *
     * @param sComment
     * @param sActual
     * @param sExpected
     */
    public void verifyMatches(String sComment, String sActual, String sExpected)
    {
        getValidate().verifyMatches(sComment, sActual, sExpected);
    }

    /**
     * Wrapper around Validate.assertMatches method in ThreadLocal
     *
     * @param sComment
     * @param sActual
     * @param sExpected
     */
    public void assertMatches(String sComment, String sActual, String sExpected)
    {
        getValidate().assertMatches(sComment, sActual, sExpected);
    }

    /**
     * Wrapper around Validate.verifyVisible method in ThreadLocal
     *
     * @param sComment
     * @param oTarget
     */
    public void verifyVisible(String sComment, CommonWebElement oTarget)
    {
        getValidate().verifyVisible(sComment, oTarget);
    }

    /**
     * Wrapper around Validate.verifyInvisible method in ThreadLocal
     *
     * @param sComment
     * @param oTarget
     */
    public void verifyInvisible(String sComment, CommonWebElement oTarget)
    {
        getValidate().verifyInvisible(sComment, oTarget);
    }

    /**
     * Wrapper around CommonValidate.verifyLinkNavigation method in ThreadLocal
     *
     * @param sComment
     * @param oStartPage
     * @param oLink
     * @param sExpectedURL
     */
    public void verifyLinkNavigation(String sComment, WebBase oStartPage, CommonWebElement oLink, String sExpectedURL)
    {
        getValidate().verifyLinkNavigation(sComment, oStartPage, oLink, sExpectedURL);
    }

    /**
     * Wrapper around CommonValidate.verifyLinkPopup method in ThreadLocal
     *
     * @param sComment
     * @param oStartPage
     * @param oLink
     * @param sExpectedURL
     */
    public void verifyLinkPopup(String sComment, WebBase oStartPage, CommonWebElement oLink, String sExpectedURL)
    {
        getValidate().verifyLinkPopup(sComment, oStartPage, oLink, sExpectedURL);
    }

    /**
     * Wrapper around CommonValidate.verifyAttribute method in ThreadLocal
     *
     * @param sComment
     * @param oTarget
     * @param sAttibName
     * @param sExpectedValue
     */
    public void verifyAttribute(String sComment, CommonWebElement oTarget, String sAttibName, String sExpectedValue)
    {
        getValidate().verifyAttribute(sComment, oTarget, sAttibName, sExpectedValue);
    }

    /**
     * Wrapper around CommonValidate.verifyCssValue method in ThreadLocal
     *
     * @param sComment
     * @param oTarget
     * @param sCssProp
     * @param sExpectedValue
     */
    public void verifyCssValue(String sComment, CommonWebElement oTarget, String sCssProp, String sExpectedValue)
    {
        getValidate().verifyCssValue(sComment, oTarget, sCssProp, sExpectedValue);
    }

    /**
     * Wrapper around CommonValidate.verifyElementText method in ThreadLocal
     *
     * @param sComment
     * @param oTarget
     * @param sExpectedText
     * @param sExpectedFont
     * @param sExpectedSize
     * @param sExpectedColor
     */
    public void verifyElementText(String sComment, CommonWebElement oTarget, String sExpectedText, String sExpectedFont, String sExpectedSize, String sExpectedColor)
    {
        getValidate().verifyElementText(sComment, oTarget, sExpectedText, sExpectedFont, sExpectedSize, sExpectedColor);
    }

    /**
     * Wrapper around CommonValidate.verifyTextEquals method in ThreadLocal
     *
     * @param sComment
     * @param oTarget
     * @param sExpected
     */
    public void verifyTextEquals(String sComment, CommonWebElement oTarget, String sExpected)
    {
        getValidate().verifyTextEquals(sComment, oTarget, sExpected);
    }

    /**
     * Wrapper around CommonValidate.verifyTextMatches method in ThreadLocal
     *
     * @param sComment
     * @param oTarget
     * @param sExpected
     */
    public void verifyTextMatches(String sComment, CommonWebElement oTarget, String sExpected)
    {
        getValidate().verifyTextMatches(sComment, oTarget, sExpected);
    }

    /**
     * Wrapper around Validate.logFailure method in ThreadLocal
     *
     * @param sMessage
     * (String) - Error message to log to verification error queue.
     */
    public void logFailure(String sMessage)
    {
        getValidate().logFailure(sMessage);
    }

//	}
}