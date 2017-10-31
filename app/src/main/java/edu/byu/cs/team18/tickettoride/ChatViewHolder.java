package edu.byu.cs.team18.tickettoride;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import edu.byu.cs.team18.tickettoride.Common.ChatMessage;

/**
 * Created by abram on 10/27/2017.
 */

public class ChatViewHolder extends RecyclerView.ViewHolder{
    private ChatMessage chatMessage;
    private TextView name;
    private TextView message;
    private TextView date;


    public ChatViewHolder(View v) {
        super(v);
        name = (TextView) v.findViewById(R.id.chat_name_text_view);
        message = (TextView) v.findViewById(R.id.chat_message_text_view);
        date = (TextView) v.findViewById(R.id.chat_date_text_view);
    }

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getMessage() {
        return message;
    }

    public void setMessage(TextView message) {
        this.message = message;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }
}
