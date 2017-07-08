package com.heal.framework.web;

import com.heal.framework.exception.HealException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * Created by vahanmelikyan on 6/29/17.
 */
public class WebBase {

    Logger logger = LoggerFactory.getLogger(WebBase.class);

    public static final int IMPLICIT_WAIT = 60;
    public static final String SCREENSHOT_LOCATION = "/Automation/out/screenshots";

    public WebDriver oWebDriver;
    public String sBrowserType;
    public String sHomeUrl;
    public String sWindowHandle = "";

    public WebBase() {
    }

    /**
     * Constructor for using existing WebDriver object
     *
     * @param oTargetDriver
     */
    public WebBase(WebDriver oTargetDriver) {
        logger.trace("WebBase(WebDriver oTargetDriver)");

        oWebDriver = oTargetDriver;

//		sHomeUrl = oWebDriver.getCurrentUrl();
        sWindowHandle = oWebDriver.getWindowHandle();

        if (oWebDriver instanceof org.openqa.selenium.chrome.ChromeDriver) {
            sBrowserType = "Chrome";
        } else if (oWebDriver instanceof org.openqa.selenium.ie.InternetExplorerDriver) {
            sBrowserType = "IE";
        } else if (oWebDriver instanceof org.openqa.selenium.firefox.FirefoxDriver) {
            sBrowserType = "Firefox";
        } else if (oWebDriver instanceof org.openqa.selenium.phantomjs.PhantomJSDriver) {
            sBrowserType = "HTML";
        } else if (oWebDriver instanceof org.openqa.selenium.safari.SafariDriver) {
            sBrowserType = "Safari";
        }
    }

    /**
     * Constructor for using existing WebDriver object and URL
     *
     * @param oTargetDriver
     * @param sUrl
     */
    public WebBase(WebDriver oTargetDriver, String sUrl) {
        logger.trace("WebBase(WebDriver oTargetDriver, String sUrl)");

        oWebDriver = oTargetDriver;
        sHomeUrl = sUrl;
        if (!oWebDriver.getCurrentUrl().equals(sHomeUrl))
            oWebDriver.get(sHomeUrl);
        sWindowHandle = oWebDriver.getWindowHandle();

        if (oWebDriver instanceof org.openqa.selenium.chrome.ChromeDriver) {
            sBrowserType = "Chrome";
        } else if (oWebDriver instanceof org.openqa.selenium.ie.InternetExplorerDriver) {
            sBrowserType = "IE";
        } else if (oWebDriver instanceof org.openqa.selenium.firefox.FirefoxDriver) {
            sBrowserType = "Firefox";
        } else if (oWebDriver instanceof org.openqa.selenium.phantomjs.PhantomJSDriver) {
            sBrowserType = "HTML";
        } else if (oWebDriver instanceof org.openqa.selenium.safari.SafariDriver) {
            sBrowserType = "Safari";
        }
    }

    /**
     * Constructor for using new WebDriver object
     *
     * @param sBrowser
     * @param sUrl
     */
    public WebBase(String sBrowser, String sUrl) {
        logger.trace("WebBase(String sBrowserType, String sUrl)");

        switch (sBrowser) {
            case "Chrome":
                oWebDriver = new org.openqa.selenium.chrome.ChromeDriver();
                break;
            case "IE":
                oWebDriver = new org.openqa.selenium.ie.InternetExplorerDriver();
                break;
            case "Firefox":
                oWebDriver = new org.openqa.selenium.firefox.FirefoxDriver();
                break;
            case "HTML":
                DesiredCapabilities dCaps = new DesiredCapabilities();
                dCaps.setJavascriptEnabled(true);
                dCaps.setCapability("takesScreenshot", false);
                oWebDriver = new PhantomJSDriver(dCaps);
                break;
            case "Safari":
                oWebDriver = new org.openqa.selenium.safari.SafariDriver();
                break;
            default:
                oWebDriver = null;
        }

        sHomeUrl = sUrl;
        sBrowserType = sBrowser;
        oWebDriver.get(sHomeUrl);
        if (!sBrowserType.equals("Safari"))
            sWindowHandle = oWebDriver.getWindowHandle();
    }

    public WebDriver getWebDriver() {
        return oWebDriver;
    }

    public void setWebDriver(WebDriver oTargetDriver) {
        oWebDriver = oTargetDriver;

        sHomeUrl = oWebDriver.getCurrentUrl();
        sWindowHandle = oWebDriver.getWindowHandle();

        if (oWebDriver instanceof org.openqa.selenium.chrome.ChromeDriver)
            sBrowserType = "Chrome";
        else if (oWebDriver instanceof org.openqa.selenium.ie.InternetExplorerDriver)
            sBrowserType = "IE";
        else if (oWebDriver instanceof org.openqa.selenium.firefox.FirefoxDriver)
            sBrowserType = "Firefox";
        else if (oWebDriver instanceof org.openqa.selenium.phantomjs.PhantomJSDriver)
            sBrowserType = "HTML";
    }

