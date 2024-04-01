package org.mandal.chat.springbootwebsocketapplication001.controller;



import org.mandal.chat.springbootwebsocketapplication001.model.Greeting;
import org.mandal.chat.springbootwebsocketapplication001.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
        return new Greeting("Hello BHAI, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
    @MessageMapping("/hello2")
    @SendTo("/topic/greetings")


    public Greeting greeting2(HelloMessage message) throws Exception {
        Thread.sleep(100); // simulated delay
        return new Greeting("Hello BRO!!!!, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @GetMapping("/employee")
    public String employeeForm(){
        return "employee";
    }
    @GetMapping("/hr")
    public String hrForm(){
        return "hr";
    }

}