package edu.byu.cs.team18.tickettoride;


import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.CreateCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.LoginCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.RegisterCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.StartCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.UpdateInProgressCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.UpdateOpenCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.UpdateUnstartedCommand;
import edu.byu.cs.team18.tickettoride.Common.GameInfo;
import edu.byu.cs.team18.tickettoride.Common.GameList;
import edu.byu.cs.team18.tickettoride.Common.IServer;

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
        return (AuthToken) ClientCommunicator.getSingleton().send("login",new LoginCommand(user, password),AuthToken.class);
    }
    /*
     * Registers a new user and logs them in. Returns authToken
     * Throws existingUser exception
     * @Pre: user&&password != null && != ""
     * @Post: 0 < int authToken < 10000 || existingUser exception
     */
    public AuthToken registerUser(String user, String password)
    {
        return (AuthToken) ClientCommunicator.getSingleton().send("register",new RegisterCommand(user, password),AuthToken.class);
    }
    /*
     * creates a new game, using authToken to determine the creator. Returns gameID
     * @Pre: 0 < authToken < 10000
     * @Post: 0 < int gameID < 10000
     */
    public GameInfo newGame(AuthToken authToken, String name)
    {
        return (GameInfo) ClientCommunicator.getSingleton().send("create",new CreateCommand(name, authToken),GameInfo.class);
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
        return ClientCommunicator.getSingleton().send("updateOpen",new UpdateOpenCommand(), GameList.class);
    }
    /*
     * returns a list of in-progress games authToken user is currently in
     * @Pre: 0 < authToken < 10000
     * @Post: Object gamesList !=null && isType List<String> of gameIDs
     */
    public Object inProgressGames(AuthToken authToken)
    {
        return ClientCommunicator.getSingleton().send("updateInProgress",new UpdateInProgressCommand(authToken),GameList.class);
    }
    /*
     * returns a list of unstarted games authToken user is currently in
     * @Pre: 0 < authToken < 10000
     * @Post: Object gamesList !=null && isType List<String> of gameIDs
     */
    public Object unstartedGames(AuthToken authToken)
    {
        return ClientCommunicator.getSingleton().send("updateUnstarted",new UpdateUnstartedCommand(authToken),GameList.class);
    }
    /*
     * flags gameID game as started. Initializes game objects for gameID
     * @Pre: 0 < gameID < 10000
     * @Post: None
     */
    public void startGame(String gameID)
    {
        Object o = ClientCommunicator.getSingleton().send("start",new StartCommand(gameID),Object.class);
    }
}
