package com.kleistit.farmapi.handlers.saleplace;

import com.google.inject.Inject;
import com.kleistit.farmapi.nodes.SalePlace;
import com.kleistit.farmapi.services.impl.KafkaOpenConsumer;
import lombok.extern.slf4j.Slf4j;

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
public class SalePalceOpenHandlerSocket implements Handler {

    private ScheduledExecutorService scheduledExecutorService;

    private KafkaOpenConsumer  kafkaOpenConsumer;

    @Inject
    public  SalePalceOpenHandlerSocket(KafkaOpenConsumer kafkaOpenConsumer){
        this.kafkaOpenConsumer = kafkaOpenConsumer;
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }
    @Override
    public void handle(Context ctx) throws Exception {
        this.scheduledExecutorService.schedule(() -> {
            Publisher<ServerSentEvent> stream = null;
            SalePlace groocery = null;
            try {
                groocery = this.kafkaOpenConsumer.consumerOpenSalePlace().blockingGet();
                stream = ParallelBatch.of(Promise.value(ServerSentEvent.builder().event("event").id("1").data("Value data" + Jackson.getObjectWriter(ctx).writeValueAsString(groocery)).build())).publisher();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            log.debug("Server sent event");
            ctx.render(ServerSentEvents.builder().build(stream));
        },100, TimeUnit.SECONDS);
    }
}
