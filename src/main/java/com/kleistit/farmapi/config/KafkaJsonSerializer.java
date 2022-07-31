package com.kleistit.farmapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
@Slf4j
public class KafkaJsonSerializer  implements Serializer {


    @Override
    public void configure(Map map, boolean b){

    }

    @Override
    public byte[] serialize(String topic, Object data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsBytes(data);
        }catch (Exception ex){
            log.debug(ex.getMessage());
        }
        return  retVal;
    }

    @Override
    public void close(){

    }
}
