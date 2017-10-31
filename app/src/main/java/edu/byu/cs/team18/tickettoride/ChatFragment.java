package edu.byu.cs.team18.tickettoride;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.ChatHistory;
import edu.byu.cs.team18.tickettoride.Common.ChatMessage;

/**
 * Created by abram on 10/25/2017.
 */

public class ChatFragment extends Fragment{

    private RecyclerView recyclerView;
    private EditText enterChatBar;
    private Button sendButton;
    private ChatAdapter chatAdapter;
    private View view;
    private ChatHistory chatHistory;
    private String playerName;
    private AuthToken authToken;
    private ChatMessage myMessage = new ChatMessage("","","",null);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.chat_recycler_view);
        chatAdapter = new ChatAdapter(getContext(),chatHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(chatAdapter);


        enterChatBar = (EditText) view.findViewById(R.id.chat_bar);
        enterChatBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myMessage.setMessage(s.toString());
                myMessage.setPlayerName(playerName);
                myMessage.setTime(new Date());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        sendButton = (Button) view.findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ChatPresenter.getChatPresenter().presentChat(myMessage,getActivity(),authToken);
                enterChatBar.setText("");
            }
        });

        return view;
    }
}
