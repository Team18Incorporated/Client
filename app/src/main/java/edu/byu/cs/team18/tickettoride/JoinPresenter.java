package edu.byu.cs.team18.tickettoride;

import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.Common.*;

/**
 * Created by Tesla on 10/9/2017.
 */

public class JoinPresenter implements Observer{

    public static JoinPresenter instance = new JoinPresenter();
    private JoinFragment view;
    private AuthToken userAuthToken;

    private JoinPresenter(){}


    public void setView(JoinFragment viewIn){
        view = viewIn;
        ClientModel.SINGLETON.observerRegister(this);
    }


    public GameList getStartedGamesList()
    {
        return ClientModel.SINGLETON.getStartedGamesList();
    }

    public GameList getJoinedGamesList()
    {
        return ClientModel.SINGLETON.getJoinedGamesList();
    }

    public GameList getJoinableGamesList()
    {
        return ClientModel.SINGLETON.getJoinableGamesList();
    }

    public void updateView()
    {
        view.refreshView();
    }


    /*joinGame joins or starts the selected game if it is able
    *
    * @pre valid gameID
    * @post plays game if the game has already started, joins the game lobby otherwise
    * */
    public void joinGame(String gameID) throws Exception
    {
        GameInfo selectedGame = ClientModel.SINGLETON.getGame(gameID);

        if(selectedGame!=null)
        {
            if(selectedGame.hasStarted())
            {
                throw new Exception("Game in progress");
            }
            else
            {
                ClientModel.SINGLETON.setCurrentLobby(selectedGame);
            }
        }
        else {
            throw new Exception("Game doesn't exist");
        }
    }

    /*
    clears view and stops observing ClientModel
    @Pre: none
    @Post: view = null
     */
    public void clearView(){
        view = null;
        ClientModel.SINGLETON.observerRemove(this);
    }
    @Override
    public void update(Observable observable, Object o) {
        updateView();
        if (o!=null && o instanceof GameInfo){
            view.joinLobby();
        }
    }
}
