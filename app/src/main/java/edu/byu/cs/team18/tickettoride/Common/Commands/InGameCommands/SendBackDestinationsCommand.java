package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import java.util.List;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.DestinationCard;

/**
 * Created by abram on 10/23/2017.
 */

public class SendBackDestinationsCommand implements ICommand {
    private AuthToken authToken;
    private String gameID;
    private List<DestinationCard> list;
    private String className;

    @Override
    public String getSuffix() {
        return "InGameCommands.SendBackDestinations";
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public void execute()
    {
        //To be executed by the server
    }


    public SendBackDestinationsCommand(AuthToken authToken, String gameID, List<DestinationCard> list) {
        this.authToken = authToken;
        this.gameID = gameID;
        this.list = list;
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

    public List<DestinationCard> getList() {
        return list;
    }

    public void setList(List<DestinationCard> list) {
        this.list = list;
    }
}
