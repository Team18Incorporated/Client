package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.PlayerInfo;

/**
 * Created by dasolomo on 11/18/17.
 */

public class UpdateTrainPiecesCommand implements ICommand {
    private String playerID;
    private int pieces;
    private String className;

    public UpdateTrainPiecesCommand(String playerID, int pieces) {
        this.playerID = playerID;
        this.pieces = pieces;
        className=getClass().getName();
    }

    @Override
    public String getSuffix() {
        return null;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void execute() {
        if(playerID.equals(ClientModel.SINGLETON.getCurrentPlayer().getPlayerID()))
        {
            ClientModel.SINGLETON.getCurrentPlayer().setNumTrainPieces(pieces);
        }
        else
        {
            PlayerInfo player=null;
            for(PlayerInfo p : ClientModel.SINGLETON.getCurrentGame().getPlayerList())
            {
                if (p.getPlayerID().equals(playerID))
                {
                 player=p;
                }
            }
            player.setNumTrainPieces(pieces);
        }
    }
}
