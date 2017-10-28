package edu.byu.cs.team18.tickettoride;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.byu.cs.team18.tickettoride.Common.ChatHistory;

/**
 * Created by abram on 10/27/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder>{
    private Context C;
    private ChatHistory chatHistory;

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(C);
        View view = inflater.inflate(R.layout.chat_item, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.getName().setText(chatHistory.getHistory().get(position).getPlayerName());
        holder.getMessage().setText(chatHistory.getHistory().get(position).getMessage());
        holder.getMessage().setText(chatHistory.getHistory().get(position).getTime().toString());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public ChatAdapter(Context c, ChatHistory chatHistory) {
        C = c;
        this.chatHistory = chatHistory;
    }
}
