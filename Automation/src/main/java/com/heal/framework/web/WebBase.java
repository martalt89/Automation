package com.heal.framework.web;

import com.heal.framework.exception.CommonException;
import com.heal.framework.test.TestBase;
import com.heal.framework.validation.CommonValidate;
import com.heal.framework.foundation.SysTools;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vahanmelikyan on 6/29/17.
 */
public class WebBase {

    private static Logger logger = LoggerFactory.getLogger(WebBase.class);

    public static final int IMPLICIT_WAIT = 60;
    public volatile static String baseUrl = "";

    public WebDriver oWebDriver;
    public String sBrowserType;
    public String sHomeUrl;
    public String sWindowHandle = "";
    public volatile static boolean Ignore_Hidden_Element = true;

    public WebBase() {
    }

    /**
     * Constructor for using existing WebDriver object
     *
     * @param oTargetDriver
     */
    public WebBase(WebDriver oTargetDriver) {

        oWebDriver = oTargetDriver;

        //sWindowHandle = oWebDriver.getWindowHandle();

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
//        logger.trace("WebBase(WebDriver oTargetDriver, String sUrl)");
        oWebDriver = oTargetDriver;
        sHomeUrl = sUrl;
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

    public WebDriver getWebDriver() {
        return oWebDriver;
    }

    public void goTo() {
        goTo(sHomeUrl);
    }

    public void goTo(String url) {
        oWebDriver.get(url);
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
            throw new CommonException("Timeout waiting for popup/window!");
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
        return getScreenshot(oWebDriver, CommonValidate.SCREENSHOT_LOCATION);
    }

    public static String getScreenshot(WebDriver oDriver, String sFileLocation) {
        File screenShot;

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

            String imageName = Paths.get(sFileLocation, SysTools.getTimestamp() + ".png").toString();

            Path fullFilePath = Paths.get(TestBase.projDir , imageName);
            FileUtils.copyFile(screenShot, fullFilePath.toFile());
              // Write page source to file
            PrintWriter out = new PrintWriter(fullFilePath.toString().replace(".png", ".html"));
            try {
                out.println(oDriver.getPageSource());
            } catch (Exception ex) {
                logger.error(String.format("Failed to dump page source to file:  {%s} <br>", ex));
            } finally {
                out.close();
            }

            return "../" + imageName ;
        } catch (Exception ex) {
            logger.error(String.format("Failed to capture screenshot:  {%s} <br>", ex));
            return "";
        }
    }

    /**
     * Wrapper around WebDriver.findElement(By by)
     *
     * @param oBy (org.openqa.selenium.By) - Search method
     * @return (WebElement) - Found element
     */
    public CommonWebElement findElement(org.openqa.selenium.By oBy) {
        List<CommonWebElement> commonWebElements = findAllElements(oBy);
        if(commonWebElements.size() == 0){
            throw new NoSuchElementException("no such element found for： " + oBy.toString());
        }
        return findAllElements(oBy).get(0);
    }

    public CommonWebElement findElement(String sTag) {
        List<CommonWebElement> commonWebElements = findAllElements(getByFromString(sTag));
        if(commonWebElements.size() == 0){
            throw new NoSuchElementException("no such element found for： " + sTag);
        }
        return findAllElements(getByFromString(sTag)).get(0);

    }

    public List<CommonWebElement> findAllElements(By oBy) {
        List<CommonWebElement> commonWebElements = new java.util.ArrayList<CommonWebElement>();
        turnOnImplicitWaits();
        if(Ignore_Hidden_Element ){
            ExpectedConditions.visibilityOfElementLocated(oBy).apply(oWebDriver);
        }
        else{
            ExpectedConditions.presenceOfElementLocated(oBy).apply(oWebDriver);
        }

        List<WebElement> lWebElement = oWebDriver.findElements(oBy);
        turnOffImplicitWaits();
        for (WebElement oElement : lWebElement) {
            if(Ignore_Hidden_Element ){
                if(!oElement.isDisplayed()){
                    continue;
                }
                commonWebElements.add(new CommonWebElement(oElement, oWebDriver));
            }
            else
                commonWebElements.add(new CommonWebElement(oElement, oWebDriver));
        }
        return commonWebElements;
    }

    private void turnOffImplicitWaits() {
        oWebDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    private void turnOnImplicitWaits() {
        oWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
                throw new CommonException("Unrecognized locator:  " + sSearchMethod);
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
    public CommonWebElement waitForElement(org.openqa.selenium.By oBy, int iTimeOut) {
        try {
            Wait<WebDriver> oWait = new WebDriverWait(oWebDriver, iTimeOut);
            WebElement oElement = oWait.until(ExpectedConditions.presenceOfElementLocated(oBy));

            return new CommonWebElement(oElement, oBy, oWebDriver);

        } catch (org.openqa.selenium.TimeoutException ex) {
            String sText = "Tag:  " + oBy.toString();
            throw new CommonException("Timeout looking for element.  " + sText, ex);
        } catch (Exception ex) {
            throw new CommonException("Unhandled exception", ex);
        }
    }

    public CommonWebElement waitForElement(org.openqa.selenium.By oBy) {
        return waitForElement(oBy, IMPLICIT_WAIT);
    }

    /**
     * Wait for any WebElement to become visible using specified search method.
     *
     * @param oBy      (org.openqa.selenium.By) - Search method.
     * @param iTimeOut (long) - Wait timeout in seconds.
     * @return (WebElement) - WebElement found.
     */
    public CommonWebElement waitForVisible(org.openqa.selenium.By oBy, int iTimeOut) {
        try {
            Wait<WebDriver> oWait = new WebDriverWait(oWebDriver, iTimeOut);
            WebElement oElement = oWait.until(ExpectedConditions.visibilityOfElementLocated(oBy));

            return new CommonWebElement(oElement, oBy, oWebDriver);
        } catch (org.openqa.selenium.TimeoutException ex) {
            String sText = "Tag:  " + oBy.toString();
            throw new CommonException("Timeout waiting for element to be visible.  " + sText, ex);
        } catch (Exception ex) {
            throw new CommonException("Unhandled exception", ex);
        }
    }

    public CommonWebElement waitForVisible(org.openqa.selenium.By oBy) {
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
            throw new CommonException("Timeout waiting page title:  " + sTitle, ex);
        } catch (Exception ex) {
            throw new CommonException("Unhandled exception", ex);
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

    public void waitForPageReady(){
        waitForUrl(sHomeUrl);
        waitForPageLoad();
    }

    public void waitForPageReady(String url){
        waitForUrl(url);
        waitForPageLoad();
    }

    public void scrollPage(String sDirection) {
        switch (sDirection) {
            case "Up":
                ((JavascriptExecutor) oWebDriver)
                        .executeScript("window.scrollTo(0, -document.body.scrollHeight)");
                break;
            case "Down":
                ((JavascriptExecutor) oWebDriver)
                        .executeScript("window.scrollTo(0, document.body.scrollHeight)");
                break;
        }
    }
}
