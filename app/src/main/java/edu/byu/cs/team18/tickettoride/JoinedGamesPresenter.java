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
}
