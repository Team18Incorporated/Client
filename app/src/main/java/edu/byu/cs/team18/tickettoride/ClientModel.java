package edu.byu.cs.team18.tickettoride;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.Common.*;
import edu.byu.cs.team18.tickettoride.Common.Commands.CommandList;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.States.IState;
import edu.byu.cs.team18.tickettoride.States.NotTurnState;


public class ClientModel extends Observable{

    private ClientGame currentGame;
    private GameInfo currentLobby;
    private GameList joinableGamesList = new GameList();
    private GameList joinedGamesList = new GameList();
    private GameList startedGamesList = new GameList();
    private Player currentPlayer;
    private Player selectedPlayer;
    private User currentUser;
    private Route currentRoute;
    private Route lastRoute;
    private Date latestDate;
    private IState state = NotTurnState.SINGLETON;
    private int commandIndex=-1;
    private boolean lastRound=false;

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

        if(latestDate==null)
        {
            latestDate=new Date();
        }
        return latestDate;
    }

    public GameInfo getCurrentLobby() {return currentLobby;}

    public void setCurrentLobby(GameInfo in) {
        currentLobby = in;
        echo(in);
    }

    public void setTrainDeckSize(int i)
    {
        currentGame.setNumTrainDeck(i);
        echo(null);
    }

    public ClientGame getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(ClientGame currentGame) {
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
        echo(null);
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

    public void claimRoute(String gameID, String playerID, Route routeIn)
    {
       /* //Add route to player's list
        route.setOwner(playerID);
        getCurrentPlayer().getClaimedRoutes().add(route);
        //Subtract length of route from number of player's train pieces
        getCurrentPlayer().setNumTrainPieces(ClientModel.SINGLETON.getCurrentPlayer().getNumTrainPieces() - route.getLength());
        getCurrentPlayer().setNumTrainPieces(getCurrentPlayer().getNumTrainPieces()-route.getLength());*/
        Route route=null;
        for(Route r : getCurrentGame().getMap().getRouteList())
        {
            if(r.equals(routeIn))
            {
                route = r;
            }
        }
        route.setOwner(playerID);
        if (getCurrentPlayer().getPlayerID().equals(playerID))
        {
            getCurrentPlayer().getClaimedRoutes().add(route);
        }
        echo(route);

    }

    public boolean claimRouteCheck()
    {
        return state.claimRouteCheck();
    }

    public void updateChatHistory(ChatHistory chatHistory)
    {
        getCurrentGame().getChatHistory().clear();
        getCurrentGame().getChatHistory().getHistory().addAll(chatHistory.getHistory());
        echo(null);
    }

    public void updateFaceUp(List<TrainCard> list)
    {
        getCurrentGame().setVisibleCards((ArrayList)list);
        echo(null);
    }

    public void updatePlayerTurn(int index)
    {
        getCurrentGame().setPlayerTurn(index);
        echo(null);
    }


    public void showDestinationChoices(List<DestinationCard> list) {
        getCurrentPlayer().setDestinationCardChoices(list);
        echo(list);
    }

    public void setLastRoute(Route route){
        lastRoute = route;
    }
    public Route getLastRoute(){
        return lastRoute;

    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public boolean drawFaceUp(int index)
    {
        return state.drawFaceUp(index);
    }

    public boolean drawTrainCard()
    {
        return state.drawFromDeck();
    }
    public boolean drawDestinationCards()
    {
        return state.drawDestinationCards();
    }

    public List<PlayerInfo> getScores(){
        List<PlayerInfo> sortedScores = new ArrayList<>();
        List<PlayerInfo> players = currentGame.getPlayerList();
        for (int i=0; i<players.size(); i++){
            for (int j=0; j<=sortedScores.size(); j++){
                if (j==sortedScores.size() || sortedScores.get(j).getPoints()<players.get(i).getPoints()){
                    sortedScores.add(j,players.get(i));
                }
            }
        }
        return sortedScores;
    }

    public int getCommandIndex() {
        return commandIndex;
    }

    public void setCommandIndex(int commandIndex) {
        this.commandIndex = commandIndex;
    }

    public void lastRound()
    {
        lastRound=true;
        echo(lastRound);
    }

    public boolean getLastRound(){
        return lastRound;
    }

    public void addToGameHistory(List<ICommand> list)
    {
        currentGame.addToGameHistory(list);
    }
    public void addToGameHistory(ICommand command)
    {
        currentGame.addToGameHistory(command);
    }

    public ArrayList<ICommand> getGameHistory()
    {
        return currentGame.getGameHistory();
    }
}
