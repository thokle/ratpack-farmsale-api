package com.kleistit.farmapi.services.impl;

import com.google.inject.Inject;
import com.kleistit.farmapi.config.KafkaJsonDeserializer;
import com.kleistit.farmapi.config.KafkaJsonSerializer;
import com.kleistit.farmapi.modules.KafkaConfiguarableModule;
import com.kleistit.farmapi.nodes.Groocery;
import com.kleistit.farmapi.nodes.SalePlace;
import io.reactivex.Single;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class KafkaOpenConsumerImpl implements  KafkaOpenConsumer{
    private Set<String> servers = new HashSet<>();

    private org.apache.kafka.clients.consumer.KafkaConsumer<String, SalePlace> kafkaProducer;

    private Single<SalePlace>   salePlaceSingle = Single.never();


    @Inject
    public KafkaOpenConsumerImpl(){
        servers.add("127.0.0.1:9092");
        Properties props = new Properties();
        props.put("buffer.memory", 1920000000);
        props.put("message.max.bytes", 104857600);
        props.put("max.request.size", 192000000);
        props.put("send.buffer.bytes", 1000000);
        props.put("bootstrap.servers", String.join(",", servers));
        props.put("client.id", "2");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer" );
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "1");
        this.kafkaProducer  = new KafkaConsumer<String, SalePlace>(props, new StringDeserializer(), new KafkaJsonDeserializer<SalePlace>(SalePlace.class));

    }

    @Override
    public Single<SalePlace> consumerOpenSalePlace() throws Exception {
      try {
            kafkaProducer.subscribe(Collections.singletonList("openplaces"));
            kafkaProducer.poll(Duration.ofSeconds(10)).forEach(stringSalePlaceConsumerRecord -> {
                this.salePlaceSingle = Single.just(stringSalePlaceConsumerRecord.value());
            });
            return this.salePlaceSingle;
      }catch (Exception e){
          throw  new Exception(e.getCause());
      }
    }
}
