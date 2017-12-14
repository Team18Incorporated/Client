package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.Inbound;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.PlayerInfo;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;

/**
 * Created by dasolomo on 11/18/17.
 */

public class SetPlayerHandCommand implements ICommand {

    private ArrayList<TrainCard> hand;
    private String className;

    public SetPlayerHandCommand(ArrayList<TrainCard> hand)
    {
        this.hand=hand;
        className=getClass().getName();

    }

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
        ClientModel.SINGLETON.getCurrentPlayer().setHand(hand);

        String currentPlayer= ClientModel.SINGLETON.getCurrentPlayer().getPlayerID();
        for(PlayerInfo pi :ClientModel.SINGLETON.getCurrentGame().getPlayerList())
        {
            if(pi.getPlayerID().equals(currentPlayer))
            {
                pi.setNumTrainCards(hand.size());
            }
        }
    }
}
