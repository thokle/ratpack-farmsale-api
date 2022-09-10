package com.kleistit.farmapi.services.impl;


import com.google.inject.Inject;
import com.kleistit.farmapi.config.KafkaJsonSerializer;
import com.kleistit.farmapi.modules.KafkaConfiguarableModule;
import com.kleistit.farmapi.nodes.Groocery;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class RatPackRatPackKafkaProducer implements IRatPackKafkaProducer {


    private org.apache.kafka.clients.producer.KafkaProducer<byte[],Groocery> kafkaProducer;
    private final KafkaConfiguarableModule.Config  config;
    private  GrooceryService grooceryService;


    private KafkaJsonSerializer kafkaJsonSerializer;

    @Inject
    public RatPackRatPackKafkaProducer(KafkaConfiguarableModule.Config  config, GrooceryService grooceryServic, KafkaJsonSerializer kafkaJsonSerializer) {
        this.config = config;
    kafkaProducer = new org.apache.kafka.clients.
            producer.KafkaProducer(this.config.getKafkaProperties(), new StringSerializer(), new KafkaJsonSerializer());
   this.grooceryService = grooceryServic;
   this.kafkaJsonSerializer = kafkaJsonSerializer;
    }

    @Override
    public void sendToTopic() throws Exception {
       this.grooceryService.getAllSoldOut().subscribe(groocery -> {
          if( groocery.getIsSoldOut()) {
              kafkaProducer.send(new ProducerRecord<>("soldout", groocery));
           }
           kafkaProducer.send(new ProducerRecord<>("soldout", groocery));

       });

    }
}
