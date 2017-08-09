package framework.restAPI;

import com.google.gson.JsonObject;
import com.sun.javaws.exceptions.InvalidArgumentException;
import framework.test.TestData;
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
    private TestData addPatientInputData = new TestData(TestData.PATIENT_SHEET);
    private String baseURL = "https://patient.qa.heal.com/api";

    //patient info
    public String sPatientIdFromInfo;
    public String sPatientFirstname;
    public String sPatientLastname;
    public String sPatientEmail;
    public String sPatientPhone;
    public String sPatientDateOfBirth;
    public String sPatientRelationship;

    //sRelationship ids
    public static final String SPOUSE = "0001433013870063-d279fc27ffff816b-0006";
    public static final String PARTNER = "0001433013870063-d279fc27ffff816b-0007";
    public static final String GRANDPARENT = "0001433013870063-d279fc27ffff816b-0011";
    public static final String GRANDCHILD = "0001433013870063-d279fc27ffff816b-0009";
    public static final String CHILD = "0001433013870063-d279fc27ffff816b-0008";
    public static final String PARENT = "0001433013870063-d279fc27ffff816b-0010";
    public static final String SIBLING = "0001433013870063-d279fc27ffff816b-0005";
    public static final String OTHER_RELATIONSHIP = "0001433013870063-d279fc27ffff816b-0014";
    public static final String YOU = "0001433013870063-d279fc27ffff816b-0004";
    public static final String FRIEND = "0001433013870063-d279fc27ffff816b-0013";
    public static final String COWORKER = "0001433013870063-d279fc27ffff816b-0012";
    public static final String ASSISTED_LIVING_RESIDENT = "0001433013870063-d279fc27ffff816b-0015";
    public static final String SKILLED_NURSING_FACILITY = "0001433013870063-d279fc27ffff816b-0016";

    //sGender ids
    public static final String FEMALE = "0001433013870063-d279fc27ffff816b-0002";
    public static final String MALE = "0001433013870063-d279fc27ffff816b-0001";
    public static final String OTHER_GENDER = "0001433013870063-d279fc27ffff816b-0003";

    //for constructors
    private String sAccUsername;
    private String sAccPassword;
    private String sPatientId;

    /**
     * Constructor - Initializes patient info variables
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
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
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
     */
    public PatientAPI(String sAccUsername, String sAccPassword){
        this.sAccUsername = sAccUsername;
        this.sAccPassword = sAccPassword;
    }

    /**
     *
     * @param sRelationship (String) Relationship string i.g Spouse (taken from Excel test data file)
     * @return (String) Relationship Id
     * @throws InvalidArgumentException
     */
    public String getRelationshipId(String sRelationship) throws InvalidArgumentException {
        String id;
        switch (sRelationship){
            case "Spouse":
                id = SPOUSE; break;
            case "Partner":
                id = PARTNER; break;
            case "Grandparent":
                id = GRANDPARENT; break;
            case "Grandchild":
                id = GRANDCHILD; break;
            case "Child":
                id = CHILD; break;
            case "Parent":
                id = PARENT; break;
            case "Sibling":
                id = SIBLING; break;
            case "Other":
                id = OTHER_RELATIONSHIP; break;
            case "You":
                id = YOU; break;
            case "Friend":
                id = FRIEND; break;
            case "Coworker":
                id = COWORKER; break;
            case "Assisted Living Resident":
                id = ASSISTED_LIVING_RESIDENT; break;
            case "Skilled Nursing Facility":
                id = SKILLED_NURSING_FACILITY; break;
            default:
                throw new InvalidArgumentException(new String[]{"Invalid argument for sRelationship: ", sRelationship});
        }
        return id;
    }

    /**
     *
     * @param sGender (String) Gender string i.g Male (taken from Excel file)
     * @return Gender id
     * @throws InvalidArgumentException
     */
    public String getGenderId(String sGender) throws InvalidArgumentException {
        String id;
        switch (sGender){
            case "Male":
                id = MALE; break;
            case "Female":
                id = FEMALE; break;
            case "Other":
                id = OTHER_GENDER; break;
            default:
                throw new InvalidArgumentException(new String[]{"Invalid argument for sGender: ", sGender });
        }
        return id;
    }

    /**
     * Adds a patient to the specified user account
     * @return (String) POST response
     * @throws InvalidArgumentException
     */
    public String addPatient() throws InvalidArgumentException {
        String resource = "/v2/patients";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("emailRegex", new JsonObject());
        jsonAsMap.put("bHasMedicareMedicaid", addPatientInputData.bHasMedicareMedicaid);
        jsonAsMap.put("firstName", addPatientInputData.sFirstname);
        jsonAsMap.put("lastName", addPatientInputData.sLastname);
        jsonAsMap.put("email", addPatientInputData.sEmail);
        jsonAsMap.put("phone", addPatientInputData.sPhoneNumber);
        jsonAsMap.put("dateOfBirth", addPatientInputData.sDateOfBirth);
        jsonAsMap.put("relationshipId", getRelationshipId(addPatientInputData.sRelationship));
        jsonAsMap.put("genderId", getGenderId(addPatientInputData.sGender));
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
     * {sPatientIdFromInfo, sFirstname, sLastname, sEmail, phone, sRelationship, date of birth}
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
        patientInfoMap.put("id", restUtils.getJsonValue(response, "id"));
        patientInfoMap.put("firstName", restUtils.getJsonValue(response, "firstName"));
        patientInfoMap.put("lastName", restUtils.getJsonValue(response, "lastName"));
        patientInfoMap.put("email", restUtils.getJsonValue(response, "email"));
        patientInfoMap.put("phone", restUtils.getJsonValue(response, "phone"));
        patientInfoMap.put("sRelationship", restUtils.getJsonValue(response, "sRelationship"));
        patientInfoMap.put("sDateOfBirth", restUtils.getJsonValue(response, "sDateOfBirth"));
        return patientInfoMap;
    }

    /**
     * Initializes patient info variables
     */
    private void initPatientInfo(){
        Map patient = collectPatientInfo();
        sPatientIdFromInfo = (String) patient.get("id");
        sPatientFirstname = (String) patient.get("firstName");
        sPatientLastname = (String) patient.get("lastName");
        sPatientEmail = (String) patient.get("email");
        sPatientPhone = (String) patient.get("phone");
        sPatientRelationship = (String) patient.get("sRelationship");
        sPatientDateOfBirth = (String) patient.get("sDateOfBirth");
    }


}