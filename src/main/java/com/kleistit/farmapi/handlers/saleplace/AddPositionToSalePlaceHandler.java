package com.kleistit.farmapi.handlers.saleplace;

import com.google.inject.Inject;
import com.kleistit.farmapi.services.impl.SalePlaceService;

import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class AddPositionToSalePlaceHandler implements Handler {

    private SalePlaceService salePlaceService;
    @Inject
    public AddPositionToSalePlaceHandler(SalePlaceService salePlaceService){
        this.salePlaceService = salePlaceService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        long id = Long.parseLong(ctx.getPathTokens().get("id"));
        double lat = Double.parseDouble( ctx.getPathTokens().get("lat"));
        double lng = Double.parseDouble(ctx.getPathTokens().get("lng"));
        var salePlaceSingle = this.salePlaceService.addPosition(id, lat, lng);
        ctx.render(json(salePlaceSingle.blockingGet()));

    }
}
