package com.heal.framework.restAPI;

import com.heal.framework.test.RunTestSuite;
import com.heal.framework.test.TestBase;
import com.heal.framework.web.WebBase;
import io.restassured.RestAssured;

public class ApiBase {

//    public String baseUrl = RunTestSuite.getExcelParams().get("baseUrl").toString();
    public String baseUrl = ".qa.heal.com";
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String username, String password) {
        this.sessionId = getSessionID(username, password);
    }


    public String getSessionID(String username, String password) {

        String sessionId = RestAssured.given()
                .auth()
                .preemptive()
                .basic(username, password)
                .get(baseUrl + "/v2/patients")
                .cookie("SESSION");
        return sessionId;
    }


}
