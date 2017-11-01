package edu.byu.cs.team18.tickettoride.GameView;

import android.graphics.Point;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.CommandList;
import edu.byu.cs.team18.tickettoride.Common.DestinationCard;
import edu.byu.cs.team18.tickettoride.Common.Player;
import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;
import edu.byu.cs.team18.tickettoride.ServerProxy;

/**
 * Created by Antman 537 on 10/25/2017.
 */

public class GamePresenter implements Observer{
    public static GamePresenter SINGLETON = new GamePresenter();
    private GameViewFragment view = null;
    private int testStep=0;

    private GamePresenter (){}

    public void setView(GameViewFragment in){
        view = in;
        ClientModel.SINGLETON.observerRegister(this);
    }
    /*
    clears view and stops observing ClientModel
    @Pre: none
    @Post: view = null
     */
    public void clearView(){
        view = null;
        ClientModel.SINGLETON.observerRemove(this);
    }

    /*
    draws a card from the deck and adds it to the active player's hand
    @pre: none
    @post: none
     */
    public void drawCard(){
        AuthToken token = ClientModel.SINGLETON.getCurrentUser().getAuthToken();
        String id = ClientModel.SINGLETON.getCurrentGame().getGameID();
        CommandList temp = ServerProxy.getServerProxy().drawTrainCard(token,id);
        temp.execute();
    }
    /*
    adds designated DestinationCard to the user's destinations
    @pre: in!=null
    @post: none
     */
    public void addDestination(DestinationCard in){
        //todo: connect to model
    }
    /*
    uses model to determine route pressed and selects
    @pre: pos !=null
    @post:none
     */
    public void selectRoute(Point pos){
        //Todo: connect to model
    }
    /*
    uses model to determine route pressed and selects
    @pre: iv!= null && iv is a car image
    @post:none
     */
    public void selectRoute(Route in){
        ClientModel.SINGLETON.setCurrentRoute(in);
        int largestSet = 0;
        ArrayList<TrainCard> hand = ClientModel.SINGLETON.getCurrentPlayer().getHand();

        //todo: write conditions for claimRoute to appear
    }
    /*
    Claims designated route for currentPlayer;
     */
    public void claimRoute(Route in){
        //todo: implement route class, connect to model
    }
    /*
    counts the given player's pieces
    @pre: player!= null  && player is in game
    @post: 0<=int out<=48 -1 = error
     */
    public int countPieces(Player in){
        int out = -1;
        //Todo: add call to get piece count
        return out;
    }

    public void incrementTest()
    {
        if(testStep==0)
        {

        }
        else if(testStep==1)
        {

        }
        else if(testStep==2)
        {

        }
        else if(testStep==3)
        {

        }
        else if(testStep==4)
        {

        }
        else if(testStep==5)
        {

        }
        else if(testStep==6)
        {

        }
        else if(testStep==7)
        {

        }
        else if(testStep==8)
        {

        }
        else if(testStep==9)
        {

        }
        else if(testStep==10)
        {

        }
    }

    @Override
    public void update(Observable observable, Object o) {

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
                //view.refreshView();
            }
        }

    }
}
