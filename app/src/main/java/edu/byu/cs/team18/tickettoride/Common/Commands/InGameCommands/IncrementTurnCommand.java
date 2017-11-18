package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by price on 11/18/2017.
 */

public class IncrementTurnCommand implements ICommand {

    int turn;
    @Override
    public String getSuffix() {
        return "InGameCommands.LastRoundCommand";
    }

    @Override
    public void execute() {
        ClientFacade.getClientFacade().incrementTurn(turn);
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }
}
