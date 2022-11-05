package com.kleistit.farmapi.handlers.groocery;

import com.google.inject.Inject;

import com.kleistit.farmapi.nodes.Groocery;
import com.kleistit.farmapi.services.impl.GrooceryService;

import com.kleistit.farmapi.services.impl.KafaConsumerImpl;
import com.kleistit.farmapi.services.impl.KafkaConsumer;
import com.kleistit.farmapi.services.impl.RatPackRatPackKafkaProducer;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Collections;
import java.util.Optional;
import java.util.TimerTask;
import java.util.concurrent.*;

import org.reactivestreams.Publisher;
import ratpack.exec.ExecController;
import ratpack.exec.Execution;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.handling.internal.DefaultContext;
import ratpack.service.Service;
import ratpack.service.StartEvent;
import ratpack.service.StopEvent;
import ratpack.websocket.WebSocket;
import ratpack.websocket.WebSockets;
import ratpack.websocket.internal.DefaultWebSocket;

import static ratpack.jackson.Jackson.json;;
@Slf4j
public class SoldOutHandlerSocket implements Handler  , Service{
    public volatile ScheduledFuture nextFuture;
    private ScheduledExecutorService  scheduledExecutorService;

    private KafkaConsumer kafkaConsumer;

    private Scheduler scheduler;
    private  Context context;
    @Inject
    public SoldOutHandlerSocket(KafkaConsumer kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
this.scheduledExecutorService = Executors.newScheduledThreadPool(2);


    }

private Future<String> fromFuture(){
        return this.scheduledExecutorService.submit(() -> {
           return "Hello Woe";
        });
}

    @Override
    public void onStart(StartEvent event) throws Exception {

        scheduledExecutorService = event.getRegistry().get(ExecController.class).getExecutor();

        scheduleNext();
    }
    private void scheduleNext(){
        nextFuture=  scheduledExecutorService.schedule(this::run, 1, TimeUnit.SECONDS);
    }
    @Override
    public void onStop(StopEvent event) throws Exception {
        boolean stopped = true;
        Optional.ofNullable(nextFuture).ifPresent(f -> f.cancel(true));
    }

    public  void run(){
        Execution.fork().onComplete(e-> scheduleNext()).onError(Throwable::printStackTrace).start(execution -> {
            log.debug("run run run");
            try {
              //  this.kafkaConsumer.consumerAllSoldOut().subscribe(Collections.singletonList("soldout"));
              //  this.kafkaConsumer.consumerAllSoldOut().poll(Duration.ofSeconds(30)).forEach(stringGrooceryConsumerRecord -> {
              //      log.debug("consume data data data data");
              //  });

            }catch (Exception e){
                log.debug(e.getMessage());
            }
            log.debug("send to solsfsdsdfsfsdfsfddout");
        });
    }



    @Override
    public void handle(Context ctx) throws Exception {

        WebSockets.websocket(context, webSocket -> {
           webSocket.send("Text");
           return "semr";
        });
}
    }





