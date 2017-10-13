package com.heal.framework.test;

import com.heal.framework.exception.CommonException;
import com.heal.framework.foundation.*;
import com.heal.framework.validation.CommonValidate;
import com.heal.framework.web.CommonWebElement;
import com.heal.framework.web.CommonWebValidate;
import com.heal.framework.web.WebBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.ScreenshotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * This is the base class for all web tests to provide common/convenient methods
 *
 *
 */

public class TestBase
{
    private static Logger logger = LoggerFactory.getLogger(TestBase.class);
    ////////////////////////
    //  Class members     //
    ////////////////////////	
    private static String os = System.getProperty("os.name");
    public static String projDir = System.getProperty("user.dir");
    public static String separator = System.getProperty("file.separator");
    private String environment = "";
    private String saucelab_url = "";
    //	private int iPageLoadTimeout = 90;
    private boolean bMaximizeBrowser = false;
    private static String browser;
    private static String releaseEnv = "";
    private static Dimension screenDimentions = new Dimension(1400,1050);

    private static HashMap<String, String> parameters;
    private static ExtentReports extent;
    private static String sessionID;

    protected static final DBConnector connector = new DBConnector();

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
    private static final InheritableThreadLocal oExtentTest = new InheritableThreadLocal();
    private static final InheritableThreadLocal oSessionID = new InheritableThreadLocal();

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
        saucelab_url = sGridServer;
    }

    public static String getBrowser()
    {
        return browser;
    }

    public static String getReleaseEnv() {
        return releaseEnv;
    }

    public static HashMap<String, String> getParameters() {
        return parameters;
    }

    public static void setParameters(HashMap<String, String> parameters) {
        TestBase.parameters = parameters;
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

    public String getSessionID(){
        return (String)oSessionID.get();
    }

    public void setSessionID(String sessionID){
        oSessionID.set(sessionID);
    }

    public ExtentTest getExtentTest(){

        return (ExtentTest)oExtentTest.get();
    }

    /**
     * Retrieves AppiumDriver instance stored in InheritableThreadLocal variable or throws an exception if
     * any thread exception is found.
     *
     * @return
     * (AppiumDriver)
     */
    public AppiumDriver getMobileDriver()
    {
        if (getException() == null)
            return (AppiumDriver)oCurrentDriver.get();
        else
            throw getException();
    }

    /**
     * Stores ExtentTest instance in InheritableThreadLocal variable.
     *
     * @param test
     *
     */
    public void setExtentTest(ExtentTest test){
        oExtentTest.set(test);
    }

    /**
     * Stores CommonValidate instance in InheritableThreadLocal variable.
     *
     * @param oV
     * (CommonValidate)
     */
    public void setValidate(CommonValidate oV)
    {
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

    public ExtentReports getextent(){
        return extent;
    }

    @BeforeSuite
    public void suiteSetup(){
        extent = ExtentManager.getReporter();
    }

    /**
     * Responsible for setting all the test class properties and instantiating an HealEntityManager to be shared by all tests.
     */
    @BeforeClass(alwaysRun=true)
    @Parameters({ "environment",
            "browserName"
            ,"platform"
            ,"version"
            ,"screenResolution"
            ,"baseUrl"
            ,"ENV"
            ,"USERNAME"
            ,"ACCESS_KEY"
            ,"saucelab_url"
            ,"implicitWait"
            ,"retryLimit"
            ,"maximize_browser"
    })
    public void setup(@Optional("local") String environment,
                      @Optional("chrome") String browserName,
                      @Optional("macOS 10.12") String platform,
                      @Optional("latest") String version,
                      @Optional("chrome") String screenResolution,
                      @Optional("-dev.heal.com") String baseUrl,
                      @Optional("dev") String env,
                      @Optional("qaheal") String username,
                      @Optional("e14bb2d7-155b-4775-8978-9365c5b22012") String accessKey,
                      @Optional("@ondemand.saucelabs.com:443/wd/hub") String saucelab_url,
                      @Optional("30") String implicitWait,
                      @Optional("1") String retryLimit,
                      @Optional("true") String maximizeBrowser)
    {

        MDC.put("threadID", String.valueOf(Thread.currentThread().getId()));
        try
        {

            // Env mode
            logger.info("setup():  Environment mode:  {}", environment);

            this.environment = environment;
            WebBase.baseUrl = baseUrl;
            // Grid server url
            if (environment.equalsIgnoreCase("remote"))
            {
                if (saucelab_url.equals(""))
                    throw new SkipException("Parameter 'grid_server_url' must be provided when running in grid mode!");
                this.saucelab_url = "https://" + username + ":" + accessKey + saucelab_url;
                logger.info("setup():  Grid server URL:  {}", this.saucelab_url);
            }

            // Target browsers
            browser = browserName.replaceAll("\\s", "");
            logger.info("setup():  Target browsers:  {}", browser);
            releaseEnv = env;
            // Maximize browser
            if (maximizeBrowser.equalsIgnoreCase("true"))
                bMaximizeBrowser = true;
            // Set time to wait for a page to load
            //iPageLoadTimeout = Integer.parseInt(oProp.getProperty("page_load_timeout", "90"));

            // Set global implicit wait value for CommonWebElement
            CommonWebElement.setImplicitWait(Integer.parseInt(implicitWait));
            logger.info("setup():  Element implicit wait:  {} sec", implicitWait);

            RetryAnalyzer.setRetryLimit(Integer.parseInt(retryLimit));
            logger.info("setup(): Set retryLimit: {}", retryLimit);

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
     * Post test class clean up.  Close HealEntityManager.
     */
    @AfterSuite(alwaysRun=true)
    public void suiteTeardown()
    {
        extent.flush();
        extent.close();
    }
    /**
     * This TestNG BeforeMethod is responsible for creating the WebDriver/RemoteWebDriver instance and making it available to
     * the test method.  Also stores the instance in InheritableThreadLocal.
     *
     * This method must never fail.  By design, TestNG will skip all subsequent test methods if the BeforeMethod fails.  However,
     * we use BeforeMethod before each test to create separate instances of WebDriver for test.  So one BeforeMethod failure should
     * not cause subsequent tests to be skipped.  Our solution is to catch all exception and let the test method handle any failure to
     * create WebDriver.     *
     *
     * @throws Exception
     */
    @BeforeMethod(alwaysRun=true)
    public void beforeMethod(Method oMethod) throws Exception
    {
        MDC.put("threadID", String.valueOf(Thread.currentThread().getId()));

        //logger.info("Executing: {} Parameters: {}", oMethod.getName(), aParams);

        try
        {
            ExtentTest test = ExtentTestManager.startNewTest(oMethod.getName());
            setExtentTest(test);
            HashMap<String, ArrayList<HashMap<String, String>>> responses = new HashMap<String, ArrayList<HashMap<String, String>>>();
            DBResult.setResponses(responses);

            WebDriver oDriver;

            if (environment.equalsIgnoreCase("remote")) {
                oDriver = StartRemoteWebDriver(parameters);
            }
            else {
                oDriver = StartWebDriver(browser);
                //oDriver.manage().window().setSize(screenDimentions);
            }

            // Only supported for Firefox in current release.
            //oDriver.manage().timeouts().pageLoadTimeout(iPageLoadTimeout, java.util.concurrent.TimeUnit.SECONDS);

            // Store WebDriver, Validate, and RemoteNode instances in the InheritableThreadLocal variable so each thread has own copy.  A must for parallel execution.
            setDriver(oDriver);
            setValidate(new CommonWebValidate(oDriver, true, test));

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
    public void afterMethod(ITestResult oResult, Method oMethod)
    {
        MDC.put("threadID", String.valueOf(Thread.currentThread().getId()));

        try
        {
            //String sStatus = oResult.getStatus() == org.testng.ITestResult.SUCCESS?"SUCCESS":"FAILURE";
            logger.trace("afterMethod():  ");
            unsetValidate();
            unsetRemoteNode();
            unsetException();
            quitDriver();
            ExtentManager.getReporter().endTest(getExtentTest());
        }
        catch (Exception ex)
        {
            logger.error("afterMethod():  Exception caught!  ", ex);
        }
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
            logger.error("handleException(): Exception caught!  ", ex);

            if (!(ex instanceof NullPointerException))
            {
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
            logger.error("handleException Error:  ", ex);
        }
        return "";
    }

    public String getScreenshotFromException(ScreenshotException ex) throws java.io.FileNotFoundException, java.io.IOException
    {
        byte[] aBase64;
        aBase64 = Base64.decodeBase64(ex.getBase64EncodedScreenshot());
        String fullFilePath = projDir + separator + "out" + separator + "screenshots" + separator  + SysTools.getTimestamp() + ".png";
        FileOutputStream fOut = new FileOutputStream(fullFilePath);
        fOut.write(aBase64);
        fOut.close();
        logger.info("Screenshot sent to {}", fullFilePath);
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
    public synchronized WebDriver StartWebDriver(String sBrowserType) throws Exception
    {
        try
        {
            switch (sBrowserType.toUpperCase())
            {
                case "CHROME":
                    if (os.contains("Mac")) {
                        System.setProperty("webdriver.chrome.driver", projDir + separator + "chromedriver");
                    } else {
                        System.setProperty("webdriver.chrome.driver", projDir + separator + "chromedriver.exe");
                    }
                    return new org.openqa.selenium.chrome.ChromeDriver();
                case "FIREFOX":
                    if (os.contains("Mac")) {
                        System.setProperty("webdriver.gecko.driver", projDir + separator + "geckodriver");
                    } else {
                        System.setProperty("webdriver.gecko.driver", projDir + separator + "geckodriver.exe");
                    }
                    return new org.openqa.selenium.firefox.FirefoxDriver();
                case "IE":
                    System.setProperty("webdriver.ie.driver", projDir + separator + "IEDriverServer.exe");
                    return new org.openqa.selenium.ie.InternetExplorerDriver();
                case "ANDROID":
                    DesiredCapabilities capabilities =  DesiredCapabilities.android();
                    capabilities.setCapability("platformName", "Android");
                    capabilities.setCapability("deviceName", "4100fb99e4c73231");
//                    capabilities.setCapability("app", "/Users/vahanmelikyan/Downloads/app-patient-debug.apk");
                    capabilities.setCapability("app", "/Users/jaypurohit/Downloads/app-patient-debug-340-4.0.0.20170907.apk");
                    capabilities.setCapability("appPackage", "com.getheal.patient.debug");
                    capabilities.setCapability("appActivity", "com.getheal.patient.activities.InitialActivity");
                    //capabilities.setCapability("avd","Nexus_5X"); //to open the emulator automatically, otherwise the emulator needs to be open
                    return new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),	capabilities);
                    /* return new org.openqa.selenium.chrome.ChromeDriver(); */
                case "SAFARI":
                    return new org.openqa.selenium.safari.SafariDriver();
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
     * Instantiate RemoteWebDriver based on browser type.  This is used for Saucelab.
     *
     *
     * @return
     * (WebDriver)
     *
     * @throws Exception
     */
    public synchronized WebDriver StartRemoteWebDriver(HashMap<String, String> parameters) throws Exception    {

        DesiredCapabilities oRequestCapability = getDesiredCapabilities(parameters);
        RemoteWebDriver oRemoteWebDriver;
        try
        {
            oRemoteWebDriver =  new RemoteWebDriver(new URL(this.saucelab_url), oRequestCapability);
            Capabilities oTargetCapability = oRemoteWebDriver.getCapabilities();
            logger.trace("Target driver capabilities:  {}", oTargetCapability.toString());
            sessionID = oRemoteWebDriver.getSessionId().toString();
            setSessionID(oRemoteWebDriver.getSessionId().toString());
            return oRemoteWebDriver;
        }
        catch (Exception e)
        {
            throw new CommonException("Failed to instantiate RemoteWebDriver!", e.getCause());
        }
    }

    private  DesiredCapabilities getDesiredCapabilities( HashMap<String, String> parameters) {
        String browserName = parameters.get("browserName");
        String platform = parameters.get("platform");
        DesiredCapabilities capabilities = null;
        if(platform.equalsIgnoreCase("ios") || platform.equalsIgnoreCase("android")){
            capabilities = platform.equalsIgnoreCase("iphone") ? DesiredCapabilities.iphone() : DesiredCapabilities.android();
            capabilities.setCapability("browserName", parameters.get("browserName"));
            capabilities.setCapability("appiumVersion", parameters.get("appiumVersion"));
            capabilities.setCapability("deviceName", parameters.get("deviceName"));
            capabilities.setCapability("deviceOrientation", "portrait");
            capabilities.setCapability("platformVersion", parameters.get("platformVersion"));
            capabilities.setCapability("platformName", parameters.get("platformName"));
            capabilities.setCapability("version", parameters.get("version"));
            return capabilities;
        }

        switch (browserName.toLowerCase()) {

            case "safari":
                capabilities = DesiredCapabilities.safari();
                capabilities.setCapability("version", parameters.get("version"));
                capabilities.setCapability("platform", parameters.get("platform"));
                capabilities.setCapability("screenResolution", parameters.get("screenResolution"));
                capabilities.setCapability("seleniumVersion", "3.4.0");
                break;
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability("version", parameters.get("version"));
                capabilities.setCapability("platform", parameters.get("platform"));
                capabilities.setCapability("screenResolution", parameters.get("screenResolution"));
                capabilities.setCapability("seleniumVersion", "3.4.0");
                break;
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("version", parameters.get("version"));
                capabilities.setCapability("platform", parameters.get("platform"));
                capabilities.setCapability("screenResolution", parameters.get("screenResolution"));
                capabilities.setCapability("seleniumVersion", "3.4.0");
                break;
            case "ie":
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability("version", parameters.get("version"));
                capabilities.setCapability("platform", parameters.get("platform"));
                capabilities.setCapability("screenResolution", parameters.get("screenResolution"));
                capabilities.setCapability("seleniumVersion", "3.4.0");
                break;
            default:
                break;
        }
        return capabilities;
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
     * Wrapper around Validate.assertEquals method in ThreadLocal
     *
     * @param sComment
     * @param oActual
     * @param oExpected
     */
    public void assertEquals(String sComment, Object oActual, Object oExpected, int timeout)
    {
        getValidate().assertEquals(sComment, oActual, oExpected, timeout);
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


}