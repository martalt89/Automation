package framework.web;

import com.google.common.base.Function;
import foundation.SysTools;
import framework.exception.CommonException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.testng.Reporter;


import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by vahanmelikyan on 7/1/17.
 */
public class CommonWebElement implements WebElement, Locatable {
//    Logger logger = LoggerFactory.getLogger(CommonWebElement.class);


    private static int iImplicitWait = 30;
    private static int iThrottleValue = 0;
    private static boolean bMonitorMode = false;

    private WebDriver oWebDriver;
    private JavascriptExecutor oJavascriptExecutor;
    private WebElement oParentWebElement;
    private WebElement oWebElement;
    private String sElementName;
    private String sElementTag;
    private By oBy;
    private String border = "";
    private Actions oAction;

    /**
     * Constructor to wrap CommonWebElement around a declared page element.
     *
     * @param oElement (WebElement) - Declared page element.
     * @param sName    (String)- Declared variable name.
     * @param sTag     (String) - Declared tag.
     * @param oDriver  (WebDriver) - WebDriver object.
     */
    public CommonWebElement(WebElement oElement, String sName, String sTag, WebDriver oDriver) {
        this.oWebElement = oElement;
        this.sElementName = sName;
        this.sElementTag = sTag;
        this.oWebDriver = oDriver;
        this.oJavascriptExecutor = (JavascriptExecutor) oDriver;
        this.oBy = WebBase.getByFromString(sTag);
        this.oAction = new Actions(oWebDriver);
    }

    public CommonWebElement(WebElement oElement, String sTag, WebDriver oDriver) {
        this(oElement, null, sTag, oDriver);
    }

    public CommonWebElement(String sName, String sTag, WebDriver oDriver) {
        this(null, sName, sTag, oDriver);
    }

    /**
     * Constructor to wrap CommonWebElement around a declared page element.
     *
     * @param oElement (WebElement) - Declared page element.
     * @param sName    (String)- Declared variable name.
     * @param oBy     (By) - Declared tag.
     * @param oDriver  (WebDriver) - WebDriver object.
     */
    public CommonWebElement(WebElement oElement, String sName, By oBy, WebDriver oDriver) {
        this.sElementName = sName;
        this.oWebElement = oElement;
        this.oBy = oBy;
        this.oWebDriver = oDriver;
        this.oJavascriptExecutor = (JavascriptExecutor) oDriver;
        this.oAction = new Actions(oWebDriver);
    }

    public CommonWebElement(WebElement oElement, By oBy, WebDriver oDriver) {
        this(oElement, null, oBy, oDriver);
    }

    public CommonWebElement(String sName, By oBy, WebDriver oDriver) {
        this(null, sName, oBy, oDriver);
    }

    /**
     * Constructor for wrapping any WebElement.
     *
     * @param oElement (WebElement) - Any WebElement object.
     * @param oDriver  (WebDriver) - WebDriver object.
     */
    public CommonWebElement(WebElement oElement, WebDriver oDriver) {
        this.oWebElement = oElement;
        this.oWebDriver = oDriver;
        this.oJavascriptExecutor = (JavascriptExecutor) oDriver;
        this.oAction = new Actions(oWebDriver);
    }

    /**
     * Constructor for wrapping any WebElement with a parent WebElement
     *
     * @param oElement       (WebElement) - Any WebElement object.
     * @param oElement       (WebElement) - Any WebElement object.
     * @param oParentElement (WebElement) - Parent WebElement.
     */
    public CommonWebElement(WebElement oElement, By oBy, WebElement oParentElement, WebDriver oDriver) {
        this.oWebElement = oElement;
        this.oParentWebElement = oParentElement;
        this.oBy = oBy;
        this.oWebDriver = oDriver;
        this.oJavascriptExecutor = (JavascriptExecutor) oDriver;
        this.oAction = new Actions(oWebDriver);
    }

