package com.kleistit.farmapi.modules;

import ratpack.guice.ConfigurableModule;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class KafkaConfiguarableModule extends ConfigurableModule<KafkaConfiguarableModule.Config> {

    @Override
    protected void configure() {

    }

    public static class Config {

        private Set<String> servers = new HashSet<>();

        private String clientId = "1";
        private Long maxBlockMillis = TimeUnit.MINUTES.toMillis(1);
        private boolean enabled = true;
        private Integer lingersMs = 0;
        private Integer batchSize = 200;
        private Integer sendBufferBytes = 200;
        private Integer maxInFlightRequestsPerConnection  = 1;
        private Long bufferMemory = 200L;
        private String acks ="1";

        public Config() {
        }

        public Properties getKafkaProperties() {
            servers.add("127.0.0.1:9092");
            Properties props = new Properties();
            props.put("buffer.memory", 1920000000);
            props.put("message.max.bytes", 104857600);
            props.put("max.request.size", 192000000);
            props.put("send.buffer.bytes", 1000000);
            props.put("bootstrap.servers", String.join(",", servers));
            props.put("client.id", clientId);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer" );
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer" );
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("group.id", "1");

            props.put("max.block.ms", maxBlockMillis);
            if (lingersMs != null) {
                props.put("linger.ms", lingersMs);
            }
            if (batchSize != null) {
                props.put("batch.size", batchSize);
            }
            if (sendBufferBytes != null) {
                props.put("send.buffer.bytes", sendBufferBytes);
            }
            if (maxInFlightRequestsPerConnection != null) {
                props.put("max.in.flight.requests.per.connection", maxInFlightRequestsPerConnection);
            }
            if (bufferMemory != null) {
                props.put("buffer.memory", bufferMemory);
            }
            if (acks != null && !acks.isEmpty()) {
                props.put("acks", acks);
            }

            return props;
        }

        public Set<String> getServers() {
            return servers;
        }

        public void setServers(Set<String> servers) {
            this.servers = servers;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public Long getMaxBlockMillis() {
            return maxBlockMillis;
        }

        public void setMaxBlockMillis(Long maxBlockMillis) {
            this.maxBlockMillis = maxBlockMillis;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Integer getLingersMs() {
            return lingersMs;
        }

        public void setLingersMs(Integer lingersMs) {
            this.lingersMs = lingersMs;
        }

        public Integer getBatchSize() {
            return batchSize;
        }

        public void setBatchSize(Integer batchSize) {
            this.batchSize = batchSize;
        }

        public Integer getSendBufferBytes() {
            return sendBufferBytes;
        }

        public void setSendBufferBytes(Integer sendBufferBytes) {
            this.sendBufferBytes = sendBufferBytes;
        }

        public Integer getMaxInFlightRequestsPerConnection() {
            return maxInFlightRequestsPerConnection;
        }

        public void setMaxInFlightRequestsPerConnection(Integer maxInFlightRequestsPerConnection) {
            this.maxInFlightRequestsPerConnection = maxInFlightRequestsPerConnection;
        }

        public Long getBufferMemory() {
            return bufferMemory;
        }

        public void setBufferMemory(Long bufferMemory) {
            this.bufferMemory = bufferMemory;
        }

        public String getAcks() {
            return acks;
        }

        public void setAcks(String acks) {
            this.acks = acks;
        }
    }
}

