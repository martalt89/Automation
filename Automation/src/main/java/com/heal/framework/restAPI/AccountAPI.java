package com.heal.framework.restAPI;

import com.google.gson.JsonObject;
import com.heal.framework.test.TestData;
import com.heal.framework.web.WebBase;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Token;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


/**
 * REST calls on /account API
 * Create & get account, add address, add payment methods
 */

public class AccountAPI extends ApiBase {

    private TestData addCardInputData = new TestData(TestData.CARD_SHEET);
    private TestData addAccountInputData = new TestData(TestData.ACCOUNT_SHEET);
    private RestUtils restUtils = new RestUtils();
    private String baseURL = "https://patient" + baseUrl + "/api";
//    private String baseURL = "https://patient.qa.heal.com/api";
    private static final String APIkey = "pk_test_DIamIvBnKXe5LdP5fl1iR0v8";

    /**
     *  Account info variables
     *  initialized using initAccountInfo() method
     */

    public String sUsername = "";
    public String sPassword = "";
    private String sUserId;
    
    /**
     * Constructor
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
     * @param bCreateAccount (Boolean) Account bCreateAccount
     */
    public AccountAPI(String sAccUsername, String sAccPassword, Boolean bCreateAccount){
        this.sUsername = sAccUsername;
        this.sPassword = sAccPassword;
        if(bCreateAccount) createAccount();
        initAccountInfo();
    }

    /**
     * Constructor
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
     */
    public AccountAPI(String sAccUsername, String sAccPassword){
        this.sUsername = sAccUsername;
        this.sPassword = sAccPassword;
    }

    /**
     * Makes a POST request on /account, which creates a new account
     */
    private void createAccount(){
        String resourceAPI = "/v2/account";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("campaign", new JsonObject());
        jsonAsMap.put("firstName", addAccountInputData.sFirstname);
        jsonAsMap.put("lastName", addAccountInputData.sLastname);
        jsonAsMap.put("username", sUsername);
        jsonAsMap.put("password", sPassword);
        jsonAsMap.put("password2", sPassword);
        jsonAsMap.put("mobile", addAccountInputData.sPhoneNumber);
        jsonAsMap.put("zipcode", addAccountInputData.sZipCode);

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .log().all()
                .post(baseURL + resourceAPI);
        response.prettyPrint();
    }

    /**
     * Get user's data in JSON format
     * @return (Map) account resource info
     */
    public Map getAccountInfo(){
        String resourceAPI = "/v2/account";
        Map<String,String> accountInfoMap = new HashMap<>();
        Response getResponse = RestAssured.given()
                .auth()
                .preemptive()
                .basic(this.sUsername, this.sPassword)
                .log().all()
                .get(baseURL + resourceAPI);
        getResponse.prettyPrint();
        String response = getResponse.asString();

        accountInfoMap.put("id", restUtils.getJsonValue(response, "id"));
        accountInfoMap.put("status", restUtils.getJsonValue(response, "status"));
        return accountInfoMap;
    }

