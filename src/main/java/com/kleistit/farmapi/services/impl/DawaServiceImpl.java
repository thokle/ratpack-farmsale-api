package com.kleistit.farmapi.services.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;

import io.reactivex.Single;

import lombok.var;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DawaServiceImpl implements DawaService{
    @Override
    public Single<JSONArray> getDawaJson(int postnr) throws Exception {
        URL url  = new URL("https://api.dataforsyningen.dk/postnumre?nr="+postnr);
        var httpUrlConnection = (HttpURLConnection) url.openConnection();

        httpUrlConnection.setRequestMethod("GET");
        httpUrlConnection.connect();;
        var responseCode = httpUrlConnection.getResponseCode();

        if(responseCode !=200) {

        }
        String line = "";
        var scanner  = new Scanner(url.openStream());

        while (scanner.hasNext()){
            line += scanner.nextLine();
        }

        scanner.close();

          JSONParser jsonParser = new JSONParser();
        JSONArray jsonObject = (JSONArray) jsonParser.parse(line);


        return  Single.just(jsonObject);



    }

}
