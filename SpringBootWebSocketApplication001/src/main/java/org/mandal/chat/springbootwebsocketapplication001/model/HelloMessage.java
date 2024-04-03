package org.mandal.chat.springbootwebsocketapplication001.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter


public class HelloMessage {


    private String id;

    private String name;
    private String status = "pendingswe";

    public HelloMessage() {
    }

    public HelloMessage(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public HelloMessage(String name) {
        this.name = name;
    }

}
