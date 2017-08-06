package framework.restAPI;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * REST calls on /patients API
 * Add patient, return patient info
 */
public class PatientAPI {

    private RestUtils restUtils = new RestUtils();
    private String baseURL = "https://patient.qa.heal.com/api";

    //patient info
    public String sPatientFirstname;
    public String sPatientLastname;
    public String sPatientEmail;
    public String sPatientPhone;
    public String sPatientDateOfBirth;
    public String sPatientRelationship;

    private String sAccUsername;
    private String sAccPassword;
    private String sPatientId;

    /**
     * Constructor - Initializes patient info variables
     * @param sAccUsername (String) Account email
     * @param sAccPassword (String) Account password
     * @param sPatientId (String) Patient id
     */
    public PatientAPI(String sAccUsername, String sAccPassword, String sPatientId){
        this.sAccUsername = sAccUsername;
        this.sAccPassword = sAccPassword;
        this.sPatientId = sPatientId;
        initPatientInfo();
    }

    /**
     * Constructor
     * @param sAccUsername (String) Account email
     * @param sAccPassword (String) Account password
     */
    public PatientAPI(String sAccUsername, String sAccPassword){
        this.sAccUsername = sAccUsername;
        this.sAccPassword = sAccPassword;
    }

    public String addPatient(){
        //TODO: Refactor/Improve
        String resource = "/v2/patients";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("emailRegex", new JsonObject());
        jsonAsMap.put("hasMedicareMedicaid", "false");
        jsonAsMap.put("firstName", "Mihai");
        jsonAsMap.put("lastName", "Auto");
        jsonAsMap.put("email", "patientx10@heal.com");
        jsonAsMap.put("phone", "+12015555555");
        jsonAsMap.put("dateOfBirth", "09/09/1989");
        jsonAsMap.put("relationshipId", "0001433013870063-d279fc27ffff816b-0014");
        jsonAsMap.put("genderId", "0001433013870063-d279fc27ffff816b-0001");
        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .contentType("application/json")
                .body(jsonAsMap)
                .post(baseURL+resource);
        return response.asString();
    }

    /**
     * Collects useful patient info from the GET /patient/id request
     * {firstname, lastname, email, phone, relationship, date of birth}
     * @return (Map) - Patient info
     */
    private Map collectPatientInfo(){
        Map<String,String> patientInfoMap = new HashMap<>();
        String resource = "/v2/patients/";
        Response getResponse = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .get(baseURL+resource+ sPatientId);
        String response = getResponse.asString();
        patientInfoMap.put("firstName", restUtils.getJsonValue(response, "firstName"));
        patientInfoMap.put("lastName", restUtils.getJsonValue(response, "lastName"));
        patientInfoMap.put("email", restUtils.getJsonValue(response, "email"));
        patientInfoMap.put("phone", restUtils.getJsonValue(response, "phone"));
        patientInfoMap.put("relationship", restUtils.getJsonValue(response, "relationship"));
        patientInfoMap.put("dateOfBirth", restUtils.getJsonValue(response, "dateOfBirth"));
        return patientInfoMap;
    }

    /**
     * Initializes patient info variables
     */
    private void initPatientInfo(){
        Map patient = collectPatientInfo();
        sPatientFirstname = (String) patient.get("firstName");
        sPatientLastname = (String) patient.get("lastName");
        sPatientEmail = (String) patient.get("email");
        sPatientPhone = (String) patient.get("phone");
        sPatientRelationship = (String) patient.get("relationship");
        sPatientDateOfBirth = (String) patient.get("dateOfBirth");
    }
}