package com.kleistit.farmapi.handlers.dawa;

import com.google.inject.Inject;
import com.kleistit.farmapi.services.impl.DawaService;

import lombok.var;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class DawaHandler implements Handler {

    private DawaService _DawaService;

    @Inject
    public DawaHandler(DawaService service){
        this._DawaService = service;
    }
    @Override
    public void handle(Context ctx) throws Exception {

        var postnr = Integer.parseInt(ctx.getPathTokens().get("postnr"));

       ctx.render(json(this._DawaService.getDawaJson(postnr).blockingGet()));

    }
}
