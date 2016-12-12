package com.stfalcon.chatkit.features.messages.models;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Date;

/*
 * Created by troy379 on 09.12.16.
 */
public class DefaultMessage implements IMessage {

    public DefaultMessage(long messageId, IUser user, Date createdAt, String text) {
        this.messageId = messageId;
        this.user = user;
        this.createdAt = createdAt;
        this.text = text;
    }

    private long messageId;
    private IUser user;
    private Date createdAt;
    private String text;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String getId() {
        return String.valueOf(messageId);
    }

    public String getText() {
        return text;
    }

    @Override
    public IUser getUser() {
        return user;
    }

    public void setText(String text) {
        this.text = text;
    }
}