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
    private ChatHistory chatHistory;

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

    public void setPlayerList(List<PlayerInfo> playerList) {
        this.playerList = playerList;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public void setVisibleCards(ArrayList<TrainCard> visibleCards) {
        this.visibleCards = visibleCards;
    }

    public void setNumTrainDeck(int numTrainDeck) {
        this.numTrainDeck = numTrainDeck;
    }

    public void setNumDestinationDeck(int numDestinationDeck) {
        this.numDestinationDeck = numDestinationDeck;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public ChatHistory getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ChatHistory chatHistory) {
        this.chatHistory = chatHistory;
    }
}
