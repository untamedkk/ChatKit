package com.stfalcon.chatkit.sample.features.demo.custom.layout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.MessageContentType;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.stfalcon.chatkit.sample.R;
import com.stfalcon.chatkit.sample.common.data.fixtures.MessagesFixtures;
import com.stfalcon.chatkit.sample.common.data.model.Message;
import com.stfalcon.chatkit.sample.features.demo.DemoMessagesActivity;
import com.stfalcon.chatkit.sample.utils.AppUtils;

public class CustomLayoutMessagesActivity extends DemoMessagesActivity
        implements MessagesListAdapter.OnMessageLongClickListener<Message>,
        MessageInput.InputListener,
        MessageInput.AttachmentsListener,
        MessagesListAdapter.OnMessageViewClickListener {

    public static void open(Context context) {
        context.startActivity(new Intent(context, CustomLayoutMessagesActivity.class));
    }

    private MessagesList messagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout_messages);

        messagesList = (MessagesList) findViewById(R.id.messagesList);
        initAdapter();

        MessageInput input = (MessageInput) findViewById(R.id.input);
        input.setInputListener(this);
        input.setAttachmentsListener(this);
    }

    @Override
    public boolean onSubmit(CharSequence input) {
        messagesAdapter.addToStart(
                MessagesFixtures.getTextMessage(input.toString()), true);
        return true;
    }

    @Override
    public void onAddAttachments() {
        messagesAdapter.addToStart(MessagesFixtures.getImageMessage(), true);
    }

    @Override
    public void onMessageLongClick(Message message) {
        AppUtils.showToast(this, R.string.on_log_click_message, false);
    }

    @Override
    public void onMessageViewClick(View view, IMessage message) {
        if (message instanceof MessageContentType.Image) {
            ShowImageFullScreenActivity.open(this, ((MessageContentType.Image) message).getImageUrl());
        }
    }

    private void initAdapter() {
        MessageHolders holdersConfig = new MessageHolders()
                .setIncomingTextLayout(R.layout.item_custom_incoming_text_message)
                .setOutcomingTextLayout(R.layout.item_custom_outcoming_text_message)
                .setIncomingImageLayout(R.layout.item_custom_incoming_image_message)
                .setOutcomingImageLayout(R.layout.item_custom_outcoming_image_message);

        super.messagesAdapter = new MessagesListAdapter<>(super.senderId, holdersConfig, super.imageLoader);
        super.messagesAdapter.setOnMessageLongClickListener(this);
        super.messagesAdapter.setLoadMoreListener(this);
        super.messagesAdapter.setOnMessageViewClickListener(this);
        messagesList.setAdapter(super.messagesAdapter);
    }
}