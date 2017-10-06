package edu.byu.cs.team18.tickettoride;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antman 537 on 10/5/2017.
 */
//TODO: make observer to model

public class LobbyPresenter {
    public static LobbyPresenter instance = new LobbyPresenter();
    private LobbyFragment view;
    private int gameID;

    private LobbyPresenter (){}

    /*
    Sets fragment for the presesnter to contact
    @Pre: viewIn != null
    @Post: none
     */
    public void setView(LobbyFragment viewIn){
        view = viewIn;
    }
    /*
    clears view
    @Pre: none
    @Post: view = null
     */
    public void clearView(){
        view = null;
    }
    /*
    Returns a list of the playerID's in the game
    @Pre: none
    @Post: players != null and players.size <= 5
     */
    public List<String> getPlayers(){
        List<String> players = new ArrayList<>();
        //TODO: call model fetchPlayers
        return players;
    }

    /*
    Tells LobbyFragment to update
    @Pre: none
    @Post: if view!=null, view.refreshView
     */
    public void sendUpdate(){
        if (view!=null){
            view.refreshView();
        }
    }

    public void setGame(int idIn){
        gameID = idIn;
    }
}
