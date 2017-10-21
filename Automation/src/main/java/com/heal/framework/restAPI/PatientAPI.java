package com.heal.framework.restAPI;

import com.heal.framework.test.TestData;

import java.lang.IllegalArgumentException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * REST calls on /patients API
 * Add patient, return patient info
 */
public class PatientAPI extends ApiBase {

    private RestUtils restUtils = new RestUtils();
    private TestData addPatientInputData = new TestData(TestData.PATIENT_SHEET);
//    private String baseUrlPatient = "https://patient" + baseUrl + "/api";
    private String baseURLAPIv3 = "http://apiv3"+ baseUrl;
    private JSONObject allPatients;
//    private Map<String, String> authCookies;



    public void setAllPatients(){
        allPatients = getAllPatientsOfTheAccount(patientUsername, patientPassword);
    }

    public JSONObject getAllPatients() {
        return allPatients;
    }

    public List<String> setAllPatientList() {
        JSONArray patients = allPatients.getJSONArray("results");
        List<String> patientIDs = new LinkedList<>();
        HashMap<String, String> patientInfo;
        for (int i = 0; i < patients.length(); i++) {
            JSONObject patient = patients.getJSONObject(i);
            patientIDs.add(patient.get("patientId").toString());
            }
        return patientIDs;
    }

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

    private String sPatientId = "";
    private String sSessionID = "";

    /**
     * Constructor
     * @param sAccUsername (String) Account sEmail
     * @param patientPasswprd (String) Account sPassword
     */
    public PatientAPI(String sAccUsername, String patientPasswprd){
        patientUsername = sAccUsername;
        patientPassword = patientPasswprd;
        setAuthCookiesPatient();
        setCurrentStatus();
        sPaymentId = getPaymentId();
    }

    /**
     * Constructor - Initializes patient info variables after adding a patient using test data from Excel
     * @param sAccUsername (String) Account sEmail
     * @param patientPassword (String) Account sPassword
     * @param bAddPatientFromExcel (Boolean)
     *                    If True:
     *                      - a patient will be added using addPatientFromExcel() method.
     *                      - addPatientFromExcel() returns patientId that will initialize sPatientId
     *                    If False:
     *                      - patient will not be added and another constructor will be invoked
     *
     * @throws IllegalArgumentException if values taken from the Excel file are incorrect/invalid
     */
    public PatientAPI(String sAccUsername, String patientPassword, Boolean bAddPatientFromExcel) throws IllegalArgumentException {
        this(sAccUsername, patientPassword);
        if(bAddPatientFromExcel) {
            this.sPatientId = addPatientFromExcel();
            initPatientInfo(this.sPatientId);
        }
    }

