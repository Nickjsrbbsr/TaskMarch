package com.deploy.model;

public class Greeting {
    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }

    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
