package edu.byu.cs.team18.tickettoride;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.Common.Commands.StartCommand;
import edu.byu.cs.team18.tickettoride.Common.Player;
import edu.byu.cs.team18.tickettoride.Common.PlayerInfo;
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

    /*
    sets gameID
    @param: String gameID
    @pre: string!=null && string is actual id
    @post: none
     */
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
            }
        }

    }

    /*
    starts game
    @param: none
    @pre: none
    @post: game is started
     */
    public void start()
    {
        ArrayList<Player> players =ClientModel.SINGLETON.getCurrentLobby().getPlayerList();
        for(int i=0; i<players.size(); i++)
        {
            if(players.get(i).getPlayerName().equals(ClientModel.SINGLETON.getCurrentUser().getUsername()))
            {
                ClientModel.SINGLETON.setCurrentPlayer(players.get(i));
            }
        }
        StartedGameResult result = ServerProxy.getServerProxy().startGame
                (ClientModel.SINGLETON.getCurrentLobby().getGameID(), ClientModel.SINGLETON.getCurrentUser().getAuthToken());
        if(result!=null)
        {
            boolean started= result.hasStarted();
            LobbyPresenter.instance.checkStarted(started, result);
        }

    }

    /*
    checks if game has started
     */
    public void checkStarted(boolean started, StartedGameResult result)
    {
        if(started)
        {
            Toast.makeText(view.getActivity(), "Game Starting", Toast.LENGTH_LONG).show();
            ClientModel.SINGLETON.setCurrentGame(result.getGame());
            ClientModel.SINGLETON.setLatestDate(new Date());
            ClientModel.SINGLETON.setCurrentPlayer(result.getGame().getCurrentPlayer());
            //ClientModel.SINGLETON.getCurrentPlayer().setHand(result.getPlayerHand());
            view.launchGame();
            clearView();
        }
        else
        {
            Toast.makeText(view.getActivity(), "Not Enough Players", Toast.LENGTH_LONG).show();
        }
    }

    /*

     */
    public boolean checkStarted(){
        return ClientModel.SINGLETON.getCurrentLobby().hasStarted();
    }


}
