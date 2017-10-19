package com.heal.framework.restAPI;

import com.heal.framework.test.TestData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 *  Created by adrian.rosu on 07/08/2017.
 */
public class VisitsAPI extends ApiBase {
//    private String baseUrlPatient = "https://patient.qa.heal.com/api";

//    private String baseUrlPatient = "https://patient" + baseUrl + "/api";
    private String baseURLAPIv3 = "http://apiv3"+ baseUrl;
    private String baseUrlPatient = "https://patient" + baseUrl + "/api";
    private TestData accountTestData = new TestData(TestData.ACCOUNT_SHEET);
    private RestUtils restUtils = new RestUtils();
    private static Map<String, String> authCookies;


    private String sAccUsername;
    private String sAccPassword;
    private String sPatientId; //this can be set by using getPatientIdByEmail or getPatientIdByFnLn from PatientAPI
    private String sSessionId;
    public JSONObject currentStatus;


    /**
     * Constructor
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
     */
    public VisitsAPI(String sAccUsername, String sAccPassword){
        this.sAccUsername = sAccUsername;
        this.sAccPassword = sAccPassword;
        setAuthCookies();
        setCurrentStatus();
    }

    public void setPatientId(String sPatientId){
        this.sPatientId = sPatientId;
    }

    public void setCurrentStatus(){
        String resource = "/v3/patient/current_status";
        String response = RestAssured.given()
                .header("Content-Type", "application/json")
                .cookies(authCookies)
                .get(baseUrlPatient + resource)
                .asString();
        this.currentStatus = new JSONObject(response);
        if(restUtils.getJsonValue(response,"status").equalsIgnoreCase("OK")) {
            this.currentStatus = new JSONObject(response);
        } else {
            throw new  IllegalArgumentException("Unable to get account info. Reason: " + restUtils.getJsonValue(response,"description") + " Status code: " + restUtils.getJsonValue(response,"status"));
        }
    }

    public void setAuthCookies(){
        String resourceLogin = "/login";
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(loginPostParams())
                .post(baseUrlApi + resourceLogin);
        try {
            if  (restUtils.getJsonValue(response.asString(), "status").equalsIgnoreCase("OK")){
                this.authCookies = response.getCookies();
                this.sSessionId = authCookies.get("SESSION");
            } else {
                throw new IllegalArgumentException("Unable to authenticate. Reason: " + restUtils.getJsonValue(response.asString(),"description") + " Status code: " + restUtils.getJsonValue(response.asString(),"status"));
            }
        } catch (JSONException e) {
            throw e;
        }
    }

