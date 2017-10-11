package com.heal.framework.restAPI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heal.framework.foundation.SysTools;
import org.json.*;

import java.util.HashMap;
import java.util.Map;


public class RestUtils {

    /**
     * Parses a JSON string and returns the searched value
     * @param sJsonString (String) JSON String to be parsed
     * @param sJsonKey (String) JSON key that is searched
     *                e.g. "firstName" from {"firstname":"Joe"}
     * @return (String) Value of the given JSON key
     */
    public String getJsonValue(String sJsonString, String sJsonKey){
        JSONObject obj = new JSONObject(sJsonString);
        return obj.getString(sJsonKey);
    }

    /**
     * Parses a JSON string and returns the searched value
     * @param sJsonString (String) JSON String to be parsed
     * @param jsonObject (String) JSON object containing the key that is searched
     *                  e.g. "account" from "account"[{ "sFirstname" from "sFirstname":"Joe"}]
     * @param sJsonKey (String) JSON key that is searched e.g. "sFirstname" from "sFirstname":"Joe"
     * @return (String) Value of the given JSON key
     */
    public String getJsonValue(String sJsonString, String jsonObject, String sJsonKey){
        JSONObject obj = new JSONObject(sJsonString);
        return obj.getJSONObject(jsonObject).getString(sJsonKey);
    }

    /**
     * Generates a unique username using timestamp
     * @return (String) Generated username
     */
    public String generateUsername(){
        return "qa_auto_test_" + SysTools.getTimestamp("yyyy_MM_dd_HH-mm-ss") +"@heal.com";
    }

    public HashMap<String, String> jsonStringToHashMap(String jsonString){
        HashMap<String, String> map = new HashMap<>();
        try {
            map = new ObjectMapper().readValue(jsonString, new TypeReference<Map<String, String>>(){});
        } catch (Exception ex) {
        }
        return map;
    }
}

