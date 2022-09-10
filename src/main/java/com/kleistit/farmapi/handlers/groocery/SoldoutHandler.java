package com.kleistit.farmapi.handlers.groocery;

import com.google.inject.Inject;
import com.kleistit.farmapi.services.impl.GrooceryService;

import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class SoldoutHandler implements Handler {

    private GrooceryService grooceryService;

    @Inject
    public SoldoutHandler(GrooceryService grooceryService){
        this.grooceryService = grooceryService;
    }
    @Override
    public void handle(Context ctx) throws Exception {
        var allSoldOut = this.grooceryService.getAllSoldOut();

        ctx.render(json(allSoldOut.blockingIterable()));
    }
}
