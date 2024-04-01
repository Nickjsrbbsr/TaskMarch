package org.mandal.chat.april01websocketapp.controller;

import org.mandal.chat.april01websocketapp.model.Request;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/submitRequest")
    @SendTo("/topic/hrPortal")
    public Request submitRequest(Request request) {
        // Process request and save it in the database
        // For simplicity, just return the same request for now
        return request;
    }

    @MessageMapping("/updateStatus")
    @SendTo("/topic/employeePage")
    public Request updateStatus(Request request) {
        // Update status of the request in the database
        // For simplicity, just return the same request for now
        return request;
    }
}
