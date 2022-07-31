package com.kleistit.farmapi.config;

import com.google.gson.Gson;
import com.google.inject.Inject;

/**
 * Created by thokle on 21/03/2017.
 */
public class JsonUtil {

   static Gson gson;

    @Inject
    public JsonUtil(Gson gson) {
        this.gson = gson;
    }


    public  static  String toString(Object o){

       return gson.toJson(o);
    }

}
