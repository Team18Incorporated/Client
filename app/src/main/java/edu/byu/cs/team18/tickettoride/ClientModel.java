package edu.byu.cs.team18.tickettoride;

/**
 * Created by Solomons on 10/7/2017.
 */

public class ClientModel {

    private Game currentGame;
    private GameList joinableGamesList;
    private GameList joinedGamesList;
    private GameList startedGamesList;
    private Player currentPlayer;
    private User currentUser;

    //needs Observable


    public static ClientModel SINGLETON = new ClientModel();

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public GameList getJoinableGamesList() {
        return joinableGamesList;
    }

    public void setJoinableGamesList(GameList joinableGamesList) {
        this.joinableGamesList = joinableGamesList;
    }

    public GameList getJoinedGamesList() {
        return joinedGamesList;
    }

    public void setJoinedGamesList(GameList joinedGamesList) {
        this.joinedGamesList = joinedGamesList;
    }

    public GameList getStartedGamesList() {
        return startedGamesList;
    }

    public void setStartedGamesList(GameList startedGamesList) {
        this.startedGamesList = startedGamesList;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public GameInfo getGame(int gameID)
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
