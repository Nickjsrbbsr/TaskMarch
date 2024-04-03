package org.mandal.chat.springbootwebsocketapplication001.model;

//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@AllArgsConstructor
@Getter
@Setter

public class Greeting {
    @Getter
    private String content;
//    private String id;
//    private String name;

    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }
//    public Greeting(String id , String name){
//        this.id = id;
//        this.name=name;
//    }

}
