package edu.byu.cs.team18.tickettoride;


import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.ChatMessage;
import edu.byu.cs.team18.tickettoride.Common.Commands.CommandList;
import edu.byu.cs.team18.tickettoride.Common.Commands.CreateCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.ClaimRouteCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.DrawDestinationCardCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.DrawFromFaceUpCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.DrawTrainCardCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.EndTurnCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.ForfeitCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.SendBackDestinationsCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.SendChatCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.Inbound.ShowDestinationChoicesCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.Inbound.UpdateChatHistoryCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.Inbound.UpdateFaceUpCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateGameHistoryCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.JoinCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.LoginCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.RegisterCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.StartCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.UpdateInProgressCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.UpdateOpenCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.UpdateUnstartedCommand;
import edu.byu.cs.team18.tickettoride.Common.DestinationCard;
import edu.byu.cs.team18.tickettoride.Common.GameInfo;
import edu.byu.cs.team18.tickettoride.Common.GameList;
import edu.byu.cs.team18.tickettoride.Common.IServer;
import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.Common.StartedGameResult;


import edu.byu.cs.team18.tickettoride.Common.User;




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
    public User userLogin(String user, String password)
    {
        return (User) ClientCommunicator.getSingleton().sendCmd(new LoginCommand(user, password), User.class);
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
        AuthToken auth= (AuthToken) ClientCommunicator.getSingleton().sendCmd(new RegisterCommand(user,password), AuthToken.class);
        return auth;
    }
    /*
     * creates a new game, using authToken to determine the creator. Returns gameID
     * @Pre: 0 < authToken < 10000
     * @Post: 0 < int gameID < 10000
     */
    @Override
    public GameInfo newGame(AuthToken authToken, String name)
    {
        return (GameInfo) ClientCommunicator.getSingleton().sendCmd(new CreateCommand(name, authToken), GameInfo.class);
    }
    /*
     * adds authToken user to gameID game
     * @Pre: 0 < authToken&&gameID < 10000
     * @Post: None
     */
    @Override
    public GameInfo join(AuthToken authToken, String gameID)
    {
        return (GameInfo) ClientCommunicator.getSingleton().sendCmd(new JoinCommand(gameID,authToken), GameInfo.class);
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
    public Object openGames(AuthToken token)
    {
        return (GameList) ClientCommunicator.getSingleton().sendCmd(new UpdateOpenCommand(token), GameList.class);
    }
    /*
     * returns a list of in-progress games authToken user is currently in
     * @Pre: 0 < authToken < 10000
     * @Post: Object gamesList !=null && isType List<String> of gameIDs
     */
    @Override
    public Object inProgressGames(AuthToken authToken)
    {
        return (GameList) ClientCommunicator.getSingleton().sendCmd(new UpdateInProgressCommand(authToken), GameList.class);
    }
    /*
     * returns a list of unstarted games authToken user is currently in
     * @Pre: 0 < authToken < 10000
     * @Post: Object gamesList !=null && isType List<String> of gameIDs
     */
    @Override
    public Object unstartedGames(AuthToken authToken)
    {
        return (GameList) ClientCommunicator.getSingleton().sendCmd(new UpdateUnstartedCommand(authToken), GameList.class);
    }
    /*
     * flags gameID game as started. Initializes game objects for gameID
     * @Pre: 0 < gameID < 10000
     * @Post: None
     */
    @Override
    public StartedGameResult startGame(String gameID,AuthToken token)
    {
        return (StartedGameResult) ClientCommunicator.getSingleton().sendCmd(new StartCommand(gameID, token), StartedGameResult.class);

    }

    /*
	* @pre route is not null
	* @pre 0 < authToken&&gameID < 10000
	* @post returns a list of commands to be executed on the client.
	 */
    @Override
    public void claimRoute(AuthToken authToken, String gameID, Route route, ArrayList<Integer> discard)
    {
        ClientCommunicator.getSingleton().sendCmd(new ClaimRouteCommand(authToken,gameID,route, discard), CommandList.class);
    }

    /*
    * @pre 0 < authToken&&gameID < 10000
    * @post returns a list of commands (UpdateTrainHand and UpdateTrainDeckSize)
     */
    @Override
    public void drawTrainCard(AuthToken authToken, String gameID)
    {
        ClientCommunicator.getSingleton().sendCmd(new DrawTrainCardCommand(authToken,gameID), CommandList.class);
    }

    /*
     * @pre 0 < authToken&&gameID < 10000
     * @post returns a command which shows choosable cards in the GUI.
     */
    @Override
    public void drawDestinationCard(AuthToken authToken, String gameID)
    {
      ClientCommunicator.getSingleton()
                .sendCmd(new DrawDestinationCardCommand(authToken,gameID), ShowDestinationChoicesCommand.class);
    }

    @Override
    public void sendBackDestinations(AuthToken authToken, String gameID, List<DestinationCard> list)
    {
       ClientCommunicator.getSingleton()
                .sendCmd(new SendBackDestinationsCommand(authToken,gameID,list), CommandList.class);
    }

    @Override
    public void drawFromFaceUp(AuthToken authToken, String gameID, int card)
    {
       ClientCommunicator.getSingleton()
                .sendCmd(new DrawFromFaceUpCommand(authToken,gameID,card), UpdateFaceUpCommand.class);
    }

    @Override
    public void sendChat(AuthToken authToken, ChatMessage chatMessage, String gameID)
    {
        ClientCommunicator.getSingleton().sendCmd(new SendChatCommand(chatMessage, authToken, gameID), UpdateChatHistoryCommand.class);
    }

    @Override
    public CommandList getHistory(AuthToken token, String gameID, int index)//Date date)
    {
        return (CommandList)ClientCommunicator.getSingleton()
                .sendCmd(new UpdateGameHistoryCommand(token, gameID, index),CommandList.class);
    }

    @Override
    public void endTurn(AuthToken token, String gameID, String playerID)
    {
        ClientCommunicator.getSingleton().sendCmd(new EndTurnCommand(token, gameID, playerID), EndTurnCommand.class);
    }

    @Override
    public void forfeit(AuthToken token, String gameID){
        ClientCommunicator.getSingleton().sendCmd(new ForfeitCommand(token,gameID), ForfeitCommand.class);
    }

}
