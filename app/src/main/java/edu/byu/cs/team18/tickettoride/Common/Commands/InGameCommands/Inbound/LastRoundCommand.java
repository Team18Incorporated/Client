package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.Inbound;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by price on 11/18/2017.
 */

public class LastRoundCommand implements ICommand {

    @Override
    public String getSuffix() {
        return "InGameCommands.LastRoundCommand";
    }

    @Override
    public void execute() {
        ClientFacade.getClientFacade().lastRound();
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }
}
