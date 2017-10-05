package com.heal.framework.restAPI;

import com.heal.framework.test.RunTestSuite;
import com.heal.framework.test.TestBase;
import com.heal.framework.web.WebBase;

public class ApiBase {
//    public String baseUrl = RunTestSuite.getExcelParams().get("baseUrl");
//    public String baseUrl = ".qa.heal.com";
    public String baseUrl = RunTestSuite.getExcelParams().get("baseUrl").toString();

}
