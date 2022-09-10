package com.kleistit.farmapi.handlers.saleplace;

import com.google.inject.Inject;
import com.kleistit.farmapi.services.impl.SalePlaceService;
import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class GetSalePlacesByAddressHandler implements Handler {

    private SalePlaceService salePlaceService;

    @Inject
    public GetSalePlacesByAddressHandler(SalePlaceService salePlaceService){
        this.salePlaceService = salePlaceService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        var streetname = ctx.getRequest().getHeaders().get("streetname");
        var postalcode  = Integer.parseInt(ctx.getRequest().getHeaders().get("postalCode"));
        var res = this.salePlaceService.getSalePlacesByAddess(streetname, postalcode).blockingIterable();

        ctx.render(json(res));
    }
}
