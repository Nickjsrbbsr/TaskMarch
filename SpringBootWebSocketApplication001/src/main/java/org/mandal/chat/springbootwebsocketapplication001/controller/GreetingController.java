package org.mandal.chat.springbootwebsocketapplication001.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.mandal.chat.springbootwebsocketapplication001.model.Greeting;
import org.mandal.chat.springbootwebsocketapplication001.model.HelloMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(100); // simulated delay
        return new Greeting("Hello BHAI, " + HtmlUtils.htmlEscape(message.getName()) +"&"+HtmlUtils.htmlEscape(message.getId())+ "!");
    }
//    @MessageMapping("/hello2")
//    @SendTo("/topic/greetings")
//    public HelloMessage greeting2(HelloMessage message) {
//        // Create an instance of ObjectMapper
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // Create a sample Java object
//        HelloMessage helloMessage = new HelloMessage(message.getId(),message.getName());
//
//        try {
//            // Convert Java object to JSON string
//            String jsonString = objectMapper.writeValueAsString(helloMessage);
//            System.out.println(jsonString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("hello");
//        try{
//        Thread.sleep(100); // simulated delay
//        System.out.println("Hello BRO!!!!, " + HtmlUtils.htmlEscape(message.getName()) + message.getId() + "!");
//
//        return message;
//    } catch (Exception e){
//            e.printStackTrace();;
//        }
//        return  null;
//    }

    @MessageMapping("/hello2/{id}")
    @SendTo("/topic/greeting/{id}")
    public Greeting greeting2(@Payload HelloMessage message, @DestinationVariable String id) {
        System.out.println("hello I am called  greeting2 ");
        try {
            Thread.sleep(100); // simulated delay
            System.out.println("Hello BRO2!!!! Take O2, " + HtmlUtils.htmlEscape(message.getName()) +message.getId()+ " "  + "!");

            return new Greeting( HtmlUtils.htmlEscape(message.getName()) +"_$_"+ message.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping("/employee")
    public String employeeForm(){
        return "employee9";
    }
    @GetMapping("/hr")
    public String hrForm(){
        return "hr9";
    }



}