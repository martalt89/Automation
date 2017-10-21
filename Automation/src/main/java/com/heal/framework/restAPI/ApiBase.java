package com.heal.framework.restAPI;

import com.heal.framework.test.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiBase {

    private RestUtils restUtils = new RestUtils();
    private String env = TestBase.getParameters().get("ENV");
    public static String patientSessionId = null;
    public static String opsSessionId = null;
    protected String baseUrl;
    protected String baseUrlPatient;
    protected String baseUrlOps;
    protected String baseUrlApi;
    public String sPaymentId;
    public static JSONObject currentStatus = null;
    public static Map<String, String> authCookiesPatient = null;
    public static String patientUsername;
    public static String patientPassword;
    public static String opstUsername;
    public static String opstPassword;

    public String getSessionId() {
        return patientSessionId;
    }

    public ApiBase(){
        if (env.equalsIgnoreCase("qa")){
            baseUrl = ".qa.heal.com";
            baseUrlApi = "https://apiv3" + baseUrl;
            baseUrlOps = "https://ops" + baseUrl;
        }else if (env.equalsIgnoreCase("dev1")){
            baseUrl = ".dev1.heal.com";
            baseUrlApi = "https://api" + baseUrl;
        }else if (env.equalsIgnoreCase("dev")){
            baseUrl = "-dev.heal.com";
            baseUrlApi = "https://api" + baseUrl;
        }
        baseUrlPatient = "https://patient" + baseUrl + "/api";
        baseUrlOps = "https://ops" + baseUrl;
    }

    /**
     * Set the authCookie and patientSessionId variables
     */
    public void setSessionId(String username, String password) {
        this.patientSessionId = getSessionID(username, password);
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
