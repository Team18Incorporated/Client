package edu.byu.cs.team18.tickettoride;


import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.Common.*;
import edu.byu.cs.team18.tickettoride.Common.Commands.CreateCommand;

/**
 * Created by Solomons on 10/7/2017.
 */

public class JoinedGamesPresenter implements Observer{


    //NEEDS TO BE MADE AN OBSERVER

    public static JoinedGamesPresenter instance = new JoinedGamesPresenter();
    private JoinedGamesFragment view;
    private AuthToken userAuthToken;

    private JoinedGamesPresenter(){}


    public void setView(JoinedGamesFragment viewIn){
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
    public void joinGame(String gameID) throws Exception
    {
        GameInfo selectedGame = ClientModel.SINGLETON.getGame(gameID);

        if(selectedGame!=null)
        {
            if(selectedGame.hasStarted())
            {
                //NEED TO KNOW HOW TO PLAY
                //playGame(selectedGame.getGameID());
                //throw new Exception("Game in progress");
                ClientModel.SINGLETON.setCurrentLobby(selectedGame);


            }
            else
            {
                ClientModel.SINGLETON.setCurrentLobby(selectedGame);
            }
        }
        else
        {
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

    public void create(){
        User user = ClientModel.SINGLETON.getCurrentUser();
        StringBuilder name  = new StringBuilder(user.getUsername());
        name.append("'s game");
        /*CreateCommand createCommand = new CreateCommand(name.toString(), user.getAuthToken());
        CreateAsyncTask createAsyncTask = new CreateAsyncTask();
        createAsyncTask.execute(createCommand);*/
        ClientModel.SINGLETON.setCurrentLobby(ServerProxy.getServerProxy().newGame(user.getAuthToken(), name.toString()));
    }



    private class CreateAsyncTask extends AsyncTask<CreateCommand, Void, GameInfo>
    {
        @Override
        protected GameInfo doInBackground(CreateCommand... createCommands) {
            GameInfo newGame = ServerProxy.getServerProxy().newGame(createCommands[0].getToken(), createCommands[0].getUsername());
            return newGame;
        }

        protected void onPostExecute(GameInfo gameInfo)
        {
            ClientModel.SINGLETON.setCurrentLobby(gameInfo);
        }
    }
}
