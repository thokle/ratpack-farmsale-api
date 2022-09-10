package com.kleistit.farmapi.handlers.groocery;

import com.google.inject.Inject;
import com.kleistit.farmapi.helperEntities.SalePlaceGroocery;
import com.kleistit.farmapi.services.impl.GrooceryService;

import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class AddGrooceryHandler implements Handler {

    private GrooceryService grooceryService;

    @Inject
    public AddGrooceryHandler(GrooceryService grooceryService){
        this.grooceryService = grooceryService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.parse(SalePlaceGroocery.class).then(salePlaceGroocery -> {
           var res = this.grooceryService.addGrooceryToSalePlace(salePlaceGroocery.getSalePlace().getId(), salePlaceGroocery.getGroocery());
           ctx.render(json(res.blockingGet()));
        });
    }
}
