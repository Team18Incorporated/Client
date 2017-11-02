package edu.byu.cs.team18.tickettoride;


import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.Common.*;


public class ClientModel extends Observable{

    private Game currentGame;
    private GameInfo currentLobby;
    private GameList joinableGamesList = new GameList();
    private GameList joinedGamesList = new GameList();
    private GameList startedGamesList = new GameList();
    private Player currentPlayer;
    private Player selectedPlayer;
    private User currentUser;
    private Route currentRoute;
    private Date latestDate;

    private PollerAsyncTask poller = new PollerAsyncTask();

    private ClientModel (){
    }

    /*
    notifies observers of changes
    @pre: none
    @post: none
     */
    public void echo(Object arg){
        setChanged();
        notifyObservers(arg);
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

    public void setLatestDate(Date date){
        this.latestDate = date;
    }

    public Date getLatestDate(){
        return latestDate;
    }

    public GameInfo getCurrentLobby() {return currentLobby;}

    public void setCurrentLobby(GameInfo in) {
        currentLobby = in;
        echo(in);
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
        echo(currentGame);
    }

    public GameList getJoinableGamesList() {
        return joinableGamesList;
    }

    public void setJoinableGamesList(GameList joinableGamesList) {
        this.joinableGamesList = joinableGamesList;
        echo(joinableGamesList);
    }

    public GameList getJoinedGamesList() {
        return joinedGamesList;
    }

    public void setJoinedGamesList(GameList joinedGamesList) {
        this.joinedGamesList = joinedGamesList;
        echo(joinedGamesList);
    }

    public GameList getStartedGamesList() {
        return startedGamesList;
    }

    public void setStartedGamesList(GameList startedGamesList) {
        this.startedGamesList = startedGamesList;
        echo(startedGamesList);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        echo(currentPlayer);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        echo(currentUser);
        //if(poller.getStatus() != AsyncTask.Status.RUNNING)
        poller.execute();
    }

    public Route getCurrentRoute() {
        return currentRoute;
    }

    public void setCurrentRoute(Route currentRoute) {
        this.currentRoute = currentRoute;
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
