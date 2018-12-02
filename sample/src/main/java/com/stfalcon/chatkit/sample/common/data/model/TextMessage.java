package com.stfalcon.chatkit.sample.common.data.model;

import java.util.Date;

public class TextMessage extends Message {
    public TextMessage(String id, User user, String text) {
        super(id, user, text);
    }

    public TextMessage(String id, User user, String text, Date createdAt) {
        super(id, user, text, createdAt);
    }
}