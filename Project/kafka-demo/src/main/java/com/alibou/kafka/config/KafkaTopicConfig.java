package com.alibou.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic alibouTopic() {
        return TopicBuilder
                .name("alibou")
                .build();
    }
    @Bean
    public SimpMessagingTemplate simpMessagingTemplate() {
        return new SimpMessagingTemplate(new MessageChannel() {
            @Override
            public boolean send(Message<?> message, long timeout) {
                return false;
            }
        });
    }
}
