package com.kleistit.farmapi.handlers.saleplace;

import com.google.inject.Inject;
import com.kleistit.farmapi.helperEntities.AddressSalePlace;
import com.kleistit.farmapi.services.impl.SalePlaceService;

import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;


public class AddSalePlaceHandler implements Handler {
    SalePlaceService salePlaceService;

    @Inject
    public AddSalePlaceHandler(SalePlaceService salePlaceService){
        this.salePlaceService = salePlaceService;
    }
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.parse(AddressSalePlace.class).then(addressSalePlace -> {
         var res =    this.salePlaceService.addSalePlaceToAddress(addressSalePlace.getAddress(), addressSalePlace.getSalePlace());

         ctx.render(json(res.blockingGet()));
                }
        );
    }

    public static class SoldOutHandler implements Handler {
        @Override
        public void handle(Context ctx) throws Exception {

        }
    }
}
