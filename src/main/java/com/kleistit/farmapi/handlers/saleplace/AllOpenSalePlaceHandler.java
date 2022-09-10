package com.kleistit.farmapi.handlers.saleplace;

import com.google.inject.Inject;
import com.kleistit.farmapi.services.impl.SalePlaceService;
import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class AllOpenSalePlaceHandler implements Handler {

    private SalePlaceService salePlaceService;

    @Inject
    public  AllOpenSalePlaceHandler(SalePlaceService salePlaceService){
        this.salePlaceService = salePlaceService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        var salePlaceFlowable = this.salePlaceService.allOpenSalePlacess();


        ctx.render(json(salePlaceFlowable.blockingIterable()
        ));
    }
}
