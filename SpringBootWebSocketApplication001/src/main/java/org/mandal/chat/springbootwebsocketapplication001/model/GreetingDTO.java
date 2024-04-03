package org.mandal.chat.springbootwebsocketapplication001.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class GreetingDTO {

    @Id
    private Integer id;
    private String name;
    private String status;

}
