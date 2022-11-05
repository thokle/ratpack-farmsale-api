package com.kleistit.farmapi.handlers.saleplace;

import com.google.inject.Inject;
import com.kleistit.farmapi.services.impl.SalePlaceService;
import lombok.val;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class AllCloseSalePlaceHandler implements Handler {

    private SalePlaceService salePlaceService;

    @Inject
    public AllCloseSalePlaceHandler(SalePlaceService salePlaceService){
        this.salePlaceService = salePlaceService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        val salePlaceFlowable = this.salePlaceService.allCloseSalePlaces();
        ctx.render(json(salePlaceFlowable.blockingIterable()));

    }
}
