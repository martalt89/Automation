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
	
	private static String localpath	= System.getProperty("user.dir");
    public static final String USERNAME = "vmelikyan";
    public static final String ACCESS_KEY = "48aaa651-4c31-41b7-9536-9de238905f03";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";


    static WebDriver createInstance (String browserName, String platform, String version) {
        WebDriver driver = null;
        Dimension dimension = new Dimension(1380,900);


        if (browserName.toLowerCase().contains("sauce")) {
            DesiredCapabilities caps = DesiredCapabilities.safari();
            caps.setCapability("platform", platform);
            caps.setCapability("version", version);

            try {
                driver = new RemoteWebDriver(new URL(URL), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            driver.manage().window().maximize();
            return driver;
        }


        if (browserName.toLowerCase().contains("firefox")) {
            driver = new FirefoxDriver();
            driver.manage().window().setSize(dimension);
            return driver;
        }
        if (browserName.toLowerCase().contains("safari")) {
            driver = new SafariDriver();

            return driver;
        }

        if (browserName.toLowerCase().contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            DesiredCapabilities chromeDesiredCapabilities = DesiredCapabilities.chrome();
            driver = new ChromeDriver(chromeDesiredCapabilities);
            driver.manage().window().setSize(dimension);
            return driver;
        }
        return driver;
    }


	public static String getDriver(String key)
	{
		return localpath + System.getProperty("file.separator") + key;
	}
}