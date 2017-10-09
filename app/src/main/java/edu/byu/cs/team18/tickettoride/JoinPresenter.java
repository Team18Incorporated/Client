package edu.byu.cs.team18.tickettoride;

import edu.byu.cs.team18.tickettoride.Common.*;

/**
 * Created by Tesla on 10/9/2017.
 */

public class JoinPresenter {

    public static JoinPresenter instance = new JoinPresenter();
    private JoinFragment view;
    private AuthToken userAuthToken;

    private JoinPresenter(){}


    public void setView(JoinFragment viewIn){
        view = viewIn;
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
    public void joinGame(String gameID)
    {
        GameInfo selectedGame = ClientModel.SINGLETON.getGame(gameID);

        if(selectedGame!=null)
        {
            if(selectedGame.hasStarted())
            {
                //NEED TO KNOW HOW TO PLAY
                //playGame(selectedGame.getGameID());
            }
            else
            {
                //NEEDS TO SWITCH TO LOBBY VIEW
                //joinGame(selectedGame);
            }
        }
    }
}
