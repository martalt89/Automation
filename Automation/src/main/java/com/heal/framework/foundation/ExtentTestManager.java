package com.heal.framework.foundation;


import com.heal.framework.test.RunTestSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by vahanmelikyan on 9/11/2017.
 */
public class ExtentTestManager {
    private static Logger logger = LoggerFactory.getLogger(ExtentManager.class);
    static Map<Integer, ExtentTest> extentTestMap = new HashMap();
    private static ExtentReports extent = ExtentManager.getReporter();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.endTest(extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        test.assignCategory("Browser: " + RunTestSuite.getExcelParams().get("browserName"), "Env: " + RunTestSuite.getExcelParams().get("ENV"));
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

        return test;
    }

    public synchronized static void removeTest(String testName){

        Field testListField;

        try{
            testListField = extent.getClass().getSuperclass().getDeclaredField("testList");
            testListField.setAccessible(true);
            ArrayList<ExtentTest> testList = (ArrayList<ExtentTest>)testListField.get(extent);
            testList.removeIf(new Predicate<ExtentTest>() {
                @Override
                public boolean test(ExtentTest test) {
                    if(test.getTest().getName().equals(testName)){
                        return true;
                    }
                    return false;
                }
            });
//            testList.forEach(test -> {
//                if(test.getTest().getName().equals(testName)){
//                    testList.remove(test);
//                }
//            });
        }
        catch (NoSuchFieldException|IllegalAccessException e){
            logger.error(e.getMessage());
        }
    }

    public synchronized static ExtentTest startNewTest(String testName){
        removeTest(testName);
        return extent.startTest(testName);
    }

}
