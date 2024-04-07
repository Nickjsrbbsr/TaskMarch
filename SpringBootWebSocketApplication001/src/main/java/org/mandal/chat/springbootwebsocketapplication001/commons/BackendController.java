package org.mandal.chat.springbootwebsocketapplication001.commons;

import org.mandal.chat.springbootwebsocketapplication001.model.Greeting;
import org.mandal.chat.springbootwebsocketapplication001.model.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

// BackendController.java
@Controller
public class BackendController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @MessageMapping("/hello")
    public void greetings(HelloMessage message) throws Exception {
        Thread.sleep(100); // Simulated delay
//        kafkaTemplate.send("greetings-topic", HtmlUtils.htmlEscape(message.getId()) + "&&&&&" + HtmlUtils.htmlEscape(message.getName()));
        kafkaTemplate.send("greetings-topic", message.getId() + "&&&&&" + message.getName());
    }

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(100); // simulated delay
//        return new Greeting( HtmlUtils.htmlEscape(message.getId())+"&&&&&"+HtmlUtils.htmlEscape(message.getName()) );
//
//
//    }

//    @MessageMapping("/hello2/{id}")
//    @SendTo("/topic/greeting/{id}")
//    public Greeting greeting(@DestinationVariable String id, @Payload HelloMessage message) {
//        try {
//            // Send message to Kafka topic dynamically based on id
//            String topic = "wikimedia-stream";
//            String payload = id + "&&&&&" + message;
//            kafkaTemplate.send(topic, payload);
//                        return new Greeting( HtmlUtils.htmlEscape(message.getId())+"&&&&&"+HtmlUtils.htmlEscape(message.getName()) +"&&&&&"+HtmlUtils.htmlEscape(message.getStatus()));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }



}
