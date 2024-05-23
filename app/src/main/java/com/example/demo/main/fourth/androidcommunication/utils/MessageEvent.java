package com.example.demo.main.fourth.androidcommunication.utils;

import java.io.Serializable;

/**
 * Author: Eccentric
 * Created on 2024/5/22 18:29.
 * Description: com.example.demo.main.fourth.androidcommunication.utils.MessageEvent
 */
public class MessageEvent implements Serializable {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
