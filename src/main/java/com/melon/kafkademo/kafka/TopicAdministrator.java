package com.melon.kafkademo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@ConditionalOnProperty(prefix = "ops.server", name = "enabled", havingValue = "true")
@ConditionalOnClass(TopicProperties.class)
public class TopicAdministrator {

    public static final Logger LOGGER = LoggerFactory.getLogger(TopicAdministrator.class);

    private final TopicProperties configurations;
    private final GenericWebApplicationContext context;

    @Autowired
    public TopicAdministrator(TopicProperties configurations, GenericWebApplicationContext genericContext) {
        this.configurations = configurations;
        this.context = genericContext;
    }

    @PostConstruct
    public void init() {
        initializeBeans(configurations.getTopics());
    }

    private void initializeBeans(List<TopicProperties.Topic> topics) {
        Assert.notNull(topics, "topics must not be NULL");
        topics.forEach(t -> {
            context.registerBean(t.name, NewTopic.class, t::toNewTopic);
            LOGGER.info("Create kafka topic: [{}] ", t.name);
        });
    }
}
