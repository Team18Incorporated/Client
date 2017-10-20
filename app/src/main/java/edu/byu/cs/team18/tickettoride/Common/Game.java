package edu.byu.cs.team18.tickettoride.Common;

import java.util.ArrayList;
import java.util.List;



public class Game {

    private List<PlayerInfo> playerList;
    private String gameID; //could make ID's into something other than Strings
    private GameMap map;
    private ArrayList<TrainCard> visibleCards;
    private int numTrainDeck;
    private int numDestinationDeck;
    private int playerTurn;

    //CONSTRUCTOR-----------------------------------------------------------------------------------
    public Game()
    {

    }

    //METHODS---------------------------------------------------------------------------------------


    public List<PlayerInfo> getPlayerList() {
        return playerList;
    }

    public String getGameID() {
        return gameID;
    }

    public GameMap getMap() {
        return map;
    }

    public ArrayList<TrainCard> getVisibleCards() {
        return visibleCards;
    }

    public int getNumTrainDeck() {
        return numTrainDeck;
    }

    public int getNumDestinationDeck() {
        return numDestinationDeck;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }
}
