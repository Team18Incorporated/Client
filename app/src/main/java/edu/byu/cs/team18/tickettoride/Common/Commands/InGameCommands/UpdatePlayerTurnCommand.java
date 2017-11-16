package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by Solomons on 10/31/2017.
 */

public class UpdatePlayerTurnCommand implements ICommand {
    private int playerIndex;
    private String className;

    public UpdatePlayerTurnCommand(int playerIndex) {
        this.playerIndex = playerIndex;
        className=getClass().getName();
    }

    @Override
    public String getSuffix() {
        return null;
    }

    @Override
    public void execute() {
        ClientFacade.getClientFacade().updatePlayerTurn(playerIndex);
    }

    public String toString()
    {
        return ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(playerIndex).getPlayerName()+"'s turn";
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }
}
