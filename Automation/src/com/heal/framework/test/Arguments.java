package com.heal.framework.test;

import java.util.HashMap;

/**
 * Created by vahanmelikyan on 9/4/2017.
 */
public class Arguments {


    public static HashMap<String, String> parseArguments(String[] args){

        HashMap<String, String> argMap = new HashMap<String, String>();

        for(String arg : args){
            String[] map = arg.split("=");
            if(map.length == 2){
                argMap.put(map[0], map[1]);
            }
            else {
                argMap.put(map[0], "");
            }
        }

        return argMap;
    }
}
