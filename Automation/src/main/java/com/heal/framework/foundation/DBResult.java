package com.heal.framework.foundation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vahanmelikyan on 9/18/2017.
 */
public class DBResult {

    private static final InheritableThreadLocal oResponses = new InheritableThreadLocal();
    private static InheritableThreadLocal oCurrentResponseName = new InheritableThreadLocal();

    public static void setResponse(String responseName){
        oCurrentResponseName.set(responseName);
    }

    public static void setResponses(HashMap<String, ArrayList<HashMap<String, String>>> responses){
        oResponses.set(responses);
    }

    public static HashMap<String, ArrayList<HashMap<String, String>>> getResponses(){
        return (HashMap<String, ArrayList<HashMap<String, String>>>)oResponses.get();
    }

    public static void addResponse(String responseName, ArrayList<HashMap<String, String>> dbRecord){
        getResponses().put(responseName, dbRecord);
    }

    public static ArrayList<HashMap<String, String>> getResponse(){
        return getResponses().get(oCurrentResponseName.get());
    }

    public static String cell(String columnName){
        return cell(0, columnName);
    }

    public static String cell(int rowNo, String columnName){

        return row(rowNo).get(columnName);
    }

    public static HashMap<String, String> row(int rowNo){
        return getResponse().get(rowNo);
    }


}
