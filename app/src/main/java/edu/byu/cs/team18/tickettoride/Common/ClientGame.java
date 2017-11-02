package edu.byu.cs.team18.tickettoride.Common;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;


public class ClientGame {

    private Player currentPlayer;
    private List<PlayerInfo> playerList;
    private String gameID; //could make ID's into something other than Strings
    private GameMap map;
    private ArrayList<TrainCard> visibleCards;
    private int numTrainDeck;
    private int numDestinationDeck;
    private int playerTurn;
    private ChatHistory chatHistory;
    private ArrayList<ICommand> gameHistory;
    private boolean startStatus=false;

    //CONSTRUCTOR-----------------------------------------------------------------------------------
    public ClientGame()
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

    public boolean getStartStatus() {
        return startStatus;
    }

    public void setStartStatus(boolean startStatus) {
        this.startStatus = startStatus;
    }

    public void addToGameHistory(List<ICommand> list)
    {
        gameHistory.addAll(list);
    }

    public void addToGameHistory(ICommand command)
    {
        gameHistory.add(command);
    }

    public ArrayList<ICommand> getGameHistory() {
        return gameHistory;
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }
}
