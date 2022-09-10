package com.kleistit.farmapi.handlers.saleplace;

import com.google.inject.Inject;
import com.kleistit.farmapi.nodes.SalePlace;
import com.kleistit.farmapi.services.impl.KafkaCloseConsumer;
import com.kleistit.farmapi.services.impl.SalePlaceService;
import lombok.extern.slf4j.Slf4j;


import lombok.var;
import org.reactivestreams.Publisher;
import ratpack.exec.Promise;

import ratpack.exec.util.ParallelBatch;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.Jackson;
import ratpack.sse.ServerSentEvent;
import ratpack.sse.ServerSentEvents;

import static ratpack.jackson.Jackson.json;
@Slf4j
public class CloseSalePlacesSocket implements Handler {

 private KafkaCloseConsumer kafkaCloseConsumer;


 private  volatile ScheduledExecutorService scheduledExecutorService;
 @Inject
    private CloseSalePlacesSocket(KafkaCloseConsumer kafkaCloseConsumer) {
  this.kafkaCloseConsumer = kafkaCloseConsumer;
  this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }
    @Override
    public void handle(Context ctx) throws Exception {
        try{
       this.scheduledExecutorService.schedule(() -> {
            log.debug("SscheduledExecutorService close place");
        }, 20, TimeUnit.SECONDS);
        Publisher<ServerSentEvent> stream = null;
        var groocery = this.kafkaCloseConsumer.consumeClosedSalePlace().blockingGet();
        stream = ParallelBatch.of(Promise.value(ServerSentEvent.builder().event("event").id("1").data("Value data" + Jackson.getObjectWriter(ctx).writeValueAsString(groocery)).build())).publisher();

        log.debug("Server sent event");
        ctx.render(ServerSentEvents.builder().build(stream));


    } catch (Exception ex) {
        log.debug(ex.getMessage());
    }

    }
}
