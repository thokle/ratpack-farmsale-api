package com.kleistit.farmapi.handlers.saleplace;

import com.google.inject.Inject;
import com.kleistit.farmapi.services.impl.SalePlaceService;

import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class OpenSalePlaceHandler implements Handler {

    private SalePlaceService salePlaceService;

    
    @Inject
    OpenSalePlaceHandler(SalePlaceService salePlaceService){
        this.salePlaceService = salePlaceService;
    }
    @Override
    public void handle(Context ctx) throws Exception {
       var id = Long.parseLong(ctx.getPathTokens().get("id"));

        var salePlaceSingle = this.salePlaceService.openSalePalace(id);


        ctx.render(json(salePlaceSingle.blockingGet()));

    }
}
