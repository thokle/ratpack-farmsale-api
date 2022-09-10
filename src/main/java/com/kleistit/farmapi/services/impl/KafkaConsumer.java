package com.kleistit.farmapi.services.impl;

import com.kleistit.farmapi.nodes.Groocery;
import io.reactivex.Flowable;
import io.reactivex.Single;
import ratpack.exec.Promise;

public interface KafkaConsumer {

    org.apache.kafka.clients.consumer.KafkaConsumer<String, Groocery> consumerAllSoldOut() throws  Exception;
}
