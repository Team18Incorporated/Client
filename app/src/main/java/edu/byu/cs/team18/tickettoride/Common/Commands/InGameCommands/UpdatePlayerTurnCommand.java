package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by Solomons on 10/31/2017.
 */

public class UpdatePlayerTurnCommand implements ICommand {
    private int playerIndex;

    public UpdatePlayerTurnCommand(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    @Override
    public String getSuffix() {
        return null;
    }

    @Override
    public void execute() {
        ClientModel.SINGLETON.getCurrentGame().setPlayerTurn(playerIndex);
    }

    public String toString()
    {
        return ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(playerIndex).getPlayerName()+"'s turn";
    }
}
