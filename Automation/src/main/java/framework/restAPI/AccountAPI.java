package framework.restAPI;

import com.google.gson.JsonObject;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Token;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class AccountAPI {
    private String sUsername = RestUtils.generateUsername();
    private String sPassword = "Heal4325";
    private int iExpiryMonth = 12;
    private int iExpiryYear = 2022;
    private String baseURI = "https://patient.qa.heal.com/api";


    public void createAccount(){
        String resourceAPI = "/v2/account";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("campaign", new JsonObject());
        jsonAsMap.put("firstName", "mihai");
        jsonAsMap.put("lastName", "auto");
        jsonAsMap.put("username", sUsername);
        jsonAsMap.put("password", sPassword);
        jsonAsMap.put("password2", "Heal4325");
        jsonAsMap.put("mobile", "+12015555555");
        jsonAsMap.put("zipcode", "5840");

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .log().all()
                .post(baseURI + resourceAPI);
        response.prettyPrint();
    }

    public String getPatientsByUsername(){
        String resourceAPI = "/v2/account";
        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sUsername, sPassword)
                .param("username", sUsername)
                .log().all()
                .get(baseURI + resourceAPI);
        return response.prettyPrint();
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
                .basic(sUsername, sPassword)
                .contentType("application/json")
                .body(jsonAsMap)
                .log().all()
                .put(baseURI + resourceAPI);
        response.prettyPrint();
    }


    public String createStripeToken() {
        Stripe.apiKey = "pk_test_DIamIvBnKXe5LdP5fl1iR0v8";
        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", "4242424242424242");
        cardParams.put("exp_month", iExpiryMonth);
        cardParams.put("exp_year", iExpiryYear);
        cardParams.put("cvc", "314");
        tokenParams.put("card", cardParams);
        try {
            return Token.create(tokenParams).getId();
        } catch (AuthenticationException | APIException | InvalidRequestException | APIConnectionException | CardException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createPayment(){
        String resourceAPI = "/account/payment";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("cardExpiryMonth", iExpiryMonth);
        jsonAsMap.put("cardExpiryYear", iExpiryYear);
        jsonAsMap.put("cardLast", "4242");
        jsonAsMap.put("cardType", "Visa");
        jsonAsMap.put("token", createStripeToken());

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sUsername, sPassword)
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
        api.getPatientsByUsername();
        api.addAddress();
        api.createPayment();
        api.createPayment();
    }
}