    /**
     * JSON parameters to be send on login call
     * @return (Map) user and password credentials
     */
    private Map loginPostParams() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("username", sAccUsername);
        jsonAsMap.put("password", sAccPassword);
        return jsonAsMap;
    }

    private Map createVisitPostParams() {
        String mainPatientOfAccount = null;
        JSONArray patients = this.currentStatus.getJSONObject("account").getJSONArray("patients");
        for (int i = 0; i < patients.length(); i++) {
            JSONObject patient = patients.getJSONObject(i);
            if (patient.getBoolean("mainPatientOfAccount")) {
                mainPatientOfAccount = patient.get("patientId").toString();
                break;
            }
        }
        return createVisitPostParams(mainPatientOfAccount);
    }

    private Map createVisitPostParams(String sPatientId){
        AccountAPI accountAPI = new AccountAPI(this.sAccUsername, this.sAccPassword);
        double longitude = accountAPI.getAddressLongitude(accountTestData.sAddress);
        double latitude = accountAPI.getAddressLatitude(accountTestData.sAddress);
        Map<String, Object> jsonAsMap = new HashMap<>();
        String sTimeSlotID = getTimeSlotID();
        jsonAsMap.put("patientId", sPatientId);
        jsonAsMap.put("serviceCode", "SICK_ADULT");
        jsonAsMap.put("timeSlotId", sTimeSlotID);
        jsonAsMap.put("symptoms", "IGNORE - Booked by automation test..");
        jsonAsMap.put("promoCode", null);
        jsonAsMap.put("paymentId", sPaymentId);
        jsonAsMap.put("addressLongitude", longitude);
        jsonAsMap.put("addressLatitude", latitude);
        jsonAsMap.put("addressId", accountAPI.getAddressId(accountTestData.sAddress));
        jsonAsMap.put("establishment", "");
        jsonAsMap.put("address", accountTestData.sAddress);
        jsonAsMap.put("city", accountTestData.sCity);
        jsonAsMap.put("unit", accountTestData.sUnit);
        jsonAsMap.put("country", accountTestData.sCountry);
        jsonAsMap.put("zipcode", accountTestData.sZipCode);
        jsonAsMap.put("instructions", accountTestData.sInstruction);
        jsonAsMap.put("addressType", accountTestData.sAddressType);
        jsonAsMap.put("latitude", longitude);
        jsonAsMap.put("longitude", latitude);
        jsonAsMap.put("defaultAddress", false);
        return jsonAsMap;
    }

    /**
     * Get "available" time slot id
     * @return - String id
     */
    public String getTimeSlotID() {

        PatientAPI patientAPI = new PatientAPI(sAccUsername, sAccPassword);
        Map<String, String> params = new HashMap<>();
//        params.put("latitude", String.valueOf(accountAPI.getAddressLatitude(accountTestData.sAddress)));
        params.put("latitude", "34.3040026");
//        params.put("longitude", String.valueOf(accountAPI.getAddressLongitude(accountTestData.sAddress)));
        params.put("longitude", "-118.5003491");
        params.put("patientId", patientAPI.getPatientIdByEmail(sAccUsername));
        params.put("serviceCode", "SICK_ADULT");
        params.put("zipcode", accountTestData.sZipCode);
        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .params(params)
//                .get("https://patient.qa.heal.com/api/patient/visit/timeSlots");
                .get(baseUrlPatient + "/patient/visit/timeSlots");

        String strResponse = response.asString();

        JSONObject obj = null;
        try {
            obj = new JSONObject(strResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray arrTimeSlots = obj.getJSONArray("timeSlots");
        int i = 0;
        while (!arrTimeSlots.getJSONObject(i).getBoolean("available")){
            i++;
        }
        return arrTimeSlots.getJSONObject(i).getString("id");
    }

    /**
     * Create visit
     * @return - String visit code
     */
    public String createVisit(){
        String resourceAPI = "/v5/patient/visit";
//        String sessionId = RestAssured.given()
//                .auth()
//                .preemptive()
//                .basic(sAccUsername, sAccPassword)
//                .get(baseUrlPatient + "/v2/patients")
//                .cookie("SESSION");


        String response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .contentType("application/json")
                .cookie("SESSION", sSessionId)
                .body(createVisitPostParams())
                .post(baseUrlPatient + resourceAPI)
                .asString();
        try {
            return restUtils.getJsonValue(response, "visitCode");
        } catch (Exception e){
            System.out.println("Unable to get the visit code from the response.");
//            System.exit(1);
            return "";
        }
    }

    public String createVisit(String sPatientID){
        setPatientId(sPatientID);
        String resourceAPI = "/v4/patient/visit";
//        String sessionId = RestAssured.given()
//                .auth()
//                .preemptive()
//                .basic(sAccUsername, sAccPassword)
//                .get(baseUrlPatient + "/v2/patients")
//                .cookie("SESSION");

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .contentType("application/json")
                .cookie("SESSION", sSessionId)
                .body(createVisitPostParams(sPatientID))
                .post(baseUrlPatient + resourceAPI);
        return restUtils.getJsonValue(response.asString(),"visitCode");
    }

    public String cancelVisit(String visitCode){

        Map<String, String> cancelParam = new HashMap<>();
        cancelParam.put("reasonId", "24");
        cancelParam.put("note", "Canceled by automation test (via API).");

        String resourceAPI = "/patient/visit/"+visitCode+"/cancel";
//        String sessionId = RestAssured.given()
//                .auth()
//                .preemptive()
//                .basic(sAccUsername, sAccPassword)
//                .get(baseUrlPatient + "/v2/patients")
//                .cookie("SESSION");

        String response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .contentType("application/json")
                .cookie("SESSION", sSessionId)
                .body(cancelParam)
                .post(baseUrlPatient + resourceAPI)
                .asString();
        System.out.println(response);
        return response;
    }
}

