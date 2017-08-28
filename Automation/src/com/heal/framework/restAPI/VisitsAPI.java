package com.heal.framework.restAPI;

import com.heal.framework.test.TestData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 *  Created by adrian.rosu on 07/08/2017.
 */
public class VisitsAPI {
    private String baseURI = "https://patient.qa.heal.com/api";
    private TestData accountTestData = new TestData(TestData.ACCOUNT_SHEET);
    private RestUtils restUtils = new RestUtils();

    private String sAccUsername;
    private String sAccPassword;

    /**
     * Constructor
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
     */
    public VisitsAPI(String sAccUsername, String sAccPassword){
        this.sAccUsername = sAccUsername;
        this.sAccPassword = sAccPassword;

    }

    private Map createVisitPostParams(){
        PatientAPI patientAPI = new PatientAPI(sAccUsername, sAccPassword);
        Map<String, Object> jsonAsMap = new HashMap<>();
        String sTimeSlotID = getTimeSlotID();
        jsonAsMap.put("patientId", patientAPI.getPatientIdByEmail(sAccUsername));
        jsonAsMap.put("serviceCode", "SICK_ADULT");
        jsonAsMap.put("timeSlotId", sTimeSlotID);
        jsonAsMap.put("promoCode", null);
        jsonAsMap.put("paymentId", "0001501850382645-2f663b05b4c-0001"); // todo: find out where to extract paymentId
        jsonAsMap.put("addressLongitude", "-122.39483660000002");
        jsonAsMap.put("addressLatitude", "37.7938462");
        jsonAsMap.put("addressId", "0001502109172052-2f663b05b4c-0001");
        jsonAsMap.put("establishment", "");
        jsonAsMap.put("address", accountTestData.sAddress);
        jsonAsMap.put("city", accountTestData.sCity);
        jsonAsMap.put("unit", accountTestData.sUnit);
        jsonAsMap.put("country", accountTestData.sCountry);
        jsonAsMap.put("zipcode", accountTestData.sZipCode);
        jsonAsMap.put("instructions", accountTestData.sInstruction);
        jsonAsMap.put("addressType", accountTestData.sAddressType);
        jsonAsMap.put("latitude", 37.7938462);
        jsonAsMap.put("longitude", -122.39483660000002);
        jsonAsMap.put("defaultAddress", false);
        return jsonAsMap;
    }

    /**
     * Get "available" time slot id
     * @return - String id
     */
    public String getTimeSlotID() {
        Map<String, String> params = new HashMap<>();
        params.put("latitude", "34.3040026");
        params.put("longitude", "-118.5003491");
        params.put("patientId", "0001503693482854-2f663b05b4c-0001");
        params.put("serviceCode", "SICK_ADULT");
        params.put("zipcode", accountTestData.sZipCode);
        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .params(params)
                .get("https://patient.qa.heal.com/api/patient/visit/timeSlots");

        String strResponse = response.asString();
        JSONObject obj = new JSONObject(strResponse);
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
        String resourceAPI = "/v4/patient/visit";
        String sessionId = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .get("https://patient.qa.heal.com/api/v2/patients")
                .cookie("SESSION");

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .contentType("application/json")
                .cookie("SESSION", sessionId)
                .body(createVisitPostParams())
                .post(baseURI + resourceAPI);
        return restUtils.getJsonValue(response.asString(),"visitCode");
    }
    public String createVisit(String sPatientID){
        String resourceAPI = "/v4/patient/visit";
        String sessionId = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .get("https://patient.qa.heal.com/api/v2/patients")
                .cookie("SESSION");

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .contentType("application/json")
                .cookie("SESSION", sessionId)
                .body(createVisitPostParams())
                .post(baseURI + resourceAPI);
        return restUtils.getJsonValue(response.asString(),"visitCode");
    }

}
