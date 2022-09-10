package com.kleistit.farmapi.services.impl;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import ratpack.exec.ExecController;
import ratpack.exec.Execution;
import ratpack.handling.Context;
import ratpack.service.Service;
import ratpack.service.StartEvent;
import ratpack.service.StopEvent;

import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


@Slf4j
public class SoldOutConsumerService implements Service {

    public volatile ScheduledFuture nextFuture;
    private ScheduledExecutorService scheduledExecutorService;
    private KafkaConsumer kafkaConsumer;


    Context context;
    @Inject
    public SoldOutConsumerService(KafkaConsumer kafkaConsumer ){


        this.kafkaConsumer = kafkaConsumer;
    }

    @Override
    public void onStart(StartEvent event) throws Exception {

        scheduledExecutorService = event.getRegistry().get(ExecController.class).getExecutor();

        scheduleNext();
    }

    @Override
    public void onStop(StopEvent event) throws Exception {
        boolean stopped = true;
        Optional.ofNullable(nextFuture).ifPresent(f -> f.cancel(true));
    }
    private void scheduleNext(){
        nextFuture=  scheduledExecutorService.schedule(this::run, 60, TimeUnit.SECONDS);
    }

    private void run(){
        Execution.fork().onComplete(e-> scheduleNext()).onError(Throwable::printStackTrace).start(execution -> {

            this.kafkaConsumer.consumerAllSoldOut();
            log.debug("get consumer");

        });
    }
}
