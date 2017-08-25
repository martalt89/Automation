package com.heal.framework.restAPI;

import com.heal.framework.test.TestData;
import io.restassured.RestAssured;
import io.restassured.response.Response;

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
        jsonAsMap.put("patientId", patientAPI.getPatientIdByEmail(sAccUsername));
        jsonAsMap.put("serviceCode", "SICK_ADULT");
        jsonAsMap.put("timeSlotId", "0001501121465155-784f435902cb-11052"); // todo: list all available time slots, put them in test data
        jsonAsMap.put("promoCode", null);
        jsonAsMap.put("paymentId", "0001501850382645-2f663b05b4c-0001"); // todo: find out where to extract paymentId
        jsonAsMap.put("addressLongitude", "-122.39483660000002");
        jsonAsMap.put("addressLatitude", "37.7938462");
        jsonAsMap.put("addressId", "0001502109172052-2f663b05b4c-0001");
        jsonAsMap.put("establishment", "");
        jsonAsMap.put("address", accountTestData.sAddress);
        jsonAsMap.put("city", "San Francisco");
        jsonAsMap.put("unit", "2");
        jsonAsMap.put("country", "United States");
        jsonAsMap.put("zipcode", "94105");
        jsonAsMap.put("instructions", "");
        jsonAsMap.put("addressType", "HOME");
        jsonAsMap.put("latitude", 37.7938462);
        jsonAsMap.put("longitude", -122.39483660000002);
        jsonAsMap.put("defaultAddress", false);
        return jsonAsMap;
    }

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

}
