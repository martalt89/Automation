package framework.restAPI;

import foundation.SysTools;
import org.json.*;

public class RestUtils {

    /**
     * Parses a JSON string and returns the searched value
     * @param sJsonString (String) JSON String to be parsed
     * @param sJsonKey (String) JSON key that is searched
     *                e.g. "firstName" from {"firstname":"Joe"}
     * @return (String) Value of the given JSON key
     */
    public String responseParser(String sJsonString, String sJsonKey){
        JSONObject obj = new JSONObject(sJsonString);
        return obj.getString(sJsonKey);
    }

    /**
     * Parses a JSON string and returns the searched value
     * @param sJsonString (String) JSON String to be parsed
     * @param jsonObject (String) JSON object containing the key that is searched
     *                  e.g. "account" from "account"[{ "firstname" from "firstname":"Joe"}]
     * @param sJsonKey (String) JSON key that is searched e.g. "firstname" from "firstname":"Joe"
     * @return (String) Value of the given JSON key
     */
    public String responseParser(String sJsonString, String jsonObject, String sJsonKey){
        JSONObject obj = new JSONObject(sJsonString);
        return obj.getJSONObject(jsonObject).getString(sJsonKey);
    }

    /**
     * Generates a unique username using timestamp
     * @return (String) Generated username
     */
    public static String generateUsername(){
        return "qa_auto_test_" + SysTools.getTimestamp("yyyy_MM_dd_HH-mm-ss") +"@heal.com";
    }
}

