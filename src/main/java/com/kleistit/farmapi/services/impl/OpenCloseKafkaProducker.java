package com.kleistit.farmapi.services.impl;

import com.google.inject.Inject;
import com.kleistit.farmapi.config.KafkaJsonSerializer;
import com.kleistit.farmapi.modules.KafkaConfiguarableModule;
import com.kleistit.farmapi.nodes.Groocery;
import com.kleistit.farmapi.nodes.SalePlace;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Slf4j
public class OpenCloseKafkaProducker implements IOpenCloseKafkaProducker{

    private Set<String> servers = new HashSet<>();

    private org.apache.kafka.clients.producer.KafkaProducer<byte[], SalePlace> kafkaProducer;
private SalePlaceService salePlaceService;
    @Inject
    public OpenCloseKafkaProducker(SalePlaceService salePlaceService){
        servers.add("127.0.0.1:9092");
        Properties props = new Properties();
        props.put("buffer.memory", 1920000000);
        props.put("message.max.bytes", 104857600);
        props.put("max.request.size", 192000000);
        props.put("send.buffer.bytes", 1000000);
        props.put("bootstrap.servers", String.join(",", servers));
        props.put("client.id", "3");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer" );
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "1");

        this.salePlaceService = salePlaceService;
        this.kafkaProducer =  new org.apache.kafka.clients.
                producer.KafkaProducer(props, new StringSerializer(), new KafkaJsonSerializer());
    }
    @Override
    public void sendToTopic() throws Exception {
        this.salePlaceService.allOpenSalePlacess().subscribe(salePlace -> {
            this.kafkaProducer.send(new ProducerRecord<>("openplaces", salePlace));
        }, throwable -> {
            log.debug("Open saleplace error" + throwable.toString());
        });

        this.salePlaceService.allCloseSalePlaces().subscribe(salePlace -> {
            this.kafkaProducer.send(new ProducerRecord<>("allcloseplaces", salePlace));
        }, throwable -> {
            log.debug("Close saleplace error"  + throwable.toString());
        });
    }
}
