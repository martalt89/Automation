package com.heal.framework.foundation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vahanmelikyan on 9/19/2017.
 */
public class DBVariable {

    //private static Map<String, List<String>> dbVariable = new HashMap<String, List<String>>();
    private static ArrayList<HashMap<String, String>> dbVariable = new ArrayList<HashMap<String, String>>();

    public String getValue(String columnName){
        return getValue(0, columnName);
    }

    public String getValue(int row, String columnName){

        return dbVariable.get(row).get(columnName);
    }

    public HashMap<String, String> getFirstRowResponse(){
        return dbVariable.get(0);
    }

    public HashMap<String, String> getRowResponse(int row){
        return dbVariable.get(row);
    }

    public static void setValue(String columnName, String value){
        setValue(0, columnName, value);
    }

    public static void setValue(int row, String columnName, String value){

        HashMap<String, String> rowResponse = dbVariable.get(row);
        rowResponse.put(columnName, value);

    }

}
