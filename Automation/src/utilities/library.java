package utilities;

import java.io.IOException;
import java.util.Properties;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utilities.DriverManager.*;

/**
 * Created by vahanmelikyan on 6/22/17.
 */
public class library {

    int timeOut = 30;
    private Properties properties = new Properties();

    public void prinlntWithThreadID(String string) {
        System.out.println("Thread " + Thread.currentThread().getId() + "---"+ string);

    }

    public void loadPropertyFile(String fileName) throws IOException {
        fileName = "resources/" + fileName;
        try (FileReader inputStream = new FileReader(fileName)) {
         properties.load(inputStream);
    } catch (Exception e) {
        e.printStackTrace();
        }
    }

    public String getProp(String propName) {
        String myProp = properties.getProperty(propName.toString());
        return myProp;
    }

    public static void browserGoTo(String url) {
        getDriver().get(url);
    }

    public WebElement waitForElement(String element) {
        WebElement we = null;

            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), timeOut);
                we = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
            } catch (Exception ex) {
            }
        return we;
    }

    public void waitForElementToDisappear(String  element) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeOut);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
        } catch (Exception ex) {
        }

    }

    public Boolean elementExists(String element) {

        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 2);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
            if (getDriver().findElement(By.xpath(element)).isDisplayed() || getDriver().findElement(By.xpath(element)).isEnabled() ) {
                return true;
            }else {
                System.out.println("The element is clickable but not displayed........");
                return false;

            }

        } catch (Exception ex) {
            prinlntWithThreadID("Cannot find element " + element.toString());
            return false;
        }
    }

    public void clickElement(String element) {
        if (timeOut != 0) {
            prinlntWithThreadID("Clicking on " + element.toString());
            waitForElement(element).click();
        } else  {
            getDriver().findElement(By.xpath(element)).click();
        }
    }
    // Performs a click on 'element' and waits for 'elementToDisappear' to disappear
    public void clickElementAndWait(String element, String elementToDisappear) {
        if (timeOut != 0) {
            prinlntWithThreadID("Clicking on " + element.toString());
            waitForElement(element).click();
        } else  {
            getDriver().findElement(By.xpath(element)).click();
        }
        try {
            if (elementExists(elementToDisappear)) {
                waitForElementToDisappear(elementToDisappear);
            }
        } catch (StaleElementReferenceException ex) {

        }

    }

    // Clears text field
    public void clearText(String element) {
        try {
            waitForElement(element);
            getDriver().findElement(By.xpath(element)).clear();
        } catch (Exception e) {

        }
    }

    //sets the text field value and will clear the text field before writing
    //based on the clear boolean
    public void setText(String element, Boolean clear, String data, int timeOut) {
        try {
            waitForElement(element);
            if (clear) {
                clearText(element);
            }
            getDriver().findElement(By.xpath(element)).sendKeys(data);
            waitForElement(element);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void openMenu(String element) {
        if (elementExists(element)){
            clickElement(element);
        }
    }
}
