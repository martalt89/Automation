package com.heal.framework.foundation;

import java.util.HashMap;

/**
 * Created by vahanmelikyan on 9/18/2017.
 */
public class DbQuery {

    private static HashMap<String, String> queryMap = getDbQueryMap();


    public static String getQuery(String queryName){
        return queryMap.get(queryName);
    }

    private static HashMap<String, String> getDbQueryMap(){
        if(queryMap == null){
            queryMap = new HashMap<String, String>();
        }
        loadDbQueryMap();
        return queryMap;
    }


    private static void loadDbQueryMap(){

        //load dbquery mappings
        queryMap.put("user_account", "Select * from \n" +
                                        "user_account \n" +
                                        "where username='vahan+qa@heal.com'");

        queryMap.put("cancel_reason", "SELECT *\n" +
                                        " FROM cancel_reason\n" +
                                        "    where id = '%{id}';");

    }
}
