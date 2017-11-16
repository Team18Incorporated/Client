package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.States.StartTurnState;

/**
 * Created by abram on 11/13/2017.
 */

public class StartTurnCommand implements ICommand {
    private String className;


    public StartTurnCommand()
    {
        className=getClass().getName();
    }

    public void execute()
    {
        ClientModel.SINGLETON.setState(StartTurnState.SINGLETON);
    }

    @Override
    public String getSuffix() {
        return "StartTurn";
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }
}
