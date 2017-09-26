package com.heal.framework.foundation;

import java.util.ArrayList;
import java.util.HashMap;



/**
 * Created by vahanmelikyan on 9/19/2017.
 */
public class DBVariable {

    private static HashMap<String, ArrayList<HashMap<String, String>>> responses = new HashMap<String, ArrayList<HashMap<String, String>>>();
    private static ArrayList<HashMap<String, String>> dbVariable = new ArrayList<HashMap<String, String>>();
    private static String CurrentDBRecordName = "";

    public static ArrayList<HashMap<String, String>> getResponse(String dbRecordName){
        CurrentDBRecordName = dbRecordName;
        return responses.get(dbRecordName);
    }

    public static ArrayList<HashMap<String, String>> getResponse(){
        return responses.get(CurrentDBRecordName);
    }

    public static String cell(String columnName){
        return cell(0, columnName);
    }

    public static String cell(int row, String columnName){

        return dbVariable.get(row).get(columnName);
    }

    public static HashMap<String, String> getFirstRowResponse(){
        return dbVariable.get(0);
    }

    public static HashMap<String, String> getRowResponse(int row){
        return dbVariable.get(row);
    }

    public static void setCell(String columnName, String value){
        setCell(0, columnName, value);
    }

    public static void setCell(int row, String columnName, String value){
        HashMap<String, String> rowResponse;
        if(dbVariable.size()<= row){
            rowResponse = new HashMap<String, String>();
            dbVariable.add(rowResponse);
        }
        rowResponse = dbVariable.get(row);
        rowResponse.put(columnName, value);

    }

}
