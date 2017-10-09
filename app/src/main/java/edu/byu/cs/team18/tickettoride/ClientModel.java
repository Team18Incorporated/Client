package edu.byu.cs.team18.tickettoride;


import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.Common.*;
/**
 * Created by Solomons on 10/7/2017.
 */

public class ClientModel extends Observable{

    private Game currentGame;
    private GameInfo currentLobby;
    private GameList joinableGamesList;
    private GameList joinedGamesList;
    private GameList startedGamesList;
    private Player currentPlayer;
    private User currentUser;

    /*
    notifies observers of changes
    @pre: none
    @post: none
     */
    public void echo(){
        setChanged();
        notifyObservers();
    }
    /*
    adds observer for clientModel
    @pre: in != null
    @post: none
     */
    public void observerRegister(Observer in){
        addObserver(in);
    }

    /*
    deletes observer in for clientModel
    @pre: in!= null
    @post: none
     */
    public void observerRemove(Observer in){
        deleteObserver(in);
    }

    public static ClientModel SINGLETON = new ClientModel();

    public GameInfo getCurrentLobby() {return currentLobby;}

    public void setCurrentLobby(GameInfo in) {
        currentLobby = in;
        echo();
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
        echo();
    }

    public GameList getJoinableGamesList() {
        return joinableGamesList;
    }

    public void setJoinableGamesList(GameList joinableGamesList) {
        this.joinableGamesList = joinableGamesList;
        echo();
    }

    public GameList getJoinedGamesList() {
        return joinedGamesList;
    }

    public void setJoinedGamesList(GameList joinedGamesList) {
        this.joinedGamesList = joinedGamesList;
        echo();
    }

    public GameList getStartedGamesList() {
        return startedGamesList;
    }

    public void setStartedGamesList(GameList startedGamesList) {
        this.startedGamesList = startedGamesList;
        echo();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        echo();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        echo();
    }

    public GameInfo getGame(String gameID)
    {
        GameInfo gameInfo = null;
        if(joinableGamesList.getGameByID(gameID)!= null)
        {
           gameInfo=joinableGamesList.getGameByID(gameID);
        }
        else if(joinedGamesList.getGameByID(gameID)!=null)
        {
            gameInfo=joinedGamesList.getGameByID(gameID);
        }
        else if(startedGamesList.getGameByID(gameID)!=null)
        {
            gameInfo=startedGamesList.getGameByID(gameID);
        }

        return gameInfo;
    }
}
