package edu.byu.cs.team18.tickettoride;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antman 537 on 10/5/2017.
 */

public class LobbyPresenter {
    public static LobbyPresenter instance = new LobbyPresenter();
    private LobbyFragment view;

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
        return players;
    }
}
