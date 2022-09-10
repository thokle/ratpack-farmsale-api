package com.kleistit.farmapi.services.impl;

import com.google.inject.Inject;
import com.kleistit.farmapi.nodes.SalePlace;
import lombok.extern.slf4j.Slf4j;

import ratpack.exec.ExecController;
import ratpack.exec.Execution;
import ratpack.service.Service;
import ratpack.service.StartEvent;
import ratpack.service.StopEvent;

import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
@Slf4j
public class OpenCloseService implements Service {


    private SalePlaceService salePlace;

    public volatile ScheduledFuture nextFuture;
    private ScheduledExecutorService scheduledExecutorService;
    private IOpenCloseKafkaProducker iOpenCloseKafkaProducker;

    @Inject
    public OpenCloseService(IOpenCloseKafkaProducker iOpenCloseKafkaProducker){
            this.iOpenCloseKafkaProducker = iOpenCloseKafkaProducker;
    }

    @Override
    public void onStart(StartEvent event) throws Exception {

        scheduledExecutorService = event.getRegistry().get(ExecController.class).getExecutor();
        this.salePlace = event.getRegistry().get(SalePlaceService.class);
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

        this.iOpenCloseKafkaProducker.sendToTopic();
        });


    }

}
