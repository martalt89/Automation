package framework.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Properties;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;
import framework.exception.CommonException;
import foundation.SysTools;

/**
 * Created by vahanmelikyan on 8/1/2017.
 */
public class TestListener extends TestListenerAdapter {

    private static String path = System.getProperty("user.dir");
    private static String separator = System.getProperty("file.separator");
    Logger logger = LoggerFactory.getLogger(TestListener.class);
    Document oResultXML;
    java.util.List<ITestResult> lConfigFailures = new ArrayList<ITestResult>();
    int iSuiteVerificationPassTotal = 0;
    int iSuiteVerificationFailTotal = 0;
    Date suiteStart = null;
    java.util.List<ITestResult> lAllTests = new ArrayList<ITestResult>();

    private  TestBase oTestBase;
    public TestListener(Document resultXML)
    {
        oResultXML = resultXML;
    }

    @Override
    public void onTestStart(ITestResult oResult) {
        oTestBase = (TestBase)oResult.getInstance();
        super.onTestStart(oResult);
        logger.info("【" + oResult.getName() + " Start】");

    }

    @Override
    public void onTestFailure(ITestResult oResult)
    {
        ExtentTest test = oTestBase.getExtentTest();
        try
        {
            logger.trace("onTestFailure():  {}", oResult.getName());
            TestBase oTestBase = (TestBase)oResult.getInstance();

            // If there's exception in TestBase.beforeMethod().  Skip the test.
            if (oTestBase.getException() != null)
            {
                onTestSkipped(oResult);
                return;
            }

            String sException;

            // This is to remove the build info from the actual exception message to produce a more concise run summary
            if (!(oResult.getThrowable() instanceof java.lang.NullPointerException))
            {
                sException = oResult.getThrowable().getMessage();
                if (sException == null)
                    sException = "Unknown exception message";
                int iRemoveBegin = sException.indexOf("Command duration or timeout:");
                if (iRemoveBegin > 0)
                    sException = sException.substring(0, iRemoveBegin).trim();
            }
            else
            {
                sException = "java.lang.NullPointerException";
            }

            // Work around to get result to show up on dashboard if there was unhandled exception and no verification errors were found.
            if (sException.indexOf("verification failures") == -1)
                oTestBase.getValidate().verifyEquals("Verify whether test method completed without exception", sException + "\n\n" + oResult.getThrowable().getCause(), "");


            // Convert errors to string to be output as exception message
            StringBuilder errorString = new StringBuilder();
            for(String sError : oTestBase.getValidate().getFailures())
            {
                errorString.append(sError);
                errorString.append("\n\n");
            }

            // Get screenshot if any.  Verification errors each already have it's own screenshot.
            String sCause = "";
            if (sException.indexOf("verification failures") > 0)
            {
                sCause = "Verification failures found:\n\n" + errorString.toString() + "\n\n";
                oResult.setAttribute("Cause", sCause);
                oResult.setThrowable(new CommonException(sCause, oResult.getThrowable()));
            }
            else
            {
                String sScreenshotPath = oTestBase.handleException(oResult.getThrowable());
                String img = test.addScreenCapture(sScreenshotPath);
                test.log(LogStatus.ERROR, "Exception Image", "Exception Screenshot: " + img);
                sCause = "Unhandled exception:  [Screenshot:  " + sScreenshotPath + "]\n\n" + sException + "\n\n";
                oResult.setAttribute("Cause", sCause);
                oResult.setThrowable(new CommonException(sCause, oResult.getThrowable()));
            }

            oResult.setAttribute("RemoteNode", oTestBase.getRemoteNode());
            oResult.setAttribute("TotalVerifications", oTestBase.getValidate().getTotalCount());
            oResult.setAttribute("FailedVerifications", oTestBase.getValidate().getFailureCount());

            // Need to convert vector to string array because vector object does not "travel" with setAttribute.
            if (oTestBase.getValidate() != null && oTestBase.getValidate().getFailureCount() > 0)
            {
                String[] aErrors = new String[oTestBase.getValidate().getFailureCount()];
                oTestBase.getValidate().getFailures().toArray(aErrors);
                oResult.setAttribute("Errors", aErrors);
            }

            java.util.List<ITestResult> lFailedTests = getFailedTests();
            lFailedTests.add(oResult);
            setFailedTests(lFailedTests);
            iSuiteVerificationFailTotal = iSuiteVerificationFailTotal +  oTestBase.getValidate().getFailureCount();
            iSuiteVerificationPassTotal = iSuiteVerificationPassTotal + oTestBase.getValidate().getTotalCount() - oTestBase.getValidate().getFailureCount();

            BufferedWriter out = new BufferedWriter(new FileWriter(path + separator + "out" + separator + "logs" + separator + "err_count.txt"));
            out.write(iSuiteVerificationFailTotal + "");
            out.close();

            // Add to all test list for generating XML result later
            lAllTests.add(oResult);

        }
        catch(Exception ex)
        {
            logger.error("onTestFailure():  Exception caught! ", ex);
        }

        test.log(LogStatus.FAIL, oResult.getThrowable());
    }

