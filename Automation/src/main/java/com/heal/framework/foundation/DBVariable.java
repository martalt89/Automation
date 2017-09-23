package com.heal.framework.foundation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vahanmelikyan on 9/19/2017.
 */
public class DBVariable {

    //private static Map<String, List<String>> dbVariable = new HashMap<String, List<String>>();
    private static ArrayList<HashMap<String, String>> dbVariable = new ArrayList<HashMap<String, String>>();

    public static String getCellValue(String columnName){
        return getCellValue(0, columnName);
    }

    public static String getCellValue(int row, String columnName){

        return dbVariable.get(row).get(columnName);
    }

    public static HashMap<String, String> getFirstRowResponse(){
        return dbVariable.get(0);
    }

    public static HashMap<String, String> getRowResponse(int row){
        return dbVariable.get(row);
    }

    public static void setCellValue(String columnName, String value){
        setCellValue(0, columnName, value);
    }

    public static void setCellValue(int row, String columnName, String value){
        HashMap<String, String> rowResponse;
        if(dbVariable.size()<= row){
            rowResponse = new HashMap<String, String>();
            dbVariable.add(rowResponse);
        }
        rowResponse = dbVariable.get(row);
        rowResponse.put(columnName, value);
    }

}
