package com.kleistit.farmapi.handlers.groocery;

import com.google.inject.Inject;
import com.kleistit.farmapi.helperEntities.SalePlaceGroocery;
import com.kleistit.farmapi.services.impl.GrooceryService;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;

public class AddGrooceryHandler implements Handler {

    private GrooceryService grooceryService;

    @Inject
    public AddGrooceryHandler(GrooceryService grooceryService){
        this.grooceryService = grooceryService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.parse(SalePlaceGroocery.class).then(salePlaceGroocery -> {
            this.grooceryService.addGrooceryToSalePlace(salePlaceGroocery.getSalePlace(), salePlaceGroocery.getGroocery());
        });
    }
}
