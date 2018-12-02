package com.stfalcon.chatkit.sample.common.data.model;

import android.support.annotation.Nullable;

import com.stfalcon.chatkit.commons.models.MessageContentType;

import java.util.Date;

public class ImageMessage extends Message implements MessageContentType.Image {

    private String imageUrl;

    public ImageMessage(String id, User user, String text) {
        super(id, user, text);
    }

    public ImageMessage(String id, User user, String text, Date createdAt) {
        super(id, user, text, createdAt);
    }

    @Nullable
    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
