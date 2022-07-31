package com.kleistit.farmapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@Slf4j
public class KafkaJsonDeserializer<T> implements Deserializer {


    private Class <T> type;

    public KafkaJsonDeserializer(Class type){
        this.type = type;
    }
@Override
public void configure(Map map, boolean b){}

    @Override
    public Object deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        T obj = null;
        try {
            obj = objectMapper.readValue(data, type);
        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return  obj;
    }



    @Override
    public void close() {
        Deserializer.super.close();
    }
}
