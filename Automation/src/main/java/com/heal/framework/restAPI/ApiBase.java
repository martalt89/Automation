package com.heal.framework.restAPI;

import com.heal.framework.test.RunTestSuite;
import com.heal.framework.test.TestBase;
import io.restassured.RestAssured;

public class ApiBase {


//    private String env = RunTestSuite.getExcelParams().get("ENV");
    private String env = TestBase.getParameters().get("ENV");
    private String sessionId;
    protected String baseUrl;
    protected String baseUrlApi;
    public String sPaymentId;

    public String getSessionId() {
        return sessionId;
    }

    public ApiBase(){
        if (env.equalsIgnoreCase("qa")){
            baseUrl = ".qa.heal.com";
            baseUrlApi = "https://apiv3" + baseUrl;
        }else if (env.equalsIgnoreCase("dev1")){
            baseUrl = ".dev1.heal.com";
            baseUrlApi = "https://api" + baseUrl;
        }else if (env.equalsIgnoreCase("dev")){
            baseUrl = "-dev.heal.com";
        }
    }

    public void setSessionId(String username, String password) {
        this.sessionId = getSessionID(username, password);
    }
//
//    public void setBaseUrl(){
//        if (RunTestSuite.getExcelParams().get("ENV").equalsIgnoreCase("qa")){
//            baseUrl=".qa.heal.com";
//        }else if (RunTestSuite.getExcelParams().get("ENV").equalsIgnoreCase("dev1")){
//            baseUrl = ".dev1.heal.com";
//        }else if (RunTestSuite.getExcelParams().get("ENV").equalsIgnoreCase("dev")){
//            baseUrl = "-dev.heal.com";
//        }
//    }

    public String getSessionID(String username, String password) {

        String sessionId = RestAssured.given()
                .auth()
                .preemptive()
                .basic(username, password)
                .get(baseUrl + "/v2/patients")
                .cookie("SESSION");
        return sessionId;
    }


}
