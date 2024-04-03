package org.mandal.chat.springbootwebsocketapplication001.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.mandal.chat.springbootwebsocketapplication001.dao.GreetingDAO;
import org.mandal.chat.springbootwebsocketapplication001.model.Greeting;
import org.mandal.chat.springbootwebsocketapplication001.model.HelloMessage;
import org.mandal.chat.springbootwebsocketapplication001.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    GreetingService greetingService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(100); // simulated delay
        return new Greeting( HtmlUtils.htmlEscape(message.getId())+"&&&&&"+HtmlUtils.htmlEscape(message.getName()) );


    }


    @MessageMapping("/hello2/{id}")
    @SendTo("/topic/greeting/{id}")
    public Greeting greeting2(@Payload HelloMessage message, @DestinationVariable String id) {
        System.out.println("hello I am called  greeting2 ");
        try {
            Thread.sleep(100); // simulated delay
            System.out.println( HtmlUtils.htmlEscape(message.getId()) );

            return new Greeting( HtmlUtils.htmlEscape(message.getId())+"&&&&&"+HtmlUtils.htmlEscape(message.getName()) +"&&&&&"+HtmlUtils.htmlEscape(message.getStatus()));
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

    @PostMapping ("/updateData")
    @ResponseBody
    public String updateDataOfStatusInDatabase(@RequestParam String messageId , @RequestParam String name ,@RequestParam String status){
        System.out.println("Put Mapping /updateData is called with id:"+messageId+" as a data and now gonna update the data -> status is :"+status +" and name :"+name);
        try {
            greetingService.feedDataToDB(messageId, name,status) ;

//             GreetingDAO dao = new GreetingDAO();
//             dao.updateStatusToApprovedById(Integer.parseInt(messageId),name,status);
            System.out.println("data updated successfully in the database");
            return "Data saved from /feedData one";
        }catch(Exception e){
            System.out.println("data was not updated  in the database");

            e.printStackTrace();
        }
         return "unable to update the data";
    }
    @PostMapping("/feedData")
    @ResponseBody
    public String feedDataInDatabase(@RequestParam String id,@RequestParam String name,@RequestParam String status ){
        System.out.println("Post Mapping /feedData is called with id:"+id+" as a data and now gonna feed the data");
        try {
            greetingService.feedDataToDB(id,name,status);
            System.out.println("Data saved and greetingService called");
            return "Data saved";
        }catch(Exception e){
            e.printStackTrace();
        }
         return "unable to update the data";
    }

    @PostMapping("/feedData2")
    @ResponseBody
    public String feedDataInDatabase2(@RequestParam String id,@RequestParam String name,@RequestParam String status ){
        System.out.println("Post2 Mapping /feedData is called with id:"+id+" as a data and now gonna feed the data");
        try {
            greetingService.feedDataToDB2(id,name,status);
            System.out.println("Data saved and greetingService2 called");
            return "Data saved from /feedData2";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "unable to update the data";
    }




}