    public void home() {
        oWebDriver.get(sHomeUrl);
        //reload();
    }

    public void reload() {
        oWebDriver.navigate().refresh();
    }

    public void back() {
        oWebDriver.navigate().back();
        waitForPageLoad();
    }

    public void maximizeWindow() {
        oWebDriver.manage().window().maximize();
        waitForPageLoad();
    }

    public void forward() {
        oWebDriver.navigate().forward();
        waitForPageLoad();
    }

    public String getCurrentUrl() {
        return oWebDriver.getCurrentUrl();
    }

    public WebDriver.Window getCurrentWindow() {
        return oWebDriver.manage().window();
    }

    public String getTitle() {
        return oWebDriver.getTitle();
    }

    public void close() {
        oWebDriver.close();
    }

    public void quit() {
        oWebDriver.quit();
    }

    public void navigateTo(String sUrl) {
        oWebDriver.get(sUrl);
    }

    public WebDriver getPopup() {
        try {
            Wait<WebDriver> oWait = new FluentWait<WebDriver>(oWebDriver)
                    .withTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
                    .pollingEvery(5, java.util.concurrent.TimeUnit.SECONDS);

            oWait.until(new com.google.common.base.Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver dr) {
//							logger.info("Handles = {}", oWebDriver.getWindowHandles().size());
                    return oWebDriver.getWindowHandles().size() > 1;
                }
            });
        } catch (org.openqa.selenium.TimeoutException ex) {
            throw new HealException("Timeout waiting for popup/window!");
        }

        java.util.Set<String> oHandles = oWebDriver.getWindowHandles();
        oHandles.remove(sWindowHandle);
        oWebDriver.switchTo().window(oHandles.iterator().next());
        return oWebDriver;
    }

    public WebDriver getFrame(WebElement oFrame) {
        oWebDriver.switchTo().frame(oFrame);
        return oWebDriver;
    }

    public WebDriver getMainFrame() {
        oWebDriver.switchTo().defaultContent();
        return oWebDriver;
    }

    public void closePopup() {
        // Fix for multiple popups
        java.util.Set<String> oHandles = oWebDriver.getWindowHandles();
        oHandles.remove(sWindowHandle);

        for (String sHandle : oHandles) {
            oWebDriver.switchTo().window(sHandle);
            oWebDriver.close();
        }
        oWebDriver.switchTo().window(sWindowHandle);
    }

    public void getFocus() {
        oWebDriver.switchTo().window(sWindowHandle);
    }

    public void setImplicitWait(int iSeconds) {
        oWebDriver.manage().timeouts().implicitlyWait(iSeconds, java.util.concurrent.TimeUnit.SECONDS);
    }

    public String getScreenshot(String sFileLocation) {
        return getScreenshot(oWebDriver, sFileLocation);
    }

    public String getScreenshot() {
        return getScreenshot(oWebDriver, SCREENSHOT_LOCATION);
    }

    public static String getScreenshot(WebDriver oDriver, String sFileLocation) {
        File screenShot;
        Logger logger = LoggerFactory.getLogger(WebBase.class);

        try {
            try {
                // This is workaround to avoid a WebDriver bug that creates duplicate browser
                // This is for local WebDriver
                RemoteWebDriver augmentedDriver = (RemoteWebDriver) oDriver;
                screenShot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
            } catch (java.lang.ClassCastException ex) {
                //  This is for RemoteWebDriver
                WebDriver augmentedDriver = new Augmenter().augment(oDriver);
                screenShot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
            }

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            String fullFilePath = sFileLocation + "/" + timestamp.toString() + ".png";
            FileUtils.copyFile(screenShot, new File(fullFilePath));
            logger.info("Screenshot sent to {}", fullFilePath);

            // Write page source to file
            PrintWriter out = new PrintWriter(sFileLocation + "/" + timestamp.toString() + ".html");
            try {
                out.println(oDriver.getPageSource());
            } catch (Exception ex) {
                logger.error("Failed to dump page source to file:  ", ex);
            } finally {
                out.close();
            }

            return fullFilePath;
        } catch (Exception ex) {
            logger.error("Failed to capture screenshot:  " + ex);
            return "";
        }
    }

    /**
     * Wrapper around WebDriver.findElement(By by)
     *
     * @param oBy (org.openqa.selenium.By) - Search method
     * @return (WebElement) - Found element
     */
    public HealWebElement findElement(org.openqa.selenium.By oBy) {
        return new HealWebElement(oWebDriver.findElement(oBy), oBy, oWebDriver);
    }

    public HealWebElement findElement(String sTag) {
        return new HealWebElement(oWebDriver.findElement(getByFromString(sTag)), sTag, oWebDriver);
    }

    public static By getByFromString(String sTag) {
        String sSearchMethod, sSearchTag;

        if (sTag.indexOf("=") == -1) {
            sSearchMethod = "id";
            sSearchTag = sTag;
        } else {
            sSearchMethod = sTag.substring(0, sTag.indexOf("="));
            sSearchTag = sTag.replaceFirst(sSearchMethod + "=", "");
        }

        // Call corresponding FindBy method based on search tag

        switch (sSearchMethod) {
            case "css":
                return By.cssSelector(sSearchTag);
            case "id":
                return By.id(sSearchTag);
            case "name":
                return By.name(sSearchTag);
            case "xpath":
                return By.xpath(sSearchTag);
            case "className":
                return By.className(sSearchTag);
            case "linkText":
                return By.linkText(sSearchTag);
            case "partialLinkText":
                return By.partialLinkText(sSearchTag);
            default:
                throw new HealException("Unrecognized locator:  " + sSearchMethod);
        }
    }

    ////////////////////////////
    //  WaitXXX methods       //
    ////////////////////////////

    /**
     * Wait for existence of any WebElement using specified search method.
     *
     * @param oBy      (org.openqa.selenium.By) - Search method.
     * @param iTimeOut (long) - Wait timeout in seconds.
     * @return (WebElement) - WebElement found.
     */
    public HealWebElement waitForElement(org.openqa.selenium.By oBy, int iTimeOut) {
        try {
            Wait<WebDriver> oWait = new WebDriverWait(oWebDriver, iTimeOut);
            WebElement oElement = oWait.until(ExpectedConditions.presenceOfElementLocated(oBy));

            return new HealWebElement(oElement, oBy, oWebDriver);

//			Wait<org.openqa.selenium.By> oWait = new FluentWait<org.openqa.selenium.By>(oBy)
//				       .withTimeout(iTimeOut, java.util.concurrent.TimeUnit.SECONDS)
//				       .pollingEvery(500, java.util.concurrent.TimeUnit.MICROSECONDS)
//				       .ignoring(NoSuchElementException.class);
//
//			WebElement oElement = oWait.until(new com.google.common.base.Function<org.openqa.selenium.By, WebElement>()
//					{
//						@Override
//						public WebElement apply(org.openqa.selenium.By oBy)
//						{
//							WebElement oFound = oWebDriver.findElement(oBy);
//							return oFound;
//							if (oFound.isDisplayed())
//								return oFound;
//							else
//								return null;
//						}
//					});

        } catch (org.openqa.selenium.TimeoutException ex) {
            String sText = "Tag:  " + oBy.toString();
            throw new HealException("Timeout looking for element.  " + sText, ex);
        } catch (Exception ex) {
            throw new HealException("Unhandled exception", ex);
        }
    }

    public HealWebElement waitForElement(org.openqa.selenium.By oBy) {
        return waitForElement(oBy, IMPLICIT_WAIT);
    }

    /**
     * Wait for any WebElement to become visible using specified search method.
     *
     * @param oBy      (org.openqa.selenium.By) - Search method.
     * @param iTimeOut (long) - Wait timeout in seconds.
     * @return (WebElement) - WebElement found.
     */
    public HealWebElement waitForVisible(org.openqa.selenium.By oBy, int iTimeOut) {
        try {
            Wait<WebDriver> oWait = new WebDriverWait(oWebDriver, iTimeOut);
            WebElement oElement = oWait.until(ExpectedConditions.visibilityOfElementLocated(oBy));

            return new HealWebElement(oElement, oBy, oWebDriver);
        } catch (org.openqa.selenium.TimeoutException ex) {
            String sText = "Tag:  " + oBy.toString();
            throw new HealException("Timeout waiting for element to be visible.  " + sText, ex);
        } catch (Exception ex) {
            throw new HealException("Unhandled exception", ex);
        }
    }

    public HealWebElement waitForVisible(org.openqa.selenium.By oBy) {
        return waitForVisible(oBy, IMPLICIT_WAIT);
    }

    /**
     * Wait for the title of page to contain certain string.
     *
     * @param sTitle   (String) - Expected string.
     * @param iTimeOut (int) - Wait timeout in seconds.
     */
    public void waitForTitle(String sTitle, int iTimeOut) {
        try {
            Wait<WebDriver> oWait = new WebDriverWait(oWebDriver, iTimeOut);
            oWait.until(ExpectedConditions.titleContains(sTitle));
        } catch (org.openqa.selenium.TimeoutException ex) {
            throw new HealException("Timeout waiting page title:  " + sTitle, ex);
        } catch (Exception ex) {
            throw new HealException("Unhandled exception", ex);
        }
    }

    public void waitForTitle(String sTitle) {
        waitForTitle(sTitle, IMPLICIT_WAIT);
    }

    /**
     * Wait for the url of page to contain certain string.
     *
     * @param sUrl     (String) - Expected string.
     * @param iTimeOut (int) - Wait timeout in seconds.
     */
    public void waitForUrl(String sUrl, int iTimeOut) {
        Wait<String> oWait = new FluentWait<String>(sUrl)
                .withTimeout(iTimeOut, java.util.concurrent.TimeUnit.SECONDS)
                .pollingEvery(500, java.util.concurrent.TimeUnit.MICROSECONDS);

        oWait.until(new com.google.common.base.Function<String, Boolean>() {
            @Override
            public Boolean apply(String sUrl) {
                return oWebDriver.getCurrentUrl().contains(sUrl);
            }
        });
    }

    public void waitForUrl(String sUrl) {
        waitForUrl(sUrl, IMPLICIT_WAIT);
    }

    /**
     * Wait for current url to be different than input url.  Filters out "about:blank" and "".
     *
     * @param iTimeOut (int) - Wait timeout in seconds.
     */
    public void waitForUrlNot(String sUrl, int iTimeOut) {
        Wait<String> oWait = new FluentWait<String>(sUrl)
                .withTimeout(iTimeOut, java.util.concurrent.TimeUnit.SECONDS)
                .pollingEvery(500, java.util.concurrent.TimeUnit.MICROSECONDS);

        oWait.until(new com.google.common.base.Function<String, Boolean>() {
            @Override
            public Boolean apply(String sUrl) {
                return !oWebDriver.getCurrentUrl().equalsIgnoreCase(sUrl) && !oWebDriver.getCurrentUrl().equalsIgnoreCase("about:blank") && !oWebDriver.getCurrentUrl().equalsIgnoreCase("");
            }
        });
    }

    public void waitForUrlNot(String sUrl) {
        waitForUrlNot(sUrl, IMPLICIT_WAIT);
    }

    /**
     * Wait for page to complete load.  This is done by waiting for a new window instance and javascript 'document.readystate'.
     * Every time a page is loaded, a new window object is created in the WebDriver.
     * So we wait for this new Window object and wait for the document.readystate to be 'complete'.
     *
     * @param iTimeOut (int) - Wait timeout in seconds.
     */
    public void waitForPageLoad(int iTimeOut) {
        WebDriver.Window oStartWindow = oWebDriver.manage().window();  // Get current window/page object

        Wait<org.openqa.selenium.WebDriver.Window> oWait = new FluentWait<org.openqa.selenium.WebDriver.Window>(oStartWindow)
                .withTimeout(iTimeOut, java.util.concurrent.TimeUnit.SECONDS)
                .pollingEvery(500, java.util.concurrent.TimeUnit.MICROSECONDS);

        oWait.until(new com.google.common.base.Function<org.openqa.selenium.WebDriver.Window, Boolean>() {
            @Override
            public Boolean apply(org.openqa.selenium.WebDriver.Window oWindow) {
                org.openqa.selenium.WebDriver.Window newWindow = oWebDriver.manage().window();  // Get new window/page object so we can compare to starting window.
                return (oWindow != newWindow) && ((JavascriptExecutor) oWebDriver).executeScript("return document.readyState").equals("complete");
            }
        });
    }

    public void waitForPageLoad() {
        waitForPageLoad(IMPLICIT_WAIT);
    }

    ////////////////////////////
    //  Misc methods          //
    ////////////////////////////

//    private boolean startSeleniumRC() {
//        try {
//            org.openqa.selenium.server.SeleniumServer ss;
//            org.openqa.selenium.server.RemoteControlConfiguration rcc;
//
//            rcc = new org.openqa.selenium.server.RemoteControlConfiguration();
//            rcc.setInteractive(true);
//            rcc.setSingleWindow(true);
//            rcc.setTimeoutInSeconds(10);
//            ss = new org.openqa.selenium.server.SeleniumServer(rcc);
//            ss.start();
//
//            Thread.sleep(5000);
//            return true;
//        } catch (Exception ex) {
//            logger.error("Failed to start Selenium RC");
//            return false;
//        }
//    }

}
