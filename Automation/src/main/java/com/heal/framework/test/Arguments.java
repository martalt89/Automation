package com.heal.framework.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by vahanmelikyan on 9/4/2017.
 */
public class Arguments {
    private static Logger logger = LoggerFactory.getLogger(Arguments.class);

    public static HashMap<String, String> parseArguments(String[] args){

        HashMap<String, String> argMap = new HashMap<String, String>();

        for(String arg : args){
            if(!arg.contains(RunTestSuite.HealCryptography))
                logger.info("argument: {}", arg);
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
