package edu.byu.cs.team18.tickettoride;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.Common.*;
import edu.byu.cs.team18.tickettoride.Common.Commands.JoinCommand;

/**
 * Created by Tesla on 10/9/2017.
 */

public class JoinPresenter implements Observer{

    public static JoinPresenter instance = new JoinPresenter();
    private JoinFragment view;
    private AuthToken userAuthToken;
    private GameInfo gameToJoin;

    private JoinPresenter(){}


    public void setView(JoinFragment viewIn){
        view = viewIn;
        ClientModel.SINGLETON.observerRegister(this);
    }

    public GameList getJoinableGamesList()
    {
        return ClientModel.SINGLETON.getJoinableGamesList();
    }

    public void updateView()
    {
        if(view.getActivity()!=null) {
            view.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(view!=null)
                    {
                        view.refreshView();
                    }
                }
            });
            //view.refreshView();
        }
    }


    /*joinGame joins or starts the selected game if it is able
    *
    * @pre valid gameID
    * @post plays game if the game has already started, joins the game lobby otherwise
    * */
    public void joinGame(String gameID){
        User user = ClientModel.SINGLETON.getCurrentUser();
        /*JoinCommand joinCommand = new JoinCommand(gameID, user.getAuthToken());
        JoinAsyncTask joinAsyncTask = new JoinAsyncTask();
        joinAsyncTask.execute(joinCommand);*/
        gameToJoin= ServerProxy.getServerProxy().join(user.getAuthToken(), gameID);
        joinCheck();

    }

    public void joinCheck() {

        GameInfo selectedGame = gameToJoin;
        if(ClientModel.SINGLETON.getJoinedGamesList().getGameByID(selectedGame.getGameID())!=null)
        {
            Toast.makeText(view.getActivity().getApplicationContext(), "Already a player in this game", Toast.LENGTH_LONG).show();
        }

        else if(selectedGame!=null)
        {
            if(selectedGame.hasStarted())
            {
                Toast.makeText(view.getActivity().getApplicationContext(), "This game has already started", Toast.LENGTH_LONG).show();
            }
            else if(selectedGame.hasMaxPlayers())
            {
                Toast.makeText(view.getActivity().getApplicationContext(), "This game is full", Toast.LENGTH_LONG).show();
            }
            else
            {
                ClientModel.SINGLETON.setCurrentLobby(selectedGame);
            }
        }
        else {
        Toast.makeText(view.getActivity().getApplicationContext(), "Game Does not exist", Toast.LENGTH_LONG).show();
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

    private class JoinAsyncTask extends AsyncTask<JoinCommand, Void, GameInfo>
    {
        @Override
        protected GameInfo doInBackground(JoinCommand... joinCommands) {
            GameInfo newGame = ServerProxy.getServerProxy().join(joinCommands[0].getToken(), joinCommands[0].getGameID());
            return newGame;
        }

        protected void onPostExecute(GameInfo gameInfo)
        {
            gameToJoin=gameInfo;
            JoinPresenter.instance.joinCheck();
            //ClientModel.SINGLETON.setCurrentLobby(gameInfo);
        }
    }
}
