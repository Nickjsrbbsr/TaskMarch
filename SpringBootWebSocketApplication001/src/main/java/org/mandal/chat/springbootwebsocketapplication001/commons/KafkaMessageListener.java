package org.mandal.chat.springbootwebsocketapplication001.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

// KafkaMessageListener.java
@Component
public class KafkaMessageListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @KafkaListener(groupId = "myGroup", topics = "wikimedia-stream")
    public void consumeMsg(String message) {
        // Extract ID from message, assuming it's the first part of the message
        String[] parts = message.split("&&&&&");
        if (parts.length >= 2) {
            String id = parts[0];
            String data = parts[1];

            String destination = "/topic/greeting/" + id;
            simpMessagingTemplate.convertAndSend(destination, data);
        }
    }
}