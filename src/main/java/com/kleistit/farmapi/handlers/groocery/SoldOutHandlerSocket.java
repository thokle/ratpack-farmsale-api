package com.kleistit.farmapi.handlers.groocery;

import com.google.inject.Inject;
import com.kleistit.farmapi.helperEntities.SalePlaceGroocery;
import com.kleistit.farmapi.nodes.Groocery;
import com.kleistit.farmapi.services.impl.GrooceryService;
import lombok.var;
import org.reactivestreams.Publisher;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.sse.ServerSentEvent;

import static ratpack.core.jackson.Jackson.json;

public class SoldOutHandlerSocket implements Handler {

    private GrooceryService grooceryService;
    @Inject
    public SoldOutHandlerSocket(GrooceryService grooceryService){
        this.grooceryService = grooceryService;
    }
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.parse(SalePlaceGroocery.class).then(ss -> {

            var res =   this.grooceryService.soldOut(ss.getSalePlace(), ss.getGroocery());

            Publisher<Groocery> publisher = null;
            ctx.render(ServerSentEvent.builder().data(json(res).toString()));
        });
    }
}
