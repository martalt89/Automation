package com.heal.framework.test;

import com.heal.framework.foundation.ExtentManager;
import com.heal.framework.foundation.ExtentTestManager;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.saucelabs.saucerest.SauceREST;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.w3c.dom.Document;

import 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vahanmelikyan on 8/1/2017.
 */
public class TestListener extends TestListenerAdapter {
    Map testParams = RunTestSuite.getExcelParams();
    String env = testParams.get("environment").toString();
    String sauceUsername = testParams.get("USERNAME").toString();
    String sauceAccessKey = testParams.get("ACCESS_KEY").toString();
    SauceREST sauceREST = new SauceREST(sauceUsername, sauceAccessKey);

    Logger logger = LoggerFactory.getLogger(TestListener.class);
    Document oResultXML;
    java.util.List<ITestResult> lConfigFailures = new ArrayList<ITestResult>();

    private  TestBase oTestBase;
    public TestListener(Document resultXML)
    {
        oResultXML = resultXML;
    }

    @Override
    public void onTestStart(ITestResult oResult) {
        oTestBase = (TestBase)oResult.getInstance();
        String job_id = oTestBase.getSessionID();
        Map<String, Object> jobUpdates = new HashMap<>();
        System.out.println(oTestBase);
        super.onTestStart(oResult);
        logger.info("[" + oResult.getName() + " Start]");
        ExtentTest test = oTestBase.getExtentTest();
        if (env.equalsIgnoreCase("remote")){
            String testName = oResult.getName();
            jobUpdates.put("name", testName);
            jobUpdates.put("tags", testParams.get("ENV").toString());
            jobUpdates.put("tags", testParams.get("ENV").toString());

            sauceREST.updateJobInfo(job_id, jobUpdates);
        }
        if(test == null){
            test = ExtentTestManager.startTest(oResult.getName());
            oTestBase.setExtentTest(test);
        }
    }

    @Override
    public void onTestFailure(ITestResult oResult)
    {
        ExtentTest test = oTestBase.getExtentTest();
        String sScreenshotPath = oTestBase.handleException(oResult.getThrowable());
        String img = test.addScreenCapture(sScreenshotPath);
        test.log(LogStatus.FAIL, oResult.getThrowable());
        test.log(LogStatus.FAIL, "Exception Image", "Exception Screenshot: " + img);
        ExtentManager.getReporter().endTest(test);
        if (env.equalsIgnoreCase("remote")){
        String job_id = oTestBase.getSessionID();
        sauceREST.jobFailed(job_id);
        }
    }

    @Override
    public void onTestSuccess(ITestResult oResult)
    {
        ExtentTest test = oTestBase.getExtentTest();
        ExtentManager.getReporter().endTest(test);
        if (env.equalsIgnoreCase("remote")){
            String job_id = oTestBase.getSessionID();
            sauceREST.jobPassed(job_id);
        }
    }

    @Override
    public void onTestSkipped(ITestResult oResult)
    {
        ExtentTest test = ExtentTestManager.startNewTest(oResult.getName());
        oTestBase.setExtentTest(test);
        test.log(LogStatus.SKIP, oResult.getThrowable());
        ExtentManager.getReporter().endTest(test);
    }

    @Override
    public void onConfigurationFailure(ITestResult oResult)
    {
        try
        {
            logger.error("onConfigurationFailure():  {}", oResult.getName());
            logger.error("onConfigurationFailure():  ", oResult.getThrowable());
            oResult.setAttribute("Errors", oResult.getThrowable().getMessage());
            lConfigFailures.add(oResult);
        }
        catch(Exception ex)
        {
            logger.error("onConfigurationFailure():  Exception caught! ", ex);
        }
    }

    @Override
    public void onFinish(ITestContext oTestContext)
    {


    }

    /**
     * Comparator class used to sort ITestResult objects by test name
     *
     */
    public class ResultComparator implements Comparator<ITestResult>
    {
        @Override
        public int compare(ITestResult o1, ITestResult o2)
        {
            String testName1, testName2;
            testName1 = o1.getName();
            testName2 = o2.getName();

            return testName1.compareToIgnoreCase(testName2);
        }

    }

}
