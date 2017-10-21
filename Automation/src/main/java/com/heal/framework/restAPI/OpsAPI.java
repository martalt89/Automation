package com.heal.framework.restAPI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by vahanmelikyan on 10/13/17.
 */

public class OpsAPI extends ApiBase{

    private String baseURL = "https://ops" + baseUrl;
    private String sAccUsername;
    private String sAccPassword;
    private String sSessionID;
    private Map<String, String> authCookies;

    public static final String COMPLETED = "COMPLETED";
    public static final String CANCELLED = "CANCELLED";
    public static final String SCHEDULED = "SCHEDULED";
    public static final String ACTIVE = "ACTIVE";
    public static final String STARTED = "STARTED";
    public static final String NOT_FINISHED = "NOT_FINISHED";
    public static final String ALL = "ALL";

    /**
     * Constructor
     * @param sAccUsername (String) Account sEmail
     * @param sAccPassword (String) Account sPassword
     */
    public OpsAPI(String sAccUsername, String sAccPassword){
        this.sAccUsername = sAccUsername;
        this.sAccPassword = sAccPassword;
        setsSessionID();
    }

    /**
     * JSON parameters to be send on login call
     * @return (Map) user and password credentials
     */
    private Map loginPostParams() {
        Map<String, String> map = new HashMap<>();
        map.put("username", sAccUsername);
        map.put("password", sAccPassword);
        return map;
    }

    public void setsSessionID(){
        if (opsSessionId==null) {
            opsSessionId = RestAssured.given()
                    .auth()
                    .preemptive()
                    .basic(sAccUsername, sAccPassword)
                    .contentType("application/x-www-form-urlencoded")
                    .get(baseUrlApi + "/security_check2")
                    .cookie("SESSION");
        }
    }

//    public String getSessionID(String username, String password) {
//        return this.sSessionID;
//    }


    /**
     * Returns visit codes based on the search query and status.
     *      Queries can be separated by "," e.g. 'vahan,mitton,Century Park E'
     *      Status need to be on of the following
     *          COMPLETED
     *          CANCELLED
     *          SCHEDULED
     *          ACTIVE
     *          STARTED
     *          NOT_FINISHED
     *          ALL
     *
     * @param sSearchQuery
     * @return List of visitCodes that matched the search query and the status
     */
    public List getVisits(String sSearchQuery, String sStatus, int iPastDays) {
        long now = Instant.now().toEpochMilli();
        long startDate = now - iPastDays*60*60*24*1000;
        long endDate = now + 60*60*24*1000;

        List<String> lVisits = new LinkedList<>();
        String sQuery = "limit=100&search=" + sSearchQuery + "&status=" + sStatus.toUpperCase() + "&start_date=" + startDate + "&end_date="+ endDate;
        String sResourceAPI = "/visits/admin/query?" + sQuery;

        String response = RestAssured.given()
                .auth()
                .preemptive()
                .basic(sAccUsername, sAccPassword)
//                .contentType("application/x-www-form-urlencoded")
//                .cookie("SESSION", sSessionID)
                .cookie("SESSION", opsSessionId)
//                .get(baseUrlApi + sResourceAPI)
                .get(baseUrlOps + sResourceAPI)
                .asString();

        JSONObject obj = new JSONObject(response);
        JSONArray visits = obj.getJSONArray("results");

        for (int i = 0; i < visits.length(); i++) {
            JSONObject patient = visits.getJSONObject(i);
            lVisits.add(patient.getString("visitCode"));
        }
        return lVisits;
    }

    /**
     * Cancel multiple visits
     * @param visitCodes - (List) List of the visits to be canceled
     */

    public void cancelVisit(List visitCodes){
        for (int i=0; i < visitCodes.size(); i++) {
            String visit = visitCodes.get(i).toString();
            System.out.println(visit);
            cancelVisit(visitCodes.get(i).toString());
        }
    }

    /**
     * Cancel single visit
     * @param sVisitCode - (String) Visit code
     */
        public void cancelVisit(String sVisitCode){
            Map<String, String> cancelParam = new HashMap<>();
            cancelParam.put("cancelReasonId", "24");
            cancelParam.put("note", "Canceled by automation test (via API).");
            String sResourceAPI = "/visits/"+ sVisitCode +"/cancel";

            RestAssured.given()
                    .auth()
                    .preemptive()
                    .basic(sAccUsername, sAccPassword)
                    .contentType("application/json")
                    .cookie("SESSION", sSessionID)
                    .body(cancelParam)
                    .post(baseURL + sResourceAPI);
        }
}
