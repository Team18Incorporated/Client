package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by price on 11/18/2017.
 */

public class IncrementTurnCommand implements ICommand {

    private int turn;
    private String className = getClass().getName();
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


    public String toString()
    {
        return "It's "+ ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(turn).getPlayerName()+"'s turn.";
    }
}
