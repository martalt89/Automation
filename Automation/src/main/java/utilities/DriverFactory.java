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
    private static String os = System.getProperty("os.name");
    private static String path = System.getProperty("user.dir");
    private static String separator = System.getProperty("file.separator");


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
                    capabilities.setCapability("screenResolution", screenResolution);
                    try {
                        driver = new RemoteWebDriver(new URL(URL), capabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver.manage().window().maximize();
                    return driver;
                case "iPhone":
                    capabilities = DesiredCapabilities.iphone();
                    capabilities.setCapability("appiumVersion", "1.6.4");
                    capabilities.setCapability("deviceName","iPhone Simulator");
                    capabilities.setCapability("deviceOrientation", "portrait");
                    capabilities.setCapability("platformVersion","10.3");
                    capabilities.setCapability("platformName", "iOS");
                    capabilities.setCapability("browserName", "Safari");
                    try{
                        driver = new RemoteWebDriver(new URL(URL), capabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                default:
                    break;
            }
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
}


//	public static String getDriver(String key)
//	{
//		return localpath + System.getProperty("file.separator") + key;
//	}
//}