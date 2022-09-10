package com.kleistit.farmapi.handlers.groocery;

import com.google.inject.Inject;
import com.kleistit.farmapi.handlers.saleplace.GetSalePlaceByIdHandler;
import com.kleistit.farmapi.services.impl.GrooceryService;

import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;
public class GetGrooceryByIdHandler implements Handler {
    private GrooceryService grooceryService;

    @Inject
    public GetGrooceryByIdHandler(GrooceryService grooceryService){
        this.grooceryService = grooceryService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        var id = Long.parseLong(ctx.getPathTokens().get("id"));
        var res = this.grooceryService.getGrooceryById(id);
        ctx.render(json(res.blockingGet()));

    }
}
