package framework.restAPI;

import com.google.gson.JsonObject;
import foundation.SysTools;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class AccountAPI {
    private String username = "qa_auto_test" + SysTools.getTimestamp("yyyy_MM_dd_HH-mm-ss") + "@heal.com";
    private String password = "Heal4325";
    private String baseURI = "https://patient.qa.heal.com/api";

    public void createAccount(){
        String resourceAPI = "/v2/account";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("campaign", new JsonObject());
        jsonAsMap.put("firstName", "mihai");
        jsonAsMap.put("lastName", "auto");
        jsonAsMap.put("username", username);
        jsonAsMap.put("password", password);
        jsonAsMap.put("password2", "Heal4325");
        jsonAsMap.put("mobile", "+12015555555");
        jsonAsMap.put("zipcode", "5840");

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap) //will converts Map to JSON format
                .log().all()
                .post(baseURI + resourceAPI);
        response.prettyPrint();
    }

    public void findPatientsByUsername(){
        String resourceAPI = "/v2/account";
        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(username, password)
                .param("username", username)
                .log().all()
                .get(baseURI + resourceAPI);
        response.prettyPrint();
    }

    public void addAddress(){
        String resourceAPI = "/account/address";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("address", "1123 Bulevard");
        jsonAsMap.put("addressType", "HOME");
        jsonAsMap.put("city", "San Francisco");
        jsonAsMap.put("country", "United States");
        jsonAsMap.put("zipcode", "90525");
        jsonAsMap.put("establishment", "something");
        jsonAsMap.put("unit", "2");
        jsonAsMap.put("instruction", "none");

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(username, password)
                .contentType("application/json")
                .body(jsonAsMap)
                .log().all()
                .put(baseURI + resourceAPI);
        response.prettyPrint();
    }

    public void createPayment(){
        String resourceAPI = "/account/payment";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("cardExpiryMonth", 12);
        jsonAsMap.put("cardExpiryYear", 2022);
        jsonAsMap.put("cardLast", "4242");
        jsonAsMap.put("cardType", "Visa");
        jsonAsMap.put("token", "tok_1AmkxuCBDgZDxrPNfpkwsTMg");

        // This does not work yet, I have to do one call
        // on https://api.stripe.com/v1/tokens to generate the token than I will use it
        // for creating payment on https://patient.qa.heal.com/api/account/payment


        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(username, password)
                .contentType("application/json")
                .body(jsonAsMap)
                .log().all()
                .put(baseURI + resourceAPI);
        response.prettyPrint();
    }


    public static void main(String args[]){
        //for temporary testing purposes
        AccountAPI api = new AccountAPI();
        api.createAccount();
        //api.findPatientsByUsername();
        //api.addAddress();
        //api.createPayment();
    }
}
