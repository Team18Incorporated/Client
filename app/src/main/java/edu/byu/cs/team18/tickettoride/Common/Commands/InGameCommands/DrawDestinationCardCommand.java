package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 10/20/2017.
 */

public class DrawDestinationCardCommand implements ICommand{

    private AuthToken authToken;
    private String gameID;
    private String className;

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public String getSuffix() {
        return "InGameCommands.DrawDestinationCard";
    }

    @Override
    public void execute()
    {
        //To be done on the server
    }

    public DrawDestinationCardCommand(AuthToken authToken, String gameID) {
        this.authToken = authToken;
        this.gameID = gameID;
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
}
