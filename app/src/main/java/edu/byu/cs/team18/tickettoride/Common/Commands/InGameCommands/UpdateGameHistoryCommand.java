package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 11/1/2017.
 */

public class UpdateGameHistoryCommand implements ICommand {
    private AuthToken authToken;
    private String gameID;



    @Override
    public String getSuffix() {
        return "UpdateGameHistory";
    }

    @Override
    public void execute() {

    }


    public UpdateGameHistoryCommand(AuthToken authToken, String gameID) {
        this.authToken = authToken;
        this.gameID = gameID;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }
}
