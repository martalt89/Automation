package framework.restAPI;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class PatientAPI {

    private String username = "mihaix10@heal.com";
    private String password = "Heal4325";
    private String baseURL = "https://patient.qa.heal.com/api";

    public String createPatient(){
        String resource = "/v2/patients";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("emailRegex", new JsonObject());
        jsonAsMap.put("hasMedicareMedicaid", "false");
        jsonAsMap.put("firstName", "Mihai");
        jsonAsMap.put("lastName", "Auto");
        jsonAsMap.put("email", username);
        jsonAsMap.put("phone", "+12015555555");
        jsonAsMap.put("dateOfBirth", "09/09/1989");
        jsonAsMap.put("relationshipId", "0001433013870063-d279fc27ffff816b-0014");
        jsonAsMap.put("genderId", "0001433013870063-d279fc27ffff816b-0001");
        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(username,password)
                .contentType("application/json")
                .body(jsonAsMap)
                .post(baseURL+resource);
        return response.asString();
    }

    public String getPatientDetails(){
        String uID = "0001501749997665-2f663b05b4c-0001";
        String resource = "/v2/patients/";
        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(username,password)
                .get(baseURL+resource+uID);
        return response.asString();

    }


}
