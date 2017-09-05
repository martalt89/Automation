package com.heal.framework.web;

import com.google.common.base.Function;
import com.heal.framework.exception.CommonException;
import com.heal.framework.foundation.SysTools;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by vahanmelikyan on 7/1/17.
 */
public class CommonWebElement implements WebElement, Locatable {
    private static Logger logger = LoggerFactory.getLogger(CommonWebElement.class);


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

//    public CommonWebElement(String sName, String sTagWeb, String sTagNative, WebDriver oDriver) {
//
//        this.sElementName = sName;
//        this.sElementTag = sTagWeb + " | " + sTagNative;
//        this.oWebDriver = oDriver;
//    }
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
        waitForClickable();

            //System.out.println("Clicking on... " + oBy.toString());

        oWebElement.click();
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    @Override
    public CommonWebElement findElement(By oBy) {
        waitForElement();
        List<CommonWebElement> commonWebElements = findAllElements(oBy);
        if(commonWebElements.size() == 0){
            throw new NoSuchElementException("no such element found for： " + oBy.toString());
        }
        return findAllElements(oBy).get(0);
    }

    @Override
    public List<WebElement> findElements(By oBy) {
        waitForElement();
        return oWebElement.findElements(oBy);
    }


    public List<CommonWebElement> findAllElements(By oBy) {
        waitForElement();
        List<CommonWebElement> commonWebElements = new java.util.ArrayList<CommonWebElement>();
        List<WebElement> lWebElement = oWebElement.findElements(oBy);
        for (WebElement oElement : lWebElement) {
            if(WebBase.Ignore_Hidden_Element ){
                if(!oElement.isDisplayed()){
                    continue;
                }
                commonWebElements.add(new CommonWebElement(oElement,oWebElement, oWebDriver));
            }
            else
                commonWebElements.add(new CommonWebElement(oElement,oWebElement, oWebDriver));
        }
        return commonWebElements;
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
        waitForVisible();
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
     */
    public void clickAndWait(CommonWebElement element, Boolean bAppear) {
        waitForEnabled();
        waitForVisible();
        //System.out.println("Clicking on... " + oBy.toString());
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
     * Perform javaScript click on an element and wait for another element to appear or disappear
     *
     * @param element (CommonWebElement) - Element to wait for appear or disappear
     * @param bAppear (Boolean)
     *                true(will wait for the element to appear after the click)
     *                false(will wait for the element to disappear after the click)
     */
    public void jsClickAndWait(CommonWebElement element, Boolean bAppear) {

        waitForEnabled();
        //waitForVisible();
        oJavascriptExecutor.executeScript("arguments[0].click()", oWebElement);
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
     * Set text in a given input field using javaScript
     * @param sText - Text to set in the field
     */
    public void jsSendKeys(String... sText) {
        waitForEnabled();
        //waitForVisible();
        String input = "";
        for (int i = 0; i < sText.length ; i++) {
            input = input + sText[i];
        }
        oJavascriptExecutor.executeScript("arguments[0].value='" + input + "'", oWebElement);
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
                Thread.sleep(2000);
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
            logger.trace("RomoteWebDriver found!");
            LocalFileDetector detector = new LocalFileDetector();
            file = detector.getLocalFile(arg0);
            ((RemoteWebDriver) oWebDriver).setFileDetector(detector);
        }

        waitForElement();
        logger.debug("Sending file {}", file.getAbsolutePath());
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
    
    /**
     * Click using Javascript.  The input Javascript must return an element.  For example "document.getElementById(SomeID)".
     *
     * @param sJavascript (String) - Javascript.
     */
    public void click(String sJavascript) {
        logger.trace("Clicking with Javascript:  {}", sJavascript);

        waitForVisible();
        oJavascriptExecutor.executeScript(sJavascript + ".click()");
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    /**
     * Performs javaScript click() on the element
     */
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
    public CommonWebElement selectByValue(String sSelection, Boolean... returnSelected) {
        waitForElement();
        org.openqa.selenium.support.ui.Select oSelect = new org.openqa.selenium.support.ui.Select(this);
        oSelect.selectByValue(sSelection);
        if (iThrottleValue != 0)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        if (returnSelected.length > 0 && returnSelected[0])
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
        if (this.getTagName().contains("md-select") || this.getTagName().contains("md-select-value") ) //dropdown buttons have md-select or md-select-value tags
            this.click();
        else
            throw new ElementNotInteractableException(String.format("Need a dropdown list button(contains <md-select> or <md-select-value> tag), instead found <%s> tag", this.getTagName()));
        CommonWebElement oMenuItem = new CommonWebElement("oMenuItem", "xpath=//md-option/div[text()='" + sText + "']", oWebDriver);
        if (oMenuItem.isViewable()){
            oMenuItem.click();
        }else {
            oMenuItem.jsClick();
        }
    }

    /**
     * Selects item from dropdown menu by the item text within specified parent/frame
     *
     * @param sText (String) - Item text
     * @param oContext (CommonWebElement) - The parent element
     */
    public void selectFromContextByVisibleTextAngular(String sText, CommonWebElement oContext) {
        CommonWebElement oListItem = new CommonWebElement("oListItem", "xpath=//md-option/div[text()='" + sText + "']", oWebDriver);
        if (this.getTagName().contains("md-select") || this.getTagName().contains("md-select-value") ) { //dropdown buttons have md-select or md-select-value tags
            //oContext.waitForElement();
            this.click();
        }
        else{
            throw new ElementNotInteractableException(String.format("Need a dropdown list button(contains <md-select> or <md-select-value> tag), instead found <%s> tag", this.getTagName()));
        }

        int count = 0;
        int maxTries = 3;

        CommonWebElement oMenuItem;
        while (true) {
            try {
                oMenuItem = oContext.findElement(By.xpath("//md-option/div[text()='" + sText + "']"));
                break;
            } catch (Exception e) {
                SysTools.sleepFor(1);
                if (++count == maxTries) throw e;
            }
        }
        if (oMenuItem.isViewable()){
            oMenuItem.click();
        }else {
            oMenuItem.jsClick();
        }
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
            logger.trace("xOffsetFromTop:  {}", xOffsetFromTop);
            logger.trace("viewportHeight:  {}", viewportHeight);
            logger.trace("totalOffsetFromTop:  {}", totalOffsetFromTop);
            logger.trace("yLocation:  {}", yLocation);

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

        logger.trace("waitForElement():  {}, {}", sElementName, oBy);

        try {
            if (oBy != null) {
                Wait<By> oWait = new FluentWait<>(oBy)
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
            throw new NoSuchElementException("Timeout looking for element.  " + sText, ex);
        } catch (Exception ex) {
            throw new CommonException("Unhandled exception", ex);
        }

        if (oResult != null)
        {
            oWebElement = oResult;

            // Highlight element
            if (bMonitorMode)
            {
                highlightMe();
                SysTools.sleepFor(1);
                unHighlightMe();
            }

            return this;
        }
        else
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
        logger.trace("waitForVisible():  {}, {}", sElementName, oBy);

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
        logger.trace("waitForInvisible():  {}, {}", sElementName, oBy);

        if (oBy != null) {
            try {
                Wait<WebDriver> oWait = new WebDriverWait(oWebDriver, iTimeOut);
                oWait.until(ExpectedConditions.invisibilityOfElementLocated(oBy));
            } catch (org.openqa.selenium.TimeoutException ex) {
                throw new ElementNotVisibleException("Timeout waiting for element " + sElementName + " to disappear!", ex);
            }
        }
    }

    public void waitForInvisible() {
        waitForInvisible(iImplicitWait);
    }


    public void sync(long iTimeOut){
        try{
            waitForVisible(iTimeOut);
        }
        catch (Exception e){
            //do nothing
        }

        try{
            waitForInvisible(iTimeOut);
        }
        catch (Exception e){
            //do nothing
        }
    }

    /**
     * Wait to become enabled.  Used to determine if text fields/buttons are editable/clickable.
     *
     * @param iTimeOut (long) - Wait timeout in seconds.
     */
    public void waitForEnabled(long iTimeOut) {
        logger.trace("waitForEnabled():  {}, {}", sElementName, oBy);

        if (oBy != null) {
            waitForElement(iTimeOut);

            try {
                Wait<WebElement> oWait = new FluentWait<>(oWebElement)
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
                throw new ElementNotInteractableException("Timeout waiting for element " + sElementName + " to be enabled", ex);
            }
        }
        return;
    }

    public void waitForEnabled() {
        waitForEnabled(iImplicitWait);
    }

    public void waitForClickable(){
        WebDriverWait wait = new WebDriverWait(oWebDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(oWebElement));
    }


    public void waitForAttribute(String sName, String sValue, long iTimeOut) {
        logger.trace("waitForAttribute():  {}, {}", sElementName, oBy);

        if (oBy != null) {
            waitForElement(iTimeOut);

            String[] input = {sName, sValue};

            try {
                Wait<String[]> oWait = new FluentWait<>(input)
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