    /**
     * Makes a POST request on /account/address, which adds address to user account
     */
    public void addAddress(){
        String resourceAPI = "/account/address";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("address", addAccountInputData.sAddress);
        jsonAsMap.put("addressType", addAccountInputData.sAddressType);
        jsonAsMap.put("city", addAccountInputData.sCity);
        jsonAsMap.put("country", addAccountInputData.sCountry);
        jsonAsMap.put("zipcode", addAccountInputData.sZipCode);
        jsonAsMap.put("establishment", addAccountInputData.sEstablishment);
        jsonAsMap.put("unit", addAccountInputData.sUnit);
        jsonAsMap.put("instruction", addAccountInputData.sInstruction);

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sUsername, sPassword)
                .contentType("application/json")
                .body(jsonAsMap)
                .log().all()
                .put(baseURL + resourceAPI);
        response.prettyPrint();
    }

    /**
     * Makes a POST request to Stripe API, which generates a token in order to be used for adding card
     * @return (String) stripe token
     */
    private String createStripeToken() {
        Stripe.apiKey = APIkey;
        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", addCardInputData.sCardNumber);
        cardParams.put("exp_month", addCardInputData.iExpiryMonth);
        cardParams.put("exp_year", addCardInputData.iExpiryYear);
        cardParams.put("cvc", addCardInputData.sCVC);
        tokenParams.put("card", cardParams);
        try {
            return Token.create(tokenParams).getId();
        } catch (AuthenticationException | APIException | InvalidRequestException | APIConnectionException | CardException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Makes a POST request on /account/payment API, which creates card details. Uses stripe token
     */
    public void createPayment(){
        String resourceAPI = "/account/payment";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("cardExpiryMonth", addCardInputData.iExpiryMonth);
        jsonAsMap.put("cardExpiryYear", addCardInputData.iExpiryYear);
        jsonAsMap.put("cardLast", "4242");
        jsonAsMap.put("cardType", addCardInputData.sCardType);
        jsonAsMap.put("token", createStripeToken());

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sUsername, sPassword)
                .contentType("application/json")
                .body(jsonAsMap)
                .log().all()
                .put(baseURL + resourceAPI);
        response.prettyPrint();
    }

    /**
     * Makes a GET request on /account
     * @return (String) GET response
     */
    private String accountGetRequest(){
        String resource = "/v2/account/";
        Response getResponse = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sUsername, sPassword)
                .get(baseURL +resource);
        return getResponse.asString();
    }

    /**
     * Gets patients number
     * @return (Integer) Patients number
     */
    public Integer getPatientsNumber() {
        String response = accountGetRequest();
        System.out.println("this is the response" + response);
        JSONObject obj = new JSONObject(response);
        return obj.getJSONArray("patients").length()-1;
    }

    public double getAddressLatitude(String sAddress){
        double latitudeAddress = 0;
        String response = accountGetRequest();
        JSONObject obj = new JSONObject(response);
        JSONArray addresses = obj.getJSONArray("addresses");
        for (int i = 0; i < addresses.length(); i++) {
            JSONObject address = addresses.getJSONObject(i);
            if (address.get("address").toString().equalsIgnoreCase(sAddress)) {
                latitudeAddress = address.getDouble("latitudeAddress");
                if (latitudeAddress!=0.0){
                    break;
                }
            }
        }
        return latitudeAddress;
    }

    public double getAddressLongitude(String sAddress){
        double longitudeAddress = 0;
        String response = accountGetRequest();
        JSONObject obj = new JSONObject(response);
        JSONArray addresses = obj.getJSONArray("addresses");
        for (int i = 0; i < addresses.length(); i++) {
            JSONObject address = addresses.getJSONObject(i);
            if (address.get("address").toString().equalsIgnoreCase(sAddress)) {
                longitudeAddress = address.getDouble("longitudeAddress");
                if (longitudeAddress!=0.0){
                    break;
                }
            }
        }
        return longitudeAddress;
    }

    public String getAddressZipcode(String sAddress){
        String zipcode = null;
        String response = accountGetRequest();
        JSONObject obj = new JSONObject(response);
        JSONArray addresses = obj.getJSONArray("addresses");
        for (int i = 0; i < addresses.length(); i++) {
            JSONObject address = addresses.getJSONObject(i);
            if (address.get("address").toString().equals(sAddress)) {
                zipcode = address.getString("zipcode");
            }
        }
        return zipcode;
    }

    public String getAddressCity(String sAddress){
        String city = null;
        String response = accountGetRequest();
        JSONObject obj = new JSONObject(response);
        JSONArray addresses = obj.getJSONArray("addresses");
        for (int i = 0; i < addresses.length(); i++) {
            JSONObject address = addresses.getJSONObject(i);
            if (address.get("address").toString().equals(sAddress)) {
                city = address.getString("city");
            }
        }
        return city;
    }

    public String getAddressId(String sAddress){
        String id = null;
        String response = accountGetRequest();
        JSONObject obj = new JSONObject(response);
        JSONArray addresses = obj.getJSONArray("addresses");
        for (int i = 0; i < addresses.length(); i++) {
            JSONObject address = addresses.getJSONObject(i);
            if (address.get("address").toString().equals(sAddress)) {
                id = address.getString("id");
            }
        }
        return id;
    }

    public String getAddressType(String sAddress){
        String addressType = null;
        String response = accountGetRequest();
        JSONObject obj = new JSONObject(response);
        JSONArray addresses = obj.getJSONArray("addresses");
        for (int i = 0; i < addresses.length(); i++) {
            JSONObject address = addresses.getJSONObject(i);
            if (address.get("address").toString().equals(sAddress)) {
                addressType = address.getString("addressType");
            }
        }
        return addressType;
    }

    /**
     * Initializes account info variables
     */
    private void initAccountInfo(){
        //init account info below
        Map account = getAccountInfo();
        sUserId = (String) account.get("id");
    }

}
