package com.kleistit.farmapi.services.impl;

import com.google.inject.Inject;
import com.kleistit.farmapi.config.KafkaJsonDeserializer;
import com.kleistit.farmapi.modules.KafkaConfiguarableModule;
import com.kleistit.farmapi.nodes.Groocery;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.common.serialization.StringDeserializer;
import ratpack.exec.Execution;
import ratpack.exec.Promise;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
@Slf4j
public class KafaConsumerImpl implements KafkaConsumer{
    private Set<String> servers = new HashSet<>();

    private String clientId = "1";
    private Long maxBlockMillis = TimeUnit.MINUTES.toMillis(1);

    public volatile ScheduledFuture nextFuture;
    private ScheduledExecutorService scheduledExecutorService;
    org.apache.kafka.clients.consumer.KafkaConsumer<String, Groocery> kafkaConsumer;
    private final KafkaConfiguarableModule.Config config;
    private Groocery groocery;
    @Inject
    KafaConsumerImpl(KafkaConfiguarableModule.Config config){
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

   this.config = config;
        kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, Groocery>(this.config.getKafkaProperties(),new StringDeserializer(), new KafkaJsonDeserializer<Groocery>(Groocery.class));

    }


    @Override
    public synchronized org.apache.kafka.clients.consumer.KafkaConsumer<String, Groocery> consumerAllSoldOut() throws Exception {
        try {
            return this.kafkaConsumer;
        }   catch (Exception e){
        throw  new Exception(e.getCause());
        }


}
    }