    /**
     * Constructor for wrapping any WebElement with a parent WebElement
     *
     * @param oElement       (WebElement) - Any WebElement object.
     * @param oParentElement (WebElement) - Parent WebElement.
     */
    public CommonWebElement(WebElement oElement, WebElement oParentElement, WebDriver oDriver) {
        this(oElement, null, oParentElement, oDriver);
    }

    /**
     * Constructor for wrapping a WebElementDummy object used for error handling.
     *
     * @param sName (String) - Declared variable name.
     */
    public CommonWebElement(String sName, String sTag) {
        this.sElementName = sName;
        this.sElementTag = sTag;
//        this.oWebElement = new WebElementDummy(sName);
    }

////////////////////////////
//  Getter/Setters        //
////////////////////////////

    public static int getImplicitWait() {
        return iImplicitWait;
    }

    public static void setImplicitWait(int iImplicitWait) {
        CommonWebElement.iImplicitWait = iImplicitWait;
    }

    public static int getThrottle() {
        return iThrottleValue;
    }

    public static void setThrottle(int iSeconds) {
        CommonWebElement.iThrottleValue = iSeconds;
    }

    public static boolean isbMonitorMode() {
        return bMonitorMode;
    }

    public static void setbMonitorMode(boolean bMonitorMode) {
        CommonWebElement.bMonitorMode = bMonitorMode;
    }

    public WebDriver getWebDriver() {
        return oWebDriver;
    }

    public void setWebDriver(WebDriver oWebDriver) {
        this.oWebDriver = oWebDriver;
        this.oJavascriptExecutor = (JavascriptExecutor) oWebDriver;
    }

    public WebElement getParentWebElement() {
        return oParentWebElement;
    }

    public void setoParentWebElement(WebElement oParentWebElement) {
        this.oParentWebElement = oParentWebElement;
    }

    public WebElement getWebElement() {
        return oWebElement;
    }

    public void setWebElement(WebElement oWebElement) {
        this.oWebElement = oWebElement;
    }

    public String getElementName() {
        return sElementName;
    }

    public void setElementName(String sElementName) {
        this.sElementName = sElementName;
    }

    public String getElementTag() {
        return sElementTag;
    }

    public void setElementTag(String sElementTag) {
        this.sElementTag = sElementTag;
    }

    public By getBy() {
        return oBy;
    }

    public void setBy(By oBy) {
        this.oBy = oBy;
    }

    ///////////////////////////////////////////
    //  WebElement interface implementation  //
    ///////////////////////////////////////////

