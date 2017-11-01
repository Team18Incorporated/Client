package edu.byu.cs.team18.tickettoride;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.Common.Commands.StartCommand;
import edu.byu.cs.team18.tickettoride.Common.StartedGameResult;

/**
 * Created by Antman 537 on 10/5/2017.
 */

public class LobbyPresenter implements Observer {
    public static LobbyPresenter instance = new LobbyPresenter();
    private LobbyFragment view;
    private String gameID;

    private LobbyPresenter (){}

    /*
    Sets fragment for the presesnter to contact. Begins observing clientModel
    @Pre: viewIn != null
    @Post: none
     */
    public void setView(LobbyFragment viewIn){
        view = viewIn;
        ClientModel.SINGLETON.observerRegister(this);
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
    /*
    Returns a list of the playerID's in the game
    @Pre: none
    @Post: players != null and players.size <= 5
     */
    public List<String> getPlayers(){
        List<String> players;
        players = ClientModel.SINGLETON.getCurrentLobby().getPlayerNames();
        return players;
    }

    /*
    Tells LobbyFragment to update
    @Pre: none
    @Post: if view!=null, view.refreshView
     */
    public void sendUpdate(){
        if (view!=null){
            view.refreshView();
        }
    }

    public void setGame(String idIn){
        gameID = idIn;
    }

    @Override
    public void update(Observable observable, Object o) {
        updateView();
    }

    private void updateView()
    {
        if(view!=null)
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

    }

    public void start()
    {
        /*StartCommand startCommand= new StartCommand(ClientModel.SINGLETON.getCurrentLobby().getGameID());
        StartAsyncTask startAsyncTask = new StartAsyncTask();
        startAsyncTask.execute(startCommand);*/
        StartedGameResult result = ServerProxy.getServerProxy().startGame
                (ClientModel.SINGLETON.getCurrentLobby().getGameID());
        if(result!=null)
        {
            boolean started= result.hasStarted();
            LobbyPresenter.instance.checkStarted(started, result);
        }

    }

    public void checkStarted(boolean started, StartedGameResult result)
    {
        if(started)
        {
            Toast.makeText(view.getActivity(), "Game Starting", Toast.LENGTH_LONG).show();
            ClientModel.SINGLETON.setCurrentGame(result.getGame());
            view.launchGame();
            clearView();
        }
        else
        {
            Toast.makeText(view.getActivity(), "Not Enough Players", Toast.LENGTH_LONG).show();
        }
    }


}
