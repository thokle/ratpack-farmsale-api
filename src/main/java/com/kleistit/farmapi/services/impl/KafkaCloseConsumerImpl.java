package com.kleistit.farmapi.services.impl;

import com.google.inject.Inject;
import com.kleistit.farmapi.config.KafkaJsonDeserializer;
import com.kleistit.farmapi.modules.KafkaConfiguarableModule;
import com.kleistit.farmapi.nodes.SalePlace;
import io.reactivex.Single;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;

public class KafkaCloseConsumerImpl implements KafkaCloseConsumer{



    private org.apache.kafka.clients.consumer.KafkaConsumer<String, SalePlace> kafkaConsumer;
    private final KafkaConfiguarableModule.Config  config;
    private Single<SalePlace>   salePlaceSingle = Single.never();


    @Inject
    public KafkaCloseConsumerImpl(KafkaConfiguarableModule.Config config){
        this.config = config;
        this.kafkaConsumer  = new KafkaConsumer<String, SalePlace>(this.config.getKafkaProperties(), new StringDeserializer(), new KafkaJsonDeserializer<SalePlace>(SalePlace.class));

    }

    @Override
    public Single<SalePlace> consumeClosedSalePlace() throws Exception {
       try {
           this.kafkaConsumer.subscribe(Collections.singletonList("allcloseplaces"));
           this.kafkaConsumer.poll(Duration.ofSeconds(30)).forEach(stringSalePlaceConsumerRecord -> {
               this.salePlaceSingle = Single.just(stringSalePlaceConsumerRecord.value());
           });
           return  this.salePlaceSingle;
       }  catch (Exception e){
           throw new Exception(e.getCause());
       }
    }
}
