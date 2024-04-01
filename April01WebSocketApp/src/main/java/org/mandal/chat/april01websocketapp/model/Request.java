package org.mandal.chat.april01websocketapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Request {
    private int id;
    private String name;
    private String status;

    // Getters and setters
}