    @Override
    public void onTestSuccess(ITestResult oResult)
    {
        ExtentTest test = oTestBase.getExtentTest();
        try
        {
            logger.trace("onTestSuccess():  {}", oResult.getName());
            java.util.List<ITestResult> lPassedTests = getPassedTests();
            TestBase oTestBase = (TestBase)oResult.getInstance();
            oResult.setAttribute("RemoteNode", oTestBase.getRemoteNode());
            oResult.setAttribute("TotalVerifications", oTestBase.getValidate().getTotalCount());
            lPassedTests.add(oResult);
            setPassedTests(lPassedTests);
            iSuiteVerificationPassTotal = iSuiteVerificationPassTotal + oTestBase.getValidate().getTotalCount();

            // Add to all test list for generating XML result later
            lAllTests.add(oResult);

        }
        catch(Exception ex)
        {
           logger.error("onTestSuccess():  Exception caught! ", ex);
        }

        //test.log(LogStatus.PASS, oResult.getName() + ": PASS");
    }

    @Override
    public void onTestSkipped(ITestResult oResult)
    {
        ExtentTest test = oTestBase.getExtentTest();
        try
        {
            if (lConfigFailures.size() > 0)
            {
                String error = (String)lConfigFailures.get(0).getAttribute("Errors");
                logger.info("onTestSkipped():  {}.  Reason:  {}", oResult.getName(), error);
                oResult.setAttribute("Errors", error);
            }

            java.util.List<ITestResult> lSkippedTests = getSkippedTests();
            lSkippedTests.add(oResult);
            setSkippedTests(lSkippedTests);

            // Add to all test list for generating XML result later
            lAllTests.add(oResult);

        }
        catch(Exception ex)
        {
            logger.error("onTestSkipped():  Exception caught! ", ex);
        }

        //test.log(LogStatus.SKIP, oResult.getName() + ": Skip");
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
        int nbrPassedTests = getPassedTests().size();
        int nbrFailedTests = getFailedTests().size();
        int nbrSkippedTests = getSkippedTests().size();

        ///////////////////////////////////////
        //  Create Xml result file           //
        ///////////////////////////////////////

        SimpleDateFormat xmlDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        // Adding stylesheet reference for processing results.xml into html
        oResultXML.setXmlStandalone(true);
        ProcessingInstruction pi = oResultXML.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"Result.xslt\"");
        oResultXML.insertBefore(pi, null);

        // Create Suite node if not already present
        Element suiteNode = oResultXML.getDocumentElement();
        if (suiteNode == null)
        {
            suiteNode = oResultXML.createElement("suite");
            suiteNode.setAttribute("name", oTestContext.getSuite().getName());
            suiteStart = oTestContext.getStartDate();
            suiteNode.setAttribute("start", xmlDateFormat.format(suiteStart));
            oResultXML.appendChild(suiteNode);
        }

