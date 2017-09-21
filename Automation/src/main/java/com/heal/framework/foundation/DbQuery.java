package com.heal.framework.foundation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vahanmelikyan on 9/18/2017.
 */
public class DbQuery {

    private static Map<String, String> queryMap = new HashMap<String, String>();

    public static String getQuery(String queryName){

        return queryMap.get(queryName);
    }

    private static void initDbQueryMap(){
        //load dbquery mappings from file

    }

}
