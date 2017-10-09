package com.example.abram.phase1main;

import com.example.abram.phase1main.Commands.CreateCommand;
import com.example.abram.phase1main.Commands.LoginCommand;
import com.example.abram.phase1main.Commands.RegisterCommand;
import com.example.abram.phase1main.Commands.StartCommand;
import com.example.abram.phase1main.Commands.UpdateInProgressCommand;
import com.example.abram.phase1main.Commands.UpdateOpenCommand;
import com.example.abram.phase1main.Commands.UpdateUnstartedCommand;
import com.example.abram.phase1main.ModelClasses.AuthToken;
import com.example.abram.phase1main.ModelClasses.GameInfo;

/**
 * Created by abram on 10/9/2017.
 */

public class ServerProxy implements IServer {

    private static ServerProxy serverProxy = null;

    private ServerProxy(){}

    public static ServerProxy getServerProxy()
    {
        if(serverProxy == null)
        {
            serverProxy = new ServerProxy();
        }
        return serverProxy;
    }



    /*
	 * logs user in and returns authToken. Throws login exceptions
	 * @Pre: user&&password !=null && != ""
	 * @Post: 0 < int authToken < 10000 || exception
	 */
    public AuthToken userLogin(String user, String password)
    {
        return (AuthToken) ClientCommunicator.getClientCommunicator().send(new LoginCommand(user, password));
    }
    /*
     * Registers a new user and logs them in. Returns authToken
     * Throws existingUser exception
     * @Pre: user&&password != null && != ""
     * @Post: 0 < int authToken < 10000 || existingUser exception
     */
    public AuthToken registerUser(String user, String password)
    {
        return (AuthToken) ClientCommunicator.getClientCommunicator().send(new RegisterCommand(user, password));
    }
    /*
     * creates a new game, using authToken to determine the creator. Returns gameID
     * @Pre: 0 < authToken < 10000
     * @Post: 0 < int gameID < 10000
     */
    public GameInfo newGame(AuthToken authToken, String name)
    {
        return (GameInfo) ClientCommunicator.getClientCommunicator().send(new CreateCommand(name, authToken));
    }
    /*
     * adds authToken user to gameID game
     * @Pre: 0 < authToken&&gameID < 10000
     * @Post: None
     */
    public void join(AuthToken authToken, String gameID)
    {

    }
    /*
     * removes authToken user from gameID game
     * @Pre: 0 < authToken&&gameID < 10000
     * @Post: None
     */
    public void leave(AuthToken authToken, String gameID)
    {

    }
    /*
     * returns a list of the join-able games on the server
     * @Pre: None
     * @Post: Object gamesList !=null && isType List<String> of gameIDs
     */
    public Object openGames()
    {
        return ClientCommunicator.getClientCommunicator().send(new UpdateOpenCommand());
    }
    /*
     * returns a list of in-progress games authToken user is currently in
     * @Pre: 0 < authToken < 10000
     * @Post: Object gamesList !=null && isType List<String> of gameIDs
     */
    public Object inProgressGames(AuthToken authToken)
    {
        return ClientCommunicator.getClientCommunicator().send(new UpdateInProgressCommand(authToken));
    }
    /*
     * returns a list of unstarted games authToken user is currently in
     * @Pre: 0 < authToken < 10000
     * @Post: Object gamesList !=null && isType List<String> of gameIDs
     */
    public Object unstartedGames(AuthToken authToken)
    {
        return ClientCommunicator.getClientCommunicator().send(new UpdateUnstartedCommand(authToken));
    }
    /*
     * flags gameID game as started. Initializes game objects for gameID
     * @Pre: 0 < gameID < 10000
     * @Post: None
     */
    public void startGame(String gameID)
    {
        Object o = ClientCommunicator.getClientCommunicator().send(new StartCommand(gameID));
    }
}
