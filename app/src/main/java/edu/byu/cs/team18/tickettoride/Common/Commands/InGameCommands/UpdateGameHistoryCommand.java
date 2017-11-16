package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import java.util.Date;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 11/1/2017.
 */

public class UpdateGameHistoryCommand implements ICommand {
    private AuthToken authToken;
    private String gameID;
    private Date date;
    private String className;


    @Override

    public String getSuffix() {
        return "InGameCommands.UpdateGameHistory";
    }

    @Override
    public void execute() {

    }


    public UpdateGameHistoryCommand(AuthToken authToken, String gameID, Date date) {
        this.authToken = authToken;
        this.gameID = gameID;
        this.date=date;
        className=getClass().getName();

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

    @Override
    public String getClassName() {
        return getClass().getName();
    }
}
