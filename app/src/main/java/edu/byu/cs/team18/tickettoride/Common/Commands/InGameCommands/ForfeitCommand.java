package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;


import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by Antman 537 on 11/27/2017.
 */

public class ForfeitCommand implements ICommand {
    private AuthToken authToken;
    private String gameID;

    public ForfeitCommand(AuthToken tokenIn, String idIn){
        authToken = tokenIn;
        gameID = idIn;
    }

    @Override
    public String getSuffix() {
        return "InGameCommands.Forfeit";
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public void execute() {

    }
}