    @Override
    public void clear() {
        waitForVisible();
        oWebElement.clear();

        //If text can not be cleared successfully
        //send backspaces keys again to delete the text
        int length = oWebElement.getAttribute("value").length();
        for (int i = length; i > 0; i--) {
            oWebElement.sendKeys(Keys.BACK_SPACE);
        }

        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void click() {
        waitForEnabled();
        waitForVisible();

            System.out.println("Clicking on... " + oBy.toString());

        oWebElement.click();
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    @Override
    public CommonWebElement findElement(By arg0) {
        waitForElement();
        return new CommonWebElement(oWebElement.findElement(arg0), arg0, oWebElement, oWebDriver);
    }

    @Override
    public List<WebElement> findElements(By arg0) {
        waitForElement();
        java.util.List<WebElement> lATFWebElement = new java.util.Vector<WebElement>();
        java.util.List<WebElement> lWebElement = oWebElement.findElements(arg0);
        for (WebElement oElement : lWebElement) {
            lATFWebElement.add(new CommonWebElement(oElement, oWebElement, oWebDriver));
        }
        return lATFWebElement;
    }

    @Override
    public String getAttribute(String arg0) {
        waitForElement();
        return oWebElement.getAttribute(arg0);
    }

    @Override
    public String getCssValue(String arg0) {
        waitForElement();
        return oWebElement.getCssValue(arg0);
    }

    @Override
    public Point getLocation() {
        waitForElement();
        return oWebElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        waitForElement();
        return oWebElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getTagName() {
        waitForElement();
        return oWebElement.getTagName();
    }

    @Override
    public String getText() {
        waitForVisible();
        return oWebElement.getText();
    }

    @Override
    public boolean isDisplayed() {
        try {
            waitForElement();
            return oWebElement.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isDisplayed(int iWaitForElementTimeout) {
        try {
            waitForElement(iWaitForElementTimeout);
            return oWebElement.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean isEnabled() {
        try {
            waitForElement();
            return oWebElement.isEnabled();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean isSelected() {
        waitForElement();
        return oWebElement.isSelected();
    }

    @Override
    public void sendKeys(CharSequence... arg0) {
        //waitForVisible();
        waitForEnabled();
        oWebElement.clear();
        oWebElement.sendKeys(arg0);
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void submit() {
        waitForElement();
        oWebElement.submit();
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Coordinates getCoordinates() {
        waitForElement();
        return ((RemoteWebElement) oWebElement).getCoordinates();
    }

    //////////////////////////////////////////
    //  Class methods                       //
    //////////////////////////////////////////
    /**
     * Click on an element and wait for another element to appear or disappear
     *
     * @param element (CommonWebElement) - Element to wait for appear or disappear
     * @param bAppear (Boolean)
     *                true(will wait for the element to appear after the click)
     *                false(will wait for the element to disappear after the click)
     *
     */
    public void clickAndWait(CommonWebElement element, Boolean bAppear) {
        waitForEnabled();
        waitForVisible();
        System.out.println("Clicking on... " + oWebElement.toString());
        oWebElement.click();
        if (iThrottleValue != 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (bAppear){
            element.waitForElement();
        } else {
            if (element.exists()){
                element.waitForInvisible();
            }
        }
    }

    /**
     * Click on an offset from the top-left corner of the element.
     *
     * @param xOffset (int) - Offset from left edge of element.
     * @param yOffset (int) - Offset from top edge of element.
     */
    public void click(int xOffset, int yOffset) {
        waitForVisible();
        oAction.moveToElement(oWebElement, xOffset, yOffset).perform();
        oAction.click().perform();
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    /**
     * Double-click on element.
     */
    public void doubleClick() {
        waitForVisible();
        oAction.doubleClick(oWebElement).perform();
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    /**
     * Double-click on an offset from the top-left corner of the element.
     *
     * @param xOffset (int) - Offset from left edge of element.
     * @param yOffset (int) - Offset from top edge of element.
     */
    public void doubleClick(int xOffset, int yOffset) {
        waitForVisible();
        oAction.moveToElement(oWebElement, xOffset, yOffset).perform();
        oAction.doubleClick().perform();
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    /**
     * Mouse over element.
     */
    public void hover() {
        waitForVisible();
        oAction.moveToElement(oWebElement).perform();
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    /**
     * Mouse over element with offset.
     *
     * @param xOffset (int) - Offset from left edge of element.
     * @param yOffset (int) - Offset from top edge of element.
     */
    public void hover(int xOffset, int yOffset) {
        waitForVisible();
        oAction.moveToElement(oWebElement, xOffset, yOffset).perform();
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    /**
     * Simulate user scrolling in a scrollable element by using the HOME, END, PAGE_UP, PAGE_DOWN keys.
     *
     * @param sTarget (String) - One of four scroll keys:  HOME, END, PAGE_UP, PAGE_DOWN.
     */
    public void scrollTo(String sTarget) {
        this.click();
        switch (sTarget.toUpperCase()) {
            case "HOME":
                this.appendKeys(Keys.HOME);
                break;
            case "END":
                this.appendKeys(Keys.END);
                break;
            case "PAGEUP":
                this.appendKeys(Keys.PAGE_UP);
                break;
            case "PAGEDOWN":
                this.appendKeys(Keys.PAGE_DOWN);
                break;
            default:
                throw new CommonException("Scroll input '" + sTarget + "' not recognized!");
        }
    }

    /**
     * Scroll upto the required element
     */
    public void scrollForElement() {
        oJavascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight); return true");
    }

    /**
     * Scroll the browser so element is visible.
     */
    public Coordinates scrollIntoView() {
        return getCoordinates();
    }

    /**
     * Append input text to existing text in test field.
     *
     * @param arg0 (String) - Input text.
     */
    public void appendKeys(CharSequence... arg0) {
        waitForVisible();
        oWebElement.sendKeys(arg0);
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    /**
     * Call by element with a file input to submit the given file.
     *
     * @param arg0 (String) - Input file path.
     */
    public void sendFile(CharSequence... arg0) {
        File file = new File(arg0[0].toString());

        // This code allows RemoteWebDriver to upload local file to remote node.
        if (!(oWebDriver instanceof org.openqa.selenium.chrome.ChromeDriver) && !(oWebDriver instanceof org.openqa.selenium.firefox.FirefoxDriver) && !(oWebDriver instanceof org.openqa.selenium.ie.InternetExplorerDriver)) {
//            logger.trace("RomoteWebDriver found!");
            Reporter.log("RomoteWebDriver found! <br>");
            LocalFileDetector detector = new LocalFileDetector();
            file = detector.getLocalFile(arg0);
            ((RemoteWebDriver) oWebDriver).setFileDetector(detector);
        }

        waitForElement();
//        logger.debug("Sending file {}", file.getAbsolutePath());
        Reporter.log(String.format("Sending file {%s} <br>", file.getAbsolutePath()));
        oWebElement.sendKeys(file.getAbsolutePath());
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    /**
     * Check if element exists on the page.
     *
     * @return (Boolean)
     */
    public boolean exists() {
        boolean isPresent = oWebDriver.findElements(oBy).size() > 0;
        if (isPresent) {
            return true;
        }else {
            return false;
        }
    }
//    public boolean exists() {
//        try {
//            waitForElement(3);
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }

    /**
     * Check if this element is a dummy element.
     *
     * @return
     * (boolean)
     */
//    public boolean isDummy()
//    {
//        if (oWebElement instanceof WebElementDummy)
//            return true;
//        else
//            return false;
//    }

    /**
     * Click using Javascript.  The input Javascript must return an element.  For example "document.getElementById(SomeID)".
     *
     * @param sJavascript (String) - Javascript.
     */
    public void click(String sJavascript) {
//        logger.trace("Clicking with Javascript:  {}", sJavascript);
        Reporter.log(String.format("Clicking with Javascript:  {%s} <br>", sJavascript));

        waitForVisible();
        oJavascriptExecutor.executeScript(sJavascript + ".click()");
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public void jsClick() {
        waitForEnabled();
        //waitForVisible();
        oJavascriptExecutor.executeScript("arguments[0].click()", oWebElement);
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    /**
     * Selects item from dropdown menu by visible text.
     *
     * @param sSelection     (String) - Item text
     * @param returnSelected (boolean) - Optional parameter to indicate whether to return the selected WebElement.
     * @return (CommonWebElement)
     */
    public CommonWebElement select(String sSelection, Boolean... returnSelected) {
        waitForElement();
        org.openqa.selenium.support.ui.Select oSelect = new org.openqa.selenium.support.ui.Select(this);
        oSelect.selectByVisibleText(sSelection);
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        if (returnSelected.length > 0 && returnSelected[0] == true)
            return new CommonWebElement(oSelect.getFirstSelectedOption(), oWebElement, oWebDriver);
        else
            return null;
    }

    /**
     * Selects item from dropdown menu by visible text.
     *
     * @param sSelection     (String) - Item text
     * @param returnSelected (boolean) - Optional parameter to indicate whether to return the selected WebElement.
     * @return (CommonWebElement)
     */
    public CommonWebElement selectAngular(String sSelection) {
        CommonWebElement listItem = new CommonWebElement("listItem", "xpath=//*[text()='" + sSelection + "']", oWebDriver);
        waitForElement();
        if (!isViewable()) {
            scrollForElement();
        }
        click();
        listItem.waitForElement();
        listItem.jsClick();
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return listItem;
    }

    /**
     * Selects item from dropdown menu by the option value attribute.
     *
     * @param sSelection     (String) - Item value attribute text.
     * @param returnSelected (boolean) - Optional parameter to indicate whether to return the selected WebElement.
     * @return (CommonWebElement)
     */
    public CommonWebElement selectByValue(String sSelection, Boolean[] returnSelected) {
        waitForElement();
        org.openqa.selenium.support.ui.Select oSelect = new org.openqa.selenium.support.ui.Select(this);
        oSelect.selectByValue(sSelection);
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        if (returnSelected.length > 0 && returnSelected[0] == true)
            return new CommonWebElement(oSelect.getFirstSelectedOption(), oWebElement, oWebDriver);
        else
            return null;
    }

    /**
     * Selects item from dropdown menu by index.
     *
     * @param iIndex     (int) - Item index.
     * @param returnSelected (boolean) - Optional parameter to indicate whether to return the selected WebElement.
     * @return (CommonWebElement)
     */
    public CommonWebElement selectByIndex(int iIndex, Boolean... returnSelected) {
        waitForElement();
        org.openqa.selenium.support.ui.Select oSelect = new org.openqa.selenium.support.ui.Select(this);
        oSelect.selectByIndex(iIndex);
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        if (returnSelected.length > 0 && returnSelected[0] == true)
            return new CommonWebElement(oSelect.getFirstSelectedOption(), oWebElement, oWebDriver);
        else
            return null;
    }

    /**
     * Selects item from dropdown menu by the item text
     *
     * @param sText (String) - Item text
     */
    public void selectByVisibleTextAngular(String sText) {
        if (this.getTagName().contains("md-select")) //dropdown buttons have md-select tags
            this.click();
        else
            throw new ElementNotInteractableException(String.format("Need a dropdown list button(contains <md-select> tag), instead found <%s> tag", this.getTagName()));
        CommonWebElement oMenuItem = new CommonWebElement("oMenuItem", "xpath=//*[text()='" + sText + "']", oWebDriver);
        if (oMenuItem.isViewable()){
            oMenuItem.click();
        }else {
            oMenuItem.jsClick();
        }
        SysTools.sleepFor(1);
    }
    /**
     * Selects item from dropdown menu by the value attribute.
     *
     * @param sText (String) - Item's value attribute
     */
    public void selectByValueAngular(String sText){
        oWebElement.click(); // click menu button first to open drop down menu
        List<WebElement> options = oWebDriver.findElements(By.xpath("//md-option[@value="+ Quotes.escape(sText) + "]"));

        boolean matched = false;
        for (WebElement option : options) {
            if (option.isDisplayed())
                option.click();
            matched = true;
        }
        if (!matched) {
            throw new NoSuchElementException("Cannot locate element with value: " + sText);
        }
    }

    /**
     * Highlight element by drawing red border around it.
     */
    public void highlightMe() {
        this.border = (String) oJavascriptExecutor.executeScript("var border = arguments[0].style.border; arguments[0].style.border='2px dashed green'; return border;", oWebElement);
    }

    /**
     * Unhighlight element by removing red border around it.
     */
    public void unHighlightMe() {
        if (border == null || border.equals(""))
            oJavascriptExecutor.executeScript("arguments[0].style.border='none'", oWebElement);
        else
            oJavascriptExecutor.executeScript("arguments[0].style.border='" + border + "'", oWebElement);
    }

    /**
     * Check to see if element is viewable (displayed within the viewport of the browser).
     *
     * @return (boolean)
     */
    public boolean isViewable() {
        try {
            if (!isDisplayed())
                return false;

            long xOffsetFromTop = (long) oJavascriptExecutor.executeScript("return window.pageYOffset;");
            long viewportHeight = (long) oJavascriptExecutor.executeScript("return $(window).height();");
            long totalOffsetFromTop = xOffsetFromTop + viewportHeight;

            long yLocation;
            if (oWebDriver instanceof org.openqa.selenium.firefox.FirefoxDriver)
                yLocation = (long) oJavascriptExecutor.executeScript("return arguments[0].offsetTop;", oWebElement);
            else
                yLocation = getLocation().y;

//            logger.trace("xOffsetFromTop:  {}", xOffsetFromTop);
//            logger.trace("viewportHeight:  {}", viewportHeight);
//            logger.trace("totalOffsetFromTop:  {}", totalOffsetFromTop);
//            logger.trace("yLocation:  {}", yLocation);

            Reporter.log(String.format("xOffsetFromTop:  {%s} <br>", xOffsetFromTop));
            Reporter.log(String.format("viewportHeight:  {%s} <br>", viewportHeight));
            Reporter.log(String.format("totalOffsetFromTop:  {%s} <br>", totalOffsetFromTop));
            Reporter.log(String.format("yLocation:  {%s} <br>", yLocation));

            return (yLocation >= xOffsetFromTop) && (yLocation <= totalOffsetFromTop);
        } catch (Exception ex) {
            throw new CommonException(ex);
        }
    }

    ////////////////////////////
    //  Wait Methods          //
    ////////////////////////////

    /**
     * Wait for existence of element using specified search method.
     *
     * @param iTimeOut (long) - Wait timeout in seconds.
     */
    public CommonWebElement waitForElement(long iTimeOut) {
        WebElement oResult = null;

//        logger.trace("waitForElement():  {}, {}", sElementName, oBy);
        Reporter.log(String.format("waitForElement():  {%s}, {%s} <br>", sElementName, oBy));

        try {
            if (oBy != null) {
                Wait<By> oWait = new FluentWait<By>(oBy)
                        .withTimeout(iTimeOut, java.util.concurrent.TimeUnit.SECONDS)
                        .pollingEvery(500, java.util.concurrent.TimeUnit.MICROSECONDS)
                        .ignoring(NoSuchElementException.class);

                oResult = oWait.until(new com.google.common.base.Function<org.openqa.selenium.By, WebElement>() {
                    @Override
                    public WebElement apply(org.openqa.selenium.By oBy) {
                        WebElement oFound;

                        try {
                            if (oParentWebElement != null)
                                oFound = oParentWebElement.findElement(oBy);
                            else
                                oFound = oWebDriver.findElement(oBy);
                        } catch (Exception ex) {
                            return null;
                        }
                        return oFound;
                    }
                });
            } else
                oResult = oWebElement;
        } catch (org.openqa.selenium.TimeoutException ex) {
            String sText = "Name:  " + sElementName + ", Tag:  " + (sElementTag != null ? sElementTag : oBy.toString());
            throw new CommonException("Timeout looking for element.  " + sText, ex);
        } catch (Exception ex) {
            throw new CommonException("Unhandled exception", ex);
        }

        if (oResult != null) {
            oWebElement = oResult;

            // Highlight element
            if (bMonitorMode) {
                highlightMe();
                try {Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                unHighlightMe();
            }

            return this;
        } else
            return this;
    }

    public CommonWebElement waitForElement() {
        return waitForElement(iImplicitWait);
    }

    /**
     * Wait to become visible.
     *
     * @param iTimeOut (long) - Wait timeout in seconds.
     */
    public CommonWebElement waitForVisible(long iTimeOut) {
//        logger.trace("waitForVisible():  {}, {}", sElementName, oBy);
        Reporter.log(String.format("waitForVisible():  {%s}, {%s} <br>", sElementName, oBy));

        if (oBy != null) {
            waitForElement(iTimeOut);

            try {
                Wait<WebDriver> oWait = new WebDriverWait(oWebDriver, iTimeOut);
                oWait.until(ExpectedConditions.visibilityOf(oWebElement));
            } catch (org.openqa.selenium.TimeoutException ex) {
                throw new CommonException("Timeout waiting for element " + sElementName + " to become visible", ex);
            }
        }
        return this;
    }

    public CommonWebElement waitForVisible() {
        return waitForVisible(iImplicitWait);
    }

    /**
     * Wait to become invisible.
     *
     * @param iTimeOut (long) - Wait timeout in seconds.
     */
    public void waitForInvisible(long iTimeOut) {
//        logger.trace("waitForInvisible():  {}, {}", sElementName, oBy);
        Reporter.log(String.format("waitForInvisible():  {%s}, {%s} <br>", sElementName, oBy));

        if (oBy != null) {
            try {
                Wait<WebDriver> oWait = new WebDriverWait(oWebDriver, iTimeOut);
                oWait.until(ExpectedConditions.invisibilityOfElementLocated(oBy));
            } catch (org.openqa.selenium.TimeoutException ex) {
                throw new CommonException("Timeout waiting for element " + sElementName + " to disappear!", ex);
            }
        }
    }

    public void waitForInvisible() {
        waitForInvisible(iImplicitWait);
    }

    /**
     * Wait to become enabled.  Used to determine if text fields/buttons are editable/clickable.
     *
     * @param iTimeOut (long) - Wait timeout in seconds.
     */
    public void waitForEnabled(long iTimeOut) {
//        logger.trace("waitForEnabled():  {}, {}", sElementName, oBy);
        Reporter.log(String.format("waitForEnabled():  {%s}, {%s} <br>", sElementName, oBy));

        if (oBy != null) {
            waitForElement(iTimeOut);

            try {
                Wait<WebElement> oWait = new FluentWait<WebElement>(oWebElement)
                        .withTimeout(iTimeOut, java.util.concurrent.TimeUnit.SECONDS)
                        .pollingEvery(500, java.util.concurrent.TimeUnit.MICROSECONDS)
                        .ignoring(NoSuchElementException.class);

//                oWait.until(new com.google.common.base.Function<WebElement, Boolean>() {
//                    @Override
//                    public Boolean apply(WebElement oWebElement) {
//                        return oWebElement.isEnabled();
//                    }
//                });
                oWait.until((Function<WebElement, Boolean>) oWebElement -> oWebElement.isEnabled());
            } catch (org.openqa.selenium.TimeoutException ex) {
                throw new CommonException("Timeout waiting for element " + sElementName + " to be enabled", ex);
            }
        }
        return;
    }

    public void waitForEnabled() {
        waitForEnabled(iImplicitWait);
    }

    public void waitForAttribute(String sName, String sValue, long iTimeOut) {
//        logger.trace("waitForAttribute():  {}, {}", sElementName, oBy);
        Reporter.log(String.format("waitForAttribute():  {%s}, {%s} <br>", sElementName, oBy));

        if (oBy != null) {
            waitForElement(iTimeOut);

            String[] input = {sName, sValue};

            try {
                Wait<String[]> oWait = new FluentWait<String[]>(input)
                        .withTimeout(iTimeOut, java.util.concurrent.TimeUnit.SECONDS)
                        .pollingEvery(500, java.util.concurrent.TimeUnit.MICROSECONDS)
                        .ignoring(NoSuchElementException.class);

                oWait.until(new com.google.common.base.Function<String[], Boolean>() {
                    @Override
                    public Boolean apply(String[] input) {
                        String sAttribValue = oWebElement.getAttribute(input[0]);
                        if (sAttribValue == null)
                            sAttribValue = "";
                        return Pattern.matches(input[1], sAttribValue);
                    }
                });
            } catch (org.openqa.selenium.TimeoutException ex) {
                throw new CommonException("Timeout waiting for element " + sElementName + " to have attribute '" + sName + "' value matching '" + sValue + "'", ex);
            }
        }
        return;
    }

    public void waitForAttribute(String sName, String sValue) {
        waitForAttribute(sName, sValue, iImplicitWait);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }
}
