package utilities;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

class DriverFactory {

    private static final String USERNAME = "qaheal";
    private static final String ACCESS_KEY = "e14bb2d7-155b-4775-8978-9365c5b22012";
    private static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    private static String os = System.getProperty("os.name");
    private static String path = System.getProperty("user.dir");
    private static String separator = System.getProperty("file.separator");
    private static DesiredCapabilities capabilities;

    static WebDriver createInstance(String environment, String browserName, String platform, String version, String screenResolution) {
        WebDriver driver = null;
        Dimension dimension = new Dimension(1280, 960);
        //DesiredCapabilities capabilities;

        if (environment.equalsIgnoreCase("remote")) {

            capabilities = getDesiredCapabilities(browserName, platform, version);
            try {
                driver = new RemoteWebDriver(new URL(URL), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }

        switch (browserName) {
            case "chrome":
                if (os.contains("Mac")) {
                    System.setProperty("webdriver.chrome.driver", path + separator + "chromedriver");
                }else {
                    System.setProperty("webdriver.chrome.driver", path + separator + "chromedriver.exe");
                }

                DesiredCapabilities chromeDesiredCapabilities = DesiredCapabilities.chrome();
                driver = new ChromeDriver(chromeDesiredCapabilities);
                driver.manage().window().setSize(dimension);
                return driver;
            case "safari":
                driver = new SafariDriver();
                return driver;
            case "firefox":
                driver = new FirefoxDriver();
                driver.manage().window().setSize(dimension);
                return driver;
            default:
                break;
        }
        return driver;
    }

    private static DesiredCapabilities getDesiredCapabilities(String browserName, String platform, String browserVersion) {

        switch (browserName) {
            case "IE":
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability("version", browserVersion);
                capabilities.setCapability("platform", platform);
                break;
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("version", browserVersion);
                capabilities.setCapability("platform", platform);
                break;
            case "safari":
                capabilities = DesiredCapabilities.safari();
                capabilities.setCapability("version", browserVersion);
                capabilities.setCapability("platform", platform);
                break;
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability("version", browserVersion);
                capabilities.setCapability("platform", platform);
                break;
            case "iPhone":
                capabilities = DesiredCapabilities.iphone();
                capabilities.setCapability("browserName", "Safari");
                capabilities.setCapability("appiumVersion", "1.6.4");
                capabilities.setCapability("deviceName", "iPhone 7 Simulator");
                capabilities.setCapability("deviceOrientation", "portrait");
                capabilities.setCapability("platformVersion", "10.3");
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("version", browserVersion);
                break;
            default:
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability("version", browserVersion);
                break;
        }
        return capabilities;
    }
}