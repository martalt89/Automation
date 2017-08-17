package framework.restAPI;

import com.sun.javaws.exceptions.InvalidArgumentException;
import framework.restAPI.RestUtils;
import framework.test.TestData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * REST calls on /patients API
 * Add patient, return patient info
 */
public class PatientAPI {

    private RestUtils restUtils = new RestUtils();
    private TestData addPatientInputData = new TestData(TestData.PATIENT_SHEET);
    private String baseURL = "https://patient.qa.heal.com/api";

    /**
     * Patient info variables
     * - initialized using initPatientInfo() method
     */
    public String sPatientIdFromInfo;
    private String sPatientFirstname;
    private String sPatientLastname;
    private String sPatientEmail;
    private String sPatientPhone;
    private String sPatientDateOfBirth;
    private String sPatientRelationship;

    //TODO - create enums with the values below
    /**
     * Relationship ids - Used when making POST requests on /patients
     * POST request is sending relationshipId param instead of the relationship string
     */
    private static final String SPOUSE = "0001433013870063-d279fc27ffff816b-0006";
    private static final String PARTNER = "0001433013870063-d279fc27ffff816b-0007";
    private static final String GRANDPARENT = "0001433013870063-d279fc27ffff816b-0011";
    private static final String GRANDCHILD = "0001433013870063-d279fc27ffff816b-0009";
    private static final String CHILD = "0001433013870063-d279fc27ffff816b-0008";
    private static final String PARENT = "0001433013870063-d279fc27ffff816b-0010";
    private static final String SIBLING = "0001433013870063-d279fc27ffff816b-0005";
    private static final String OTHER_RELATIONSHIP = "0001433013870063-d279fc27ffff816b-0014";
    private static final String YOU = "0001433013870063-d279fc27ffff816b-0004";
    private static final String FRIEND = "0001433013870063-d279fc27ffff816b-0013";
    private static final String COWORKER = "0001433013870063-d279fc27ffff816b-0012";
    private static final String ASSISTED_LIVING_RESIDENT = "0001433013870063-d279fc27ffff816b-0015";
    private static final String SKILLED_NURSING_FACILITY = "0001433013870063-d279fc27ffff816b-0016";

    /**
     * Gender ids - Used when making POST requests on /patients
     * POST request is sending genderId param instead of the gender string
     */
    private static final String FEMALE = "0001433013870063-d279fc27ffff816b-0002";
    private static final String MALE = "0001433013870063-d279fc27ffff816b-0001";
    private static final String OTHER_GENDER = "0001433013870063-d279fc27ffff816b-0003";

    //for constructors
    private String sAccUsername;
    private String sAccPassword;
    private String sPatientId;


    /**
     * Constructor - Initializes patient info variables using patient id
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
     * @param sPatientId (String) Patient id
     */
    public PatientAPI(String sAccUsername, String sAccPassword, String sPatientId){
        this.sAccUsername = sAccUsername;
        this.sAccPassword = sAccPassword;
        this.sPatientId = sPatientId;
        initPatientInfo(this.sPatientId);
    }

    /**
     * Constructor
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
     */
    public PatientAPI(String sAccUsername, String sAccPassword){
        this.sAccUsername = sAccUsername;
        this.sAccPassword = sAccPassword;
    }

    /**
     * Constructor - Initializes patient info variables after adding a patient using test data from Excel
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
     * @param bAddPatientFromExcel (Boolean)
     *                    If True:
     *                      - a patient will be added using addPatientFromExcel() method.
     *                      - addPatientFromExcel() returns patientId that will initialize sPatientId
     *                    If False:
     *                      - patient will not be added and another constructor will be invoked
     *
     * @throws InvalidArgumentException if values taken from the Excel file are incorrect/invalid
     */
    public PatientAPI(String sAccUsername, String sAccPassword, Boolean bAddPatientFromExcel) throws InvalidArgumentException {
        this(sAccUsername, sAccPassword);
        if(bAddPatientFromExcel) {
            this.sPatientId = addPatientFromExcel();
            initPatientInfo(this.sPatientId);
        }
    }

