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
    public static String opsUsername;
    public static String opsPassword;

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

    /**
     * login parameters to be send on login call
     * @return (Map) user and password credentials
     */
    private Map loginPostParamsPatient() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", patientUsername);
        map.put("password", patientPassword);
        return map;
    }

    /**
     * Set the authCookie and sSessionID variables
     */
    public void setAuthCookiesPatient(){
        if (authCookiesPatient==null){
            String resourceLogin = "/login";
            Response response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .body(loginPostParamsPatient())
                    .post(baseUrlApi + resourceLogin);
            try {
                if  (restUtils.getJsonValue(response.asString(), "status").equalsIgnoreCase("OK")){
                    authCookiesPatient = response.getCookies();
                    patientSessionId = authCookiesPatient.get("SESSION");
                } else {
                    throw new IllegalArgumentException("Unable to authenticate. Reason: " + restUtils.getJsonValue(response.asString(),"description") + " Status code: " + restUtils.getJsonValue(response.asString(),"status"));
                }
            } catch (JSONException e) {
                throw e;
            }
        }
    }

    /**
     * makes a call to current_status endpoint which will return all of the account information needed for further action. It will set the 'currentStatus' in ApiBase if it is null.
     */
    public void setCurrentStatus(){
        if (currentStatus==null){
            String resource = "/v3/patient/current_status";
            String response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .cookies(authCookiesPatient)
                    .get(baseUrlPatient + resource)
                    .asString();
            currentStatus = new JSONObject(response);
            if(restUtils.getJsonValue(response,"status").equalsIgnoreCase("OK")) {
                currentStatus = new JSONObject(response);
            } else {
                throw new  IllegalArgumentException("Unable to get account info. Reason: " + restUtils.getJsonValue(response,"description") + " Status code: " + restUtils.getJsonValue(response,"status"));
            }
        }
    }

    public void setOpsSessionId(){
        if (opsSessionId==null) {
            opsSessionId = RestAssured.given()
                    .auth()
                    .preemptive()
                    .basic(opsUsername, opsPassword)
                    .contentType("application/x-www-form-urlencoded")
                    .get(baseUrlApi + "/security_check2")
                    .cookie("SESSION");
        }
    }


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
