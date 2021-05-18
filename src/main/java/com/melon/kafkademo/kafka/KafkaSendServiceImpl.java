package com.melon.kafkademo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public class KafkaSendServiceImpl implements KafkaSendService {

    public static final Logger LOGGER = LoggerFactory.getLogger(KafkaSendServiceImpl.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final TopicProperties topicProperties;

    @Autowired
    public KafkaSendServiceImpl(KafkaTemplate<String, String> kafkaTemplate, TopicProperties topicProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicProperties = topicProperties;

    }

    @Override
    public void sendMessage(String topic, String message) {
        if (!topicProperties.getTopics().stream().anyMatch(t -> t.getName().equals(topic))) {
            LOGGER.error("Topic not exists; topic :[{}]", topic);
            throw new RuntimeException("Topic not exists ,add topic :[{}] to config file");
        }
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, String.valueOf(System.currentTimeMillis()), message);
        send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOGGER.error("message send failed, topic: [{}] ,message: [{}]", topic, message, throwable);
                throw new RuntimeException("Message send failed", throwable);
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                LOGGER.info("message send success, sendresult :[{}]", stringStringSendResult.toString());
            }
        });
    }
}