    /**
     * Initializes patient info variables using a provided patient id
     * - getPatientAsJson(patientId) returns a JSON string containing patient info
     * @param patientId (String) Patient id
     */
    private void initPatientInfo(String patientId){
        String patientJson = getPatientAsJson(patientId);
        sPatientIdFromInfo = patientId;
        sPatientFirstname = restUtils.getJsonValue(patientJson, "firstName");
        sPatientLastname = restUtils.getJsonValue(patientJson, "lastName");
        sPatientEmail = restUtils.getJsonValue(patientJson, "email");
        sPatientPhone = restUtils.getJsonValue(patientJson, "phone");
        sPatientRelationship = restUtils.getJsonValue(patientJson, "relationship");
        sPatientDateOfBirth = restUtils.getJsonValue(patientJson, "dateOfBirth");
    }

    //getters
    public String getPatientFirstname(){
        return this.sPatientFirstname;
    }

    public String getPatientLastname(){
        return this.sPatientLastname;
    }

    public String getPatientEmail(){
        return this.sPatientEmail;
    }

    public String getPatientRelationship(){
        return this.sPatientRelationship;
    }

    public String getsPatientPhone(){
        return this.sPatientPhone;
    }

    public String getPatientDateOfBirth(){
        return this.sPatientDateOfBirth;
    }


    /**
     * Gets relationshipId(used as param in POST requests on /patients) based on relationship string
     * @param sRelationship (String) Relationship string i.g Spouse (taken from Excel test data file)
     * @return (String) relationshipId
     * @throws InvalidArgumentException if provided argument is incorrect/invalid
     */
    private String getRelationshipId(String sRelationship) throws InvalidArgumentException {
        String sRelationshipId;
        switch (sRelationship){
            case "Spouse":
                sRelationshipId = SPOUSE; break;
            case "Partner":
                sRelationshipId = PARTNER; break;
            case "Grandparent":
                sRelationshipId = GRANDPARENT; break;
            case "Grandchild":
                sRelationshipId = GRANDCHILD; break;
            case "Child":
                sRelationshipId = CHILD; break;
            case "Parent":
                sRelationshipId = PARENT; break;
            case "Sibling":
                sRelationshipId = SIBLING; break;
            case "Other":
                sRelationshipId = OTHER_RELATIONSHIP; break;
            case "You":
                sRelationshipId = YOU; break;
            case "Friend":
                sRelationshipId = FRIEND; break;
            case "Coworker":
                sRelationshipId = COWORKER; break;
            case "Assisted Living Resident":
                sRelationshipId = ASSISTED_LIVING_RESIDENT; break;
            case "Skilled Nursing Facility":
                sRelationshipId = SKILLED_NURSING_FACILITY; break;
            default:
                throw new InvalidArgumentException(new String[]{"Invalid argument for sRelationship: ", sRelationship});
        }
        return sRelationshipId;
    }

    /**
     * Gets genderId(used as param in POST requests on /patients) based on gender string
     * @param sGender (String) Gender string i.g Male (taken from Excel file)
     * @return (String) genderId
     * @throws InvalidArgumentException if provided argument is incorrect/invalid
     */
    private String getGenderId(String sGender) throws InvalidArgumentException {
        String genderId;
        switch (sGender){
            case "Male":
                genderId = MALE; break;
            case "Female":
                genderId = FEMALE; break;
            case "Other":
                genderId = OTHER_GENDER; break;
            default:
                throw new InvalidArgumentException(new String[]{"Invalid argument for sGender: ", sGender });
        }
        return genderId;
    }

    //////////////////
    // POST METHODS //
    //////////////////

    /**
     * Creates a map containing POST params for add patient request, taken from Excel test data file
     * @return (Map) POST params for add patient request
     * @throws InvalidArgumentException if values taken from the Excel file are incorrect/invalid
     */
    private Map addPatientPostParamsFromExcel() throws InvalidArgumentException {
        Map<String, Object> postParams = new HashMap<>();
        postParams.put("emailRegex", "{}");
        postParams.put("bHasMedicareMedicaid", addPatientInputData.bHasMedicareMedicaid);
        postParams.put("firstName", addPatientInputData.sFirstname);
        postParams.put("lastName", addPatientInputData.sLastname);
        postParams.put("email", addPatientInputData.sEmail);
        postParams.put("phone", addPatientInputData.sPhoneNumber);
        postParams.put("dateOfBirth", addPatientInputData.sDateOfBirth);
        postParams.put("relationshipId", getRelationshipId(addPatientInputData.sRelationship));
        postParams.put("genderId", getGenderId(addPatientInputData.sGender));
        return postParams;
    }

