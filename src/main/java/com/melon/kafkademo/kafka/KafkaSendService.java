package com.melon.kafkademo.kafka;

public interface KafkaSendService {
    void sendMessage(String topic, String message);

}
