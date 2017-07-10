package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.openqa.selenium.Dimension;

public class WebDriverListener implements IInvokedMethodListener {


    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
//            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            String browserName = method.getTestMethod().getXmlTest().getAllParameters().get("browserName");
            String platform = method.getTestMethod().getXmlTest().getAllParameters().get("platform");
            String version = method.getTestMethod().getXmlTest().getAllParameters().get("version");
            WebDriver driver = DriverFactory.createInstance(browserName, platform, version);
            DriverManager.setWebDriver(driver);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                driver.close();
                driver.quit();
            }
        }
    }
}