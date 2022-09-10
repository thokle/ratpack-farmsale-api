package com.kleistit.farmapi.handlers.position;

import com.google.inject.Inject;
import com.kleistit.farmapi.services.impl.PositionService;
import lombok.val;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class UpdatePositionHandler implements Handler {

    private PositionService positionService;
    @Inject
    UpdatePositionHandler(PositionService positionService){
        this.positionService = positionService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        val id = Long.parseLong(ctx.getPathTokens().get("id"));
        val lat = Double.parseDouble(ctx.getPathTokens().get("lat"));
        val lng = Double.parseDouble(ctx.getPathTokens().get("lng"));
        val position = this.positionService.updatePosition(id, lat, lng).blockingGet();
        ctx.render(json(position));

    }
}
