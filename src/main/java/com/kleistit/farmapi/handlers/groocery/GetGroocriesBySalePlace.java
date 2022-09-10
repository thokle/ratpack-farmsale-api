package com.kleistit.farmapi.handlers.groocery;

import com.google.inject.Inject;
import com.kleistit.farmapi.nodes.SalePlace;
import com.kleistit.farmapi.services.impl.GrooceryService;

import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class GetGroocriesBySalePlace implements Handler {

    private GrooceryService grooceryService;

    @Inject
    public GetGroocriesBySalePlace(GrooceryService grooceryService){
        this.grooceryService = grooceryService;
    }
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.parse(SalePlace.class).then(salePlace -> {
            var grooceryBySalePlace = this.grooceryService.getGrooceryBySalePlace(salePlace);

            ctx.render(json(grooceryBySalePlace.blockingIterable()));

        });
    }
}
