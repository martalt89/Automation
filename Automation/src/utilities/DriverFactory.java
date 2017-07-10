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


    private static final String USERNAME = "vmelikyan";
    private static final String ACCESS_KEY = "48aaa651-4c31-41b7-9536-9de238905f03";
    private static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";


    static WebDriver createInstance(String environment, String browserName, String platform, String version, String screenResolution) {
        WebDriver driver = null;
        Dimension dimension = new Dimension(1280, 960);
        DesiredCapabilities capabilities;

        if (environment.equalsIgnoreCase("remote")) {
            switch (browserName) {
                case "chrome":
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.setCapability("platform", platform);
                    capabilities.setCapability("version", version);
                    capabilities.setCapability("screenResolution", screenResolution);
                    try {
                        driver = new RemoteWebDriver(new URL(URL), capabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver.manage().window().maximize();
                    return driver;

                case "safari":
                    capabilities = DesiredCapabilities.safari();
                    capabilities.setCapability("platform", platform);
                    capabilities.setCapability("version", version);
                    capabilities.setCapability("screenResolution", screenResolution);
                    try {
                        driver = new RemoteWebDriver(new URL(URL), capabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver.manage().window().maximize();
                    return driver;
                case "firefox":
                    capabilities = DesiredCapabilities.firefox();
                    capabilities.setCapability("platform", platform);
                    capabilities.setCapability("version", version);
                    capabilities.setCapability("screenResolution", screenResolution);
                    try {
                        driver = new RemoteWebDriver(new URL(URL), capabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver.manage().window().maximize();
                    return driver;
                case "IE":
                    capabilities = DesiredCapabilities.internetExplorer();
                    capabilities.setCapability("platform", platform);
                    capabilities.setCapability("version", version);
                    try {
                        driver = new RemoteWebDriver(new URL(URL), capabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver.manage().window().maximize();
                    return driver;
                default:
                    break;
            }
        }
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "chromedriver");
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
//        if (browserName.toLowerCase().contains("firefox")) {
//            driver = new FirefoxDriver();
//            driver.manage().window().setSize(dimension);
//            return driver;
//        }
//        if (browserName.toLowerCase().contains("safari")) {
//            driver = new SafariDriver();
//
//            return driver;
//        }
//
//        if (browserName.toLowerCase().contains("chrome")) {
//            System.setProperty("webdriver.chrome.driver", "chromedriver");
//            DesiredCapabilities chromeDesiredCapabilities = DesiredCapabilities.chrome();
//            driver = new ChromeDriver(chromeDesiredCapabilities);
//            driver.manage().window().setSize(dimension);
//            return driver;
//        }
        return driver;
    }
}


//	public static String getDriver(String key)
//	{
//		return localpath + System.getProperty("file.separator") + key;
//	}
//}