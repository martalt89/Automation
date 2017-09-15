package com.heal.framework.foundation;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;
import com.relevantcodes.extentreports.ReporterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vahanmelikyan on 9/11/2017.
 */
public class ExtentManager {

    private static Logger logger = LoggerFactory.getLogger(ExtentManager.class);
    private static String reportLocation = "report/ExtentReport.html";
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports(reportLocation, true, NetworkMode.OFFLINE);
            extent.startReporter(ReporterType.DB, reportLocation);
            extent.addSystemInfo("Host Name", "vahanmelikyan");
            extent.addSystemInfo("User Name", "vahan");
        }

        return extent;
    }



}
