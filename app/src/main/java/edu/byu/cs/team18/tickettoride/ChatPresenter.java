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
 * This class is the presenter that works with the chat view and the client model.
 */

public class ChatPresenter implements Observer {
    private static ChatPresenter chatPresenter;
    private ChatFragment view = null;

    /**
     * Creates the ChatPresenter object that will serve as the singleton.
     */
    private ChatPresenter(){}

    /**
     * <p>Implements singleton pattern. Returns the singleton if one
     * exists, and creates the singleton if there is none.</p>
     * @return the ChatPresenter singleton
     */
    public static ChatPresenter getChatPresenter()
    {
        if(chatPresenter == null)
        {
            chatPresenter = new ChatPresenter();
        }
        return chatPresenter;
    }

    /**
     * <p>Sets up the model-view-presenter pattern by setting the view that the
     * singleton is working with, and registers the presenter as an observer, with the
     * client model as the observable.</p>
     * @pre "in" is not null.
     * @post Presenter is given the correct view to work with and presenter is set as an observer of the model.
     * @param in -- The view (ChatFragment) that the presenter is dealing with.
     */
    public void setView(ChatFragment in){
        view = in;
        ClientModel.SINGLETON.observerRegister(this);
    }

    /**
     * <p>Checks to see if the user's chat message can be sent or not, i.e., whether or not it is invalid.
     * Displays a toast to the user if the message is not valid</p>
     * @param myMessage - The ChatMessage object made by the user.
     * @param A - The activity containing the chat, where the failure toast will be displayed.
     * @pre myMessage != null
     * @pre A != null
     * @post rejects empty messages and returns true for valid ones.
     * @return true if the message is valid, false otherwise, i.e., if it is empty.
     */
    private boolean canDo(ChatMessage myMessage, Activity A)
    {
        if(myMessage.getMessage().length() == 0)
        {
            Toast.makeText(A, "Please type a message.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * <p>Calls canDo to see if the input is valid. If it is, sends the message to the server
     * proxy so that it can be sent to the server. Otherwise, it does nothing.</p>
     * @param myMessage - The ChatMessage object made by the user.
     * @param A- The activity containing the chat, where the failure toast will be displayed.
     * @param authToken - The unique authToken corresponding to the current user's current session.
     * @pre myMessage != null
     * @pre A != null
     * @pre authToken != null
     * @pre ID of the current game is not null.
     * @post sends the message, authtoken, and current game's ID to the server if canDo is true.
     */
    public void presentChat(ChatMessage myMessage, Activity A, AuthToken authToken)
    {
        if(canDo(myMessage,A))
        {
            ServerProxy.getServerProxy().sendChat(authToken,myMessage,ClientModel.SINGLETON.getCurrentGame().getGameID());
        }
    }

    /**
     * <p>Gets the current game's chat history from the model so that it can be displayed in the chat view.</p>
     * @return the ChatHistory of the current game.
     */
    public ChatHistory getChatHistory()
    {
        return ClientModel.SINGLETON.getCurrentGame().getChatHistory();
    }

    /**
     * <p>Gets changes in the model (observable) and calls updateView.</p>
     * @param observable - The client model
     * @param o - The object being changed (ChatHistory)
     * @pre observable != null
     * @pre o != null
     * @post Correctly updates the view with changes made to the model.
     */
    @Override
    public void update(Observable observable, Object o) {
        updateView();
        //todo: add conditions
    }

    /**
     * <p>Updates the view (ChatFragment) to which the presenter singleton is attached.</p>
     * @pre view != null
     * @pre view.getActivity() != null
     * @post updates the view (ChatFragment) with the changes made to the model
     */
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