        suiteNode.setAttribute("end", xmlDateFormat.format(oTestContext.getEndDate()));
        suiteNode.setAttribute("elapsed", SysTools.getElapsedTimeString(suiteStart, oTestContext.getEndDate()));
        suiteNode.setAttribute("status", nbrFailedTests > 0?"Fail":"Pass");
        suiteNode.setAttribute("verify.pass", String.valueOf(iSuiteVerificationPassTotal));
        suiteNode.setAttribute("verify.fail", String.valueOf(iSuiteVerificationFailTotal));
        suiteNode.setAttribute("user", System.getenv("USERNAME"));


        // Create Class node
        Element classNode = oResultXML.createElement("class");
        classNode.setAttribute("name", oTestContext.getName());
        classNode.setAttribute("start",xmlDateFormat.format(oTestContext.getStartDate()));
        classNode.setAttribute("end", xmlDateFormat.format(oTestContext.getEndDate()));
        classNode.setAttribute("elapsed", SysTools.getElapsedTimeString(oTestContext.getStartDate(), oTestContext.getEndDate()));
        classNode.setAttribute("status", nbrFailedTests > 0?"Fail":"Pass");
        classNode.setAttribute("pass", String.valueOf(nbrPassedTests));
        classNode.setAttribute("fail", String.valueOf(nbrFailedTests));
        classNode.setAttribute("skip", String.valueOf(nbrSkippedTests));
        suiteNode.appendChild(classNode);

        // Sort result by test name alphabetically
        Collections.sort(lAllTests, new ResultComparator());

        // Create test nodes
        for(ITestResult oResult : lAllTests)
        {
            String testName = "";
            String propString;

            // Create Test node
            Element testNode = oResultXML.createElement("test");

            // If test is keywordTestDriver, then get the testcase name from second parameter
            testName = oResult.getName();

            // Get test properties object and set the attribute
            if (oResult.getParameters().length > 1)
            {
                propString = ((Properties)oResult.getParameters()[1]).toString();
                testNode.setAttribute("parameters", propString.substring(1, propString.length()-1));
            }


            testNode.setAttribute("name", testName);
            //testNode.setAttribute("browser", (String)oResult.getParameters()[0]);

            if (oResult.getStatus() == ITestResult.SUCCESS)
            {
                testNode.setAttribute("status", "Pass");
                testNode.setAttribute("verify.pass", String.valueOf((Integer)oResult.getAttribute("TotalVerifications")));
                testNode.setAttribute("verify.fail", "0");
            }
            else if (oResult.getStatus() == ITestResult.FAILURE)
            {
                int iFailed = oResult.getAttribute("FailedVerifications") == null?0:(Integer)oResult.getAttribute("FailedVerifications");
                int iPassed = (Integer)oResult.getAttribute("TotalVerifications") - iFailed;

                testNode.setAttribute("status", "Fail");
                testNode.setAttribute("verify.pass", String.valueOf(iPassed));
                testNode.setAttribute("verify.fail", String.valueOf(iFailed));

                // Create Error node
                String[] errors = (String[])oResult.getAttribute("Errors");
                if (errors != null)
                    for (String errorText : errors)
                    {
                        Element errorNode = oResultXML.createElement("error");
                        errorNode.setAttribute("testgroupName", oResult.getName());
                        errorNode.setAttribute("error", errorText);
                        testNode.appendChild(errorNode);
                    }
            }
            else if (oResult.getStatus() == ITestResult.SKIP)
            {
                testNode.setAttribute("status", "Skip");

                if (oResult.getAttribute("Errors") != null)
                {
                    testNode.setAttribute("error", "yes");

                    // Create Error node
                    Element errorNode = oResultXML.createElement("error");
                    errorNode.setAttribute("testgroupName", oResult.getName());
                    errorNode.setAttribute("error", (String)oResult.getAttribute("Errors"));
                    testNode.appendChild(errorNode);
                }
            }

            testNode.setAttribute("elapsed", SysTools.getElapsedTimeString(oResult.getEndMillis() - oResult.getStartMillis()));
            classNode.appendChild(testNode);
        }

        // Clear current test list
        lAllTests = new ArrayList<ITestResult>();
        lConfigFailures = new ArrayList<ITestResult>();

        ///////////////////////////////////////
        //  Write summary to log file        //
        ///////////////////////////////////////

