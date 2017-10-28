package edu.byu.cs.team18.tickettoride;

import android.app.Activity;
import android.widget.Toast;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.ChatMessage;

/**
 * Created by abram on 10/28/2017.
 */

public class ChatPresenter {
    private static ChatPresenter chatPresenter = null;
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
            ServerProxy.getServerProxy().sendChat(authToken,myMessage);
        }
    }

}

