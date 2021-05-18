package com.melon.kafkademo.kafka;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "kafka")
public class TopicProperties {

    private List<Topic> topics;

    @Setter
    @Getter
    @ToString
    static class Topic {

        String name;
        Integer numPartitions = 3;
        Short replicationFactor = 1;


        NewTopic toNewTopic() {
            return new NewTopic(this.name, this.numPartitions, this.replicationFactor);
        }
    }
}