    /**
     * Get the paymentid of default payment method for the account
     */
    public String getPaymentId() {
        try {
            return restUtils.getJsonValue(currentStatus.getJSONObject("account").getJSONArray("cards").getJSONObject(0).toString(), "paymentId");
        } catch (Exception e){
            try {
                throw new Exception("Unable to get paymentID from the account, please make sure the account has payment method ", e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public String getsSessionID(){
        return this.sSessionID;
    }

    /**
     * Constructor - Initializes patient info variables using patient id
     * @param sAccUsername (String) Account sEmail
     * @param patientPassword (String) Account sPassword
     * @param sPatientId (String) Patient id
     */
    public PatientAPI(String sAccUsername, String patientPassword, String sPatientId){
        patientUsername = sAccUsername;
        patientPassword = patientPassword;
        this.sPatientId = sPatientId;
        initPatientInfo(this.sPatientId);
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
     * @throws IllegalArgumentException if provided argument is incorrect/invalid
     */
    private String getRelationshipId(String sRelationship) throws IllegalArgumentException {
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
                throw new IllegalArgumentException("Invalid argument for sRelationship: " + sRelationship);
        }
        return sRelationshipId;
    }

    /**
     * Gets genderId(used as param in POST requests on /patients) based on gender string
     * @param sGender (String) Gender string i.g Male (taken from Excel file)
     * @return (String) genderId
     * @throws IllegalArgumentException if provided argument is incorrect/invalid
     */
    private String getGenderId(String sGender) throws IllegalArgumentException {
        String genderId;
        switch (sGender){
            case "Male":
                genderId = MALE; break;
            case "Female":
                genderId = FEMALE; break;
            case "Other":
                genderId = OTHER_GENDER; break;
            default:
                throw new IllegalArgumentException("Invalid argument for sGender: " + sGender );
        }
        return genderId;
    }

    //////////////////
    // POST METHODS //
    //////////////////

    /**
     * Creates a map containing POST params for add patient request, taken from Excel test data file
     * @return (Map) POST params for add patient request
     * @throws IllegalArgumentException if values taken from the Excel file are incorrect/invalid
     */
    private Map addPatientPostParamsFromExcel() throws IllegalArgumentException {
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
                .basic(patientUsername, patientPassword)
                .contentType("application/json")
                .body(postParams)
                .post(baseUrlPatient +resource);
        return restUtils.getJsonValue(response.asString(), "src/patient","patientId");
    }

    /**
     * Adds a patient using data from Excel test data file
     * @return (String) patientId
     * @throws IllegalArgumentException if values taken from the Excel file are incorrect/invalid
     */
    public String addPatientFromExcel() throws IllegalArgumentException {
        String patientId = addPatientPostRequest(addPatientPostParamsFromExcel());
        initPatientInfo(patientId);
        return patientId;
    }

    /**
     * Adds a patient using data from Excel test data file
     * @param patientPostParams (Map) POST request params
     * @return (String) patientId
     * @throws IllegalArgumentException if values taken from the Excel file are incorrect/invalid
     */
    public String addPatient(Map patientPostParams) throws IllegalArgumentException {
        String patientId =  addPatientPostRequest(patientPostParams);
        return patientId;
    }


    /**
     * Makes a GET request on /patients
     * @return (String) GET response
     */
    private String patientsGetRequest(){
        String resource = "/v2/patients/";
        Response getResponse = RestAssured.given()
                .auth()
                .preemptive()
                .basic(patientUsername, patientPassword)
                .get(baseUrlPatient +resource);
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
                .basic(this.patientUsername, this.patientPassword)
                .get(baseUrlPatient +resource+ sPatientId);
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
                .basic(this.patientUsername, this.patientPassword)
                .get(baseUrlPatient +resource);
        return getResponse.asString();
    }

    /**(Work in progress)
     * Gets patientId by providing patient firstname and lastname
     * @param sFn (String) Patient firstname
     * @param sLn (String) Patient lastname
     * @return (String) patientId
     */
    public String getPatientIdByFnLn(String sFn, String sLn) {
        JSONArray patients = currentStatus.getJSONObject("account").getJSONArray("patients");
        String id = null;
        for (int i = 0; i < patients.length(); i++) {
            JSONObject patient = patients.getJSONObject(i);
            if (patient.get("patientFirstName").toString().equals(sFn) && patient.get("patientLastName").toString().equals(sLn)) {
                id = patient.getString("patientId");
            }
        }
        return id;
    }

    /**
     * Gets patientId by providing patient firstname
     * @param patientUsername (String) account username
     * @param sPassword (String) account password
     * @return (String) patientId
     */
    public JSONObject getAllPatientsOfTheAccount(String patientUsername, String sPassword) {
        int numberOfPatient = 200;
        String resource = "/v2/patients/" + "?limit="+numberOfPatient;
        String response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(patientUsername, patientPassword)
                .get(baseUrlPatient +resource)
                .asString();
        return new JSONObject(response);
    }

    /**
     * Gets patientId by providing patient firstname
     * @param sFirstName (String) Patient firstname
     * @return (String) patientId
     */
    public String getPatientIdByFirstname(String sFirstName) {
        String id = null;
        JSONArray patients = currentStatus.getJSONObject("account").getJSONArray("patients");

        for (int i = 0; i < patients.length(); i++) {
            JSONObject patient = patients.getJSONObject(i);
            if (patient.get("patientFirstName").toString().equals(sFirstName)) {
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
        String id = null;
        JSONArray patients = currentStatus.getJSONObject("account").getJSONArray("patients");
        for (int i = 0; i < patients.length(); i++) {
            JSONObject patient = patients.getJSONObject(i);
            if (patient.get("email").toString().equals(sEmail)) {
                id = patient.getString("patientId");
                break;
            }
        }
        if (id==null){
            throw new  IllegalArgumentException("Cannot find a patient with " + sEmail +" email in " + patientUsername + "'s account.");
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

    /**
     * login parameters to be send on login call
     * @return (Map) user and password credentials
     */
    private Map loginPostParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", patientUsername);
        map.put("password", patientPassword);
        return map;
    }


    /**
     * Removes a patient from account by patientId
     * DELETE /v2/patient/{patientId}
     *
     * This method makes a login, takes the cookies and pass them to DELETE request
     */
    public String deletePatient(String sId){
        String resourcePatient = "/v2/patient/";
        Response deleteResponse = RestAssured.given()
                .header("Origin", "http://localhost.getheal.com")
                .header("Content-Type", "application/json")
                .cookies(ApiBase.authCookiesPatient)
                .delete(baseURLAPIv3 + resourcePatient + sId);
        return restUtils.getJsonValue(deleteResponse.asString(),"status");
    }

    /**
     * This method will return user details for a specific user
     * @param sPatientID - (String) Patient ID
     * @return HashMap of user details
     */
    public HashMap<String, String> getPatientDetailsByID(String sPatientID) {
        String resource = "/v3/patients/" + sPatientID;
        String response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(patientUsername, patientPassword)
                .get(baseUrlPatient +resource)
                .asString();
        return restUtils.jsonStringToHashMap(response);
    }

    /**
     * This method will return user details for a specific user
     * @param sPatientFirstname - (String) Patient ID
     * @return HashMap of user details
     */
    public HashMap<String, String> getPatientDetailsByFirstName(String sPatientFirstname) {
        String patientID = getPatientIdByFirstname(sPatientFirstname);
        return getPatientDetailsByID(patientID);
    }

    /**
     * Update patient details
     * @param sPatientID - String patient ID of the patient to be updated
     * @param patientDetails - Map of the details to be used to update
     */
    public void updatePatient(String sPatientID, HashMap<String, String> patientDetails){
        String resource = "/v3/patients/" + sPatientID;
        RestAssured.given()
                .auth()
                .preemptive()
                .basic(patientUsername, patientPassword)
                .contentType("application/json")
                .cookie("SESSION", sSessionID)
                .body(patientDetails)
                .put(baseUrlPatient + resource);
    }

    public void removeInsurance(String patientFirstName){
        String patientID = getPatientIdByFirstname(patientFirstName);
        HashMap<String, String> map = getPatientDetailsByID(patientID);
        map.replace("status", null);

        updatePatient(patientID, map);
    }

    public void addTestInsuranceToPatient(String sPatientId){

        HashMap<String, String> patientDetails = getPatientDetailsByID(sPatientId);
        patientDetails.replace("hasInsurance", "true");
        patientDetails.replace("insuranceName", "aetna");
        patientDetails.replace("memberId", "COST_ESTIMATES_025");
        patientDetails.replace("patientInsuranceId", "0001508045392319-2f663b05b4c-0001");
        patientDetails.replace("payerId", "0001444090950393-c6983382190f-0006");
        patientDetails.replace("insuranceId", "0001444090950393-c6983382190f-0006");
        patientDetails.replace("groupId", "X0001004");
        patientDetails.replace("planName", "SILVER 70 PPO JAN16");
        patientDetails.replace("status", "ACTIVE");
        patientDetails.replace("relationship", "Child");
        patientDetails.put("eligibilityId", insuranceEligibility(patientDetails));
        updatePatient(sPatientId, patientDetails);

    }

    public String insuranceEligibility(HashMap<String, String> patientDetails) { //Todo make this cleaner
        HashMap<String, String > map;
        String resource = "/v2/insurance-eligibility";
        patientDetails.put("memberId","COST_ESTIMATES_025");
        patientDetails.put("insuranceName","aetna");
        patientDetails.replace("phone", "18182123842");
        patientDetails.put("mode", "MANUAL");
        patientDetails.put("emailRegex", "{}");
        patientDetails.put("patientId", patientDetails.get("id"));

       String response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(patientUsername, patientPassword)
                .contentType("application/json")
                .cookie("SESSION", sSessionID)
                .body(patientDetails)
                .post(baseUrlPatient +resource)
                .asString();
        return restUtils.getJsonValue(response,"eligibilityId");

    }

}