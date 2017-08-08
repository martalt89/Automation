package framework.restAPI;

import com.google.gson.JsonObject;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Token;
import framework.test.TestData;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class AccountAPI {
    private TestData addCardInputData = new TestData(TestData.CARD_SHEET);
    private RestUtils restUtils = new RestUtils();
    public String sUsername;
    public String sPassword;
    private String sUserId;
    private String baseURI = "https://patient.qa.heal.com/api";
    private static final String APIkey = "pk_test_DIamIvBnKXe5LdP5fl1iR0v8";
    /**
     * Constructor
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
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

    private void createAccount(){
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

    private Map getAccountByUsername(){
        String resourceAPI = "/v2/account";
        Map<String,String> accountInfoMap = new HashMap<>();
        Response getResponse = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sUsername, sPassword)
                .param("username", sUsername)
                .log().all()
                .get(baseURI + resourceAPI);
        getResponse.prettyPrint();
        String response = getResponse.asString();

        accountInfoMap.put("id", restUtils.getJsonValue(response, "id"));
        return accountInfoMap;
    }

    public void addAddress(){
        String resourceAPI = "/account/address";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("address", "1 Market St");
        jsonAsMap.put("addressType", "HOME");
        jsonAsMap.put("city", "San Francisco");
        jsonAsMap.put("country", "United States");
        jsonAsMap.put("zipcode", "94105");
        jsonAsMap.put("establishment", "");
        jsonAsMap.put("unit", "2");
        jsonAsMap.put("instruction", "gate code 4235");

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

    public void createPayment(){
        String resourceAPI = "/account/payment";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("cardExpiryMonth", addCardInputData.iExpiryMonth);
        jsonAsMap.put("cardExpiryYear", addCardInputData.iExpiryYear);
        jsonAsMap.put("cardLast", "4242"); // to do
        jsonAsMap.put("cardType", addCardInputData.sCardType);
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

    /**
     * Initializes account info variables
     */
    private void initAccountInfo(){
        //init account info below
        Map account = getAccountByUsername();
        sUserId = (String) account.get("id");


    }
}
