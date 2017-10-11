package edu.byu.cs.team18.tickettoride;

import edu.byu.cs.team18.tickettoride.Common.Game;
import edu.byu.cs.team18.tickettoride.Common.GameInfo;
import edu.byu.cs.team18.tickettoride.Common.GameList;
import edu.byu.cs.team18.tickettoride.Common.IClient;
import edu.byu.cs.team18.tickettoride.Common.Player;
import edu.byu.cs.team18.tickettoride.Common.User;

/**
 * Created by abram on 10/9/2017.
 */

public class ClientFacade implements IClient {

    private static ClientFacade clientFacade = null;

    private ClientFacade(){}

    public static ClientFacade getClientFacade()
    {
        if(clientFacade == null)
        {
            clientFacade = new ClientFacade();
        }
        return clientFacade;
    }


    /*updateJoinGameList updates the gameList of joinable games
    *
    * @pre gameList cannot be null
    * @post will update the JoinableGames GameList on the Client to match the incoming GameList
    * */
    public void updateJoinGameList(GameList gameList){
        ClientModel.SINGLETON.setJoinableGamesList(gameList);
    }

    /*updateCurrentGamesList updates list of game the player is currently in
    *
    * @pre gameList cannot be null
    * @post will update the PlayerGames GameList on the Client to match the incoming GameList
    * */
    public void updateCurrentGamesList(GameList gameList)
    {
        ClientModel.SINGLETON.setJoinedGamesList(gameList);
    }

    /*updateUser updates the info stored in the client about the current user

    * @pre user cannot be null
    * @post stored User info will be updated
    * */
    public void updateUser(User user)
    {
        ClientModel.SINGLETON.setCurrentUser(user);
    }

    /*updatePlayer updates info about the current Player

    *@pre player cannot be null
    *@post Player in the client will be updated
    * */
    public void updatePlayer(Player player)
    {
        ClientModel.SINGLETON.setCurrentPlayer(player);
    }

    /*updateGame updates the current game in the client

    * @pre game cannot be null
    * @post current game will be updated
    *
    * */
    public void updateGame(Game game)
    {
        ClientModel.SINGLETON.setCurrentGame(game);
    }

    /*updateGame updates parts of the game without passing over the whole Game object

    * @pre gameInfo cannot be null. CurrentGame on the client cannot be null
    * @post CurrentGame will be updated
    * */
    public void updateGame(GameInfo gameInfo)
    {

    }

}
