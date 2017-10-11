package edu.byu.cs.team18.tickettoride;


import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.CreateCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.JoinCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.LoginCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.RegisterCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.StartCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.UpdateInProgressCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.UpdateOpenCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.UpdateUnstartedCommand;
import edu.byu.cs.team18.tickettoride.Common.GameInfo;
import edu.byu.cs.team18.tickettoride.Common.GameList;
import edu.byu.cs.team18.tickettoride.Common.IServer;
import edu.byu.cs.team18.tickettoride.Common.StartedGameResult;

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
    @Override
    public AuthToken userLogin(String user, String password)
    {
        return (AuthToken) ClientCommunicator.getSingleton().send("Login",new LoginCommand(user, password),AuthToken.class);
    }
    /*
     * Registers a new user and logs them in. Returns authToken
     * Throws existingUser exception
     * @Pre: user&&password != null && != ""
     * @Post: 0 < int authToken < 10000 || existingUser exception
     */
    @Override
    public AuthToken registerUser(String user, String password)
    {
        return (AuthToken) ClientCommunicator.getSingleton().send("Register",new RegisterCommand(user, password),AuthToken.class);
    }
    /*
     * creates a new game, using authToken to determine the creator. Returns gameID
     * @Pre: 0 < authToken < 10000
     * @Post: 0 < int gameID < 10000
     */
    @Override
    public GameInfo newGame(AuthToken authToken, String name)
    {
        return (GameInfo) ClientCommunicator.getSingleton().send("Create",new CreateCommand(name, authToken),GameInfo.class);
    }
    /*
     * adds authToken user to gameID game
     * @Pre: 0 < authToken&&gameID < 10000
     * @Post: None
     */
    @Override
    public GameInfo join(AuthToken authToken, String gameID)
    {
        return (GameInfo) ClientCommunicator.getSingleton().send("Join",new JoinCommand(gameID,authToken),GameInfo.class);
    }
    /*
     * removes authToken user from gameID game
     * @Pre: 0 < authToken&&gameID < 10000
     * @Post: None
     */
    @Override
    public void leave(AuthToken authToken, String gameID)
    {

    }
    /*
     * returns a list of the join-able games on the server
     * @Pre: None
     * @Post: Object gamesList !=null && isType List<String> of gameIDs
     */
    @Override
    public Object openGames()
    {
        return ClientCommunicator.getSingleton().send("UpdateOpen",new UpdateOpenCommand(), GameList.class);
    }
    /*
     * returns a list of in-progress games authToken user is currently in
     * @Pre: 0 < authToken < 10000
     * @Post: Object gamesList !=null && isType List<String> of gameIDs
     */
    @Override
    public Object inProgressGames(AuthToken authToken)
    {
        return ClientCommunicator.getSingleton().send("UpdateInProgress",new UpdateInProgressCommand(authToken),GameList.class);
    }
    /*
     * returns a list of unstarted games authToken user is currently in
     * @Pre: 0 < authToken < 10000
     * @Post: Object gamesList !=null && isType List<String> of gameIDs
     */
    @Override
    public Object unstartedGames(AuthToken authToken)
    {
        return ClientCommunicator.getSingleton().send("UpdateUnstarted",new UpdateUnstartedCommand(authToken),GameList.class);
    }
    /*
     * flags gameID game as started. Initializes game objects for gameID
     * @Pre: 0 < gameID < 10000
     * @Post: None
     */
    @Override
    public StartedGameResult startGame(String gameID)
    {
        return (StartedGameResult) ClientCommunicator.getSingleton().send("Start",new StartCommand(gameID),StartedGameResult.class);
    }
}
