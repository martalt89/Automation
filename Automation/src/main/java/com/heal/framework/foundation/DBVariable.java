package com.heal.framework.foundation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vahanmelikyan on 9/19/2017.
 */
public class DBVariable {

    private static Map<String, List<String>> dbVariable = new HashMap<String, List<String>>();


    public String getValue(String columnName){
        return getValue(0, columnName);
    }

    public String getValue(int row, String columnName){

        return dbVariable.get(columnName).get(row);
    }

    public static void setValue(String columnName, String value){
        setValue(0, columnName, value);
    }

    public static void setValue(int row, String columnName, String value){
        List<String> resultList = null;
        if(dbVariable.containsKey(columnName) || dbVariable.get(columnName) == null){
            resultList = new ArrayList<String>();
        }
        else{
            resultList = dbVariable.get(columnName);
        }

        if(resultList.size() <= row){
            resultList.add(value);
        }else{
            resultList.set(row, value);
        }

        dbVariable.put(columnName, resultList);
    }

}
