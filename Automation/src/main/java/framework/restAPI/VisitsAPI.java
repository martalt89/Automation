package framework.restAPI;

import framework.test.TestData;
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
    public String sAccUsername;
    public String sAccPassword;


    /**
     * Constructor
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
     */
    public VisitsAPI(String sAccUsername, String sAccPassword){
        this.sAccUsername = sAccUsername;
        this.sAccPassword = sAccPassword;
    }

    public void createVisit(){
        String resourceAPI = "/v4/patient/visit";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("patientId", "0001501691831758-2f663b05b4c-0001"); // todo: need patient id from Create patient component
        jsonAsMap.put("serviceCode", "SICK_ADULT");
        jsonAsMap.put("timeSlotId", "0001501121464233-784f435902cb-4572"); // todo: list all available time slots, put them in test data
        jsonAsMap.put("promoCode", null);
        jsonAsMap.put("paymentId", "0001501850382645-2f663b05b4c-0001"); // todo: find out where to extract paymentId
        jsonAsMap.put("addressLongitude", "-122.39483660000002");
        jsonAsMap.put("addressLatitude", "37.7938462");
        jsonAsMap.put("addressId", "0001502109172052-2f663b05b4c-0001");
        jsonAsMap.put("establishment", "");
        jsonAsMap.put("address", "1 Market St"); // todo: put the address on test data - patient sheet
        jsonAsMap.put("city", "San Francisco");
        jsonAsMap.put("unit", "2");
        jsonAsMap.put("country", "United States");
        jsonAsMap.put("zipcode", "94105");
        jsonAsMap.put("instructions", "");
        jsonAsMap.put("addressType", "HOME");
        jsonAsMap.put("latitude", 37.7938462);
        jsonAsMap.put("longitude", -122.39483660000002);
        jsonAsMap.put("defaultAddress", false);

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccUsername)
                .contentType("application/json")
                .body(jsonAsMap)
                .log().all()
                .put(baseURI + resourceAPI);
        response.prettyPrint();
    }

}
