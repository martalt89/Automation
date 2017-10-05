package com.heal.framework.restAPI;

import com.heal.framework.test.TestData;
import com.heal.framework.web.WebBase;
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
    private String baseURL = "https://patient" + baseUrl + "/api";
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
        this.sAccUsername = sAccUsername;
        this.sAccPassword = sAccPassword;

    }

    public void setPatientId(String sPatientId){
        this.sPatientId = sPatientId;
    }

    private Map createVisitPostParams(){
        AccountAPI accountAPI = new AccountAPI(this.sAccUsername, this.sAccPassword);
        Map<String, Object> jsonAsMap = new HashMap<>();
        String sTimeSlotID = getTimeSlotID();
        jsonAsMap.put("patientId", this.sPatientId);
        jsonAsMap.put("serviceCode", "SICK_ADULT");
        jsonAsMap.put("timeSlotId", sTimeSlotID);
        jsonAsMap.put("symptoms", "IGNORE - Booked by automation test..");
        jsonAsMap.put("promoCode", null);
        jsonAsMap.put("paymentId", "0001501850382645-2f663b05b4c-0001"); // todo: find out where to extract paymentId
        jsonAsMap.put("addressLongitude", accountAPI.getAddressLongitude(accountTestData.sAddress));
        jsonAsMap.put("addressLatitude", accountAPI.getAddressLatitude(accountTestData.sAddress));
        jsonAsMap.put("addressId", accountAPI.getAddressId(accountTestData.sAddress));
        jsonAsMap.put("establishment", "");
        jsonAsMap.put("address", accountTestData.sAddress);
        jsonAsMap.put("city", accountTestData.sCity);
        jsonAsMap.put("unit", accountTestData.sUnit);
        jsonAsMap.put("country", accountTestData.sCountry);
        jsonAsMap.put("zipcode", accountTestData.sZipCode);
        jsonAsMap.put("instructions", accountTestData.sInstruction);
        jsonAsMap.put("addressType", accountTestData.sAddressType);
        jsonAsMap.put("latitude", accountAPI.getAddressLatitude(accountTestData.sAddress));
        jsonAsMap.put("longitude", accountAPI.getAddressLongitude(accountTestData.sAddress));
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
                .get(baseURL + "/patient/visit/timeSlots");

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
        String sessionId = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .get(baseURL + "/v2/patients")
                .cookie("SESSION");


        String response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .contentType("application/json")
                .cookie("SESSION", sessionId)
                .body(createVisitPostParams())
                .post(baseURL + resourceAPI)
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
        String sessionId = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .get(baseURL + "/v2/patients")
                .cookie("SESSION");

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .contentType("application/json")
                .cookie("SESSION", sessionId)
                .body(createVisitPostParams())
                .post(baseURL + resourceAPI);
        return restUtils.getJsonValue(response.asString(),"visitCode");
    }

    public String cancelVisit(String visitCode){

        Map<String, String> cancelParam = new HashMap<>();
        cancelParam.put("reasonId", "24");
        cancelParam.put("note", "test");

        String resourceAPI = "/patient/visit/"+visitCode+"/cancel";
        String sessionId = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .get(baseURL + "/v2/patients")
                .cookie("SESSION");

//        Response response = RestAssured.given()
//                .auth()
//                .preemptive()
//                .basic(sAccUsername, sAccPassword)
//                .contentType("application/json")
//                .cookie("SESSION", sessionId)
//                .body(createVisitPostParams())
//                .post(baseURL + resourceAPI);
//        return restUtils.getJsonValue(response.asString(),"visitCode");

        String response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .contentType("application/json")
                .cookie("SESSION", sessionId)
                .body(cancelParam)
                .post(baseURL + resourceAPI)
                .asString();
        System.out.println(response);
        return response;
    }



}

