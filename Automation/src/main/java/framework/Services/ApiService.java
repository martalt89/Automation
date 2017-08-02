package framework.Services;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApiService {
    private String username = "mihaix10@heal.com";
    public void postRegister(){
        String body = "{\"campaign\":{},\"firstName\":\"mihai\",\"lastName\":\"test\",\"username\":\"mihaix8@heal.com\",\"password\":\"Heal4325\",\"password2\":\"Heal4325\",\"mobile\":\"+12015555555\",\"zipcode\":\"2342\"}";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("campaign", new ArrayList<>());
        //need to find out how to build the empty object campaign":{}.It does not work with new ArrayList<>(), but it works for POST /patients. will investigate more tomorrow
        //{zipcode=5840, firstName=mihai, lastName=auto, password=Heal4325, mobile=+12015555555, campaign=[], password2=Heal4325, username=mihaix10@heal.com}
        jsonAsMap.put("firstName", "mihai");
        jsonAsMap.put("lastName", "auto");
        jsonAsMap.put("username", username);
        jsonAsMap.put("password", "Heal4325");
        jsonAsMap.put("password2", "Heal4325");
        jsonAsMap.put("mobile", "+12015555555");
        jsonAsMap.put("zipcode", "5840");

        Response re = RestAssured.given()
                .contentType("application/json")
                    .body(jsonAsMap) //will converts Map to JSON format
                .post("https://patient.qa.heal.com/api/v2/account");
        System.out.println("json" + jsonAsMap);
        System.out.println("POST /account status code: " + re.getStatusCode());
        System.out.println("POST /account response: " + re.body().prettyPrint());
    }

    public void postPatients(){
        String body = "{\"emailRegex\":{},\"hasMedicareMedicaid\":false,\"firstName\":\"Mihai\",\"lastName\":\"Auto\",\"email\":\"mihaix8@heal.com\",\"phone\":\"+12015555555\",\"dateOfBirth\":\"09/09/1989\",\"relationshipId\":\"0001433013870063-d279fc27ffff816b-0014\",\"genderId\":\"0001433013870063-d279fc27ffff816b-0001\"}";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("emailRegex", new ArrayList<>());
        jsonAsMap.put("hasMedicareMedicaid", "false");
        jsonAsMap.put("firstName", "Mihai");
        jsonAsMap.put("lastName", "Auto");
        jsonAsMap.put("email", username);
        jsonAsMap.put("phone", "+12015555555");
        jsonAsMap.put("dateOfBirth", "09/09/1989");
        jsonAsMap.put("relationshipId", "0001433013870063-d279fc27ffff816b-0014");
        jsonAsMap.put("genderId", "0001433013870063-d279fc27ffff816b-0001");
        Response re = RestAssured.given()
                .auth()
                .preemptive()
                .basic(username,"Heal4325")
                .contentType("application/json")
                .body(jsonAsMap) // will converts Map to JSON format
                .post("https://patient.qa.heal.com/api/v2/patients");
        System.out.println("POST /patients status code: " + re.getStatusCode());
        System.out.println("POST /patients response: " + re.body().print());
    }

    public void postLogin() {
        String body = "{\"username\":\"mihaix8@heal.com\",\"password\":\"Heal4325\"}";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("username", username);
        jsonAsMap.put("password", "Heal4325");
        Response re = RestAssured.given()
                .auth()
                .preemptive()
                .basic(username,"Heal4325")
                .contentType("application/json")
                .body(jsonAsMap) // will converts Map to JSON format
                .post("https://patient.qa.heal.com/api/login");

        System.out.println("POST /login status code: " + re.getStatusCode());
        System.out.println("POST /login response: " + re.body().print());
    }

    public static void main(String args[]){
        //testing the existing methods
        ApiService api = new ApiService();
        api.postRegister();
        api.postPatients();
        api.postLogin();
    }


}
