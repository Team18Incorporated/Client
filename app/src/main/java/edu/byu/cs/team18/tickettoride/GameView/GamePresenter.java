package edu.byu.cs.team18.tickettoride.GameView;

import android.graphics.Point;
import android.widget.ImageView;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.DestinationCard;
import edu.byu.cs.team18.tickettoride.Common.Player;
import edu.byu.cs.team18.tickettoride.Common.Route;

/**
 * Created by Antman 537 on 10/25/2017.
 */

public class GamePresenter {
    public static GamePresenter SINGLETON = new GamePresenter();


    private GamePresenter (){}

    /*
    draws a card from the deck and adds it to the active player's hand
    @pre: none
    @post: none
     */
    public void drawCard(){
        //todo: connect to model
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

}
