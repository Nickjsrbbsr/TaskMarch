package org.mandal.chat.kafkaandwebsockets.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

// BackendController.java
@Controller
public class BackendController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @MessageMapping("/hello/{id}")
    public void greeting(@DestinationVariable String id, String message) {
        try {
            // Send message to Kafka topic dynamically based on id
            String topic = "wikimedia-stream";
            String payload = id + "&&&&&" + message;
            kafkaTemplate.send(topic, payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
