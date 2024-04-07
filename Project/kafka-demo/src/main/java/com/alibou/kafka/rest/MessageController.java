package com.alibou.kafka.rest;

//import com.alibou.kafka.payload.Student;
//import com.alibou.kafka.producer.KafkaJsonProducer;
import com.alibou.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaProducer kafkaProducer;
//    private final KafkaJsonProducer kafkaJsonProducer;


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @KafkaListener(topics = "test")
    public void listenAndSendToWebSocket(String message) {
        // Extract necessary information from the message if needed
        // For example, if your message contains id as a part of the payload
        // you can extract it and use it to construct the destination topic dynamically
        String[] parts = message.split("&&&&&");
        if (parts.length >= 3) {
            String id = parts[0]; // Assuming id is the first part of the message
            String name = parts[1];
            String status = parts[2];

            // Construct the destination topic dynamically using the extracted id
            String destinationTopic = "/topic/greeting/" + id;

            // Send message to the WebSocket topic
            simpMessagingTemplate.convertAndSend(destinationTopic, message);
        } else {
            // Handle the case when the message format is incorrect
            System.out.println("Invalid message format: " + message);
        }
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(
            @RequestBody String message
    ) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message queued successfully");
    }
//
//    @PostMapping("/json")
//    public ResponseEntity<String> sendJsonMessage(
//            @RequestBody Student message
//    ) {
//        kafkaJsonProducer.sendMessage(message);
//        return ResponseEntity.ok("Message queued successfully as JSON");
//    }
}
