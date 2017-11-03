package edu.byu.cs.team18.tickettoride;

import android.app.Activity;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.ChatHistory;
import edu.byu.cs.team18.tickettoride.Common.ChatMessage;
import edu.byu.cs.team18.tickettoride.GameView.GameViewFragment;

/**
 * Created by abram on 10/28/2017.
 */

public class ChatPresenter implements Observer {
    private static ChatPresenter chatPresenter;
    private ChatFragment view = null;

    private ChatPresenter(){}

    public static ChatPresenter getChatPresenter()
    {
        if(chatPresenter == null)
        {
            chatPresenter = new ChatPresenter();
        }
        return chatPresenter;
    }

    public void setView(ChatFragment in){
        view = in;
        ClientModel.SINGLETON.observerRegister(this);
    }


    private boolean canDo(ChatMessage myMessage, Activity A)
    {
        if(myMessage.getMessage().length() == 0)
        {
            Toast.makeText(A, "Please type a message.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void presentChat(ChatMessage myMessage, Activity A, AuthToken authToken)
    {
        if(canDo(myMessage,A))
        {
            ServerProxy.getServerProxy().sendChat(authToken,myMessage,ClientModel.SINGLETON.getCurrentGame().getGameID());
        }
    }

    public ChatHistory getChatHistory()
    {
        return ClientModel.SINGLETON.getCurrentGame().getChatHistory();
    }


    @Override
    public void update(Observable observable, Object o) {
        updateView();
        //todo: add conditions
    }

    private void updateView()
    {
        if(view!=null)
        {
            if(view.getActivity()!=null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(view!=null)
                        {
                            view.refreshView();
                        }
                    }
                });

            }//view.refreshView();
        }

    }

}

