package com.heal.framework.restAPI;

import com.heal.framework.test.RunTestSuite;
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
//    private String baseURL = "https://patient.qa.heal.com/api";
private String baseUrlPatient = "https://patient" + baseUrl + "/api";
    private TestData accountTestData = new TestData(TestData.ACCOUNT_SHEET);
    private RestUtils restUtils = new RestUtils();

    private String sAccUsername;
    private String sAccPassword;
    private String sPatientId; //this can be set by using getPatientIdByEmail or getPatientIdByFnLn from PatientAPI
    /**
     * Constructor
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
     */
    public VisitsAPI(String sAccUsername, String sAccPassword){
        patientUsername = sAccUsername;
        patientPassword = sAccPassword;
        setAuthCookies();
        setCurrentStatus();
    }

    public void setPatientId(String sPatientId){
        this.sPatientId = sPatientId;
    }




    private Map createVisitPostParams(){
        AccountAPI accountAPI = new AccountAPI(patientUsername, patientPassword);
        double longitude = accountAPI.getAddressLongitude(accountTestData.sAddress);
        double latitude = accountAPI.getAddressLatitude(accountTestData.sAddress);
        Map<String, Object> jsonAsMap = new HashMap<>();
        String sTimeSlotID = getTimeSlotID();
        jsonAsMap.put("patientId", this.sPatientId);
        jsonAsMap.put("serviceCode", "SICK_ADULT");
        jsonAsMap.put("timeSlotId", sTimeSlotID);
        jsonAsMap.put("symptoms", "IGNORE - Booked by automation test..");
        jsonAsMap.put("promoCode", null);
        jsonAsMap.put("paymentId", getPayemntId());
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
     * Get the paymentid of default payment method for the account
     */
    public String getPayemntId(){
        return restUtils.getJsonValue(currentStatus.getJSONObject("account").getJSONArray("cards").getJSONObject(0).toString(), "paymentId");
    }

    /**
     * Get "available" time slot id
     * @return - String id
     */
    public String getTimeSlotID() {

        PatientAPI patientAPI = new PatientAPI(patientUsername, patientPassword);
        Map<String, String> params = new HashMap<>();
//        params.put("latitude", String.valueOf(accountAPI.getAddressLatitude(accountTestData.sAddress)));
        params.put("latitude", "34.3040026");
//        params.put("longitude", String.valueOf(accountAPI.getAddressLongitude(accountTestData.sAddress)));
        params.put("longitude", "-118.5003491");
        params.put("patientId", patientAPI.getPatientIdByEmail(patientUsername));
        params.put("serviceCode", "SICK_ADULT");
        params.put("zipcode", accountTestData.sZipCode);
        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(patientUsername, patientPassword)
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
                .basic(patientUsername, patientPassword)
                .contentType("application/json")
                .cookie("SESSION", patientSessionId)
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
                .basic(patientUsername, patientPassword)
                .contentType("application/json")
                .cookie("SESSION", patientSessionId)
                .body(createVisitPostParams())
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
                .basic(patientUsername, patientPassword)
                .contentType("application/json")
                .cookie("SESSION", patientSessionId)
                .body(cancelParam)
                .post(baseUrlPatient + resourceAPI)
                .asString();
        System.out.println(response);
        return response;
    }

    /**
     * login parameters to be send on login call
     * @return (Map) user and password credentials
     */
    private Map loginPostParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", patientUsername);
        map.put("password", patientPassword);
        return map;
    }

    /**
     * Set the authCookie and sSessionID variables
     */
    public void setAuthCookies(){
        if (authCookiesPatient==null){
            String resourceLogin = "/login";
            Response response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .body(loginPostParams())
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
            if(restUtils.getJsonValue(response,"status").equalsIgnoreCase("OK")) {
                currentStatus = new JSONObject(response);
            } else {
                throw new  IllegalArgumentException("Unable to get account info. Reason: " + restUtils.getJsonValue(response,"description") + " Status code: " + restUtils.getJsonValue(response,"status"));
            }
        }
    }
}

