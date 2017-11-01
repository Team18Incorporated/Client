package edu.byu.cs.team18.tickettoride.Common;


import java.util.List;

import edu.byu.cs.team18.tickettoride.Common.Commands.CommandList;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.ClaimRouteCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.ShowDestinationChoicesCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateChatHistoryCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateFaceUpCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateScoreCommand;

public interface IServer {
	/*
	 * logs user in and returns authToken. Throws login exceptions
	 * @Pre: user&&password !=null && != ""
	 * @Post: 0 < int authToken < 10000 || exception
	 */
	public AuthToken userLogin(String user, String password);
	/*
	 * Registers a new user and logs them in. Returns authToken
	 * Throws existingUser exception
	 * @Pre: user&&password != null && != ""
	 * @Post: 0 < int authToken < 10000 || existingUser exception 
	 */
	public AuthToken registerUser(String user, String password);
	/*
	 * creates a new game, using authToken to determine the creator. Returns gameID
	 * @Pre: 0 < authToken < 10000
	 * @Post: 0 < int gameID < 10000
	 */
	public GameInfo newGame(AuthToken authToken, String name);
	/*
	 * adds authToken user to gameID game
	 * @Pre: 0 < authToken&&gameID < 10000
	 * @Post: None
	 */
	public GameInfo join(AuthToken authToken, String gameID);
	/*
	 * removes authToken user from gameID game
	 * @Pre: 0 < authToken&&gameID < 10000
	 * @Post: None
	 */
	public void leave(AuthToken authToken, String gameID);
	/*
	 * returns a list of the join-able games on the server
	 * @Pre: None
	 * @Post: Object gamesList !=null && isType List<String> of gameIDs
	 */
	public Object openGames(AuthToken token);
	/*
	 * returns a list of in-progress games authToken user is currently in
	 * @Pre: 0 < authToken < 10000
	 * @Post: Object gamesList !=null && isType List<String> of gameIDs
	 */
	public Object inProgressGames(AuthToken authToken);
	/*
	 * returns a list of unstarted games authToken user is currently in
	 * @Pre: 0 < authToken < 10000
	 * @Post: Object gamesList !=null && isType List<String> of gameIDs
	 */
	public Object unstartedGames(AuthToken authToken);
	/*
	 * flags gameID game as started. Initializes game objects for gameID
	 * @Pre: 0 < gameID < 10000
	 * @Post: None
	 */
	public StartedGameResult startGame(String gameID);

	/*
	* @pre route is not null
	* @pre 0 < authToken&&gameID < 10000
	* @post returns a list of commands to be executed on the client.
	 */
	public CommandList claimRoute(AuthToken authToken, String gameID, Route route);

	/*
	* @pre 0 < authToken&&gameID < 10000
	* @post returns a list of commands (UpdateTrainHand and UpdateTrainDeckSize)
	 */
	public CommandList drawTrainCard(AuthToken authToken, String gameID);

	/*
	* @pre 0 < authToken&&gameID < 10000
	* @post returns a command that displays the player's choosable destination cards.
 	*/
	public ShowDestinationChoicesCommand drawDestinationCard(AuthToken authToken, String gameID);

	/*
	* @pre 0 < authToken&&gameID < 10000
	* @post returns a list of commands
	 */
	public CommandList sendBackDestinations(AuthToken authToken, String gameID, List<DestinationCard> list);

	/*
	* @pre 0 < authToken&&gameID < 10000
	* @pre card is not null
	* @post returns a list containing an update hand command and update
	 */
	public UpdateFaceUpCommand drawFromFaceUp(AuthToken authToken, String gameID, TrainCard card);

	public UpdateChatHistoryCommand sendChat(AuthToken authToken, ChatMessage chatMessage);

	public CommandList getHistory(AuthToken token, String gameID);
}
