package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

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

    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }
}
