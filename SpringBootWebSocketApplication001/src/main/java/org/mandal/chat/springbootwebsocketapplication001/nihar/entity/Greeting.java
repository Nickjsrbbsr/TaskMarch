package org.mandal.chat.springbootwebsocketapplication001.nihar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class Greeting {

    @Id
    private Integer id;
    private String name;
    private String status;

}