        logger.info(" ");
        logger.info("======================================================================================================================");
        logger.info(" ");
        logger.info("  Run Summary:");
        logger.info(" ");
        logger.info("     Suite Name:       {}", oTestContext.getSuite().getName());
        logger.info("     Test Class:       {}", oTestContext.getName().split("_")[0]);
        logger.info("     Test Group:       {}", oTestContext.getIncludedGroups().length == 0?"":oTestContext.getIncludedGroups()[0]);
        logger.info(" ");
        logger.info("     Passed:           {}", nbrPassedTests);
        logger.info("     Failed:           {}", nbrFailedTests);
        logger.info("     Skipped:          {}", nbrSkippedTests);
        logger.info("     Total:            {}", nbrPassedTests + nbrFailedTests + nbrSkippedTests);
        logger.info(" ");
        logger.info("     Start:            {}", oTestContext.getStartDate());
        logger.info("     End:              {}", oTestContext.getEndDate());
        logger.info("     Elapsed:          {}", SysTools.getElapsedTimeString(oTestContext.getStartDate(), oTestContext.getEndDate()));

        Object[] aStrings = new Object[5];

        if (nbrPassedTests != 0)
        {
            logger.info(" ");
            logger.info("  Passed Tests:   {}", nbrPassedTests);
            logger.info(" ");

            for(ITestResult oResult : getPassedTests())
            {
                aStrings[0] = oResult.getAttribute("RemoteNode") == null?"":"[" + oResult.getAttribute("RemoteNode") + "]  ";
                aStrings[1] = oResult.getTestClass().getName();
                aStrings[2] = oResult.getName();
                aStrings[3] = oResult.getParameters();
                aStrings[4] = "Total verifications (" + oResult.getAttribute("TotalVerifications") + ")";

                logger.info("     {}{}.{}{} - {}", aStrings);
            }
        }

        if (nbrFailedTests != 0)
        {
            logger.info(" ");
            logger.info("  Failed Tests:   {}", nbrFailedTests);
            logger.info(" ");

            for(ITestResult oResult : getFailedTests())
            {
                aStrings[0] = oResult.getAttribute("RemoteNode") == null?"":"[" + oResult.getAttribute("RemoteNode") + "]  ";
                aStrings[1] = oResult.getTestClass().getName();
                aStrings[2] = oResult.getName();
                aStrings[3] = oResult.getParameters();
                aStrings[4] = oResult.getAttribute("Cause");

                logger.info("     {}{}.{}{} - {}", aStrings);
            }
        }

        if (nbrSkippedTests != 0)
        {
            logger.info(" ");
            logger.info("  Skipped Tests:  {}", nbrSkippedTests);
            logger.info(" ");

            for(ITestResult oResult : getSkippedTests())
            {
                try
                {
                    aStrings[0] = oResult.getTestClass().getName();
                    aStrings[1] = oResult.getName();
                    aStrings[2] = oResult.getParameters();

                    logger.info("     {}.{}{}", aStrings);
                }
                catch(Exception ex)
                {
                    logger.debug("onFinish():  Exception caught! ", ex);
                }
            }
        }

        if (lConfigFailures.size() != 0)
        {
            logger.info(" ");
            logger.info("  Configuration Failures:  {}", lConfigFailures.size());
            logger.info(" ");

            for(ITestResult oResult : lConfigFailures)
            {
                try
                {
                    aStrings[0] = oResult.getTestClass().getName();
                    aStrings[1] = oResult.getName();
                    aStrings[2] = oResult.getParameters();

                    logger.info("     {}.{}{}", aStrings);
                    logger.info("");
                    logger.info("     Cause:  {}", oResult.getThrowable().getMessage());
                }
                catch(Exception ex)
                {
                    logger.debug("onFinish():  Exception caught! ", ex);
                }
            }
        }

        logger.info(" ");
        logger.info("======================================================================================================================");
        logger.info(" ");

        // Clear all result for this current test
        setSkippedTests(new java.util.ArrayList<ITestResult>());
        setPassedTests(new java.util.ArrayList<ITestResult>());
        setFailedTests(new java.util.ArrayList<ITestResult>());
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
