package com.kleistit.farmapi.services.impl;





import io.reactivex.Single;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface DawaService {

    public Single<JSONArray> getDawaJson(int postnr) throws Exception;
}
