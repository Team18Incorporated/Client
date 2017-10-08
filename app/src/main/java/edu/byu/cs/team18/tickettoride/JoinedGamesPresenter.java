package edu.byu.cs.team18.tickettoride;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Solomons on 10/7/2017.
 */

public class JoinedGamesPresenter {


    //NEEDS TO BE MADE AN OBSERVER

    public static JoinedGamesPresenter instance = new JoinedGamesPresenter();
    private JoinedGamesFragment view;
    private AuthToken userAuthToken;

    private JoinedGamesPresenter(){}


    public void setView(JoinedGamesFragment viewIn){
        view = viewIn;
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
    public void joinGame(int gameID)
    {
        GameInfo selectedGame = ClientModel.SINGLETON.getGame(gameID);

        if(selectedGame!=null)
        {
            if(selectedGame.hasStarted())
            {
                //NEED TO KNOW HOW TO PLAY
                playGame(selectedGame.getGameID());
            }
            else
            {
                //NEEDS TO SWITCH TO LOBBY VIEW
                joinGame(selectedGame);
            }
        }
    }
}
