package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by Antman 537 on 11/20/2017.
 */

public class EndgameCommand implements ICommand {
    @Override
    public String getSuffix() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
    }

    @Override
    public void execute() {
        ClientFacade.getClientFacade().endgame();
    }
}
