package com.example.springbootapplicationtc1.model;

import lombok.Getter;
import org.springframework.stereotype.Component;
@Getter
public class Message {

    private String messageText;

    public Message(String messageText) {
        this.messageText = messageText;
    }
}