    /**
     * Makes a POST request on /patients, which adds a new patient
     * @param postParams (Map) POST request params
     * @return (String) patientId
     */
    private String addPatientPostRequest(Map postParams){
        String resource = "/v2/patients";
        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(this.sAccUsername, this.sAccPassword)
                .contentType("application/json")
                .body(postParams)
                .post(baseURL+resource);
        return restUtils.getJsonValue(response.asString(),"patient","patientId");
    }

    /**
     * Adds a patient using data from Excel test data file
     * @return (String) patientId
     * @throws InvalidArgumentException if values taken from the Excel file are incorrect/invalid
     */
    public String addPatientFromExcel() throws InvalidArgumentException {
        String patientId = addPatientPostRequest(addPatientPostParamsFromExcel());
        initPatientInfo(patientId);
        return patientId;
    }

    /**
     * Adds a patient using data from Excel test data file
     * @param patientPostParams (Map) POST request params
     * @return (String) patientId
     * @throws InvalidArgumentException if values taken from the Excel file are incorrect/invalid
     */
    public String addPatient(Map patientPostParams) throws InvalidArgumentException {
        String patientId =  addPatientPostRequest(patientPostParams);
        return patientId;
    }

    /**
     * Makes a GET request on /account
     * @return (String) GET response
     */

    /**
     * Makes a GET request on /patients
     * @return (String) GET response
     */
    private String patientsGetRequest(){
        String resource = "/v2/patients/";
        Response getResponse = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .get(baseURL+resource);
        return getResponse.asString();
    }


    /////////////////
    // GET METHODS //
    /////////////////

    /**
     * Gets patient info in JSON format from /patients/patientId
     * @param sPatientId (String) Id of the patient
     * @return (String) Patient info as JSON string
     */
    private String getPatientAsJson(String sPatientId){
        String resource = "/v2/patients/";
        Response getResponse = RestAssured.given()
                .auth()
                .preemptive()
                .basic(this.sAccUsername, this.sAccPassword)
                .get(baseURL+resource+ sPatientId);
        return getResponse.asString();
    }

    /**
     * Gets all patients info in JSON string format from /patients
     * @return (String) All patients info as JSON String
     */
    private String getPatientsAsJson(){
        String resource = "/v2/patients/";
        Response getResponse = RestAssured.given()
                .auth()
                .preemptive()
                .basic(this.sAccUsername, this.sAccPassword)
                .get(baseURL+resource);
        return getResponse.asString();
    }

    /**(Work in progress)
     * Gets patientId by providing patient firstname and lastname
     * @param sFn (String) Patient firstname
     * @param sLn (String) Patient lastname
     * @return (String) patientId
     */
    public String getPatientIdByFnLn(String sFn, String sLn) {
        //TODO - WIP
        String resource = "/v2/patients/";
        Response getResponse = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .get(baseURL+resource);
        String response = getResponse.asString();
        String id = null;
        JSONObject obj = new JSONObject(response);
        JSONArray patients = obj.getJSONArray("results");
        for (int i = 0; i < patients.length(); i++) {
            JSONObject patient = patients.getJSONObject(i);
            if (patient.get("patientFirstName").toString().equals(sFn) && patient.get("patientLastName").toString().equals(sLn)) {
                id = patient.getString("patientId");
            }
        }
        return id;
    }

    /**(Work in progress)
     * Gets patientId by providing patient email
     * @param sEmail (String) Patient email
     * @return (String) patientId
     */
    public String getPatientIdByEmail(String sEmail) {
        //TODO - WIP
        String resource = "/v2/account/";
        Response getResponse = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
                .get(baseURL+resource);
        String response = getResponse.asString();
        String id = null;
        JSONObject obj = new JSONObject(response);
        JSONArray patients = obj.getJSONArray("patients");
        for (int i = 0; i < patients.length(); i++) {
            JSONObject patient = patients.getJSONObject(i);
            if (patient.get("email").toString().equals(sEmail)) {
                id = patient.getString("id");
            }
        }
        return id;
    }


    /**
     * Gets patients number
     * @return (Integer) Patients number
     */
    public Integer getPatientsNumber() {
        String response = patientsGetRequest();
        JSONObject obj = new JSONObject(response);
        return obj.getJSONArray("results").length();
    }
}